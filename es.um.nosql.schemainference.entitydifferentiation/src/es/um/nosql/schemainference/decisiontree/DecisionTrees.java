/**
 */
package es.um.nosql.schemainference.decisiontree;

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
 *   <li>{@link es.um.nosql.schemainference.decisiontree.DecisionTrees#getTrees <em>Trees</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getDecisionTrees()
 * @model
 * @generated
 */
public interface DecisionTrees extends EObject {
	/**
	 * Returns the value of the '<em><b>Trees</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trees</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trees</em>' containment reference list.
	 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getDecisionTrees_Trees()
	 * @model containment="true"
	 * @generated
	 */
	EList<DecisonTreeForEntity> getTrees();

} // DecisionTrees
