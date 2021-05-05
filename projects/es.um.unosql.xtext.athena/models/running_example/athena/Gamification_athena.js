Gamification_athena = db.getSiblingDB("Gamification_athena")

Gamification_athena.createCollection("Stage",
{
  validator:
  {
    $jsonSchema:
    {
      bsonType: "object",
      required: [ "id", "category", "description", "name", "createdAt", "updatedAt" ],
      properties:
      {
        id:
        {
          bsonType: "objectId",
          description: "Field id must be of type objectId and IS required."
        }, 
        category:
        {
          bsonType: "string",
          description: "Field category must be of type string and IS required."
        }, 
        description:
        {
          bsonType: "string",
          description: "Field description must be of type string and IS required."
        }, 
        name:
        {
          bsonType: "string",
          description: "Field name must be of type string and IS required."
        }, 
        createdAt:
        {
          bsonType: "timestamp",
          description: "Field createdAt must be of type timestamp and IS required."
        }, 
        updatedAt:
        {
          bsonType: "timestamp",
          description: "Field updatedAt must be of type timestamp and IS required."
        }
      }
    }
  }
});

Gamification_athena.createCollection("Minigame",
{
  validator:
  {
    $jsonSchema:
    {
      bsonType: "object",
      required: [ "id", "categories", "isActive", "name", "points", "stageIds", "createdAt", "updatedAt" ],
      properties:
      {
        id:
        {
          bsonType: "objectId",
          description: "Field id must be of type objectId and IS required."
        }, 
        categories:
        {
          bsonType: "array",
          items: { bsonType: "string" } ,
          description: "Field categories must be of type array and IS required."
        }, 
        isActive:
        {
          bsonType: "bool",
          description: "Field isActive must be of type bool and IS required."
        }, 
        name:
        {
          bsonType: "string",
          description: "Field name must be of type string and IS required."
        }, 
        points:
        {
          bsonType: "int",
          minimum: 0,
          maximum: 99,
          description: "Field points must be of type int in range [ 0..99 ] and IS required."
        }, 
        stageIds:
        {
          bsonType: "array",
          items: { bsonType: "objectId" } ,
          description: "Field stageIds must be of type array and IS required."
        }, 
        createdAt:
        {
          bsonType: "timestamp",
          description: "Field createdAt must be of type timestamp and IS required."
        }, 
        updatedAt:
        {
          bsonType: "timestamp",
          description: "Field updatedAt must be of type timestamp and IS required."
        }
      }
    }
  }
});

Gamification_athena.createCollection("User",
{
  validator:
  {
    bsonType: "object",
    required: [ "id", "avatarUrl", "email", "personalInfo" ],
    properties:
    {
      id:
      {
        bsonType: "objectId",
        description: "Field id must be of type objectId and IS required."
      }, 
      avatarUrl:
      {
        bsonType: "string",
        description: "Field avatarUrl must be of type string and IS required."
      }, 
      email:
      {
        bsonType : "string",
        pattern : "/^.+@.+\\.com$/",
        description: "Field email must be of type string , IS required  and match the regular expression pattern: /^.+@.+\\.com$/."
      }, 
      personalInfo:
      {
        bsonType: "object",
        required: [ "city", "name", "number", "street" ],
        properties:
        {
          city:
          {
            bsonType: "string",
            description: "Field city must be of type string and IS required."
          }, 
          name:
          {
            bsonType : "string",
            pattern : "/^[A-Z][a-z]*$/",
            description: "Field name must be of type string , IS required  and match the regular expression pattern: /^[A-Z][a-z]*$/."
          }, 
          number:
          {
            bsonType: "int",
            description: "Field number must be of type int and IS required."
          }, 
          street:
          {
            bsonType: "string",
            description: "Field street must be of type string and IS required."
          }, 
          postcode:
          {
            bsonType: "int",
            description: "Field postcode must be of type int."
          }
        },
        description: "Field personalInfo must be of type object and IS required."
      }, 
      games:
      {
        bsonType: "array",
        items: { bsonType: "object" } ,
        required: [ "gameId", "scheduledAt" ],
        properties:
        {
          gameId:
          {
            bsonType: "objectId",
            description: "Field gameId must be of type objectId and IS required."
          }, 
          scheduledAt:
          {
            bsonType: "timestamp",
            description: "Field scheduledAt must be of type timestamp and IS required."
          }, 
          completedAt:
          {
            bsonType: "timestamp",
            description: "Field completedAt must be of type timestamp."
          }, 
          points:
          {
            bsonType: "int",
            description: "Field points must be of type int."
          }
        },
        description: "Field games must be of type array."
      }, 
      points:
      {
        bsonType: "int",
        minimum: 0,
        maximum: 9999,
        description: "Field points must be of type int in range [ 0..9999 ]."
      }
    }
  }
});

