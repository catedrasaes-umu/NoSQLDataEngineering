/**
 */
package Variation_Diff.util;

import Variation_Diff.*;

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
 * @see Variation_Diff.Variation_DiffPackage
 * @generated
 */
public class Variation_DiffSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static Variation_DiffPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Variation_DiffSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = Variation_DiffPackage.eINSTANCE;
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
  protected boolean isSwitchFor(EPackage ePackage)
  {
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
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case Variation_DiffPackage.NO_SQL_DIFFERENCES:
      {
        NoSQLDifferences noSQLDifferences = (NoSQLDifferences)theEObject;
        T result = caseNoSQLDifferences(noSQLDifferences);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.TYPE_DIFFERENCE:
      {
        TypeDifference typeDifference = (TypeDifference)theEObject;
        T result = caseTypeDifference(typeDifference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.TYPE_HINT:
      {
        TypeHint typeHint = (TypeHint)theEObject;
        T result = caseTypeHint(typeHint);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.HAS_FIELD:
      {
        HasField hasField = (HasField)theEObject;
        T result = caseHasField(hasField);
        if (result == null) result = caseTypeHint(hasField);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.HAS_NOT_FIELD:
      {
        HasNotField hasNotField = (HasNotField)theEObject;
        T result = caseHasNotField(hasNotField);
        if (result == null) result = caseTypeHint(hasNotField);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.FIELD_TYPE:
      {
        FieldType fieldType = (FieldType)theEObject;
        T result = caseFieldType(fieldType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.PRIMITIVE_TYPE:
      {
        PrimitiveType primitiveType = (PrimitiveType)theEObject;
        T result = casePrimitiveType(primitiveType);
        if (result == null) result = caseFieldType(primitiveType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.TUPLE_TYPE:
      {
        TupleType tupleType = (TupleType)theEObject;
        T result = caseTupleType(tupleType);
        if (result == null) result = caseFieldType(tupleType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.ASSOCIATION_TYPE:
      {
        AssociationType associationType = (AssociationType)theEObject;
        T result = caseAssociationType(associationType);
        if (result == null) result = caseFieldType(associationType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.AGGREGATE_TYPE:
      {
        AggregateType aggregateType = (AggregateType)theEObject;
        T result = caseAggregateType(aggregateType);
        if (result == null) result = caseAssociationType(aggregateType);
        if (result == null) result = caseFieldType(aggregateType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.REFERENCE_TYPE:
      {
        ReferenceType referenceType = (ReferenceType)theEObject;
        T result = caseReferenceType(referenceType);
        if (result == null) result = caseAssociationType(referenceType);
        if (result == null) result = caseFieldType(referenceType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.ENTITY_TYPE:
      {
        EntityType entityType = (EntityType)theEObject;
        T result = caseEntityType(entityType);
        if (result == null) result = caseFieldType(entityType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.HOMOGENEOUS_TUPLE_TYPE:
      {
        HomogeneousTupleType homogeneousTupleType = (HomogeneousTupleType)theEObject;
        T result = caseHomogeneousTupleType(homogeneousTupleType);
        if (result == null) result = caseTupleType(homogeneousTupleType);
        if (result == null) result = caseFieldType(homogeneousTupleType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case Variation_DiffPackage.HETEROGENEOUS_TUPLE_TYPE:
      {
        HeterogeneousTupleType heterogeneousTupleType = (HeterogeneousTupleType)theEObject;
        T result = caseHeterogeneousTupleType(heterogeneousTupleType);
        if (result == null) result = caseTupleType(heterogeneousTupleType);
        if (result == null) result = caseFieldType(heterogeneousTupleType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>No SQL Differences</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>No SQL Differences</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNoSQLDifferences(NoSQLDifferences object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type Difference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Difference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTypeDifference(TypeDifference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type Hint</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Hint</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTypeHint(TypeHint object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Has Field</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Has Field</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHasField(HasField object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Has Not Field</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Has Not Field</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHasNotField(HasNotField object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Field Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Field Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFieldType(FieldType object)
  {
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
  public T casePrimitiveType(PrimitiveType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Tuple Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Tuple Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTupleType(TupleType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Association Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Association Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssociationType(AssociationType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Aggregate Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Aggregate Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAggregateType(AggregateType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Reference Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reference Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReferenceType(ReferenceType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Entity Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEntityType(EntityType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Homogeneous Tuple Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Homogeneous Tuple Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHomogeneousTupleType(HomogeneousTupleType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Heterogeneous Tuple Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Heterogeneous Tuple Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHeterogeneousTupleType(HeterogeneousTupleType object)
  {
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
  public T defaultCase(EObject object)
  {
    return null;
  }

} //Variation_DiffSwitch
