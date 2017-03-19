package es.um.nosql.schemainference.decisiontree.utils;

import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec;

public class ModelTree2
{
	private PropertySpec property;
	private EntityVersion tag;
	private ModelTree2 nodePresent;
	private ModelTree2 nodeAbsent;
	private Entity entity;

	public ModelTree2(Entity e, EntityVersion tag)
	{
		super();
		this.tag = tag;
		this.entity = e;
	}
	
	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public ModelTree2(PropertySpec property) {
		super();
		this.property = property;
	}

	public PropertySpec getProperty() {
		return property;
	}
	
	public void setProperty(PropertySpec property) {
		this.property = property;
	}
	
	public EntityVersion getTag() {
		return tag;
	}

	public void setTag(EntityVersion tag) {
		this.tag = tag;
	}

	public ModelTree2 getNodePresent() {
		return nodePresent;
	}

	public void setNodePresent(ModelTree2 nodePresent) {
		this.nodePresent = nodePresent;
	}

	public ModelTree2 getNodeAbsent() {
		return nodeAbsent;
	}

	public void setNodeAbsent(ModelTree2 nodeAbsent)
	{
		this.nodeAbsent = nodeAbsent;
	}

	public boolean is_leaf()
	{
		return nodePresent == null && nodeAbsent == null;
	}
}
