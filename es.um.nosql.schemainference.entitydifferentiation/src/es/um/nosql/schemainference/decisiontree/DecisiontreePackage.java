/**
 */
package es.um.nosql.schemainference.decisiontree;

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
 * @see es.um.nosql.schemainference.decisiontree.DecisiontreeFactory
 * @model kind="package"
 * @generated
 */
public interface DecisiontreePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "decisiontree";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:es.um.nosql.schemainference.decisiontree";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "decisiontree";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DecisiontreePackage eINSTANCE = es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl.init();

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.decisiontree.impl.DecisionTreeNodeImpl <em>Decision Tree Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisionTreeNodeImpl
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getDecisionTreeNode()
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
	 * The number of structural features of the '<em>Decision Tree Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TREE_NODE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Decision Tree Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TREE_NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.decisiontree.impl.LeafNodeImpl <em>Leaf Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.decisiontree.impl.LeafNodeImpl
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getLeafNode()
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
	 * The feature id for the '<em><b>Identified Version</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__IDENTIFIED_VERSION = DECISION_TREE_NODE_FEATURE_COUNT + 0;

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
	 * The meta object id for the '{@link es.um.nosql.schemainference.decisiontree.impl.IntermediateNodeImpl <em>Intermediate Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.decisiontree.impl.IntermediateNodeImpl
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getIntermediateNode()
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
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERMEDIATE_NODE__PROPERTY = DECISION_TREE_NODE_FEATURE_COUNT + 0;

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
	 * The meta object id for the '{@link es.um.nosql.schemainference.decisiontree.impl.DecisionTreeForEntityImpl <em>Decision Tree For Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisionTreeForEntityImpl
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getDecisionTreeForEntity()
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
	 * The number of structural features of the '<em>Decision Tree For Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TREE_FOR_ENTITY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Decision Tree For Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TREE_FOR_ENTITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.decisiontree.impl.DecisionTreesImpl <em>Decision Trees</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisionTreesImpl
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getDecisionTrees()
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
	 * The number of structural features of the '<em>Decision Trees</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TREES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Decision Trees</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TREES_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.decisiontree.impl.PropertySpec2Impl <em>Property Spec2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.decisiontree.impl.PropertySpec2Impl
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getPropertySpec2()
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
	 * The meta object id for the '{@link es.um.nosql.schemainference.decisiontree.impl.HasPropertyImpl <em>Has Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.decisiontree.impl.HasPropertyImpl
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getHasProperty()
	 * @generated
	 */
	int HAS_PROPERTY = 6;

	/**
	 * The feature id for the '<em><b>Yes Branch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_PROPERTY__YES_BRANCH = INTERMEDIATE_NODE__YES_BRANCH;

	/**
	 * The feature id for the '<em><b>No Branch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_PROPERTY__NO_BRANCH = INTERMEDIATE_NODE__NO_BRANCH;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_PROPERTY__PROPERTY = INTERMEDIATE_NODE__PROPERTY;

	/**
	 * The number of structural features of the '<em>Has Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_PROPERTY_FEATURE_COUNT = INTERMEDIATE_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Has Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_PROPERTY_OPERATION_COUNT = INTERMEDIATE_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.decisiontree.impl.HasNotPropertyImpl <em>Has Not Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.decisiontree.impl.HasNotPropertyImpl
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getHasNotProperty()
	 * @generated
	 */
	int HAS_NOT_PROPERTY = 7;

	/**
	 * The feature id for the '<em><b>Yes Branch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_NOT_PROPERTY__YES_BRANCH = INTERMEDIATE_NODE__YES_BRANCH;

	/**
	 * The feature id for the '<em><b>No Branch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_NOT_PROPERTY__NO_BRANCH = INTERMEDIATE_NODE__NO_BRANCH;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_NOT_PROPERTY__PROPERTY = INTERMEDIATE_NODE__PROPERTY;

	/**
	 * The number of structural features of the '<em>Has Not Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_NOT_PROPERTY_FEATURE_COUNT = INTERMEDIATE_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Has Not Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_NOT_PROPERTY_OPERATION_COUNT = INTERMEDIATE_NODE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.decisiontree.DecisionTreeNode <em>Decision Tree Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision Tree Node</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisionTreeNode
	 * @generated
	 */
	EClass getDecisionTreeNode();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.schemainference.decisiontree.DecisionTreeNode#getYesBranch <em>Yes Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Yes Branch</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisionTreeNode#getYesBranch()
	 * @see #getDecisionTreeNode()
	 * @generated
	 */
	EReference getDecisionTreeNode_YesBranch();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.schemainference.decisiontree.DecisionTreeNode#getNoBranch <em>No Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>No Branch</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisionTreeNode#getNoBranch()
	 * @see #getDecisionTreeNode()
	 * @generated
	 */
	EReference getDecisionTreeNode_NoBranch();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.decisiontree.LeafNode <em>Leaf Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Leaf Node</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.LeafNode
	 * @generated
	 */
	EClass getLeafNode();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.schemainference.decisiontree.LeafNode#getIdentifiedVersion <em>Identified Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Identified Version</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.LeafNode#getIdentifiedVersion()
	 * @see #getLeafNode()
	 * @generated
	 */
	EReference getLeafNode_IdentifiedVersion();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.decisiontree.IntermediateNode <em>Intermediate Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intermediate Node</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.IntermediateNode
	 * @generated
	 */
	EClass getIntermediateNode();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.schemainference.decisiontree.IntermediateNode#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.IntermediateNode#getProperty()
	 * @see #getIntermediateNode()
	 * @generated
	 */
	EReference getIntermediateNode_Property();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.decisiontree.DecisionTreeForEntity <em>Decision Tree For Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision Tree For Entity</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisionTreeForEntity
	 * @generated
	 */
	EClass getDecisionTreeForEntity();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.schemainference.decisiontree.DecisionTreeForEntity#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisionTreeForEntity#getRoot()
	 * @see #getDecisionTreeForEntity()
	 * @generated
	 */
	EReference getDecisionTreeForEntity_Root();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.schemainference.decisiontree.DecisionTreeForEntity#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entity</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisionTreeForEntity#getEntity()
	 * @see #getDecisionTreeForEntity()
	 * @generated
	 */
	EReference getDecisionTreeForEntity_Entity();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.decisiontree.DecisionTrees <em>Decision Trees</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision Trees</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisionTrees
	 * @generated
	 */
	EClass getDecisionTrees();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.decisiontree.DecisionTrees#getTrees <em>Trees</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Trees</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisionTrees#getTrees()
	 * @see #getDecisionTrees()
	 * @generated
	 */
	EReference getDecisionTrees_Trees();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.decisiontree.PropertySpec2 <em>Property Spec2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Spec2</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.PropertySpec2
	 * @generated
	 */
	EClass getPropertySpec2();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.schemainference.decisiontree.PropertySpec2#isNeedsTypeCheck <em>Needs Type Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Needs Type Check</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.PropertySpec2#isNeedsTypeCheck()
	 * @see #getPropertySpec2()
	 * @generated
	 */
	EAttribute getPropertySpec2_NeedsTypeCheck();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.schemainference.decisiontree.PropertySpec2#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Property</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.PropertySpec2#getProperty()
	 * @see #getPropertySpec2()
	 * @generated
	 */
	EReference getPropertySpec2_Property();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.decisiontree.HasProperty <em>Has Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Has Property</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.HasProperty
	 * @generated
	 */
	EClass getHasProperty();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.decisiontree.HasNotProperty <em>Has Not Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Has Not Property</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.HasNotProperty
	 * @generated
	 */
	EClass getHasNotProperty();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DecisiontreeFactory getDecisiontreeFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.decisiontree.impl.DecisionTreeNodeImpl <em>Decision Tree Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisionTreeNodeImpl
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getDecisionTreeNode()
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
		 * The meta object literal for the '{@link es.um.nosql.schemainference.decisiontree.impl.LeafNodeImpl <em>Leaf Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.decisiontree.impl.LeafNodeImpl
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getLeafNode()
		 * @generated
		 */
		EClass LEAF_NODE = eINSTANCE.getLeafNode();

		/**
		 * The meta object literal for the '<em><b>Identified Version</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LEAF_NODE__IDENTIFIED_VERSION = eINSTANCE.getLeafNode_IdentifiedVersion();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.decisiontree.impl.IntermediateNodeImpl <em>Intermediate Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.decisiontree.impl.IntermediateNodeImpl
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getIntermediateNode()
		 * @generated
		 */
		EClass INTERMEDIATE_NODE = eINSTANCE.getIntermediateNode();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERMEDIATE_NODE__PROPERTY = eINSTANCE.getIntermediateNode_Property();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.decisiontree.impl.DecisionTreeForEntityImpl <em>Decision Tree For Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisionTreeForEntityImpl
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getDecisionTreeForEntity()
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
		 * The meta object literal for the '{@link es.um.nosql.schemainference.decisiontree.impl.DecisionTreesImpl <em>Decision Trees</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisionTreesImpl
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getDecisionTrees()
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
		 * The meta object literal for the '{@link es.um.nosql.schemainference.decisiontree.impl.PropertySpec2Impl <em>Property Spec2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.decisiontree.impl.PropertySpec2Impl
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getPropertySpec2()
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

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.decisiontree.impl.HasPropertyImpl <em>Has Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.decisiontree.impl.HasPropertyImpl
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getHasProperty()
		 * @generated
		 */
		EClass HAS_PROPERTY = eINSTANCE.getHasProperty();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.decisiontree.impl.HasNotPropertyImpl <em>Has Not Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.decisiontree.impl.HasNotPropertyImpl
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getHasNotProperty()
		 * @generated
		 */
		EClass HAS_NOT_PROPERTY = eINSTANCE.getHasNotProperty();

	}

} //DecisiontreePackage
