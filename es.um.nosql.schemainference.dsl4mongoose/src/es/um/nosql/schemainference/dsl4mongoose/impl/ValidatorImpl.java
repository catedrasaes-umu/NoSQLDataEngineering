/**
 */
package es.um.nosql.schemainference.dsl4mongoose.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage;
import es.um.nosql.schemainference.dsl4mongoose.Validator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Validator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.impl.ValidatorImpl#getValidatorName <em>Validator Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValidatorImpl extends FieldParameterImpl implements Validator {
	/**
	 * The default value of the '{@link #getValidatorName() <em>Validator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidatorName()
	 * @generated
	 * @ordered
	 */
	protected static final String VALIDATOR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidatorName() <em>Validator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidatorName()
	 * @generated
	 * @ordered
	 */
	protected String validatorName = VALIDATOR_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Dsl4mongoosePackage.Literals.VALIDATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValidatorName() {
		return validatorName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidatorName(String newValidatorName) {
		String oldValidatorName = validatorName;
		validatorName = newValidatorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Dsl4mongoosePackage.VALIDATOR__VALIDATOR_NAME, oldValidatorName, validatorName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Dsl4mongoosePackage.VALIDATOR__VALIDATOR_NAME:
				return getValidatorName();
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
			case Dsl4mongoosePackage.VALIDATOR__VALIDATOR_NAME:
				setValidatorName((String)newValue);
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
			case Dsl4mongoosePackage.VALIDATOR__VALIDATOR_NAME:
				setValidatorName(VALIDATOR_NAME_EDEFAULT);
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
			case Dsl4mongoosePackage.VALIDATOR__VALIDATOR_NAME:
				return VALIDATOR_NAME_EDEFAULT == null ? validatorName != null : !VALIDATOR_NAME_EDEFAULT.equals(validatorName);
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
		result.append(" (ValidatorName: ");
		result.append(validatorName);
		result.append(')');
		return result.toString();
	}

} //ValidatorImpl
