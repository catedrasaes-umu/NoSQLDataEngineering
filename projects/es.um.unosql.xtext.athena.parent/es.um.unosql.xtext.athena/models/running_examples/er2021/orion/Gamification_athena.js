Gamification_athena = db.getSiblingDB("Gamification_athena")

Gamification_athena.getCollectionNames().forEach(function(collName)
{
  Gamification_athena[collName].updateMany({}, [{$set: { "points" : { $convert: { input: "$points", to: 1 }}}}])
})

