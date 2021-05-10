/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getEntitySpec <em>Entity Spec</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getOperation <em>Operation</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getHaving <em>Having</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getEntityQuery()
 * @model
 * @generated
 */
public interface EntityQuery extends Query {
	/**
	 * Returns the value of the '<em><b>Entity Spec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity Spec</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity Spec</em>' containment reference.
	 * @see #setEntitySpec(EntitySpec)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getEntityQuery_EntitySpec()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EntitySpec getEntitySpec();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getEntitySpec <em>Entity Spec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity Spec</em>' containment reference.
	 * @see #getEntitySpec()
	 * @generated
	 */
	void setEntitySpec(EntitySpec value);

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' containment reference.
	 * @see #setOperation(Operation)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getEntityQuery_Operation()
	 * @model containment="true"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getOperation <em>Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' containment reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Having</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Having</em>' containment reference.
	 * @see #setHaving(Having)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getEntityQuery_Having()
	 * @model containment="true"
	 * @generated
	 */
	Having getHaving();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getHaving <em>Having</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Having</em>' containment reference.
	 * @see #getHaving()
	 * @generated
	 */
	void setHaving(Having value);

} // EntityQuery
