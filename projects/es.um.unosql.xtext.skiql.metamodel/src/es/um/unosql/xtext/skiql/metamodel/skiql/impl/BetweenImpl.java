/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.Between;
import es.um.unosql.xtext.skiql.metamodel.skiql.DateTime;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Between</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.BetweenImpl#getAfterDateTime <em>After Date Time</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.BetweenImpl#getBeforeDateTime <em>Before Date Time</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BetweenImpl extends VersionHistoryImpl implements Between {
	/**
	 * The cached value of the '{@link #getAfterDateTime() <em>After Date Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfterDateTime()
	 * @generated
	 * @ordered
	 */
	protected DateTime afterDateTime;

	/**
	 * The cached value of the '{@link #getBeforeDateTime() <em>Before Date Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeforeDateTime()
	 * @generated
	 * @ordered
	 */
	protected DateTime beforeDateTime;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BetweenImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.BETWEEN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DateTime getAfterDateTime() {
		return afterDateTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAfterDateTime(DateTime newAfterDateTime, NotificationChain msgs) {
		DateTime oldAfterDateTime = afterDateTime;
		afterDateTime = newAfterDateTime;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.BETWEEN__AFTER_DATE_TIME, oldAfterDateTime, newAfterDateTime);
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
	public void setAfterDateTime(DateTime newAfterDateTime) {
		if (newAfterDateTime != afterDateTime) {
			NotificationChain msgs = null;
			if (afterDateTime != null)
				msgs = ((InternalEObject)afterDateTime).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.BETWEEN__AFTER_DATE_TIME, null, msgs);
			if (newAfterDateTime != null)
				msgs = ((InternalEObject)newAfterDateTime).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.BETWEEN__AFTER_DATE_TIME, null, msgs);
			msgs = basicSetAfterDateTime(newAfterDateTime, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.BETWEEN__AFTER_DATE_TIME, newAfterDateTime, newAfterDateTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DateTime getBeforeDateTime() {
		return beforeDateTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBeforeDateTime(DateTime newBeforeDateTime, NotificationChain msgs) {
		DateTime oldBeforeDateTime = beforeDateTime;
		beforeDateTime = newBeforeDateTime;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.BETWEEN__BEFORE_DATE_TIME, oldBeforeDateTime, newBeforeDateTime);
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
	public void setBeforeDateTime(DateTime newBeforeDateTime) {
		if (newBeforeDateTime != beforeDateTime) {
			NotificationChain msgs = null;
			if (beforeDateTime != null)
				msgs = ((InternalEObject)beforeDateTime).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.BETWEEN__BEFORE_DATE_TIME, null, msgs);
			if (newBeforeDateTime != null)
				msgs = ((InternalEObject)newBeforeDateTime).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.BETWEEN__BEFORE_DATE_TIME, null, msgs);
			msgs = basicSetBeforeDateTime(newBeforeDateTime, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.BETWEEN__BEFORE_DATE_TIME, newBeforeDateTime, newBeforeDateTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.BETWEEN__AFTER_DATE_TIME:
				return basicSetAfterDateTime(null, msgs);
			case SkiqlPackage.BETWEEN__BEFORE_DATE_TIME:
				return basicSetBeforeDateTime(null, msgs);
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
			case SkiqlPackage.BETWEEN__AFTER_DATE_TIME:
				return getAfterDateTime();
			case SkiqlPackage.BETWEEN__BEFORE_DATE_TIME:
				return getBeforeDateTime();
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
			case SkiqlPackage.BETWEEN__AFTER_DATE_TIME:
				setAfterDateTime((DateTime)newValue);
				return;
			case SkiqlPackage.BETWEEN__BEFORE_DATE_TIME:
				setBeforeDateTime((DateTime)newValue);
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
			case SkiqlPackage.BETWEEN__AFTER_DATE_TIME:
				setAfterDateTime((DateTime)null);
				return;
			case SkiqlPackage.BETWEEN__BEFORE_DATE_TIME:
				setBeforeDateTime((DateTime)null);
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
			case SkiqlPackage.BETWEEN__AFTER_DATE_TIME:
				return afterDateTime != null;
			case SkiqlPackage.BETWEEN__BEFORE_DATE_TIME:
				return beforeDateTime != null;
		}
		return super.eIsSet(featureID);
	}

} //BetweenImpl
