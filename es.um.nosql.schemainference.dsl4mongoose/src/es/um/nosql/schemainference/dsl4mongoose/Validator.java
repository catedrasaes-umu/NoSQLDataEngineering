/**
 */
package es.um.nosql.schemainference.dsl4mongoose;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.Validator#getValidatorName <em>Validator Name</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getValidator()
 * @model
 * @generated
 */
public interface Validator extends FieldParameter {
	/**
	 * Returns the value of the '<em><b>Validator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validator Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Validator Name</em>' attribute.
	 * @see #setValidatorName(String)
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getValidator_ValidatorName()
	 * @model
	 * @generated
	 */
	String getValidatorName();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.dsl4mongoose.Validator#getValidatorName <em>Validator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validator Name</em>' attribute.
	 * @see #getValidatorName()
	 * @generated
	 */
	void setValidatorName(String value);

} // Validator
