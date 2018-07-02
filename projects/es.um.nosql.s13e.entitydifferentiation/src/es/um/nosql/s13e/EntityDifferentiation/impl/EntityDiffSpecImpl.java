/**
 */
package es.um.nosql.s13e.EntityDifferentiation.impl;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;

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
 * An implementation of the model object '<em><b>Entity Diff Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityDiffSpecImpl#getEntity <em>Entity</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityDiffSpecImpl#getEntityVariationProps <em>Entity Variation Props</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityDiffSpecImpl#getCommonProps <em>Common Props</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityDiffSpecImpl extends MinimalEObjectImpl.Container implements EntityDiffSpec
{
  /**
   * The cached value of the '{@link #getEntity() <em>Entity</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntity()
   * @generated
   * @ordered
   */
  protected Entity entity;

  /**
   * The cached value of the '{@link #getEntityVariationProps() <em>Entity Variation Props</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityVariationProps()
   * @generated
   * @ordered
   */
  protected EList<EntityVariationProp> entityVariationProps;

  /**
   * The cached value of the '{@link #getCommonProps() <em>Common Props</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommonProps()
   * @generated
   * @ordered
   */
  protected EList<PropertySpec> commonProps;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EntityDiffSpecImpl()
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
    return EntityDifferentiationPackage.Literals.ENTITY_DIFF_SPEC;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Entity getEntity()
  {
    if (entity != null && entity.eIsProxy())
    {
      InternalEObject oldEntity = (InternalEObject)entity;
      entity = (Entity)eResolveProxy(oldEntity);
      if (entity != oldEntity)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY, oldEntity, entity));
      }
    }
    return entity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Entity basicGetEntity()
  {
    return entity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntity(Entity newEntity)
  {
    Entity oldEntity = entity;
    entity = newEntity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY, oldEntity, entity));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EntityVariationProp> getEntityVariationProps()
  {
    if (entityVariationProps == null)
    {
      entityVariationProps = new EObjectContainmentEList<EntityVariationProp>(EntityVariationProp.class, this, EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VARIATION_PROPS);
    }
    return entityVariationProps;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PropertySpec> getCommonProps()
  {
    if (commonProps == null)
    {
      commonProps = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, EntityDifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS);
    }
    return commonProps;
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
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VARIATION_PROPS:
        return ((InternalEList<?>)getEntityVariationProps()).basicRemove(otherEnd, msgs);
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
        return ((InternalEList<?>)getCommonProps()).basicRemove(otherEnd, msgs);
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
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY:
        if (resolve) return getEntity();
        return basicGetEntity();
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VARIATION_PROPS:
        return getEntityVariationProps();
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
        return getCommonProps();
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
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY:
        setEntity((Entity)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VARIATION_PROPS:
        getEntityVariationProps().clear();
        getEntityVariationProps().addAll((Collection<? extends EntityVariationProp>)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
        getCommonProps().clear();
        getCommonProps().addAll((Collection<? extends PropertySpec>)newValue);
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
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY:
        setEntity((Entity)null);
        return;
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VARIATION_PROPS:
        getEntityVariationProps().clear();
        return;
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
        getCommonProps().clear();
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
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY:
        return entity != null;
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__ENTITY_VARIATION_PROPS:
        return entityVariationProps != null && !entityVariationProps.isEmpty();
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC__COMMON_PROPS:
        return commonProps != null && !commonProps.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //EntityDiffSpecImpl
