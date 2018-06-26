/**
 */
package es.um.nosql.s13e.NoSQLSchema;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aggregate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.Aggregate#getRefTo <em>Ref To</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getAggregate()
 * @model
 * @generated
 */
public interface Aggregate extends Association
{
  /**
   * Returns the value of the '<em><b>Ref To</b></em>' reference list.
   * The list contents are of type {@link es.um.nosql.s13e.NoSQLSchema.EntityVersion}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref To</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref To</em>' reference list.
   * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getAggregate_RefTo()
   * @model required="true"
   * @generated
   */
  EList<EntityVersion> getRefTo();

} // Aggregate
