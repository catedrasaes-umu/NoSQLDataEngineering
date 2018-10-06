/**
 */
package es.um.nosql.s13e.NoSQLSchema.impl;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

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
 * An implementation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.ClassifierImpl#getName <em>Name</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.ClassifierImpl#getParents <em>Parents</em>}</li>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.ClassifierImpl#getVariations <em>Variations</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ClassifierImpl extends MinimalEObjectImpl.Container implements Classifier {
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
   * The cached value of the '{@link #getParents() <em>Parents</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParents()
   * @generated
   * @ordered
   */
  protected EList<Classifier> parents;

  /**
   * The cached value of the '{@link #getVariations() <em>Variations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariations()
   * @generated
   * @ordered
   */
  protected EList<StructuralVariation> variations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClassifierImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return NoSQLSchemaPackage.Literals.CLASSIFIER;
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
      eNotify(new ENotificationImpl(this, Notification.SET, NoSQLSchemaPackage.CLASSIFIER__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Classifier> getParents() {
    if (parents == null) {
      parents = new EObjectResolvingEList<Classifier>(Classifier.class, this, NoSQLSchemaPackage.CLASSIFIER__PARENTS);
    }
    return parents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<StructuralVariation> getVariations() {
    if (variations == null) {
      variations = new EObjectContainmentEList<StructuralVariation>(StructuralVariation.class, this, NoSQLSchemaPackage.CLASSIFIER__VARIATIONS);
    }
    return variations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case NoSQLSchemaPackage.CLASSIFIER__VARIATIONS:
        return ((InternalEList<?>)getVariations()).basicRemove(otherEnd, msgs);
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
      case NoSQLSchemaPackage.CLASSIFIER__NAME:
        return getName();
      case NoSQLSchemaPackage.CLASSIFIER__PARENTS:
        return getParents();
      case NoSQLSchemaPackage.CLASSIFIER__VARIATIONS:
        return getVariations();
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
      case NoSQLSchemaPackage.CLASSIFIER__NAME:
        setName((String)newValue);
        return;
      case NoSQLSchemaPackage.CLASSIFIER__PARENTS:
        getParents().clear();
        getParents().addAll((Collection<? extends Classifier>)newValue);
        return;
      case NoSQLSchemaPackage.CLASSIFIER__VARIATIONS:
        getVariations().clear();
        getVariations().addAll((Collection<? extends StructuralVariation>)newValue);
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
      case NoSQLSchemaPackage.CLASSIFIER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case NoSQLSchemaPackage.CLASSIFIER__PARENTS:
        getParents().clear();
        return;
      case NoSQLSchemaPackage.CLASSIFIER__VARIATIONS:
        getVariations().clear();
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
      case NoSQLSchemaPackage.CLASSIFIER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case NoSQLSchemaPackage.CLASSIFIER__PARENTS:
        return parents != null && !parents.isEmpty();
      case NoSQLSchemaPackage.CLASSIFIER__VARIATIONS:
        return variations != null && !variations.isEmpty();
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

} //ClassifierImpl
