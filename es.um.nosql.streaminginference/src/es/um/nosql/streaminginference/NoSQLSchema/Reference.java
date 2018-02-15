/**
 */
package es.um.nosql.streaminginference.NoSQLSchema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.streaminginference.NoSQLSchema.Reference#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link es.um.nosql.streaminginference.NoSQLSchema.Reference#getRefTo <em>Ref To</em>}</li>
 *   <li>{@link es.um.nosql.streaminginference.NoSQLSchema.Reference#getOriginalType <em>Original Type</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage#getReference()
 * @model
 * @generated
 */
public interface Reference extends Association {
	/**
	 * Returns the value of the '<em><b>Opposite</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Opposite</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Opposite</em>' reference.
	 * @see #setOpposite(Reference)
	 * @see es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage#getReference_Opposite()
	 * @model
	 * @generated
	 */
	Reference getOpposite();

	/**
	 * Sets the value of the '{@link es.um.nosql.streaminginference.NoSQLSchema.Reference#getOpposite <em>Opposite</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opposite</em>' reference.
	 * @see #getOpposite()
	 * @generated
	 */
	void setOpposite(Reference value);

	/**
	 * Returns the value of the '<em><b>Ref To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ref To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref To</em>' reference.
	 * @see #setRefTo(Entity)
	 * @see es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage#getReference_RefTo()
	 * @model required="true"
	 * @generated
	 */
	Entity getRefTo();

	/**
	 * Sets the value of the '{@link es.um.nosql.streaminginference.NoSQLSchema.Reference#getRefTo <em>Ref To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref To</em>' reference.
	 * @see #getRefTo()
	 * @generated
	 */
	void setRefTo(Entity value);

	/**
	 * Returns the value of the '<em><b>Original Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Original Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Type</em>' attribute.
	 * @see #setOriginalType(String)
	 * @see es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage#getReference_OriginalType()
	 * @model
	 * @generated
	 */
	String getOriginalType();

	/**
	 * Sets the value of the '{@link es.um.nosql.streaminginference.NoSQLSchema.Reference#getOriginalType <em>Original Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Type</em>' attribute.
	 * @see #getOriginalType()
	 * @generated
	 */
	void setOriginalType(String value);

} // Reference
