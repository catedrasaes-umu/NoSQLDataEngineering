/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.util;

import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.*;

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
 * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage
 * @generated
 */
public class EntityDifferentiationSwitch<T> extends Switch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static EntityDifferentiationPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityDifferentiationSwitch() {
    if (modelPackage == null) {
      modelPackage = EntityDifferentiationPackage.eINSTANCE;
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
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION: {
        EntityDifferentiation entityDifferentiation = (EntityDifferentiation)theEObject;
        T result = caseEntityDifferentiation(entityDifferentiation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EntityDifferentiationPackage.ENTITY_DIFF: {
        EntityDiff entityDiff = (EntityDiff)theEObject;
        T result = caseEntityDiff(entityDiff);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EntityDifferentiationPackage.PROPERTY_SPEC: {
        PropertySpec propertySpec = (PropertySpec)theEObject;
        T result = casePropertySpec(propertySpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF: {
        StructuralVariationDiff structuralVariationDiff = (StructuralVariationDiff)theEObject;
        T result = caseStructuralVariationDiff(structuralVariationDiff);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Entity Differentiation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity Differentiation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEntityDifferentiation(EntityDifferentiation object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Entity Diff</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity Diff</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEntityDiff(EntityDiff object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertySpec(PropertySpec object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Structural Variation Diff</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structural Variation Diff</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStructuralVariationDiff(StructuralVariationDiff object) {
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

} //EntityDifferentiationSwitch
