var express = require('express');
var router = express.Router();
const mongoose = require('mongoose');

//this is our table: in Mongoose called Schema: takes data from database and adjust the types to JS
const measurementSchema = new mongoose.Schema({
  day_id: String,
  date: Date, //includes time as well
  coordinates: {
    lat: String,
    long: String
  },
  water_id: String,
  level: Number,
  radiation: Number,
  material: String,
  oil_detection: Number,
  surface_temperature: Number,
  objects_detected: Array
})

// collection is going to be creates as measurements
const Measurement = mongoose.model("Measurement", measurementSchema);

router.get('/', function(req, res, next) {
  Measurement.find({},function(err,data) {
    if (err){
      res.send(err);
    }
    res.send(data);
  });
});

module.exports = router;
