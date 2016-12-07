/**
 */
package es.um.nosql.schemainference.entitydifferentiation.impl;

import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec;
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation;
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage;

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
 * An implementation of the model object '<em><b>Entity Differentiation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityDifferentiationImpl#getEntityDiffSpecs <em>Entity Diff Specs</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityDifferentiationImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityDifferentiationImpl extends MinimalEObjectImpl.Container implements EntityDifferentiation {
	/**
	 * The cached value of the '{@link #getEntityDiffSpecs() <em>Entity Diff Specs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntityDiffSpecs()
	 * @generated
	 * @ordered
	 */
	protected EList<EntityDiffSpec> entityDiffSpecs;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityDifferentiationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EntitydifferentiationPackage.Literals.ENTITY_DIFFERENTIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EntityDiffSpec> getEntityDiffSpecs() {
		if (entityDiffSpecs == null) {
			entityDiffSpecs = new EObjectContainmentEList<EntityDiffSpec>(EntityDiffSpec.class, this, EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS);
		}
		return entityDiffSpecs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
				return ((InternalEList<?>)getEntityDiffSpecs()).basicRemove(otherEnd, msgs);
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
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
				return getEntityDiffSpecs();
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
				return getName();
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
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
				getEntityDiffSpecs().clear();
				getEntityDiffSpecs().addAll((Collection<? extends EntityDiffSpec>)newValue);
				return;
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
				setName((String)newValue);
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
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
				getEntityDiffSpecs().clear();
				return;
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
				setName(NAME_EDEFAULT);
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
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
				return entityDiffSpecs != null && !entityDiffSpecs.isEmpty();
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //EntityDifferentiationImpl
