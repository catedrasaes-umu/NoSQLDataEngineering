/**
 */
package Version_Diff.impl;

import Version_Diff.NoSQLDifferences;
import Version_Diff.TypeDifference;
import Version_Diff.Version_DiffPackage;

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
 * An implementation of the model object '<em><b>No SQL Differences</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Version_Diff.impl.NoSQLDifferencesImpl#getName <em>Name</em>}</li>
 *   <li>{@link Version_Diff.impl.NoSQLDifferencesImpl#getHasTypeDifferences <em>Has Type Differences</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NoSQLDifferencesImpl extends MinimalEObjectImpl.Container implements NoSQLDifferences {
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
	 * The cached value of the '{@link #getHasTypeDifferences() <em>Has Type Differences</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasTypeDifferences()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeDifference> hasTypeDifferences;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NoSQLDifferencesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Version_DiffPackage.Literals.NO_SQL_DIFFERENCES;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Version_DiffPackage.NO_SQL_DIFFERENCES__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeDifference> getHasTypeDifferences() {
		if (hasTypeDifferences == null) {
			hasTypeDifferences = new EObjectContainmentEList<TypeDifference>(TypeDifference.class, this, Version_DiffPackage.NO_SQL_DIFFERENCES__HAS_TYPE_DIFFERENCES);
		}
		return hasTypeDifferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Version_DiffPackage.NO_SQL_DIFFERENCES__HAS_TYPE_DIFFERENCES:
				return ((InternalEList<?>)getHasTypeDifferences()).basicRemove(otherEnd, msgs);
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
			case Version_DiffPackage.NO_SQL_DIFFERENCES__NAME:
				return getName();
			case Version_DiffPackage.NO_SQL_DIFFERENCES__HAS_TYPE_DIFFERENCES:
				return getHasTypeDifferences();
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
			case Version_DiffPackage.NO_SQL_DIFFERENCES__NAME:
				setName((String)newValue);
				return;
			case Version_DiffPackage.NO_SQL_DIFFERENCES__HAS_TYPE_DIFFERENCES:
				getHasTypeDifferences().clear();
				getHasTypeDifferences().addAll((Collection<? extends TypeDifference>)newValue);
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
			case Version_DiffPackage.NO_SQL_DIFFERENCES__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Version_DiffPackage.NO_SQL_DIFFERENCES__HAS_TYPE_DIFFERENCES:
				getHasTypeDifferences().clear();
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
			case Version_DiffPackage.NO_SQL_DIFFERENCES__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Version_DiffPackage.NO_SQL_DIFFERENCES__HAS_TYPE_DIFFERENCES:
				return hasTypeDifferences != null && !hasTypeDifferences.isEmpty();
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

} //NoSQLDifferencesImpl
