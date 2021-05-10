/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression#getEntitySpec <em>Entity Spec</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getEntityExpression()
 * @model
 * @generated
 */
public interface EntityExpression extends TargetExpression {
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
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getEntityExpression_EntitySpec()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EntitySpec getEntitySpec();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression#getEntitySpec <em>Entity Spec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity Spec</em>' containment reference.
	 * @see #getEntitySpec()
	 * @generated
	 */
	void setEntitySpec(EntitySpec value);

} // EntityExpression
