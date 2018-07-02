/**
 */
package es.um.nosql.s13e.NoSQLSchema.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import es.um.nosql.s13e.NoSQLSchema.util.NoSQLSchemaAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class NoSQLSchemaItemProviderAdapterFactory extends NoSQLSchemaAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable
{
  /**
   * This keeps track of the root adapter factory that delegates to this adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection<Object> supportedTypes = new ArrayList<Object>();

  /**
   * This constructs an instance.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NoSQLSchemaItemProviderAdapterFactory()
  {
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(IItemPropertySource.class);
  }

  /**
   * This keeps track of the one adapter used for all {@link es.um.nosql.s13e.NoSQLSchema.NoSQLSchema} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NoSQLSchemaItemProvider noSQLSchemaItemProvider;

  /**
   * This creates an adapter for a {@link es.um.nosql.s13e.NoSQLSchema.NoSQLSchema}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createNoSQLSchemaAdapter()
  {
    if (noSQLSchemaItemProvider == null)
    {
      noSQLSchemaItemProvider = new NoSQLSchemaItemProvider(this);
    }

    return noSQLSchemaItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link es.um.nosql.s13e.NoSQLSchema.Entity} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EntityItemProvider entityItemProvider;

  /**
   * This creates an adapter for a {@link es.um.nosql.s13e.NoSQLSchema.Entity}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEntityAdapter()
  {
    if (entityItemProvider == null)
    {
      entityItemProvider = new EntityItemProvider(this);
    }

    return entityItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link es.um.nosql.s13e.NoSQLSchema.EntityVariation} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EntityVariationItemProvider entityVariationItemProvider;

  /**
   * This creates an adapter for a {@link es.um.nosql.s13e.NoSQLSchema.EntityVariation}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEntityVariationAdapter()
  {
    if (entityVariationItemProvider == null)
    {
      entityVariationItemProvider = new EntityVariationItemProvider(this);
    }

    return entityVariationItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link es.um.nosql.s13e.NoSQLSchema.Attribute} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeItemProvider attributeItemProvider;

  /**
   * This creates an adapter for a {@link es.um.nosql.s13e.NoSQLSchema.Attribute}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createAttributeAdapter()
  {
    if (attributeItemProvider == null)
    {
      attributeItemProvider = new AttributeItemProvider(this);
    }

    return attributeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link es.um.nosql.s13e.NoSQLSchema.Tuple} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TupleItemProvider tupleItemProvider;

  /**
   * This creates an adapter for a {@link es.um.nosql.s13e.NoSQLSchema.Tuple}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createTupleAdapter()
  {
    if (tupleItemProvider == null)
    {
      tupleItemProvider = new TupleItemProvider(this);
    }

    return tupleItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link es.um.nosql.s13e.NoSQLSchema.Reference} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ReferenceItemProvider referenceItemProvider;

  /**
   * This creates an adapter for a {@link es.um.nosql.s13e.NoSQLSchema.Reference}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createReferenceAdapter()
  {
    if (referenceItemProvider == null)
    {
      referenceItemProvider = new ReferenceItemProvider(this);
    }

    return referenceItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link es.um.nosql.s13e.NoSQLSchema.Aggregate} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AggregateItemProvider aggregateItemProvider;

  /**
   * This creates an adapter for a {@link es.um.nosql.s13e.NoSQLSchema.Aggregate}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createAggregateAdapter()
  {
    if (aggregateItemProvider == null)
    {
      aggregateItemProvider = new AggregateItemProvider(this);
    }

    return aggregateItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link es.um.nosql.s13e.NoSQLSchema.PrimitiveType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PrimitiveTypeItemProvider primitiveTypeItemProvider;

  /**
   * This creates an adapter for a {@link es.um.nosql.s13e.NoSQLSchema.PrimitiveType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createPrimitiveTypeAdapter()
  {
    if (primitiveTypeItemProvider == null)
    {
      primitiveTypeItemProvider = new PrimitiveTypeItemProvider(this);
    }

    return primitiveTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link es.um.nosql.s13e.NoSQLSchema.Null} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NullItemProvider nullItemProvider;

  /**
   * This creates an adapter for a {@link es.um.nosql.s13e.NoSQLSchema.Null}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createNullAdapter()
  {
    if (nullItemProvider == null)
    {
      nullItemProvider = new NullItemProvider(this);
    }

    return nullItemProvider;
  }

  /**
   * This returns the root adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This sets the composed adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    this.parentAdapterFactory = parentAdapterFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object type)
  {
    return supportedTypes.contains(type) || super.isFactoryForType(type);
  }

  /**
   * This implementation substitutes the factory itself as the key for the adapter.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter adapt(Notifier notifier, Object type)
  {
    return super.adapt(notifier, this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object adapt(Object object, Object type)
  {
    if (isFactoryForType(type))
    {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter)))
      {
        return adapter;
      }
    }

    return null;
  }

  /**
   * This adds a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.addListener(notifyChangedListener);
  }

  /**
   * This removes a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
  }

  /**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void fireNotifyChanged(Notification notification)
  {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null)
    {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

  /**
   * This disposes all of the item providers created by this factory. 
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void dispose()
  {
    if (noSQLSchemaItemProvider != null) noSQLSchemaItemProvider.dispose();
    if (entityItemProvider != null) entityItemProvider.dispose();
    if (entityVariationItemProvider != null) entityVariationItemProvider.dispose();
    if (attributeItemProvider != null) attributeItemProvider.dispose();
    if (tupleItemProvider != null) tupleItemProvider.dispose();
    if (referenceItemProvider != null) referenceItemProvider.dispose();
    if (aggregateItemProvider != null) aggregateItemProvider.dispose();
    if (primitiveTypeItemProvider != null) primitiveTypeItemProvider.dispose();
    if (nullItemProvider != null) nullItemProvider.dispose();
  }

}
