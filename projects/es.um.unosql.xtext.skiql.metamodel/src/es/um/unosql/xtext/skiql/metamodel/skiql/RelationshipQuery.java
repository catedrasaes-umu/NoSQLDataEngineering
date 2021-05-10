/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relationship Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery#getFrom <em>From</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery#getTo <em>To</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipQuery()
 * @model
 * @generated
 */
public interface RelationshipQuery extends Query {
	/**
	 * Returns the value of the '<em><b>From</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' containment reference.
	 * @see #setFrom(EntitySpec)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipQuery_From()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EntitySpec getFrom();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery#getFrom <em>From</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' containment reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(EntitySpec value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' containment reference list.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipQuery_To()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<RelationshipSpec> getTo();

} // RelationshipQuery
