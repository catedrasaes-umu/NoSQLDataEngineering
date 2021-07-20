db = db.getMongo().startSession()
OneOfEach_Phased = db.getDatabase("OneOfEach_Phased")

OneOfEach_Phased.createCollection("EntityAdded1")
OneOfEach_Phased.EntityAdded1.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityAdded2")
OneOfEach_Phased.EntityAdded2.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityAdded3")
OneOfEach_Phased.EntityAdded3.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Phased.EntityAdded2.drop()

OneOfEach_Phased.EntityAdded3.renameCollection("EntityRenamed")

OneOfEach_Phased.createCollection("EntityToSplit")
OneOfEach_Phased.EntityToSplit.updateMany({}, [{$addFields: { "_id": ObjectId(), "attr1": "", "attr2": "", "attr3": "", "attr4": "", "attr5": "", "attr6": ""}}], {upsert: true})

OneOfEach_Phased.EntityToSplit.aggregate([
  { $project: { "_id": 1, "attr1": 1, "attr2": 1, "attr3": 1 }},
  { $out: "EntitySplit1" }
])
OneOfEach_Phased.EntityToSplit.aggregate([
  { $project: { "_id": 1, "attr1": 1, "attr5": 1, "attr6": 1 }},
  { $out: "EntitySplit2" }
])
OneOfEach_Phased.EntityToSplit.drop()

