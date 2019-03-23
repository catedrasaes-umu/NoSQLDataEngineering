/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl;

import es.um.nosql.s13e.NoSQLSchema.EntityType;

import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff;

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
 * An implementation of the model object '<em><b>Entity Diff</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDiffImpl#getEntity <em>Entity</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDiffImpl#getVariationDiffs <em>Variation Diffs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDiffImpl#getCommonProps <em>Common Props</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityDiffImpl extends MinimalEObjectImpl.Container implements EntityDiff {
  /**
   * The cached value of the '{@link #getEntity() <em>Entity</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntity()
   * @generated
   * @ordered
   */
  protected EntityType entity;

  /**
   * The cached value of the '{@link #getVariationDiffs() <em>Variation Diffs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariationDiffs()
   * @generated
   * @ordered
   */
  protected EList<StructuralVariationDiff> variationDiffs;

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
  protected EntityDiffImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return EntityDifferentiationPackage.Literals.ENTITY_DIFF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EntityType getEntity() {
    if (entity != null && entity.eIsProxy()) {
      InternalEObject oldEntity = (InternalEObject)entity;
      entity = (EntityType)eResolveProxy(oldEntity);
      if (entity != oldEntity) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntityDifferentiationPackage.ENTITY_DIFF__ENTITY, oldEntity, entity));
      }
    }
    return entity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityType basicGetEntity() {
    return entity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setEntity(EntityType newEntity) {
    EntityType oldEntity = entity;
    entity = newEntity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.ENTITY_DIFF__ENTITY, oldEntity, entity));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<StructuralVariationDiff> getVariationDiffs() {
    if (variationDiffs == null) {
      variationDiffs = new EObjectContainmentEList<StructuralVariationDiff>(StructuralVariationDiff.class, this, EntityDifferentiationPackage.ENTITY_DIFF__VARIATION_DIFFS);
    }
    return variationDiffs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<PropertySpec> getCommonProps() {
    if (commonProps == null) {
      commonProps = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, EntityDifferentiationPackage.ENTITY_DIFF__COMMON_PROPS);
    }
    return commonProps;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case EntityDifferentiationPackage.ENTITY_DIFF__VARIATION_DIFFS:
        return ((InternalEList<?>)getVariationDiffs()).basicRemove(otherEnd, msgs);
      case EntityDifferentiationPackage.ENTITY_DIFF__COMMON_PROPS:
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
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case EntityDifferentiationPackage.ENTITY_DIFF__ENTITY:
        if (resolve) return getEntity();
        return basicGetEntity();
      case EntityDifferentiationPackage.ENTITY_DIFF__VARIATION_DIFFS:
        return getVariationDiffs();
      case EntityDifferentiationPackage.ENTITY_DIFF__COMMON_PROPS:
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
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case EntityDifferentiationPackage.ENTITY_DIFF__ENTITY:
        setEntity((EntityType)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_DIFF__VARIATION_DIFFS:
        getVariationDiffs().clear();
        getVariationDiffs().addAll((Collection<? extends StructuralVariationDiff>)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_DIFF__COMMON_PROPS:
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
  public void eUnset(int featureID) {
    switch (featureID) {
      case EntityDifferentiationPackage.ENTITY_DIFF__ENTITY:
        setEntity((EntityType)null);
        return;
      case EntityDifferentiationPackage.ENTITY_DIFF__VARIATION_DIFFS:
        getVariationDiffs().clear();
        return;
      case EntityDifferentiationPackage.ENTITY_DIFF__COMMON_PROPS:
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
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case EntityDifferentiationPackage.ENTITY_DIFF__ENTITY:
        return entity != null;
      case EntityDifferentiationPackage.ENTITY_DIFF__VARIATION_DIFFS:
        return variationDiffs != null && !variationDiffs.isEmpty();
      case EntityDifferentiationPackage.ENTITY_DIFF__COMMON_PROPS:
        return commonProps != null && !commonProps.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //EntityDiffImpl
