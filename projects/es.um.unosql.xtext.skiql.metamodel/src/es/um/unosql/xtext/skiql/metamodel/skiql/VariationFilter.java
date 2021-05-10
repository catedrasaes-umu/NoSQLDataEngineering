/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variation Filter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter#getPropertySpecs <em>Property Specs</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter#isOnly <em>Only</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getVariationFilter()
 * @model
 * @generated
 */
public interface VariationFilter extends EObject {
	/**
	 * Returns the value of the '<em><b>Property Specs</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Specs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Specs</em>' containment reference list.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getVariationFilter_PropertySpecs()
	 * @model containment="true"
	 * @generated
	 */
	EList<PropertySpec> getPropertySpecs();

	/**
	 * Returns the value of the '<em><b>Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Only</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Only</em>' attribute.
	 * @see #setOnly(boolean)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getVariationFilter_Only()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isOnly();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter#isOnly <em>Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Only</em>' attribute.
	 * @see #isOnly()
	 * @generated
	 */
	void setOnly(boolean value);

} // VariationFilter
