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
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getTimestamp <em>Timestamp</em>}</li>
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
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timestamp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(long)
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage#getStructuralVariation_Timestamp()
	 * @model
	 * @generated
	 */
	long getTimestamp();

	/**
	 * Sets the value of the '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getTimestamp <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(long value);

} // StructuralVariation
