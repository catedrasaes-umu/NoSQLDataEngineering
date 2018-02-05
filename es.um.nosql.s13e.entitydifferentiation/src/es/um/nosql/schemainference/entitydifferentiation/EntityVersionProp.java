/**
 */
package es.um.nosql.s13e.entitydifferentiation;

import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Version Prop</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityVersionProp#getPropertySpecs <em>Property Specs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityVersionProp#getEntityVersion <em>Entity Version</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityVersionProp#getNotProps <em>Not Props</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.entitydifferentiation.EntitydifferentiationPackage#getEntityVersionProp()
 * @model
 * @generated
 */
public interface EntityVersionProp extends EObject {
	/**
	 * Returns the value of the '<em><b>Property Specs</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.s13e.entitydifferentiation.PropertySpec}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Specs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Specs</em>' containment reference list.
	 * @see es.um.nosql.s13e.entitydifferentiation.EntitydifferentiationPackage#getEntityVersionProp_PropertySpecs()
	 * @model containment="true"
	 * @generated
	 */
	EList<PropertySpec> getPropertySpecs();

	/**
	 * Returns the value of the '<em><b>Entity Version</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity Version</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity Version</em>' reference.
	 * @see #setEntityVersion(EntityVersion)
	 * @see es.um.nosql.s13e.entitydifferentiation.EntitydifferentiationPackage#getEntityVersionProp_EntityVersion()
	 * @model required="true"
	 * @generated
	 */
	EntityVersion getEntityVersion();

	/**
	 * Sets the value of the '{@link es.um.nosql.s13e.entitydifferentiation.EntityVersionProp#getEntityVersion <em>Entity Version</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity Version</em>' reference.
	 * @see #getEntityVersion()
	 * @generated
	 */
	void setEntityVersion(EntityVersion value);

	/**
	 * Returns the value of the '<em><b>Not Props</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.s13e.entitydifferentiation.PropertySpec}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Not Props</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Not Props</em>' containment reference list.
	 * @see es.um.nosql.s13e.entitydifferentiation.EntitydifferentiationPackage#getEntityVersionProp_NotProps()
	 * @model containment="true"
	 * @generated
	 */
	EList<PropertySpec> getNotProps();

} // EntityVersionProp
