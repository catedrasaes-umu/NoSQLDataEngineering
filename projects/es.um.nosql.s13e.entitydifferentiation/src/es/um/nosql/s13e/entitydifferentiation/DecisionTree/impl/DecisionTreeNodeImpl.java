/**
 */
package es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl;

import es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreeNode;
import es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeNodeImpl#getYesBranch <em>Yes Branch</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.DecisionTreeNodeImpl#getNoBranch <em>No Branch</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DecisionTreeNodeImpl extends MinimalEObjectImpl.Container implements DecisionTreeNode
{
  /**
   * The cached value of the '{@link #getYesBranch() <em>Yes Branch</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getYesBranch()
   * @generated
   * @ordered
   */
  protected DecisionTreeNode yesBranch;

  /**
   * The cached value of the '{@link #getNoBranch() <em>No Branch</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNoBranch()
   * @generated
   * @ordered
   */
  protected DecisionTreeNode noBranch;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DecisionTreeNodeImpl()
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
    return DecisionTreePackage.Literals.DECISION_TREE_NODE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DecisionTreeNode getYesBranch()
  {
    return yesBranch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetYesBranch(DecisionTreeNode newYesBranch, NotificationChain msgs)
  {
    DecisionTreeNode oldYesBranch = yesBranch;
    yesBranch = newYesBranch;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DecisionTreePackage.DECISION_TREE_NODE__YES_BRANCH, oldYesBranch, newYesBranch);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setYesBranch(DecisionTreeNode newYesBranch)
  {
    if (newYesBranch != yesBranch)
    {
      NotificationChain msgs = null;
      if (yesBranch != null)
        msgs = ((InternalEObject)yesBranch).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DecisionTreePackage.DECISION_TREE_NODE__YES_BRANCH, null, msgs);
      if (newYesBranch != null)
        msgs = ((InternalEObject)newYesBranch).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DecisionTreePackage.DECISION_TREE_NODE__YES_BRANCH, null, msgs);
      msgs = basicSetYesBranch(newYesBranch, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DecisionTreePackage.DECISION_TREE_NODE__YES_BRANCH, newYesBranch, newYesBranch));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DecisionTreeNode getNoBranch()
  {
    return noBranch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNoBranch(DecisionTreeNode newNoBranch, NotificationChain msgs)
  {
    DecisionTreeNode oldNoBranch = noBranch;
    noBranch = newNoBranch;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DecisionTreePackage.DECISION_TREE_NODE__NO_BRANCH, oldNoBranch, newNoBranch);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNoBranch(DecisionTreeNode newNoBranch)
  {
    if (newNoBranch != noBranch)
    {
      NotificationChain msgs = null;
      if (noBranch != null)
        msgs = ((InternalEObject)noBranch).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DecisionTreePackage.DECISION_TREE_NODE__NO_BRANCH, null, msgs);
      if (newNoBranch != null)
        msgs = ((InternalEObject)newNoBranch).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DecisionTreePackage.DECISION_TREE_NODE__NO_BRANCH, null, msgs);
      msgs = basicSetNoBranch(newNoBranch, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DecisionTreePackage.DECISION_TREE_NODE__NO_BRANCH, newNoBranch, newNoBranch));
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
      case DecisionTreePackage.DECISION_TREE_NODE__YES_BRANCH:
        return basicSetYesBranch(null, msgs);
      case DecisionTreePackage.DECISION_TREE_NODE__NO_BRANCH:
        return basicSetNoBranch(null, msgs);
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
      case DecisionTreePackage.DECISION_TREE_NODE__YES_BRANCH:
        return getYesBranch();
      case DecisionTreePackage.DECISION_TREE_NODE__NO_BRANCH:
        return getNoBranch();
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
    switch (featureID)
    {
      case DecisionTreePackage.DECISION_TREE_NODE__YES_BRANCH:
        setYesBranch((DecisionTreeNode)newValue);
        return;
      case DecisionTreePackage.DECISION_TREE_NODE__NO_BRANCH:
        setNoBranch((DecisionTreeNode)newValue);
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
      case DecisionTreePackage.DECISION_TREE_NODE__YES_BRANCH:
        setYesBranch((DecisionTreeNode)null);
        return;
      case DecisionTreePackage.DECISION_TREE_NODE__NO_BRANCH:
        setNoBranch((DecisionTreeNode)null);
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
      case DecisionTreePackage.DECISION_TREE_NODE__YES_BRANCH:
        return yesBranch != null;
      case DecisionTreePackage.DECISION_TREE_NODE__NO_BRANCH:
        return noBranch != null;
    }
    return super.eIsSet(featureID);
  }

} //DecisionTreeNodeImpl
