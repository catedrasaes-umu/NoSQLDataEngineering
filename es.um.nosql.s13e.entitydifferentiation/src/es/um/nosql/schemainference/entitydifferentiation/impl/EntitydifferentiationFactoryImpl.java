/**
 */
package es.um.nosql.s13e.entitydifferentiation.impl;

import es.um.nosql.s13e.entitydifferentiation.*;

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
public class EntitydifferentiationFactoryImpl extends EFactoryImpl implements EntitydifferentiationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EntitydifferentiationFactory init() {
		try {
			EntitydifferentiationFactory theEntitydifferentiationFactory = (EntitydifferentiationFactory)EPackage.Registry.INSTANCE.getEFactory(EntitydifferentiationPackage.eNS_URI);
			if (theEntitydifferentiationFactory != null) {
				return theEntitydifferentiationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EntitydifferentiationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntitydifferentiationFactoryImpl() {
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
			case EntitydifferentiationPackage.ENTITY_DIFFERENTIATION: return createEntityDifferentiation();
			case EntitydifferentiationPackage.ENTITY_DIFF_SPEC: return createEntityDiffSpec();
			case EntitydifferentiationPackage.PROPERTY_SPEC: return createPropertySpec();
			case EntitydifferentiationPackage.ENTITY_VERSION_PROP: return createEntityVersionProp();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityDifferentiation createEntityDifferentiation() {
		EntityDifferentiationImpl entityDifferentiation = new EntityDifferentiationImpl();
		return entityDifferentiation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityDiffSpec createEntityDiffSpec() {
		EntityDiffSpecImpl entityDiffSpec = new EntityDiffSpecImpl();
		return entityDiffSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertySpec createPropertySpec() {
		PropertySpecImpl propertySpec = new PropertySpecImpl();
		return propertySpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityVersionProp createEntityVersionProp() {
		EntityVersionPropImpl entityVersionProp = new EntityVersionPropImpl();
		return entityVersionProp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntitydifferentiationPackage getEntitydifferentiationPackage() {
		return (EntitydifferentiationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EntitydifferentiationPackage getPackage() {
		return EntitydifferentiationPackage.eINSTANCE;
	}

} //EntitydifferentiationFactoryImpl
