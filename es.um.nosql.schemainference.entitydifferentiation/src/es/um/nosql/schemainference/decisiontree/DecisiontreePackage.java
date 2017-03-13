/**
 */
package es.um.nosql.schemainference.decisiontree;

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
	 * The feature id for the '<em><b>Checked Property</b></em>' reference.
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
	 * The meta object id for the '{@link es.um.nosql.schemainference.decisiontree.impl.DecisonTreeForEntityImpl <em>Decison Tree For Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisonTreeForEntityImpl
	 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getDecisonTreeForEntity()
	 * @generated
	 */
	int DECISON_TREE_FOR_ENTITY = 3;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISON_TREE_FOR_ENTITY__ROOT = 0;

	/**
	 * The feature id for the '<em><b>Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISON_TREE_FOR_ENTITY__ENTITY = 1;

	/**
	 * The number of structural features of the '<em>Decison Tree For Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISON_TREE_FOR_ENTITY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Decison Tree For Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISON_TREE_FOR_ENTITY_OPERATION_COUNT = 0;

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
	 * Returns the meta object for the reference '{@link es.um.nosql.schemainference.decisiontree.IntermediateNode#getCheckedProperty <em>Checked Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Checked Property</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.IntermediateNode#getCheckedProperty()
	 * @see #getIntermediateNode()
	 * @generated
	 */
	EReference getIntermediateNode_CheckedProperty();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity <em>Decison Tree For Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decison Tree For Entity</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity
	 * @generated
	 */
	EClass getDecisonTreeForEntity();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity#getRoot()
	 * @see #getDecisonTreeForEntity()
	 * @generated
	 */
	EReference getDecisonTreeForEntity_Root();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entity</em>'.
	 * @see es.um.nosql.schemainference.decisiontree.DecisonTreeForEntity#getEntity()
	 * @see #getDecisonTreeForEntity()
	 * @generated
	 */
	EReference getDecisonTreeForEntity_Entity();

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
		 * The meta object literal for the '<em><b>Checked Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERMEDIATE_NODE__CHECKED_PROPERTY = eINSTANCE.getIntermediateNode_CheckedProperty();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.decisiontree.impl.DecisonTreeForEntityImpl <em>Decison Tree For Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisonTreeForEntityImpl
		 * @see es.um.nosql.schemainference.decisiontree.impl.DecisiontreePackageImpl#getDecisonTreeForEntity()
		 * @generated
		 */
		EClass DECISON_TREE_FOR_ENTITY = eINSTANCE.getDecisonTreeForEntity();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISON_TREE_FOR_ENTITY__ROOT = eINSTANCE.getDecisonTreeForEntity_Root();

		/**
		 * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISON_TREE_FOR_ENTITY__ENTITY = eINSTANCE.getDecisonTreeForEntity_Entity();

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

	}

} //DecisiontreePackage
