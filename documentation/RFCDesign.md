## RFC
| Status | Proposed |
| :-----| :----- | 
| RFC #| 001 | 
| Author(s) | Yang Cheng, Max Sterken | 
| Updated | 04/03/2021 |
### Abstract
We'll develop our system using LoRa P2P, Arduino, Bleutooth Low Energy, LoRaWAN and Swagger. The messages have a small packet length and handle the available bandwidth as efficiently as possible.

### User Benefit
The United Nations will use the UNH2O-system for its Water Safety program. Unmanned drones
are collecting information about water reservoirs, rivers and coastlines. Groups of drones will be launched from a mobile base camp (e.g. Apache AH-64) in order to perform measurements at
certain areas. The results will be shared as Open Data with other UN-members using LoRaWAN.
### Drone
The drone communicates with the gateway using LoRa P2P. The	drones	are still in development and will be configured with several sensors. The following types of measurements simulated in prototype:
- **type H2O-01: watercolour for detecting algae (RGB)**
- **type H20-02:radiation (like 0,96 μSv/h)**
- **type H20-03:material type (like ICE, LIQUID, GAS, SAND, STONE etc.)**
- **type H20-04:oil	detection percentage (like 85%)**
- **type H20-05:surface temperature (like -1.2°C)**
- **type H20-06:object detection (like 00=none, 01=human, 02=vehicle,.. FF=unidentified object)**

We  made a small protocol. The protocol makes use of a gateway which is able to send and receive the data from the surrounding drones.

The exchanged messages format:

`<id> <Time> <Location> <Type> <Value> <Result>`
#### id
Id of the drone that is sending message
#### Time
Date of drone measurement data. (Unix format) 
#### Location
Location of drone measurement data.
#### Type 
Types of tasks drone performs.
#### Value
Specific data measured by drone.
#### Result
The data measured by drone compared with the normal value, divided into three grades: good, normal and bad. "good" is represented by green, "normal" is represented by yellow, and "bad" is represented by red. 

#### Drone ID format: 4 bits
Number between 0 and 10 (only 10 drones for this project)

#### Time format: 32 bits
Unix timestamp : 32 bit

#### Coordinates in decimal degrees format: 64 bits
Latitude: (-90.0000)-(90.0000) range = 32 bits

Longitude (-180.0000)-(180.0000) range = 32 bits

#### Datatype format: 3 bits
6 possiblities: 3 bits

#### Value format: 32 bits
Any number: (-9999.9999)-(9999.9999) range : 32 bits


#### Bit construction of drone controller order:
First 4 bits: DroneID

Next 3 bits: Datatype

Next 64 bits: Coordinates

Total bits: 71

#### Bit construction of drone measurement:
First 4 bits: drone id

Next 4 bits: datatype collected

Next 64 bits: coordinates

Next 32 bits: time

Next 32 bits: value

Total: 135 bits, 17 bytes

| bit index | Content | Value  | Explanation |
| --- | --- | --- | --- |
|   0 to 3   | Drone id| 0 - 10 |  The drone id that the message is being sent to or coming from |
|   4 to 7   | Datatype of the message | 0 - 5 | The number that represents the datatype in the payload of message |
|   8 to 71  | GPS coordinates | -90000.0000 ~ 90000.0000 / -180000.0000 - 180000.0000 | Latitude and Longitude values |
|  72 to 103 | Unix time | 0 - 2147483647 | The unix time when message was sent |
| 104 to 135 | Data | -99999.99999 - 99999.99999 | The payload of the message |

#### All traffic will be encrypted with 256 AES encryption as per requirements, meaning all messages will consists of 2 packets, first the 256 bit secret key and then the 256 bit encrypted message (AES will pad the message up until its 256 bits automatically, after decryption the bit amount should equal the amount specified in the constructions above).



### Base camp
The communication between the devices in the base camp(Apachi) is carried out through UDP. The messages sent by the drone are forwarded by the gateway device to the other devices in the base camp. In this way, the gateway device will serve as a UDP server. The other devices will have UDP sockets using the dgram module. As User Datagram Protocol is on the 4th layer of the OSI model, it introduces the concept of port. Each device has its function, in this way one will be used to receive data from drone and graph the data to display on the screen, another device will be used to transfer data to the server via Lorawan.
### Server
The communication between the server and the base camp is made possible using LoRaWAN. The format for LoRaWAN messages is similar to LoRa P2P.
In the communication between the servers is used HTTP (Hypertext Transfer Protocol).

 The HTTP request example:
`GET http://task/H2O-02`

This will give H2O-02 Task measurement results


The server will save the received data as JSON objects:


Template
>{
"drone":"ID",
"date":"ddMMYY",
"time": "HHMM",
"location": "XXX"
"type": "H2O-01~06",
"value": "XXX",
"result": "good/normal/bad"
}


Example:
>{
"drone":"1"
"date":"02032021",
"time": "1530",
"location": "xxxlake"
"type": "H2O-02",
"value": "0,88 μSv/h",
"result": "good"
}
