/**
 */
package es.um.nosql.streaminginference.NoSQLSchema;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.streaminginference.NoSQLSchema.Tuple#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage#getTuple()
 * @model
 * @generated
 */
public interface Tuple extends Type {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.streaminginference.NoSQLSchema.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage#getTuple_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Type> getElements();

} // Tuple
