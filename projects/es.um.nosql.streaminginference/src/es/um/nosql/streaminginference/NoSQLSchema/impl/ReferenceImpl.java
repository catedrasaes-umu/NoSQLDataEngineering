/**
 */
package es.um.nosql.streaminginference.NoSQLSchema.impl;

import es.um.nosql.streaminginference.NoSQLSchema.Entity;
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.streaminginference.NoSQLSchema.Reference;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.streaminginference.NoSQLSchema.impl.ReferenceImpl#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link es.um.nosql.streaminginference.NoSQLSchema.impl.ReferenceImpl#getRefTo <em>Ref To</em>}</li>
 *   <li>{@link es.um.nosql.streaminginference.NoSQLSchema.impl.ReferenceImpl#getOriginalType <em>Original Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferenceImpl extends AssociationImpl implements Reference {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 2966586439L;

	/**
	 * The cached value of the '{@link #getOpposite() <em>Opposite</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpposite()
	 * @generated
	 * @ordered
	 */
	protected Reference opposite;

	/**
	 * The cached value of the '{@link #getRefTo() <em>Ref To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefTo()
	 * @generated
	 * @ordered
	 */
	protected Entity refTo;

	/**
	 * The default value of the '{@link #getOriginalType() <em>Original Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalType()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGINAL_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginalType() <em>Original Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalType()
	 * @generated
	 * @ordered
	 */
	protected String originalType = ORIGINAL_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NoSQLSchemaPackage.Literals.REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference getOpposite() {
		if (opposite != null && opposite.eIsProxy()) {
			InternalEObject oldOpposite = (InternalEObject)opposite;
			opposite = (Reference)eResolveProxy(oldOpposite);
			if (opposite != oldOpposite) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NoSQLSchemaPackage.REFERENCE__OPPOSITE, oldOpposite, opposite));
			}
		}
		return opposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference basicGetOpposite() {
		return opposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpposite(Reference newOpposite) {
		Reference oldOpposite = opposite;
		opposite = newOpposite;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.REFERENCE__OPPOSITE, oldOpposite, opposite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getRefTo() {
		if (refTo != null && refTo.eIsProxy()) {
			InternalEObject oldRefTo = (InternalEObject)refTo;
			refTo = (Entity)eResolveProxy(oldRefTo);
			if (refTo != oldRefTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NoSQLSchemaPackage.REFERENCE__REF_TO, oldRefTo, refTo));
			}
		}
		return refTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity basicGetRefTo() {
		return refTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRefTo(Entity newRefTo) {
		Entity oldRefTo = refTo;
		refTo = newRefTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.REFERENCE__REF_TO, oldRefTo, refTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOriginalType() {
		return originalType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalType(String newOriginalType) {
		String oldOriginalType = originalType;
		originalType = newOriginalType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE, oldOriginalType, originalType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NoSQLSchemaPackage.REFERENCE__OPPOSITE:
				if (resolve) return getOpposite();
				return basicGetOpposite();
			case NoSQLSchemaPackage.REFERENCE__REF_TO:
				if (resolve) return getRefTo();
				return basicGetRefTo();
			case NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE:
				return getOriginalType();
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
			case NoSQLSchemaPackage.REFERENCE__OPPOSITE:
				setOpposite((Reference)newValue);
				return;
			case NoSQLSchemaPackage.REFERENCE__REF_TO:
				setRefTo((Entity)newValue);
				return;
			case NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE:
				setOriginalType((String)newValue);
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
			case NoSQLSchemaPackage.REFERENCE__OPPOSITE:
				setOpposite((Reference)null);
				return;
			case NoSQLSchemaPackage.REFERENCE__REF_TO:
				setRefTo((Entity)null);
				return;
			case NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE:
				setOriginalType(ORIGINAL_TYPE_EDEFAULT);
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
			case NoSQLSchemaPackage.REFERENCE__OPPOSITE:
				return opposite != null;
			case NoSQLSchemaPackage.REFERENCE__REF_TO:
				return refTo != null;
			case NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE:
				return ORIGINAL_TYPE_EDEFAULT == null ? originalType != null : !ORIGINAL_TYPE_EDEFAULT.equals(originalType);
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
		result.append(" (originalType: ");
		result.append(originalType);
		result.append(')');
		return result.toString();
	}
	
	/**
	   * Always treat de-serialization as a full-blown constructor, by
	   * validating the final state of the de-serialized object.
	   */	
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
	     //always perform the default de-serialization first
	     aInputStream.defaultReadObject();
	}

    /**
    * This is the default implementation of writeObject.
    * Customise if necessary.
    */
    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {    	
      //perform the default serialization for all non-transient, non-static fields
      aOutputStream.defaultWriteObject();
    }

} //ReferenceImpl
