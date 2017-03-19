/**
 */
package es.um.nosql.schemainference.decisiontree.impl;

import es.um.nosql.schemainference.decisiontree.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DecisiontreeFactoryImpl extends EFactoryImpl implements DecisiontreeFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DecisiontreeFactory init() {
		try {
			DecisiontreeFactory theDecisiontreeFactory = (DecisiontreeFactory)EPackage.Registry.INSTANCE.getEFactory(DecisiontreePackage.eNS_URI);
			if (theDecisiontreeFactory != null) {
				return theDecisiontreeFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DecisiontreeFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecisiontreeFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DecisiontreePackage.LEAF_NODE: return createLeafNode();
			case DecisiontreePackage.DECISION_TREE_FOR_ENTITY: return createDecisionTreeForEntity();
			case DecisiontreePackage.DECISION_TREES: return createDecisionTrees();
			case DecisiontreePackage.PROPERTY_SPEC2: return createPropertySpec2();
			case DecisiontreePackage.HAS_PROPERTY: return createHasProperty();
			case DecisiontreePackage.HAS_NOT_PROPERTY: return createHasNotProperty();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LeafNode createLeafNode() {
		LeafNodeImpl leafNode = new LeafNodeImpl();
		return leafNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecisionTreeForEntity createDecisionTreeForEntity() {
		DecisionTreeForEntityImpl decisionTreeForEntity = new DecisionTreeForEntityImpl();
		return decisionTreeForEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecisionTrees createDecisionTrees() {
		DecisionTreesImpl decisionTrees = new DecisionTreesImpl();
		return decisionTrees;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertySpec2 createPropertySpec2() {
		PropertySpec2Impl propertySpec2 = new PropertySpec2Impl();
		return propertySpec2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HasProperty createHasProperty() {
		HasPropertyImpl hasProperty = new HasPropertyImpl();
		return hasProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HasNotProperty createHasNotProperty() {
		HasNotPropertyImpl hasNotProperty = new HasNotPropertyImpl();
		return hasNotProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecisiontreePackage getDecisiontreePackage() {
		return (DecisiontreePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DecisiontreePackage getPackage() {
		return DecisiontreePackage.eINSTANCE;
	}

} //DecisiontreeFactoryImpl
