'use strict';

const mongoose = require('mongoose');

const measurementSchema = new mongoose.Schema({
    day_id: String,
    date: Date,
    coordinates: {
        lat: String,
        lon: String
    },
    water_id: String,
    level: Number,
    radiation: Number,
    material: String,
    oil_detection: Number,
    surface_temperature: Number,
    objects_detected: Array
});

const Measurement = mongoose.model("Measurement", measurementSchema);

/**
 * Finds measurements by date
 * get all measurements from a specific date
 *
 * date String date that needs to be considered for filter
 * returns List
 **/
exports.findMeasurementsByDate = function (date) {
    return new Promise(function (resolve, reject) {
        Measurement.find({"date": date}, function (err, data) {
            if (err) {
                err.status = 406;
                reject();
            }
            resolve(data)
        });
    });
}


/**
 * Finds measurements by day
 * get all measurements from a specific day
 *
 * day_id Integer day id that need to be considered for filter
 * returns List
 **/
exports.findMeasurementsByDay = function (day_id) {
    return new Promise(function (resolve, reject) {
        Measurement.find({"day_id": day_id}, function (err, data) {
            if (err) {
                err.status = 406;
                reject();
            }
            resolve(data)
        });
    });
}


/**
 * Finds measurements by level
 * get all measurements from a specific level
 *
 * level Integer level that needs to be considered for filter
 * returns List
 **/
exports.findMeasurementsByLevel = function (level) {
    return new Promise(function (resolve, reject) {
        Measurement.find({"level": level}, function (err, data) {
            if (err) {
                err.status = 406;
                reject();
            }
            resolve(data)
        });
    });
}

/**
 * Finds measurements by material found
 * get all measurements from a specific material
 *
 * material String material that needs to be considered for filter
 * returns List
 **/
exports.findMeasurementsByMaterial = function (material) {
    return new Promise(function (resolve, reject) {
        Measurement.find({"material": material}, function (err, data) {
            if (err) {
                err.status = 406;
                reject();
            }
            resolve(data)
        });
    });
}


/**
 * Finds measurements by oil level
 * get all measurements from a specific oil level
 *
 * oil_detection Integer Oil level that needs to be considered for filter
 * returns List
 **/
exports.findMeasurementsByOil = function (oil_detection) {
    return new Promise(function (resolve, reject) {
        Measurement.find({"oil_detection": oil_detection}, function (err, data) {
            if (err) {
                err.status = 406;
                reject();
            }
            resolve(data)
        });
    });
}


/**
 * Finds measurements by surface temperatur
 * get all measurements from a specific surface temperature
 *
 * surface_temperature Integer Surface temperaturethat needs to be considered for filter
 * returns List
 **/
exports.findMeasurementsByTemperature = function (surface_temperature) {
    return new Promise(function (resolve, reject) {
        Measurement.find({"surface_temperature": surface_temperature}, function (err, data) {
            if (err) {
                err.status = 406;
                reject();
            }
            resolve(data)
        });
    });
}


/**
 * Finds measurements by body of water
 * get all measurements from a specific body of water
 *
 * water_id String body of water that needs to be considered for filter
 * returns List
 **/
exports.findMeasurementsByWater = function (water_id) {
    return new Promise(function (resolve, reject) {
        Measurement.find({"water_id": water_id}, function (err, data) {
            if (err) {
                err.status = 406;
                reject();
            }
            resolve(data)
        });
    });
}


/**
 * Finds measurements by radiation
 * get all measurements from a specific radiation level
 *
 * radiation Integer Radiation that needs to be considered for filter
 * returns List
 **/
exports.findMeasurementsByradiation = function (radiation) {
    return new Promise(function (resolve, reject) {
        Measurement.find({"radiation": radiation}, function (err, data) {
            if (err) {
                err.status = 406;
                reject();
            }
            resolve(data)
        });
    });
}


/**
 * get all measurements
 *
 *
 * returns Measurement
 **/
exports.getmeasurements = function () {
    return new Promise(function (resolve, reject) {
        Measurement.find({}, function (err, data) {
            if (err) {
                err.status = 406;
                reject();
            }
            resolve(data)
        });
    });
}
