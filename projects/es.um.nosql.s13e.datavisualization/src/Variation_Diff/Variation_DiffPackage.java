/**
 */
package Variation_Diff;

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
 * @see Variation_Diff.Variation_DiffFactory
 * @model kind="package"
 * @generated
 */
public interface Variation_DiffPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "Variation_Diff";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.modelum.es/variation_diff";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "Variation_Diff";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Variation_DiffPackage eINSTANCE = Variation_Diff.impl.Variation_DiffPackageImpl.init();

  /**
   * The meta object id for the '{@link Variation_Diff.impl.NoSQLDifferencesImpl <em>No SQL Differences</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.NoSQLDifferencesImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getNoSQLDifferences()
   * @generated
   */
  int NO_SQL_DIFFERENCES = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NO_SQL_DIFFERENCES__NAME = 0;

  /**
   * The feature id for the '<em><b>Has Type Differences</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NO_SQL_DIFFERENCES__HAS_TYPE_DIFFERENCES = 1;

  /**
   * The number of structural features of the '<em>No SQL Differences</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NO_SQL_DIFFERENCES_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>No SQL Differences</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NO_SQL_DIFFERENCES_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.TypeDifferenceImpl <em>Type Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.TypeDifferenceImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getTypeDifference()
   * @generated
   */
  int TYPE_DIFFERENCE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DIFFERENCE__NAME = 0;

  /**
   * The feature id for the '<em><b>Hints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DIFFERENCE__HINTS = 1;

  /**
   * The number of structural features of the '<em>Type Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DIFFERENCE_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Type Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DIFFERENCE_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.TypeHintImpl <em>Type Hint</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.TypeHintImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getTypeHint()
   * @generated
   */
  int TYPE_HINT = 2;

  /**
   * The feature id for the '<em><b>With Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_HINT__WITH_NAME = 0;

  /**
   * The feature id for the '<em><b>With Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_HINT__WITH_TYPE = 1;

  /**
   * The number of structural features of the '<em>Type Hint</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_HINT_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Type Hint</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_HINT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.HasFieldImpl <em>Has Field</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.HasFieldImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getHasField()
   * @generated
   */
  int HAS_FIELD = 3;

  /**
   * The feature id for the '<em><b>With Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_FIELD__WITH_NAME = TYPE_HINT__WITH_NAME;

  /**
   * The feature id for the '<em><b>With Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_FIELD__WITH_TYPE = TYPE_HINT__WITH_TYPE;

  /**
   * The number of structural features of the '<em>Has Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_FIELD_FEATURE_COUNT = TYPE_HINT_FEATURE_COUNT + 0;

  /**
   * The number of operations of the '<em>Has Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_FIELD_OPERATION_COUNT = TYPE_HINT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.HasNotFieldImpl <em>Has Not Field</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.HasNotFieldImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getHasNotField()
   * @generated
   */
  int HAS_NOT_FIELD = 4;

  /**
   * The feature id for the '<em><b>With Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_NOT_FIELD__WITH_NAME = TYPE_HINT__WITH_NAME;

  /**
   * The feature id for the '<em><b>With Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_NOT_FIELD__WITH_TYPE = TYPE_HINT__WITH_TYPE;

  /**
   * The number of structural features of the '<em>Has Not Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_NOT_FIELD_FEATURE_COUNT = TYPE_HINT_FEATURE_COUNT + 0;

  /**
   * The number of operations of the '<em>Has Not Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_NOT_FIELD_OPERATION_COUNT = TYPE_HINT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.FieldTypeImpl <em>Field Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.FieldTypeImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getFieldType()
   * @generated
   */
  int FIELD_TYPE = 5;

  /**
   * The number of structural features of the '<em>Field Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIELD_TYPE_FEATURE_COUNT = 0;

  /**
   * The number of operations of the '<em>Field Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIELD_TYPE_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.PrimitiveTypeImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getPrimitiveType()
   * @generated
   */
  int PRIMITIVE_TYPE = 6;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMITIVE_TYPE__TYPE = FIELD_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Primitive Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMITIVE_TYPE_FEATURE_COUNT = FIELD_TYPE_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Primitive Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMITIVE_TYPE_OPERATION_COUNT = FIELD_TYPE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.TupleTypeImpl <em>Tuple Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.TupleTypeImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getTupleType()
   * @generated
   */
  int TUPLE_TYPE = 7;

  /**
   * The number of structural features of the '<em>Tuple Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_TYPE_FEATURE_COUNT = FIELD_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of operations of the '<em>Tuple Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_TYPE_OPERATION_COUNT = FIELD_TYPE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.AssociationTypeImpl <em>Association Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.AssociationTypeImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getAssociationType()
   * @generated
   */
  int ASSOCIATION_TYPE = 8;

  /**
   * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION_TYPE__LOWER_BOUND = FIELD_TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION_TYPE__UPPER_BOUND = FIELD_TYPE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Association Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION_TYPE_FEATURE_COUNT = FIELD_TYPE_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>Association Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION_TYPE_OPERATION_COUNT = FIELD_TYPE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.AggregateTypeImpl <em>Aggregate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.AggregateTypeImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getAggregateType()
   * @generated
   */
  int AGGREGATE_TYPE = 9;

  /**
   * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_TYPE__LOWER_BOUND = ASSOCIATION_TYPE__LOWER_BOUND;

  /**
   * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_TYPE__UPPER_BOUND = ASSOCIATION_TYPE__UPPER_BOUND;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_TYPE__TYPE = ASSOCIATION_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Aggregate Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_TYPE_FEATURE_COUNT = ASSOCIATION_TYPE_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Aggregate Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATE_TYPE_OPERATION_COUNT = ASSOCIATION_TYPE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.ReferenceTypeImpl <em>Reference Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.ReferenceTypeImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getReferenceType()
   * @generated
   */
  int REFERENCE_TYPE = 10;

  /**
   * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TYPE__LOWER_BOUND = ASSOCIATION_TYPE__LOWER_BOUND;

  /**
   * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TYPE__UPPER_BOUND = ASSOCIATION_TYPE__UPPER_BOUND;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TYPE__TYPE = ASSOCIATION_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Reference Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TYPE_FEATURE_COUNT = ASSOCIATION_TYPE_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Reference Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TYPE_OPERATION_COUNT = ASSOCIATION_TYPE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.EntityTypeImpl <em>Entity Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.EntityTypeImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getEntityType()
   * @generated
   */
  int ENTITY_TYPE = 11;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_TYPE__TYPE = FIELD_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Entity Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_TYPE_FEATURE_COUNT = FIELD_TYPE_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Entity Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_TYPE_OPERATION_COUNT = FIELD_TYPE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.HomogeneousTupleTypeImpl <em>Homogeneous Tuple Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.HomogeneousTupleTypeImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getHomogeneousTupleType()
   * @generated
   */
  int HOMOGENEOUS_TUPLE_TYPE = 12;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HOMOGENEOUS_TUPLE_TYPE__TYPE = TUPLE_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Homogeneous Tuple Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HOMOGENEOUS_TUPLE_TYPE_FEATURE_COUNT = TUPLE_TYPE_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Homogeneous Tuple Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HOMOGENEOUS_TUPLE_TYPE_OPERATION_COUNT = TUPLE_TYPE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link Variation_Diff.impl.HeterogeneousTupleTypeImpl <em>Heterogeneous Tuple Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see Variation_Diff.impl.HeterogeneousTupleTypeImpl
   * @see Variation_Diff.impl.Variation_DiffPackageImpl#getHeterogeneousTupleType()
   * @generated
   */
  int HETEROGENEOUS_TUPLE_TYPE = 13;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HETEROGENEOUS_TUPLE_TYPE__TYPE = TUPLE_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Heterogeneous Tuple Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HETEROGENEOUS_TUPLE_TYPE_FEATURE_COUNT = TUPLE_TYPE_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Heterogeneous Tuple Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HETEROGENEOUS_TUPLE_TYPE_OPERATION_COUNT = TUPLE_TYPE_OPERATION_COUNT + 0;


  /**
   * Returns the meta object for class '{@link Variation_Diff.NoSQLDifferences <em>No SQL Differences</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>No SQL Differences</em>'.
   * @see Variation_Diff.NoSQLDifferences
   * @generated
   */
  EClass getNoSQLDifferences();

  /**
   * Returns the meta object for the attribute '{@link Variation_Diff.NoSQLDifferences#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see Variation_Diff.NoSQLDifferences#getName()
   * @see #getNoSQLDifferences()
   * @generated
   */
  EAttribute getNoSQLDifferences_Name();

  /**
   * Returns the meta object for the containment reference list '{@link Variation_Diff.NoSQLDifferences#getHasTypeDifferences <em>Has Type Differences</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Has Type Differences</em>'.
   * @see Variation_Diff.NoSQLDifferences#getHasTypeDifferences()
   * @see #getNoSQLDifferences()
   * @generated
   */
  EReference getNoSQLDifferences_HasTypeDifferences();

  /**
   * Returns the meta object for class '{@link Variation_Diff.TypeDifference <em>Type Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Difference</em>'.
   * @see Variation_Diff.TypeDifference
   * @generated
   */
  EClass getTypeDifference();

  /**
   * Returns the meta object for the attribute '{@link Variation_Diff.TypeDifference#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see Variation_Diff.TypeDifference#getName()
   * @see #getTypeDifference()
   * @generated
   */
  EAttribute getTypeDifference_Name();

  /**
   * Returns the meta object for the containment reference list '{@link Variation_Diff.TypeDifference#getHints <em>Hints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Hints</em>'.
   * @see Variation_Diff.TypeDifference#getHints()
   * @see #getTypeDifference()
   * @generated
   */
  EReference getTypeDifference_Hints();

  /**
   * Returns the meta object for class '{@link Variation_Diff.TypeHint <em>Type Hint</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Hint</em>'.
   * @see Variation_Diff.TypeHint
   * @generated
   */
  EClass getTypeHint();

  /**
   * Returns the meta object for the attribute '{@link Variation_Diff.TypeHint#getWithName <em>With Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>With Name</em>'.
   * @see Variation_Diff.TypeHint#getWithName()
   * @see #getTypeHint()
   * @generated
   */
  EAttribute getTypeHint_WithName();

  /**
   * Returns the meta object for the containment reference '{@link Variation_Diff.TypeHint#getWithType <em>With Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>With Type</em>'.
   * @see Variation_Diff.TypeHint#getWithType()
   * @see #getTypeHint()
   * @generated
   */
  EReference getTypeHint_WithType();

  /**
   * Returns the meta object for class '{@link Variation_Diff.HasField <em>Has Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Has Field</em>'.
   * @see Variation_Diff.HasField
   * @generated
   */
  EClass getHasField();

  /**
   * Returns the meta object for class '{@link Variation_Diff.HasNotField <em>Has Not Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Has Not Field</em>'.
   * @see Variation_Diff.HasNotField
   * @generated
   */
  EClass getHasNotField();

  /**
   * Returns the meta object for class '{@link Variation_Diff.FieldType <em>Field Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Field Type</em>'.
   * @see Variation_Diff.FieldType
   * @generated
   */
  EClass getFieldType();

  /**
   * Returns the meta object for class '{@link Variation_Diff.PrimitiveType <em>Primitive Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Primitive Type</em>'.
   * @see Variation_Diff.PrimitiveType
   * @generated
   */
  EClass getPrimitiveType();

  /**
   * Returns the meta object for the attribute '{@link Variation_Diff.PrimitiveType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see Variation_Diff.PrimitiveType#getType()
   * @see #getPrimitiveType()
   * @generated
   */
  EAttribute getPrimitiveType_Type();

  /**
   * Returns the meta object for class '{@link Variation_Diff.TupleType <em>Tuple Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuple Type</em>'.
   * @see Variation_Diff.TupleType
   * @generated
   */
  EClass getTupleType();

  /**
   * Returns the meta object for class '{@link Variation_Diff.AssociationType <em>Association Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Association Type</em>'.
   * @see Variation_Diff.AssociationType
   * @generated
   */
  EClass getAssociationType();

  /**
   * Returns the meta object for the attribute '{@link Variation_Diff.AssociationType#getLowerBound <em>Lower Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lower Bound</em>'.
   * @see Variation_Diff.AssociationType#getLowerBound()
   * @see #getAssociationType()
   * @generated
   */
  EAttribute getAssociationType_LowerBound();

  /**
   * Returns the meta object for the attribute '{@link Variation_Diff.AssociationType#getUpperBound <em>Upper Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Upper Bound</em>'.
   * @see Variation_Diff.AssociationType#getUpperBound()
   * @see #getAssociationType()
   * @generated
   */
  EAttribute getAssociationType_UpperBound();

  /**
   * Returns the meta object for class '{@link Variation_Diff.AggregateType <em>Aggregate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Aggregate Type</em>'.
   * @see Variation_Diff.AggregateType
   * @generated
   */
  EClass getAggregateType();

  /**
   * Returns the meta object for the attribute list '{@link Variation_Diff.AggregateType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Type</em>'.
   * @see Variation_Diff.AggregateType#getType()
   * @see #getAggregateType()
   * @generated
   */
  EAttribute getAggregateType_Type();

  /**
   * Returns the meta object for class '{@link Variation_Diff.ReferenceType <em>Reference Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reference Type</em>'.
   * @see Variation_Diff.ReferenceType
   * @generated
   */
  EClass getReferenceType();

  /**
   * Returns the meta object for the attribute '{@link Variation_Diff.ReferenceType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see Variation_Diff.ReferenceType#getType()
   * @see #getReferenceType()
   * @generated
   */
  EAttribute getReferenceType_Type();

  /**
   * Returns the meta object for class '{@link Variation_Diff.EntityType <em>Entity Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Type</em>'.
   * @see Variation_Diff.EntityType
   * @generated
   */
  EClass getEntityType();

  /**
   * Returns the meta object for the attribute '{@link Variation_Diff.EntityType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see Variation_Diff.EntityType#getType()
   * @see #getEntityType()
   * @generated
   */
  EAttribute getEntityType_Type();

  /**
   * Returns the meta object for class '{@link Variation_Diff.HomogeneousTupleType <em>Homogeneous Tuple Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Homogeneous Tuple Type</em>'.
   * @see Variation_Diff.HomogeneousTupleType
   * @generated
   */
  EClass getHomogeneousTupleType();

  /**
   * Returns the meta object for the attribute '{@link Variation_Diff.HomogeneousTupleType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see Variation_Diff.HomogeneousTupleType#getType()
   * @see #getHomogeneousTupleType()
   * @generated
   */
  EAttribute getHomogeneousTupleType_Type();

  /**
   * Returns the meta object for class '{@link Variation_Diff.HeterogeneousTupleType <em>Heterogeneous Tuple Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Heterogeneous Tuple Type</em>'.
   * @see Variation_Diff.HeterogeneousTupleType
   * @generated
   */
  EClass getHeterogeneousTupleType();

  /**
   * Returns the meta object for the attribute list '{@link Variation_Diff.HeterogeneousTupleType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Type</em>'.
   * @see Variation_Diff.HeterogeneousTupleType#getType()
   * @see #getHeterogeneousTupleType()
   * @generated
   */
  EAttribute getHeterogeneousTupleType_Type();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  Variation_DiffFactory getVariation_DiffFactory();

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
     * The meta object literal for the '{@link Variation_Diff.impl.NoSQLDifferencesImpl <em>No SQL Differences</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.NoSQLDifferencesImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getNoSQLDifferences()
     * @generated
     */
    EClass NO_SQL_DIFFERENCES = eINSTANCE.getNoSQLDifferences();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NO_SQL_DIFFERENCES__NAME = eINSTANCE.getNoSQLDifferences_Name();

    /**
     * The meta object literal for the '<em><b>Has Type Differences</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NO_SQL_DIFFERENCES__HAS_TYPE_DIFFERENCES = eINSTANCE.getNoSQLDifferences_HasTypeDifferences();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.TypeDifferenceImpl <em>Type Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.TypeDifferenceImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getTypeDifference()
     * @generated
     */
    EClass TYPE_DIFFERENCE = eINSTANCE.getTypeDifference();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TYPE_DIFFERENCE__NAME = eINSTANCE.getTypeDifference_Name();

    /**
     * The meta object literal for the '<em><b>Hints</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_DIFFERENCE__HINTS = eINSTANCE.getTypeDifference_Hints();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.TypeHintImpl <em>Type Hint</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.TypeHintImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getTypeHint()
     * @generated
     */
    EClass TYPE_HINT = eINSTANCE.getTypeHint();

    /**
     * The meta object literal for the '<em><b>With Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TYPE_HINT__WITH_NAME = eINSTANCE.getTypeHint_WithName();

    /**
     * The meta object literal for the '<em><b>With Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_HINT__WITH_TYPE = eINSTANCE.getTypeHint_WithType();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.HasFieldImpl <em>Has Field</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.HasFieldImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getHasField()
     * @generated
     */
    EClass HAS_FIELD = eINSTANCE.getHasField();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.HasNotFieldImpl <em>Has Not Field</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.HasNotFieldImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getHasNotField()
     * @generated
     */
    EClass HAS_NOT_FIELD = eINSTANCE.getHasNotField();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.FieldTypeImpl <em>Field Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.FieldTypeImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getFieldType()
     * @generated
     */
    EClass FIELD_TYPE = eINSTANCE.getFieldType();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.PrimitiveTypeImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getPrimitiveType()
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
     * The meta object literal for the '{@link Variation_Diff.impl.TupleTypeImpl <em>Tuple Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.TupleTypeImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getTupleType()
     * @generated
     */
    EClass TUPLE_TYPE = eINSTANCE.getTupleType();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.AssociationTypeImpl <em>Association Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.AssociationTypeImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getAssociationType()
     * @generated
     */
    EClass ASSOCIATION_TYPE = eINSTANCE.getAssociationType();

    /**
     * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ASSOCIATION_TYPE__LOWER_BOUND = eINSTANCE.getAssociationType_LowerBound();

    /**
     * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ASSOCIATION_TYPE__UPPER_BOUND = eINSTANCE.getAssociationType_UpperBound();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.AggregateTypeImpl <em>Aggregate Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.AggregateTypeImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getAggregateType()
     * @generated
     */
    EClass AGGREGATE_TYPE = eINSTANCE.getAggregateType();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AGGREGATE_TYPE__TYPE = eINSTANCE.getAggregateType_Type();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.ReferenceTypeImpl <em>Reference Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.ReferenceTypeImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getReferenceType()
     * @generated
     */
    EClass REFERENCE_TYPE = eINSTANCE.getReferenceType();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REFERENCE_TYPE__TYPE = eINSTANCE.getReferenceType_Type();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.EntityTypeImpl <em>Entity Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.EntityTypeImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getEntityType()
     * @generated
     */
    EClass ENTITY_TYPE = eINSTANCE.getEntityType();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_TYPE__TYPE = eINSTANCE.getEntityType_Type();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.HomogeneousTupleTypeImpl <em>Homogeneous Tuple Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.HomogeneousTupleTypeImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getHomogeneousTupleType()
     * @generated
     */
    EClass HOMOGENEOUS_TUPLE_TYPE = eINSTANCE.getHomogeneousTupleType();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute HOMOGENEOUS_TUPLE_TYPE__TYPE = eINSTANCE.getHomogeneousTupleType_Type();

    /**
     * The meta object literal for the '{@link Variation_Diff.impl.HeterogeneousTupleTypeImpl <em>Heterogeneous Tuple Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see Variation_Diff.impl.HeterogeneousTupleTypeImpl
     * @see Variation_Diff.impl.Variation_DiffPackageImpl#getHeterogeneousTupleType()
     * @generated
     */
    EClass HETEROGENEOUS_TUPLE_TYPE = eINSTANCE.getHeterogeneousTupleType();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute HETEROGENEOUS_TUPLE_TYPE__TYPE = eINSTANCE.getHeterogeneousTupleType_Type();

  }

} //Variation_DiffPackage
