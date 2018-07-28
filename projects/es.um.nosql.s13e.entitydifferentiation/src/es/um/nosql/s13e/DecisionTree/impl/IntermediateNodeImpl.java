/**
 */
package es.um.nosql.s13e.DecisionTree.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import es.um.nosql.s13e.DecisionTree.DecisionTreePackage;
import es.um.nosql.s13e.DecisionTree.IntermediateNode;
import es.um.nosql.s13e.DecisionTree.PropertySpec2;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intermediate Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.impl.IntermediateNodeImpl#getCheckedProperty <em>Checked Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IntermediateNodeImpl extends DecisionTreeNodeImpl implements IntermediateNode
{
  /**
	 * The cached value of the '{@link #getCheckedProperty() <em>Checked Property</em>}' containment reference.
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
  protected IntermediateNodeImpl()
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
		return DecisionTreePackage.Literals.INTERMEDIATE_NODE;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public PropertySpec2 getCheckedProperty()
  {
		return checkedProperty;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetCheckedProperty(PropertySpec2 newCheckedProperty, NotificationChain msgs)
  {
		PropertySpec2 oldCheckedProperty = checkedProperty;
		checkedProperty = newCheckedProperty;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DecisionTreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY, oldCheckedProperty, newCheckedProperty);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setCheckedProperty(PropertySpec2 newCheckedProperty)
  {
		if (newCheckedProperty != checkedProperty) {
			NotificationChain msgs = null;
			if (checkedProperty != null)
				msgs = ((InternalEObject)checkedProperty).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DecisionTreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY, null, msgs);
			if (newCheckedProperty != null)
				msgs = ((InternalEObject)newCheckedProperty).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DecisionTreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY, null, msgs);
			msgs = basicSetCheckedProperty(newCheckedProperty, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DecisionTreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY, newCheckedProperty, newCheckedProperty));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
		switch (featureID) {
			case DecisionTreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY:
				return basicSetCheckedProperty(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case DecisionTreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY:
				return getCheckedProperty();
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
			case DecisionTreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY:
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
  public void eUnset(int featureID)
  {
		switch (featureID) {
			case DecisionTreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY:
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
  public boolean eIsSet(int featureID)
  {
		switch (featureID) {
			case DecisionTreePackage.INTERMEDIATE_NODE__CHECKED_PROPERTY:
				return checkedProperty != null;
		}
		return super.eIsSet(featureID);
	}

} //IntermediateNodeImpl
