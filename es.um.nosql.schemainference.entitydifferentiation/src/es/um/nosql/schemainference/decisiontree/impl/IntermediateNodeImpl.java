/**
 */
package es.um.nosql.schemainference.decisiontree.impl;

import es.um.nosql.schemainference.decisiontree.DecisiontreePackage;
import es.um.nosql.schemainference.decisiontree.IntermediateNode;

import es.um.nosql.schemainference.decisiontree.PropertySpec2;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intermediate Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.decisiontree.impl.IntermediateNodeImpl#getCheckHasProperty <em>Check Has Property</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.decisiontree.impl.IntermediateNodeImpl#getCheckHasNotProperty <em>Check Has Not Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IntermediateNodeImpl extends DecisionTreeNodeImpl implements IntermediateNode {
	/**
	 * The cached value of the '{@link #getCheckHasProperty() <em>Check Has Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckHasProperty()
	 * @generated
	 * @ordered
	 */
	protected PropertySpec2 checkHasProperty;
	/**
	 * The cached value of the '{@link #getCheckHasNotProperty() <em>Check Has Not Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckHasNotProperty()
	 * @generated
	 * @ordered
	 */
	protected PropertySpec2 checkHasNotProperty;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntermediateNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DecisiontreePackage.Literals.INTERMEDIATE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertySpec2 getCheckHasProperty() {
		if (checkHasProperty != null && checkHasProperty.eIsProxy()) {
			InternalEObject oldCheckHasProperty = (InternalEObject)checkHasProperty;
			checkHasProperty = (PropertySpec2)eResolveProxy(oldCheckHasProperty);
			if (checkHasProperty != oldCheckHasProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_PROPERTY, oldCheckHasProperty, checkHasProperty));
			}
		}
		return checkHasProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertySpec2 basicGetCheckHasProperty() {
		return checkHasProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCheckHasProperty(PropertySpec2 newCheckHasProperty) {
		PropertySpec2 oldCheckHasProperty = checkHasProperty;
		checkHasProperty = newCheckHasProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_PROPERTY, oldCheckHasProperty, checkHasProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertySpec2 getCheckHasNotProperty() {
		if (checkHasNotProperty != null && checkHasNotProperty.eIsProxy()) {
			InternalEObject oldCheckHasNotProperty = (InternalEObject)checkHasNotProperty;
			checkHasNotProperty = (PropertySpec2)eResolveProxy(oldCheckHasNotProperty);
			if (checkHasNotProperty != oldCheckHasNotProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_NOT_PROPERTY, oldCheckHasNotProperty, checkHasNotProperty));
			}
		}
		return checkHasNotProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertySpec2 basicGetCheckHasNotProperty() {
		return checkHasNotProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCheckHasNotProperty(PropertySpec2 newCheckHasNotProperty) {
		PropertySpec2 oldCheckHasNotProperty = checkHasNotProperty;
		checkHasNotProperty = newCheckHasNotProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_NOT_PROPERTY, oldCheckHasNotProperty, checkHasNotProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_PROPERTY:
				if (resolve) return getCheckHasProperty();
				return basicGetCheckHasProperty();
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_NOT_PROPERTY:
				if (resolve) return getCheckHasNotProperty();
				return basicGetCheckHasNotProperty();
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
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_PROPERTY:
				setCheckHasProperty((PropertySpec2)newValue);
				return;
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_NOT_PROPERTY:
				setCheckHasNotProperty((PropertySpec2)newValue);
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
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_PROPERTY:
				setCheckHasProperty((PropertySpec2)null);
				return;
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_NOT_PROPERTY:
				setCheckHasNotProperty((PropertySpec2)null);
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
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_PROPERTY:
				return checkHasProperty != null;
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECK_HAS_NOT_PROPERTY:
				return checkHasNotProperty != null;
		}
		return super.eIsSet(featureID);
	}

} //IntermediateNodeImpl
