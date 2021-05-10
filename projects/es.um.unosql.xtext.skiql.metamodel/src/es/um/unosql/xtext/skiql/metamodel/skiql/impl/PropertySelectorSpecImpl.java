/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.PropertyTypeEnum;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;
import es.um.unosql.xtext.skiql.metamodel.skiql.SpecificityTypeEnum;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Selector Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySelectorSpecImpl#getType <em>Type</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertySelectorSpecImpl#getSpecifity <em>Specifity</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertySelectorSpecImpl extends MinimalEObjectImpl.Container implements PropertySelectorSpec {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final PropertyTypeEnum TYPE_EDEFAULT = PropertyTypeEnum.ALL;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected PropertyTypeEnum type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpecifity() <em>Specifity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecifity()
	 * @generated
	 * @ordered
	 */
	protected static final SpecificityTypeEnum SPECIFITY_EDEFAULT = SpecificityTypeEnum.ALL;

	/**
	 * The cached value of the '{@link #getSpecifity() <em>Specifity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecifity()
	 * @generated
	 * @ordered
	 */
	protected SpecificityTypeEnum specifity = SPECIFITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertySelectorSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.PROPERTY_SELECTOR_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PropertyTypeEnum getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(PropertyTypeEnum newType) {
		PropertyTypeEnum oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.PROPERTY_SELECTOR_SPEC__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpecificityTypeEnum getSpecifity() {
		return specifity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpecifity(SpecificityTypeEnum newSpecifity) {
		SpecificityTypeEnum oldSpecifity = specifity;
		specifity = newSpecifity == null ? SPECIFITY_EDEFAULT : newSpecifity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.PROPERTY_SELECTOR_SPEC__SPECIFITY, oldSpecifity, specifity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC__TYPE:
				return getType();
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC__SPECIFITY:
				return getSpecifity();
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
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC__TYPE:
				setType((PropertyTypeEnum)newValue);
				return;
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC__SPECIFITY:
				setSpecifity((SpecificityTypeEnum)newValue);
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
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC__SPECIFITY:
				setSpecifity(SPECIFITY_EDEFAULT);
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
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC__TYPE:
				return type != TYPE_EDEFAULT;
			case SkiqlPackage.PROPERTY_SELECTOR_SPEC__SPECIFITY:
				return specifity != SPECIFITY_EDEFAULT;
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
		result.append(" (type: ");
		result.append(type);
		result.append(", specifity: ");
		result.append(specifity);
		result.append(')');
		return result.toString();
	}

} //PropertySelectorSpecImpl
