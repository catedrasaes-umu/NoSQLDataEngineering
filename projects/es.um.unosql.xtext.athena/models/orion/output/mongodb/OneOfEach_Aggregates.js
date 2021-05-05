OneOfEach_Aggregates = db.getSiblingDB("OneOfEach_Aggregates")

OneOfEach_Aggregates.createCollection("EntityToAggr")
OneOfEach_Aggregates.EntityToAggr.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Aggregates.EntityToAggr.bulkWrite([
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

OneOfEach_Aggregates.createCollection("EntityMorphAggr")
OneOfEach_Aggregates.EntityMorphAggr.updateMany({}, [{$addFields: { "_id": ObjectId()}}], {upsert: true})

OneOfEach_Aggregates.EntityMorphAggr.bulkWrite([
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggr1": {"string1": "", "string2": ""}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggr2": {"aList": [], "aMap": {$literal: {}}}}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggr3": [{"string5": "", "string6": ""}]}}], upsert: true}},
  {updateMany: {"filter": {}, "update": [{$addFields: { "aggr4": [{"aSet": [], "aTuple": []}]}}], upsert: true}}
])

OneOfEach_Aggregates.EntityMorphAggr.find().forEach(function(sDoc) {
    if (!sDoc.aggr1.hasOwnProperty('_id'))
      sDoc.aggr1._id = ObjectId().str;

    OneOfEach_Aggregates.Aggr1.insert(sDoc.aggr1);
    OneOfEach_Aggregates.EntityMorphAggr.updateMany({}, {$set: {"refToAggr1": sDoc.aggr1._id }})
});

OneOfEach_Aggregates.EntityMorphAggr.find().forEach(function(sDoc) {
    if (!sDoc.aggr2.hasOwnProperty('_id'))
      sDoc.aggr2._id = ObjectId().str;

    OneOfEach_Aggregates.Aggr2.insert(sDoc.aggr2);
    OneOfEach_Aggregates.EntityMorphAggr.updateMany({}, {$set: {"refToAggr2": sDoc.aggr2._id }})
});

OneOfEach_Aggregates.EntityMorphAggr.find().forEach(function(sDoc) {
    var ids = [];
    sDoc.aggr3.forEach(function(i)
    {
      if (!i.hasOwnProperty('_id'))
        i._id = ObjectId().str;

      ids.push(i._id);
    });
    OneOfEach_Aggregates.Aggr3.insertMany(sDoc.aggr3);
    OneOfEach_Aggregates.EntityMorphAggr.updateMany({}, {$set: {"refToAggr3": ids}});
});

OneOfEach_Aggregates.EntityMorphAggr.find().forEach(function(sDoc) {
    var ids = [];
    sDoc.aggr4.forEach(function(i)
    {
      if (!i.hasOwnProperty('_id'))
        i._id = ObjectId().str;

      ids.push(i._id);
    });
    OneOfEach_Aggregates.Aggr4.insertMany(sDoc.aggr4);
    OneOfEach_Aggregates.EntityMorphAggr.updateMany({}, {$set: {"refToAggr4": ids}});
});

