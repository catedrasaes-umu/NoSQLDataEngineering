package es.um.nosql.schemainference.decisiontree.utils;

import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.Property;

public class ModelTree {

	private Property property;
	private EntityVersion tag;
	private ModelTree nodePresent;
	private ModelTree nodeAbsent;

	public ModelTree(EntityVersion tag){
		super();
		this.tag = tag;
	}
	
	public ModelTree(Property property) {
		super();
		this.property = property;
	}

	public Property getProperty() {
		return property;
	}
	
	public void setProperty(Property property) {
		this.property = property;
	}
	
	public EntityVersion getTag() {
		return tag;
	}

	public void setTag(EntityVersion tag) {
		this.tag = tag;
	}

	public ModelTree getNodePresent() {
		return nodePresent;
	}

	public void setNodePresent(ModelTree nodePresent) {
		this.nodePresent = nodePresent;
	}

	public ModelTree getNodeAbsent() {
		return nodeAbsent;
	}

	public void setNodeAbsent(ModelTree nodeAbsent) {
		this.nodeAbsent = nodeAbsent;
	}

	public boolean is_leaft(){
		return ((this.nodePresent == null) & (this.nodeAbsent == null));
	}
}
