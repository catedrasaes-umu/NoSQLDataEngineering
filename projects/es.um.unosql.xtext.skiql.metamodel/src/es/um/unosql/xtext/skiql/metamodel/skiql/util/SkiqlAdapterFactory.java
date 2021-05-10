/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.util;

import es.um.unosql.xtext.skiql.metamodel.skiql.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage
 * @generated
 */
public class SkiqlAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SkiqlPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkiqlAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SkiqlPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SkiqlSwitch<Adapter> modelSwitch =
		new SkiqlSwitch<Adapter>() {
			@Override
			public Adapter caseSkiQLModel(SkiQLModel object) {
				return createSkiQLModelAdapter();
			}
			@Override
			public Adapter caseQuery(Query object) {
				return createQueryAdapter();
			}
			@Override
			public Adapter caseRelationshipQuery(RelationshipQuery object) {
				return createRelationshipQueryAdapter();
			}
			@Override
			public Adapter caseEntityQuery(EntityQuery object) {
				return createEntityQueryAdapter();
			}
			@Override
			public Adapter caseEntitySpec(EntitySpec object) {
				return createEntitySpecAdapter();
			}
			@Override
			public Adapter caseEntityOperation(EntityOperation object) {
				return createEntityOperationAdapter();
			}
			@Override
			public Adapter caseType(Type object) {
				return createTypeAdapter();
			}
			@Override
			public Adapter caseVersionHistory(VersionHistory object) {
				return createVersionHistoryAdapter();
			}
			@Override
			public Adapter caseBefore(Before object) {
				return createBeforeAdapter();
			}
			@Override
			public Adapter caseAfter(After object) {
				return createAfterAdapter();
			}
			@Override
			public Adapter caseBetween(Between object) {
				return createBetweenAdapter();
			}
			@Override
			public Adapter caseDateTime(DateTime object) {
				return createDateTimeAdapter();
			}
			@Override
			public Adapter caseAll(All object) {
				return createAllAdapter();
			}
			@Override
			public Adapter caseCountOperation(CountOperation object) {
				return createCountOperationAdapter();
			}
			@Override
			public Adapter caseVariationFilter(VariationFilter object) {
				return createVariationFilterAdapter();
			}
			@Override
			public Adapter casePropertySpec(PropertySpec object) {
				return createPropertySpecAdapter();
			}
			@Override
			public Adapter casePropertySelectorSpec(PropertySelectorSpec object) {
				return createPropertySelectorSpecAdapter();
			}
			@Override
			public Adapter caseOperation(Operation object) {
				return createOperationAdapter();
			}
			@Override
			public Adapter caseRelationshipSpec(RelationshipSpec object) {
				return createRelationshipSpecAdapter();
			}
			@Override
			public Adapter caseAggregationSpec(AggregationSpec object) {
				return createAggregationSpecAdapter();
			}
			@Override
			public Adapter caseReferenceSpec(ReferenceSpec object) {
				return createReferenceSpecAdapter();
			}
			@Override
			public Adapter caseTargetExpression(TargetExpression object) {
				return createTargetExpressionAdapter();
			}
			@Override
			public Adapter caseRelationshipExpression(RelationshipExpression object) {
				return createRelationshipExpressionAdapter();
			}
			@Override
			public Adapter caseEntityExpression(EntityExpression object) {
				return createEntityExpressionAdapter();
			}
			@Override
			public Adapter casePrimitiveType(PrimitiveType object) {
				return createPrimitiveTypeAdapter();
			}
			@Override
			public Adapter caseRelationshipType(RelationshipType object) {
				return createRelationshipTypeAdapter();
			}
			@Override
			public Adapter caseRelationSpec(RelationSpec object) {
				return createRelationSpecAdapter();
			}
			@Override
			public Adapter casePropertyFilter(PropertyFilter object) {
				return createPropertyFilterAdapter();
			}
			@Override
			public Adapter caseHaving(Having object) {
				return createHavingAdapter();
			}
			@Override
			public Adapter caseHavingType(HavingType object) {
				return createHavingTypeAdapter();
			}
			@Override
			public Adapter caseHavingKeys(HavingKeys object) {
				return createHavingKeysAdapter();
			}
			@Override
			public Adapter caseHavingVariations(HavingVariations object) {
				return createHavingVariationsAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.SkiQLModel <em>Ski QL Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiQLModel
	 * @generated
	 */
	public Adapter createSkiQLModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Query <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Query
	 * @generated
	 */
	public Adapter createQueryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery <em>Relationship Query</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery
	 * @generated
	 */
	public Adapter createRelationshipQueryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery <em>Entity Query</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery
	 * @generated
	 */
	public Adapter createEntityQueryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec <em>Entity Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec
	 * @generated
	 */
	public Adapter createEntitySpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityOperation <em>Entity Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityOperation
	 * @generated
	 */
	public Adapter createEntityOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.VersionHistory <em>Version History</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.VersionHistory
	 * @generated
	 */
	public Adapter createVersionHistoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Before <em>Before</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Before
	 * @generated
	 */
	public Adapter createBeforeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.After <em>After</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.After
	 * @generated
	 */
	public Adapter createAfterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Between <em>Between</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Between
	 * @generated
	 */
	public Adapter createBetweenAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.DateTime <em>Date Time</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.DateTime
	 * @generated
	 */
	public Adapter createDateTimeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.All <em>All</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.All
	 * @generated
	 */
	public Adapter createAllAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.CountOperation <em>Count Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.CountOperation
	 * @generated
	 */
	public Adapter createCountOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter <em>Variation Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter
	 * @generated
	 */
	public Adapter createVariationFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec <em>Property Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec
	 * @generated
	 */
	public Adapter createPropertySpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec <em>Property Selector Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec
	 * @generated
	 */
	public Adapter createPropertySelectorSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec <em>Relationship Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec
	 * @generated
	 */
	public Adapter createRelationshipSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.AggregationSpec <em>Aggregation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.AggregationSpec
	 * @generated
	 */
	public Adapter createAggregationSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.ReferenceSpec <em>Reference Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.ReferenceSpec
	 * @generated
	 */
	public Adapter createReferenceSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.TargetExpression <em>Target Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.TargetExpression
	 * @generated
	 */
	public Adapter createTargetExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression <em>Relationship Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression
	 * @generated
	 */
	public Adapter createRelationshipExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression <em>Entity Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression
	 * @generated
	 */
	public Adapter createEntityExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PrimitiveType
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType <em>Relationship Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType
	 * @generated
	 */
	public Adapter createRelationshipTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationSpec <em>Relation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationSpec
	 * @generated
	 */
	public Adapter createRelationSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertyFilter <em>Property Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertyFilter
	 * @generated
	 */
	public Adapter createPropertyFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Having <em>Having</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Having
	 * @generated
	 */
	public Adapter createHavingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingType <em>Having Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.HavingType
	 * @generated
	 */
	public Adapter createHavingTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingKeys <em>Having Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.HavingKeys
	 * @generated
	 */
	public Adapter createHavingKeysAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations <em>Having Variations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations
	 * @generated
	 */
	public Adapter createHavingVariationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SkiqlAdapterFactory
