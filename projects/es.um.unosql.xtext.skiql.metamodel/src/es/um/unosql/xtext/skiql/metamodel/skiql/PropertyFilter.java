/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Filter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertyFilter#getPropertySelectorSpecs <em>Property Selector Specs</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getPropertyFilter()
 * @model
 * @generated
 */
public interface PropertyFilter extends Operation {
	/**
	 * Returns the value of the '<em><b>Property Selector Specs</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Selector Specs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Selector Specs</em>' containment reference list.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getPropertyFilter_PropertySelectorSpecs()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<PropertySelectorSpec> getPropertySelectorSpecs();

} // PropertyFilter
