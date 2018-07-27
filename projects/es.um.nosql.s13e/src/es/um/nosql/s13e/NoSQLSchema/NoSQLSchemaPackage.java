/**
 */
package es.um.nosql.s13e.NoSQLSchema;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory
 * @model kind="package"
 * @generated
 */
public interface NoSQLSchemaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "NoSQLSchema";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modelum.es/NoSQLSchema";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "NoSQLSchema";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NoSQLSchemaPackage eINSTANCE = es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl.init();

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaImpl <em>No SQL Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getNoSQLSchema()
	 * @generated
	 */
	int NO_SQL_SCHEMA = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_SQL_SCHEMA__NAME = 0;

	/**
	 * The feature id for the '<em><b>Entities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_SQL_SCHEMA__ENTITIES = 1;

	/**
	 * The feature id for the '<em><b>Ref Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_SQL_SCHEMA__REF_CLASSES = 2;

	/**
	 * The number of structural features of the '<em>No SQL Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_SQL_SCHEMA_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>No SQL Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_SQL_SCHEMA_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.ClassifierImpl <em>Classifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.ClassifierImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getClassifier()
	 * @generated
	 */
	int CLASSIFIER = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Parents</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__PARENTS = 1;

	/**
	 * The feature id for the '<em><b>Variations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER__VARIATIONS = 2;

	/**
	 * The number of structural features of the '<em>Classifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Classifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityClassImpl <em>Entity Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.EntityClassImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getEntityClass()
	 * @generated
	 */
	int ENTITY_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_CLASS__NAME = CLASSIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Parents</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_CLASS__PARENTS = CLASSIFIER__PARENTS;

	/**
	 * The feature id for the '<em><b>Variations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_CLASS__VARIATIONS = CLASSIFIER__VARIATIONS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_CLASS__ROOT = CLASSIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Entity Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_CLASS_FEATURE_COUNT = CLASSIFIER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Entity Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_CLASS_OPERATION_COUNT = CLASSIFIER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl <em>Structural Variation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getStructuralVariation()
	 * @generated
	 */
	int STRUCTURAL_VARIATION = 2;

	/**
	 * The feature id for the '<em><b>Variation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_VARIATION__VARIATION_ID = 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_VARIATION__PROPERTIES = 1;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_VARIATION__COUNT = 2;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_VARIATION__TIMESTAMP = 3;

	/**
	 * The number of structural features of the '<em>Structural Variation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_VARIATION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Structural Variation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_VARIATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.PropertyImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.AttributeImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TYPE = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.TypeImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 5;

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
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PListImpl <em>PList</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.PListImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPList()
	 * @generated
	 */
	int PLIST = 6;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLIST__ELEMENT_TYPE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>PList</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLIST_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>PList</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLIST_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.AssociationImpl <em>Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.AssociationImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getAssociation()
	 * @generated
	 */
	int ASSOCIATION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__NAME = PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__LOWER_BOUND = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__UPPER_BOUND = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.ReferenceImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__NAME = ASSOCIATION__NAME;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__LOWER_BOUND = ASSOCIATION__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__UPPER_BOUND = ASSOCIATION__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Opposite</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__OPPOSITE = ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Refs To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__REFS_TO = ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Original Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__ORIGINAL_TYPE = ASSOCIATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__FEATURES = ASSOCIATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = ASSOCIATION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_OPERATION_COUNT = ASSOCIATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.AggregateImpl <em>Aggregate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.AggregateImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getAggregate()
	 * @generated
	 */
	int AGGREGATE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGGREGATE__NAME = ASSOCIATION__NAME;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGGREGATE__LOWER_BOUND = ASSOCIATION__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGGREGATE__UPPER_BOUND = ASSOCIATION__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Aggregates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGGREGATE__AGGREGATES = ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Aggregate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGGREGATE_FEATURE_COUNT = ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Aggregate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGGREGATE_OPERATION_COUNT = ASSOCIATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.PrimitiveTypeImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPrimitiveType()
	 * @generated
	 */
	int PRIMITIVE_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__NAME = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.NullImpl <em>Null</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NullImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getNull()
	 * @generated
	 */
	int NULL = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL__NAME = PROPERTY__NAME;

	/**
	 * The number of structural features of the '<em>Null</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Null</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.ReferenceClassImpl <em>Reference Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.ReferenceClassImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getReferenceClass()
	 * @generated
	 */
	int REFERENCE_CLASS = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLASS__NAME = CLASSIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Parents</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLASS__PARENTS = CLASSIFIER__PARENTS;

	/**
	 * The feature id for the '<em><b>Variations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLASS__VARIATIONS = CLASSIFIER__VARIATIONS;

	/**
	 * The number of structural features of the '<em>Reference Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLASS_FEATURE_COUNT = CLASSIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Reference Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLASS_OPERATION_COUNT = CLASSIFIER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PMapImpl <em>PMap</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.PMapImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPMap()
	 * @generated
	 */
	int PMAP = 14;

	/**
	 * The feature id for the '<em><b>Key Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PMAP__KEY_TYPE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PMAP__VALUE_TYPE = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>PMap</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PMAP_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>PMap</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PMAP_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PSetImpl <em>PSet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.PSetImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPSet()
	 * @generated
	 */
	int PSET = 15;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSET__ELEMENT_TYPE = 0;

	/**
	 * The number of structural features of the '<em>PSet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSET_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>PSet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PTupleImpl <em>PTuple</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.PTupleImpl
	 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPTuple()
	 * @generated
	 */
	int PTUPLE = 16;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTUPLE__ELEMENTS = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>PTuple</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTUPLE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>PTuple</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTUPLE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.NoSQLSchema <em>No SQL Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No SQL Schema</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchema
	 * @generated
	 */
	EClass getNoSQLSchema();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.NoSQLSchema#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchema#getName()
	 * @see #getNoSQLSchema()
	 * @generated
	 */
	EAttribute getNoSQLSchema_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.NoSQLSchema.NoSQLSchema#getEntities <em>Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entities</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchema#getEntities()
	 * @see #getNoSQLSchema()
	 * @generated
	 */
	EReference getNoSQLSchema_Entities();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.NoSQLSchema.NoSQLSchema#getRefClasses <em>Ref Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ref Classes</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchema#getRefClasses()
	 * @see #getNoSQLSchema()
	 * @generated
	 */
	EReference getNoSQLSchema_RefClasses();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.EntityClass <em>Entity Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Class</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.EntityClass
	 * @generated
	 */
	EClass getEntityClass();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.EntityClass#isRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Root</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.EntityClass#isRoot()
	 * @see #getEntityClass()
	 * @generated
	 */
	EAttribute getEntityClass_Root();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation <em>Structural Variation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Structural Variation</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.StructuralVariation
	 * @generated
	 */
	EClass getStructuralVariation();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getVariationId <em>Variation Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variation Id</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getVariationId()
	 * @see #getStructuralVariation()
	 * @generated
	 */
	EAttribute getStructuralVariation_VariationId();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getProperties()
	 * @see #getStructuralVariation()
	 * @generated
	 */
	EReference getStructuralVariation_Properties();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getCount()
	 * @see #getStructuralVariation()
	 * @generated
	 */
	EAttribute getStructuralVariation_Count();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.StructuralVariation#getTimestamp()
	 * @see #getStructuralVariation()
	 * @generated
	 */
	EAttribute getStructuralVariation_Timestamp();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.s13e.NoSQLSchema.Attribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Attribute#getType()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_Type();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.PList <em>PList</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PList</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PList
	 * @generated
	 */
	EClass getPList();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.s13e.NoSQLSchema.PList#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Element Type</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PList#getElementType()
	 * @see #getPList()
	 * @generated
	 */
	EReference getPList_ElementType();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Association <em>Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Association
	 * @generated
	 */
	EClass getAssociation();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.Association#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Association#getLowerBound()
	 * @see #getAssociation()
	 * @generated
	 */
	EAttribute getAssociation_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.Association#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Association#getUpperBound()
	 * @see #getAssociation()
	 * @generated
	 */
	EAttribute getAssociation_UpperBound();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getOpposite <em>Opposite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Opposite</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Reference#getOpposite()
	 * @see #getReference()
	 * @generated
	 */
	EReference getReference_Opposite();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getRefsTo <em>Refs To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Refs To</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Reference#getRefsTo()
	 * @see #getReference()
	 * @generated
	 */
	EReference getReference_RefsTo();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getOriginalType <em>Original Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Original Type</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Reference#getOriginalType()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_OriginalType();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Features</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Reference#getFeatures()
	 * @see #getReference()
	 * @generated
	 */
	EReference getReference_Features();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Aggregate <em>Aggregate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aggregate</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Aggregate
	 * @generated
	 */
	EClass getAggregate();

	/**
	 * Returns the meta object for the reference list '{@link es.um.nosql.s13e.NoSQLSchema.Aggregate#getAggregates <em>Aggregates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Aggregates</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Aggregate#getAggregates()
	 * @see #getAggregate()
	 * @generated
	 */
	EReference getAggregate_Aggregates();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Type</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PrimitiveType
	 * @generated
	 */
	EClass getPrimitiveType();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.PrimitiveType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PrimitiveType#getName()
	 * @see #getPrimitiveType()
	 * @generated
	 */
	EAttribute getPrimitiveType_Name();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Null <em>Null</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Null</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Null
	 * @generated
	 */
	EClass getNull();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.ReferenceClass <em>Reference Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Class</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.ReferenceClass
	 * @generated
	 */
	EClass getReferenceClass();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Classifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Classifier</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Classifier
	 * @generated
	 */
	EClass getClassifier();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.Classifier#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Classifier#getName()
	 * @see #getClassifier()
	 * @generated
	 */
	EAttribute getClassifier_Name();

	/**
	 * Returns the meta object for the reference list '{@link es.um.nosql.s13e.NoSQLSchema.Classifier#getParents <em>Parents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parents</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Classifier#getParents()
	 * @see #getClassifier()
	 * @generated
	 */
	EReference getClassifier_Parents();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.NoSQLSchema.Classifier#getVariations <em>Variations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variations</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.Classifier#getVariations()
	 * @see #getClassifier()
	 * @generated
	 */
	EReference getClassifier_Variations();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.PMap <em>PMap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PMap</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PMap
	 * @generated
	 */
	EClass getPMap();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.s13e.NoSQLSchema.PMap#getKeyType <em>Key Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key Type</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PMap#getKeyType()
	 * @see #getPMap()
	 * @generated
	 */
	EReference getPMap_KeyType();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.s13e.NoSQLSchema.PMap#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Type</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PMap#getValueType()
	 * @see #getPMap()
	 * @generated
	 */
	EReference getPMap_ValueType();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.PSet <em>PSet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PSet</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PSet
	 * @generated
	 */
	EClass getPSet();

	/**
	 * Returns the meta object for the containment reference '{@link es.um.nosql.s13e.NoSQLSchema.PSet#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Element Type</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PSet#getElementType()
	 * @see #getPSet()
	 * @generated
	 */
	EReference getPSet_ElementType();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.PTuple <em>PTuple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PTuple</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PTuple
	 * @generated
	 */
	EClass getPTuple();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.NoSQLSchema.PTuple#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see es.um.nosql.s13e.NoSQLSchema.PTuple#getElements()
	 * @see #getPTuple()
	 * @generated
	 */
	EReference getPTuple_Elements();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NoSQLSchemaFactory getNoSQLSchemaFactory();

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
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaImpl <em>No SQL Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getNoSQLSchema()
		 * @generated
		 */
		EClass NO_SQL_SCHEMA = eINSTANCE.getNoSQLSchema();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO_SQL_SCHEMA__NAME = eINSTANCE.getNoSQLSchema_Name();

		/**
		 * The meta object literal for the '<em><b>Entities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NO_SQL_SCHEMA__ENTITIES = eINSTANCE.getNoSQLSchema_Entities();

		/**
		 * The meta object literal for the '<em><b>Ref Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NO_SQL_SCHEMA__REF_CLASSES = eINSTANCE.getNoSQLSchema_RefClasses();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityClassImpl <em>Entity Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.EntityClassImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getEntityClass()
		 * @generated
		 */
		EClass ENTITY_CLASS = eINSTANCE.getEntityClass();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY_CLASS__ROOT = eINSTANCE.getEntityClass_Root();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl <em>Structural Variation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getStructuralVariation()
		 * @generated
		 */
		EClass STRUCTURAL_VARIATION = eINSTANCE.getStructuralVariation();

		/**
		 * The meta object literal for the '<em><b>Variation Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURAL_VARIATION__VARIATION_ID = eINSTANCE.getStructuralVariation_VariationId();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURAL_VARIATION__PROPERTIES = eINSTANCE.getStructuralVariation_Properties();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURAL_VARIATION__COUNT = eINSTANCE.getStructuralVariation_Count();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURAL_VARIATION__TIMESTAMP = eINSTANCE.getStructuralVariation_Timestamp();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.PropertyImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.AttributeImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE__TYPE = eINSTANCE.getAttribute_Type();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.TypeImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PListImpl <em>PList</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.PListImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPList()
		 * @generated
		 */
		EClass PLIST = eINSTANCE.getPList();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLIST__ELEMENT_TYPE = eINSTANCE.getPList_ElementType();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.AssociationImpl <em>Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.AssociationImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getAssociation()
		 * @generated
		 */
		EClass ASSOCIATION = eINSTANCE.getAssociation();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSOCIATION__LOWER_BOUND = eINSTANCE.getAssociation_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSOCIATION__UPPER_BOUND = eINSTANCE.getAssociation_UpperBound();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.ReferenceImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '<em><b>Opposite</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE__OPPOSITE = eINSTANCE.getReference_Opposite();

		/**
		 * The meta object literal for the '<em><b>Refs To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE__REFS_TO = eINSTANCE.getReference_RefsTo();

		/**
		 * The meta object literal for the '<em><b>Original Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__ORIGINAL_TYPE = eINSTANCE.getReference_OriginalType();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE__FEATURES = eINSTANCE.getReference_Features();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.AggregateImpl <em>Aggregate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.AggregateImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getAggregate()
		 * @generated
		 */
		EClass AGGREGATE = eINSTANCE.getAggregate();

		/**
		 * The meta object literal for the '<em><b>Aggregates</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGGREGATE__AGGREGATES = eINSTANCE.getAggregate_Aggregates();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.PrimitiveTypeImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPrimitiveType()
		 * @generated
		 */
		EClass PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_TYPE__NAME = eINSTANCE.getPrimitiveType_Name();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.NullImpl <em>Null</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NullImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getNull()
		 * @generated
		 */
		EClass NULL = eINSTANCE.getNull();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.ReferenceClassImpl <em>Reference Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.ReferenceClassImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getReferenceClass()
		 * @generated
		 */
		EClass REFERENCE_CLASS = eINSTANCE.getReferenceClass();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.ClassifierImpl <em>Classifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.ClassifierImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getClassifier()
		 * @generated
		 */
		EClass CLASSIFIER = eINSTANCE.getClassifier();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASSIFIER__NAME = eINSTANCE.getClassifier_Name();

		/**
		 * The meta object literal for the '<em><b>Parents</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER__PARENTS = eINSTANCE.getClassifier_Parents();

		/**
		 * The meta object literal for the '<em><b>Variations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER__VARIATIONS = eINSTANCE.getClassifier_Variations();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PMapImpl <em>PMap</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.PMapImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPMap()
		 * @generated
		 */
		EClass PMAP = eINSTANCE.getPMap();

		/**
		 * The meta object literal for the '<em><b>Key Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PMAP__KEY_TYPE = eINSTANCE.getPMap_KeyType();

		/**
		 * The meta object literal for the '<em><b>Value Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PMAP__VALUE_TYPE = eINSTANCE.getPMap_ValueType();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PSetImpl <em>PSet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.PSetImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPSet()
		 * @generated
		 */
		EClass PSET = eINSTANCE.getPSet();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PSET__ELEMENT_TYPE = eINSTANCE.getPSet_ElementType();

		/**
		 * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.PTupleImpl <em>PTuple</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.PTupleImpl
		 * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getPTuple()
		 * @generated
		 */
		EClass PTUPLE = eINSTANCE.getPTuple();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PTUPLE__ELEMENTS = eINSTANCE.getPTuple_Elements();

	}

} //NoSQLSchemaPackage
