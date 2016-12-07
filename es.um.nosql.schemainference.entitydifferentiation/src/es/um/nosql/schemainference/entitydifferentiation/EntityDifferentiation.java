/**
 */
package es.um.nosql.schemainference.entitydifferentiation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Differentiation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation#getEntityDiffSpecs <em>Entity Diff Specs</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage#getEntityDifferentiation()
 * @model
 * @generated
 */
public interface EntityDifferentiation extends EObject {
	/**
	 * Returns the value of the '<em><b>Entity Diff Specs</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity Diff Specs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity Diff Specs</em>' containment reference list.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage#getEntityDifferentiation_EntityDiffSpecs()
	 * @model containment="true"
	 * @generated
	 */
	EList<EntityDiffSpec> getEntityDiffSpecs();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage#getEntityDifferentiation_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // EntityDifferentiation
