/**
 */
package es.um.nosql.schemainference.dsl4mongoose.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage;
import es.um.nosql.schemainference.dsl4mongoose.EntityParameter;
import es.um.nosql.schemainference.dsl4mongoose.Index;
import es.um.nosql.schemainference.dsl4mongoose.Unique;
import es.um.nosql.schemainference.dsl4mongoose.Update;
import es.um.nosql.schemainference.dsl4mongoose.Validator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.impl.EntityParameterImpl#getValidators <em>Validators</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.impl.EntityParameterImpl#getUniques <em>Uniques</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.impl.EntityParameterImpl#getUpdates <em>Updates</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.impl.EntityParameterImpl#isDiscriminator <em>Discriminator</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.impl.EntityParameterImpl#getIndexes <em>Indexes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityParameterImpl extends NamedElementImpl implements EntityParameter {
	/**
	 * The cached value of the '{@link #getValidators() <em>Validators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidators()
	 * @generated
	 * @ordered
	 */
	protected EList<Validator> validators;

	/**
	 * The cached value of the '{@link #getUniques() <em>Uniques</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniques()
	 * @generated
	 * @ordered
	 */
	protected EList<Unique> uniques;

	/**
	 * The cached value of the '{@link #getUpdates() <em>Updates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpdates()
	 * @generated
	 * @ordered
	 */
	protected EList<Update> updates;

	/**
	 * The default value of the '{@link #isDiscriminator() <em>Discriminator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDiscriminator()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISCRIMINATOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDiscriminator() <em>Discriminator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDiscriminator()
	 * @generated
	 * @ordered
	 */
	protected boolean discriminator = DISCRIMINATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIndexes() <em>Indexes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList<Index> indexes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Dsl4mongoosePackage.Literals.ENTITY_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Validator> getValidators() {
		if (validators == null) {
			validators = new EObjectContainmentEList<Validator>(Validator.class, this, Dsl4mongoosePackage.ENTITY_PARAMETER__VALIDATORS);
		}
		return validators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Unique> getUniques() {
		if (uniques == null) {
			uniques = new EObjectContainmentEList<Unique>(Unique.class, this, Dsl4mongoosePackage.ENTITY_PARAMETER__UNIQUES);
		}
		return uniques;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Update> getUpdates() {
		if (updates == null) {
			updates = new EObjectContainmentEList<Update>(Update.class, this, Dsl4mongoosePackage.ENTITY_PARAMETER__UPDATES);
		}
		return updates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDiscriminator() {
		return discriminator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiscriminator(boolean newDiscriminator) {
		boolean oldDiscriminator = discriminator;
		discriminator = newDiscriminator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Dsl4mongoosePackage.ENTITY_PARAMETER__DISCRIMINATOR, oldDiscriminator, discriminator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Index> getIndexes() {
		if (indexes == null) {
			indexes = new EObjectContainmentEList<Index>(Index.class, this, Dsl4mongoosePackage.ENTITY_PARAMETER__INDEXES);
		}
		return indexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Dsl4mongoosePackage.ENTITY_PARAMETER__VALIDATORS:
				return ((InternalEList<?>)getValidators()).basicRemove(otherEnd, msgs);
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UNIQUES:
				return ((InternalEList<?>)getUniques()).basicRemove(otherEnd, msgs);
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UPDATES:
				return ((InternalEList<?>)getUpdates()).basicRemove(otherEnd, msgs);
			case Dsl4mongoosePackage.ENTITY_PARAMETER__INDEXES:
				return ((InternalEList<?>)getIndexes()).basicRemove(otherEnd, msgs);
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
			case Dsl4mongoosePackage.ENTITY_PARAMETER__VALIDATORS:
				return getValidators();
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UNIQUES:
				return getUniques();
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UPDATES:
				return getUpdates();
			case Dsl4mongoosePackage.ENTITY_PARAMETER__DISCRIMINATOR:
				return isDiscriminator();
			case Dsl4mongoosePackage.ENTITY_PARAMETER__INDEXES:
				return getIndexes();
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
			case Dsl4mongoosePackage.ENTITY_PARAMETER__VALIDATORS:
				getValidators().clear();
				getValidators().addAll((Collection<? extends Validator>)newValue);
				return;
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UNIQUES:
				getUniques().clear();
				getUniques().addAll((Collection<? extends Unique>)newValue);
				return;
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UPDATES:
				getUpdates().clear();
				getUpdates().addAll((Collection<? extends Update>)newValue);
				return;
			case Dsl4mongoosePackage.ENTITY_PARAMETER__DISCRIMINATOR:
				setDiscriminator((Boolean)newValue);
				return;
			case Dsl4mongoosePackage.ENTITY_PARAMETER__INDEXES:
				getIndexes().clear();
				getIndexes().addAll((Collection<? extends Index>)newValue);
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
			case Dsl4mongoosePackage.ENTITY_PARAMETER__VALIDATORS:
				getValidators().clear();
				return;
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UNIQUES:
				getUniques().clear();
				return;
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UPDATES:
				getUpdates().clear();
				return;
			case Dsl4mongoosePackage.ENTITY_PARAMETER__DISCRIMINATOR:
				setDiscriminator(DISCRIMINATOR_EDEFAULT);
				return;
			case Dsl4mongoosePackage.ENTITY_PARAMETER__INDEXES:
				getIndexes().clear();
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
			case Dsl4mongoosePackage.ENTITY_PARAMETER__VALIDATORS:
				return validators != null && !validators.isEmpty();
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UNIQUES:
				return uniques != null && !uniques.isEmpty();
			case Dsl4mongoosePackage.ENTITY_PARAMETER__UPDATES:
				return updates != null && !updates.isEmpty();
			case Dsl4mongoosePackage.ENTITY_PARAMETER__DISCRIMINATOR:
				return discriminator != DISCRIMINATOR_EDEFAULT;
			case Dsl4mongoosePackage.ENTITY_PARAMETER__INDEXES:
				return indexes != null && !indexes.isEmpty();
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (discriminator: ");
		result.append(discriminator);
		result.append(')');
		return result.toString();
	}

} //EntityParameterImpl
