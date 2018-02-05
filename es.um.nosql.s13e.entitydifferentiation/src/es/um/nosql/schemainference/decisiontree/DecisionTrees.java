/**
 */
package es.um.nosql.s13e.decisiontree;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decision Trees</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.decisiontree.DecisionTrees#getTrees <em>Trees</em>}</li>
 *   <li>{@link es.um.nosql.s13e.decisiontree.DecisionTrees#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.decisiontree.DecisiontreePackage#getDecisionTrees()
 * @model
 * @generated
 */
public interface DecisionTrees extends EObject {
	/**
	 * Returns the value of the '<em><b>Trees</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.s13e.decisiontree.DecisionTreeForEntity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trees</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trees</em>' containment reference list.
	 * @see es.um.nosql.s13e.decisiontree.DecisiontreePackage#getDecisionTrees_Trees()
	 * @model containment="true"
	 * @generated
	 */
	EList<DecisionTreeForEntity> getTrees();

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
	 * @see es.um.nosql.s13e.decisiontree.DecisiontreePackage#getDecisionTrees_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link es.um.nosql.s13e.decisiontree.DecisionTrees#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // DecisionTrees
