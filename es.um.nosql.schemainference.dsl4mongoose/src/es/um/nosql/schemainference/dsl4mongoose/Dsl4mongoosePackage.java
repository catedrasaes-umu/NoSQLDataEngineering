/**
 */
package es.um.nosql.schemainference.dsl4mongoose;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongooseFactory
 * @model kind="package"
 * @generated
 */
public interface Dsl4mongoosePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dsl4mongoose";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modelum.es/dsl4mongoose";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dsl4mongoose";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Dsl4mongoosePackage eINSTANCE = es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl.init();

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.MongooseModelImpl <em>Mongoose Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.MongooseModelImpl
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getMongooseModel()
	 * @generated
	 */
	int MONGOOSE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGOOSE_MODEL__PARAMETERS = 0;

	/**
	 * The feature id for the '<em><b>Mapper</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGOOSE_MODEL__MAPPER = 1;

	/**
	 * The number of structural features of the '<em>Mongoose Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGOOSE_MODEL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Mongoose Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGOOSE_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.NamedElementImpl
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.EntityParameterImpl <em>Entity Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.EntityParameterImpl
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getEntityParameter()
	 * @generated
	 */
	int ENTITY_PARAMETER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARAMETER__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Validators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARAMETER__VALIDATORS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uniques</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARAMETER__UNIQUES = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Updates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARAMETER__UPDATES = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Discriminator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARAMETER__DISCRIMINATOR = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARAMETER__INDEXES = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Entity Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARAMETER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Entity Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_PARAMETER_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.FieldParameterImpl <em>Field Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.FieldParameterImpl
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getFieldParameter()
	 * @generated
	 */
	int FIELD_PARAMETER = 7;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_PARAMETER__FIELD_NAME = 0;

	/**
	 * The number of structural features of the '<em>Field Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_PARAMETER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Field Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.ValidatorImpl <em>Validator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.ValidatorImpl
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getValidator()
	 * @generated
	 */
	int VALIDATOR = 2;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR__FIELD_NAME = FIELD_PARAMETER__FIELD_NAME;

	/**
	 * The feature id for the '<em><b>Validator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR__VALIDATOR_NAME = FIELD_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Validator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_FEATURE_COUNT = FIELD_PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Validator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_OPERATION_COUNT = FIELD_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.UniqueImpl <em>Unique</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.UniqueImpl
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getUnique()
	 * @generated
	 */
	int UNIQUE = 4;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE__FIELD_NAME = FIELD_PARAMETER__FIELD_NAME;

	/**
	 * The number of structural features of the '<em>Unique</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_FEATURE_COUNT = FIELD_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Unique</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_OPERATION_COUNT = FIELD_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl <em>Update</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getUpdate()
	 * @generated
	 */
	int UPDATE = 5;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE__FIELD_NAME = FIELD_PARAMETER__FIELD_NAME;

	/**
	 * The number of structural features of the '<em>Update</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_FEATURE_COUNT = FIELD_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Update</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_OPERATION_COUNT = FIELD_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.IndexImpl <em>Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.IndexImpl
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getIndex()
	 * @generated
	 */
	int INDEX = 6;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__FIELD_NAME = FIELD_PARAMETER__FIELD_NAME;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__KIND = FIELD_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_FEATURE_COUNT = FIELD_PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_OPERATION_COUNT = FIELD_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.dsl4mongoose.IndexKind <em>Index Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.dsl4mongoose.IndexKind
	 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getIndexKind()
	 * @generated
	 */
	int INDEX_KIND = 8;


	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.dsl4mongoose.MongooseModel <em>Mongoose Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mongoose Model</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.MongooseModel
	 * @generated
	 */
	EClass getMongooseModel();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.dsl4mongoose.MongooseModel#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.MongooseModel#getParameters()
	 * @see #getMongooseModel()
	 * @generated
	 */
	EReference getMongooseModel_Parameters();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.schemainference.dsl4mongoose.MongooseModel#getMapper <em>Mapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mapper</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.MongooseModel#getMapper()
	 * @see #getMongooseModel()
	 * @generated
	 */
	EAttribute getMongooseModel_Mapper();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter <em>Entity Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Parameter</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.EntityParameter
	 * @generated
	 */
	EClass getEntityParameter();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getValidators <em>Validators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Validators</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getValidators()
	 * @see #getEntityParameter()
	 * @generated
	 */
	EReference getEntityParameter_Validators();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getUniques <em>Uniques</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Uniques</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getUniques()
	 * @see #getEntityParameter()
	 * @generated
	 */
	EReference getEntityParameter_Uniques();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getUpdates <em>Updates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Updates</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getUpdates()
	 * @see #getEntityParameter()
	 * @generated
	 */
	EReference getEntityParameter_Updates();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#isDiscriminator <em>Discriminator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Discriminator</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.EntityParameter#isDiscriminator()
	 * @see #getEntityParameter()
	 * @generated
	 */
	EAttribute getEntityParameter_Discriminator();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexes</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getIndexes()
	 * @see #getEntityParameter()
	 * @generated
	 */
	EReference getEntityParameter_Indexes();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.dsl4mongoose.Validator <em>Validator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validator</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Validator
	 * @generated
	 */
	EClass getValidator();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.schemainference.dsl4mongoose.Validator#getValidatorName <em>Validator Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Validator Name</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Validator#getValidatorName()
	 * @see #getValidator()
	 * @generated
	 */
	EAttribute getValidator_ValidatorName();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.dsl4mongoose.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.schemainference.dsl4mongoose.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.dsl4mongoose.Unique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unique</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Unique
	 * @generated
	 */
	EClass getUnique();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.dsl4mongoose.Update <em>Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Update</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Update
	 * @generated
	 */
	EClass getUpdate();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.dsl4mongoose.Index <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Index
	 * @generated
	 */
	EClass getIndex();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.schemainference.dsl4mongoose.Index#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Index#getKind()
	 * @see #getIndex()
	 * @generated
	 */
	EAttribute getIndex_Kind();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.dsl4mongoose.FieldParameter <em>Field Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Parameter</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.FieldParameter
	 * @generated
	 */
	EClass getFieldParameter();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.schemainference.dsl4mongoose.FieldParameter#getFieldName <em>Field Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Name</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.FieldParameter#getFieldName()
	 * @see #getFieldParameter()
	 * @generated
	 */
	EAttribute getFieldParameter_FieldName();

	/**
	 * Returns the meta object for enum '{@link es.um.nosql.schemainference.dsl4mongoose.IndexKind <em>Index Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Index Kind</em>'.
	 * @see es.um.nosql.schemainference.dsl4mongoose.IndexKind
	 * @generated
	 */
	EEnum getIndexKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Dsl4mongooseFactory getDsl4mongooseFactory();

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
		 * The meta object literal for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.MongooseModelImpl <em>Mongoose Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.MongooseModelImpl
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getMongooseModel()
		 * @generated
		 */
		EClass MONGOOSE_MODEL = eINSTANCE.getMongooseModel();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MONGOOSE_MODEL__PARAMETERS = eINSTANCE.getMongooseModel_Parameters();

		/**
		 * The meta object literal for the '<em><b>Mapper</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONGOOSE_MODEL__MAPPER = eINSTANCE.getMongooseModel_Mapper();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.EntityParameterImpl <em>Entity Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.EntityParameterImpl
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getEntityParameter()
		 * @generated
		 */
		EClass ENTITY_PARAMETER = eINSTANCE.getEntityParameter();

		/**
		 * The meta object literal for the '<em><b>Validators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_PARAMETER__VALIDATORS = eINSTANCE.getEntityParameter_Validators();

		/**
		 * The meta object literal for the '<em><b>Uniques</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_PARAMETER__UNIQUES = eINSTANCE.getEntityParameter_Uniques();

		/**
		 * The meta object literal for the '<em><b>Updates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_PARAMETER__UPDATES = eINSTANCE.getEntityParameter_Updates();

		/**
		 * The meta object literal for the '<em><b>Discriminator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY_PARAMETER__DISCRIMINATOR = eINSTANCE.getEntityParameter_Discriminator();

		/**
		 * The meta object literal for the '<em><b>Indexes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_PARAMETER__INDEXES = eINSTANCE.getEntityParameter_Indexes();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.ValidatorImpl <em>Validator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.ValidatorImpl
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getValidator()
		 * @generated
		 */
		EClass VALIDATOR = eINSTANCE.getValidator();

		/**
		 * The meta object literal for the '<em><b>Validator Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATOR__VALIDATOR_NAME = eINSTANCE.getValidator_ValidatorName();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.NamedElementImpl
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.UniqueImpl <em>Unique</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.UniqueImpl
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getUnique()
		 * @generated
		 */
		EClass UNIQUE = eINSTANCE.getUnique();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl <em>Update</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getUpdate()
		 * @generated
		 */
		EClass UPDATE = eINSTANCE.getUpdate();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.IndexImpl <em>Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.IndexImpl
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getIndex()
		 * @generated
		 */
		EClass INDEX = eINSTANCE.getIndex();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX__KIND = eINSTANCE.getIndex_Kind();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.dsl4mongoose.impl.FieldParameterImpl <em>Field Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.FieldParameterImpl
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getFieldParameter()
		 * @generated
		 */
		EClass FIELD_PARAMETER = eINSTANCE.getFieldParameter();

		/**
		 * The meta object literal for the '<em><b>Field Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_PARAMETER__FIELD_NAME = eINSTANCE.getFieldParameter_FieldName();

		/**
		 * The meta object literal for the '{@link es.um.nosql.schemainference.dsl4mongoose.IndexKind <em>Index Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.dsl4mongoose.IndexKind
		 * @see es.um.nosql.schemainference.dsl4mongoose.impl.Dsl4mongoosePackageImpl#getIndexKind()
		 * @generated
		 */
		EEnum INDEX_KIND = eINSTANCE.getIndexKind();

	}

} //Dsl4mongoosePackage
