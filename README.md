# 2020-2021-Project-Networking-DHI2V.Sp-teamA

Welcome to the repository for the UNH20-System.

### Index
**Documentation:**
- Research
- Presentations
- Images
- Timesheet
- RFC Design
- Functional Design
- Technical Design

**src:**
- Heltec-esp32 LoRa sketches
- Arduino Bluetooth Sketch
- Jars (JserialComm)
- TTN Sketches


## Home Base Station
### Database
We are using MongoDB.
* To avoid issue of a one student storing database locally, please install a database on your PC as well. 
https://docs.mongodb.com/manual/installation/
* run the following command: brew services start mongodb-community@4.4
- Once you running your (you need two terminals open, one with command `mongod` and another one `mongo`), write in your mongo terminal
use HomeStation (this command will create a database called HomeStation on your local machine - this you will need to be able to connect it with server).
- you can install Compass as GUI and manually create data for your database to test server. For Compass connection with db, do not use URL but click on connecting it locally and just enter localhost.

### HomeStationServer
* make sure you pulled from repository
* cd to HomeStation and run `npm install` (this will run all node modules necessary for server)
* you can run server either with command `node app.js` or `nodemon app.js` (you need to install nodemon. With it you don't have to rerun the app everytime you make changes.)
* server runs on port 3000 and will automatically connects with your local db 

## Mobile Base Station
### Database
Same solution as home base station

### MobileBaseServer
* Because Springboot project it's configured with Mongo, you need to be running your database for the server to be working (otherwise you get an error).

### DroneControllerServer
* To run the drone controller server:
  - Plug the Heltec esp32 device in and ensure it is running the .ino file: src/heltec_esp32/LoRa_base/LoRa_base.ino
 
* Run the application at: DroneControllerServer/com/company/DroneControllerServer.java

* The server is now listening on the adress '224.0.0.5' port '8888' for UDP messages
* It is also listening to the selected COM port for incoming Serial data
* UDP messages ("bytes") received by the server will be sent to serial


### Drone Simulation
To run the drone simulator you need to have a virtual machine running Ubuntu 20.04.
* Step by step installation and run instructions can be found in the readme.md file in the appropriate folder.
