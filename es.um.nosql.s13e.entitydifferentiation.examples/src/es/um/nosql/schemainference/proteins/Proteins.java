package es.um.nosql.s13e.proteins;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "proteins", noClassnameStored = true)
public class Proteins
{
  @Property
  @NotNull(message = "Alternate_format_for_PDB_variant can't be null")
  private String Alternate_format_for_PDB_variant;
  public String getAlternate_format_for_PDB_variant() {return this.Alternate_format_for_PDB_variant;}
  public void setAlternate_format_for_PDB_variant(String Alternate_format_for_PDB_variant) {this.Alternate_format_for_PDB_variant = Alternate_format_for_PDB_variant;}
  
  @Property
  private String Alternate_format_for_UniProt_variant;
  public String getAlternate_format_for_UniProt_variant() {return this.Alternate_format_for_UniProt_variant;}
  public void setAlternate_format_for_UniProt_variant(String Alternate_format_for_UniProt_variant) {this.Alternate_format_for_UniProt_variant = Alternate_format_for_UniProt_variant;}
  
  @Property
  @NotNull(message = "Cluster_Number can't be null")
  private String Cluster_Number;
  public String getCluster_Number() {return this.Cluster_Number;}
  public void setCluster_Number(String Cluster_Number) {this.Cluster_Number = Cluster_Number;}
  
  @Property
  private String Comments;
  public String getComments() {return this.Comments;}
  public void setComments(String Comments) {this.Comments = Comments;}
  
  @Property
  private Integer Entrez_Gene_ID;
  public Integer getEntrez_Gene_ID() {return this.Entrez_Gene_ID;}
  public void setEntrez_Gene_ID(Integer Entrez_Gene_ID) {this.Entrez_Gene_ID = Entrez_Gene_ID;}
  
  @Property
  private String Gene_Symbol;
  public String getGene_Symbol() {return this.Gene_Symbol;}
  public void setGene_Symbol(String Gene_Symbol) {this.Gene_Symbol = Gene_Symbol;}
  
  @Property
  @NotNull(message = "Identifier_for_representative_protein_chain can't be null")
  private String Identifier_for_representative_protein_chain;
  public String getIdentifier_for_representative_protein_chain() {return this.Identifier_for_representative_protein_chain;}
  public void setIdentifier_for_representative_protein_chain(String Identifier_for_representative_protein_chain) {this.Identifier_for_representative_protein_chain = Identifier_for_representative_protein_chain;}
  
  @Property
  private String Link_to_Entrez_Gene;
  public String getLink_to_Entrez_Gene() {return this.Link_to_Entrez_Gene;}
  public void setLink_to_Entrez_Gene(String Link_to_Entrez_Gene) {this.Link_to_Entrez_Gene = Link_to_Entrez_Gene;}
  
  @Property
  @NotNull(message = "Link_to_PDB can't be null")
  private String Link_to_PDB;
  public String getLink_to_PDB() {return this.Link_to_PDB;}
  public void setLink_to_PDB(String Link_to_PDB) {this.Link_to_PDB = Link_to_PDB;}
  
  @Property
  private String Link_to_UniProt;
  public String getLink_to_UniProt() {return this.Link_to_UniProt;}
  public void setLink_to_UniProt(String Link_to_UniProt) {this.Link_to_UniProt = Link_to_UniProt;}
  
  @Property
  private String Mapped_Variant_in_UniProt;
  public String getMapped_Variant_in_UniProt() {return this.Mapped_Variant_in_UniProt;}
  public void setMapped_Variant_in_UniProt(String Mapped_Variant_in_UniProt) {this.Mapped_Variant_in_UniProt = Mapped_Variant_in_UniProt;}
  
  @Property
  @NotNull(message = "PDB_chain can't be null")
  private String PDB_chain;
  public String getPDB_chain() {return this.PDB_chain;}
  public void setPDB_chain(String PDB_chain) {this.PDB_chain = PDB_chain;}
  
  @Property
  @NotNull(message = "PDB_id can't be null")
  private String PDB_id;
  public String getPDB_id() {return this.PDB_id;}
  public void setPDB_id(String PDB_id) {this.PDB_id = PDB_id;}
  
  @Property
  private String UniProt_Id;
  public String getUniProt_Id() {return this.UniProt_Id;}
  public void setUniProt_Id(String UniProt_Id) {this.UniProt_Id = UniProt_Id;}
  
  @Property
  @NotNull(message = "Variant_in_PDB can't be null")
  private String Variant_in_PDB;
  public String getVariant_in_PDB() {return this.Variant_in_PDB;}
  public void setVariant_in_PDB(String Variant_in_PDB) {this.Variant_in_PDB = Variant_in_PDB;}
  
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "ddG can't be null")
  private String ddG;
  public String getDdG() {return this.ddG;}
  public void setDdG(String ddG) {this.ddG = ddG;}
}
