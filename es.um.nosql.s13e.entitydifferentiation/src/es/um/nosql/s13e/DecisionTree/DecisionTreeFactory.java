/**
 */
package es.um.nosql.s13e.DecisionTree;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see es.um.nosql.s13e.DecisionTree.DecisionTreePackage
 * @generated
 */
public interface DecisionTreeFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DecisionTreeFactory eINSTANCE = es.um.nosql.s13e.DecisionTree.impl.DecisionTreeFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Leaf Node</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Leaf Node</em>'.
   * @generated
   */
  LeafNode createLeafNode();

  /**
   * Returns a new object of class '<em>Intermediate Node</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Intermediate Node</em>'.
   * @generated
   */
  IntermediateNode createIntermediateNode();

  /**
   * Returns a new object of class '<em>For Entity</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>For Entity</em>'.
   * @generated
   */
  DecisionTreeForEntity createDecisionTreeForEntity();

  /**
   * Returns a new object of class '<em>Decision Trees</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Decision Trees</em>'.
   * @generated
   */
  DecisionTrees createDecisionTrees();

  /**
   * Returns a new object of class '<em>Property Spec2</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Property Spec2</em>'.
   * @generated
   */
  PropertySpec2 createPropertySpec2();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  DecisionTreePackage getDecisionTreePackage();

} //DecisionTreeFactory
