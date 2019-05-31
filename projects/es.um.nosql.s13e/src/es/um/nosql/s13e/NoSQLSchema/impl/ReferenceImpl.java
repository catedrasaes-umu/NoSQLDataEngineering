/**
 */
package es.um.nosql.s13e.NoSQLSchema.impl;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.ReferenceImpl#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.ReferenceImpl#getRefsTo <em>Refs To</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.ReferenceImpl#getOriginalType <em>Original Type</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.ReferenceImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferenceImpl extends AssociationImpl implements Reference {
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
	 * The cached value of the '{@link #getRefsTo() <em>Refs To</em>}' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getRefsTo()
	 * @generated
	 * @ordered
	 */
  protected EntityType refsTo;

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
	 * The cached value of the '{@link #getFeatures() <em>Features</em>}' reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getFeatures()
	 * @generated
	 * @ordered
	 */
  protected EList<StructuralVariation> features;

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
  @Override
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
  @Override
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
  @Override
  public EntityType getRefsTo() {
		if (refsTo != null && refsTo.eIsProxy()) {
			InternalEObject oldRefsTo = (InternalEObject)refsTo;
			refsTo = (EntityType)eResolveProxy(oldRefsTo);
			if (refsTo != oldRefsTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NoSQLSchemaPackage.REFERENCE__REFS_TO, oldRefsTo, refsTo));
			}
		}
		return refsTo;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EntityType basicGetRefsTo() {
		return refsTo;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void setRefsTo(EntityType newRefsTo) {
		EntityType oldRefsTo = refsTo;
		refsTo = newRefsTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.REFERENCE__REFS_TO, oldRefsTo, refsTo));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public String getOriginalType() {
		return originalType;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
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
  public EList<StructuralVariation> getFeatures() {
		if (features == null) {
			features = new EObjectResolvingEList<StructuralVariation>(StructuralVariation.class, this, NoSQLSchemaPackage.REFERENCE__FEATURES);
		}
		return features;
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
			case NoSQLSchemaPackage.REFERENCE__REFS_TO:
				if (resolve) return getRefsTo();
				return basicGetRefsTo();
			case NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE:
				return getOriginalType();
			case NoSQLSchemaPackage.REFERENCE__FEATURES:
				return getFeatures();
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
			case NoSQLSchemaPackage.REFERENCE__OPPOSITE:
				setOpposite((Reference)newValue);
				return;
			case NoSQLSchemaPackage.REFERENCE__REFS_TO:
				setRefsTo((EntityType)newValue);
				return;
			case NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE:
				setOriginalType((String)newValue);
				return;
			case NoSQLSchemaPackage.REFERENCE__FEATURES:
				getFeatures().clear();
				getFeatures().addAll((Collection<? extends StructuralVariation>)newValue);
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
			case NoSQLSchemaPackage.REFERENCE__REFS_TO:
				setRefsTo((EntityType)null);
				return;
			case NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE:
				setOriginalType(ORIGINAL_TYPE_EDEFAULT);
				return;
			case NoSQLSchemaPackage.REFERENCE__FEATURES:
				getFeatures().clear();
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
			case NoSQLSchemaPackage.REFERENCE__REFS_TO:
				return refsTo != null;
			case NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE:
				return ORIGINAL_TYPE_EDEFAULT == null ? originalType != null : !ORIGINAL_TYPE_EDEFAULT.equals(originalType);
			case NoSQLSchemaPackage.REFERENCE__FEATURES:
				return features != null && !features.isEmpty();
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
		result.append(" (originalType: ");
		result.append(originalType);
		result.append(')');
		return result.toString();
	}

} //ReferenceImpl
