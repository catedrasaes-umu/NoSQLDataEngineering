/**
 */
package es.um.nosql.s13e.EntityDifferentiation.impl;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.PropertySpecImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.PropertySpecImpl#isNeedsTypeCheck <em>Needs Type Check</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertySpecImpl extends MinimalEObjectImpl.Container implements PropertySpec
{
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
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected PropertySpecImpl()
  {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  protected EClass eStaticClass()
  {
		return EntityDifferentiationPackage.Literals.PROPERTY_SPEC;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Property getProperty()
  {
		if (property != null && property.eIsProxy()) {
			InternalEObject oldProperty = (InternalEObject)property;
			property = (Property)eResolveProxy(oldProperty);
			if (property != oldProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntityDifferentiationPackage.PROPERTY_SPEC__PROPERTY, oldProperty, property));
			}
		}
		return property;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Property basicGetProperty()
  {
		return property;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setProperty(Property newProperty)
  {
		Property oldProperty = property;
		property = newProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.PROPERTY_SPEC__PROPERTY, oldProperty, property));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public boolean isNeedsTypeCheck()
  {
		return needsTypeCheck;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setNeedsTypeCheck(boolean newNeedsTypeCheck)
  {
		boolean oldNeedsTypeCheck = needsTypeCheck;
		needsTypeCheck = newNeedsTypeCheck;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.PROPERTY_SPEC__NEEDS_TYPE_CHECK, oldNeedsTypeCheck, needsTypeCheck));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
		switch (featureID) {
			case EntityDifferentiationPackage.PROPERTY_SPEC__PROPERTY:
				if (resolve) return getProperty();
				return basicGetProperty();
			case EntityDifferentiationPackage.PROPERTY_SPEC__NEEDS_TYPE_CHECK:
				return isNeedsTypeCheck();
		}
		return super.eGet(featureID, resolve, coreType);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void eSet(int featureID, Object newValue)
  {
		switch (featureID) {
			case EntityDifferentiationPackage.PROPERTY_SPEC__PROPERTY:
				setProperty((Property)newValue);
				return;
			case EntityDifferentiationPackage.PROPERTY_SPEC__NEEDS_TYPE_CHECK:
				setNeedsTypeCheck((Boolean)newValue);
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
  public void eUnset(int featureID)
  {
		switch (featureID) {
			case EntityDifferentiationPackage.PROPERTY_SPEC__PROPERTY:
				setProperty((Property)null);
				return;
			case EntityDifferentiationPackage.PROPERTY_SPEC__NEEDS_TYPE_CHECK:
				setNeedsTypeCheck(NEEDS_TYPE_CHECK_EDEFAULT);
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
  public boolean eIsSet(int featureID)
  {
		switch (featureID) {
			case EntityDifferentiationPackage.PROPERTY_SPEC__PROPERTY:
				return property != null;
			case EntityDifferentiationPackage.PROPERTY_SPEC__NEEDS_TYPE_CHECK:
				return needsTypeCheck != NEEDS_TYPE_CHECK_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public String toString()
  {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (needsTypeCheck: ");
		result.append(needsTypeCheck);
		result.append(')');
		return result.toString();
	}

} //PropertySpecImpl
