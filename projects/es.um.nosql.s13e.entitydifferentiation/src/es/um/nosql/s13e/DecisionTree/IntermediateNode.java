/**
 */
package es.um.nosql.s13e.DecisionTree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intermediate Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.IntermediateNode#getCheckedProperty <em>Checked Property</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getIntermediateNode()
 * @model
 * @generated
 */
public interface IntermediateNode extends DecisionTreeNode
{
  /**
	 * Returns the value of the '<em><b>Checked Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Checked Property</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Checked Property</em>' containment reference.
	 * @see #setCheckedProperty(PropertySpec2)
	 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getIntermediateNode_CheckedProperty()
	 * @model containment="true" required="true"
	 * @generated
	 */
  PropertySpec2 getCheckedProperty();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.DecisionTree.IntermediateNode#getCheckedProperty <em>Checked Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Checked Property</em>' containment reference.
	 * @see #getCheckedProperty()
	 * @generated
	 */
  void setCheckedProperty(PropertySpec2 value);

} // IntermediateNode
