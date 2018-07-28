/**
 */
package es.um.nosql.s13e.DecisionTree;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.DecisionTreeForEntity#getRoot <em>Root</em>}</li>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.DecisionTreeForEntity#getEntity <em>Entity</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getDecisionTreeForEntity()
 * @model
 * @generated
 */
public interface DecisionTreeForEntity extends EObject
{
  /**
	 * Returns the value of the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Root</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' containment reference.
	 * @see #setRoot(DecisionTreeNode)
	 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getDecisionTreeForEntity_Root()
	 * @model containment="true"
	 * @generated
	 */
  DecisionTreeNode getRoot();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.DecisionTree.DecisionTreeForEntity#getRoot <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' containment reference.
	 * @see #getRoot()
	 * @generated
	 */
  void setRoot(DecisionTreeNode value);

  /**
	 * Returns the value of the '<em><b>Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity</em>' reference.
	 * @see #setEntity(EntityClass)
	 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getDecisionTreeForEntity_Entity()
	 * @model required="true"
	 * @generated
	 */
  EntityClass getEntity();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.DecisionTree.DecisionTreeForEntity#getEntity <em>Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity</em>' reference.
	 * @see #getEntity()
	 * @generated
	 */
	void setEntity(EntityClass value);

} // DecisionTreeForEntity
