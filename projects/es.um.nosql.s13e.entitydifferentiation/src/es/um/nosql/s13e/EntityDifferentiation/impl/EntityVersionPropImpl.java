/**
 */
package es.um.nosql.s13e.EntityDifferentiation.impl;

import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;

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
 * An implementation of the model object '<em><b>Entity Version Prop</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityVersionPropImpl#getPropertySpecs <em>Property Specs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityVersionPropImpl#getEntityVersion <em>Entity Version</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityVersionPropImpl#getNotProps <em>Not Props</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityVersionPropImpl extends MinimalEObjectImpl.Container implements EntityVersionProp
{
  /**
   * The cached value of the '{@link #getPropertySpecs() <em>Property Specs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertySpecs()
   * @generated
   * @ordered
   */
  protected EList<PropertySpec> propertySpecs;

  /**
   * The cached value of the '{@link #getEntityVersion() <em>Entity Version</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityVersion()
   * @generated
   * @ordered
   */
  protected EntityVersion entityVersion;

  /**
   * The cached value of the '{@link #getNotProps() <em>Not Props</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNotProps()
   * @generated
   * @ordered
   */
  protected EList<PropertySpec> notProps;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EntityVersionPropImpl()
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
    return EntityDifferentiationPackage.Literals.ENTITY_VERSION_PROP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PropertySpec> getPropertySpecs()
  {
    if (propertySpecs == null)
    {
      propertySpecs = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, EntityDifferentiationPackage.ENTITY_VERSION_PROP__PROPERTY_SPECS);
    }
    return propertySpecs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityVersion getEntityVersion()
  {
    if (entityVersion != null && entityVersion.eIsProxy())
    {
      InternalEObject oldEntityVersion = (InternalEObject)entityVersion;
      entityVersion = (EntityVersion)eResolveProxy(oldEntityVersion);
      if (entityVersion != oldEntityVersion)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntityDifferentiationPackage.ENTITY_VERSION_PROP__ENTITY_VERSION, oldEntityVersion, entityVersion));
      }
    }
    return entityVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityVersion basicGetEntityVersion()
  {
    return entityVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntityVersion(EntityVersion newEntityVersion)
  {
    EntityVersion oldEntityVersion = entityVersion;
    entityVersion = newEntityVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.ENTITY_VERSION_PROP__ENTITY_VERSION, oldEntityVersion, entityVersion));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PropertySpec> getNotProps()
  {
    if (notProps == null)
    {
      notProps = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, EntityDifferentiationPackage.ENTITY_VERSION_PROP__NOT_PROPS);
    }
    return notProps;
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
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__PROPERTY_SPECS:
        return ((InternalEList<?>)getPropertySpecs()).basicRemove(otherEnd, msgs);
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__NOT_PROPS:
        return ((InternalEList<?>)getNotProps()).basicRemove(otherEnd, msgs);
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
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__PROPERTY_SPECS:
        return getPropertySpecs();
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__ENTITY_VERSION:
        if (resolve) return getEntityVersion();
        return basicGetEntityVersion();
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__NOT_PROPS:
        return getNotProps();
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
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__PROPERTY_SPECS:
        getPropertySpecs().clear();
        getPropertySpecs().addAll((Collection<? extends PropertySpec>)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__ENTITY_VERSION:
        setEntityVersion((EntityVersion)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__NOT_PROPS:
        getNotProps().clear();
        getNotProps().addAll((Collection<? extends PropertySpec>)newValue);
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
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__PROPERTY_SPECS:
        getPropertySpecs().clear();
        return;
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__ENTITY_VERSION:
        setEntityVersion((EntityVersion)null);
        return;
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__NOT_PROPS:
        getNotProps().clear();
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
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__PROPERTY_SPECS:
        return propertySpecs != null && !propertySpecs.isEmpty();
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__ENTITY_VERSION:
        return entityVersion != null;
      case EntityDifferentiationPackage.ENTITY_VERSION_PROP__NOT_PROPS:
        return notProps != null && !notProps.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //EntityVersionPropImpl
