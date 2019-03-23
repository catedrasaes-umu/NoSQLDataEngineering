/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;

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
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getEntityDiffs <em>Entity Diffs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getName <em>Name</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getSchema <em>Schema</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getEntityDifferentiation()
 * @model
 * @generated
 */
public interface EntityDifferentiation extends EObject {
  /**
   * Returns the value of the '<em><b>Entity Diffs</b></em>' containment reference list.
   * The list contents are of type {@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity Diffs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity Diffs</em>' containment reference list.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getEntityDifferentiation_EntityDiffs()
   * @model containment="true"
   * @generated
   */
  EList<EntityDiff> getEntityDiffs();

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
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getEntityDifferentiation_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Schema</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Schema</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Schema</em>' reference.
   * @see #setSchema(NoSQLSchema)
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage#getEntityDifferentiation_Schema()
   * @model required="true"
   * @generated
   */
  NoSQLSchema getSchema();

  /**
   * Sets the value of the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getSchema <em>Schema</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Schema</em>' reference.
   * @see #getSchema()
   * @generated
   */
  void setSchema(NoSQLSchema value);

} // EntityDifferentiation
