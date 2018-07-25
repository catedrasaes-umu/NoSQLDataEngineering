/**
 */
package es.um.nosql.s13e.NoSQLSchema.impl;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityImpl#getName <em>Name</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityImpl#isRoot <em>Root</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityImpl#getEntityVariations <em>Entity Variations</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.EntityImpl#getParents <em>Parents</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityImpl extends MinimalEObjectImpl.Container implements Entity
{
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
	 * The default value of the '{@link #isRoot() <em>Root</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isRoot()
	 * @generated
	 * @ordered
	 */
  protected static final boolean ROOT_EDEFAULT = true;

  /**
	 * The cached value of the '{@link #isRoot() <em>Root</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isRoot()
	 * @generated
	 * @ordered
	 */
  protected boolean root = ROOT_EDEFAULT;

  /**
	 * The cached value of the '{@link #getEntityVariations() <em>Entity Variations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getEntityVariations()
	 * @generated
	 * @ordered
	 */
  protected EList<EntityVariation> entityVariations;

  /**
	 * The cached value of the '{@link #getParents() <em>Parents</em>}' reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getParents()
	 * @generated
	 * @ordered
	 */
  protected EList<Entity> parents;

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EntityImpl()
  {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  protected EClass eStaticClass()
  {
		return NoSQLSchemaPackage.Literals.ENTITY;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getName()
  {
		return name;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setName(String newName)
  {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.ENTITY__NAME, oldName, name));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public boolean isRoot()
  {
		return root;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setRoot(boolean newRoot)
  {
		boolean oldRoot = root;
		root = newRoot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.ENTITY__ROOT, oldRoot, root));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList<EntityVariation> getEntityVariations()
  {
		if (entityVariations == null) {
			entityVariations = new EObjectContainmentEList<EntityVariation>(EntityVariation.class, this, NoSQLSchemaPackage.ENTITY__ENTITY_VARIATIONS);
		}
		return entityVariations;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList<Entity> getParents()
  {
		if (parents == null) {
			parents = new EObjectResolvingEList<Entity>(Entity.class, this, NoSQLSchemaPackage.ENTITY__PARENTS);
		}
		return parents;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
		switch (featureID) {
			case NoSQLSchemaPackage.ENTITY__ENTITY_VARIATIONS:
				return ((InternalEList<?>)getEntityVariations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
		switch (featureID) {
			case NoSQLSchemaPackage.ENTITY__NAME:
				return getName();
			case NoSQLSchemaPackage.ENTITY__ROOT:
				return isRoot();
			case NoSQLSchemaPackage.ENTITY__ENTITY_VARIATIONS:
				return getEntityVariations();
			case NoSQLSchemaPackage.ENTITY__PARENTS:
				return getParents();
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
  public void eSet(int featureID, Object newValue)
  {
		switch (featureID) {
			case NoSQLSchemaPackage.ENTITY__NAME:
				setName((String)newValue);
				return;
			case NoSQLSchemaPackage.ENTITY__ROOT:
				setRoot((Boolean)newValue);
				return;
			case NoSQLSchemaPackage.ENTITY__ENTITY_VARIATIONS:
				getEntityVariations().clear();
				getEntityVariations().addAll((Collection<? extends EntityVariation>)newValue);
				return;
			case NoSQLSchemaPackage.ENTITY__PARENTS:
				getParents().clear();
				getParents().addAll((Collection<? extends Entity>)newValue);
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
  public void eUnset(int featureID)
  {
		switch (featureID) {
			case NoSQLSchemaPackage.ENTITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case NoSQLSchemaPackage.ENTITY__ROOT:
				setRoot(ROOT_EDEFAULT);
				return;
			case NoSQLSchemaPackage.ENTITY__ENTITY_VARIATIONS:
				getEntityVariations().clear();
				return;
			case NoSQLSchemaPackage.ENTITY__PARENTS:
				getParents().clear();
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
  public boolean eIsSet(int featureID)
  {
		switch (featureID) {
			case NoSQLSchemaPackage.ENTITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case NoSQLSchemaPackage.ENTITY__ROOT:
				return root != ROOT_EDEFAULT;
			case NoSQLSchemaPackage.ENTITY__ENTITY_VARIATIONS:
				return entityVariations != null && !entityVariations.isEmpty();
			case NoSQLSchemaPackage.ENTITY__PARENTS:
				return parents != null && !parents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public String toString()
  {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", root: ");
		result.append(root);
		result.append(')');
		return result.toString();
	}

} //EntityImpl
