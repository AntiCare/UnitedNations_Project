'use strict';

const fs = require('fs'),
    path = require('path'),
    http = require('http');

const app = require('connect')();
const swaggerTools = require('swagger-tools');
const jsyaml = require('js-yaml');
const serverPort = 8080;
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

module.exports = measurementSchema; // this is what you want

mongoose.connect('mongodb://localhost:27017/HomeStation', {useNewUrlParser: true, useUnifiedTopology: true}).then(() => {
  console.log("Database is connected.");
}).catch(err => {
  console.log("Database connection is not working:", err);
});

// swaggerRouter configuration
var options = {
  swaggerUi: path.join(__dirname, '/swagger.json'),
  controllers: path.join(__dirname, './controllers'),
  useStubs: process.env.NODE_ENV === 'development' // Conditionally turn on stubs (mock mode)
};

// The Swagger document (require it, build it programmatically, fetch it from a URL, ...)
var spec = fs.readFileSync(path.join(__dirname,'api/swagger.yaml'), 'utf8');
var swaggerDoc = jsyaml.safeLoad(spec);

// Initialize the Swagger middleware
swaggerTools.initializeMiddleware(swaggerDoc, function (middleware) {

  // Interpret Swagger resources and attach metadata to request - must be first in swagger-tools middleware chain
  app.use(middleware.swaggerMetadata());

  // Validate Swagger requests
  app.use(middleware.swaggerValidator());

  // Route validated requests to appropriate controller
  app.use(middleware.swaggerRouter(options));

  // Serve the Swagger documents and Swagger UI
  app.use(middleware.swaggerUi());


  // Start the server
  http.createServer(app).listen(serverPort, function () {
    console.log('Your server is listening on port %d (http://localhost:%d)', serverPort, serverPort);
    console.log('Swagger-ui is available on http://localhost:%d/docs', serverPort);
  });
});
