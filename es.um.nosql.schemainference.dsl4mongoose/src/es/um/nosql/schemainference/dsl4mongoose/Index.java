/**
 */
package es.um.nosql.schemainference.dsl4mongoose;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.Index#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getIndex()
 * @model
 * @generated
 */
public interface Index extends FieldParameter {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link es.um.nosql.schemainference.dsl4mongoose.IndexKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see es.um.nosql.schemainference.dsl4mongoose.IndexKind
	 * @see #setKind(IndexKind)
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getIndex_Kind()
	 * @model
	 * @generated
	 */
	IndexKind getKind();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.dsl4mongoose.Index#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see es.um.nosql.schemainference.dsl4mongoose.IndexKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(IndexKind value);

} // Index
