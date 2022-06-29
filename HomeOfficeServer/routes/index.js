var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next) {
  res.send("Measurements are displayed on /measurements");
});

module.exports = router;
