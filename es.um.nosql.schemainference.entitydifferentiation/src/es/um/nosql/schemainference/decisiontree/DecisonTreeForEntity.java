/**
 */
package es.um.nosql.schemainference.decisiontree;

import es.um.nosql.schemainference.NoSQLSchema.Entity;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decison Tree For Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity#getRoot <em>Root</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity#getEntity <em>Entity</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getDecisonTreeForEntity()
 * @model
 * @generated
 */
public interface DecisonTreeForEntity extends EObject {
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
	 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getDecisonTreeForEntity_Root()
	 * @model containment="true"
	 * @generated
	 */
	DecisionTreeNode getRoot();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity#getRoot <em>Root</em>}' containment reference.
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
	 * @see #setEntity(Entity)
	 * @see es.um.nosql.schemainference.decisiontree.DecisiontreePackage#getDecisonTreeForEntity_Entity()
	 * @model required="true"
	 * @generated
	 */
	Entity getEntity();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity#getEntity <em>Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity</em>' reference.
	 * @see #getEntity()
	 * @generated
	 */
	void setEntity(Entity value);

} // DecisonTreeForEntity