OneOfEach_Phased.createCollection("EntityToMerge1")
OneOfEach_Phased.EntityToMerge1.updateMany({}, [{$addFields: { "_id": "id", "a": "", "b": NumberInt(0)}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToMerge2")
OneOfEach_Phased.EntityToMerge2.updateMany({}, [{$addFields: { "_id": "id", "c": [], "b": null}}], {upsert: true})

OneOfEach_Phased.EntityToMerge2.aggregate([{ $merge: { into: "EntityMerged", on: "_id", whenMatched: "merge", whenNotMatched: "insert" }}])
OneOfEach_Phased.EntityToMerge1.aggregate([{ $merge: { into: "EntityMerged", on: "_id", whenMatched: "merge", whenNotMatched: "insert" }}])
OneOfEach_Phased.EntityToMerge2.drop()
OneOfEach_Phased.EntityToMerge1.drop()

OneOfEach_Phased.createCollection("EntityToExtract")
OneOfEach_Phased.EntityToExtract.updateMany({}, [{$addFields: { "_id": ObjectId(), "feat1": null, "feat2": null, "feat3": null, "feat4": null}}], {upsert: true})

OneOfEach_Phased.EntityToExtract.aggregate([
  { $project: { "_id": 1, "feat2": 1, "feat4": 1 }},
  { $out: "EntityExtracted" }
])

OneOfEach_Phased.createCollection("EntityToRemove")
OneOfEach_Phased.EntityToRemove.updateMany({}, [{$addFields: { "_id": ObjectId(), "attrToDel": null, "attrToDelStr": "", "attrToDelNum": NumberInt(0), "attrToDelDouble": 0.0, "attrToDelBool": false, "attrToRen": null, "attrToRenStr": "", "attrToRenNum": NumberInt(0), "attrToRenDouble": 0.0, "attrToRenBool": false}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToCopySource1")
OneOfEach_Phased.EntityToCopySource1.updateMany({}, [{$addFields: { "_id": "id1", "valueToCopy1": "COPY_THIS", "valueToCopy2": [], "valueToCopy3": {$literal: {}}}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToCopySource2")
OneOfEach_Phased.EntityToCopySource2.updateMany({}, [{$addFields: { "_id": "id2", "valueToCopy4": "COPY_THAT"}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToCopyTarget")
OneOfEach_Phased.EntityToCopyTarget.updateMany({}, [{$addFields: { "_id": "", "thisRef": "id1", "thatRef": "id2"}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToMoveSource1")
OneOfEach_Phased.EntityToMoveSource1.updateMany({}, [{$addFields: { "_id": "id1", "valueToMove1": "MOVE_THIS", "valueToMove2": [], "valueToMove3": {$literal: {}}}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToMoveSource2")
OneOfEach_Phased.EntityToMoveSource2.updateMany({}, [{$addFields: { "_id": "id2", "valueToMove4": "MOVE_THAT"}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToMoveTarget")
OneOfEach_Phased.EntityToMoveTarget.updateMany({}, [{$addFields: { "_id": "", "thisRef": "id1", "thatRef": "id2"}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToNest")
OneOfEach_Phased.EntityToNest.updateMany({}, [{$addFields: { "_id": "", "name": null, "age": null, "street": null, "postalCode": null, "city": null, "aList": [], "aMap": {$literal: {}}}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToUnnest")
OneOfEach_Phased.EntityToUnnest.updateMany({}, [{$addFields: { "_id": "", "name": null, "age": null}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToAdd")
OneOfEach_Phased.EntityToAdd.updateMany({}, [{$addFields: { "_id": ""}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToCast")
OneOfEach_Phased.EntityToCast.updateMany({}, [{$addFields: { "_id": ObjectId(), "attrToCast": null, "attrToCastNull": null, "attrToCastStr": "", "attrToCastNum": NumberInt(0), "attrToCastDouble": 0.0, "attrToCastBool": false}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityToAggr")
OneOfEach_Phased.EntityToAggr.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityMorphAggr")
OneOfEach_Phased.EntityMorphAggr.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityDummy")
OneOfEach_Phased.EntityDummy.updateMany({}, [{$addFields: { "_id": ""}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityRefs")
OneOfEach_Phased.EntityRefs.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityRef1")
OneOfEach_Phased.EntityRef1.updateMany({}, [{$addFields: { "_id": "refId1", "value1": "value1", "value2": "value2"}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityRef2")
OneOfEach_Phased.EntityRef2.updateMany({}, [{$addFields: { "_id": "refId2", "value3": "value3", "value4": "value4"}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityRef3")
OneOfEach_Phased.EntityRef3.updateMany({}, [{$addFields: { "_id": "refId3", "value5": "value6", "value6": "value6"}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityRef4")
OneOfEach_Phased.EntityRef4.updateMany({}, [{$addFields: { "_id": "refId4", "value7": "value7", "value8": "value8"}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityRef5")
OneOfEach_Phased.EntityRef5.updateMany({}, [{$addFields: { "_id": "refId5", "value9": "value9", "value10": "value10"}}], {upsert: true})

OneOfEach_Phased.createCollection("EntityMorphRef")
OneOfEach_Phased.EntityMorphRef.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

db.startTransaction()

OneOfEach_Phased.EntityToRemove.bulkWrite([
  {updateMany: {"filter": {}, "update": {$unset: {"attrToDel": 1 }}}},
  {updateMany: {"filter": {}, "update": {$unset: {"attrToDelStr": 1 }}}},
  {updateMany: {"filter": {}, "update": {$unset: {"attrToDelNum": 1 }}}},
  {updateMany: {"filter": {}, "update": {$unset: {"attrToDelDouble": 1 }}}},
  {updateMany: {"filter": {}, "update": {$unset: {"attrToDelBool": 1 }}}},
  {updateMany: {"filter": {}, "update": {$rename: {"attrToRen": "attrTrue1" }}}},
  {updateMany: {"filter": {}, "update": {$rename: {"attrToRenStr": "attrTrue2" }}}},
  {updateMany: {"filter": {}, "update": {$rename: {"attrToRenNum": "attrTrue3" }}}},
  {updateMany: {"filter": {}, "update": {$rename: {"attrToRenDouble": "attrTrue4" }}}},
  {updateMany: {"filter": {}, "update": {$rename: {"attrToRenBool": "attrTrue5" }}}}
])

OneOfEach_Phased.EntityToCopySource1.find().forEach( function(sDoc) { OneOfEach_Phased.EntityToCopyTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "copy1": sDoc.valueToCopy1} }); })

OneOfEach_Phased.EntityToCopySource1.find().forEach( function(sDoc) { OneOfEach_Phased.EntityToCopyTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "copy2": sDoc.valueToCopy2} }); })

OneOfEach_Phased.EntityToCopySource1.find().forEach( function(sDoc) { OneOfEach_Phased.EntityToCopyTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "copy3": sDoc.valueToCopy3} }); })

OneOfEach_Phased.EntityToCopySource2.find().forEach( function(sDoc) { OneOfEach_Phased.EntityToCopyTarget.updateMany({ "thatRef": sDoc._id }, { $set: { "valueToCopy4": sDoc.valueToCopy4} }); })

OneOfEach_Phased.EntityToMoveSource1.find().forEach( function(sDoc) { OneOfEach_Phased.EntityToMoveTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "move1": sDoc.valueToMove1} }); })
OneOfEach_Phased.EntityToMoveSource1.updateMany({}, {$unset: {"valueToMove1": 1}})

OneOfEach_Phased.EntityToMoveSource1.find().forEach( function(sDoc) { OneOfEach_Phased.EntityToMoveTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "move2": sDoc.valueToMove2} }); })
OneOfEach_Phased.EntityToMoveSource1.updateMany({}, {$unset: {"valueToMove2": 1}})

OneOfEach_Phased.EntityToMoveSource1.find().forEach( function(sDoc) { OneOfEach_Phased.EntityToMoveTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "move3": sDoc.valueToMove3} }); })
OneOfEach_Phased.EntityToMoveSource1.updateMany({}, {$unset: {"valueToMove3": 1}})

OneOfEach_Phased.EntityToMoveSource2.find().forEach( function(sDoc) { OneOfEach_Phased.EntityToMoveTarget.updateMany({ "thatRef": sDoc._id }, { $set: { "move4": sDoc.valueToMove4} }); })
OneOfEach_Phased.EntityToMoveSource2.updateMany({}, {$unset: {"valueToMove4": 1}})

OneOfEach_Phased.EntityToNest.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "nestAggrNest1": {"nest1": null, "nest2": null, "nest3": null}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "nestAggrNest2": [{"nest4": null, "nest5": null, "nest6": null}]}}], upsert: true}},
  {updateMany: {"filter": {}, "update": {$rename: {"street": "nestedAggr.street", "postalCode": "nestedAggr.postalCode", "city": "nestedAggr.city", "nestAggrNest1": "nestedAggr.nestAggrNest1", "nestAggrNest2": "nestedAggr.nestAggrNest2"}}}}
])

OneOfEach_Phased.EntityToUnnest.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "unnestAggrNest1": {"nest1": null, "nest2": null, "nest3": null, "street": null, "postalCode": null, "city": null, "aList": [], "aMap": {$literal: {}}}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "unnestAggrNest2": {"nest4": null, "nest5": null, "nest6": null}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": {$rename: {"unnestAggrNest1.street": "street", "unnestAggrNest1.postalCode": "postalCode", "unnestAggrNest1.city": "city", "unnestAggrNest1.aList": "aList", "unnestAggrNest1.aMap": "aMap", "unnestAggrNest2.nest4": "nest4"}}}}
])

db.commitTransaction()

OneOfEach_Phased.getCollectionNames().forEach(function(collName)
{
  OneOfEach_Phased[collName].bulkWrite([
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

db.startTransaction()

OneOfEach_Phased.EntityToCast.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCast": { $convert: { input: "$attrToCast", to: 8 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastNull": { $convert: { input: "$attrToCastNull", to: 16 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastStr": { $convert: { input: "$attrToCastStr", to: 8 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastNum": { $convert: { input: "$attrToCastNum", to: 2 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastDouble": { $convert: { input: "$attrToCastDouble", to: 8 }}}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "attrToCastBool": { $convert: { input: "$attrToCastBool", to: 2 }}}}]}}
])

db.commitTransaction()

db.startTransaction()

OneOfEach_Phased.EntityToAggr.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrSimple": {"attr1": "", "attr2": NumberInt(0), "attr3": {$literal: {}}, "attr4": []}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrList": [{"attr1": "", "attr2": NumberInt(0), "attr3": {$literal: {}}, "attr4": []}]}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrSimple.innerAggrSimple": {"innerAttr1": "", "innerAttr2": NumberInt(0), "innerAttr3": {$literal: {}}, "innerAttr4": []}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrSimple.innerAggrList": [{"innerAttr1": "", "innerAttr2": NumberInt(0), "innerAttr3": {$literal: {}}, "innerAttr4": []}]}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strAggrSimpleChange": {"attr1": "", "attr2": null}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strAggrArrayChange": [{"attr1": "", "attr2": null}]}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numAggrSimpleChange": {"attr1": NumberInt(0), "attr2": null}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numAggrArrayChange": [{"attr1": NumberInt(0), "attr2": null}]}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strAggrSimpleChange": [ "$strAggrSimpleChange" ]}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "strAggrArrayChange": { $arrayElemAt: [ "$strAggrArrayChange", 0] }}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numAggrSimpleChange": [ "$numAggrSimpleChange" ]}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "numAggrArrayChange": { $arrayElemAt: [ "$numAggrArrayChange", 0] }}}]}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrByHand1": {"string1": ""}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrByHand1.nullAttrSimpleChange": null}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrByHand1.nullAttrArrayChange": []}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrByHand1.strAttrSimpleChange": "ref1"}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrByHand1.strAttrArrayChange": ["ref1"]}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrByHand2": {"string2": ""}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrByHand2.numAttrSimpleChange": NumberInt(33)}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrByHand2.numAttrArrayChange": [NumberInt(44)]}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggrByHand1": [ "$aggrByHand1" ]}}]}}
])

OneOfEach_Phased.EntityMorphAggr.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggr1": {"string1": "", "string2": ""}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggr2": {"aList": [], "aMap": {$literal: {}}}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggr3": [{"string5": "", "string6": ""}]}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggr4": [{"aSet": [], "aTuple": []}]}}], upsert: true}}
])

db.commitTransaction()

db.startTransaction()

OneOfEach_Phased.EntityRefs.bulkWrite([
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

OneOfEach_Phased.EntityMorphRef.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity1": "refId1"}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity2": "refId2"}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity3": "refId3"}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity4": ["refId4"]}}], "upsert": true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "refToEntity5": ["refId5"]}}], "upsert": true}}
])

