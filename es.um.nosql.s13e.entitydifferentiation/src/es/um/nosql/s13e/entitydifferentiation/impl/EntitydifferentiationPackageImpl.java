/**
 */
package es.um.nosql.s13e.entitydifferentiation.impl;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;

import es.um.nosql.s13e.entitydifferentiation.EntityDiffSpec;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.entitydifferentiation.EntityVersionProp;
import es.um.nosql.s13e.entitydifferentiation.EntitydifferentiationFactory;
import es.um.nosql.s13e.entitydifferentiation.EntitydifferentiationPackage;
import es.um.nosql.s13e.entitydifferentiation.PropertySpec;

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
public class EntitydifferentiationPackageImpl extends EPackageImpl implements EntitydifferentiationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityDifferentiationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityDiffSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertySpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityVersionPropEClass = null;

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
	 * @see es.um.nosql.s13e.entitydifferentiation.EntitydifferentiationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EntitydifferentiationPackageImpl() {
		super(eNS_URI, EntitydifferentiationFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EntitydifferentiationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EntitydifferentiationPackage init() {
		if (isInited) return (EntitydifferentiationPackage)EPackage.Registry.INSTANCE.getEPackage(EntitydifferentiationPackage.eNS_URI);

		// Obtain or create and register package
		EntitydifferentiationPackageImpl theEntitydifferentiationPackage = (EntitydifferentiationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EntitydifferentiationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EntitydifferentiationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		NoSQLSchemaPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEntitydifferentiationPackage.createPackageContents();

		// Initialize created meta-data
		theEntitydifferentiationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEntitydifferentiationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EntitydifferentiationPackage.eNS_URI, theEntitydifferentiationPackage);
		return theEntitydifferentiationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityDifferentiation() {
		return entityDifferentiationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityDifferentiation_EntityDiffSpecs() {
		return (EReference)entityDifferentiationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityDifferentiation_Name() {
		return (EAttribute)entityDifferentiationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityDifferentiation_Schema() {
		return (EReference)entityDifferentiationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityDiffSpec() {
		return entityDiffSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityDiffSpec_Entity() {
		return (EReference)entityDiffSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityDiffSpec_EntityVersionProps() {
		return (EReference)entityDiffSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityDiffSpec_CommonProps() {
		return (EReference)entityDiffSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertySpec() {
		return propertySpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertySpec_Property() {
		return (EReference)propertySpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertySpec_NeedsTypeCheck() {
		return (EAttribute)propertySpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityVersionProp() {
		return entityVersionPropEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityVersionProp_PropertySpecs() {
		return (EReference)entityVersionPropEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityVersionProp_EntityVersion() {
		return (EReference)entityVersionPropEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityVersionProp_NotProps() {
		return (EReference)entityVersionPropEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntitydifferentiationFactory getEntitydifferentiationFactory() {
		return (EntitydifferentiationFactory)getEFactoryInstance();
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
		entityDifferentiationEClass = createEClass(ENTITY_DIFFERENTIATION);
		createEReference(entityDifferentiationEClass, ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS);
		createEAttribute(entityDifferentiationEClass, ENTITY_DIFFERENTIATION__NAME);
		createEReference(entityDifferentiationEClass, ENTITY_DIFFERENTIATION__SCHEMA);

		entityDiffSpecEClass = createEClass(ENTITY_DIFF_SPEC);
		createEReference(entityDiffSpecEClass, ENTITY_DIFF_SPEC__ENTITY);
		createEReference(entityDiffSpecEClass, ENTITY_DIFF_SPEC__ENTITY_VERSION_PROPS);
		createEReference(entityDiffSpecEClass, ENTITY_DIFF_SPEC__COMMON_PROPS);

		propertySpecEClass = createEClass(PROPERTY_SPEC);
		createEReference(propertySpecEClass, PROPERTY_SPEC__PROPERTY);
		createEAttribute(propertySpecEClass, PROPERTY_SPEC__NEEDS_TYPE_CHECK);

		entityVersionPropEClass = createEClass(ENTITY_VERSION_PROP);
		createEReference(entityVersionPropEClass, ENTITY_VERSION_PROP__PROPERTY_SPECS);
		createEReference(entityVersionPropEClass, ENTITY_VERSION_PROP__ENTITY_VERSION);
		createEReference(entityVersionPropEClass, ENTITY_VERSION_PROP__NOT_PROPS);
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

		// Obtain other dependent packages
		NoSQLSchemaPackage theNoSQLSchemaPackage = (NoSQLSchemaPackage)EPackage.Registry.INSTANCE.getEPackage(NoSQLSchemaPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(entityDifferentiationEClass, EntityDifferentiation.class, "EntityDifferentiation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEntityDifferentiation_EntityDiffSpecs(), this.getEntityDiffSpec(), null, "entityDiffSpecs", null, 0, -1, EntityDifferentiation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntityDifferentiation_Name(), ecorePackage.getEString(), "name", null, 0, 1, EntityDifferentiation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityDifferentiation_Schema(), theNoSQLSchemaPackage.getNoSQLSchema(), null, "schema", null, 1, 1, EntityDifferentiation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityDiffSpecEClass, EntityDiffSpec.class, "EntityDiffSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEntityDiffSpec_Entity(), theNoSQLSchemaPackage.getEntity(), null, "entity", null, 1, 1, EntityDiffSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityDiffSpec_EntityVersionProps(), this.getEntityVersionProp(), null, "entityVersionProps", null, 0, -1, EntityDiffSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityDiffSpec_CommonProps(), this.getPropertySpec(), null, "commonProps", null, 0, -1, EntityDiffSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertySpecEClass, PropertySpec.class, "PropertySpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertySpec_Property(), theNoSQLSchemaPackage.getProperty(), null, "property", null, 1, 1, PropertySpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertySpec_NeedsTypeCheck(), ecorePackage.getEBoolean(), "needsTypeCheck", null, 0, 1, PropertySpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityVersionPropEClass, EntityVersionProp.class, "EntityVersionProp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEntityVersionProp_PropertySpecs(), this.getPropertySpec(), null, "propertySpecs", null, 0, -1, EntityVersionProp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityVersionProp_EntityVersion(), theNoSQLSchemaPackage.getEntityVersion(), null, "entityVersion", null, 1, 1, EntityVersionProp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityVersionProp_NotProps(), this.getPropertySpec(), null, "notProps", null, 0, -1, EntityVersionProp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //EntitydifferentiationPackageImpl
