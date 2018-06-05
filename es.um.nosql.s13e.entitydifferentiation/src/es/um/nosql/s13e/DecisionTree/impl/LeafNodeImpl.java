/**
 */
package es.um.nosql.s13e.DecisionTree.impl;

import es.um.nosql.s13e.DecisionTree.DecisionTreePackage;
import es.um.nosql.s13e.DecisionTree.LeafNode;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Leaf Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.impl.LeafNodeImpl#getIdentifiedVersion <em>Identified Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LeafNodeImpl extends DecisionTreeNodeImpl implements LeafNode
{
  /**
   * The cached value of the '{@link #getIdentifiedVersion() <em>Identified Version</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifiedVersion()
   * @generated
   * @ordered
   */
  protected EntityVersion identifiedVersion;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LeafNodeImpl()
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
    return DecisionTreePackage.Literals.LEAF_NODE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityVersion getIdentifiedVersion()
  {
    if (identifiedVersion != null && identifiedVersion.eIsProxy())
    {
      InternalEObject oldIdentifiedVersion = (InternalEObject)identifiedVersion;
      identifiedVersion = (EntityVersion)eResolveProxy(oldIdentifiedVersion);
      if (identifiedVersion != oldIdentifiedVersion)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DecisionTreePackage.LEAF_NODE__IDENTIFIED_VERSION, oldIdentifiedVersion, identifiedVersion));
      }
    }
    return identifiedVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityVersion basicGetIdentifiedVersion()
  {
    return identifiedVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdentifiedVersion(EntityVersion newIdentifiedVersion)
  {
    EntityVersion oldIdentifiedVersion = identifiedVersion;
    identifiedVersion = newIdentifiedVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DecisionTreePackage.LEAF_NODE__IDENTIFIED_VERSION, oldIdentifiedVersion, identifiedVersion));
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
      case DecisionTreePackage.LEAF_NODE__IDENTIFIED_VERSION:
        if (resolve) return getIdentifiedVersion();
        return basicGetIdentifiedVersion();
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
      case DecisionTreePackage.LEAF_NODE__IDENTIFIED_VERSION:
        setIdentifiedVersion((EntityVersion)newValue);
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
      case DecisionTreePackage.LEAF_NODE__IDENTIFIED_VERSION:
        setIdentifiedVersion((EntityVersion)null);
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
      case DecisionTreePackage.LEAF_NODE__IDENTIFIED_VERSION:
        return identifiedVersion != null;
    }
    return super.eIsSet(featureID);
  }

} //LeafNodeImpl
