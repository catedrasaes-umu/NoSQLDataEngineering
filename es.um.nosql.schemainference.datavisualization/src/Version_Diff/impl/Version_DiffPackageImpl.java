/**
 */
package Version_Diff.impl;

import Version_Diff.AggregateType;
import Version_Diff.AssociationType;
import Version_Diff.EntityType;
import Version_Diff.FieldType;
import Version_Diff.HasField;
import Version_Diff.HasNotField;
import Version_Diff.HeterogeneousTupleType;
import Version_Diff.HomogeneousTupleType;
import Version_Diff.NoSQLDifferences;
import Version_Diff.PrimitiveType;
import Version_Diff.ReferenceType;
import Version_Diff.TupleType;
import Version_Diff.TypeDifference;
import Version_Diff.TypeHint;
import Version_Diff.Version_DiffFactory;
import Version_Diff.Version_DiffPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Version_DiffPackageImpl extends EPackageImpl implements Version_DiffPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass noSQLDifferencesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeDifferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeHintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hasFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hasNotFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aggregateTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass homogeneousTupleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass heterogeneousTupleTypeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see Version_Diff.Version_DiffPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Version_DiffPackageImpl() {
		super(eNS_URI, Version_DiffFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link Version_DiffPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Version_DiffPackage init() {
		if (isInited) return (Version_DiffPackage)EPackage.Registry.INSTANCE.getEPackage(Version_DiffPackage.eNS_URI);

		// Obtain or create and register package
		Version_DiffPackageImpl theVersion_DiffPackage = (Version_DiffPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Version_DiffPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Version_DiffPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theVersion_DiffPackage.createPackageContents();

		// Initialize created meta-data
		theVersion_DiffPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theVersion_DiffPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Version_DiffPackage.eNS_URI, theVersion_DiffPackage);
		return theVersion_DiffPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNoSQLDifferences() {
		return noSQLDifferencesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNoSQLDifferences_Name() {
		return (EAttribute)noSQLDifferencesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNoSQLDifferences_HasTypeDifferences() {
		return (EReference)noSQLDifferencesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDifference() {
		return typeDifferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeDifference_Name() {
		return (EAttribute)typeDifferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDifference_Hints() {
		return (EReference)typeDifferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeHint() {
		return typeHintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeHint_WithName() {
		return (EAttribute)typeHintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeHint_WithType() {
		return (EReference)typeHintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHasField() {
		return hasFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHasNotField() {
		return hasNotFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldType() {
		return fieldTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveType() {
		return primitiveTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrimitiveType_Type() {
		return (EAttribute)primitiveTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTupleType() {
		return tupleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociationType() {
		return associationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociationType_LowerBound() {
		return (EAttribute)associationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociationType_UpperBound() {
		return (EAttribute)associationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAggregateType() {
		return aggregateTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAggregateType_Type() {
		return (EAttribute)aggregateTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceType() {
		return referenceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceType_Type() {
		return (EAttribute)referenceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityType() {
		return entityTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityType_Type() {
		return (EAttribute)entityTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHomogeneousTupleType() {
		return homogeneousTupleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHomogeneousTupleType_Type() {
		return (EAttribute)homogeneousTupleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHeterogeneousTupleType() {
		return heterogeneousTupleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHeterogeneousTupleType_Type() {
		return (EAttribute)heterogeneousTupleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Version_DiffFactory getVersion_DiffFactory() {
		return (Version_DiffFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		noSQLDifferencesEClass = createEClass(NO_SQL_DIFFERENCES);
		createEAttribute(noSQLDifferencesEClass, NO_SQL_DIFFERENCES__NAME);
		createEReference(noSQLDifferencesEClass, NO_SQL_DIFFERENCES__HAS_TYPE_DIFFERENCES);

		typeDifferenceEClass = createEClass(TYPE_DIFFERENCE);
		createEAttribute(typeDifferenceEClass, TYPE_DIFFERENCE__NAME);
		createEReference(typeDifferenceEClass, TYPE_DIFFERENCE__HINTS);

		typeHintEClass = createEClass(TYPE_HINT);
		createEAttribute(typeHintEClass, TYPE_HINT__WITH_NAME);
		createEReference(typeHintEClass, TYPE_HINT__WITH_TYPE);

		hasFieldEClass = createEClass(HAS_FIELD);

		hasNotFieldEClass = createEClass(HAS_NOT_FIELD);

		fieldTypeEClass = createEClass(FIELD_TYPE);

		primitiveTypeEClass = createEClass(PRIMITIVE_TYPE);
		createEAttribute(primitiveTypeEClass, PRIMITIVE_TYPE__TYPE);

		tupleTypeEClass = createEClass(TUPLE_TYPE);

		associationTypeEClass = createEClass(ASSOCIATION_TYPE);
		createEAttribute(associationTypeEClass, ASSOCIATION_TYPE__LOWER_BOUND);
		createEAttribute(associationTypeEClass, ASSOCIATION_TYPE__UPPER_BOUND);

		aggregateTypeEClass = createEClass(AGGREGATE_TYPE);
		createEAttribute(aggregateTypeEClass, AGGREGATE_TYPE__TYPE);

		referenceTypeEClass = createEClass(REFERENCE_TYPE);
		createEAttribute(referenceTypeEClass, REFERENCE_TYPE__TYPE);

		entityTypeEClass = createEClass(ENTITY_TYPE);
		createEAttribute(entityTypeEClass, ENTITY_TYPE__TYPE);

		homogeneousTupleTypeEClass = createEClass(HOMOGENEOUS_TUPLE_TYPE);
		createEAttribute(homogeneousTupleTypeEClass, HOMOGENEOUS_TUPLE_TYPE__TYPE);

		heterogeneousTupleTypeEClass = createEClass(HETEROGENEOUS_TUPLE_TYPE);
		createEAttribute(heterogeneousTupleTypeEClass, HETEROGENEOUS_TUPLE_TYPE__TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		hasFieldEClass.getESuperTypes().add(this.getTypeHint());
		hasNotFieldEClass.getESuperTypes().add(this.getTypeHint());
		primitiveTypeEClass.getESuperTypes().add(this.getFieldType());
		tupleTypeEClass.getESuperTypes().add(this.getFieldType());
		associationTypeEClass.getESuperTypes().add(this.getFieldType());
		aggregateTypeEClass.getESuperTypes().add(this.getAssociationType());
		referenceTypeEClass.getESuperTypes().add(this.getAssociationType());
		entityTypeEClass.getESuperTypes().add(this.getFieldType());
		homogeneousTupleTypeEClass.getESuperTypes().add(this.getTupleType());
		heterogeneousTupleTypeEClass.getESuperTypes().add(this.getTupleType());

		// Initialize classes, features, and operations; add parameters
		initEClass(noSQLDifferencesEClass, NoSQLDifferences.class, "NoSQLDifferences", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNoSQLDifferences_Name(), ecorePackage.getEString(), "name", null, 1, 1, NoSQLDifferences.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNoSQLDifferences_HasTypeDifferences(), this.getTypeDifference(), null, "hasTypeDifferences", null, 1, -1, NoSQLDifferences.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeDifferenceEClass, TypeDifference.class, "TypeDifference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTypeDifference_Name(), ecorePackage.getEString(), "name", null, 1, 1, TypeDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDifference_Hints(), this.getTypeHint(), null, "hints", null, 1, -1, TypeDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeHintEClass, TypeHint.class, "TypeHint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTypeHint_WithName(), ecorePackage.getEString(), "withName", null, 1, 1, TypeHint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeHint_WithType(), this.getFieldType(), null, "withType", null, 1, 1, TypeHint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hasFieldEClass, HasField.class, "HasField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(hasNotFieldEClass, HasNotField.class, "HasNotField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fieldTypeEClass, FieldType.class, "FieldType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeEClass, PrimitiveType.class, "PrimitiveType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrimitiveType_Type(), ecorePackage.getEString(), "type", null, 1, 1, PrimitiveType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tupleTypeEClass, TupleType.class, "TupleType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(associationTypeEClass, AssociationType.class, "AssociationType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssociationType_LowerBound(), ecorePackage.getEInt(), "lowerBound", null, 0, 1, AssociationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociationType_UpperBound(), ecorePackage.getEInt(), "upperBound", null, 0, 1, AssociationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aggregateTypeEClass, AggregateType.class, "AggregateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAggregateType_Type(), ecorePackage.getEString(), "type", null, 1, -1, AggregateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceTypeEClass, ReferenceType.class, "ReferenceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReferenceType_Type(), ecorePackage.getEString(), "type", null, 1, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityTypeEClass, EntityType.class, "EntityType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntityType_Type(), ecorePackage.getEString(), "type", null, 1, 1, EntityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(homogeneousTupleTypeEClass, HomogeneousTupleType.class, "HomogeneousTupleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHomogeneousTupleType_Type(), ecorePackage.getEString(), "type", null, 1, 1, HomogeneousTupleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(heterogeneousTupleTypeEClass, HeterogeneousTupleType.class, "HeterogeneousTupleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHeterogeneousTupleType_Type(), ecorePackage.getEString(), "type", null, 1, -1, HeterogeneousTupleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //Version_DiffPackageImpl
