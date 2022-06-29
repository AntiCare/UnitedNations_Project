const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const indexRouter = require('./routes/index');
const measurementRouter = require('./routes/measurements');
const app = express();
const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/HomeStation', {useNewUrlParser: true, useUnifiedTopology: true}).then(() => {
  console.log("Database is connected.");
}).catch(err => {
  console.log("Database connection is not working:", err);
});

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/measurements', measurementRouter);

app.listen(3000, () => {
  console.log(`App is listening on port 3000`);
})

module.exports = app;
