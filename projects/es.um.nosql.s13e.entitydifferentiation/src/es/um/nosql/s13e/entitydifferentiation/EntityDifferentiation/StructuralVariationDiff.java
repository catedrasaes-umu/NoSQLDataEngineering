/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation;

import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structural Variation Diff</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getPropertySpecs <em>Property Specs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getVariation <em>Variation</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getNotProps <em>Not Props</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getStructuralVariationDiff()
 * @model
 * @generated
 */
public interface StructuralVariationDiff extends EObject {
  /**
   * Returns the value of the '<em><b>Property Specs</b></em>' containment reference list.
   * The list contents are of type {@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property Specs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Specs</em>' containment reference list.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getStructuralVariationDiff_PropertySpecs()
   * @model containment="true"
   * @generated
   */
  EList<PropertySpec> getPropertySpecs();

  /**
   * Returns the value of the '<em><b>Variation</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variation</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variation</em>' reference.
   * @see #setVariation(StructuralVariation)
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getStructuralVariationDiff_Variation()
   * @model required="true"
   * @generated
   */
  StructuralVariation getVariation();

  /**
   * Sets the value of the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getVariation <em>Variation</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variation</em>' reference.
   * @see #getVariation()
   * @generated
   */
  void setVariation(StructuralVariation value);

  /**
   * Returns the value of the '<em><b>Not Props</b></em>' containment reference list.
   * The list contents are of type {@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Not Props</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Not Props</em>' containment reference list.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getStructuralVariationDiff_NotProps()
   * @model containment="true"
   * @generated
   */
  EList<PropertySpec> getNotProps();

} // StructuralVariationDiff
