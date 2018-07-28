/**
 */
package es.um.nosql.s13e.DecisionTree;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.DecisionTreeNode#getYesBranch <em>Yes Branch</em>}</li>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.DecisionTreeNode#getNoBranch <em>No Branch</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getDecisionTreeNode()
 * @model abstract="true"
 * @generated
 */
public interface DecisionTreeNode extends EObject
{
  /**
	 * Returns the value of the '<em><b>Yes Branch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Yes Branch</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Yes Branch</em>' containment reference.
	 * @see #setYesBranch(DecisionTreeNode)
	 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getDecisionTreeNode_YesBranch()
	 * @model containment="true"
	 * @generated
	 */
  DecisionTreeNode getYesBranch();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.DecisionTree.DecisionTreeNode#getYesBranch <em>Yes Branch</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Yes Branch</em>' containment reference.
	 * @see #getYesBranch()
	 * @generated
	 */
  void setYesBranch(DecisionTreeNode value);

  /**
	 * Returns the value of the '<em><b>No Branch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>No Branch</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>No Branch</em>' containment reference.
	 * @see #setNoBranch(DecisionTreeNode)
	 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getDecisionTreeNode_NoBranch()
	 * @model containment="true"
	 * @generated
	 */
  DecisionTreeNode getNoBranch();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.DecisionTree.DecisionTreeNode#getNoBranch <em>No Branch</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No Branch</em>' containment reference.
	 * @see #getNoBranch()
	 * @generated
	 */
  void setNoBranch(DecisionTreeNode value);

} // DecisionTreeNode
