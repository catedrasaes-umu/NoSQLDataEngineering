OneOfEach_Features = db.getSiblingDB("OneOfEach_Features")

OneOfEach_Features.createCollection("EntityToRemove")
OneOfEach_Features.EntityToRemove.updateMany({}, [{$addFields: { "_id": ObjectId(), "attrToDel": null, "attrToDelStr": "", "attrToDelNum": NumberInt(0), "attrToDelDouble": 0.0, "attrToDelBool": false, "attrToRen": null, "attrToRenStr": "", "attrToRenNum": NumberInt(0), "attrToRenDouble": 0.0, "attrToRenBool": false}}], {upsert: true})

OneOfEach_Features.EntityToRemove.bulkWrite([
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

OneOfEach_Features.createCollection("EntityToCopySource1")
OneOfEach_Features.EntityToCopySource1.updateMany({}, [{$addFields: { "_id": "id1", "valueToCopy1": "COPY_THIS", "valueToCopy2": [], "valueToCopy3": {$literal: {}}}}], {upsert: true})

OneOfEach_Features.createCollection("EntityToCopySource2")
OneOfEach_Features.EntityToCopySource2.updateMany({}, [{$addFields: { "_id": "id2", "valueToCopy4": "COPY_THAT"}}], {upsert: true})

OneOfEach_Features.createCollection("EntityToCopyTarget")
OneOfEach_Features.EntityToCopyTarget.updateMany({}, [{$addFields: { "_id": "", "thisRef": "id1", "thatRef": "id2"}}], {upsert: true})

OneOfEach_Features.EntityToCopySource1.find().forEach( function(sDoc) { OneOfEach_Features.EntityToCopyTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "copy1": sDoc.valueToCopy1} }); })

OneOfEach_Features.EntityToCopySource1.find().forEach( function(sDoc) { OneOfEach_Features.EntityToCopyTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "copy2": sDoc.valueToCopy2} }); })

OneOfEach_Features.EntityToCopySource1.find().forEach( function(sDoc) { OneOfEach_Features.EntityToCopyTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "copy3": sDoc.valueToCopy3} }); })

OneOfEach_Features.EntityToCopySource2.find().forEach( function(sDoc) { OneOfEach_Features.EntityToCopyTarget.updateMany({ "thatRef": sDoc._id }, { $set: { "valueToCopy4": sDoc.valueToCopy4} }); })

OneOfEach_Features.createCollection("EntityToMoveSource1")
OneOfEach_Features.EntityToMoveSource1.updateMany({}, [{$addFields: { "_id": "id1", "valueToMove1": "MOVE_THIS", "valueToMove2": [], "valueToMove3": {$literal: {}}}}], {upsert: true})

OneOfEach_Features.createCollection("EntityToMoveSource2")
OneOfEach_Features.EntityToMoveSource2.updateMany({}, [{$addFields: { "_id": "id2", "valueToMove4": "MOVE_THAT"}}], {upsert: true})

OneOfEach_Features.createCollection("EntityToMoveTarget")
OneOfEach_Features.EntityToMoveTarget.updateMany({}, [{$addFields: { "_id": "", "thisRef": "id1", "thatRef": "id2"}}], {upsert: true})

OneOfEach_Features.EntityToMoveSource1.find().forEach( function(sDoc) { OneOfEach_Features.EntityToMoveTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "move1": sDoc.valueToMove1} }); })
OneOfEach_Features.EntityToMoveSource1.updateMany({}, {$unset: {"valueToMove1": 1}})

OneOfEach_Features.EntityToMoveSource1.find().forEach( function(sDoc) { OneOfEach_Features.EntityToMoveTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "move2": sDoc.valueToMove2} }); })
OneOfEach_Features.EntityToMoveSource1.updateMany({}, {$unset: {"valueToMove2": 1}})

OneOfEach_Features.EntityToMoveSource1.find().forEach( function(sDoc) { OneOfEach_Features.EntityToMoveTarget.updateMany({ "thisRef": sDoc._id }, { $set: { "move3": sDoc.valueToMove3} }); })
OneOfEach_Features.EntityToMoveSource1.updateMany({}, {$unset: {"valueToMove3": 1}})

OneOfEach_Features.EntityToMoveSource2.find().forEach( function(sDoc) { OneOfEach_Features.EntityToMoveTarget.updateMany({ "thatRef": sDoc._id }, { $set: { "move4": sDoc.valueToMove4} }); })
OneOfEach_Features.EntityToMoveSource2.updateMany({}, {$unset: {"valueToMove4": 1}})

OneOfEach_Features.createCollection("EntityToNest")
OneOfEach_Features.EntityToNest.updateMany({}, [{$addFields: { "_id": "", "name": null, "age": null, "street": null, "postalCode": null, "city": null, "aList": [], "aMap": {$literal: {}}}}], {upsert: true})

OneOfEach_Features.EntityToNest.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "nestAggrNest1": {"nest1": null, "nest2": null, "nest3": null}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "nestAggrNest2": [{"nest4": null, "nest5": null, "nest6": null}]}}], upsert: true}},
  {updateMany: {"filter": {}, "update": {$rename: {"street": "nestedAggr.street", "postalCode": "nestedAggr.postalCode", "city": "nestedAggr.city", "nestAggrNest1": "nestedAggr.nestAggrNest1", "nestAggrNest2": "nestedAggr.nestAggrNest2"}}}}
])

OneOfEach_Features.createCollection("EntityToUnnest")
OneOfEach_Features.EntityToUnnest.updateMany({}, [{$addFields: { "_id": "", "name": null, "age": null}}], {upsert: true})

OneOfEach_Features.EntityToUnnest.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "unnestAggrNest1": {"nest1": null, "nest2": null, "nest3": null, "street": null, "postalCode": null, "city": null, "aList": [], "aMap": {$literal: {}}}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "unnestAggrNest2": {"nest4": null, "nest5": null, "nest6": null}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": {$rename: {"unnestAggrNest1.street": "street", "unnestAggrNest1.postalCode": "postalCode", "unnestAggrNest1.city": "city", "unnestAggrNest1.aList": "aList", "unnestAggrNest1.aMap": "aMap", "unnestAggrNest2.nest4": "nest4"}}}}
])

