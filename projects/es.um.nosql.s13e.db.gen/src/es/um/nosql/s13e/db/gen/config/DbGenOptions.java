package es.um.nosql.s13e.db.gen.config;

import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class DbGenOptions
{
  private EntityOptions entities;
  public void setEntities(EntityOptions entities) {this.entities = entities;}
  public EntityOptions getEntities() {return this.entities;}

  private AttributeOptions attributes;
  public void setAttributes(AttributeOptions attributes) {this.attributes = attributes;}
  public AttributeOptions getAttributes() {return this.attributes;}

  private AssociationOptions associations;
  public void setAssociations(AssociationOptions associations) {this.associations = associations;}
  public AssociationOptions getAssociations() {return this.associations;}

  private InputOptions input;
  public void setInput(InputOptions input) {this.input = input;}
  public InputOptions getInput() {return this.input;}

  private OutputOptions output;
  public void setOutput(OutputOptions output) {this.output = output;}
  public OutputOptions getOutput() {return this.output;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getEntities() != null)     result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Entities:\n" + this.getEntities().toString());
    if (this.getAttributes() != null)   result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Attributes:\n" + this.getAttributes().toString());
    if (this.getAssociations() != null) result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Associations:\n" + this.getAssociations().toString());
    if (this.getInput() != null)        result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Input:\n" + this.getInput().toString());
    if (this.getOutput() != null)       result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Output:\n" + this.getOutput().toString());

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getEntities() != null)
      this.getEntities().doCheck();

    if (this.getAttributes() != null)
      this.getAttributes().doCheck();

    if (this.getAssociations() != null)
      this.getAssociations().doCheck();

    if (this.getInput() == null)
      throw new IllegalArgumentException("An \"input\" section is required to provide a valid input XMI file.");

    this.getInput().doCheck();

    if (this.getOutput() != null)
      this.getOutput().doCheck();

    return true;
  }
}
