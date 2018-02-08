'use strict'

var mongoose = require('mongoose');

var ProteinsSchema = new mongoose.Schema({
  Alternate_format_for_PDB_variant: {type: String, required: true},
  Alternate_format_for_UniProt_variant: String,
  Cluster_Number: {type: String, required: true},
  Comments: String,
  Entrez_Gene_ID: Number,
  Gene_Symbol: String,
  Identifier_for_representative_protein_chain: {type: String, required: true},
  Link_to_Entrez_Gene: String,
  Link_to_PDB: {type: String, required: true},
  Link_to_UniProt: String,
  Mapped_Variant_in_UniProt: String,
  PDB_chain: {type: String, required: true},
  PDB_id: {type: String, required: true},
  UniProt_Id: String,
  Variant_in_PDB: {type: String, required: true},
  _id: {type: mongoose.Schema.Types.ObjectId, required: true},
  ddG: {type: String, required: true}
}, { versionKey: false, collection: 'proteins'});


module.exports = mongoose.model('Proteins', ProteinsSchema);
