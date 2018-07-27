/**
 */
package es.um.nosql.s13e.NoSQLSchema;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PSet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.PSet#getElementType <em>Element Type</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getPSet()
 * @model
 * @generated
 */
public interface PSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Type</em>' containment reference.
	 * @see #setElementType(Type)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getPSet_ElementType()
	 * @model containment="true"
	 * @generated
	 */
	Type getElementType();

	/**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.PSet#getElementType <em>Element Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Type</em>' containment reference.
	 * @see #getElementType()
	 * @generated
	 */
	void setElementType(Type value);

} // PSet
