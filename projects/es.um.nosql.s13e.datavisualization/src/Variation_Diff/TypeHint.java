/**
 */
package Variation_Diff;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Hint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Variation_Diff.TypeHint#getWithName <em>With Name</em>}</li>
 *   <li>{@link Variation_Diff.TypeHint#getWithType <em>With Type</em>}</li>
 * </ul>
 *
 * @see Variation_Diff.Variation_DiffPackage#getTypeHint()
 * @model abstract="true"
 * @generated
 */
public interface TypeHint extends EObject
{
  /**
   * Returns the value of the '<em><b>With Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>With Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>With Name</em>' attribute.
   * @see #setWithName(String)
   * @see Variation_Diff.Variation_DiffPackage#getTypeHint_WithName()
   * @model required="true"
   * @generated
   */
  String getWithName();

  /**
   * Sets the value of the '{@link Variation_Diff.TypeHint#getWithName <em>With Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>With Name</em>' attribute.
   * @see #getWithName()
   * @generated
   */
  void setWithName(String value);

  /**
   * Returns the value of the '<em><b>With Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>With Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>With Type</em>' containment reference.
   * @see #setWithType(FieldType)
   * @see Variation_Diff.Variation_DiffPackage#getTypeHint_WithType()
   * @model containment="true" required="true"
   * @generated
   */
  FieldType getWithType();

  /**
   * Sets the value of the '{@link Variation_Diff.TypeHint#getWithType <em>With Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>With Type</em>' containment reference.
   * @see #getWithType()
   * @generated
   */
  void setWithType(FieldType value);

} // TypeHint
