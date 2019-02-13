/**
 */
package es.um.nosql.s13e.NoSQLSchema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PMap</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.PMap#getKeyType <em>Key Type</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.PMap#getValueType <em>Value Type</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getPMap()
 * @model
 * @generated
 */
public interface PMap extends Type {
  /**
	 * Returns the value of the '<em><b>Key Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Key Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Key Type</em>' containment reference.
	 * @see #setKeyType(PrimitiveType)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getPMap_KeyType()
	 * @model containment="true"
	 * @generated
	 */
  PrimitiveType getKeyType();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.PMap#getKeyType <em>Key Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key Type</em>' containment reference.
	 * @see #getKeyType()
	 * @generated
	 */
  void setKeyType(PrimitiveType value);

  /**
	 * Returns the value of the '<em><b>Value Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' containment reference.
	 * @see #setValueType(Type)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getPMap_ValueType()
	 * @model containment="true"
	 * @generated
	 */
  Type getValueType();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.PMap#getValueType <em>Value Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' containment reference.
	 * @see #getValueType()
	 * @generated
	 */
  void setValueType(Type value);

} // PMap
