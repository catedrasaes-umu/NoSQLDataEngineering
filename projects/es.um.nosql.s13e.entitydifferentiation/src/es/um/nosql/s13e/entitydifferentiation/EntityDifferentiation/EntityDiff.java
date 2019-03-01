/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Diff</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getEntity <em>Entity</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getVariationDiffs <em>Variation Diffs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getCommonProps <em>Common Props</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getEntityDiff()
 * @model
 * @generated
 */
public interface EntityDiff extends EObject {
	/**
	 * Returns the value of the '<em><b>Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity</em>' reference.
	 * @see #setEntity(EntityClass)
	 * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getEntityDiff_Entity()
	 * @model required="true"
	 * @generated
	 */
	EntityClass getEntity();

	/**
	 * Sets the value of the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getEntity <em>Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity</em>' reference.
	 * @see #getEntity()
	 * @generated
	 */
	void setEntity(EntityClass value);

	/**
	 * Returns the value of the '<em><b>Variation Diffs</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variation Diffs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variation Diffs</em>' containment reference list.
	 * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getEntityDiff_VariationDiffs()
	 * @model containment="true"
	 * @generated
	 */
	EList<StructuralVariationDiff> getVariationDiffs();

	/**
	 * Returns the value of the '<em><b>Common Props</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Common Props</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Common Props</em>' containment reference list.
	 * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getEntityDiff_CommonProps()
	 * @model containment="true"
	 * @generated
	 */
	EList<PropertySpec> getCommonProps();

} // EntityDiff
