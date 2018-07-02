/**
 */
package Variation_Diff;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>No SQL Differences</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Variation_Diff.NoSQLDifferences#getName <em>Name</em>}</li>
 *   <li>{@link Variation_Diff.NoSQLDifferences#getHasTypeDifferences <em>Has Type Differences</em>}</li>
 * </ul>
 *
 * @see Variation_Diff.Variation_DiffPackage#getNoSQLDifferences()
 * @model
 * @generated
 */
public interface NoSQLDifferences extends EObject
{
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
   * @see Variation_Diff.Variation_DiffPackage#getNoSQLDifferences_Name()
   * @model required="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link Variation_Diff.NoSQLDifferences#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Has Type Differences</b></em>' containment reference list.
   * The list contents are of type {@link Variation_Diff.TypeDifference}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Has Type Differences</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Has Type Differences</em>' containment reference list.
   * @see Variation_Diff.Variation_DiffPackage#getNoSQLDifferences_HasTypeDifferences()
   * @model containment="true" required="true"
   * @generated
   */
  EList<TypeDifference> getHasTypeDifferences();

} // NoSQLDifferences
