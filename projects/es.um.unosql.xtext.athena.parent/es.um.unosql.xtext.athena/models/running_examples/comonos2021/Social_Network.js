Social_Network = db.getSiblingDB("Social_Network")

Social_Network.createCollection("Comment",
{
  validator:
  {
    $jsonSchema:
    {
      bsonType: "object",
      required: [ "id", "message", "post_id", "user_id", "created_time", "last_activity_date", "upvotes", "downvotes" ],
      properties:
      {
        id:
        {
          bsonType: "string",
          description: "Field id must be of type string and IS required."
        }, 
        message:
        {
          bsonType: "string",
          description: "Field message must be of type string and IS required."
        }, 
        post_id:
        {
          bsonType: "string",
          description: "Field post_id must be of type string and IS required."
        }, 
        user_id:
        {
          bsonType: "string",
          description: "Field user_id must be of type string and IS required."
        }, 
        created_time:
        {
          bsonType: "timestamp",
          description: "Field created_time must be of type timestamp and IS required."
        }, 
        last_activity_date:
        {
          bsonType: "timestamp",
          description: "Field last_activity_date must be of type timestamp and IS required."
        }, 
        upvotes:
        {
          bsonType: "int",
          minimum: 0,
          maximum: 1000,
          description: "Field upvotes must be of type int in range [ 0..1000 ] and IS required."
        }, 
        downvotes:
        {
          bsonType: "int",
          minimum: 0,
          maximum: 1000,
          description: "Field downvotes must be of type int in range [ 0..1000 ] and IS required."
        }
      }
    }
  }
});

Social_Network.createCollection("Post",
{
  validator:
  {
    $jsonSchema:
    {
      bsonType: "object",
      required: [ "id", "message", "social", "title", "user_id", "created_time", "last_activity_date", "upvotes", "downvotes" ],
      properties:
      {
        id:
        {
          bsonType: "objectId",
          description: "Field id must be of type objectId and IS required."
        }, 
        message:
        {
          bsonType: "string",
          description: "Field message must be of type string and IS required."
        }, 
        social:
        {
          bsonType: "object",
          properties:
          {
            views:
            {
              bsonType: "int",
              description: "Field views must be of type int."
            }, 
            reactions:
            {
              bsonType: "int",
              description: "Field reactions must be of type int."
            }, 
            shares:
            {
              bsonType: "number",
              description: "Field shares must be of type number."
            }
          },
          description: "Field social must be of type object and IS required."
        }, 
        title:
        {
          bsonType: "string",
          description: "Field title must be of type string and IS required."
        }, 
        user_id:
        {
          bsonType: "objectId",
          description: "Field user_id must be of type objectId and IS required."
        }, 
        tags:
        {
          bsonType: "array",
          items: { bsonType: "string" } ,
          description: "Field tags must be of type array."
        }, 
        created_time:
        {
          bsonType: "timestamp",
          description: "Field created_time must be of type timestamp and IS required."
        }, 
        last_activity_date:
        {
          bsonType: "timestamp",
          description: "Field last_activity_date must be of type timestamp and IS required."
        }, 
        upvotes:
        {
          bsonType: "int",
          minimum: 0,
          maximum: 1000,
          description: "Field upvotes must be of type int in range [ 0..1000 ] and IS required."
        }, 
        downvotes:
        {
          bsonType: "int",
          minimum: 0,
          maximum: 1000,
          description: "Field downvotes must be of type int in range [ 0..1000 ] and IS required."
        }
      }
    }
  }
});

Social_Network.createCollection("User",
{
  validator:
  {
    bsonType: "object",
    required: [ "id", "email", "type", "user_data" ],
    properties:
    {
      id:
      {
        bsonType: "objectId",
        description: "Field id must be of type objectId and IS required."
      }, 
      email:
      {
        bsonType : "string",
        pattern : "/^.+@.+\\.com$/",
        description: "Field email must be of type string , IS required  and match the regular expression pattern: /^.+@.+\\.com$/."
      }, 
      type:
      {
        enum: [ "Guest", "User", "Admin" ],
        description: "Field type can only be one of the ENUM values: [ Guest, User, Admin ]  and IS required."
      }, 
      user_data:
      {
        bsonType: "object",
        required: [ "address", "name" ],
        properties:
        {
          address:
          {
            bsonType: "string",
            description: "Field address must be of type string and IS required."
          }, 
          name:
          {
            bsonType : "string",
            pattern : "/^[A-Z][a-z]*$/",
            description: "Field name must be of type string , IS required  and match the regular expression pattern: /^[A-Z][a-z]*$/."
          }, 
          about_me:
          {
            bsonType: "string",
            description: "Field about_me must be of type string."
          }
        },
        description: "Field user_data must be of type object and IS required."
      }, 
      is_active:
      {
        bsonType: "bool",
        description: "Field is_active must be of type bool."
      }, 
      suspended_acc:
      {
        bsonType: [ "int", "bool" ],
        description: "Field suspended_acc must be of type [ int, bool ]."
      }
    }
  }
});

