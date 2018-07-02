/**
 */
package Variation_Diff;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Heterogeneous Tuple Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Variation_Diff.HeterogeneousTupleType#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see Variation_Diff.Variation_DiffPackage#getHeterogeneousTupleType()
 * @model
 * @generated
 */
public interface HeterogeneousTupleType extends TupleType
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute list.
   * @see Variation_Diff.Variation_DiffPackage#getHeterogeneousTupleType_Type()
   * @model unique="false" required="true"
   * @generated
   */
  EList<String> getType();

} // HeterogeneousTupleType
