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
 *   <li>{@link es.um.nosql.schemainference.decisiontree.impl.IntermediateNodeImpl#getCheckedProperty <em>Checked Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IntermediateNodeImpl extends DecisionTreeNodeImpl implements IntermediateNode {
	/**
	 * The cached value of the '{@link #getCheckedProperty() <em>Checked Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckedProperty()
	 * @generated
	 * @ordered
	 */
	protected PropertySpec2 checkedProperty;

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
	public PropertySpec2 getCheckedProperty() {
		if (checkedProperty != null && checkedProperty.eIsProxy()) {
			InternalEObject oldCheckedProperty = (InternalEObject)checkedProperty;
			checkedProperty = (PropertySpec2)eResolveProxy(oldCheckedProperty);
			if (checkedProperty != oldCheckedProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DecisiontreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY, oldCheckedProperty, checkedProperty));
			}
		}
		return checkedProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertySpec2 basicGetCheckedProperty() {
		return checkedProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCheckedProperty(PropertySpec2 newCheckedProperty) {
		PropertySpec2 oldCheckedProperty = checkedProperty;
		checkedProperty = newCheckedProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DecisiontreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY, oldCheckedProperty, checkedProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY:
				if (resolve) return getCheckedProperty();
				return basicGetCheckedProperty();
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
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY:
				setCheckedProperty((PropertySpec2)newValue);
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
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY:
				setCheckedProperty((PropertySpec2)null);
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
			case DecisiontreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY:
				return checkedProperty != null;
		}
		return super.eIsSet(featureID);
	}

} //IntermediateNodeImpl
