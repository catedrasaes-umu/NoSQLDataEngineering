/**
 */
package es.um.nosql.s13e.NoSQLSchema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.Reference#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.Reference#getRefsTo <em>Refs To</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.Reference#getOriginalType <em>Original Type</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.Reference#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getReference()
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
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getReference_Opposite()
	 * @model
	 * @generated
	 */
	Reference getOpposite();

	/**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getOpposite <em>Opposite</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opposite</em>' reference.
	 * @see #getOpposite()
	 * @generated
	 */
	void setOpposite(Reference value);

	/**
	 * Returns the value of the '<em><b>Refs To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refs To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refs To</em>' reference.
	 * @see #setRefsTo(EntityClass)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getReference_RefsTo()
	 * @model required="true"
	 * @generated
	 */
	EntityClass getRefsTo();

	/**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getRefsTo <em>Refs To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Refs To</em>' reference.
	 * @see #getRefsTo()
	 * @generated
	 */
	void setRefsTo(EntityClass value);

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
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getReference_OriginalType()
	 * @model
	 * @generated
	 */
	String getOriginalType();

	/**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getOriginalType <em>Original Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Type</em>' attribute.
	 * @see #getOriginalType()
	 * @generated
	 */
	void setOriginalType(String value);

	/**
	 * Returns the value of the '<em><b>Features</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' reference.
	 * @see #setFeatures(StructuralVariation)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getReference_Features()
	 * @model
	 * @generated
	 */
	StructuralVariation getFeatures();

	/**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getFeatures <em>Features</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Features</em>' reference.
	 * @see #getFeatures()
	 * @generated
	 */
	void setFeatures(StructuralVariation value);

} // Reference
