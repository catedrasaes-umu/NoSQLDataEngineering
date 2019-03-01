/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;

import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage;

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
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl#getEntityDiffs <em>Entity Diffs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl#getName <em>Name</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityDifferentiationImpl extends MinimalEObjectImpl.Container implements EntityDifferentiation {
	/**
	 * The cached value of the '{@link #getEntityDiffs() <em>Entity Diffs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntityDiffs()
	 * @generated
	 * @ordered
	 */
	protected EList<EntityDiff> entityDiffs;

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
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected NoSQLSchema schema;

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
		return EntityDifferentiationPackage.Literals.ENTITY_DIFFERENTIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EntityDiff> getEntityDiffs() {
		if (entityDiffs == null) {
			entityDiffs = new EObjectContainmentEList<EntityDiff>(EntityDiff.class, this, EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFFS);
		}
		return entityDiffs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NoSQLSchema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (NoSQLSchema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoSQLSchema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSchema(NoSQLSchema newSchema) {
		NoSQLSchema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA, oldSchema, schema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFFS:
				return ((InternalEList<?>)getEntityDiffs()).basicRemove(otherEnd, msgs);
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
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFFS:
				return getEntityDiffs();
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
				return getName();
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
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
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFFS:
				getEntityDiffs().clear();
				getEntityDiffs().addAll((Collection<? extends EntityDiff>)newValue);
				return;
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
				setName((String)newValue);
				return;
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA:
				setSchema((NoSQLSchema)newValue);
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
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFFS:
				getEntityDiffs().clear();
				return;
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA:
				setSchema((NoSQLSchema)null);
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
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFFS:
				return entityDiffs != null && !entityDiffs.isEmpty();
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA:
				return schema != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //EntityDifferentiationImpl
