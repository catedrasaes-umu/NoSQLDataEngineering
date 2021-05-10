/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.ReferenceSpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;
import es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.ReferenceSpecImpl#getVariationFilter <em>Variation Filter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferenceSpecImpl extends RelationSpecImpl implements ReferenceSpec {
	/**
	 * The cached value of the '{@link #getVariationFilter() <em>Variation Filter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariationFilter()
	 * @generated
	 * @ordered
	 */
	protected VariationFilter variationFilter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.REFERENCE_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariationFilter getVariationFilter() {
		return variationFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariationFilter(VariationFilter newVariationFilter, NotificationChain msgs) {
		VariationFilter oldVariationFilter = variationFilter;
		variationFilter = newVariationFilter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.REFERENCE_SPEC__VARIATION_FILTER, oldVariationFilter, newVariationFilter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVariationFilter(VariationFilter newVariationFilter) {
		if (newVariationFilter != variationFilter) {
			NotificationChain msgs = null;
			if (variationFilter != null)
				msgs = ((InternalEObject)variationFilter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.REFERENCE_SPEC__VARIATION_FILTER, null, msgs);
			if (newVariationFilter != null)
				msgs = ((InternalEObject)newVariationFilter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.REFERENCE_SPEC__VARIATION_FILTER, null, msgs);
			msgs = basicSetVariationFilter(newVariationFilter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.REFERENCE_SPEC__VARIATION_FILTER, newVariationFilter, newVariationFilter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.REFERENCE_SPEC__VARIATION_FILTER:
				return basicSetVariationFilter(null, msgs);
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
			case SkiqlPackage.REFERENCE_SPEC__VARIATION_FILTER:
				return getVariationFilter();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SkiqlPackage.REFERENCE_SPEC__VARIATION_FILTER:
				setVariationFilter((VariationFilter)newValue);
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
			case SkiqlPackage.REFERENCE_SPEC__VARIATION_FILTER:
				setVariationFilter((VariationFilter)null);
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
			case SkiqlPackage.REFERENCE_SPEC__VARIATION_FILTER:
				return variationFilter != null;
		}
		return super.eIsSet(featureID);
	}

} //ReferenceSpecImpl
