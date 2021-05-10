/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Having</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.Having#isNegative <em>Negative</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.Having#getHavingType <em>Having Type</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getHaving()
 * @model
 * @generated
 */
public interface Having extends EObject {
	/**
	 * Returns the value of the '<em><b>Negative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Negative</em>' attribute.
	 * @see #setNegative(boolean)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getHaving_Negative()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isNegative();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Having#isNegative <em>Negative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Negative</em>' attribute.
	 * @see #isNegative()
	 * @generated
	 */
	void setNegative(boolean value);

	/**
	 * Returns the value of the '<em><b>Having Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Having Type</em>' containment reference.
	 * @see #setHavingType(HavingType)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getHaving_HavingType()
	 * @model containment="true" required="true"
	 * @generated
	 */
	HavingType getHavingType();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Having#getHavingType <em>Having Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Having Type</em>' containment reference.
	 * @see #getHavingType()
	 * @generated
	 */
	void setHavingType(HavingType value);

} // Having
