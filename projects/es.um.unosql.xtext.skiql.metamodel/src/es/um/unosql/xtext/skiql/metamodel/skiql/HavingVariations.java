/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Having Variations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations#getLowerBounds <em>Lower Bounds</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations#getUpperBounds <em>Upper Bounds</em>}</li>
 * </ul>
 *
 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getHavingVariations()
 * @model
 * @generated
 */
public interface HavingVariations extends HavingType {
	/**
	 * Returns the value of the '<em><b>Lower Bounds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bounds</em>' attribute.
	 * @see #setLowerBounds(int)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getHavingVariations_LowerBounds()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 * @generated
	 */
	int getLowerBounds();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations#getLowerBounds <em>Lower Bounds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bounds</em>' attribute.
	 * @see #getLowerBounds()
	 * @generated
	 */
	void setLowerBounds(int value);

	/**
	 * Returns the value of the '<em><b>Upper Bounds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bounds</em>' attribute.
	 * @see #setUpperBounds(int)
	 * @see es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage#getHavingVariations_UpperBounds()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 * @generated
	 */
	int getUpperBounds();

	/**
	 * Sets the value of the '{@link es.um.unosql.xtext.skiql.metamodel.skiql.HavingVariations#getUpperBounds <em>Upper Bounds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bounds</em>' attribute.
	 * @see #getUpperBounds()
	 * @generated
	 */
	void setUpperBounds(int value);

} // HavingVariations
