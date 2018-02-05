/**
 */
package es.um.nosql.schemainference.dsl4mongoose.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import es.um.nosql.schemainference.dsl4mongoose.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage
 * @generated
 */
public class Dsl4mongooseAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Dsl4mongoosePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dsl4mongooseAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Dsl4mongoosePackage.eINSTANCE;
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
	protected Dsl4mongooseSwitch<Adapter> modelSwitch =
		new Dsl4mongooseSwitch<Adapter>() {
			@Override
			public Adapter caseMongooseModel(MongooseModel object) {
				return createMongooseModelAdapter();
			}
			@Override
			public Adapter caseEntityParameter(EntityParameter object) {
				return createEntityParameterAdapter();
			}
			@Override
			public Adapter caseValidator(Validator object) {
				return createValidatorAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseUnique(Unique object) {
				return createUniqueAdapter();
			}
			@Override
			public Adapter caseUpdate(Update object) {
				return createUpdateAdapter();
			}
			@Override
			public Adapter caseIndex(Index object) {
				return createIndexAdapter();
			}
			@Override
			public Adapter caseFieldParameter(FieldParameter object) {
				return createFieldParameterAdapter();
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
	 * Creates a new adapter for an object of class '{@link es.um.nosql.schemainference.dsl4mongoose.MongooseModel <em>Mongoose Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.schemainference.dsl4mongoose.MongooseModel
	 * @generated
	 */
	public Adapter createMongooseModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter <em>Entity Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.schemainference.dsl4mongoose.EntityParameter
	 * @generated
	 */
	public Adapter createEntityParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.schemainference.dsl4mongoose.Validator <em>Validator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Validator
	 * @generated
	 */
	public Adapter createValidatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.schemainference.dsl4mongoose.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.schemainference.dsl4mongoose.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.schemainference.dsl4mongoose.Unique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Unique
	 * @generated
	 */
	public Adapter createUniqueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.schemainference.dsl4mongoose.Update <em>Update</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Update
	 * @generated
	 */
	public Adapter createUpdateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.schemainference.dsl4mongoose.Index <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Index
	 * @generated
	 */
	public Adapter createIndexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.um.nosql.schemainference.dsl4mongoose.FieldParameter <em>Field Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.um.nosql.schemainference.dsl4mongoose.FieldParameter
	 * @generated
	 */
	public Adapter createFieldParameterAdapter() {
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

} //Dsl4mongooseAdapterFactory
