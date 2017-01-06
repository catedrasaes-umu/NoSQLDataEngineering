/**
 */
package es.um.nosql.schemainference.dsl4mongoose;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getValidators <em>Validators</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getUniques <em>Uniques</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getUpdates <em>Updates</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#isDiscriminator <em>Discriminator</em>}</li>
 *   <li>{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#getIndexes <em>Indexes</em>}</li>
 * </ul>
 *
 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getEntityParameter()
 * @model
 * @generated
 */
public interface EntityParameter extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Validators</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.schemainference.dsl4mongoose.Validator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Validators</em>' containment reference list.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getEntityParameter_Validators()
	 * @model containment="true"
	 * @generated
	 */
	EList<Validator> getValidators();

	/**
	 * Returns the value of the '<em><b>Uniques</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.schemainference.dsl4mongoose.Unique}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uniques</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uniques</em>' containment reference list.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getEntityParameter_Uniques()
	 * @model containment="true"
	 * @generated
	 */
	EList<Unique> getUniques();

	/**
	 * Returns the value of the '<em><b>Updates</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.schemainference.dsl4mongoose.Update}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Updates</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Updates</em>' containment reference list.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getEntityParameter_Updates()
	 * @model containment="true"
	 * @generated
	 */
	EList<Update> getUpdates();

	/**
	 * Returns the value of the '<em><b>Discriminator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Discriminator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Discriminator</em>' attribute.
	 * @see #setDiscriminator(boolean)
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getEntityParameter_Discriminator()
	 * @model
	 * @generated
	 */
	boolean isDiscriminator();

	/**
	 * Sets the value of the '{@link es.um.nosql.schemainference.dsl4mongoose.EntityParameter#isDiscriminator <em>Discriminator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Discriminator</em>' attribute.
	 * @see #isDiscriminator()
	 * @generated
	 */
	void setDiscriminator(boolean value);

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' containment reference list.
	 * The list contents are of type {@link es.um.nosql.schemainference.dsl4mongoose.Index}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexes</em>' containment reference list.
	 * @see es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage#getEntityParameter_Indexes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Index> getIndexes();

} // EntityParameter
