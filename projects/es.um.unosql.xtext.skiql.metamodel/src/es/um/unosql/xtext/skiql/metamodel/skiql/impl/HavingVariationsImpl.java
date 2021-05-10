/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Having Variations</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingVariationsImpl#getLowerBounds <em>Lower Bounds</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.HavingVariationsImpl#getUpperBounds <em>Upper Bounds</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HavingVariationsImpl extends HavingTypeImpl implements HavingVariations {
	/**
	 * The default value of the '{@link #getLowerBounds() <em>Lower Bounds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBounds()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_BOUNDS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLowerBounds() <em>Lower Bounds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBounds()
	 * @generated
	 * @ordered
	 */
	protected int lowerBounds = LOWER_BOUNDS_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperBounds() <em>Upper Bounds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBounds()
	 * @generated
	 * @ordered
	 */
	protected static final int UPPER_BOUNDS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getUpperBounds() <em>Upper Bounds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBounds()
	 * @generated
	 * @ordered
	 */
	protected int upperBounds = UPPER_BOUNDS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HavingVariationsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.HAVING_VARIATIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLowerBounds() {
		return lowerBounds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLowerBounds(int newLowerBounds) {
		int oldLowerBounds = lowerBounds;
		lowerBounds = newLowerBounds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.HAVING_VARIATIONS__LOWER_BOUNDS, oldLowerBounds, lowerBounds));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getUpperBounds() {
		return upperBounds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUpperBounds(int newUpperBounds) {
		int oldUpperBounds = upperBounds;
		upperBounds = newUpperBounds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.HAVING_VARIATIONS__UPPER_BOUNDS, oldUpperBounds, upperBounds));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SkiqlPackage.HAVING_VARIATIONS__LOWER_BOUNDS:
				return getLowerBounds();
			case SkiqlPackage.HAVING_VARIATIONS__UPPER_BOUNDS:
				return getUpperBounds();
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
			case SkiqlPackage.HAVING_VARIATIONS__LOWER_BOUNDS:
				setLowerBounds((Integer)newValue);
				return;
			case SkiqlPackage.HAVING_VARIATIONS__UPPER_BOUNDS:
				setUpperBounds((Integer)newValue);
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
			case SkiqlPackage.HAVING_VARIATIONS__LOWER_BOUNDS:
				setLowerBounds(LOWER_BOUNDS_EDEFAULT);
				return;
			case SkiqlPackage.HAVING_VARIATIONS__UPPER_BOUNDS:
				setUpperBounds(UPPER_BOUNDS_EDEFAULT);
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
			case SkiqlPackage.HAVING_VARIATIONS__LOWER_BOUNDS:
				return lowerBounds != LOWER_BOUNDS_EDEFAULT;
			case SkiqlPackage.HAVING_VARIATIONS__UPPER_BOUNDS:
				return upperBounds != UPPER_BOUNDS_EDEFAULT;
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
		result.append(" (lowerBounds: ");
		result.append(lowerBounds);
		result.append(", upperBounds: ");
		result.append(upperBounds);
		result.append(')');
		return result.toString();
	}

} //HavingVariationsImpl
