/**
 */
package Variation_Diff.util;

import Variation_Diff.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see Variation_Diff.Variation_DiffPackage
 * @generated
 */
public class Variation_DiffAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static Variation_DiffPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Variation_DiffAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = Variation_DiffPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Variation_DiffSwitch<Adapter> modelSwitch =
    new Variation_DiffSwitch<Adapter>()
    {
      @Override
      public Adapter caseNoSQLDifferences(NoSQLDifferences object)
      {
        return createNoSQLDifferencesAdapter();
      }
      @Override
      public Adapter caseTypeDifference(TypeDifference object)
      {
        return createTypeDifferenceAdapter();
      }
      @Override
      public Adapter caseTypeHint(TypeHint object)
      {
        return createTypeHintAdapter();
      }
      @Override
      public Adapter caseHasField(HasField object)
      {
        return createHasFieldAdapter();
      }
      @Override
      public Adapter caseHasNotField(HasNotField object)
      {
        return createHasNotFieldAdapter();
      }
      @Override
      public Adapter caseFieldType(FieldType object)
      {
        return createFieldTypeAdapter();
      }
      @Override
      public Adapter casePrimitiveType(PrimitiveType object)
      {
        return createPrimitiveTypeAdapter();
      }
      @Override
      public Adapter caseTupleType(TupleType object)
      {
        return createTupleTypeAdapter();
      }
      @Override
      public Adapter caseAssociationType(AssociationType object)
      {
        return createAssociationTypeAdapter();
      }
      @Override
      public Adapter caseAggregateType(AggregateType object)
      {
        return createAggregateTypeAdapter();
      }
      @Override
      public Adapter caseReferenceType(ReferenceType object)
      {
        return createReferenceTypeAdapter();
      }
      @Override
      public Adapter caseEntityType(EntityType object)
      {
        return createEntityTypeAdapter();
      }
      @Override
      public Adapter caseHomogeneousTupleType(HomogeneousTupleType object)
      {
        return createHomogeneousTupleTypeAdapter();
      }
      @Override
      public Adapter caseHeterogeneousTupleType(HeterogeneousTupleType object)
      {
        return createHeterogeneousTupleTypeAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.NoSQLDifferences <em>No SQL Differences</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.NoSQLDifferences
   * @generated
   */
  public Adapter createNoSQLDifferencesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.TypeDifference <em>Type Difference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.TypeDifference
   * @generated
   */
  public Adapter createTypeDifferenceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.TypeHint <em>Type Hint</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.TypeHint
   * @generated
   */
  public Adapter createTypeHintAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.HasField <em>Has Field</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.HasField
   * @generated
   */
  public Adapter createHasFieldAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.HasNotField <em>Has Not Field</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.HasNotField
   * @generated
   */
  public Adapter createHasNotFieldAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.FieldType <em>Field Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.FieldType
   * @generated
   */
  public Adapter createFieldTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.PrimitiveType <em>Primitive Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.PrimitiveType
   * @generated
   */
  public Adapter createPrimitiveTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.TupleType <em>Tuple Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.TupleType
   * @generated
   */
  public Adapter createTupleTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.AssociationType <em>Association Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.AssociationType
   * @generated
   */
  public Adapter createAssociationTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.AggregateType <em>Aggregate Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.AggregateType
   * @generated
   */
  public Adapter createAggregateTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.ReferenceType <em>Reference Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.ReferenceType
   * @generated
   */
  public Adapter createReferenceTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.EntityType <em>Entity Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.EntityType
   * @generated
   */
  public Adapter createEntityTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.HomogeneousTupleType <em>Homogeneous Tuple Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.HomogeneousTupleType
   * @generated
   */
  public Adapter createHomogeneousTupleTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link Variation_Diff.HeterogeneousTupleType <em>Heterogeneous Tuple Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see Variation_Diff.HeterogeneousTupleType
   * @generated
   */
  public Adapter createHeterogeneousTupleTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //Variation_DiffAdapterFactory
