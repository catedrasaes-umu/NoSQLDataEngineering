/**
 */
package Version_Diff.impl;

import Version_Diff.*;

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
public class Version_DiffFactoryImpl extends EFactoryImpl implements Version_DiffFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Version_DiffFactory init() {
		try {
			Version_DiffFactory theVersion_DiffFactory = (Version_DiffFactory)EPackage.Registry.INSTANCE.getEFactory(Version_DiffPackage.eNS_URI);
			if (theVersion_DiffFactory != null) {
				return theVersion_DiffFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Version_DiffFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Version_DiffFactoryImpl() {
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
			case Version_DiffPackage.NO_SQL_DIFFERENCES: return createNoSQLDifferences();
			case Version_DiffPackage.TYPE_DIFFERENCE: return createTypeDifference();
			case Version_DiffPackage.HAS_FIELD: return createHasField();
			case Version_DiffPackage.HAS_NOT_FIELD: return createHasNotField();
			case Version_DiffPackage.PRIMITIVE_TYPE: return createPrimitiveType();
			case Version_DiffPackage.AGGREGATE_TYPE: return createAggregateType();
			case Version_DiffPackage.REFERENCE_TYPE: return createReferenceType();
			case Version_DiffPackage.ENTITY_TYPE: return createEntityType();
			case Version_DiffPackage.HOMOGENEOUS_TUPLE_TYPE: return createHomogeneousTupleType();
			case Version_DiffPackage.HETEROGENEOUS_TUPLE_TYPE: return createHeterogeneousTupleType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoSQLDifferences createNoSQLDifferences() {
		NoSQLDifferencesImpl noSQLDifferences = new NoSQLDifferencesImpl();
		return noSQLDifferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDifference createTypeDifference() {
		TypeDifferenceImpl typeDifference = new TypeDifferenceImpl();
		return typeDifference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HasField createHasField() {
		HasFieldImpl hasField = new HasFieldImpl();
		return hasField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HasNotField createHasNotField() {
		HasNotFieldImpl hasNotField = new HasNotFieldImpl();
		return hasNotField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType createPrimitiveType() {
		PrimitiveTypeImpl primitiveType = new PrimitiveTypeImpl();
		return primitiveType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AggregateType createAggregateType() {
		AggregateTypeImpl aggregateType = new AggregateTypeImpl();
		return aggregateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceType createReferenceType() {
		ReferenceTypeImpl referenceType = new ReferenceTypeImpl();
		return referenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityType createEntityType() {
		EntityTypeImpl entityType = new EntityTypeImpl();
		return entityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HomogeneousTupleType createHomogeneousTupleType() {
		HomogeneousTupleTypeImpl homogeneousTupleType = new HomogeneousTupleTypeImpl();
		return homogeneousTupleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeterogeneousTupleType createHeterogeneousTupleType() {
		HeterogeneousTupleTypeImpl heterogeneousTupleType = new HeterogeneousTupleTypeImpl();
		return heterogeneousTupleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Version_DiffPackage getVersion_DiffPackage() {
		return (Version_DiffPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Version_DiffPackage getPackage() {
		return Version_DiffPackage.eINSTANCE;
	}

} //Version_DiffFactoryImpl
