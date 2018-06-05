/**
 */
package es.um.nosql.s13e.DecisionTree.impl;

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

import es.um.nosql.s13e.DecisionTree.DecisionTreeForEntity;
import es.um.nosql.s13e.DecisionTree.DecisionTreePackage;
import es.um.nosql.s13e.DecisionTree.DecisionTrees;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Decision Trees</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.impl.DecisionTreesImpl#getTrees <em>Trees</em>}</li>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.impl.DecisionTreesImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DecisionTreesImpl extends MinimalEObjectImpl.Container implements DecisionTrees
{
  /**
   * The cached value of the '{@link #getTrees() <em>Trees</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTrees()
   * @generated
   * @ordered
   */
  protected EList<DecisionTreeForEntity> trees;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DecisionTreesImpl()
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
    return DecisionTreePackage.Literals.DECISION_TREES;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DecisionTreeForEntity> getTrees()
  {
    if (trees == null)
    {
      trees = new EObjectContainmentEList<DecisionTreeForEntity>(DecisionTreeForEntity.class, this, DecisionTreePackage.DECISION_TREES__TREES);
    }
    return trees;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DecisionTreePackage.DECISION_TREES__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case DecisionTreePackage.DECISION_TREES__TREES:
        return ((InternalEList<?>)getTrees()).basicRemove(otherEnd, msgs);
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
    switch (featureID)
    {
      case DecisionTreePackage.DECISION_TREES__TREES:
        return getTrees();
      case DecisionTreePackage.DECISION_TREES__NAME:
        return getName();
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
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case DecisionTreePackage.DECISION_TREES__TREES:
        getTrees().clear();
        getTrees().addAll((Collection<? extends DecisionTreeForEntity>)newValue);
        return;
      case DecisionTreePackage.DECISION_TREES__NAME:
        setName((String)newValue);
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
    switch (featureID)
    {
      case DecisionTreePackage.DECISION_TREES__TREES:
        getTrees().clear();
        return;
      case DecisionTreePackage.DECISION_TREES__NAME:
        setName(NAME_EDEFAULT);
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
    switch (featureID)
    {
      case DecisionTreePackage.DECISION_TREES__TREES:
        return trees != null && !trees.isEmpty();
      case DecisionTreePackage.DECISION_TREES__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //DecisionTreesImpl
