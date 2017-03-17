/**
 */
package es.um.nosql.schemainference.decisiontree;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intermediate Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.decisiontree.IntermediateNode#getCheckHasProperty <em>Check Has Property</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.decisiontree.IntermediateNode#getCheckHasNotProperty <em>Check Has Not Property</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getIntermediateNode()
 * @model
 * @generated
 */
public interface IntermediateNode extends DecisionTreeNode {
	/**
	 * Returns the value of the '<em><b>Check Has Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check Has Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check Has Property</em>' reference.
	 * @see #setCheckHasProperty(PropertySpec2)
	 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getIntermediateNode_CheckHasProperty()
	 * @model
	 * @generated
	 */
	PropertySpec2 getCheckHasProperty();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.decisiontree.IntermediateNode#getCheckHasProperty <em>Check Has Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Check Has Property</em>' reference.
	 * @see #getCheckHasProperty()
	 * @generated
	 */
	void setCheckHasProperty(PropertySpec2 value);

	/**
	 * Returns the value of the '<em><b>Check Has Not Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check Has Not Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check Has Not Property</em>' reference.
	 * @see #setCheckHasNotProperty(PropertySpec2)
	 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getIntermediateNode_CheckHasNotProperty()
	 * @model
	 * @generated
	 */
	PropertySpec2 getCheckHasNotProperty();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.decisiontree.IntermediateNode#getCheckHasNotProperty <em>Check Has Not Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Check Has Not Property</em>' reference.
	 * @see #getCheckHasNotProperty()
	 * @generated
	 */
	void setCheckHasNotProperty(PropertySpec2 value);

} // IntermediateNode
