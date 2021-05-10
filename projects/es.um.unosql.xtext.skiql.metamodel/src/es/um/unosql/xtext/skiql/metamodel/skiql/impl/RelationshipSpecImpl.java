/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.RelationSpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;
import es.um.unosql.xtext.skiql.metamodel.skiql.TargetExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relationship Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipSpecImpl#getTargetExpression <em>Target Expression</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipSpecImpl#isIndirect <em>Indirect</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipSpecImpl#getRelationSpec <em>Relation Spec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationshipSpecImpl extends MinimalEObjectImpl.Container implements RelationshipSpec {
	/**
	 * The cached value of the '{@link #getTargetExpression() <em>Target Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetExpression()
	 * @generated
	 * @ordered
	 */
	protected TargetExpression targetExpression;

	/**
	 * The default value of the '{@link #isIndirect() <em>Indirect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIndirect()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INDIRECT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIndirect() <em>Indirect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIndirect()
	 * @generated
	 * @ordered
	 */
	protected boolean indirect = INDIRECT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRelationSpec() <em>Relation Spec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationSpec()
	 * @generated
	 * @ordered
	 */
	protected RelationSpec relationSpec;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationshipSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.RELATIONSHIP_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TargetExpression getTargetExpression() {
		return targetExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetExpression(TargetExpression newTargetExpression, NotificationChain msgs) {
		TargetExpression oldTargetExpression = targetExpression;
		targetExpression = newTargetExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_SPEC__TARGET_EXPRESSION, oldTargetExpression, newTargetExpression);
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
	public void setTargetExpression(TargetExpression newTargetExpression) {
		if (newTargetExpression != targetExpression) {
			NotificationChain msgs = null;
			if (targetExpression != null)
				msgs = ((InternalEObject)targetExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.RELATIONSHIP_SPEC__TARGET_EXPRESSION, null, msgs);
			if (newTargetExpression != null)
				msgs = ((InternalEObject)newTargetExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.RELATIONSHIP_SPEC__TARGET_EXPRESSION, null, msgs);
			msgs = basicSetTargetExpression(newTargetExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_SPEC__TARGET_EXPRESSION, newTargetExpression, newTargetExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIndirect() {
		return indirect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIndirect(boolean newIndirect) {
		boolean oldIndirect = indirect;
		indirect = newIndirect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_SPEC__INDIRECT, oldIndirect, indirect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationSpec getRelationSpec() {
		return relationSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelationSpec(RelationSpec newRelationSpec, NotificationChain msgs) {
		RelationSpec oldRelationSpec = relationSpec;
		relationSpec = newRelationSpec;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_SPEC__RELATION_SPEC, oldRelationSpec, newRelationSpec);
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
	public void setRelationSpec(RelationSpec newRelationSpec) {
		if (newRelationSpec != relationSpec) {
			NotificationChain msgs = null;
			if (relationSpec != null)
				msgs = ((InternalEObject)relationSpec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.RELATIONSHIP_SPEC__RELATION_SPEC, null, msgs);
			if (newRelationSpec != null)
				msgs = ((InternalEObject)newRelationSpec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.RELATIONSHIP_SPEC__RELATION_SPEC, null, msgs);
			msgs = basicSetRelationSpec(newRelationSpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_SPEC__RELATION_SPEC, newRelationSpec, newRelationSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_SPEC__TARGET_EXPRESSION:
				return basicSetTargetExpression(null, msgs);
			case SkiqlPackage.RELATIONSHIP_SPEC__RELATION_SPEC:
				return basicSetRelationSpec(null, msgs);
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
			case SkiqlPackage.RELATIONSHIP_SPEC__TARGET_EXPRESSION:
				return getTargetExpression();
			case SkiqlPackage.RELATIONSHIP_SPEC__INDIRECT:
				return isIndirect();
			case SkiqlPackage.RELATIONSHIP_SPEC__RELATION_SPEC:
				return getRelationSpec();
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
			case SkiqlPackage.RELATIONSHIP_SPEC__TARGET_EXPRESSION:
				setTargetExpression((TargetExpression)newValue);
				return;
			case SkiqlPackage.RELATIONSHIP_SPEC__INDIRECT:
				setIndirect((Boolean)newValue);
				return;
			case SkiqlPackage.RELATIONSHIP_SPEC__RELATION_SPEC:
				setRelationSpec((RelationSpec)newValue);
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
			case SkiqlPackage.RELATIONSHIP_SPEC__TARGET_EXPRESSION:
				setTargetExpression((TargetExpression)null);
				return;
			case SkiqlPackage.RELATIONSHIP_SPEC__INDIRECT:
				setIndirect(INDIRECT_EDEFAULT);
				return;
			case SkiqlPackage.RELATIONSHIP_SPEC__RELATION_SPEC:
				setRelationSpec((RelationSpec)null);
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
			case SkiqlPackage.RELATIONSHIP_SPEC__TARGET_EXPRESSION:
				return targetExpression != null;
			case SkiqlPackage.RELATIONSHIP_SPEC__INDIRECT:
				return indirect != INDIRECT_EDEFAULT;
			case SkiqlPackage.RELATIONSHIP_SPEC__RELATION_SPEC:
				return relationSpec != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (indirect: ");
		result.append(indirect);
		result.append(')');
		return result.toString();
	}

} //RelationshipSpecImpl
