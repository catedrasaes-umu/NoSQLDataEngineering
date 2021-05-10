/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relationship Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#getTargetExpression <em>Target Expression</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#isIndirect <em>Indirect</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#getRelationSpec <em>Relation Spec</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipSpec()
 * @model
 * @generated
 */
public interface RelationshipSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Target Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Expression</em>' containment reference.
	 * @see #setTargetExpression(TargetExpression)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipSpec_TargetExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TargetExpression getTargetExpression();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#getTargetExpression <em>Target Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Expression</em>' containment reference.
	 * @see #getTargetExpression()
	 * @generated
	 */
	void setTargetExpression(TargetExpression value);

	/**
	 * Returns the value of the '<em><b>Indirect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indirect</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indirect</em>' attribute.
	 * @see #setIndirect(boolean)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipSpec_Indirect()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isIndirect();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#isIndirect <em>Indirect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indirect</em>' attribute.
	 * @see #isIndirect()
	 * @generated
	 */
	void setIndirect(boolean value);

	/**
	 * Returns the value of the '<em><b>Relation Spec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relation Spec</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relation Spec</em>' containment reference.
	 * @see #setRelationSpec(RelationSpec)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipSpec_RelationSpec()
	 * @model containment="true"
	 * @generated
	 */
	RelationSpec getRelationSpec();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#getRelationSpec <em>Relation Spec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relation Spec</em>' containment reference.
	 * @see #getRelationSpec()
	 * @generated
	 */
	void setRelationSpec(RelationSpec value);

} // RelationshipSpec
