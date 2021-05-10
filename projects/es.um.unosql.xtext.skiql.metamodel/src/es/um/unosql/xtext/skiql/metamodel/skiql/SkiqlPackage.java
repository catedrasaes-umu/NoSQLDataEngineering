/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;

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
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlFactory
 * @model kind="package"
 * @generated
 */
public interface SkiqlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "skiql";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.um.es/skiql";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "skiql";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SkiqlPackage eINSTANCE = es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl.init();

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiQLModelImpl <em>Ski QL Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiQLModelImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getSkiQLModel()
	 * @generated
	 */
	int SKI_QL_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Query</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKI_QL_MODEL__QUERY = 0;

	/**
	 * The number of structural features of the '<em>Ski QL Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKI_QL_MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Ski QL Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKI_QL_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.QueryImpl <em>Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.QueryImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getQuery()
	 * @generated
	 */
	int QUERY = 1;

	/**
	 * The number of structural features of the '<em>Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipQueryImpl <em>Relationship Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipQueryImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipQuery()
	 * @generated
	 */
	int RELATIONSHIP_QUERY = 2;

	/**
	 * The feature id for the '<em><b>From</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_QUERY__FROM = QUERY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_QUERY__TO = QUERY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Relationship Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_QUERY_FEATURE_COUNT = QUERY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Relationship Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_QUERY_OPERATION_COUNT = QUERY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityQueryImpl <em>Entity Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityQueryImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getEntityQuery()
	 * @generated
	 */
	int ENTITY_QUERY = 3;

	/**
	 * The feature id for the '<em><b>Entity Spec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_QUERY__ENTITY_SPEC = QUERY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_QUERY__OPERATION = QUERY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Having</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_QUERY__HAVING = QUERY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Entity Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_QUERY_FEATURE_COUNT = QUERY_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Entity Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_QUERY_OPERATION_COUNT = QUERY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntitySpecImpl <em>Entity Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntitySpecImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getEntitySpec()
	 * @generated
	 */
	int ENTITY_SPEC = 4;

	/**
	 * The feature id for the '<em><b>Variation Filter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_SPEC__VARIATION_FILTER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_SPEC__NAME = 1;

	/**
	 * The number of structural features of the '<em>Entity Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_SPEC_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Entity Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_SPEC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.OperationImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 17;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityOperationImpl <em>Entity Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityOperationImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getEntityOperation()
	 * @generated
	 */
	int ENTITY_OPERATION = 5;

	/**
	 * The number of structural features of the '<em>Entity Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_OPERATION_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Entity Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_OPERATION_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.TypeImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 6;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.VersionHistoryImpl <em>Version History</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.VersionHistoryImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getVersionHistory()
	 * @generated
	 */
	int VERSION_HISTORY = 7;

	/**
	 * The number of structural features of the '<em>Version History</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_HISTORY_FEATURE_COUNT = ENTITY_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Version History</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_HISTORY_OPERATION_COUNT = ENTITY_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.BeforeImpl <em>Before</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.BeforeImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getBefore()
	 * @generated
	 */
	int BEFORE = 8;

	/**
	 * The feature id for the '<em><b>Date Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEFORE__DATE_TIME = VERSION_HISTORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Before</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEFORE_FEATURE_COUNT = VERSION_HISTORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Before</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEFORE_OPERATION_COUNT = VERSION_HISTORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.AfterImpl <em>After</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.AfterImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getAfter()
	 * @generated
	 */
	int AFTER = 9;

	/**
	 * The feature id for the '<em><b>Date Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER__DATE_TIME = VERSION_HISTORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>After</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER_FEATURE_COUNT = VERSION_HISTORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>After</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER_OPERATION_COUNT = VERSION_HISTORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.BetweenImpl <em>Between</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.BetweenImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getBetween()
	 * @generated
	 */
	int BETWEEN = 10;

	/**
	 * The feature id for the '<em><b>After Date Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETWEEN__AFTER_DATE_TIME = VERSION_HISTORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Before Date Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETWEEN__BEFORE_DATE_TIME = VERSION_HISTORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Between</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETWEEN_FEATURE_COUNT = VERSION_HISTORY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Between</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETWEEN_OPERATION_COUNT = VERSION_HISTORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.DateTimeImpl <em>Date Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.DateTimeImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getDateTime()
	 * @generated
	 */
	int DATE_TIME = 11;

	/**
	 * The feature id for the '<em><b>Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME__DAY = 0;

	/**
	 * The feature id for the '<em><b>Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME__MONTH = 1;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME__YEAR = 2;

	/**
	 * The feature id for the '<em><b>Hour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME__HOUR = 3;

	/**
	 * The feature id for the '<em><b>Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME__MINUTES = 4;

	/**
	 * The feature id for the '<em><b>Seconds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME__SECONDS = 5;

	/**
	 * The number of structural features of the '<em>Date Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Date Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.AllImpl <em>All</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.AllImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getAll()
	 * @generated
	 */
	int ALL = 12;

	/**
	 * The number of structural features of the '<em>All</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_FEATURE_COUNT = VERSION_HISTORY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>All</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_OPERATION_COUNT = VERSION_HISTORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.CountOperationImpl <em>Count Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.CountOperationImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getCountOperation()
	 * @generated
	 */
	int COUNT_OPERATION = 13;

	/**
	 * The number of structural features of the '<em>Count Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNT_OPERATION_FEATURE_COUNT = ENTITY_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Count Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNT_OPERATION_OPERATION_COUNT = ENTITY_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.VariationFilterImpl <em>Variation Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.VariationFilterImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getVariationFilter()
	 * @generated
	 */
	int VARIATION_FILTER = 14;

	/**
	 * The feature id for the '<em><b>Property Specs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIATION_FILTER__PROPERTY_SPECS = 0;

	/**
	 * The feature id for the '<em><b>Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIATION_FILTER__ONLY = 1;

	/**
	 * The number of structural features of the '<em>Variation Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIATION_FILTER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Variation Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIATION_FILTER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySpecImpl <em>Property Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySpecImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPropertySpec()
	 * @generated
	 */
	int PROPERTY_SPEC = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_SPEC__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Property Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_SPEC_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Property Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_SPEC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySelectorSpecImpl <em>Property Selector Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySelectorSpecImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPropertySelectorSpec()
	 * @generated
	 */
	int PROPERTY_SELECTOR_SPEC = 16;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_SELECTOR_SPEC__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Specifity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_SELECTOR_SPEC__SPECIFITY = 1;

	/**
	 * The number of structural features of the '<em>Property Selector Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_SELECTOR_SPEC_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Property Selector Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_SELECTOR_SPEC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipSpecImpl <em>Relationship Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipSpecImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipSpec()
	 * @generated
	 */
	int RELATIONSHIP_SPEC = 18;

	/**
	 * The feature id for the '<em><b>Target Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_SPEC__TARGET_EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Indirect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_SPEC__INDIRECT = 1;

	/**
	 * The feature id for the '<em><b>Relation Spec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_SPEC__RELATION_SPEC = 2;

	/**
	 * The number of structural features of the '<em>Relationship Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_SPEC_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Relationship Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_SPEC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationSpecImpl <em>Relation Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationSpecImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationSpec()
	 * @generated
	 */
	int RELATION_SPEC = 26;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SPEC__NAME = 0;

	/**
	 * The number of structural features of the '<em>Relation Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SPEC_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Relation Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SPEC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.AggregationSpecImpl <em>Aggregation Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.AggregationSpecImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getAggregationSpec()
	 * @generated
	 */
	int AGGREGATION_SPEC = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGGREGATION_SPEC__NAME = RELATION_SPEC__NAME;

	/**
	 * The number of structural features of the '<em>Aggregation Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGGREGATION_SPEC_FEATURE_COUNT = RELATION_SPEC_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Aggregation Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGGREGATION_SPEC_OPERATION_COUNT = RELATION_SPEC_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.ReferenceSpecImpl <em>Reference Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.ReferenceSpecImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getReferenceSpec()
	 * @generated
	 */
	int REFERENCE_SPEC = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_SPEC__NAME = RELATION_SPEC__NAME;

	/**
	 * The feature id for the '<em><b>Variation Filter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_SPEC__VARIATION_FILTER = RELATION_SPEC_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reference Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_SPEC_FEATURE_COUNT = RELATION_SPEC_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Reference Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_SPEC_OPERATION_COUNT = RELATION_SPEC_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.TargetExpressionImpl <em>Target Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.TargetExpressionImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getTargetExpression()
	 * @generated
	 */
	int TARGET_EXPRESSION = 21;

	/**
	 * The number of structural features of the '<em>Target Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Target Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_EXPRESSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipExpressionImpl <em>Relationship Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipExpressionImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipExpression()
	 * @generated
	 */
	int RELATIONSHIP_EXPRESSION = 22;

	/**
	 * The feature id for the '<em><b>Relationship Query</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY = TARGET_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Relationship Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_EXPRESSION_FEATURE_COUNT = TARGET_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Relationship Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_EXPRESSION_OPERATION_COUNT = TARGET_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityExpressionImpl <em>Entity Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityExpressionImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getEntityExpression()
	 * @generated
	 */
	int ENTITY_EXPRESSION = 23;

	/**
	 * The feature id for the '<em><b>Entity Spec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_EXPRESSION__ENTITY_SPEC = TARGET_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Entity Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_EXPRESSION_FEATURE_COUNT = TARGET_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Entity Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_EXPRESSION_OPERATION_COUNT = TARGET_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.PrimitiveTypeImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPrimitiveType()
	 * @generated
	 */
	int PRIMITIVE_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__TYPE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Array</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__ARRAY = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipTypeImpl <em>Relationship Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipTypeImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipType()
	 * @generated
	 */
	int RELATIONSHIP_TYPE = 25;

	/**
	 * The feature id for the '<em><b>Target Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_TYPE__TARGET_ENTITY_NAME = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Relation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_TYPE__RELATION_TYPE = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Relationship Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Relationship Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertyFilterImpl <em>Property Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertyFilterImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPropertyFilter()
	 * @generated
	 */
	int PROPERTY_FILTER = 27;

	/**
	 * The feature id for the '<em><b>Property Selector Specs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FILTER__PROPERTY_SELECTOR_SPECS = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Property Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FILTER_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Property Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FILTER_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingImpl <em>Having</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getHaving()
	 * @generated
	 */
	int HAVING = 28;

	/**
	 * The feature id for the '<em><b>Negative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING__NEGATIVE = 0;

	/**
	 * The feature id for the '<em><b>Having Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING__HAVING_TYPE = 1;

	/**
	 * The number of structural features of the '<em>Having</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Having</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingTypeImpl <em>Having Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingTypeImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getHavingType()
	 * @generated
	 */
	int HAVING_TYPE = 29;

	/**
	 * The number of structural features of the '<em>Having Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_TYPE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Having Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingKeysImpl <em>Having Keys</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingKeysImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getHavingKeys()
	 * @generated
	 */
	int HAVING_KEYS = 30;

	/**
	 * The number of structural features of the '<em>Having Keys</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_KEYS_FEATURE_COUNT = HAVING_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Having Keys</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_KEYS_OPERATION_COUNT = HAVING_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingVariationsImpl <em>Having Variations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingVariationsImpl
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getHavingVariations()
	 * @generated
	 */
	int HAVING_VARIATIONS = 31;

	/**
	 * The feature id for the '<em><b>Lower Bounds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_VARIATIONS__LOWER_BOUNDS = HAVING_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Upper Bounds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_VARIATIONS__UPPER_BOUNDS = HAVING_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Having Variations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_VARIATIONS_FEATURE_COUNT = HAVING_TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Having Variations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVING_VARIATIONS_OPERATION_COUNT = HAVING_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum <em>Specificity Type Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getSpecificityTypeEnum()
	 * @generated
	 */
	int SPECIFICITY_TYPE_ENUM = 32;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum <em>Property Type Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPropertyTypeEnum()
	 * @generated
	 */
	int PROPERTY_TYPE_ENUM = 33;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum <em>Relationship Type Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipTypeEnum()
	 * @generated
	 */
	int RELATIONSHIP_TYPE_ENUM = 34;

	/**
	 * The meta object id for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.TypeEnum <em>Type Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.TypeEnum
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getTypeEnum()
	 * @generated
	 */
	int TYPE_ENUM = 35;


	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.SkiQLModel <em>Ski QL Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ski QL Model</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiQLModel
	 * @generated
	 */
	EClass getSkiQLModel();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.SkiQLModel#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Query</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiQLModel#getQuery()
	 * @see #getSkiQLModel()
	 * @generated
	 */
	EReference getSkiQLModel_Query();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Query <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Query
	 * @generated
	 */
	EClass getQuery();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery <em>Relationship Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relationship Query</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery
	 * @generated
	 */
	EClass getRelationshipQuery();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery#getFrom()
	 * @see #getRelationshipQuery()
	 * @generated
	 */
	EReference getRelationshipQuery_From();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>To</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery#getTo()
	 * @see #getRelationshipQuery()
	 * @generated
	 */
	EReference getRelationshipQuery_To();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery <em>Entity Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Query</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery
	 * @generated
	 */
	EClass getEntityQuery();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getEntitySpec <em>Entity Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Entity Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getEntitySpec()
	 * @see #getEntityQuery()
	 * @generated
	 */
	EReference getEntityQuery_EntitySpec();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operation</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getOperation()
	 * @see #getEntityQuery()
	 * @generated
	 */
	EReference getEntityQuery_Operation();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getHaving <em>Having</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Having</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery#getHaving()
	 * @see #getEntityQuery()
	 * @generated
	 */
	EReference getEntityQuery_Having();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec <em>Entity Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec
	 * @generated
	 */
	EClass getEntitySpec();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec#getVariationFilter <em>Variation Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variation Filter</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec#getVariationFilter()
	 * @see #getEntitySpec()
	 * @generated
	 */
	EReference getEntitySpec_VariationFilter();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec#getName()
	 * @see #getEntitySpec()
	 * @generated
	 */
	EAttribute getEntitySpec_Name();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityOperation <em>Entity Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Operation</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityOperation
	 * @generated
	 */
	EClass getEntityOperation();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.VersionHistory <em>Version History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Version History</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.VersionHistory
	 * @generated
	 */
	EClass getVersionHistory();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Before <em>Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Before</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Before
	 * @generated
	 */
	EClass getBefore();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Before#getDateTime <em>Date Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Date Time</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Before#getDateTime()
	 * @see #getBefore()
	 * @generated
	 */
	EReference getBefore_DateTime();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.After <em>After</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>After</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.After
	 * @generated
	 */
	EClass getAfter();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.After#getDateTime <em>Date Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Date Time</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.After#getDateTime()
	 * @see #getAfter()
	 * @generated
	 */
	EReference getAfter_DateTime();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Between <em>Between</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Between</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Between
	 * @generated
	 */
	EClass getBetween();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Between#getAfterDateTime <em>After Date Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>After Date Time</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Between#getAfterDateTime()
	 * @see #getBetween()
	 * @generated
	 */
	EReference getBetween_AfterDateTime();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Between#getBeforeDateTime <em>Before Date Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Before Date Time</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Between#getBeforeDateTime()
	 * @see #getBetween()
	 * @generated
	 */
	EReference getBetween_BeforeDateTime();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.DateTime <em>Date Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Time</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.DateTime
	 * @generated
	 */
	EClass getDateTime();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getDay <em>Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Day</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getDay()
	 * @see #getDateTime()
	 * @generated
	 */
	EAttribute getDateTime_Day();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getMonth <em>Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Month</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getMonth()
	 * @see #getDateTime()
	 * @generated
	 */
	EAttribute getDateTime_Month();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getYear <em>Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Year</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getYear()
	 * @see #getDateTime()
	 * @generated
	 */
	EAttribute getDateTime_Year();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getHour <em>Hour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hour</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getHour()
	 * @see #getDateTime()
	 * @generated
	 */
	EAttribute getDateTime_Hour();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getMinutes <em>Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minutes</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getMinutes()
	 * @see #getDateTime()
	 * @generated
	 */
	EAttribute getDateTime_Minutes();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getSeconds <em>Seconds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seconds</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.DateTime#getSeconds()
	 * @see #getDateTime()
	 * @generated
	 */
	EAttribute getDateTime_Seconds();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.All <em>All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.All
	 * @generated
	 */
	EClass getAll();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.CountOperation <em>Count Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Count Operation</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.CountOperation
	 * @generated
	 */
	EClass getCountOperation();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter <em>Variation Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variation Filter</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter
	 * @generated
	 */
	EClass getVariationFilter();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter#getPropertySpecs <em>Property Specs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property Specs</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter#getPropertySpecs()
	 * @see #getVariationFilter()
	 * @generated
	 */
	EReference getVariationFilter_PropertySpecs();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter#isOnly <em>Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Only</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter#isOnly()
	 * @see #getVariationFilter()
	 * @generated
	 */
	EAttribute getVariationFilter_Only();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec <em>Property Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec
	 * @generated
	 */
	EClass getPropertySpec();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec#getName()
	 * @see #getPropertySpec()
	 * @generated
	 */
	EAttribute getPropertySpec_Name();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec#getType()
	 * @see #getPropertySpec()
	 * @generated
	 */
	EReference getPropertySpec_Type();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec <em>Property Selector Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Selector Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec
	 * @generated
	 */
	EClass getPropertySelectorSpec();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec#getType()
	 * @see #getPropertySelectorSpec()
	 * @generated
	 */
	EAttribute getPropertySelectorSpec_Type();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec#getSpecifity <em>Specifity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Specifity</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec#getSpecifity()
	 * @see #getPropertySelectorSpec()
	 * @generated
	 */
	EAttribute getPropertySelectorSpec_Specifity();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec <em>Relationship Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relationship Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec
	 * @generated
	 */
	EClass getRelationshipSpec();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#getTargetExpression <em>Target Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target Expression</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#getTargetExpression()
	 * @see #getRelationshipSpec()
	 * @generated
	 */
	EReference getRelationshipSpec_TargetExpression();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#isIndirect <em>Indirect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Indirect</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#isIndirect()
	 * @see #getRelationshipSpec()
	 * @generated
	 */
	EAttribute getRelationshipSpec_Indirect();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#getRelationSpec <em>Relation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Relation Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec#getRelationSpec()
	 * @see #getRelationshipSpec()
	 * @generated
	 */
	EReference getRelationshipSpec_RelationSpec();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.AggregationSpec <em>Aggregation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aggregation Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.AggregationSpec
	 * @generated
	 */
	EClass getAggregationSpec();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.ReferenceSpec <em>Reference Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.ReferenceSpec
	 * @generated
	 */
	EClass getReferenceSpec();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.ReferenceSpec#getVariationFilter <em>Variation Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variation Filter</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.ReferenceSpec#getVariationFilter()
	 * @see #getReferenceSpec()
	 * @generated
	 */
	EReference getReferenceSpec_VariationFilter();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.TargetExpression <em>Target Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Target Expression</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.TargetExpression
	 * @generated
	 */
	EClass getTargetExpression();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression <em>Relationship Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relationship Expression</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression
	 * @generated
	 */
	EClass getRelationshipExpression();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression#getRelationshipQuery <em>Relationship Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Relationship Query</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression#getRelationshipQuery()
	 * @see #getRelationshipExpression()
	 * @generated
	 */
	EReference getRelationshipExpression_RelationshipQuery();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression <em>Entity Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Expression</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression
	 * @generated
	 */
	EClass getEntityExpression();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression#getEntitySpec <em>Entity Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Entity Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression#getEntitySpec()
	 * @see #getEntityExpression()
	 * @generated
	 */
	EReference getEntityExpression_EntitySpec();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Type</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PrimitiveType
	 * @generated
	 */
	EClass getPrimitiveType();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PrimitiveType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PrimitiveType#getType()
	 * @see #getPrimitiveType()
	 * @generated
	 */
	EAttribute getPrimitiveType_Type();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PrimitiveType#isArray <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Array</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PrimitiveType#isArray()
	 * @see #getPrimitiveType()
	 * @generated
	 */
	EAttribute getPrimitiveType_Array();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType <em>Relationship Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relationship Type</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType
	 * @generated
	 */
	EClass getRelationshipType();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType#getTargetEntityName <em>Target Entity Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Entity Name</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType#getTargetEntityName()
	 * @see #getRelationshipType()
	 * @generated
	 */
	EAttribute getRelationshipType_TargetEntityName();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType#getRelationType <em>Relation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Relation Type</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType#getRelationType()
	 * @see #getRelationshipType()
	 * @generated
	 */
	EAttribute getRelationshipType_RelationType();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationSpec <em>Relation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation Spec</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationSpec
	 * @generated
	 */
	EClass getRelationSpec();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationSpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationSpec#getName()
	 * @see #getRelationSpec()
	 * @generated
	 */
	EAttribute getRelationSpec_Name();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertyFilter <em>Property Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Filter</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertyFilter
	 * @generated
	 */
	EClass getPropertyFilter();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertyFilter#getPropertySelectorSpecs <em>Property Selector Specs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property Selector Specs</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertyFilter#getPropertySelectorSpecs()
	 * @see #getPropertyFilter()
	 * @generated
	 */
	EReference getPropertyFilter_PropertySelectorSpecs();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Having <em>Having</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Having</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Having
	 * @generated
	 */
	EClass getHaving();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Having#isNegative <em>Negative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Negative</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Having#isNegative()
	 * @see #getHaving()
	 * @generated
	 */
	EAttribute getHaving_Negative();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.unosql.xtext.skiql.metamodel.skiql.Having#getHavingType <em>Having Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Having Type</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.Having#getHavingType()
	 * @see #getHaving()
	 * @generated
	 */
	EReference getHaving_HavingType();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingType <em>Having Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Having Type</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.HavingType
	 * @generated
	 */
	EClass getHavingType();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingKeys <em>Having Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Having Keys</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.HavingKeys
	 * @generated
	 */
	EClass getHavingKeys();

	/**
	 * Returns the meta object for class '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations <em>Having Variations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Having Variations</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations
	 * @generated
	 */
	EClass getHavingVariations();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations#getLowerBounds <em>Lower Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bounds</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations#getLowerBounds()
	 * @see #getHavingVariations()
	 * @generated
	 */
	EAttribute getHavingVariations_LowerBounds();

	/**
	 * Returns the meta object for the attribute '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations#getUpperBounds <em>Upper Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bounds</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations#getUpperBounds()
	 * @see #getHavingVariations()
	 * @generated
	 */
	EAttribute getHavingVariations_UpperBounds();

	/**
	 * Returns the meta object for enum '{@link es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum <em>Specificity Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Specificity Type Enum</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum
	 * @generated
	 */
	EEnum getSpecificityTypeEnum();

	/**
	 * Returns the meta object for enum '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum <em>Property Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Property Type Enum</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum
	 * @generated
	 */
	EEnum getPropertyTypeEnum();

	/**
	 * Returns the meta object for enum '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum <em>Relationship Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Relationship Type Enum</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum
	 * @generated
	 */
	EEnum getRelationshipTypeEnum();

	/**
	 * Returns the meta object for enum '{@link es.um.unosql.xtext.skiql.metamodel.skiql.TypeEnum <em>Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Enum</em>'.
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.TypeEnum
	 * @generated
	 */
	EEnum getTypeEnum();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SkiqlFactory getSkiqlFactory();

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
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiQLModelImpl <em>Ski QL Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiQLModelImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getSkiQLModel()
		 * @generated
		 */
		EClass SKI_QL_MODEL = eINSTANCE.getSkiQLModel();

		/**
		 * The meta object literal for the '<em><b>Query</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SKI_QL_MODEL__QUERY = eINSTANCE.getSkiQLModel_Query();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.QueryImpl <em>Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.QueryImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getQuery()
		 * @generated
		 */
		EClass QUERY = eINSTANCE.getQuery();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipQueryImpl <em>Relationship Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipQueryImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipQuery()
		 * @generated
		 */
		EClass RELATIONSHIP_QUERY = eINSTANCE.getRelationshipQuery();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONSHIP_QUERY__FROM = eINSTANCE.getRelationshipQuery_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONSHIP_QUERY__TO = eINSTANCE.getRelationshipQuery_To();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityQueryImpl <em>Entity Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityQueryImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getEntityQuery()
		 * @generated
		 */
		EClass ENTITY_QUERY = eINSTANCE.getEntityQuery();

		/**
		 * The meta object literal for the '<em><b>Entity Spec</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_QUERY__ENTITY_SPEC = eINSTANCE.getEntityQuery_EntitySpec();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_QUERY__OPERATION = eINSTANCE.getEntityQuery_Operation();

		/**
		 * The meta object literal for the '<em><b>Having</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_QUERY__HAVING = eINSTANCE.getEntityQuery_Having();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntitySpecImpl <em>Entity Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntitySpecImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getEntitySpec()
		 * @generated
		 */
		EClass ENTITY_SPEC = eINSTANCE.getEntitySpec();

		/**
		 * The meta object literal for the '<em><b>Variation Filter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_SPEC__VARIATION_FILTER = eINSTANCE.getEntitySpec_VariationFilter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY_SPEC__NAME = eINSTANCE.getEntitySpec_Name();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityOperationImpl <em>Entity Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityOperationImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getEntityOperation()
		 * @generated
		 */
		EClass ENTITY_OPERATION = eINSTANCE.getEntityOperation();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.TypeImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.VersionHistoryImpl <em>Version History</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.VersionHistoryImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getVersionHistory()
		 * @generated
		 */
		EClass VERSION_HISTORY = eINSTANCE.getVersionHistory();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.BeforeImpl <em>Before</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.BeforeImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getBefore()
		 * @generated
		 */
		EClass BEFORE = eINSTANCE.getBefore();

		/**
		 * The meta object literal for the '<em><b>Date Time</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEFORE__DATE_TIME = eINSTANCE.getBefore_DateTime();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.AfterImpl <em>After</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.AfterImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getAfter()
		 * @generated
		 */
		EClass AFTER = eINSTANCE.getAfter();

		/**
		 * The meta object literal for the '<em><b>Date Time</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AFTER__DATE_TIME = eINSTANCE.getAfter_DateTime();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.BetweenImpl <em>Between</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.BetweenImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getBetween()
		 * @generated
		 */
		EClass BETWEEN = eINSTANCE.getBetween();

		/**
		 * The meta object literal for the '<em><b>After Date Time</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BETWEEN__AFTER_DATE_TIME = eINSTANCE.getBetween_AfterDateTime();

		/**
		 * The meta object literal for the '<em><b>Before Date Time</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BETWEEN__BEFORE_DATE_TIME = eINSTANCE.getBetween_BeforeDateTime();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.DateTimeImpl <em>Date Time</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.DateTimeImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getDateTime()
		 * @generated
		 */
		EClass DATE_TIME = eINSTANCE.getDateTime();

		/**
		 * The meta object literal for the '<em><b>Day</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_TIME__DAY = eINSTANCE.getDateTime_Day();

		/**
		 * The meta object literal for the '<em><b>Month</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_TIME__MONTH = eINSTANCE.getDateTime_Month();

		/**
		 * The meta object literal for the '<em><b>Year</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_TIME__YEAR = eINSTANCE.getDateTime_Year();

		/**
		 * The meta object literal for the '<em><b>Hour</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_TIME__HOUR = eINSTANCE.getDateTime_Hour();

		/**
		 * The meta object literal for the '<em><b>Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_TIME__MINUTES = eINSTANCE.getDateTime_Minutes();

		/**
		 * The meta object literal for the '<em><b>Seconds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_TIME__SECONDS = eINSTANCE.getDateTime_Seconds();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.AllImpl <em>All</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.AllImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getAll()
		 * @generated
		 */
		EClass ALL = eINSTANCE.getAll();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.CountOperationImpl <em>Count Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.CountOperationImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getCountOperation()
		 * @generated
		 */
		EClass COUNT_OPERATION = eINSTANCE.getCountOperation();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.VariationFilterImpl <em>Variation Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.VariationFilterImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getVariationFilter()
		 * @generated
		 */
		EClass VARIATION_FILTER = eINSTANCE.getVariationFilter();

		/**
		 * The meta object literal for the '<em><b>Property Specs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIATION_FILTER__PROPERTY_SPECS = eINSTANCE.getVariationFilter_PropertySpecs();

		/**
		 * The meta object literal for the '<em><b>Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIATION_FILTER__ONLY = eINSTANCE.getVariationFilter_Only();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySpecImpl <em>Property Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySpecImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPropertySpec()
		 * @generated
		 */
		EClass PROPERTY_SPEC = eINSTANCE.getPropertySpec();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_SPEC__NAME = eINSTANCE.getPropertySpec_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_SPEC__TYPE = eINSTANCE.getPropertySpec_Type();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySelectorSpecImpl <em>Property Selector Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySelectorSpecImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPropertySelectorSpec()
		 * @generated
		 */
		EClass PROPERTY_SELECTOR_SPEC = eINSTANCE.getPropertySelectorSpec();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_SELECTOR_SPEC__TYPE = eINSTANCE.getPropertySelectorSpec_Type();

		/**
		 * The meta object literal for the '<em><b>Specifity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_SELECTOR_SPEC__SPECIFITY = eINSTANCE.getPropertySelectorSpec_Specifity();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.OperationImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipSpecImpl <em>Relationship Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipSpecImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipSpec()
		 * @generated
		 */
		EClass RELATIONSHIP_SPEC = eINSTANCE.getRelationshipSpec();

		/**
		 * The meta object literal for the '<em><b>Target Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONSHIP_SPEC__TARGET_EXPRESSION = eINSTANCE.getRelationshipSpec_TargetExpression();

		/**
		 * The meta object literal for the '<em><b>Indirect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP_SPEC__INDIRECT = eINSTANCE.getRelationshipSpec_Indirect();

		/**
		 * The meta object literal for the '<em><b>Relation Spec</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONSHIP_SPEC__RELATION_SPEC = eINSTANCE.getRelationshipSpec_RelationSpec();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.AggregationSpecImpl <em>Aggregation Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.AggregationSpecImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getAggregationSpec()
		 * @generated
		 */
		EClass AGGREGATION_SPEC = eINSTANCE.getAggregationSpec();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.ReferenceSpecImpl <em>Reference Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.ReferenceSpecImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getReferenceSpec()
		 * @generated
		 */
		EClass REFERENCE_SPEC = eINSTANCE.getReferenceSpec();

		/**
		 * The meta object literal for the '<em><b>Variation Filter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_SPEC__VARIATION_FILTER = eINSTANCE.getReferenceSpec_VariationFilter();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.TargetExpressionImpl <em>Target Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.TargetExpressionImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getTargetExpression()
		 * @generated
		 */
		EClass TARGET_EXPRESSION = eINSTANCE.getTargetExpression();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipExpressionImpl <em>Relationship Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipExpressionImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipExpression()
		 * @generated
		 */
		EClass RELATIONSHIP_EXPRESSION = eINSTANCE.getRelationshipExpression();

		/**
		 * The meta object literal for the '<em><b>Relationship Query</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY = eINSTANCE.getRelationshipExpression_RelationshipQuery();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityExpressionImpl <em>Entity Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityExpressionImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getEntityExpression()
		 * @generated
		 */
		EClass ENTITY_EXPRESSION = eINSTANCE.getEntityExpression();

		/**
		 * The meta object literal for the '<em><b>Entity Spec</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_EXPRESSION__ENTITY_SPEC = eINSTANCE.getEntityExpression_EntitySpec();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.PrimitiveTypeImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPrimitiveType()
		 * @generated
		 */
		EClass PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_TYPE__TYPE = eINSTANCE.getPrimitiveType_Type();

		/**
		 * The meta object literal for the '<em><b>Array</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_TYPE__ARRAY = eINSTANCE.getPrimitiveType_Array();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipTypeImpl <em>Relationship Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipTypeImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipType()
		 * @generated
		 */
		EClass RELATIONSHIP_TYPE = eINSTANCE.getRelationshipType();

		/**
		 * The meta object literal for the '<em><b>Target Entity Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP_TYPE__TARGET_ENTITY_NAME = eINSTANCE.getRelationshipType_TargetEntityName();

		/**
		 * The meta object literal for the '<em><b>Relation Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP_TYPE__RELATION_TYPE = eINSTANCE.getRelationshipType_RelationType();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationSpecImpl <em>Relation Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationSpecImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationSpec()
		 * @generated
		 */
		EClass RELATION_SPEC = eINSTANCE.getRelationSpec();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION_SPEC__NAME = eINSTANCE.getRelationSpec_Name();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertyFilterImpl <em>Property Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertyFilterImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPropertyFilter()
		 * @generated
		 */
		EClass PROPERTY_FILTER = eINSTANCE.getPropertyFilter();

		/**
		 * The meta object literal for the '<em><b>Property Selector Specs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_FILTER__PROPERTY_SELECTOR_SPECS = eINSTANCE.getPropertyFilter_PropertySelectorSpecs();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingImpl <em>Having</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getHaving()
		 * @generated
		 */
		EClass HAVING = eINSTANCE.getHaving();

		/**
		 * The meta object literal for the '<em><b>Negative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HAVING__NEGATIVE = eINSTANCE.getHaving_Negative();

		/**
		 * The meta object literal for the '<em><b>Having Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HAVING__HAVING_TYPE = eINSTANCE.getHaving_HavingType();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingTypeImpl <em>Having Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingTypeImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getHavingType()
		 * @generated
		 */
		EClass HAVING_TYPE = eINSTANCE.getHavingType();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingKeysImpl <em>Having Keys</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingKeysImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getHavingKeys()
		 * @generated
		 */
		EClass HAVING_KEYS = eINSTANCE.getHavingKeys();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingVariationsImpl <em>Having Variations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingVariationsImpl
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getHavingVariations()
		 * @generated
		 */
		EClass HAVING_VARIATIONS = eINSTANCE.getHavingVariations();

		/**
		 * The meta object literal for the '<em><b>Lower Bounds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HAVING_VARIATIONS__LOWER_BOUNDS = eINSTANCE.getHavingVariations_LowerBounds();

		/**
		 * The meta object literal for the '<em><b>Upper Bounds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HAVING_VARIATIONS__UPPER_BOUNDS = eINSTANCE.getHavingVariations_UpperBounds();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum <em>Specificity Type Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getSpecificityTypeEnum()
		 * @generated
		 */
		EEnum SPECIFICITY_TYPE_ENUM = eINSTANCE.getSpecificityTypeEnum();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum <em>Property Type Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getPropertyTypeEnum()
		 * @generated
		 */
		EEnum PROPERTY_TYPE_ENUM = eINSTANCE.getPropertyTypeEnum();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum <em>Relationship Type Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getRelationshipTypeEnum()
		 * @generated
		 */
		EEnum RELATIONSHIP_TYPE_ENUM = eINSTANCE.getRelationshipTypeEnum();

		/**
		 * The meta object literal for the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.TypeEnum <em>Type Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.TypeEnum
		 * @see es.um.unosql.xtext.skiql.metamodel.skiql.impl.SkiqlPackageImpl#getTypeEnum()
		 * @generated
		 */
		EEnum TYPE_ENUM = eINSTANCE.getTypeEnum();

	}

} //SkiqlPackage
