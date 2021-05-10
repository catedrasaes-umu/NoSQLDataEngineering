/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class SkiqlFactoryImpl extends EFactoryImpl implements SkiqlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SkiqlFactory init() {
		try {
			SkiqlFactory theSkiqlFactory = (SkiqlFactory)EPackage.Registry.INSTANCE.getEFactory(SkiqlPackage.eNS_URI);
			if (theSkiqlFactory != null) {
				return theSkiqlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SkiqlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkiqlFactoryImpl() {
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
			case SkiqlPackage.SKI_QL_MODEL: return createSkiQLModel();
			case SkiqlPackage.RELATIONSHIP_QUERY: return createRelationshipQuery();
			case SkiqlPackage.ENTITY_QUERY: return createEntityQuery();
			case SkiqlPackage.ENTITY_SPEC: return createEntitySpec();
			case SkiqlPackage.BEFORE: return createBefore();
			case SkiqlPackage.AFTER: return createAfter();
			case SkiqlPackage.BETWEEN: return createBetween();
			case SkiqlPackage.DATE_TIME: return createDateTime();
			case SkiqlPackage.ALL: return createAll();
			case SkiqlPackage.COUNT_OPERATION: return createCountOperation();
			case SkiqlPackage.VARIATION_FILTER: return createVariationFilter();
			case SkiqlPackage.PROPERTY_SPEC: return createPropertySpec();
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC: return createPropertySelectorSpec();
			case SkiqlPackage.RELATIONSHIP_SPEC: return createRelationshipSpec();
			case SkiqlPackage.AGGREGATION_SPEC: return createAggregationSpec();
			case SkiqlPackage.REFERENCE_SPEC: return createReferenceSpec();
			case SkiqlPackage.RELATIONSHIP_EXPRESSION: return createRelationshipExpression();
			case SkiqlPackage.ENTITY_EXPRESSION: return createEntityExpression();
			case SkiqlPackage.PRIMITIVE_TYPE: return createPrimitiveType();
			case SkiqlPackage.RELATIONSHIP_TYPE: return createRelationshipType();
			case SkiqlPackage.RELATION_SPEC: return createRelationSpec();
			case SkiqlPackage.PROPERTY_FILTER: return createPropertyFilter();
			case SkiqlPackage.HAVING: return createHaving();
			case SkiqlPackage.HAVING_KEYS: return createHavingKeys();
			case SkiqlPackage.HAVING_VARIATIONS: return createHavingVariations();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SkiqlPackage.SPECIFICITY_TYPE_ENUM:
				return createSpecificityTypeEnumFromString(eDataType, initialValue);
			case SkiqlPackage.PROPERTY_TYPE_ENUM:
				return createPropertyTypeEnumFromString(eDataType, initialValue);
			case SkiqlPackage.RELATIONSHIP_TYPE_ENUM:
				return createRelationshipTypeEnumFromString(eDataType, initialValue);
			case SkiqlPackage.TYPE_ENUM:
				return createTypeEnumFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SkiqlPackage.SPECIFICITY_TYPE_ENUM:
				return convertSpecificityTypeEnumToString(eDataType, instanceValue);
			case SkiqlPackage.PROPERTY_TYPE_ENUM:
				return convertPropertyTypeEnumToString(eDataType, instanceValue);
			case SkiqlPackage.RELATIONSHIP_TYPE_ENUM:
				return convertRelationshipTypeEnumToString(eDataType, instanceValue);
			case SkiqlPackage.TYPE_ENUM:
				return convertTypeEnumToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SkiQLModel createSkiQLModel() {
		SkiQLModelImpl skiQLModel = new SkiQLModelImpl();
		return skiQLModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationshipQuery createRelationshipQuery() {
		RelationshipQueryImpl relationshipQuery = new RelationshipQueryImpl();
		return relationshipQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntityQuery createEntityQuery() {
		EntityQueryImpl entityQuery = new EntityQueryImpl();
		return entityQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntitySpec createEntitySpec() {
		EntitySpecImpl entitySpec = new EntitySpecImpl();
		return entitySpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Before createBefore() {
		BeforeImpl before = new BeforeImpl();
		return before;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public After createAfter() {
		AfterImpl after = new AfterImpl();
		return after;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Between createBetween() {
		BetweenImpl between = new BetweenImpl();
		return between;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DateTime createDateTime() {
		DateTimeImpl dateTime = new DateTimeImpl();
		return dateTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public All createAll() {
		AllImpl all = new AllImpl();
		return all;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CountOperation createCountOperation() {
		CountOperationImpl countOperation = new CountOperationImpl();
		return countOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariationFilter createVariationFilter() {
		VariationFilterImpl variationFilter = new VariationFilterImpl();
		return variationFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PropertySpec createPropertySpec() {
		PropertySpecImpl propertySpec = new PropertySpecImpl();
		return propertySpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PropertySelectorSpec createPropertySelectorSpec() {
		PropertySelectorSpecImpl propertySelectorSpec = new PropertySelectorSpecImpl();
		return propertySelectorSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationshipSpec createRelationshipSpec() {
		RelationshipSpecImpl relationshipSpec = new RelationshipSpecImpl();
		return relationshipSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AggregationSpec createAggregationSpec() {
		AggregationSpecImpl aggregationSpec = new AggregationSpecImpl();
		return aggregationSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReferenceSpec createReferenceSpec() {
		ReferenceSpecImpl referenceSpec = new ReferenceSpecImpl();
		return referenceSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationshipExpression createRelationshipExpression() {
		RelationshipExpressionImpl relationshipExpression = new RelationshipExpressionImpl();
		return relationshipExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntityExpression createEntityExpression() {
		EntityExpressionImpl entityExpression = new EntityExpressionImpl();
		return entityExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PrimitiveType createPrimitiveType() {
		PrimitiveTypeImpl primitiveType = new PrimitiveTypeImpl();
		return primitiveType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationshipType createRelationshipType() {
		RelationshipTypeImpl relationshipType = new RelationshipTypeImpl();
		return relationshipType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationSpec createRelationSpec() {
		RelationSpecImpl relationSpec = new RelationSpecImpl();
		return relationSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PropertyFilter createPropertyFilter() {
		PropertyFilterImpl propertyFilter = new PropertyFilterImpl();
		return propertyFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Having createHaving() {
		HavingImpl having = new HavingImpl();
		return having;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HavingKeys createHavingKeys() {
		HavingKeysImpl havingKeys = new HavingKeysImpl();
		return havingKeys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HavingVariations createHavingVariations() {
		HavingVariationsImpl havingVariations = new HavingVariationsImpl();
		return havingVariations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecificityTypeEnum createSpecificityTypeEnumFromString(EDataType eDataType, String initialValue) {
		SpecificityTypeEnum result = SpecificityTypeEnum.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSpecificityTypeEnumToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyTypeEnum createPropertyTypeEnumFromString(EDataType eDataType, String initialValue) {
		PropertyTypeEnum result = PropertyTypeEnum.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPropertyTypeEnumToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationshipTypeEnum createRelationshipTypeEnumFromString(EDataType eDataType, String initialValue) {
		RelationshipTypeEnum result = RelationshipTypeEnum.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRelationshipTypeEnumToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeEnum createTypeEnumFromString(EDataType eDataType, String initialValue) {
		TypeEnum result = TypeEnum.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeEnumToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SkiqlPackage getSkiqlPackage() {
		return (SkiqlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SkiqlPackage getPackage() {
		return SkiqlPackage.eINSTANCE;
	}

} //SkiqlFactoryImpl
