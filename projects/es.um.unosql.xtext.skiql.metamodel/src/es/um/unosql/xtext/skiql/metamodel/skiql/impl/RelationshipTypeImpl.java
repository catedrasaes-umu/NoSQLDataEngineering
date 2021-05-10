/**
 */
package es.um.unosql.xtext.skiql.metamodel.skiql.impl;

import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipType;
import es.um.unosql.xtext.skiql.metamodel.skiql.RelationshipTypeEnum;
import es.um.unosql.xtext.skiql.metamodel.skiql.SkiqlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relationship Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipTypeImpl#getTargetEntityName <em>Target Entity Name</em>}</li>
 *   <li>{@link es.um.unosql.xtext.skiql.metamodel.skiql.impl.RelationshipTypeImpl#getRelationType <em>Relation Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationshipTypeImpl extends TypeImpl implements RelationshipType {
	/**
	 * The default value of the '{@link #getTargetEntityName() <em>Target Entity Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetEntityName()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_ENTITY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetEntityName() <em>Target Entity Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetEntityName()
	 * @generated
	 * @ordered
	 */
	protected String targetEntityName = TARGET_ENTITY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRelationType() <em>Relation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationType()
	 * @generated
	 * @ordered
	 */
	protected static final RelationshipTypeEnum RELATION_TYPE_EDEFAULT = RelationshipTypeEnum.RELATIONSHIP;

	/**
	 * The cached value of the '{@link #getRelationType() <em>Relation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationType()
	 * @generated
	 * @ordered
	 */
	protected RelationshipTypeEnum relationType = RELATION_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationshipTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SkiqlPackage.Literals.RELATIONSHIP_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTargetEntityName() {
		return targetEntityName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetEntityName(String newTargetEntityName) {
		String oldTargetEntityName = targetEntityName;
		targetEntityName = newTargetEntityName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_TYPE__TARGET_ENTITY_NAME, oldTargetEntityName, targetEntityName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationshipTypeEnum getRelationType() {
		return relationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRelationType(RelationshipTypeEnum newRelationType) {
		RelationshipTypeEnum oldRelationType = relationType;
		relationType = newRelationType == null ? RELATION_TYPE_EDEFAULT : newRelationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SkiqlPackage.RELATIONSHIP_TYPE__RELATION_TYPE, oldRelationType, relationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_TYPE__TARGET_ENTITY_NAME:
				return getTargetEntityName();
			case SkiqlPackage.RELATIONSHIP_TYPE__RELATION_TYPE:
				return getRelationType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_TYPE__TARGET_ENTITY_NAME:
				setTargetEntityName((String)newValue);
				return;
			case SkiqlPackage.RELATIONSHIP_TYPE__RELATION_TYPE:
				setRelationType((RelationshipTypeEnum)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_TYPE__TARGET_ENTITY_NAME:
				setTargetEntityName(TARGET_ENTITY_NAME_EDEFAULT);
				return;
			case SkiqlPackage.RELATIONSHIP_TYPE__RELATION_TYPE:
				setRelationType(RELATION_TYPE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SkiqlPackage.RELATIONSHIP_TYPE__TARGET_ENTITY_NAME:
				return TARGET_ENTITY_NAME_EDEFAULT == null ? targetEntityName != null : !TARGET_ENTITY_NAME_EDEFAULT.equals(targetEntityName);
			case SkiqlPackage.RELATIONSHIP_TYPE__RELATION_TYPE:
				return relationType != RELATION_TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (targetEntityName: ");
		result.append(targetEntityName);
		result.append(", relationType: ");
		result.append(relationType);
		result.append(')');
		return result.toString();
	}

} //RelationshipTypeImpl
