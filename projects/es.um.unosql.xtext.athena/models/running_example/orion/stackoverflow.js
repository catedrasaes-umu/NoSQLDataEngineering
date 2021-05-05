stackoverflow = db.getSiblingDB("stackoverflow")

stackoverflow.getCollectionNames().forEach(function(collName)
{
  stackoverflow[collName].bulkWrite([
  {updateMany: {filter: {}, update: {$set: { "CreationDate": { $convert: { input: "$CreationDate", to: 17 }}}}}},
  {updateMany: {filter: {}, update: {$set: { "LastAccessDate": { $convert: { input: "$LastAccessDate", to: 17 }}}}}}
  ])
})

stackoverflow.Posts.bulkWrite([
  {updateMany: {filter: {}, update: {$set: { "LastActivityDate": { $convert: { input: "$LastActivityDate", to: 17 }}}}}},
  {updateMany: {filter: {}, update: {$rename: {"CreationDate": "PostMetadata.CreationDate", "LastActivityDate": "PostMetadata.LastActivityDate"}}}},
  {updateMany: {filter: {}, update: {$rename: {"LastEditDate": "PostEditionInfo.LastEditDate", "LastEditorUserId": "PostEditionInfo.LastEditorUserId", "LastEditorDisplayName": "PostEditionInfo.LastEditorDisplayName"}}}}
])

stackoverflow.Users.updateMany({}, {$rename: {"CreationDate": "UserMetadata.CreationDate", "LastAccessDate": "UserMetadata", "DownVotes": "UserMetadata", "UpVotes": "UserMetadata"}})

stackoverflow.Posts.updateMany({}, {$set: { "Tags": [ "$Tags" ]}})

stackoverflow.Postlinks.find().forEach( function(sDoc) { stackoverflow.Posts.updateMany({ "_id": sDoc.PostId }, { $set: { "postlinkId": sDoc._id} }); })

stackoverflow.Posts.aggregate([
  { $lookup: { from: "Postlinks", localField: "postlinkId", foreignField: "_id", as: "Postlinks" }},
  { $addFields: { "Postlinks": { $arrayElemAt: [ "$Postlinks", 0] }}},
  { $out: "Posts" }
])
stackoverflow.Posts.updateMany({}, { $unset: {"Postlinks._id": 1}})
stackoverflow.Postlinks.drop()

stackoverflow.Badges.find().forEach( function(sDoc) { stackoverflow.Users.updateMany({ "_id": sDoc.UserId }, { $set: { "badgeId": sDoc._id} }); })

stackoverflow.Users.aggregate([
  { $lookup: { from: "Badges", localField: "badgeId", foreignField: "_id", as: "Badges" }},
  { $addFields: { "Badges": { $arrayElemAt: [ "$Badges", 0] }}},
  { $out: "Users" }
])
stackoverflow.Users.updateMany({}, { $unset: {"Badges._id": 1}})
stackoverflow.Badges.drop()

stackoverflow.Users.bulkWrite([
  {updateMany: {filter: {}, update: {$set: { "badgeId": [ "$badgeId" ]}}}},
  {updateMany: {filter: {}, update: {$rename: {"badgeId": "badges" }}}}
])

