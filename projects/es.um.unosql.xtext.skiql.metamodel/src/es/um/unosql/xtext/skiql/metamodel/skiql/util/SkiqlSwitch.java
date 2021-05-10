/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.util;

import es.um.unosql.xtext.skiql.metamodel.skiql.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage
 * @generated
 */
public class SkiqlSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SkiqlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkiqlSwitch() {
		if (modelPackage == null) {
			modelPackage = SkiqlPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case SkiqlPackage.SKI_QL_MODEL: {
				SkiQLModel skiQLModel = (SkiQLModel)theEObject;
				T result = caseSkiQLModel(skiQLModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.QUERY: {
				Query query = (Query)theEObject;
				T result = caseQuery(query);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.RELATIONSHIP_QUERY: {
				RelationshipQuery relationshipQuery = (RelationshipQuery)theEObject;
				T result = caseRelationshipQuery(relationshipQuery);
				if (result == null) result = caseQuery(relationshipQuery);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.ENTITY_QUERY: {
				EntityQuery entityQuery = (EntityQuery)theEObject;
				T result = caseEntityQuery(entityQuery);
				if (result == null) result = caseQuery(entityQuery);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.ENTITY_SPEC: {
				EntitySpec entitySpec = (EntitySpec)theEObject;
				T result = caseEntitySpec(entitySpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.ENTITY_OPERATION: {
				EntityOperation entityOperation = (EntityOperation)theEObject;
				T result = caseEntityOperation(entityOperation);
				if (result == null) result = caseOperation(entityOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.TYPE: {
				Type type = (Type)theEObject;
				T result = caseType(type);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.VERSION_HISTORY: {
				VersionHistory versionHistory = (VersionHistory)theEObject;
				T result = caseVersionHistory(versionHistory);
				if (result == null) result = caseEntityOperation(versionHistory);
				if (result == null) result = caseOperation(versionHistory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.BEFORE: {
				Before before = (Before)theEObject;
				T result = caseBefore(before);
				if (result == null) result = caseVersionHistory(before);
				if (result == null) result = caseEntityOperation(before);
				if (result == null) result = caseOperation(before);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.AFTER: {
				After after = (After)theEObject;
				T result = caseAfter(after);
				if (result == null) result = caseVersionHistory(after);
				if (result == null) result = caseEntityOperation(after);
				if (result == null) result = caseOperation(after);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.BETWEEN: {
				Between between = (Between)theEObject;
				T result = caseBetween(between);
				if (result == null) result = caseVersionHistory(between);
				if (result == null) result = caseEntityOperation(between);
				if (result == null) result = caseOperation(between);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.DATE_TIME: {
				DateTime dateTime = (DateTime)theEObject;
				T result = caseDateTime(dateTime);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.ALL: {
				All all = (All)theEObject;
				T result = caseAll(all);
				if (result == null) result = caseVersionHistory(all);
				if (result == null) result = caseEntityOperation(all);
				if (result == null) result = caseOperation(all);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.COUNT_OPERATION: {
				CountOperation countOperation = (CountOperation)theEObject;
				T result = caseCountOperation(countOperation);
				if (result == null) result = caseEntityOperation(countOperation);
				if (result == null) result = caseOperation(countOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.VARIATION_FILTER: {
				VariationFilter variationFilter = (VariationFilter)theEObject;
				T result = caseVariationFilter(variationFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.PROPERTY_SPEC: {
				PropertySpec propertySpec = (PropertySpec)theEObject;
				T result = casePropertySpec(propertySpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC: {
				PropertySelectorSpec propertySelectorSpec = (PropertySelectorSpec)theEObject;
				T result = casePropertySelectorSpec(propertySelectorSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.OPERATION: {
				Operation operation = (Operation)theEObject;
				T result = caseOperation(operation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.RELATIONSHIP_SPEC: {
				RelationshipSpec relationshipSpec = (RelationshipSpec)theEObject;
				T result = caseRelationshipSpec(relationshipSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.AGGREGATION_SPEC: {
				AggregationSpec aggregationSpec = (AggregationSpec)theEObject;
				T result = caseAggregationSpec(aggregationSpec);
				if (result == null) result = caseRelationSpec(aggregationSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.REFERENCE_SPEC: {
				ReferenceSpec referenceSpec = (ReferenceSpec)theEObject;
				T result = caseReferenceSpec(referenceSpec);
				if (result == null) result = caseRelationSpec(referenceSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.TARGET_EXPRESSION: {
				TargetExpression targetExpression = (TargetExpression)theEObject;
				T result = caseTargetExpression(targetExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.RELATIONSHIP_EXPRESSION: {
				RelationshipExpression relationshipExpression = (RelationshipExpression)theEObject;
				T result = caseRelationshipExpression(relationshipExpression);
				if (result == null) result = caseTargetExpression(relationshipExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.ENTITY_EXPRESSION: {
				EntityExpression entityExpression = (EntityExpression)theEObject;
				T result = caseEntityExpression(entityExpression);
				if (result == null) result = caseTargetExpression(entityExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.PRIMITIVE_TYPE: {
				PrimitiveType primitiveType = (PrimitiveType)theEObject;
				T result = casePrimitiveType(primitiveType);
				if (result == null) result = caseType(primitiveType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.RELATIONSHIP_TYPE: {
				RelationshipType relationshipType = (RelationshipType)theEObject;
				T result = caseRelationshipType(relationshipType);
				if (result == null) result = caseType(relationshipType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.RELATION_SPEC: {
				RelationSpec relationSpec = (RelationSpec)theEObject;
				T result = caseRelationSpec(relationSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.PROPERTY_FILTER: {
				PropertyFilter propertyFilter = (PropertyFilter)theEObject;
				T result = casePropertyFilter(propertyFilter);
				if (result == null) result = caseOperation(propertyFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.HAVING: {
				Having having = (Having)theEObject;
				T result = caseHaving(having);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.HAVING_TYPE: {
				HavingType havingType = (HavingType)theEObject;
				T result = caseHavingType(havingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.HAVING_KEYS: {
				HavingKeys havingKeys = (HavingKeys)theEObject;
				T result = caseHavingKeys(havingKeys);
				if (result == null) result = caseHavingType(havingKeys);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SkiqlPackage.HAVING_VARIATIONS: {
				HavingVariations havingVariations = (HavingVariations)theEObject;
				T result = caseHavingVariations(havingVariations);
				if (result == null) result = caseHavingType(havingVariations);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ski QL Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ski QL Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSkiQLModel(SkiQLModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Query</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuery(Query object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relationship Query</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relationship Query</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationshipQuery(RelationshipQuery object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity Query</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity Query</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntityQuery(EntityQuery object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntitySpec(EntitySpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntityOperation(EntityOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseType(Type object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Version History</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Version History</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVersionHistory(VersionHistory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Before</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Before</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBefore(Before object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>After</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>After</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAfter(After object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Between</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Between</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBetween(Between object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Date Time</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Date Time</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDateTime(DateTime object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>All</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>All</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAll(All object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Count Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Count Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCountOperation(CountOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variation Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variation Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariationFilter(VariationFilter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertySpec(PropertySpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Selector Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Selector Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertySelectorSpec(PropertySelectorSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperation(Operation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relationship Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relationship Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationshipSpec(RelationshipSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Aggregation Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Aggregation Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAggregationSpec(AggregationSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceSpec(ReferenceSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Target Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Target Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTargetExpression(TargetExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relationship Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relationship Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationshipExpression(RelationshipExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntityExpression(EntityExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveType(PrimitiveType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relationship Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relationship Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationshipType(RelationshipType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relation Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relation Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationSpec(RelationSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyFilter(PropertyFilter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Having</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Having</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHaving(Having object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Having Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Having Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHavingType(HavingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Having Keys</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Having Keys</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHavingKeys(HavingKeys object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Having Variations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Having Variations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHavingVariations(HavingVariations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //SkiqlSwitch
