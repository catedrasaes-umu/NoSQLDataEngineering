/**
 */
package es.um.nosql.s13e.NoSQLSchema.impl;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.ReferenceClass;

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
 * An implementation of the model object '<em><b>No SQL Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaImpl#getName <em>Name</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaImpl#getEntities <em>Entities</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaImpl#getRefClasses <em>Ref Classes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NoSQLSchemaImpl extends MinimalEObjectImpl.Container implements NoSQLSchema {
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
	 * The cached value of the '{@link #getEntities() <em>Entities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntities()
	 * @generated
	 * @ordered
	 */
	protected EList<EntityClass> entities;

	/**
	 * The cached value of the '{@link #getRefClasses() <em>Ref Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<ReferenceClass> refClasses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NoSQLSchemaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NoSQLSchemaPackage.Literals.NO_SQL_SCHEMA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.NO_SQL_SCHEMA__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EntityClass> getEntities() {
		if (entities == null) {
			entities = new EObjectContainmentEList<EntityClass>(EntityClass.class, this, NoSQLSchemaPackage.NO_SQL_SCHEMA__ENTITIES);
		}
		return entities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ReferenceClass> getRefClasses() {
		if (refClasses == null) {
			refClasses = new EObjectContainmentEList<ReferenceClass>(ReferenceClass.class, this, NoSQLSchemaPackage.NO_SQL_SCHEMA__REF_CLASSES);
		}
		return refClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__ENTITIES:
				return ((InternalEList<?>)getEntities()).basicRemove(otherEnd, msgs);
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__REF_CLASSES:
				return ((InternalEList<?>)getRefClasses()).basicRemove(otherEnd, msgs);
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
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__NAME:
				return getName();
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__ENTITIES:
				return getEntities();
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__REF_CLASSES:
				return getRefClasses();
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
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__NAME:
				setName((String)newValue);
				return;
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__ENTITIES:
				getEntities().clear();
				getEntities().addAll((Collection<? extends EntityClass>)newValue);
				return;
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__REF_CLASSES:
				getRefClasses().clear();
				getRefClasses().addAll((Collection<? extends ReferenceClass>)newValue);
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
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__NAME:
				setName(NAME_EDEFAULT);
				return;
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__ENTITIES:
				getEntities().clear();
				return;
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__REF_CLASSES:
				getRefClasses().clear();
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
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__ENTITIES:
				return entities != null && !entities.isEmpty();
			case NoSQLSchemaPackage.NO_SQL_SCHEMA__REF_CLASSES:
				return refClasses != null && !refClasses.isEmpty();
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

} //NoSQLSchemaImpl