db.commitTransaction()

OneOfEach_Phased.EntityMorphAggr.find().forEach(function(sDoc) {
    if (!sDoc.aggr1.hasOwnProperty('_id'))
      sDoc.aggr1._id = ObjectId().str;

    OneOfEach_Phased.Aggr1.insert(sDoc.aggr1);
    OneOfEach_Phased.EntityMorphAggr.updateMany({}, {$set: {"refToAggr1": sDoc.aggr1._id }})
});

OneOfEach_Phased.EntityMorphAggr.find().forEach(function(sDoc) {
    if (!sDoc.aggr2.hasOwnProperty('_id'))
      sDoc.aggr2._id = ObjectId().str;

    OneOfEach_Phased.Aggr2.insert(sDoc.aggr2);
    OneOfEach_Phased.EntityMorphAggr.updateMany({}, {$set: {"refToAggr2": sDoc.aggr2._id }})
});

OneOfEach_Phased.EntityMorphAggr.find().forEach(function(sDoc) {
    var ids = [];
    sDoc.aggr3.forEach(function(i)
    {
      if (!i.hasOwnProperty('_id'))
        i._id = ObjectId().str;

      ids.push(i._id);
    });
    OneOfEach_Phased.Aggr3.insertMany(sDoc.aggr3);
    OneOfEach_Phased.EntityMorphAggr.updateMany({}, {$set: {"refToAggr3": ids}});
});

