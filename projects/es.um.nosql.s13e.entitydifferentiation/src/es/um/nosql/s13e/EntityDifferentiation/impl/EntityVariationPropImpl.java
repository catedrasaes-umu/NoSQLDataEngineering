/**
 */
package es.um.nosql.s13e.EntityDifferentiation.impl;

import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
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
 * An implementation of the model object '<em><b>Entity Variation Prop</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityVariationPropImpl#getPropertySpecs <em>Property Specs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityVariationPropImpl#getEntityVariation <em>Entity Variation</em>}</li>
 *   <li>{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityVariationPropImpl#getNotProps <em>Not Props</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityVariationPropImpl extends MinimalEObjectImpl.Container implements EntityVariationProp
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
   * The cached value of the '{@link #getEntityVariation() <em>Entity Variation</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityVariation()
   * @generated
   * @ordered
   */
  protected EntityVariation entityVariation;

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
  protected EntityVariationPropImpl()
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
    return EntityDifferentiationPackage.Literals.ENTITY_VARIATION_PROP;
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
      propertySpecs = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, EntityDifferentiationPackage.ENTITY_VARIATION_PROP__PROPERTY_SPECS);
    }
    return propertySpecs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityVariation getEntityVariation()
  {
    if (entityVariation != null && entityVariation.eIsProxy())
    {
      InternalEObject oldEntityVariation = (InternalEObject)entityVariation;
      entityVariation = (EntityVariation)eResolveProxy(oldEntityVariation);
      if (entityVariation != oldEntityVariation)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntityDifferentiationPackage.ENTITY_VARIATION_PROP__ENTITY_VARIATION, oldEntityVariation, entityVariation));
      }
    }
    return entityVariation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityVariation basicGetEntityVariation()
  {
    return entityVariation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntityVariation(EntityVariation newEntityVariation)
  {
    EntityVariation oldEntityVariation = entityVariation;
    entityVariation = newEntityVariation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.ENTITY_VARIATION_PROP__ENTITY_VARIATION, oldEntityVariation, entityVariation));
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
      notProps = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, EntityDifferentiationPackage.ENTITY_VARIATION_PROP__NOT_PROPS);
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
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__PROPERTY_SPECS:
        return ((InternalEList<?>)getPropertySpecs()).basicRemove(otherEnd, msgs);
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__NOT_PROPS:
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
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__PROPERTY_SPECS:
        return getPropertySpecs();
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__ENTITY_VARIATION:
        if (resolve) return getEntityVariation();
        return basicGetEntityVariation();
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__NOT_PROPS:
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
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__PROPERTY_SPECS:
        getPropertySpecs().clear();
        getPropertySpecs().addAll((Collection<? extends PropertySpec>)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__ENTITY_VARIATION:
        setEntityVariation((EntityVariation)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__NOT_PROPS:
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
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__PROPERTY_SPECS:
        getPropertySpecs().clear();
        return;
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__ENTITY_VARIATION:
        setEntityVariation((EntityVariation)null);
        return;
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__NOT_PROPS:
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
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__PROPERTY_SPECS:
        return propertySpecs != null && !propertySpecs.isEmpty();
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__ENTITY_VARIATION:
        return entityVariation != null;
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP__NOT_PROPS:
        return notProps != null && !notProps.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //EntityVariationPropImpl
