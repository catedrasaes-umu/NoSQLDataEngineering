/**
 */
package es.um.nosql.schemainference.entitydifferentiation.impl;

import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec;
import es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp;
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage;
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Diff Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityDiffSpecImpl#getEntity <em>Entity</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityDiffSpecImpl#getEntityVersionProps <em>Entity Version Props</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityDiffSpecImpl#getCommonProps <em>Common Props</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityDiffSpecImpl extends MinimalEObjectImpl.Container implements EntityDiffSpec {
	/**
	 * The cached value of the '{@link #getEntity() <em>Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntity()
	 * @generated
	 * @ordered
	 */
	protected Entity entity;

	/**
	 * The cached value of the '{@link #getEntityVersionProps() <em>Entity Version Props</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntityVersionProps()
	 * @generated
	 * @ordered
	 */
	protected EList<EntityVersionProp> entityVersionProps;

	/**
	 * The cached value of the '{@link #getCommonProps() <em>Common Props</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommonProps()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertySpec> commonProps;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityDiffSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EntitydifferentiationPackage.Literals.ENTITY_DIFF_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getEntity() {
		if (entity != null && entity.eIsProxy()) {
			InternalEObject oldEntity = (InternalEObject)entity;
			entity = (Entity)eResolveProxy(oldEntity);
			if (entity != oldEntity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY, oldEntity, entity));
			}
		}
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity basicGetEntity() {
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntity(Entity newEntity) {
		Entity oldEntity = entity;
		entity = newEntity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY, oldEntity, entity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EntityVersionProp> getEntityVersionProps() {
		if (entityVersionProps == null) {
			entityVersionProps = new EObjectContainmentEList<EntityVersionProp>(EntityVersionProp.class, this, EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VERSION_PROPS);
		}
		return entityVersionProps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertySpec> getCommonProps() {
		if (commonProps == null) {
			commonProps = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, EntitydifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS);
		}
		return commonProps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VERSION_PROPS:
				return ((InternalEList<?>)getEntityVersionProps()).basicRemove(otherEnd, msgs);
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
				return ((InternalEList<?>)getCommonProps()).basicRemove(otherEnd, msgs);
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
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY:
				if (resolve) return getEntity();
				return basicGetEntity();
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VERSION_PROPS:
				return getEntityVersionProps();
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
				return getCommonProps();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY:
				setEntity((Entity)newValue);
				return;
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VERSION_PROPS:
				getEntityVersionProps().clear();
				getEntityVersionProps().addAll((Collection<? extends EntityVersionProp>)newValue);
				return;
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
				getCommonProps().clear();
				getCommonProps().addAll((Collection<? extends PropertySpec>)newValue);
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
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY:
				setEntity((Entity)null);
				return;
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VERSION_PROPS:
				getEntityVersionProps().clear();
				return;
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
				getCommonProps().clear();
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
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY:
				return entity != null;
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VERSION_PROPS:
				return entityVersionProps != null && !entityVersionProps.isEmpty();
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
				return commonProps != null && !commonProps.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EntityDiffSpecImpl
