/**
 */
package es.um.nosql.s13e.NoSQLSchema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.Entity#getName <em>Name</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.Entity#getEntityVariations <em>Entity Variations</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.Entity#getParents <em>Parents</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getEntity()
 * @model
 * @generated
 */
public interface Entity extends EObject
{
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
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getEntity_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
  String getName();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.Entity#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
  void setName(String value);

  /**
	 * Returns the value of the '<em><b>Entity Variations</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.s13e.NoSQLSchema.EntityVariation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity Variations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity Variations</em>' containment reference list.
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getEntity_EntityVariations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<EntityVariation> getEntityVariations();

		/**
	 * Returns the value of the '<em><b>Parents</b></em>' reference list.
	 * The list contents are of type {@link es.um.nosql.s13e.NoSQLSchema.Entity}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parents</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Parents</em>' reference list.
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getEntity_Parents()
	 * @model
	 * @generated
	 */
  EList<Entity> getParents();

} // Entity
