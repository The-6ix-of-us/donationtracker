const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const path = require('path');

const locationsController = require('./controllers/LocationsController');
const usersController = require('./controllers/UsersController');

const app = express();

app.use(bodyParser.urlencoded({
  extended: true
}));
app.use(bodyParser.json());
app.use(cors());

let server = app.listen(process.env.PORT || 8081);

app.use(express.static('public'));

console.log('running on port 8081');

app.post('/login', (req, res) => {
  res.send(usersController.login(req.body.email, req.body.password));
});

app.post('/register', (req, res) => {
  res.send(usersController.register(req.body.userInfo));
});

app.post('/add_location', (req, res) => {
  locationsController.addLocation(req.body.location);
});

app.get('/fetch_locations', (req, res) => {
  res.send(locationsController.fetchLocations());
});
