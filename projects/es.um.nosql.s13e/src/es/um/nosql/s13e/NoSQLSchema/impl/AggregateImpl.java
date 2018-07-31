/**
 */
package es.um.nosql.s13e.NoSQLSchema.impl;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Aggregate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.NoSQLSchema.impl.AggregateImpl#getAggregates <em>Aggregates</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AggregateImpl extends AssociationImpl implements Aggregate
{
  /**
   * The cached value of the '{@link #getAggregates() <em>Aggregates</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAggregates()
   * @generated
   * @ordered
   */
  protected EList<StructuralVariation> aggregates;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AggregateImpl()
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
    return NoSQLSchemaPackage.Literals.AGGREGATE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<StructuralVariation> getAggregates()
  {
    if (aggregates == null)
    {
      aggregates = new EObjectResolvingEList<StructuralVariation>(StructuralVariation.class, this, NoSQLSchemaPackage.AGGREGATE__AGGREGATES);
    }
    return aggregates;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case NoSQLSchemaPackage.AGGREGATE__AGGREGATES:
        return getAggregates();
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
    switch (featureID)
    {
      case NoSQLSchemaPackage.AGGREGATE__AGGREGATES:
        getAggregates().clear();
        getAggregates().addAll((Collection<? extends StructuralVariation>)newValue);
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
    switch (featureID)
    {
      case NoSQLSchemaPackage.AGGREGATE__AGGREGATES:
        getAggregates().clear();
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
    switch (featureID)
    {
      case NoSQLSchemaPackage.AGGREGATE__AGGREGATES:
        return aggregates != null && !aggregates.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AggregateImpl
