/**
 */
package es.um.nosql.s13e.DecisionTree;

import org.eclipse.emf.ecore.EObject;

import es.um.nosql.s13e.NoSQLSchema.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Spec2</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.PropertySpec2#isNeedsTypeCheck <em>Needs Type Check</em>}</li>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.PropertySpec2#getProperty <em>Property</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getPropertySpec2()
 * @model
 * @generated
 */
public interface PropertySpec2 extends EObject
{
  /**
	 * Returns the value of the '<em><b>Needs Type Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Needs Type Check</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Needs Type Check</em>' attribute.
	 * @see #setNeedsTypeCheck(boolean)
	 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getPropertySpec2_NeedsTypeCheck()
	 * @model required="true"
	 * @generated
	 */
  boolean isNeedsTypeCheck();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.DecisionTree.PropertySpec2#isNeedsTypeCheck <em>Needs Type Check</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Needs Type Check</em>' attribute.
	 * @see #isNeedsTypeCheck()
	 * @generated
	 */
  void setNeedsTypeCheck(boolean value);

  /**
	 * Returns the value of the '<em><b>Property</b></em>' reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' reference.
	 * @see #setProperty(Property)
	 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage#getPropertySpec2_Property()
	 * @model required="true"
	 * @generated
	 */
  Property getProperty();

  /**
	 * Sets the value of the '{@link es.um.nosql.s13e.DecisionTree.PropertySpec2#getProperty <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' reference.
	 * @see #getProperty()
	 * @generated
	 */
  void setProperty(Property value);

} // PropertySpec2
