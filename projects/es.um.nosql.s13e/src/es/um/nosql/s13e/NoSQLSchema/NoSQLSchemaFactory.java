/**
 */
package es.um.nosql.s13e.NoSQLSchema;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage
 * @generated
 */
public interface NoSQLSchemaFactory extends EFactory {
  /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  NoSQLSchemaFactory eINSTANCE = es.um.nosql.s13e.NoSQLSchema.impl.NoSQLSchemaFactoryImpl.init();

  /**
	 * Returns a new object of class '<em>No SQL Schema</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>No SQL Schema</em>'.
	 * @generated
	 */
  NoSQLSchema createNoSQLSchema();

  /**
	 * Returns a new object of class '<em>Entity Class</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Class</em>'.
	 * @generated
	 */
  EntityClass createEntityClass();

  /**
	 * Returns a new object of class '<em>Structural Variation</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Structural Variation</em>'.
	 * @generated
	 */
  StructuralVariation createStructuralVariation();

  /**
	 * Returns a new object of class '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute</em>'.
	 * @generated
	 */
  Attribute createAttribute();

  /**
	 * Returns a new object of class '<em>PList</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>PList</em>'.
	 * @generated
	 */
  PList createPList();

  /**
	 * Returns a new object of class '<em>Reference</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference</em>'.
	 * @generated
	 */
  Reference createReference();

  /**
	 * Returns a new object of class '<em>Aggregate</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Aggregate</em>'.
	 * @generated
	 */
  Aggregate createAggregate();

  /**
	 * Returns a new object of class '<em>Primitive Type</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Type</em>'.
	 * @generated
	 */
  PrimitiveType createPrimitiveType();

  /**
	 * Returns a new object of class '<em>Null</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Null</em>'.
	 * @generated
	 */
  Null createNull();

  /**
	 * Returns a new object of class '<em>Reference Class</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference Class</em>'.
	 * @generated
	 */
  ReferenceClass createReferenceClass();

  /**
	 * Returns a new object of class '<em>PMap</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>PMap</em>'.
	 * @generated
	 */
  PMap createPMap();

  /**
	 * Returns a new object of class '<em>PSet</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>PSet</em>'.
	 * @generated
	 */
  PSet createPSet();

  /**
	 * Returns a new object of class '<em>PTuple</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>PTuple</em>'.
	 * @generated
	 */
  PTuple createPTuple();

  /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
  NoSQLSchemaPackage getNoSQLSchemaPackage();

} //NoSQLSchemaFactory
