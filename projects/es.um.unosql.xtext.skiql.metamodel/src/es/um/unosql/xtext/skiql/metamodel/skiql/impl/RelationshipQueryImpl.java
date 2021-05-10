/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.EntitySpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipQuery;
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipSpec;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relationship Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipQueryImpl#getFrom <em>From</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipQueryImpl#getTo <em>To</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationshipQueryImpl extends QueryImpl implements RelationshipQuery {
	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected EntitySpec from;

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected EList<RelationshipSpec> to;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationshipQueryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.RELATIONSHIP_QUERY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntitySpec getFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFrom(EntitySpec newFrom, NotificationChain msgs) {
		EntitySpec oldFrom = from;
		from = newFrom;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_QUERY__FROM, oldFrom, newFrom);
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
	public void setFrom(EntitySpec newFrom) {
		if (newFrom != from) {
			NotificationChain msgs = null;
			if (from != null)
				msgs = ((InternalEObject)from).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.RELATIONSHIP_QUERY__FROM, null, msgs);
			if (newFrom != null)
				msgs = ((InternalEObject)newFrom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SkiqlPackage.RELATIONSHIP_QUERY__FROM, null, msgs);
			msgs = basicSetFrom(newFrom, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_QUERY__FROM, newFrom, newFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<RelationshipSpec> getTo() {
		if (to == null) {
			to = new EObjectContainmentEList<RelationshipSpec>(RelationshipSpec.class, this, SkiqlPackage.RELATIONSHIP_QUERY__TO);
		}
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_QUERY__FROM:
				return basicSetFrom(null, msgs);
			case SkiqlPackage.RELATIONSHIP_QUERY__TO:
				return ((InternalEList<?>)getTo()).basicRemove(otherEnd, msgs);
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
			case SkiqlPackage.RELATIONSHIP_QUERY__FROM:
				return getFrom();
			case SkiqlPackage.RELATIONSHIP_QUERY__TO:
				return getTo();
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
			case SkiqlPackage.RELATIONSHIP_QUERY__FROM:
				setFrom((EntitySpec)newValue);
				return;
			case SkiqlPackage.RELATIONSHIP_QUERY__TO:
				getTo().clear();
				getTo().addAll((Collection<? extends RelationshipSpec>)newValue);
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
			case SkiqlPackage.RELATIONSHIP_QUERY__FROM:
				setFrom((EntitySpec)null);
				return;
			case SkiqlPackage.RELATIONSHIP_QUERY__TO:
				getTo().clear();
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
			case SkiqlPackage.RELATIONSHIP_QUERY__FROM:
				return from != null;
			case SkiqlPackage.RELATIONSHIP_QUERY__TO:
				return to != null && !to.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RelationshipQueryImpl
