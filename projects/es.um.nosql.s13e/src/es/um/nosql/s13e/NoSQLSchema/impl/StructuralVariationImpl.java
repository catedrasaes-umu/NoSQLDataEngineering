/**
 */
package es.um.nosql.s13e.NoSQLSchema.impl;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structural Variation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl#getVariationId <em>Variation Id</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl#getCount <em>Count</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl#getFirstTimestamp <em>First Timestamp</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl#getLastTimestamp <em>Last Timestamp</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.StructuralVariationImpl#getKey <em>Key</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StructuralVariationImpl extends MinimalEObjectImpl.Container implements StructuralVariation {
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
	 * The default value of the '{@link #getFirstTimestamp() <em>First Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getFirstTimestamp()
	 * @generated
	 * @ordered
	 */
  protected static final long FIRST_TIMESTAMP_EDEFAULT = 0L;

  /**
	 * The cached value of the '{@link #getFirstTimestamp() <em>First Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getFirstTimestamp()
	 * @generated
	 * @ordered
	 */
  protected long firstTimestamp = FIRST_TIMESTAMP_EDEFAULT;

  /**
	 * The default value of the '{@link #getLastTimestamp() <em>Last Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getLastTimestamp()
	 * @generated
	 * @ordered
	 */
  protected static final long LAST_TIMESTAMP_EDEFAULT = 0L;

  /**
	 * The cached value of the '{@link #getLastTimestamp() <em>Last Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getLastTimestamp()
	 * @generated
	 * @ordered
	 */
  protected long lastTimestamp = LAST_TIMESTAMP_EDEFAULT;

  /**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> key;

		/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected StructuralVariationImpl() {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  protected EClass eStaticClass() {
		return NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public int getVariationId() {
		return variationId;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void setVariationId(int newVariationId) {
		int oldVariationId = variationId;
		variationId = newVariationId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.STRUCTURAL_VARIATION__VARIATION_ID, oldVariationId, variationId));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public EList<Property> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<Property>(Property.class, this, NoSQLSchemaPackage.STRUCTURAL_VARIATION__PROPERTIES);
		}
		return properties;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public long getCount() {
		return count;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void setCount(long newCount) {
		long oldCount = count;
		count = newCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.STRUCTURAL_VARIATION__COUNT, oldCount, count));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public long getFirstTimestamp() {
		return firstTimestamp;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void setFirstTimestamp(long newFirstTimestamp) {
		long oldFirstTimestamp = firstTimestamp;
		firstTimestamp = newFirstTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.STRUCTURAL_VARIATION__FIRST_TIMESTAMP, oldFirstTimestamp, firstTimestamp));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public long getLastTimestamp() {
		return lastTimestamp;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void setLastTimestamp(long newLastTimestamp) {
		long oldLastTimestamp = lastTimestamp;
		lastTimestamp = newLastTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.STRUCTURAL_VARIATION__LAST_TIMESTAMP, oldLastTimestamp, lastTimestamp));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public SchemaType getContainer() {
		if (eContainerFeatureID() != NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER) return null;
		return (SchemaType)eInternalContainer();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetContainer(SchemaType newContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainer, NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER, msgs);
		return msgs;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void setContainer(SchemaType newContainer) {
		if (newContainer != eInternalContainer() || (eContainerFeatureID() != NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER && newContainer != null)) {
			if (EcoreUtil.isAncestor(this, newContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainer != null)
				msgs = ((InternalEObject)newContainer).eInverseAdd(this, NoSQLSchemaPackage.SCHEMA_TYPE__VARIATIONS, SchemaType.class, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER, newContainer, newContainer));
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Property> getKey() {
		if (key == null) {
			key = new EObjectResolvingEList<Property>(Property.class, this, NoSQLSchemaPackage.STRUCTURAL_VARIATION__KEY);
		}
		return key;
	}

		/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainer((SchemaType)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER:
				return basicSetContainer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER:
				return eInternalContainer().eInverseRemove(this, NoSQLSchemaPackage.SCHEMA_TYPE__VARIATIONS, SchemaType.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__VARIATION_ID:
				return getVariationId();
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__PROPERTIES:
				return getProperties();
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__COUNT:
				return getCount();
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__FIRST_TIMESTAMP:
				return getFirstTimestamp();
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__LAST_TIMESTAMP:
				return getLastTimestamp();
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER:
				return getContainer();
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__KEY:
				return getKey();
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
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__VARIATION_ID:
				setVariationId((Integer)newValue);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__COUNT:
				setCount((Long)newValue);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__FIRST_TIMESTAMP:
				setFirstTimestamp((Long)newValue);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__LAST_TIMESTAMP:
				setLastTimestamp((Long)newValue);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER:
				setContainer((SchemaType)newValue);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__KEY:
				getKey().clear();
				getKey().addAll((Collection<? extends Property>)newValue);
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
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__VARIATION_ID:
				setVariationId(VARIATION_ID_EDEFAULT);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__PROPERTIES:
				getProperties().clear();
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__COUNT:
				setCount(COUNT_EDEFAULT);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__FIRST_TIMESTAMP:
				setFirstTimestamp(FIRST_TIMESTAMP_EDEFAULT);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__LAST_TIMESTAMP:
				setLastTimestamp(LAST_TIMESTAMP_EDEFAULT);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER:
				setContainer((SchemaType)null);
				return;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__KEY:
				getKey().clear();
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
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__VARIATION_ID:
				return variationId != VARIATION_ID_EDEFAULT;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__COUNT:
				return count != COUNT_EDEFAULT;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__FIRST_TIMESTAMP:
				return firstTimestamp != FIRST_TIMESTAMP_EDEFAULT;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__LAST_TIMESTAMP:
				return lastTimestamp != LAST_TIMESTAMP_EDEFAULT;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__CONTAINER:
				return getContainer() != null;
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION__KEY:
				return key != null && !key.isEmpty();
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
		result.append(" (variationId: ");
		result.append(variationId);
		result.append(", count: ");
		result.append(count);
		result.append(", firstTimestamp: ");
		result.append(firstTimestamp);
		result.append(", lastTimestamp: ");
		result.append(lastTimestamp);
		result.append(')');
		return result.toString();
	}

} //StructuralVariationImpl
