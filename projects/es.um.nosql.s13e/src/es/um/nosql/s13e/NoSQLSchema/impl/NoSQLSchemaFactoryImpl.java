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
public class NoSQLSchemaFactoryImpl extends EFactoryImpl implements NoSQLSchemaFactory {
  /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public static NoSQLSchemaFactory init() {
		try {
			NoSQLSchemaFactory theNoSQLSchemaFactory = (NoSQLSchemaFactory)EPackage.Registry.INSTANCE.getEFactory(NoSQLSchemaPackage.eNS_URI);
			if (theNoSQLSchemaFactory != null) {
				return theNoSQLSchemaFactory;
			}
		}
		catch (Exception exception) {
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
  public NoSQLSchemaFactoryImpl() {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case NoSQLSchemaPackage.NO_SQL_SCHEMA: return createNoSQLSchema();
			case NoSQLSchemaPackage.ENTITY_TYPE: return createEntityType();
			case NoSQLSchemaPackage.STRUCTURAL_VARIATION: return createStructuralVariation();
			case NoSQLSchemaPackage.ATTRIBUTE: return createAttribute();
			case NoSQLSchemaPackage.PLIST: return createPList();
			case NoSQLSchemaPackage.REFERENCE: return createReference();
			case NoSQLSchemaPackage.AGGREGATE: return createAggregate();
			case NoSQLSchemaPackage.PRIMITIVE_TYPE: return createPrimitiveType();
			case NoSQLSchemaPackage.NULL: return createNull();
			case NoSQLSchemaPackage.RELATIONSHIP_TYPE: return createRelationshipType();
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
  @Override
  public NoSQLSchema createNoSQLSchema() {
		NoSQLSchemaImpl noSQLSchema = new NoSQLSchemaImpl();
		return noSQLSchema;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public EntityType createEntityType() {
		EntityTypeImpl entityType = new EntityTypeImpl();
		return entityType;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public StructuralVariation createStructuralVariation() {
		StructuralVariationImpl structuralVariation = new StructuralVariationImpl();
		return structuralVariation;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public PList createPList() {
		PListImpl pList = new PListImpl();
		return pList;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Reference createReference() {
		ReferenceImpl reference = new ReferenceImpl();
		return reference;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Aggregate createAggregate() {
		AggregateImpl aggregate = new AggregateImpl();
		return aggregate;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public PrimitiveType createPrimitiveType() {
		PrimitiveTypeImpl primitiveType = new PrimitiveTypeImpl();
		return primitiveType;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Null createNull() {
		NullImpl null_ = new NullImpl();
		return null_;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public RelationshipType createRelationshipType() {
		RelationshipTypeImpl relationshipType = new RelationshipTypeImpl();
		return relationshipType;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public PMap createPMap() {
		PMapImpl pMap = new PMapImpl();
		return pMap;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public PSet createPSet() {
		PSetImpl pSet = new PSetImpl();
		return pSet;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public PTuple createPTuple() {
		PTupleImpl pTuple = new PTupleImpl();
		return pTuple;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public NoSQLSchemaPackage getNoSQLSchemaPackage() {
		return (NoSQLSchemaPackage)getEPackage();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
  @Deprecated
  public static NoSQLSchemaPackage getPackage() {
		return NoSQLSchemaPackage.eINSTANCE;
	}

} //NoSQLSchemaFactoryImpl
