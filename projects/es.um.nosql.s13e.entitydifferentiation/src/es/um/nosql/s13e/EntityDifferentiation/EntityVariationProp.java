/**
 */
package es.um.nosql.s13e.EntityDifferentiation;

import es.um.nosql.s13e.NoSQLSchema.EntityVariation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Variation Prop</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp#getPropertySpecs <em>Property Specs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp#getEntityVariation <em>Entity Variation</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp#getNotProps <em>Not Props</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage#getEntityVariationProp()
 * @model
 * @generated
 */
public interface EntityVariationProp extends EObject
{
  /**
   * Returns the value of the '<em><b>Property Specs</b></em>' containment reference list.
   * The list contents are of type {@link es.um.nosql.s13e.EntityDifferentiation.PropertySpec}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property Specs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Specs</em>' containment reference list.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage#getEntityVariationProp_PropertySpecs()
   * @model containment="true"
   * @generated
   */
  EList<PropertySpec> getPropertySpecs();

  /**
   * Returns the value of the '<em><b>Entity Variation</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity Variation</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity Variation</em>' reference.
   * @see #setEntityVariation(EntityVariation)
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage#getEntityVariationProp_EntityVariation()
   * @model required="true"
   * @generated
   */
  EntityVariation getEntityVariation();

  /**
   * Sets the value of the '{@link es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp#getEntityVariation <em>Entity Variation</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entity Variation</em>' reference.
   * @see #getEntityVariation()
   * @generated
   */
  void setEntityVariation(EntityVariation value);

  /**
   * Returns the value of the '<em><b>Not Props</b></em>' containment reference list.
   * The list contents are of type {@link es.um.nosql.s13e.EntityDifferentiation.PropertySpec}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Not Props</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Not Props</em>' containment reference list.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage#getEntityVariationProp_NotProps()
   * @model containment="true"
   * @generated
   */
  EList<PropertySpec> getNotProps();

} // EntityVariationProp
