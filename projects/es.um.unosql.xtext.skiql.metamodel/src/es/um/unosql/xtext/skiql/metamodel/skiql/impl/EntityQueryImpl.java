/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.EntityQuery;
import es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.Having;
import es.um.unosql.xtext.skiql.metamodel.skiql.Operation;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityQueryImpl#getEntitySpec <em>Entity Spec</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityQueryImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntityQueryImpl#getHaving <em>Having</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityQueryImpl extends QueryImpl implements EntityQuery {
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
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation operation;

	/**
	 * The cached value of the '{@link #getHaving() <em>Having</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHaving()
	 * @generated
	 * @ordered
	 */
	protected Having having;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityQueryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.ENTITY_QUERY;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_QUERY__ENTITY_SPEC, oldEntitySpec, newEntitySpec);
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
				msgs = ((InternalEObject)entitySpec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_QUERY__ENTITY_SPEC, null, msgs);
			if (newEntitySpec != null)
				msgs = ((InternalEObject)newEntitySpec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_QUERY__ENTITY_SPEC, null, msgs);
			msgs = basicSetEntitySpec(newEntitySpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_QUERY__ENTITY_SPEC, newEntitySpec, newEntitySpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperation(Operation newOperation, NotificationChain msgs) {
		Operation oldOperation = operation;
		operation = newOperation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_QUERY__OPERATION, oldOperation, newOperation);
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
	public void setOperation(Operation newOperation) {
		if (newOperation != operation) {
			NotificationChain msgs = null;
			if (operation != null)
				msgs = ((InternalEObject)operation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_QUERY__OPERATION, null, msgs);
			if (newOperation != null)
				msgs = ((InternalEObject)newOperation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_QUERY__OPERATION, null, msgs);
			msgs = basicSetOperation(newOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_QUERY__OPERATION, newOperation, newOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Having getHaving() {
		return having;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHaving(Having newHaving, NotificationChain msgs) {
		Having oldHaving = having;
		having = newHaving;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_QUERY__HAVING, oldHaving, newHaving);
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
	public void setHaving(Having newHaving) {
		if (newHaving != having) {
			NotificationChain msgs = null;
			if (having != null)
				msgs = ((InternalEObject)having).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_QUERY__HAVING, null, msgs);
			if (newHaving != null)
				msgs = ((InternalEObject)newHaving).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_QUERY__HAVING, null, msgs);
			msgs = basicSetHaving(newHaving, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_QUERY__HAVING, newHaving, newHaving));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.ENTITY_QUERY__ENTITY_SPEC:
				return basicSetEntitySpec(null, msgs);
			case SkiqlPackage.ENTITY_QUERY__OPERATION:
				return basicSetOperation(null, msgs);
			case SkiqlPackage.ENTITY_QUERY__HAVING:
				return basicSetHaving(null, msgs);
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
			case SkiqlPackage.ENTITY_QUERY__ENTITY_SPEC:
				return getEntitySpec();
			case SkiqlPackage.ENTITY_QUERY__OPERATION:
				return getOperation();
			case SkiqlPackage.ENTITY_QUERY__HAVING:
				return getHaving();
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
			case SkiqlPackage.ENTITY_QUERY__ENTITY_SPEC:
				setEntitySpec((EntitySpec)newValue);
				return;
			case SkiqlPackage.ENTITY_QUERY__OPERATION:
				setOperation((Operation)newValue);
				return;
			case SkiqlPackage.ENTITY_QUERY__HAVING:
				setHaving((Having)newValue);
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
			case SkiqlPackage.ENTITY_QUERY__ENTITY_SPEC:
				setEntitySpec((EntitySpec)null);
				return;
			case SkiqlPackage.ENTITY_QUERY__OPERATION:
				setOperation((Operation)null);
				return;
			case SkiqlPackage.ENTITY_QUERY__HAVING:
				setHaving((Having)null);
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
			case SkiqlPackage.ENTITY_QUERY__ENTITY_SPEC:
				return entitySpec != null;
			case SkiqlPackage.ENTITY_QUERY__OPERATION:
				return operation != null;
			case SkiqlPackage.ENTITY_QUERY__HAVING:
				return having != null;
		}
		return super.eIsSet(featureID);
	}

} //EntityQueryImpl
