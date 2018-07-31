/**
 */
package es.um.nosql.s13e.NoSQLSchema.impl;

import es.um.nosql.s13e.NoSQLSchema.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NoSQLSchemaFactoryImpl extends EFactoryImpl implements NoSQLSchemaFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static NoSQLSchemaFactory init()
  {
    try
    {
      NoSQLSchemaFactory theNoSQLSchemaFactory = (NoSQLSchemaFactory)EPackage.Registry.INSTANCE.getEFactory(NoSQLSchemaPackage.eNS_URI);
      if (theNoSQLSchemaFactory != null)
      {
        return theNoSQLSchemaFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new NoSQLSchemaFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NoSQLSchemaFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case NoSQLSchemaPackage.NO_SQL_SCHEMA: return createNoSQLSchema();
      case NoSQLSchemaPackage.ENTITY_CLASS: return createEntityClass();
      case NoSQLSchemaPackage.STRUCTURAL_VARIATION: return createStructuralVariation();
      case NoSQLSchemaPackage.ATTRIBUTE: return createAttribute();
      case NoSQLSchemaPackage.PLIST: return createPList();
      case NoSQLSchemaPackage.REFERENCE: return createReference();
      case NoSQLSchemaPackage.AGGREGATE: return createAggregate();
      case NoSQLSchemaPackage.PRIMITIVE_TYPE: return createPrimitiveType();
      case NoSQLSchemaPackage.NULL: return createNull();
      case NoSQLSchemaPackage.REFERENCE_CLASS: return createReferenceClass();
      case NoSQLSchemaPackage.PMAP: return createPMap();
      case NoSQLSchemaPackage.PSET: return createPSet();
      case NoSQLSchemaPackage.PTUPLE: return createPTuple();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NoSQLSchema createNoSQLSchema()
  {
    NoSQLSchemaImpl noSQLSchema = new NoSQLSchemaImpl();
    return noSQLSchema;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityClass createEntityClass()
  {
    EntityClassImpl entityClass = new EntityClassImpl();
    return entityClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StructuralVariation createStructuralVariation()
  {
    StructuralVariationImpl structuralVariation = new StructuralVariationImpl();
    return structuralVariation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Attribute createAttribute()
  {
    AttributeImpl attribute = new AttributeImpl();
    return attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PList createPList()
  {
    PListImpl pList = new PListImpl();
    return pList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Reference createReference()
  {
    ReferenceImpl reference = new ReferenceImpl();
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Aggregate createAggregate()
  {
    AggregateImpl aggregate = new AggregateImpl();
    return aggregate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PrimitiveType createPrimitiveType()
  {
    PrimitiveTypeImpl primitiveType = new PrimitiveTypeImpl();
    return primitiveType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Null createNull()
  {
    NullImpl null_ = new NullImpl();
    return null_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReferenceClass createReferenceClass()
  {
    ReferenceClassImpl referenceClass = new ReferenceClassImpl();
    return referenceClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PMap createPMap()
  {
    PMapImpl pMap = new PMapImpl();
    return pMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PSet createPSet()
  {
    PSetImpl pSet = new PSetImpl();
    return pSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PTuple createPTuple()
  {
    PTupleImpl pTuple = new PTupleImpl();
    return pTuple;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NoSQLSchemaPackage getNoSQLSchemaPackage()
  {
    return (NoSQLSchemaPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static NoSQLSchemaPackage getPackage()
  {
    return NoSQLSchemaPackage.eINSTANCE;
  }

} //NoSQLSchemaFactoryImpl
