package es.um.nosql.s13e.decisiontree.m2t

import org.eclipse.xtend.lib.annotations.Data
import java.io.File
import java.util.List
import es.um.nosql.s13e.NoSQLSchema.Property
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType
import es.um.nosql.s13e.NoSQLSchema.Attribute
import es.um.nosql.s13e.NoSQLSchema.Type
import es.um.nosql.s13e.NoSQLSchema.Tuple
import es.um.nosql.s13e.NoSQLSchema.Reference
import es.um.nosql.s13e.NoSQLSchema.Aggregate
import es.um.nosql.s13e.NoSQLSchema.Entity
import es.um.nosql.s13e.DecisionTree.DecisionTrees
import es.um.nosql.s13e.DecisionTree.DecisionTreeForEntity
import es.um.nosql.s13e.DecisionTree.PropertySpec2
import es.um.nosql.s13e.DecisionTree.IntermediateNode
import es.um.nosql.s13e.DecisionTree.LeafNode
import es.um.nosql.s13e.NoSQLSchema.EntityVariation
import java.util.Deque
import java.util.HashMap
import java.util.Map
import es.um.nosql.s13e.DecisionTree.DecisionTreeNode
import es.um.nosql.s13e.util.ModelLoader
import es.um.nosql.s13e.DecisionTree.DecisionTreePackage
import es.um.nosql.s13e.decisiontree.m2t.commons.Commons

public class DecisionTreeToJS
{
  private var modelName = "";

  private val FILE_EXTENSION = "Classifier.js";

  @Data static class PropertyAndBranch
  {
    PropertySpec2 prop
    boolean branch
  }

  public def m2t(File modelFile, File outputFolder)
  {
    val loader = new ModelLoader(DecisionTreePackage.eINSTANCE);
    val decTrees = loader.load(modelFile, DecisionTrees);

    m2t(decTrees, outputFolder);
  }

  public def m2t(DecisionTrees decTrees, File outputFolder)
  {
    modelName = decTrees.name + "Classifier";

    Commons.WRITE_TO_FILE(outputFolder, decTrees.name + FILE_EXTENSION, genDecisionTree(decTrees));
  }

  private def genDecisionTree(DecisionTrees decTrees)
  '''
  'use strict'

  var «modelName» =
  {
    name: "«decTrees.name»",
    «genCheckFunctions(decTrees.trees)»
  }

  module.exports = «modelName»;
  '''

  private def genCheckFunctions(List<DecisionTreeForEntity> list)
  '''
    «FOR DecisionTreeForEntity decTreeEntity : list SEPARATOR ','»
      «genCheckFunction(decTreeEntity)»
    «ENDFOR»
  '''

  private def genCheckFunction(DecisionTreeForEntity dte)
  {
    val entityName = dte.entity.name.toFirstUpper

    val paths = calcPaths(dte)

    '''
    «entityName»:
    {
      name: "«entityName»",
      entityVariationForObject: function (obj)
      {
        «generateCheckTree(dte, dte.root)»
      },
      «FOR EntityVariation ev : dte.entity.entityVariations SEPARATOR ','»
        «generateSpecificCheck(dte, ev, paths.get(ev))»
      «ENDFOR»
    }
    '''
  }

  private def calcPaths(DecisionTreeForEntity dte) 
  {
    var paths = new HashMap<EntityVariation, Deque<PropertyAndBranch>>;
    calcPaths(paths, newLinkedList(), dte.root);

    return paths;
  }

  private def void calcPaths(Map<EntityVariation, Deque<PropertyAndBranch>> paths, Deque<PropertyAndBranch> checks, DecisionTreeNode node)
  {
    switch node
    {
      LeafNode : paths.put(node.identifiedVariation, newLinkedList(checks.clone))
      IntermediateNode :
      {
        // YES branch
        checks.add(new PropertyAndBranch(node.checkedProperty, true))
        calcPaths(paths, checks, node.yesBranch)
        checks.removeLast

        // NO branch
        checks.add(new PropertyAndBranch(node.checkedProperty, false))
        calcPaths(paths, checks, node.noBranch)
        checks.removeLast
      }
    }
  }

