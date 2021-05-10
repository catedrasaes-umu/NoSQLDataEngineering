/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;
import es.um.unosql.xtext.skiql.metamodel.skiql.VariationFilter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntitySpecImpl#getVariationFilter <em>Variation Filter</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.EntitySpecImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntitySpecImpl extends MinimalEObjectImpl.Container implements EntitySpec {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntitySpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.ENTITY_SPEC;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_SPEC__VARIATION_FILTER, oldVariationFilter, newVariationFilter);
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
				msgs = ((InternalEObject)variationFilter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_SPEC__VARIATION_FILTER, null, msgs);
			if (newVariationFilter != null)
				msgs = ((InternalEObject)newVariationFilter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.ENTITY_SPEC__VARIATION_FILTER, null, msgs);
			msgs = basicSetVariationFilter(newVariationFilter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_SPEC__VARIATION_FILTER, newVariationFilter, newVariationFilter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.ENTITY_SPEC__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.ENTITY_SPEC__VARIATION_FILTER:
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
			case SkiqlPackage.ENTITY_SPEC__VARIATION_FILTER:
				return getVariationFilter();
			case SkiqlPackage.ENTITY_SPEC__NAME:
				return getName();
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
			case SkiqlPackage.ENTITY_SPEC__VARIATION_FILTER:
				setVariationFilter((VariationFilter)newValue);
				return;
			case SkiqlPackage.ENTITY_SPEC__NAME:
				setName((String)newValue);
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
			case SkiqlPackage.ENTITY_SPEC__VARIATION_FILTER:
				setVariationFilter((VariationFilter)null);
				return;
			case SkiqlPackage.ENTITY_SPEC__NAME:
				setName(NAME_EDEFAULT);
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
			case SkiqlPackage.ENTITY_SPEC__VARIATION_FILTER:
				return variationFilter != null;
			case SkiqlPackage.ENTITY_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //EntitySpecImpl
