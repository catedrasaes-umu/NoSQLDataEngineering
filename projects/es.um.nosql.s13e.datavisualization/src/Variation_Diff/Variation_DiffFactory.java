/**
 */
package Variation_Diff;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see Variation_Diff.Variation_DiffPackage
 * @generated
 */
public interface Variation_DiffFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Variation_DiffFactory eINSTANCE = Variation_Diff.impl.Variation_DiffFactoryImpl.init();

  /**
   * Returns a new object of class '<em>No SQL Differences</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>No SQL Differences</em>'.
   * @generated
   */
  NoSQLDifferences createNoSQLDifferences();

  /**
   * Returns a new object of class '<em>Type Difference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Difference</em>'.
   * @generated
   */
  TypeDifference createTypeDifference();

  /**
   * Returns a new object of class '<em>Has Field</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Has Field</em>'.
   * @generated
   */
  HasField createHasField();

  /**
   * Returns a new object of class '<em>Has Not Field</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Has Not Field</em>'.
   * @generated
   */
  HasNotField createHasNotField();

  /**
   * Returns a new object of class '<em>Primitive Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Primitive Type</em>'.
   * @generated
   */
  PrimitiveType createPrimitiveType();

  /**
   * Returns a new object of class '<em>Aggregate Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Aggregate Type</em>'.
   * @generated
   */
  AggregateType createAggregateType();

  /**
   * Returns a new object of class '<em>Reference Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Reference Type</em>'.
   * @generated
   */
  ReferenceType createReferenceType();

  /**
   * Returns a new object of class '<em>Entity Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Entity Type</em>'.
   * @generated
   */
  EntityType createEntityType();

  /**
   * Returns a new object of class '<em>Homogeneous Tuple Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Homogeneous Tuple Type</em>'.
   * @generated
   */
  HomogeneousTupleType createHomogeneousTupleType();

  /**
   * Returns a new object of class '<em>Heterogeneous Tuple Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Heterogeneous Tuple Type</em>'.
   * @generated
   */
  HeterogeneousTupleType createHeterogeneousTupleType();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  Variation_DiffPackage getVariation_DiffPackage();

} //Variation_DiffFactory
