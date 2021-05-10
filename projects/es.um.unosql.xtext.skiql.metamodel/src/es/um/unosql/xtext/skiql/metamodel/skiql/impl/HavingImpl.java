/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.Having;
import es.um.unosql.xtext.skiql.metamodel.skiql.HavingType;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Having</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingImpl#isNegative <em>Negative</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingImpl#getHavingType <em>Having Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HavingImpl extends MinimalEObjectImpl.Container implements Having {
	/**
	 * The default value of the '{@link #isNegative() <em>Negative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNegative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEGATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNegative() <em>Negative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNegative()
	 * @generated
	 * @ordered
	 */
	protected boolean negative = NEGATIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHavingType() <em>Having Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHavingType()
	 * @generated
	 * @ordered
	 */
	protected HavingType havingType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HavingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.HAVING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isNegative() {
		return negative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNegative(boolean newNegative) {
		boolean oldNegative = negative;
		negative = newNegative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.HAVING__NEGATIVE, oldNegative, negative));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HavingType getHavingType() {
		return havingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHavingType(HavingType newHavingType, NotificationChain msgs) {
		HavingType oldHavingType = havingType;
		havingType = newHavingType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.HAVING__HAVING_TYPE, oldHavingType, newHavingType);
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
	public void setHavingType(HavingType newHavingType) {
		if (newHavingType != havingType) {
			NotificationChain msgs = null;
			if (havingType != null)
				msgs = ((InternalEObject)havingType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.HAVING__HAVING_TYPE, null, msgs);
			if (newHavingType != null)
				msgs = ((InternalEObject)newHavingType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.HAVING__HAVING_TYPE, null, msgs);
			msgs = basicSetHavingType(newHavingType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.HAVING__HAVING_TYPE, newHavingType, newHavingType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.HAVING__HAVING_TYPE:
				return basicSetHavingType(null, msgs);
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
			case SkiqlPackage.HAVING__NEGATIVE:
				return isNegative();
			case SkiqlPackage.HAVING__HAVING_TYPE:
				return getHavingType();
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
			case SkiqlPackage.HAVING__NEGATIVE:
				setNegative((Boolean)newValue);
				return;
			case SkiqlPackage.HAVING__HAVING_TYPE:
				setHavingType((HavingType)newValue);
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
			case SkiqlPackage.HAVING__NEGATIVE:
				setNegative(NEGATIVE_EDEFAULT);
				return;
			case SkiqlPackage.HAVING__HAVING_TYPE:
				setHavingType((HavingType)null);
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
			case SkiqlPackage.HAVING__NEGATIVE:
				return negative != NEGATIVE_EDEFAULT;
			case SkiqlPackage.HAVING__HAVING_TYPE:
				return havingType != null;
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
		result.append(" (negative: ");
		result.append(negative);
		result.append(')');
		return result.toString();
	}

} //HavingImpl
