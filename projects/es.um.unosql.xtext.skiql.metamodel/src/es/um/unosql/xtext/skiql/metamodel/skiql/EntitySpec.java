/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec#getVariationFilter <em>Variation Filter</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getEntitySpec()
 * @model
 * @generated
 */
public interface EntitySpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Variation Filter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variation Filter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variation Filter</em>' containment reference.
	 * @see #setVariationFilter(VariationFilter)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getEntitySpec_VariationFilter()
	 * @model containment="true"
	 * @generated
	 */
	VariationFilter getVariationFilter();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec#getVariationFilter <em>Variation Filter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variation Filter</em>' containment reference.
	 * @see #getVariationFilter()
	 * @generated
	 */
	void setVariationFilter(VariationFilter value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getEntitySpec_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // EntitySpec
