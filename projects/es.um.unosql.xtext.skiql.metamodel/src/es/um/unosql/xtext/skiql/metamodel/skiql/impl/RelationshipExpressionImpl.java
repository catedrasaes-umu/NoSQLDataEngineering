/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipExpression;
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relationship Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipExpressionImpl#getRelationshipQuery <em>Relationship Query</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationshipExpressionImpl extends TargetExpressionImpl implements RelationshipExpression {
	/**
	 * The cached value of the '{@link #getRelationshipQuery() <em>Relationship Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationshipQuery()
	 * @generated
	 * @ordered
	 */
	protected RelationshipQuery relationshipQuery;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationshipExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.RELATIONSHIP_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationshipQuery getRelationshipQuery() {
		return relationshipQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelationshipQuery(RelationshipQuery newRelationshipQuery, NotificationChain msgs) {
		RelationshipQuery oldRelationshipQuery = relationshipQuery;
		relationshipQuery = newRelationshipQuery;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY, oldRelationshipQuery, newRelationshipQuery);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRelationshipQuery(RelationshipQuery newRelationshipQuery) {
		if (newRelationshipQuery != relationshipQuery) {
			NotificationChain msgs = null;
			if (relationshipQuery != null)
				msgs = ((InternalEObject)relationshipQuery).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY, null, msgs);
			if (newRelationshipQuery != null)
				msgs = ((InternalEObject)newRelationshipQuery).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY, null, msgs);
			msgs = basicSetRelationshipQuery(newRelationshipQuery, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY, newRelationshipQuery, newRelationshipQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY:
				return basicSetRelationshipQuery(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY:
				return getRelationshipQuery();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY:
				setRelationshipQuery((RelationshipQuery)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY:
				setRelationshipQuery((RelationshipQuery)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_EXPRESSION__RELATIONSHIP_QUERY:
				return relationshipQuery != null;
		}
		return super.eIsSet(featureID);
	}

} //RelationshipExpressionImpl
