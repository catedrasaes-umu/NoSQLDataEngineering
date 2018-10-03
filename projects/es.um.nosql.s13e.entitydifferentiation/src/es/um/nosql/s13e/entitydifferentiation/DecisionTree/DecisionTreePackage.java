/**
 */
package es.um.nosql.s13e.entitydifferentiation.DecisionTree;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeFactory
 * @model kind="package"
 * @generated
 */
public interface DecisionTreePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "DecisionTree";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.modelum.es/DecisionTree";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "DecisionTree";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DecisionTreePackage eINSTANCE = es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl.init();

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeNodeImpl <em>Node</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeNodeImpl
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getDecisionTreeNode()
   * @generated
   */
  int DECISION_TREE_NODE = 0;

  /**
   * The feature id for the '<em><b>Yes Branch</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREE_NODE__YES_BRANCH = 0;

  /**
   * The feature id for the '<em><b>No Branch</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREE_NODE__NO_BRANCH = 1;

  /**
   * The number of structural features of the '<em>Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREE_NODE_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREE_NODE_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.LeafNodeImpl <em>Leaf Node</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.LeafNodeImpl
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getLeafNode()
   * @generated
   */
  int LEAF_NODE = 1;

  /**
   * The feature id for the '<em><b>Yes Branch</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEAF_NODE__YES_BRANCH = DECISION_TREE_NODE__YES_BRANCH;

  /**
   * The feature id for the '<em><b>No Branch</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEAF_NODE__NO_BRANCH = DECISION_TREE_NODE__NO_BRANCH;

  /**
   * The feature id for the '<em><b>Identified Variation</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEAF_NODE__IDENTIFIED_VARIATION = DECISION_TREE_NODE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Leaf Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEAF_NODE_FEATURE_COUNT = DECISION_TREE_NODE_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Leaf Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEAF_NODE_OPERATION_COUNT = DECISION_TREE_NODE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.IntermediateNodeImpl <em>Intermediate Node</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.IntermediateNodeImpl
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getIntermediateNode()
   * @generated
   */
  int INTERMEDIATE_NODE = 2;

  /**
   * The feature id for the '<em><b>Yes Branch</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERMEDIATE_NODE__YES_BRANCH = DECISION_TREE_NODE__YES_BRANCH;

  /**
   * The feature id for the '<em><b>No Branch</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERMEDIATE_NODE__NO_BRANCH = DECISION_TREE_NODE__NO_BRANCH;

  /**
   * The feature id for the '<em><b>Checked Property</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERMEDIATE_NODE__CHECKED_PROPERTY = DECISION_TREE_NODE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Intermediate Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERMEDIATE_NODE_FEATURE_COUNT = DECISION_TREE_NODE_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Intermediate Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERMEDIATE_NODE_OPERATION_COUNT = DECISION_TREE_NODE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeForEntityImpl <em>For Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeForEntityImpl
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getDecisionTreeForEntity()
   * @generated
   */
  int DECISION_TREE_FOR_ENTITY = 3;

  /**
   * The feature id for the '<em><b>Root</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREE_FOR_ENTITY__ROOT = 0;

  /**
   * The feature id for the '<em><b>Entity</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREE_FOR_ENTITY__ENTITY = 1;

  /**
   * The number of structural features of the '<em>For Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREE_FOR_ENTITY_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>For Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREE_FOR_ENTITY_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreesImpl <em>Decision Trees</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreesImpl
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getDecisionTrees()
   * @generated
   */
  int DECISION_TREES = 4;

  /**
   * The feature id for the '<em><b>Trees</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREES__TREES = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREES__NAME = 1;

  /**
   * The number of structural features of the '<em>Decision Trees</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREES_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Decision Trees</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECISION_TREES_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.PropertySpec2Impl <em>Property Spec2</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.PropertySpec2Impl
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getPropertySpec2()
   * @generated
   */
  int PROPERTY_SPEC2 = 5;

  /**
   * The feature id for the '<em><b>Needs Type Check</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SPEC2__NEEDS_TYPE_CHECK = 0;

  /**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SPEC2__PROPERTY = 1;

  /**
   * The number of structural features of the '<em>Property Spec2</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SPEC2_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Property Spec2</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SPEC2_OPERATION_COUNT = 0;


  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeNode <em>Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeNode
   * @generated
   */
  EClass getDecisionTreeNode();

  /**
   * Returns the meta object for the containment reference '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeNode#getYesBranch <em>Yes Branch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Yes Branch</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeNode#getYesBranch()
   * @see #getDecisionTreeNode()
   * @generated
   */
  EReference getDecisionTreeNode_YesBranch();

  /**
   * Returns the meta object for the containment reference '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeNode#getNoBranch <em>No Branch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>No Branch</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeNode#getNoBranch()
   * @see #getDecisionTreeNode()
   * @generated
   */
  EReference getDecisionTreeNode_NoBranch();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.LeafNode <em>Leaf Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Leaf Node</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.LeafNode
   * @generated
   */
  EClass getLeafNode();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.LeafNode#getIdentifiedVariation <em>Identified Variation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Identified Variation</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.LeafNode#getIdentifiedVariation()
   * @see #getLeafNode()
   * @generated
   */
  EReference getLeafNode_IdentifiedVariation();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.IntermediateNode <em>Intermediate Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Intermediate Node</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.IntermediateNode
   * @generated
   */
  EClass getIntermediateNode();

  /**
   * Returns the meta object for the containment reference '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.IntermediateNode#getCheckedProperty <em>Checked Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Checked Property</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.IntermediateNode#getCheckedProperty()
   * @see #getIntermediateNode()
   * @generated
   */
  EReference getIntermediateNode_CheckedProperty();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeForEntity <em>For Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>For Entity</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeForEntity
   * @generated
   */
  EClass getDecisionTreeForEntity();

  /**
   * Returns the meta object for the containment reference '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeForEntity#getRoot <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Root</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeForEntity#getRoot()
   * @see #getDecisionTreeForEntity()
   * @generated
   */
  EReference getDecisionTreeForEntity_Root();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeForEntity#getEntity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Entity</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeForEntity#getEntity()
   * @see #getDecisionTreeForEntity()
   * @generated
   */
  EReference getDecisionTreeForEntity_Entity();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTrees <em>Decision Trees</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Decision Trees</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTrees
   * @generated
   */
  EClass getDecisionTrees();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTrees#getTrees <em>Trees</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Trees</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTrees#getTrees()
   * @see #getDecisionTrees()
   * @generated
   */
  EReference getDecisionTrees_Trees();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTrees#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTrees#getName()
   * @see #getDecisionTrees()
   * @generated
   */
  EAttribute getDecisionTrees_Name();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.PropertySpec2 <em>Property Spec2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Spec2</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.PropertySpec2
   * @generated
   */
  EClass getPropertySpec2();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.PropertySpec2#isNeedsTypeCheck <em>Needs Type Check</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Needs Type Check</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.PropertySpec2#isNeedsTypeCheck()
   * @see #getPropertySpec2()
   * @generated
   */
  EAttribute getPropertySpec2_NeedsTypeCheck();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.PropertySpec2#getProperty <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Property</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.PropertySpec2#getProperty()
   * @see #getPropertySpec2()
   * @generated
   */
  EReference getPropertySpec2_Property();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  DecisionTreeFactory getDecisionTreeFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeNodeImpl <em>Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeNodeImpl
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getDecisionTreeNode()
     * @generated
     */
    EClass DECISION_TREE_NODE = eINSTANCE.getDecisionTreeNode();

    /**
     * The meta object literal for the '<em><b>Yes Branch</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECISION_TREE_NODE__YES_BRANCH = eINSTANCE.getDecisionTreeNode_YesBranch();

    /**
     * The meta object literal for the '<em><b>No Branch</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECISION_TREE_NODE__NO_BRANCH = eINSTANCE.getDecisionTreeNode_NoBranch();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.LeafNodeImpl <em>Leaf Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.LeafNodeImpl
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getLeafNode()
     * @generated
     */
    EClass LEAF_NODE = eINSTANCE.getLeafNode();

    /**
     * The meta object literal for the '<em><b>Identified Variation</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LEAF_NODE__IDENTIFIED_VARIATION = eINSTANCE.getLeafNode_IdentifiedVariation();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.IntermediateNodeImpl <em>Intermediate Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.IntermediateNodeImpl
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getIntermediateNode()
     * @generated
     */
    EClass INTERMEDIATE_NODE = eINSTANCE.getIntermediateNode();

    /**
     * The meta object literal for the '<em><b>Checked Property</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INTERMEDIATE_NODE__CHECKED_PROPERTY = eINSTANCE.getIntermediateNode_CheckedProperty();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeForEntityImpl <em>For Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeForEntityImpl
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getDecisionTreeForEntity()
     * @generated
     */
    EClass DECISION_TREE_FOR_ENTITY = eINSTANCE.getDecisionTreeForEntity();

    /**
     * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECISION_TREE_FOR_ENTITY__ROOT = eINSTANCE.getDecisionTreeForEntity_Root();

    /**
     * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECISION_TREE_FOR_ENTITY__ENTITY = eINSTANCE.getDecisionTreeForEntity_Entity();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreesImpl <em>Decision Trees</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreesImpl
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getDecisionTrees()
     * @generated
     */
    EClass DECISION_TREES = eINSTANCE.getDecisionTrees();

    /**
     * The meta object literal for the '<em><b>Trees</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECISION_TREES__TREES = eINSTANCE.getDecisionTrees_Trees();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DECISION_TREES__NAME = eINSTANCE.getDecisionTrees_Name();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.PropertySpec2Impl <em>Property Spec2</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.PropertySpec2Impl
     * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreePackageImpl#getPropertySpec2()
     * @generated
     */
    EClass PROPERTY_SPEC2 = eINSTANCE.getPropertySpec2();

    /**
     * The meta object literal for the '<em><b>Needs Type Check</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY_SPEC2__NEEDS_TYPE_CHECK = eINSTANCE.getPropertySpec2_NeedsTypeCheck();

    /**
     * The meta object literal for the '<em><b>Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_SPEC2__PROPERTY = eINSTANCE.getPropertySpec2_Property();

  }

} //DecisionTreePackage
