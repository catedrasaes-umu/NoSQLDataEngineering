/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relationship Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression#getRelationshipQuery <em>Relationship Query</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipExpression()
 * @model
 * @generated
 */
public interface RelationshipExpression extends TargetExpression {
	/**
	 * Returns the value of the '<em><b>Relationship Query</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationship Query</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationship Query</em>' containment reference.
	 * @see #setRelationshipQuery(RelationshipQuery)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipExpression_RelationshipQuery()
	 * @model containment="true" required="true"
	 * @generated
	 */
	RelationshipQuery getRelationshipQuery();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression#getRelationshipQuery <em>Relationship Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relationship Query</em>' containment reference.
	 * @see #getRelationshipQuery()
	 * @generated
	 */
	void setRelationshipQuery(RelationshipQuery value);

} // RelationshipExpression
