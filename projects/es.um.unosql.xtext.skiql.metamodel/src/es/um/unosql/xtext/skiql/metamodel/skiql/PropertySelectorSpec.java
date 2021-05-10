/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Selector Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec#getType <em>Type</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec#getSpecifity <em>Specifity</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getPropertySelectorSpec()
 * @model
 * @generated
 */
public interface PropertySelectorSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum
	 * @see #setType(PropertyTypeEnum)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getPropertySelectorSpec_Type()
	 * @model
	 * @generated
	 */
	PropertyTypeEnum getType();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum
	 * @see #getType()
	 * @generated
	 */
	void setType(PropertyTypeEnum value);

	/**
	 * Returns the value of the '<em><b>Specifity</b></em>' attribute.
	 * The literals are from the enumeration {@link es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specifity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specifity</em>' attribute.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum
	 * @see #setSpecifity(SpecificityTypeEnum)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getPropertySelectorSpec_Specifity()
	 * @model
	 * @generated
	 */
	SpecificityTypeEnum getSpecifity();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec#getSpecifity <em>Specifity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specifity</em>' attribute.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum
	 * @see #getSpecifity()
	 * @generated
	 */
	void setSpecifity(SpecificityTypeEnum value);

} // PropertySelectorSpec
