/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl;

import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EntityDifferentiationFactoryImpl extends EFactoryImpl implements EntityDifferentiationFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EntityDifferentiationFactory init() {
    try {
      EntityDifferentiationFactory theEntityDifferentiationFactory = (EntityDifferentiationFactory)EPackage.Registry.INSTANCE.getEFactory(EntityDifferentiationPackage.eNS_URI);
      if (theEntityDifferentiationFactory != null) {
        return theEntityDifferentiationFactory;
      }
    }
    catch (Exception exception) {
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
  public EntityDifferentiationFactoryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case EntityDifferentiationPackage.ENTITY_DIFFERENTIATION: return createEntityDifferentiation();
      case EntityDifferentiationPackage.ENTITY_DIFF: return createEntityDiff();
      case EntityDifferentiationPackage.PROPERTY_SPEC: return createPropertySpec();
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF: return createStructuralVariationDiff();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EntityDifferentiation createEntityDifferentiation() {
    EntityDifferentiationImpl entityDifferentiation = new EntityDifferentiationImpl();
    return entityDifferentiation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EntityDiff createEntityDiff() {
    EntityDiffImpl entityDiff = new EntityDiffImpl();
    return entityDiff;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public PropertySpec createPropertySpec() {
    PropertySpecImpl propertySpec = new PropertySpecImpl();
    return propertySpec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public StructuralVariationDiff createStructuralVariationDiff() {
    StructuralVariationDiffImpl structuralVariationDiff = new StructuralVariationDiffImpl();
    return structuralVariationDiff;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EntityDifferentiationPackage getEntityDifferentiationPackage() {
    return (EntityDifferentiationPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static EntityDifferentiationPackage getPackage() {
    return EntityDifferentiationPackage.eINSTANCE;
  }

} //EntityDifferentiationFactoryImpl
