/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>After</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.After#getDateTime <em>Date Time</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getAfter()
 * @model
 * @generated
 */
public interface After extends VersionHistory {
	/**
	 * Returns the value of the '<em><b>Date Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Date Time</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date Time</em>' containment reference.
	 * @see #setDateTime(DateTime)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getAfter_DateTime()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DateTime getDateTime();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.After#getDateTime <em>Date Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date Time</em>' containment reference.
	 * @see #getDateTime()
	 * @generated
	 */
	void setDateTime(DateTime value);

} // After