  private def generateSpecificCheck(DecisionTreeForEntity dte, EntityVariation variation, Deque<PropertyAndBranch> checks)
  '''
    checkEV_«dte.entity.name»_«variation.variationId»: function (obj)
    {
      «FOR PropertyAndBranch branch : checks»
      if («if (branch.branch) genPropNot(branch.prop) else genProp(branch.prop) »)
        return false;
      «ENDFOR»

      return true;
    }
  '''

  private def dispatch String generateCheckTree(DecisionTreeForEntity dte, IntermediateNode root) '''
    if («genProp(root.checkedProperty)»)
    {
      «generateCheckTree(dte,root.yesBranch)»
    }
    else
    {
      «generateCheckTree(dte,root.noBranch)»
    }
  ''' 

  private def dispatch generateCheckTree(DecisionTreeForEntity dte, LeafNode node) '''
    return "«dte.entity.name + "_" + node.identifiedVariation.variationId»";
  '''

  private def genProp(PropertySpec2 p)
  {
    if (p.needsTypeCheck)
      '''(("«p.property.name»" in obj) && «genTypeCheck(p.property)»)'''
    else
      '''("«p.property.name»" in obj)'''
  }

  private def genPropNot(PropertySpec2 p)
  {
    if (p.needsTypeCheck)
      '''(!("«p.property.name»" in obj) || !(«genTypeCheck(p.property)»))'''
    else
      '''(!("«p.property.name»" in obj))'''
  }

  private def dispatch genTypeCheck(Property p)
  {
    throw new UnsupportedOperationException("TODO: auto-generated method stub")
  }

  private def dispatch genTypeCheck(Aggregate a)
  '''
    «IF a.lowerBound == 0»
    (obj.«a.name».constructor === Array) &&
        obj.«a.name».every(function(e)
            { return (typeof e === 'object') && !(e.constructor === Array)
                && («FOR rt : a.refTo SEPARATOR " || "»
                «modelName».«(rt.eContainer as Entity).name».checkEV_«(rt.eContainer as Entity).name»_«rt.variationId»(e)
                «ENDFOR»);
            })
    «ELSE»
    «var refToEV = a.refTo.get(0)»
    (typeof obj.«a.name» === 'object') && !(obj.«a.name».constructor === Array)
        && «modelName».«(refToEV.eContainer as Entity).name».checkEV_«(refToEV.eContainer as Entity).name»_«refToEV.variationId»(obj.«a.name»)
    «ENDIF»
  '''

  private def dispatch genTypeCheck(Reference r)
  '''
    «IF r.lowerBound == 0»
    (obj.«r.name».constructor === Array) &&
        (obj.«r.name».every(function(e) { return typeof e === "number";})
         ||
         obj.«r.name».every(function(e) { return typeof e === "string";}))
    «ELSE»
    ((typeof obj.«r.name» === "number") || (typeof obj.«r.name» === "string"))
    «ENDIF»
  '''

  private def dispatch genTypeCheck(Attribute a)
  {
    genTypeCheckLowLevel(a.type, "obj." + a.name);
  }

  private def dispatch genTypeCheckLowLevel(Type type, String name)
  {
    throw new UnsupportedOperationException("TODO: auto-generated method stub")
  }

  private def dispatch CharSequence genTypeCheckLowLevel(PrimitiveType type, String name)
  {
    switch typeName : type.name.toLowerCase
    {
      case Commons.IS_STRING(typeName) : '''(typeof «name» === "string")'''
      case Commons.IS_INT(typeName) : '''(typeof «name» === "number") && («name» % 1 === 0)'''
      case Commons.IS_DOUBLE(typeName) :  '''(typeof «name» === "number") && !(«name» % 1 === 0)'''
      case Commons.IS_BOOLEAN(typeName) : '''(typeof «name» === "boolean")'''
      default: ''''''
    }
  }

  private def dispatch CharSequence genTypeCheckLowLevel(Tuple type, String name)
  {
    '''(«name».constructor === Array) && («name».length === «type.elements.size»)
    «IF type.elements.size != 0»
      &&
      «var i = 0»
      «FOR t : type.elements SEPARATOR " && "»
      «genTypeCheckLowLevel(t, name + '[' + i++ + ']')»
      «ENDFOR»
    «ENDIF»'''
  }
}
