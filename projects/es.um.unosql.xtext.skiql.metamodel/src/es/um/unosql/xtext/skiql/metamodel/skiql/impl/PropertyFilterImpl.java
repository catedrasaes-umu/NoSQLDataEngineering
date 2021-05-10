/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.PropertyFilter;
import es.um.unosql.xtext.skiql.metamodel.skiql.PropertySelectorSpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Filter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.PropertyFilterImpl#getPropertySelectorSpecs <em>Property Selector Specs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyFilterImpl extends OperationImpl implements PropertyFilter {
	/**
	 * The cached value of the '{@link #getPropertySelectorSpecs() <em>Property Selector Specs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertySelectorSpecs()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertySelectorSpec> propertySelectorSpecs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyFilterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.PROPERTY_FILTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PropertySelectorSpec> getPropertySelectorSpecs() {
		if (propertySelectorSpecs == null) {
			propertySelectorSpecs = new EObjectContainmentEList<PropertySelectorSpec>(PropertySelectorSpec.class, this, SkiqlPackage.PROPERTY_FILTER__PROPERTY_SELECTOR_SPECS);
		}
		return propertySelectorSpecs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.PROPERTY_FILTER__PROPERTY_SELECTOR_SPECS:
				return ((InternalEList<?>)getPropertySelectorSpecs()).basicRemove(otherEnd, msgs);
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
			case SkiqlPackage.PROPERTY_FILTER__PROPERTY_SELECTOR_SPECS:
				return getPropertySelectorSpecs();
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
			case SkiqlPackage.PROPERTY_FILTER__PROPERTY_SELECTOR_SPECS:
				getPropertySelectorSpecs().clear();
				getPropertySelectorSpecs().addAll((Collection<? extends PropertySelectorSpec>)newValue);
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
			case SkiqlPackage.PROPERTY_FILTER__PROPERTY_SELECTOR_SPECS:
				getPropertySelectorSpecs().clear();
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
			case SkiqlPackage.PROPERTY_FILTER__PROPERTY_SELECTOR_SPECS:
				return propertySelectorSpecs != null && !propertySelectorSpecs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PropertyFilterImpl
