'use strict';

var utils = require('../utils/writer.js');
var Measurement = require('../service/MeasurementService');

module.exports.findMeasurementsByDate = function findMeasurementsByDate (req, res, next) {
  var date = req.swagger.params['date'].value;
  Measurement.findMeasurementsByDate(date)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.findMeasurementsByDay = function findMeasurementsByDay (req, res, next) {
  var day_id = req.swagger.params['day_id'].value;
  Measurement.findMeasurementsByDay(day_id)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.findMeasurementsByLevel = function findMeasurementsByLevel (req, res, next) {
  var level = req.swagger.params['level'].value;
  Measurement.findMeasurementsByLevel(level)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.findMeasurementsByMaterial = function findMeasurementsByMaterial (req, res, next) {
  var material = req.swagger.params['material'].value;
  Measurement.findMeasurementsByMaterial(material)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.findMeasurementsByOil = function findMeasurementsByOil (req, res, next) {
  var oil_detection = req.swagger.params['oil_detection'].value;
  Measurement.findMeasurementsByOil(oil_detection)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.findMeasurementsByTemperature = function findMeasurementsByTemperature (req, res, next) {
  var surface_temperature = req.swagger.params['surface_temperature'].value;
  Measurement.findMeasurementsByTemperature(surface_temperature)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.findMeasurementsByWater = function findMeasurementsByWater (req, res, next) {
  var water_id = req.swagger.params['water_id'].value;
  Measurement.findMeasurementsByWater(water_id)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.findMeasurementsByradiation = function findMeasurementsByradiation (req, res, next) {
  var radiation = req.swagger.params['radiation'].value;
  Measurement.findMeasurementsByradiation(radiation)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.getmeasurements = function getmeasurements (req, res, next) {
  Measurement.getmeasurements()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