OneOfEach_Phased.EntityMorphAggr.find().forEach(function(sDoc) {
    var ids = [];
    sDoc.aggr4.forEach(function(i)
    {
      if (!i.hasOwnProperty('_id'))
        i._id = ObjectId().str;

      ids.push(i._id);
    });
    OneOfEach_Phased.Aggr4.insertMany(sDoc.aggr4);
    OneOfEach_Phased.EntityMorphAggr.updateMany({}, {$set: {"refToAggr4": ids}});
});

OneOfEach_Phased.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef1", localField: "refToEntity1", foreignField: "_id", as: "aggrEntity1" }},
  { $addFields: { "aggrEntity1": { $arrayElemAt: [ "$aggrEntity1", 0] }}},
  { $out: "EntityMorphRef" }
])

OneOfEach_Phased.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef2", localField: "refToEntity2", foreignField: "_id", as: "aggrEntity2" }},
  { $addFields: { "aggrEntity2": { $arrayElemAt: [ "$aggrEntity2", 0] }}},
  { $out: "EntityMorphRef" }
])
OneOfEach_Phased.EntityMorphRef.updateMany({}, { $unset: {"aggrEntity2._id": 1}})

OneOfEach_Phased.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef3", localField: "refToEntity3", foreignField: "_id", as: "aggrEntity3" }},
  { $addFields: { "aggrEntity3": { $arrayElemAt: [ "$aggrEntity3", 0] }}},
  { $out: "EntityMorphRef" }
])
OneOfEach_Phased.EntityRef3.drop()

OneOfEach_Phased.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef4", localField: "refToEntity4", foreignField: "_id", as: "aggrEntity4" }},
  { $out: "EntityMorphRef" }
])

OneOfEach_Phased.EntityMorphRef.aggregate([
  { $lookup: { from: "EntityRef5", localField: "refToEntity5", foreignField: "_id", as: "aggrEntity5" }},
  { $out: "EntityMorphRef" }
])
OneOfEach_Phased.EntityMorphRef.updateMany({}, { $unset: {"aggrEntity5.$[]._id": 1}})
OneOfEach_Phased.EntityRef5.drop()

db.endSession()
