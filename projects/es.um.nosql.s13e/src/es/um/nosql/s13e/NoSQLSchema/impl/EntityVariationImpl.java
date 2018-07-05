/**
 */
package es.um.nosql.s13e.NoSQLSchema.impl;

import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;

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
 * An implementation of the model object '<em><b>Entity Variation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityVariationImpl#getVariationId <em>Variation Id</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityVariationImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityVariationImpl#getCount <em>Count</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityVariationImpl#isRoot <em>Root</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityVariationImpl#getTimestamp <em>Timestamp</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityVariationImpl extends MinimalEObjectImpl.Container implements EntityVariation {
	/**
	 * The default value of the '{@link #getVariationId() <em>Variation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariationId()
	 * @generated
	 * @ordered
	 */
	protected static final int VARIATION_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getVariationId() <em>Variation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariationId()
	 * @generated
	 * @ordered
	 */
	protected int variationId = VARIATION_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> properties;

	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final long COUNT_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected long count = COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #isRoot() <em>Root</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRoot()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROOT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRoot() <em>Root</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRoot()
	 * @generated
	 * @ordered
	 */
	protected boolean root = ROOT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final long TIMESTAMP_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected long timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityVariationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NoSQLSchemaPackage.Literals.ENTITY_VARIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getVariationId() {
		return variationId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariationId(int newVariationId) {
		int oldVariationId = variationId;
		variationId = newVariationId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.ENTITY_VARIATION__VARIATION_ID, oldVariationId, variationId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<Property>(Property.class, this, NoSQLSchemaPackage.ENTITY_VARIATION__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getCount() {
		return count;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCount(long newCount) {
		long oldCount = count;
		count = newCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.ENTITY_VARIATION__COUNT, oldCount, count));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRoot() {
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(boolean newRoot) {
		boolean oldRoot = root;
		root = newRoot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.ENTITY_VARIATION__ROOT, oldRoot, root));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimestamp(long newTimestamp) {
		long oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.ENTITY_VARIATION__TIMESTAMP, oldTimestamp, timestamp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NoSQLSchemaPackage.ENTITY_VARIATION__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
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
			case NoSQLSchemaPackage.ENTITY_VARIATION__VARIATION_ID:
				return getVariationId();
			case NoSQLSchemaPackage.ENTITY_VARIATION__PROPERTIES:
				return getProperties();
			case NoSQLSchemaPackage.ENTITY_VARIATION__COUNT:
				return getCount();
			case NoSQLSchemaPackage.ENTITY_VARIATION__ROOT:
				return isRoot();
			case NoSQLSchemaPackage.ENTITY_VARIATION__TIMESTAMP:
				return getTimestamp();
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
			case NoSQLSchemaPackage.ENTITY_VARIATION__VARIATION_ID:
				setVariationId((Integer)newValue);
				return;
			case NoSQLSchemaPackage.ENTITY_VARIATION__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case NoSQLSchemaPackage.ENTITY_VARIATION__COUNT:
				setCount((Long)newValue);
				return;
			case NoSQLSchemaPackage.ENTITY_VARIATION__ROOT:
				setRoot((Boolean)newValue);
				return;
			case NoSQLSchemaPackage.ENTITY_VARIATION__TIMESTAMP:
				setTimestamp((Long)newValue);
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
			case NoSQLSchemaPackage.ENTITY_VARIATION__VARIATION_ID:
				setVariationId(VARIATION_ID_EDEFAULT);
				return;
			case NoSQLSchemaPackage.ENTITY_VARIATION__PROPERTIES:
				getProperties().clear();
				return;
			case NoSQLSchemaPackage.ENTITY_VARIATION__COUNT:
				setCount(COUNT_EDEFAULT);
				return;
			case NoSQLSchemaPackage.ENTITY_VARIATION__ROOT:
				setRoot(ROOT_EDEFAULT);
				return;
			case NoSQLSchemaPackage.ENTITY_VARIATION__TIMESTAMP:
				setTimestamp(TIMESTAMP_EDEFAULT);
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
			case NoSQLSchemaPackage.ENTITY_VARIATION__VARIATION_ID:
				return variationId != VARIATION_ID_EDEFAULT;
			case NoSQLSchemaPackage.ENTITY_VARIATION__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case NoSQLSchemaPackage.ENTITY_VARIATION__COUNT:
				return count != COUNT_EDEFAULT;
			case NoSQLSchemaPackage.ENTITY_VARIATION__ROOT:
				return root != ROOT_EDEFAULT;
			case NoSQLSchemaPackage.ENTITY_VARIATION__TIMESTAMP:
				return timestamp != TIMESTAMP_EDEFAULT;
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
		result.append(" (variationId: ");
		result.append(variationId);
		result.append(", count: ");
		result.append(count);
		result.append(", root: ");
		result.append(root);
		result.append(", timestamp: ");
		result.append(timestamp);
		result.append(')');
		return result.toString();
	}

} //EntityVariationImpl
