/**
 */
package es.um.nosql.s13e.DecisionTree;

import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Leaf Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.LeafNode#getIdentifiedVariation <em>Identified Variation</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getLeafNode()
 * @model
 * @generated
 */
public interface LeafNode extends DecisionTreeNode
{
  /**
	 * Returns the value of the '<em><b>Identified Variation</b></em>' reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identified Variation</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Identified Variation</em>' reference.
	 * @see #setIdentifiedVariation(StructuralVariation)
	 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getLeafNode_IdentifiedVariation()
	 * @model required="true"
	 * @generated
	 */
  StructuralVariation getIdentifiedVariation();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.DecisionTree.LeafNode#getIdentifiedVariation <em>Identified Variation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identified Variation</em>' reference.
	 * @see #getIdentifiedVariation()
	 * @generated
	 */
	void setIdentifiedVariation(StructuralVariation value);

} // LeafNode
