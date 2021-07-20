OneOfEach_Entities = db.getSiblingDB("OneOfEach_Entities")

OneOfEach_Entities.createCollection("EntityAdded1")
OneOfEach_Entities.EntityAdded1.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Entities.createCollection("EntityAdded2")
OneOfEach_Entities.EntityAdded2.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Entities.createCollection("EntityAdded3")
OneOfEach_Entities.EntityAdded3.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Entities.EntityAdded2.drop()

OneOfEach_Entities.EntityAdded3.renameCollection("EntityRenamed")

OneOfEach_Entities.createCollection("EntityToSplit")
OneOfEach_Entities.EntityToSplit.updateMany({}, [{$addFields: { "_id": ObjectId(), "attr1": "", "attr2": "", "attr3": "", "attr4": "", "attr5": "", "attr6": ""}}], {upsert: true})

OneOfEach_Entities.EntityToSplit.aggregate([
  { $project: { "_id": 1, "attr1": 1, "attr2": 1, "attr3": 1 }},
  { $out: "EntitySplit1" }
])
OneOfEach_Entities.EntityToSplit.aggregate([
  { $project: { "_id": 1, "attr1": 1, "attr5": 1, "attr6": 1 }},
  { $out: "EntitySplit2" }
])
OneOfEach_Entities.EntityToSplit.drop()

OneOfEach_Entities.createCollection("EntityToMerge1")
OneOfEach_Entities.EntityToMerge1.updateMany({}, [{$addFields: { "_id": "id", "a": "", "b": NumberInt(0)}}], {upsert: true})

OneOfEach_Entities.createCollection("EntityToMerge2")
OneOfEach_Entities.EntityToMerge2.updateMany({}, [{$addFields: { "_id": "id", "c": [], "b": null}}], {upsert: true})

OneOfEach_Entities.EntityToMerge2.aggregate([{ $merge: { into: "EntityMerged", on: "_id", whenMatched: "merge", whenNotMatched: "insert" }}])
OneOfEach_Entities.EntityToMerge1.aggregate([{ $merge: { into: "EntityMerged", on: "_id", whenMatched: "merge", whenNotMatched: "insert" }}])
OneOfEach_Entities.EntityToMerge2.drop()
OneOfEach_Entities.EntityToMerge1.drop()

OneOfEach_Entities.createCollection("EntityToExtract")
OneOfEach_Entities.EntityToExtract.updateMany({}, [{$addFields: { "_id": ObjectId(), "feat1": null, "feat2": null, "feat3": null, "feat4": null}}], {upsert: true})

OneOfEach_Entities.EntityToExtract.aggregate([
  { $project: { "_id": 1, "feat2": 1, "feat4": 1 }},
  { $out: "EntityExtracted" }
])

