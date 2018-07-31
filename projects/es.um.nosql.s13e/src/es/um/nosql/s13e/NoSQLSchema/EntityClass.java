/**
 */
package es.um.nosql.s13e.NoSQLSchema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.EntityClass#isRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getEntityClass()
 * @model
 * @generated
 */
public interface EntityClass extends Classifier
{
  /**
   * Returns the value of the '<em><b>Root</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Root</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root</em>' attribute.
   * @see #setRoot(boolean)
   * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getEntityClass_Root()
   * @model default="false"
   * @generated
   */
  boolean isRoot();

  /**
   * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.EntityClass#isRoot <em>Root</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root</em>' attribute.
   * @see #isRoot()
   * @generated
   */
  void setRoot(boolean value);

} // EntityClass
