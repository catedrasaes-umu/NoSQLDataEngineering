var mongoose = require('mongoose');
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://127.0.0.1/test2',
{
  useMongoClient: true
}, function(err)
{
  if (err)
    console.log(err);
  else
    console.log('Connected to 127.0.0.1/test2');
});
mongoose.set('debug', true);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error: '));

var Persons = require('./app/models/PersonsSchema');

var err;
var p1 = new Persons({_id: mongoose.Types.ObjectId(), name: "name1", surname: "surname1", age: 18, isMarried: false, isEmployed: false, status: "status1"});
var p2 = new Persons({_id: mongoose.Types.ObjectId(), name: "name2", surname: "surname2", age: 28, isMarried: false, isEmployed: true, status: "status2"});
var p3 = new Persons({_id: mongoose.Types.ObjectId(), name: "name3", surname: "surname3", age: 38, isMarried: true, isEmployed: false, status: "status3"});
var p4 = new Persons({_id: mongoose.Types.ObjectId(), name: "name4", surname: "surname4", age: 48, isMarried: true, isEmployed: true, status: "status4"});
var p5 = new Persons({_id: mongoose.Types.ObjectId(), name: "name5", surname: "surname5", age: 58, isMarried: false, isEmployed: false, status: "status5"});

err = p1.validateSync(); if (err !== undefined) console.log(err); else p1.save();
err = p2.validateSync(); if (err !== undefined) console.log(err); else p2.save();
err = p3.validateSync(); if (err !== undefined) console.log(err); else p3.save();
err = p4.validateSync(); if (err !== undefined) console.log(err); else p4.save();
err = p5.validateSync(); if (err !== undefined) console.log(err); else p5.save();