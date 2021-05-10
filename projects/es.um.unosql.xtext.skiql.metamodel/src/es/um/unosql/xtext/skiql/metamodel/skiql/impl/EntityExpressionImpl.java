/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.EntityExpression;
import es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityExpressionImpl#getEntitySpec <em>Entity Spec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityExpressionImpl extends TargetExpressionImpl implements EntityExpression {
	/**
	 * The cached value of the '{@link #getEntitySpec() <em>Entity Spec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntitySpec()
	 * @generated
	 * @ordered
	 */
	protected EntitySpec entitySpec;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.ENTITY_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntitySpec getEntitySpec() {
		return entitySpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEntitySpec(EntitySpec newEntitySpec, NotificationChain msgs) {
		EntitySpec oldEntitySpec = entitySpec;
		entitySpec = newEntitySpec;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_EXPRESSION__ENTITY_SPEC, oldEntitySpec, newEntitySpec);
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
	public void setEntitySpec(EntitySpec newEntitySpec) {
		if (newEntitySpec != entitySpec) {
			NotificationChain msgs = null;
			if (entitySpec != null)
				msgs = ((InternalEObject)entitySpec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_EXPRESSION__ENTITY_SPEC, null, msgs);
			if (newEntitySpec != null)
				msgs = ((InternalEObject)newEntitySpec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_EXPRESSION__ENTITY_SPEC, null, msgs);
			msgs = basicSetEntitySpec(newEntitySpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_EXPRESSION__ENTITY_SPEC, newEntitySpec, newEntitySpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.ENTITY_EXPRESSION__ENTITY_SPEC:
				return basicSetEntitySpec(null, msgs);
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
			case SkiqlPackage.ENTITY_EXPRESSION__ENTITY_SPEC:
				return getEntitySpec();
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
			case SkiqlPackage.ENTITY_EXPRESSION__ENTITY_SPEC:
				setEntitySpec((EntitySpec)newValue);
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
			case SkiqlPackage.ENTITY_EXPRESSION__ENTITY_SPEC:
				setEntitySpec((EntitySpec)null);
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
			case SkiqlPackage.ENTITY_EXPRESSION__ENTITY_SPEC:
				return entitySpec != null;
		}
		return super.eIsSet(featureID);
	}

} //EntityExpressionImpl
