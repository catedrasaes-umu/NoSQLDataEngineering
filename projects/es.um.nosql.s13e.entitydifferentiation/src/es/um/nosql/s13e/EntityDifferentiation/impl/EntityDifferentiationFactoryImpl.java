/**
 */
package es.um.nosql.s13e.EntityDifferentiation.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import es.um.nosql.s13e.EntityDifferentiation.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EntityDifferentiationFactoryImpl extends EFactoryImpl implements EntityDifferentiationFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EntityDifferentiationFactory init()
  {
    try
    {
      EntityDifferentiationFactory theEntityDifferentiationFactory = (EntityDifferentiationFactory)EPackage.Registry.INSTANCE.getEFactory(EntityDifferentiationPackage.eNS_URI);
      if (theEntityDifferentiationFactory != null)
      {
        return theEntityDifferentiationFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new EntityDifferentiationFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityDifferentiationFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION: return createEntityDifferentiation();
      case EntityDifferentiationPackage.ENTITY_DIFF_SPEC: return createEntityDiffSpec();
      case EntityDifferentiationPackage.PROPERTY_SPEC: return createPropertySpec();
      case EntityDifferentiationPackage.ENTITY_VARIATION_PROP: return createEntityVariationProp();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityDifferentiation createEntityDifferentiation()
  {
    EntityDifferentiationImpl entityDifferentiation = new EntityDifferentiationImpl();
    return entityDifferentiation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityDiffSpec createEntityDiffSpec()
  {
    EntityDiffSpecImpl entityDiffSpec = new EntityDiffSpecImpl();
    return entityDiffSpec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertySpec createPropertySpec()
  {
    PropertySpecImpl propertySpec = new PropertySpecImpl();
    return propertySpec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityVariationProp createEntityVariationProp()
  {
    EntityVariationPropImpl entityVariationProp = new EntityVariationPropImpl();
    return entityVariationProp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityDifferentiationPackage getEntityDifferentiationPackage()
  {
    return (EntityDifferentiationPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static EntityDifferentiationPackage getPackage()
  {
    return EntityDifferentiationPackage.eINSTANCE;
  }

} //EntityDifferentiationFactoryImpl
