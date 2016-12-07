/**
 */
package es.um.nosql.schemainference.entitydifferentiation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
 * @generated
 */
public interface EntitydifferentiationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EntitydifferentiationFactory eINSTANCE = es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Entity Differentiation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Differentiation</em>'.
	 * @generated
	 */
	EntityDifferentiation createEntityDifferentiation();

	/**
	 * Returns a new object of class '<em>Entity Diff Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Diff Spec</em>'.
	 * @generated
	 */
	EntityDiffSpec createEntityDiffSpec();

	/**
	 * Returns a new object of class '<em>Property Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Spec</em>'.
	 * @generated
	 */
	PropertySpec createPropertySpec();

	/**
	 * Returns a new object of class '<em>Entity Version Prop</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Version Prop</em>'.
	 * @generated
	 */
	EntityVersionProp createEntityVersionProp();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EntitydifferentiationPackage getEntitydifferentiationPackage();

} //EntitydifferentiationFactory
