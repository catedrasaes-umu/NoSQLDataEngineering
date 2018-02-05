/**
 */
package es.um.nosql.s13e.decisiontree.impl;

import es.um.nosql.s13e.NoSQLSchema.Property;

import es.um.nosql.s13e.decisiontree.DecisiontreePackage;
import es.um.nosql.s13e.decisiontree.PropertySpec2;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Spec2</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.decisiontree.impl.PropertySpec2Impl#isNeedsTypeCheck <em>Needs Type Check</em>}</li>
 *   <li>{@link es.um.nosql.s13e.decisiontree.impl.PropertySpec2Impl#getProperty <em>Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertySpec2Impl extends MinimalEObjectImpl.Container implements PropertySpec2 {
	/**
	 * The default value of the '{@link #isNeedsTypeCheck() <em>Needs Type Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedsTypeCheck()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEEDS_TYPE_CHECK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNeedsTypeCheck() <em>Needs Type Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedsTypeCheck()
	 * @generated
	 * @ordered
	 */
	protected boolean needsTypeCheck = NEEDS_TYPE_CHECK_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected Property property;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertySpec2Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DecisiontreePackage.Literals.PROPERTY_SPEC2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNeedsTypeCheck() {
		return needsTypeCheck;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNeedsTypeCheck(boolean newNeedsTypeCheck) {
		boolean oldNeedsTypeCheck = needsTypeCheck;
		needsTypeCheck = newNeedsTypeCheck;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DecisiontreePackage.PROPERTY_SPEC2__NEEDS_TYPE_CHECK, oldNeedsTypeCheck, needsTypeCheck));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property getProperty() {
		if (property != null && property.eIsProxy()) {
			InternalEObject oldProperty = (InternalEObject)property;
			property = (Property)eResolveProxy(oldProperty);
			if (property != oldProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DecisiontreePackage.PROPERTY_SPEC2__PROPERTY, oldProperty, property));
			}
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetProperty() {
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(Property newProperty) {
		Property oldProperty = property;
		property = newProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DecisiontreePackage.PROPERTY_SPEC2__PROPERTY, oldProperty, property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DecisiontreePackage.PROPERTY_SPEC2__NEEDS_TYPE_CHECK:
				return isNeedsTypeCheck();
			case DecisiontreePackage.PROPERTY_SPEC2__PROPERTY:
				if (resolve) return getProperty();
				return basicGetProperty();
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
			case DecisiontreePackage.PROPERTY_SPEC2__NEEDS_TYPE_CHECK:
				setNeedsTypeCheck((Boolean)newValue);
				return;
			case DecisiontreePackage.PROPERTY_SPEC2__PROPERTY:
				setProperty((Property)newValue);
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
			case DecisiontreePackage.PROPERTY_SPEC2__NEEDS_TYPE_CHECK:
				setNeedsTypeCheck(NEEDS_TYPE_CHECK_EDEFAULT);
				return;
			case DecisiontreePackage.PROPERTY_SPEC2__PROPERTY:
				setProperty((Property)null);
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
			case DecisiontreePackage.PROPERTY_SPEC2__NEEDS_TYPE_CHECK:
				return needsTypeCheck != NEEDS_TYPE_CHECK_EDEFAULT;
			case DecisiontreePackage.PROPERTY_SPEC2__PROPERTY:
				return property != null;
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
		result.append(" (needsTypeCheck: ");
		result.append(needsTypeCheck);
		result.append(')');
		return result.toString();
	}

} //PropertySpec2Impl
