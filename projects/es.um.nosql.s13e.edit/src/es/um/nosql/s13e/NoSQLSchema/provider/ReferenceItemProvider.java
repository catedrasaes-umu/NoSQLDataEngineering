/**
 */
package es.um.nosql.s13e.NoSQLSchema.provider;


import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Reference;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link es.um.nosql.s13e.NoSQLSchema.Reference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ReferenceItemProvider extends AssociationItemProvider
{
  /**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ReferenceItemProvider(AdapterFactory adapterFactory)
  {
		super(adapterFactory);
	}

  /**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addOppositePropertyDescriptor(object);
			addRefsToPropertyDescriptor(object);
			addOriginalTypePropertyDescriptor(object);
			addFeaturesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

  /**
	 * This adds a property descriptor for the Opposite feature.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected void addOppositePropertyDescriptor(Object object)
  {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Reference_opposite_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Reference_opposite_feature", "_UI_Reference_type"),
				 NoSQLSchemaPackage.Literals.REFERENCE__OPPOSITE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

  /**
	 * This adds a property descriptor for the Refs To feature.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected void addRefsToPropertyDescriptor(Object object)
  {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Reference_refsTo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Reference_refsTo_feature", "_UI_Reference_type"),
				 NoSQLSchemaPackage.Literals.REFERENCE__REFS_TO,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

  /**
	 * This adds a property descriptor for the Original Type feature.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected void addOriginalTypePropertyDescriptor(Object object)
  {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Reference_originalType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Reference_originalType_feature", "_UI_Reference_type"),
				 NoSQLSchemaPackage.Literals.REFERENCE__ORIGINAL_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

  /**
	 * This adds a property descriptor for the Features feature.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected void addFeaturesPropertyDescriptor(Object object)
  {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Reference_features_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Reference_features_feature", "_UI_Reference_type"),
				 NoSQLSchemaPackage.Literals.REFERENCE__FEATURES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

  /**
	 * This returns Reference.gif.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object getImage(Object object)
  {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Reference"));
	}

  /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public String getText(Object object)
  {
		String label = ((Reference)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Reference_type") :
			getString("_UI_Reference_type") + " " + label;
	}


  /**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void notifyChanged(Notification notification)
  {
		updateChildren(notification);

		switch (notification.getFeatureID(Reference.class)) {
			case NoSQLSchemaPackage.REFERENCE__ORIGINAL_TYPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

  /**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
