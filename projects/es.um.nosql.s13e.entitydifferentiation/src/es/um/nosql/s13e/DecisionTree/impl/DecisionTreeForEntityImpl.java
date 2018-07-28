/**
 */
package es.um.nosql.s13e.DecisionTree.impl;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.DecisionTree.DecisionTreeForEntity;
import es.um.nosql.s13e.DecisionTree.DecisionTreeNode;
import es.um.nosql.s13e.DecisionTree.DecisionTreePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>For Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.impl.DecisionTreeForEntityImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link es.um.nosql.s13e.DecisionTree.impl.DecisionTreeForEntityImpl#getEntity <em>Entity</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DecisionTreeForEntityImpl extends MinimalEObjectImpl.Container implements DecisionTreeForEntity
{
  /**
	 * The cached value of the '{@link #getRoot() <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getRoot()
	 * @generated
	 * @ordered
	 */
  protected DecisionTreeNode root;

  /**
	 * The cached value of the '{@link #getEntity() <em>Entity</em>}' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getEntity()
	 * @generated
	 * @ordered
	 */
  protected EntityClass entity;

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected DecisionTreeForEntityImpl()
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
		return DecisionTreePackage.Literals.DECISION_TREE_FOR_ENTITY;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public DecisionTreeNode getRoot()
  {
		return root;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetRoot(DecisionTreeNode newRoot, NotificationChain msgs)
  {
		DecisionTreeNode oldRoot = root;
		root = newRoot;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ROOT, oldRoot, newRoot);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setRoot(DecisionTreeNode newRoot)
  {
		if (newRoot != root) {
			NotificationChain msgs = null;
			if (root != null)
				msgs = ((InternalEObject)root).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ROOT, null, msgs);
			if (newRoot != null)
				msgs = ((InternalEObject)newRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ROOT, null, msgs);
			msgs = basicSetRoot(newRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ROOT, newRoot, newRoot));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EntityClass getEntity()
  {
		if (entity != null && entity.eIsProxy()) {
			InternalEObject oldEntity = (InternalEObject)entity;
			entity = (EntityClass)eResolveProxy(oldEntity);
			if (entity != oldEntity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ENTITY, oldEntity, entity));
			}
		}
		return entity;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EntityClass basicGetEntity()
  {
		return entity;
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntity(EntityClass newEntity) {
		EntityClass oldEntity = entity;
		entity = newEntity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ENTITY, oldEntity, entity));
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
			case DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ROOT:
				return basicSetRoot(null, msgs);
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
			case DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ROOT:
				return getRoot();
			case DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ENTITY:
				if (resolve) return getEntity();
				return basicGetEntity();
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
			case DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ROOT:
				setRoot((DecisionTreeNode)newValue);
				return;
			case DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ENTITY:
				setEntity((EntityClass)newValue);
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
			case DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ROOT:
				setRoot((DecisionTreeNode)null);
				return;
			case DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ENTITY:
				setEntity((EntityClass)null);
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
			case DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ROOT:
				return root != null;
			case DecisionTreePackage.DECISION_TREE_FOR_ENTITY__ENTITY:
				return entity != null;
		}
		return super.eIsSet(featureID);
	}

} //DecisionTreeForEntityImpl
