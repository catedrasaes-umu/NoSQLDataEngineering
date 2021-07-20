OneOfEach_References = db.getSiblingDB("OneOfEach_References")

OneOfEach_References.createCollection("EntityDummy")
OneOfEach_References.EntityDummy.updateMany({}, [{$addFields: { "_id": ""}}], {upsert: true})

OneOfEach_References.createCollection("EntityRefs")
OneOfEach_References.EntityRefs.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_References.EntityRefs.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "nullRefSimple": null}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "nullRefArray": []}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strRefSimple": ""}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strRefArray": [""]}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numRefSimple": NumberInt(0)}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numRefArray": [NumberInt(0)]}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "nullRefSimpleChange": null}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "nullRefArrayChange": []}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strRefSimpleChange": ""}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strRefArrayChange": [""]}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numRefSimpleChange": NumberInt(0)}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numRefArrayChange": [NumberInt(0)]}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "nullRefSimpleChange": [ "$nullRefSimpleChange" ]}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "nullRefArrayChange": { $arrayElemAt: [ "$nullRefArrayChange", 0] }}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strRefSimpleChange": [ "$strRefSimpleChange" ]}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strRefArrayChange": { $arrayElemAt: [ "$strRefArrayChange", 0] }}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numRefSimpleChange": [ "$numRefSimpleChange" ]}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numRefArrayChange": { $arrayElemAt: [ "$numRefArrayChange", 0] }}}]}}
])

OneOfEach_References.createCollection("EntityRef1")
OneOfEach_References.EntityRef1.updateMany({}, [{$addFields: { "_id": "refId1", "value1": "value1", "value2": "value2"}}], {upsert: true})

OneOfEach_References.createCollection("EntityRef2")
OneOfEach_References.EntityRef2.updateMany({}, [{$addFields: { "_id": "refId2", "value3": "value3", "value4": "value4"}}], {upsert: true})

OneOfEach_References.createCollection("EntityRef3")
OneOfEach_References.EntityRef3.updateMany({}, [{$addFields: { "_id": "refId3", "value5": "value6", "value6": "value6"}}], {upsert: true})

OneOfEach_References.createCollection("EntityRef4")
OneOfEach_References.EntityRef4.updateMany({}, [{$addFields: { "_id": "refId4", "value7": "value7", "value8": "value8"}}], {upsert: true})

OneOfEach_References.createCollection("EntityRef5")
OneOfEach_References.EntityRef5.updateMany({}, [{$addFields: { "_id": "refId5", "value9": "value9", "value10": "value10"}}], {upsert: true})

OneOfEach_References.createCollection("EntityMorphRef")
OneOfEach_References.EntityMorphRef.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_References.EntityMorphRef.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity1": "refId1"}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity2": "refId2"}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity3": "refId3"}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity4": ["refId4"]}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity5": ["refId5"]}}], "upsert": true}}
])

OneOfEach_References.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef1", localField: "refToEntity1", foreignField: "_id", as: "aggrEntity1" }},
  { $addFields: { "aggrEntity1": { $arrayElemAt: [ "$aggrEntity1", 0] }}},
  { $out: "EntityMorphRef" }
])

OneOfEach_References.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef2", localField: "refToEntity2", foreignField: "_id", as: "aggrEntity2" }},
  { $addFields: { "aggrEntity2": { $arrayElemAt: [ "$aggrEntity2", 0] }}},
  { $out: "EntityMorphRef" }
])
OneOfEach_References.EntityMorphRef.updateMany({}, { $unset: {"aggrEntity2._id": 1}})

OneOfEach_References.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef3", localField: "refToEntity3", foreignField: "_id", as: "aggrEntity3" }},
  { $addFields: { "aggrEntity3": { $arrayElemAt: [ "$aggrEntity3", 0] }}},
  { $out: "EntityMorphRef" }
])
OneOfEach_References.EntityRef3.drop()

OneOfEach_References.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef4", localField: "refToEntity4", foreignField: "_id", as: "aggrEntity4" }},
  { $out: "EntityMorphRef" }
])

OneOfEach_References.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef5", localField: "refToEntity5", foreignField: "_id", as: "aggrEntity5" }},
  { $out: "EntityMorphRef" }
])
OneOfEach_References.EntityMorphRef.updateMany({}, { $unset: {"aggrEntity5.$[]._id": 1}})
OneOfEach_References.EntityRef5.drop()

