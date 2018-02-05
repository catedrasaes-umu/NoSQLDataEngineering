/**
 */
package es.um.nosql.schemainference.dsl4mongoose;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.FieldParameter#getFieldName <em>Field Name</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getFieldParameter()
 * @model abstract="true"
 * @generated
 */
public interface FieldParameter extends EObject {
	/**
	 * Returns the value of the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Name</em>' attribute.
	 * @see #setFieldName(String)
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getFieldParameter_FieldName()
	 * @model
	 * @generated
	 */
	String getFieldName();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.dsl4mongoose.FieldParameter#getFieldName <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Name</em>' attribute.
	 * @see #getFieldName()
	 * @generated
	 */
	void setFieldName(String value);

} // FieldParameter
