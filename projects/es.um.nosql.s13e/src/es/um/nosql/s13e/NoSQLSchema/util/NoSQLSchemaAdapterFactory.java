/**
 */
package es.um.nosql.s13e.NoSQLSchema.util;

import es.um.nosql.s13e.NoSQLSchema.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage
 * @generated
 */
public class NoSQLSchemaAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static NoSQLSchemaPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoSQLSchemaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = NoSQLSchemaPackage.eINSTANCE;
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
	protected NoSQLSchemaSwitch<Adapter> modelSwitch =
		new NoSQLSchemaSwitch<Adapter>() {
			@Override
			public Adapter caseNoSQLSchema(NoSQLSchema object) {
				return createNoSQLSchemaAdapter();
			}
			@Override
			public Adapter caseEntityClass(EntityClass object) {
				return createEntityClassAdapter();
			}
			@Override
			public Adapter caseStructuralVariation(StructuralVariation object) {
				return createStructuralVariationAdapter();
			}
			@Override
			public Adapter caseProperty(Property object) {
				return createPropertyAdapter();
			}
			@Override
			public Adapter caseAttribute(Attribute object) {
				return createAttributeAdapter();
			}
			@Override
			public Adapter caseType(Type object) {
				return createTypeAdapter();
			}
			@Override
			public Adapter casePList(PList object) {
				return createPListAdapter();
			}
			@Override
			public Adapter caseAssociation(Association object) {
				return createAssociationAdapter();
			}
			@Override
			public Adapter caseReference(Reference object) {
				return createReferenceAdapter();
			}
			@Override
			public Adapter caseAggregate(Aggregate object) {
				return createAggregateAdapter();
			}
			@Override
			public Adapter casePrimitiveType(PrimitiveType object) {
				return createPrimitiveTypeAdapter();
			}
			@Override
			public Adapter caseNull(Null object) {
				return createNullAdapter();
			}
			@Override
			public Adapter caseReferenceClass(ReferenceClass object) {
				return createReferenceClassAdapter();
			}
			@Override
			public Adapter caseClassifier(Classifier object) {
				return createClassifierAdapter();
			}
			@Override
			public Adapter casePMap(PMap object) {
				return createPMapAdapter();
			}
			@Override
			public Adapter casePSet(PSet object) {
				return createPSetAdapter();
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
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.NoSQLSchema <em>No SQL Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchema
	 * @generated
	 */
	public Adapter createNoSQLSchemaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.EntityClass <em>Entity Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.EntityClass
	 * @generated
	 */
	public Adapter createEntityClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation <em>Structural Variation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.StructuralVariation
	 * @generated
	 */
	public Adapter createStructuralVariationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.Property
	 * @generated
	 */
	public Adapter createPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.PList <em>PList</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.PList
	 * @generated
	 */
	public Adapter createPListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.Association <em>Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.Association
	 * @generated
	 */
	public Adapter createAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.Reference
	 * @generated
	 */
	public Adapter createReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.Aggregate <em>Aggregate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.Aggregate
	 * @generated
	 */
	public Adapter createAggregateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.PrimitiveType
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.Null <em>Null</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.Null
	 * @generated
	 */
	public Adapter createNullAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.ReferenceClass <em>Reference Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.ReferenceClass
	 * @generated
	 */
	public Adapter createReferenceClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.Classifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.Classifier
	 * @generated
	 */
	public Adapter createClassifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.PMap <em>PMap</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.PMap
	 * @generated
	 */
	public Adapter createPMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.s13e.NoSQLSchema.PSet <em>PSet</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.s13e.NoSQLSchema.PSet
	 * @generated
	 */
	public Adapter createPSetAdapter() {
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

} //NoSQLSchemaAdapterFactory
