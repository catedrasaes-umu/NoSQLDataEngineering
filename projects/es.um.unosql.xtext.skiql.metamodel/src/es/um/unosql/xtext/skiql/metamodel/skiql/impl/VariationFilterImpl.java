/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.PropertySpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;
import es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter;

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
 * An implementation of the model object '<em><b>Variation Filter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.VariationFilterImpl#getPropertySpecs <em>Property Specs</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.VariationFilterImpl#isOnly <em>Only</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariationFilterImpl extends MinimalEObjectImpl.Container implements VariationFilter {
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
	 * The default value of the '{@link #isOnly() <em>Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOnly() <em>Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnly()
	 * @generated
	 * @ordered
	 */
	protected boolean only = ONLY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariationFilterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.VARIATION_FILTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PropertySpec> getPropertySpecs() {
		if (propertySpecs == null) {
			propertySpecs = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, SkiqlPackage.VARIATION_FILTER__PROPERTY_SPECS);
		}
		return propertySpecs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOnly() {
		return only;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOnly(boolean newOnly) {
		boolean oldOnly = only;
		only = newOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.VARIATION_FILTER__ONLY, oldOnly, only));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.VARIATION_FILTER__PROPERTY_SPECS:
				return ((InternalEList<?>)getPropertySpecs()).basicRemove(otherEnd, msgs);
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
			case SkiqlPackage.VARIATION_FILTER__PROPERTY_SPECS:
				return getPropertySpecs();
			case SkiqlPackage.VARIATION_FILTER__ONLY:
				return isOnly();
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
			case SkiqlPackage.VARIATION_FILTER__PROPERTY_SPECS:
				getPropertySpecs().clear();
				getPropertySpecs().addAll((Collection<? extends PropertySpec>)newValue);
				return;
			case SkiqlPackage.VARIATION_FILTER__ONLY:
				setOnly((Boolean)newValue);
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
			case SkiqlPackage.VARIATION_FILTER__PROPERTY_SPECS:
				getPropertySpecs().clear();
				return;
			case SkiqlPackage.VARIATION_FILTER__ONLY:
				setOnly(ONLY_EDEFAULT);
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
			case SkiqlPackage.VARIATION_FILTER__PROPERTY_SPECS:
				return propertySpecs != null && !propertySpecs.isEmpty();
			case SkiqlPackage.VARIATION_FILTER__ONLY:
				return only != ONLY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (only: ");
		result.append(only);
		result.append(')');
		return result.toString();
	}

} //VariationFilterImpl
