/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relationship Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType#getTargetEntityName <em>Target Entity Name</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType#getRelationType <em>Relation Type</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipType()
 * @model
 * @generated
 */
public interface RelationshipType extends Type {
	/**
	 * Returns the value of the '<em><b>Target Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Entity Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Entity Name</em>' attribute.
	 * @see #setTargetEntityName(String)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipType_TargetEntityName()
	 * @model
	 * @generated
	 */
	String getTargetEntityName();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType#getTargetEntityName <em>Target Entity Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Entity Name</em>' attribute.
	 * @see #getTargetEntityName()
	 * @generated
	 */
	void setTargetEntityName(String value);

	/**
	 * Returns the value of the '<em><b>Relation Type</b></em>' attribute.
	 * The literals are from the enumeration {@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relation Type</em>' attribute.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum
	 * @see #setRelationType(RelationshipTypeEnum)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getRelationshipType_RelationType()
	 * @model
	 * @generated
	 */
	RelationshipTypeEnum getRelationType();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType#getRelationType <em>Relation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relation Type</em>' attribute.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum
	 * @see #getRelationType()
	 * @generated
	 */
	void setRelationType(RelationshipTypeEnum value);

} // RelationshipType
