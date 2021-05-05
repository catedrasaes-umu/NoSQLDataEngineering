OneOfEach_Attributes = db.getSiblingDB("OneOfEach_Attributes")

OneOfEach_Attributes.createCollection("EntityToAdd")
OneOfEach_Attributes.EntityToAdd.updateMany({}, [{$addFields: { "_id": ""}}], {upsert: true})

OneOfEach_Attributes.getCollectionNames().forEach(function(collName)
{
  OneOfEach_Attributes[collName].bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrSinTipo": null}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrStr": ""}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrNum": NumberInt(0)}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrDouble": 0.0}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrBool": false}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrSet": []}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrList": []}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrTuple": []}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrMap": {$literal: {}}}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrNull": null}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrStrVal": "initValue"}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrNumVal": NumberInt(33)}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrDoubleVal": 33.1}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "addAttrBoolVal": false}}], "upsert": true}}
  ])
})

OneOfEach_Attributes.createCollection("EntityToCast")
OneOfEach_Attributes.EntityToCast.updateMany({}, [{$addFields: { "_id": ObjectId(), "attrToCast": null, "attrToCastNull": null, "attrToCastStr": "", "attrToCastNum": NumberInt(0), "attrToCastDouble": 0.0, "attrToCastBool": false}}], {upsert: true})

OneOfEach_Attributes.EntityToCast.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCast": { $convert: { input: "$attrToCast", to: 8 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastNull": { $convert: { input: "$attrToCastNull", to: 16 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastStr": { $convert: { input: "$attrToCastStr", to: 8 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastNum": { $convert: { input: "$attrToCastNum", to: 2 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastDouble": { $convert: { input: "$attrToCastDouble", to: 8 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastBool": { $convert: { input: "$attrToCastBool", to: 2 }}}}]}}
])

