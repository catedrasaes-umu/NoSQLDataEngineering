/**
 */
package es.um.nosql.schemainference.dsl4mongoose;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mongoose Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.MongooseModel#getParameters <em>Parameters</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.MongooseModel#getMapper <em>Mapper</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getMongooseModel()
 * @model
 * @generated
 */
public interface MongooseModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getMongooseModel_Parameters()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<EntityParameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Mapper</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapper</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapper</em>' attribute.
	 * @see #setMapper(String)
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getMongooseModel_Mapper()
	 * @model
	 * @generated
	 */
	String getMapper();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.dsl4mongoose.MongooseModel#getMapper <em>Mapper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapper</em>' attribute.
	 * @see #getMapper()
	 * @generated
	 */
	void setMapper(String value);

} // MongooseModel
