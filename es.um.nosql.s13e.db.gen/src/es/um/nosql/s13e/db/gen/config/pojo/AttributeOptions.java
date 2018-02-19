package es.um.nosql.s13e.db.gen.config.pojo;

public class AttributeOptions
{
  private PrimitiveTypeOptions primitiveTypes;
  public void setPrimitiveTypes(PrimitiveTypeOptions primitiveTypes) {this.primitiveTypes = primitiveTypes;}
  public PrimitiveTypeOptions getPrimitiveTypes() {return this.primitiveTypes;}

  private TuplesOptions tuples;
  public void setTuples(TuplesOptions tuples) {this.tuples = tuples;}
  public TuplesOptions getTuples() {return this.tuples;}
}
