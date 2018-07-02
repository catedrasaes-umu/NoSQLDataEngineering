/**
 */
package es.um.nosql.s13e.EntityDifferentiation;

import es.um.nosql.s13e.NoSQLSchema.Entity;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Diff Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getEntity <em>Entity</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getEntityVariationProps <em>Entity Variation Props</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getCommonProps <em>Common Props</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage#getEntityDiffSpec()
 * @model
 * @generated
 */
public interface EntityDiffSpec extends EObject
{
  /**
   * Returns the value of the '<em><b>Entity</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity</em>' reference.
   * @see #setEntity(Entity)
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage#getEntityDiffSpec_Entity()
   * @model required="true"
   * @generated
   */
  Entity getEntity();

  /**
   * Sets the value of the '{@link es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getEntity <em>Entity</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entity</em>' reference.
   * @see #getEntity()
   * @generated
   */
  void setEntity(Entity value);

  /**
   * Returns the value of the '<em><b>Entity Variation Props</b></em>' containment reference list.
   * The list contents are of type {@link es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity Variation Props</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity Variation Props</em>' containment reference list.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage#getEntityDiffSpec_EntityVariationProps()
   * @model containment="true"
   * @generated
   */
  EList<EntityVariationProp> getEntityVariationProps();

  /**
   * Returns the value of the '<em><b>Common Props</b></em>' containment reference list.
   * The list contents are of type {@link es.um.nosql.s13e.EntityDifferentiation.PropertySpec}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Common Props</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Common Props</em>' containment reference list.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage#getEntityDiffSpec_CommonProps()
   * @model containment="true"
   * @generated
   */
  EList<PropertySpec> getCommonProps();

} // EntityDiffSpec
