/**
 */
package es.um.nosql.s13e.NoSQLSchema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structural Variation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getVariationId <em>Variation Id</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getProperties <em>Properties</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getCount <em>Count</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getFirstTimestamp <em>First Timestamp</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getLastTimestamp <em>Last Timestamp</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getContainer <em>Container</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getKey <em>Key</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getStructuralVariation()
 * @model
 * @generated
 */
public interface StructuralVariation extends EObject {
  /**
	 * Returns the value of the '<em><b>Variation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variation Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Variation Id</em>' attribute.
	 * @see #setVariationId(int)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getStructuralVariation_VariationId()
	 * @model required="true"
	 * @generated
	 */
  int getVariationId();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getVariationId <em>Variation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variation Id</em>' attribute.
	 * @see #getVariationId()
	 * @generated
	 */
  void setVariationId(int value);

  /**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.s13e.NoSQLSchema.Property}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getStructuralVariation_Properties()
	 * @model containment="true" required="true"
	 * @generated
	 */
  EList<Property> getProperties();

  /**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(long)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getStructuralVariation_Count()
	 * @model default="0"
	 * @generated
	 */
  long getCount();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
  void setCount(long value);

  /**
	 * Returns the value of the '<em><b>First Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>First Timestamp</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>First Timestamp</em>' attribute.
	 * @see #setFirstTimestamp(long)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getStructuralVariation_FirstTimestamp()
	 * @model
	 * @generated
	 */
  long getFirstTimestamp();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getFirstTimestamp <em>First Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Timestamp</em>' attribute.
	 * @see #getFirstTimestamp()
	 * @generated
	 */
  void setFirstTimestamp(long value);

  /**
	 * Returns the value of the '<em><b>Last Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Last Timestamp</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Timestamp</em>' attribute.
	 * @see #setLastTimestamp(long)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getStructuralVariation_LastTimestamp()
	 * @model
	 * @generated
	 */
  long getLastTimestamp();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getLastTimestamp <em>Last Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Timestamp</em>' attribute.
	 * @see #getLastTimestamp()
	 * @generated
	 */
  void setLastTimestamp(long value);

  /**
	 * Returns the value of the '<em><b>Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link es.um.nosql.s13e.NoSQLSchema.SchemaType#getVariations <em>Variations</em>}'.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Container</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' container reference.
	 * @see #setContainer(SchemaType)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getStructuralVariation_Container()
	 * @see es.um.nosql.s13e.NoSQLSchema.SchemaType#getVariations
	 * @model opposite="variations" required="true" transient="false"
	 * @generated
	 */
  SchemaType getContainer();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getContainer <em>Container</em>}' container reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' container reference.
	 * @see #getContainer()
	 * @generated
	 */
  void setContainer(SchemaType value);

		/**
	 * Returns the value of the '<em><b>Key</b></em>' reference list.
	 * The list contents are of type {@link es.um.nosql.s13e.NoSQLSchema.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' reference list.
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getStructuralVariation_Key()
	 * @model
	 * @generated
	 */
	EList<Property> getKey();

} // StructuralVariation
