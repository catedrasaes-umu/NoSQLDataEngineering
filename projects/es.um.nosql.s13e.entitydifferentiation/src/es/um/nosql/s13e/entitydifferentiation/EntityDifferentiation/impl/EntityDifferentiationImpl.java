/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;

import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiffSpec;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage;

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
 * An implementation of the model object '<em><b>Entity Differentiation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl#getEntityDiffSpecs <em>Entity Diff Specs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl#getName <em>Name</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityDifferentiationImpl extends MinimalEObjectImpl.Container implements EntityDifferentiation
{
  /**
   * The cached value of the '{@link #getEntityDiffSpecs() <em>Entity Diff Specs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityDiffSpecs()
   * @generated
   * @ordered
   */
  protected EList<EntityDiffSpec> entityDiffSpecs;

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
   * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSchema()
   * @generated
   * @ordered
   */
  protected NoSQLSchema schema;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EntityDifferentiationImpl()
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
    return EntityDifferentiationPackage.Literals.ENTITY_DIFFERENTIATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EntityDiffSpec> getEntityDiffSpecs()
  {
    if (entityDiffSpecs == null)
    {
      entityDiffSpecs = new EObjectContainmentEList<EntityDiffSpec>(EntityDiffSpec.class, this, EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS);
    }
    return entityDiffSpecs;
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
      eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NoSQLSchema getSchema()
  {
    if (schema != null && schema.eIsProxy())
    {
      InternalEObject oldSchema = (InternalEObject)schema;
      schema = (NoSQLSchema)eResolveProxy(oldSchema);
      if (schema != oldSchema)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA, oldSchema, schema));
      }
    }
    return schema;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NoSQLSchema basicGetSchema()
  {
    return schema;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSchema(NoSQLSchema newSchema)
  {
    NoSQLSchema oldSchema = schema;
    schema = newSchema;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA, oldSchema, schema));
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
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
        return ((InternalEList<?>)getEntityDiffSpecs()).basicRemove(otherEnd, msgs);
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
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
        return getEntityDiffSpecs();
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
        return getName();
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA:
        if (resolve) return getSchema();
        return basicGetSchema();
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
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
        getEntityDiffSpecs().clear();
        getEntityDiffSpecs().addAll((Collection<? extends EntityDiffSpec>)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
        setName((String)newValue);
        return;
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA:
        setSchema((NoSQLSchema)newValue);
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
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
        getEntityDiffSpecs().clear();
        return;
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA:
        setSchema((NoSQLSchema)null);
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
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS:
        return entityDiffSpecs != null && !entityDiffSpecs.isEmpty();
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION__SCHEMA:
        return schema != null;
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

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //EntityDifferentiationImpl
