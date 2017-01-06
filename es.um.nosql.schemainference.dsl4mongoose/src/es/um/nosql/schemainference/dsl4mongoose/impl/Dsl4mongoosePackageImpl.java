/**
 */
package es.um.nosql.schemainference.dsl4mongoose.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import es.um.nosql.schemainference.dsl4mongoose.Dsl4mongooseFactory;
import es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage;
import es.um.nosql.schemainference.dsl4mongoose.EntityParameter;
import es.um.nosql.schemainference.dsl4mongoose.FieldParameter;
import es.um.nosql.schemainference.dsl4mongoose.Index;
import es.um.nosql.schemainference.dsl4mongoose.IndexKind;
import es.um.nosql.schemainference.dsl4mongoose.MongooseModel;
import es.um.nosql.schemainference.dsl4mongoose.NamedElement;
import es.um.nosql.schemainference.dsl4mongoose.Unique;
import es.um.nosql.schemainference.dsl4mongoose.Update;
import es.um.nosql.schemainference.dsl4mongoose.Validator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Dsl4mongoosePackageImpl extends EPackageImpl implements Dsl4mongoosePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mongooseModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uniqueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass updateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum indexKindEEnum = null;

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
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Dsl4mongoosePackageImpl() {
		super(eNS_URI, Dsl4mongooseFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Dsl4mongoosePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Dsl4mongoosePackage init() {
		if (isInited) return (Dsl4mongoosePackage)EPackage.Registry.INSTANCE.getEPackage(Dsl4mongoosePackage.eNS_URI);

		// Obtain or create and register package
		Dsl4mongoosePackageImpl theDsl4mongoosePackage = (Dsl4mongoosePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Dsl4mongoosePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Dsl4mongoosePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theDsl4mongoosePackage.createPackageContents();

		// Initialize created meta-data
		theDsl4mongoosePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDsl4mongoosePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Dsl4mongoosePackage.eNS_URI, theDsl4mongoosePackage);
		return theDsl4mongoosePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMongooseModel() {
		return mongooseModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMongooseModel_Parameters() {
		return (EReference)mongooseModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMongooseModel_Mapper() {
		return (EAttribute)mongooseModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityParameter() {
		return entityParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityParameter_Validators() {
		return (EReference)entityParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityParameter_Uniques() {
		return (EReference)entityParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityParameter_Updates() {
		return (EReference)entityParameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityParameter_Discriminator() {
		return (EAttribute)entityParameterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityParameter_Indexes() {
		return (EReference)entityParameterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValidator() {
		return validatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidator_ValidatorName() {
		return (EAttribute)validatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnique() {
		return uniqueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUpdate() {
		return updateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndex() {
		return indexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndex_Kind() {
		return (EAttribute)indexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldParameter() {
		return fieldParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFieldParameter_FieldName() {
		return (EAttribute)fieldParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getIndexKind() {
		return indexKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dsl4mongooseFactory getDsl4mongooseFactory() {
		return (Dsl4mongooseFactory)getEFactoryInstance();
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
		mongooseModelEClass = createEClass(MONGOOSE_MODEL);
		createEReference(mongooseModelEClass, MONGOOSE_MODEL__PARAMETERS);
		createEAttribute(mongooseModelEClass, MONGOOSE_MODEL__MAPPER);

		entityParameterEClass = createEClass(ENTITY_PARAMETER);
		createEReference(entityParameterEClass, ENTITY_PARAMETER__VALIDATORS);
		createEReference(entityParameterEClass, ENTITY_PARAMETER__UNIQUES);
		createEReference(entityParameterEClass, ENTITY_PARAMETER__UPDATES);
		createEAttribute(entityParameterEClass, ENTITY_PARAMETER__DISCRIMINATOR);
		createEReference(entityParameterEClass, ENTITY_PARAMETER__INDEXES);

		validatorEClass = createEClass(VALIDATOR);
		createEAttribute(validatorEClass, VALIDATOR__VALIDATOR_NAME);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		uniqueEClass = createEClass(UNIQUE);

		updateEClass = createEClass(UPDATE);

		indexEClass = createEClass(INDEX);
		createEAttribute(indexEClass, INDEX__KIND);

		fieldParameterEClass = createEClass(FIELD_PARAMETER);
		createEAttribute(fieldParameterEClass, FIELD_PARAMETER__FIELD_NAME);

		// Create enums
		indexKindEEnum = createEEnum(INDEX_KIND);
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
		entityParameterEClass.getESuperTypes().add(this.getNamedElement());
		validatorEClass.getESuperTypes().add(this.getFieldParameter());
		uniqueEClass.getESuperTypes().add(this.getFieldParameter());
		updateEClass.getESuperTypes().add(this.getFieldParameter());
		indexEClass.getESuperTypes().add(this.getFieldParameter());

		// Initialize classes, features, and operations; add parameters
		initEClass(mongooseModelEClass, MongooseModel.class, "MongooseModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMongooseModel_Parameters(), this.getEntityParameter(), null, "parameters", null, 1, -1, MongooseModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMongooseModel_Mapper(), ecorePackage.getEString(), "mapper", null, 0, 1, MongooseModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityParameterEClass, EntityParameter.class, "EntityParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEntityParameter_Validators(), this.getValidator(), null, "validators", null, 0, -1, EntityParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityParameter_Uniques(), this.getUnique(), null, "uniques", null, 0, -1, EntityParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityParameter_Updates(), this.getUpdate(), null, "updates", null, 0, -1, EntityParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntityParameter_Discriminator(), ecorePackage.getEBoolean(), "discriminator", null, 0, 1, EntityParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityParameter_Indexes(), this.getIndex(), null, "indexes", null, 0, -1, EntityParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(validatorEClass, Validator.class, "Validator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidator_ValidatorName(), ecorePackage.getEString(), "ValidatorName", null, 0, 1, Validator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uniqueEClass, Unique.class, "Unique", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(updateEClass, Update.class, "Update", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(indexEClass, Index.class, "Index", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIndex_Kind(), this.getIndexKind(), "kind", null, 0, 1, Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fieldParameterEClass, FieldParameter.class, "FieldParameter", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFieldParameter_FieldName(), ecorePackage.getEString(), "fieldName", null, 0, 1, FieldParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(indexKindEEnum, IndexKind.class, "IndexKind");
		addEEnumLiteral(indexKindEEnum, IndexKind.SORTED);
		addEEnumLiteral(indexKindEEnum, IndexKind.HASHED);

		// Create resource
		createResource(eNS_URI);
	}

} //Dsl4mongoosePackageImpl
