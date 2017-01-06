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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage;
import es.um.nosql.schemainference.dsl4mongoose.EntityParameter;
import es.um.nosql.schemainference.dsl4mongoose.MongooseModel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mongoose Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.impl.MongooseModelImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.impl.MongooseModelImpl#getMapper <em>Mapper</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MongooseModelImpl extends MinimalEObjectImpl.Container implements MongooseModel {
	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<EntityParameter> parameters;

	/**
	 * The default value of the '{@link #getMapper() <em>Mapper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapper()
	 * @generated
	 * @ordered
	 */
	protected static final String MAPPER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMapper() <em>Mapper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapper()
	 * @generated
	 * @ordered
	 */
	protected String mapper = MAPPER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MongooseModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Dsl4mongoosePackage.Literals.MONGOOSE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EntityParameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<EntityParameter>(EntityParameter.class, this, Dsl4mongoosePackage.MONGOOSE_MODEL__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMapper() {
		return mapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapper(String newMapper) {
		String oldMapper = mapper;
		mapper = newMapper;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Dsl4mongoosePackage.MONGOOSE_MODEL__MAPPER, oldMapper, mapper));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Dsl4mongoosePackage.MONGOOSE_MODEL__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
			case Dsl4mongoosePackage.MONGOOSE_MODEL__PARAMETERS:
				return getParameters();
			case Dsl4mongoosePackage.MONGOOSE_MODEL__MAPPER:
				return getMapper();
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
			case Dsl4mongoosePackage.MONGOOSE_MODEL__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends EntityParameter>)newValue);
				return;
			case Dsl4mongoosePackage.MONGOOSE_MODEL__MAPPER:
				setMapper((String)newValue);
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
			case Dsl4mongoosePackage.MONGOOSE_MODEL__PARAMETERS:
				getParameters().clear();
				return;
			case Dsl4mongoosePackage.MONGOOSE_MODEL__MAPPER:
				setMapper(MAPPER_EDEFAULT);
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
			case Dsl4mongoosePackage.MONGOOSE_MODEL__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case Dsl4mongoosePackage.MONGOOSE_MODEL__MAPPER:
				return MAPPER_EDEFAULT == null ? mapper != null : !MAPPER_EDEFAULT.equals(mapper);
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
		result.append(" (mapper: ");
		result.append(mapper);
		result.append(')');
		return result.toString();
	}

} //MongooseModelImpl
