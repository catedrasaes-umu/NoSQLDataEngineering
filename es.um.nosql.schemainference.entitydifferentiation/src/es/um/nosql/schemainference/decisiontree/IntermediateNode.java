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
 *   <li>{@link es.um.nosql.schemainference.decisiontree.IntermediateNode#getProperty <em>Property</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getIntermediateNode()
 * @model abstract="true"
 * @generated
 */
public interface IntermediateNode extends DecisionTreeNode {
	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference.
	 * @see #setProperty(PropertySpec2)
	 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getIntermediateNode_Property()
	 * @model containment="true" required="true"
	 * @generated
	 */
	PropertySpec2 getProperty();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.decisiontree.IntermediateNode#getProperty <em>Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' containment reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(PropertySpec2 value);

} // IntermediateNode
