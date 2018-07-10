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
public interface NoSQLSchemaPackage extends EPackage
{
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
   * The number of structural features of the '<em>No SQL Schema</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NO_SQL_SCHEMA_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>No SQL Schema</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NO_SQL_SCHEMA_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityImpl <em>Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.NoSQLSchema.impl.EntityImpl
   * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getEntity()
   * @generated
   */
  int ENTITY = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__NAME = 0;

  /**
   * The feature id for the '<em><b>Root</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__ROOT = 1;

  /**
   * The feature id for the '<em><b>Entity Variations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__ENTITY_VARIATIONS = 2;

  /**
   * The feature id for the '<em><b>Parents</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__PARENTS = 3;

  /**
   * The number of structural features of the '<em>Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityVariationImpl <em>Entity Variation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.NoSQLSchema.impl.EntityVariationImpl
   * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getEntityVariation()
   * @generated
   */
  int ENTITY_VARIATION = 2;

  /**
   * The feature id for the '<em><b>Variation Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VARIATION__VARIATION_ID = 0;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VARIATION__PROPERTIES = 1;

  /**
   * The feature id for the '<em><b>Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VARIATION__COUNT = 2;

  /**
   * The feature id for the '<em><b>Timestamp</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VARIATION__TIMESTAMP = 3;

  /**
   * The number of structural features of the '<em>Entity Variation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VARIATION_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>Entity Variation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VARIATION_OPERATION_COUNT = 0;

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
   * The meta object id for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.TupleImpl <em>Tuple</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.NoSQLSchema.impl.TupleImpl
   * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getTuple()
   * @generated
   */
  int TUPLE = 6;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE__ELEMENTS = TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Tuple</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Tuple</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

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
   * The feature id for the '<em><b>Ref To</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE__REF_TO = ASSOCIATION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Original Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE__ORIGINAL_TYPE = ASSOCIATION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE__PROPERTIES = ASSOCIATION_FEATURE_COUNT + 3;

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
   * The feature id for the '<em><b>Ref To</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE__REF_TO = ASSOCIATION_FEATURE_COUNT + 0;

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
   * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Entity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Entity
   * @generated
   */
  EClass getEntity();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.Entity#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Entity#getName()
   * @see #getEntity()
   * @generated
   */
  EAttribute getEntity_Name();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.Entity#isRoot <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Root</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Entity#isRoot()
   * @see #getEntity()
   * @generated
   */
  EAttribute getEntity_Root();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.NoSQLSchema.Entity#getEntityVariations <em>Entity Variations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Variations</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Entity#getEntityVariations()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_EntityVariations();

  /**
   * Returns the meta object for the reference list '{@link es.um.nosql.s13e.NoSQLSchema.Entity#getParents <em>Parents</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Parents</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Entity#getParents()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_Parents();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.EntityVariation <em>Entity Variation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Variation</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.EntityVariation
   * @generated
   */
  EClass getEntityVariation();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.EntityVariation#getVariationId <em>Variation Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Variation Id</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.EntityVariation#getVariationId()
   * @see #getEntityVariation()
   * @generated
   */
  EAttribute getEntityVariation_VariationId();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.NoSQLSchema.EntityVariation#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Properties</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.EntityVariation#getProperties()
   * @see #getEntityVariation()
   * @generated
   */
  EReference getEntityVariation_Properties();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.EntityVariation#getCount <em>Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Count</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.EntityVariation#getCount()
   * @see #getEntityVariation()
   * @generated
   */
  EAttribute getEntityVariation_Count();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.NoSQLSchema.EntityVariation#getTimestamp <em>Timestamp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Timestamp</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.EntityVariation#getTimestamp()
   * @see #getEntityVariation()
   * @generated
   */
  EAttribute getEntityVariation_Timestamp();

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
   * Returns the meta object for class '{@link es.um.nosql.s13e.NoSQLSchema.Tuple <em>Tuple</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuple</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Tuple
   * @generated
   */
  EClass getTuple();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.NoSQLSchema.Tuple#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Tuple#getElements()
   * @see #getTuple()
   * @generated
   */
  EReference getTuple_Elements();

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
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getRefTo <em>Ref To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ref To</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Reference#getRefTo()
   * @see #getReference()
   * @generated
   */
  EReference getReference_RefTo();

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
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.NoSQLSchema.Reference#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Properties</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Reference#getProperties()
   * @see #getReference()
   * @generated
   */
  EReference getReference_Properties();

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
   * Returns the meta object for the reference list '{@link es.um.nosql.s13e.NoSQLSchema.Aggregate#getRefTo <em>Ref To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Ref To</em>'.
   * @see es.um.nosql.s13e.NoSQLSchema.Aggregate#getRefTo()
   * @see #getAggregate()
   * @generated
   */
  EReference getAggregate_RefTo();

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
  interface Literals
  {
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
     * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityImpl <em>Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.NoSQLSchema.impl.EntityImpl
     * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getEntity()
     * @generated
     */
    EClass ENTITY = eINSTANCE.getEntity();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY__NAME = eINSTANCE.getEntity_Name();

    /**
     * The meta object literal for the '<em><b>Root</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY__ROOT = eINSTANCE.getEntity_Root();

    /**
     * The meta object literal for the '<em><b>Entity Variations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__ENTITY_VARIATIONS = eINSTANCE.getEntity_EntityVariations();

    /**
     * The meta object literal for the '<em><b>Parents</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__PARENTS = eINSTANCE.getEntity_Parents();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityVariationImpl <em>Entity Variation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.NoSQLSchema.impl.EntityVariationImpl
     * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getEntityVariation()
     * @generated
     */
    EClass ENTITY_VARIATION = eINSTANCE.getEntityVariation();

    /**
     * The meta object literal for the '<em><b>Variation Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_VARIATION__VARIATION_ID = eINSTANCE.getEntityVariation_VariationId();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_VARIATION__PROPERTIES = eINSTANCE.getEntityVariation_Properties();

    /**
     * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_VARIATION__COUNT = eINSTANCE.getEntityVariation_Count();

    /**
     * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_VARIATION__TIMESTAMP = eINSTANCE.getEntityVariation_Timestamp();

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
     * The meta object literal for the '{@link es.um.nosql.s13e.NoSQLSchema.impl.TupleImpl <em>Tuple</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.NoSQLSchema.impl.TupleImpl
     * @see es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaPackageImpl#getTuple()
     * @generated
     */
    EClass TUPLE = eINSTANCE.getTuple();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TUPLE__ELEMENTS = eINSTANCE.getTuple_Elements();

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
     * The meta object literal for the '<em><b>Ref To</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REFERENCE__REF_TO = eINSTANCE.getReference_RefTo();

    /**
     * The meta object literal for the '<em><b>Original Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REFERENCE__ORIGINAL_TYPE = eINSTANCE.getReference_OriginalType();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REFERENCE__PROPERTIES = eINSTANCE.getReference_Properties();

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
     * The meta object literal for the '<em><b>Ref To</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AGGREGATE__REF_TO = eINSTANCE.getAggregate_RefTo();

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

  }

} //NoSQLSchemaPackage
