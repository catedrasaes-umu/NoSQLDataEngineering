/**
 */
package es.um.nosql.s13e.DecisionTree.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import es.um.nosql.s13e.DecisionTree.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DecisionTreeFactoryImpl extends EFactoryImpl implements DecisionTreeFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static DecisionTreeFactory init()
  {
    try
    {
      DecisionTreeFactory theDecisionTreeFactory = (DecisionTreeFactory)EPackage.Registry.INSTANCE.getEFactory(DecisionTreePackage.eNS_URI);
      if (theDecisionTreeFactory != null)
      {
        return theDecisionTreeFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DecisionTreeFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DecisionTreeFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case DecisionTreePackage.LEAF_NODE: return createLeafNode();
      case DecisionTreePackage.INTERMEDIATE_NODE: return createIntermediateNode();
      case DecisionTreePackage.DECISION_TREE_FOR_ENTITY: return createDecisionTreeForEntity();
      case DecisionTreePackage.DECISION_TREES: return createDecisionTrees();
      case DecisionTreePackage.PROPERTY_SPEC2: return createPropertySpec2();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LeafNode createLeafNode()
  {
    LeafNodeImpl leafNode = new LeafNodeImpl();
    return leafNode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntermediateNode createIntermediateNode()
  {
    IntermediateNodeImpl intermediateNode = new IntermediateNodeImpl();
    return intermediateNode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DecisionTreeForEntity createDecisionTreeForEntity()
  {
    DecisionTreeForEntityImpl decisionTreeForEntity = new DecisionTreeForEntityImpl();
    return decisionTreeForEntity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DecisionTrees createDecisionTrees()
  {
    DecisionTreesImpl decisionTrees = new DecisionTreesImpl();
    return decisionTrees;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertySpec2 createPropertySpec2()
  {
    PropertySpec2Impl propertySpec2 = new PropertySpec2Impl();
    return propertySpec2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DecisionTreePackage getDecisionTreePackage()
  {
    return (DecisionTreePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static DecisionTreePackage getPackage()
  {
    return DecisionTreePackage.eINSTANCE;
  }

} //DecisionTreeFactoryImpl
