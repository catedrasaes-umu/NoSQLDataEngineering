/**
 */
package Version_Diff.impl;

import Version_Diff.FieldType;
import Version_Diff.TypeHint;
import Version_Diff.Version_DiffPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Hint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Version_Diff.impl.TypeHintImpl#getWithName <em>With Name</em>}</li>
 *   <li>{@link Version_Diff.impl.TypeHintImpl#getWithType <em>With Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TypeHintImpl extends MinimalEObjectImpl.Container implements TypeHint {
	/**
	 * The default value of the '{@link #getWithName() <em>With Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWithName()
	 * @generated
	 * @ordered
	 */
	protected static final String WITH_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWithName() <em>With Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWithName()
	 * @generated
	 * @ordered
	 */
	protected String withName = WITH_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWithType() <em>With Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWithType()
	 * @generated
	 * @ordered
	 */
	protected FieldType withType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeHintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Version_DiffPackage.Literals.TYPE_HINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWithName() {
		return withName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWithName(String newWithName) {
		String oldWithName = withName;
		withName = newWithName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Version_DiffPackage.TYPE_HINT__WITH_NAME, oldWithName, withName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldType getWithType() {
		return withType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWithType(FieldType newWithType, NotificationChain msgs) {
		FieldType oldWithType = withType;
		withType = newWithType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Version_DiffPackage.TYPE_HINT__WITH_TYPE, oldWithType, newWithType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWithType(FieldType newWithType) {
		if (newWithType != withType) {
			NotificationChain msgs = null;
			if (withType != null)
				msgs = ((InternalEObject)withType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Version_DiffPackage.TYPE_HINT__WITH_TYPE, null, msgs);
			if (newWithType != null)
				msgs = ((InternalEObject)newWithType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Version_DiffPackage.TYPE_HINT__WITH_TYPE, null, msgs);
			msgs = basicSetWithType(newWithType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Version_DiffPackage.TYPE_HINT__WITH_TYPE, newWithType, newWithType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Version_DiffPackage.TYPE_HINT__WITH_TYPE:
				return basicSetWithType(null, msgs);
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
			case Version_DiffPackage.TYPE_HINT__WITH_NAME:
				return getWithName();
			case Version_DiffPackage.TYPE_HINT__WITH_TYPE:
				return getWithType();
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
			case Version_DiffPackage.TYPE_HINT__WITH_NAME:
				setWithName((String)newValue);
				return;
			case Version_DiffPackage.TYPE_HINT__WITH_TYPE:
				setWithType((FieldType)newValue);
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
			case Version_DiffPackage.TYPE_HINT__WITH_NAME:
				setWithName(WITH_NAME_EDEFAULT);
				return;
			case Version_DiffPackage.TYPE_HINT__WITH_TYPE:
				setWithType((FieldType)null);
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
			case Version_DiffPackage.TYPE_HINT__WITH_NAME:
				return WITH_NAME_EDEFAULT == null ? withName != null : !WITH_NAME_EDEFAULT.equals(withName);
			case Version_DiffPackage.TYPE_HINT__WITH_TYPE:
				return withType != null;
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
		result.append(" (withName: ");
		result.append(withName);
		result.append(')');
		return result.toString();
	}

} //TypeHintImpl
