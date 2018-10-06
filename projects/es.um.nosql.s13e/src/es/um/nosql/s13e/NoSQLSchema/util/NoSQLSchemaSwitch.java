/**
 */
package es.um.nosql.s13e.NoSQLSchema.util;

import es.um.nosql.s13e.NoSQLSchema.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage
 * @generated
 */
public class NoSQLSchemaSwitch<T> extends Switch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static NoSQLSchemaPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NoSQLSchemaSwitch() {
    if (modelPackage == null) {
      modelPackage = NoSQLSchemaPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case NoSQLSchemaPackage.NO_SQL_SCHEMA: {
        NoSQLSchema noSQLSchema = (NoSQLSchema)theEObject;
        T result = caseNoSQLSchema(noSQLSchema);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.ENTITY_CLASS: {
        EntityClass entityClass = (EntityClass)theEObject;
        T result = caseEntityClass(entityClass);
        if (result == null) result = caseClassifier(entityClass);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.STRUCTURAL_VARIATION: {
        StructuralVariation structuralVariation = (StructuralVariation)theEObject;
        T result = caseStructuralVariation(structuralVariation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.PROPERTY: {
        Property property = (Property)theEObject;
        T result = caseProperty(property);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.ATTRIBUTE: {
        Attribute attribute = (Attribute)theEObject;
        T result = caseAttribute(attribute);
        if (result == null) result = caseProperty(attribute);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.TYPE: {
        Type type = (Type)theEObject;
        T result = caseType(type);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.PLIST: {
        PList pList = (PList)theEObject;
        T result = casePList(pList);
        if (result == null) result = caseType(pList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.ASSOCIATION: {
        Association association = (Association)theEObject;
        T result = caseAssociation(association);
        if (result == null) result = caseProperty(association);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.REFERENCE: {
        Reference reference = (Reference)theEObject;
        T result = caseReference(reference);
        if (result == null) result = caseAssociation(reference);
        if (result == null) result = caseProperty(reference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.AGGREGATE: {
        Aggregate aggregate = (Aggregate)theEObject;
        T result = caseAggregate(aggregate);
        if (result == null) result = caseAssociation(aggregate);
        if (result == null) result = caseProperty(aggregate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.PRIMITIVE_TYPE: {
        PrimitiveType primitiveType = (PrimitiveType)theEObject;
        T result = casePrimitiveType(primitiveType);
        if (result == null) result = caseType(primitiveType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.NULL: {
        Null null_ = (Null)theEObject;
        T result = caseNull(null_);
        if (result == null) result = caseProperty(null_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.REFERENCE_CLASS: {
        ReferenceClass referenceClass = (ReferenceClass)theEObject;
        T result = caseReferenceClass(referenceClass);
        if (result == null) result = caseClassifier(referenceClass);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.CLASSIFIER: {
        Classifier classifier = (Classifier)theEObject;
        T result = caseClassifier(classifier);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.PMAP: {
        PMap pMap = (PMap)theEObject;
        T result = casePMap(pMap);
        if (result == null) result = caseType(pMap);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.PSET: {
        PSet pSet = (PSet)theEObject;
        T result = casePSet(pSet);
        if (result == null) result = caseType(pSet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case NoSQLSchemaPackage.PTUPLE: {
        PTuple pTuple = (PTuple)theEObject;
        T result = casePTuple(pTuple);
        if (result == null) result = caseType(pTuple);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>No SQL Schema</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>No SQL Schema</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNoSQLSchema(NoSQLSchema object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Entity Class</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity Class</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEntityClass(EntityClass object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Structural Variation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structural Variation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStructuralVariation(StructuralVariation object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProperty(Property object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAttribute(Attribute object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseType(Type object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>PList</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>PList</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePList(PList object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Association</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Association</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssociation(Association object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReference(Reference object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Aggregate</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Aggregate</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAggregate(Aggregate object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePrimitiveType(PrimitiveType object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Null</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Null</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNull(Null object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Reference Class</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reference Class</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReferenceClass(ReferenceClass object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Classifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Classifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClassifier(Classifier object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>PMap</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>PMap</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePMap(PMap object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>PSet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>PSet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePSet(PSet object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>PTuple</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>PTuple</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePTuple(PTuple object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object) {
    return null;
  }

} //NoSQLSchemaSwitch
