package es.um.nosql.schemainference.db.pojo.protein;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"PDB id", "PDB chain", "Cluster Number", "Identifier_for_representative_protein(chain)", "Variant in PDB", "Alternate format for PDB variant", "ddG",
  "UniProt Id", "Mapped Variant in UniProt", "Alternate format for UniProt variant", "Gene Symbol", "Entrez Gene ID", "Link to PDB", "Link to UniProt", "Link to Entrez Gene", "Comments"})
public class Protein
{
  private String PDB_id;

  private String PDB_chain;

  private String Cluster_Number;

  private String Identifier_for_representative_protein_chain;

  private String Variant_in_PDB;

  private String Alternate_format_for_PDB_variant;

  private String ddG;

  private String UniProt_Id;

  private String Mapped_Variant_in_UniProt;

  private String Alternate_format_for_UniProt_variant;

  private String Gene_Symbol;

  private Integer Entrez_Gene_ID;

  private String Link_to_PDB;

  private String Link_to_UniProt;

  private String Link_to_Entrez_Gene;

  private String Comments;

  @JsonProperty("PDB id")
  public String getPDB_id() {
    return PDB_id;
  }

  public void setPDB_id(String pDB_id) {
    PDB_id = pDB_id;
  }

  @JsonProperty("PDB chain")
  public String getPDB_chain() {
    return PDB_chain;
  }

  public void setPDB_chain(String pDB_chain) {
    PDB_chain = pDB_chain;
  }

  @JsonProperty("Cluster Number")
  public String getCluster_Number() {
    return Cluster_Number;
  }

  public void setCluster_Number(String cluster_Number) {
    Cluster_Number = cluster_Number;
  }

  @JsonProperty("Identifier_for_representative_protein(chain)")
  public String getIdentifier_for_representative_protein_chain() {
    return Identifier_for_representative_protein_chain;
  }

  public void setIdentifier_for_representative_protein_chain(String identifier_for_representative_protein_chain) {
    Identifier_for_representative_protein_chain = identifier_for_representative_protein_chain;
  }

  @JsonProperty("Variant in PDB")
  public String getVariant_in_PDB() {
    return Variant_in_PDB;
  }

  public void setVariant_in_PDB(String variant_in_PDB) {
    Variant_in_PDB = variant_in_PDB;
  }

  @JsonProperty("Alternate format for PDB variant")
  public String getAlternate_format_for_PDB_variant() {
    return Alternate_format_for_PDB_variant;
  }

  public void setAlternate_format_for_PDB_variant(String alternate_format_for_PDB_variant) {
    Alternate_format_for_PDB_variant = alternate_format_for_PDB_variant;
  }

  @JsonProperty("ddG")
  public String getDdG() {
    return ddG;
  }

  public void setDdG(String ddG) {
    this.ddG = ddG;
  }

  @JsonProperty("UniProt Id")
  public String getUniProt_Id() {
    return UniProt_Id;
  }

  public void setUniProt_Id(String uniProt_Id) {
    UniProt_Id = uniProt_Id;
  }

  @JsonProperty("Mapped Variant in UniProt")
  public String getMapped_Variant_in_UniProt() {
    return Mapped_Variant_in_UniProt;
  }

  public void setMapped_Variant_in_UniProt(String mapped_Variant_in_UniProt) {
    Mapped_Variant_in_UniProt = mapped_Variant_in_UniProt;
  }

  @JsonProperty("Alternate format for UniProt variant")
  public String getAlternate_format_for_UniProt_variant() {
    return Alternate_format_for_UniProt_variant;
  }

  public void setAlternate_format_for_UniProt_variant(String alternate_format_for_UniProt_variant) {
    Alternate_format_for_UniProt_variant = alternate_format_for_UniProt_variant;
  }

  @JsonProperty("Gene Symbol")
  public String getGene_Symbol() {
    return Gene_Symbol;
  }

  public void setGene_Symbol(String gene_Symbol) {
    Gene_Symbol = gene_Symbol;
  }

  @JsonProperty("Entrez Gene ID")
  public Integer getEntrez_Gene_ID() {
    return Entrez_Gene_ID;
  }

  public void setEntrez_Gene_ID(Integer entrez_Gene_ID) {
    Entrez_Gene_ID = entrez_Gene_ID;
  }

  @JsonProperty("Link to PDB")
  public String getLink_to_PDB() {
    return Link_to_PDB;
  }

  public void setLink_to_PDB(String link_to_PDB) {
    Link_to_PDB = link_to_PDB;
  }

  @JsonProperty("Link to UniProt")
  public String getLink_to_UniProt() {
    return Link_to_UniProt;
  }

  public void setLink_to_UniProt(String link_to_UniProt) {
    Link_to_UniProt = link_to_UniProt;
  }

  @JsonProperty("Link to Entrez Gene")
  public String getLink_to_Entrez_Gene() {
    return Link_to_Entrez_Gene;
  }

  public void setLink_to_Entrez_Gene(String link_to_Entrez_Gene) {
    Link_to_Entrez_Gene = link_to_Entrez_Gene;
  }

  @JsonProperty("Comments")
  public String getComments() {
    return Comments;
  }

  public void setComments(String comments) {
    Comments = comments;
  }
}
