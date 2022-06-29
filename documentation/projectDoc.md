# Project documentation

## 1 Introduction
 The United Nations' Safety program will launched a group of UNH20 drones, which will be collecting information about water reservoirs, river and coastlines. The data they collect will be shared and become visible on the monitoring system. 
 For this project, our goal is to create the prototype system for the case description above. We are going to research all the hardware necessary for creating this prototype, compose them and then create an appropriate software solution.

## 2 Team composition 
Name | Lucia | Stephen | Max | Sem | Zuzana | Yang | Johan | Andrei | Anh
--- | --- | --- | --- |--- |--- |--- |--- |--- |--- 
Student number |474237 | 484629 | 485736 | 470066 | 464686 | 474340 |  - | 480211 | 470999 
Group Role | Git master | Scrum master 3 | Database master | Database master 2 | Scrum master 1 | Architecture master | Project Leader | Aduino master | Scrum master 2

### Team meetings
Usually every Wednesday at 11am. Adjustments to this based on discussion of the group.

## 3 Code of conduct
1. Everyone in the group should ensure that they can be contacted easily by other members of the group by giving them adequate contact information. 
 
2. When working in a group, please treat other members of the group with courtesy and respect their opinions, even if you do not necessarily agree with them. 

3. You are expected to make a full and fair contribution to the work of the group. 
 
4. When you agree to undertake a task that has been assigned to you by the group you must try work to the agreed deadline since failure to do so could impede the progress of the whole project. 
 
5. You have the right, naturally, to challenge other’s opinions but please try to do it in a non-aggressive way. 
 
6. It is your responsibility to attend all meetings arranged by the group to advance the project, and to arrive at those meetings on time.  If you cannot attend a meeting of the group, you should consider providing your input in written form and giving this to the other group members before the meeting. 
 
7. If another member of your group tells you something in confidence, you should respect their wishes. 
 
8. If you encounter a bug or something which takes a lot of time and still cannot be resolved, please communicate with the team members in time. 
 
9. The group should meet up at least twice a week to reflect on the work being done to ensure progress. 
 
10. If a group member does not complete their work within a reasonable timeframe and has no reason/does not communicate any reason why that is the case, the group should meet up and discuss who will take on the responsibility for their work as soon as possible. 
 
11. When the group deems that a member has not been keeping to the agreements made this member will be issued a strike, after a member gets a second strike a meeting should be planned with the teacher to discuss how to proceed going forward with this member of the team. 

## 4 Plan of approach

### Introduction
This document holds all the necessary information to build the UNH20-system. The general idea of the project is to build a system to test the river, reservoir and coastline water within cities and states. The testing must be done remotely with the use of drones, launched from base-camps. The data will also be stored remotely and should be accessible by all base-camps involved. The data will be sent using LoRa-technology which stands for Long Range, as the name implies this means the data will be sent long distances while still being very power efficient.

This document will contain Requirements, Wireframes, Architecture Diagrams, Use-Case Diagrams and Class diagrams. Also our primary and Secondary objectives and research questions. 

### Hardware
Hardware received will be split as following based on the group decision
Stephen: 2x LoRaWAN modules
Zuzana & Anh:1x Arduino UNO / 1x Arduino Shield / 1x Bluetooth module


### 4.1 Primary objective
The main objective for this project is to combine all sub objectives into the final complete prototype of a UNH20 Drone system.
This includes a **Web application** for viewing data sent to and from the drones, a complete **RFC** specification, a fully developed **Database** for all 
bases, the **Swagger** api in conjunction with **LoRaWAN** to transfer data to seperate basecamps, **Drone simulation** software, and finally an implementation of the **LoRaP2P** technology to communicate with the simulated drones in the field.

### 4.2 Secondary objectives

#### Web Application
A web application is one of the main parts of this project. The application will be mainly used to communicate with drones from the mobile base-camps.
This application will be built using HTML, CSS and Javascript.

#### RFC
The RFC (request for comments) is a formal document that describes the specifications for our technology. This document will be drafted for our communications to and from the drones, the database and base-camps.

#### Database
In order to store the data that is gathered from various drones persistently we need a database. This database can not be cloud based as the mobile basecamps are not equipped with Internet. 

#### Swagger API
All communication with other member states will be mutually agreed upon, and Documented as Open Data API in Swagger. 

#### Drone Simulation
We will sadly not be using real drones for this project, however we still will be able to simulate these drones using various api's. The drone simulation api's we will be using are PX4 and mavLINK

#### Communication services
In order to keep the communication between drones efficient we will use LoRaP2P technology which is known for having very good range and using very little power.
UDP, LoRaWAN and Bluetooth/BLE protocols will also be used for communication

### 4.3 Research questions
* What is Lora and LoraWAN?
* Arduino / Arduino Shield
* What is Swagger and how will it help our RESTful API?

#### 4.3.1 Sub-research questions
* How does Lora communicate?

### 4.4 Wireframes/Diagrams
[Architecture Diagram](https://github.com/SaxionACT/2020-2021-Project-Networking-DHI2V.Sp-teamA/blob/main/documentation/images/projectNetworkDiagram.png)

 [Webapp Wireframe](https://github.com/SaxionACT/2020-2021-Project-Networking-DHI2V.Sp-teamA/blob/main/documentation/images/App-wireframe.png)
 
### 4.5 Requirements
Req nr. | Description | ISO 25010 | F/nF | Priority | Source | Accomplished 
 --- | --- | --- | --- | --- | --- | ---
 RQ-B01 | All	communication	is	encrypted	using	symmetric	encryption	(minimal	AES	256	bit). | B/Security | NF | MUST | CEO | No | 
 RQ-B02 |The	communication	service	is	strictly	compliant	to	the	‘1%	duty	cycle’-rule,	it	only	uses	the	free	868	MHz	band	and	it	limits	the	transmit	output	power	to	maximum	authorised	capacity	in	the	EU. | B/Efficiency | NF | MUST | CEO | Yes |
 RQ-B03 | The	internal	communication	service	is	based	upon	a	self-made	binary	communication	protocol,	described	in a	RFC | B/Portability | NF | MUST | CEO | Yes |
 RQ-B04 | The	communication	service	uses	UDP,	LoRa	P2P,	LoRaWAN	and	Bluetooth/BLE	protocols. | B/Portability | NF | MUST | CEO | Yes |
 RQ-B05 | Communication	with	other	member	states	are	mutually	agreed	upon,	documented	as	Open	Data	API	in	Swagger.	 | B/Portability | NF| MUST | CEO | Yes |
 RQ-B06 | The	solution	operates	on	Things	Network	Stack	V2	and	will	be	migrated	to	The	Things	Stack	V3	at	the	end	of	2021. | B/Portability | NF | SHOULD | CEO | Yes |
 RQ-U01 | The	user	can	display	measurements on	a	simplified	version	of	a	fixed	map. | U/Functionality | F | MUST | SU | No |
 RQ-U02 | The	user	is	able	to	send	commands	to	a	group	of	drones	in	order	to	request new	measurements	of	a	specific	area.	 | U/Functionality | F | MUST | SU | No |
 RQ-U03 | The	drone	can	send	measurements	to	the	base	camp	including	measurement	type,	value,	location,	timestamp	(Unix	Timestamp). | U/Functionality | F | MUST | SU | No |
 RQ-U04 | The	user	can	view	actual	measurements,	that	are	measured	during	the	last	60	minutes	and	exceed	a	certain	value. | U/Functionality | F | MUST| SU | Yes |
 RQ-S01 | The	drones	support	PX4	firmware	and	are	simulated	using	Ubuntu	20.04	LTS and	MavLink. | B/Portability | NF | MUST | CTO | Yes |
 RQ-S02 | Senders	and	keys	aren’t	stored	persistently	on	clients	without	encryption. | U/Risk	Free | NF | MUST | CTO | No |
 RQ-S03 | The	system	takes	care	for	the	availability	of	data,	by	bringing	data	each	15	minutes	to	the	base	camp	in	case	a	group	of	drones	is	out	of	reach.	 | B/Reliability | F | SHOULD | CTO | No |
 RQ-S04 | A	group	of	drones	can	ask	for	assistence	when	a	drone	is	missing	in	action. | B/Reliability | F | SHOULD | CTO | No |
 RQ-S05 | A	drone	can	be	added	to	a	group	of	drones	and	after	that	the	measurement	assignment	is	reallocated. | B/Efficiency | F | COULD | CTO | No |
 RQ-S06 | The	system	uses	NiceRF	Lora611AES-TTL-868MHz	of	Heltec	ESP32	Lora	SX1276	USB	modules	for	the	communication	with	drones. | B/Portability | NF | MUST | CTO | Yes |
 RQ-S07 | The	system	uses Laird RM186	BLE	or	Arduio	Dragino	LoRa	modules	and	communicates	over	Bluetooth/BLEand LoRaWAN. | B/Portability | NF | MUST | CTO | Yes |
 RQ-S08 | Messages	have	as	small	as	possible	packetlength	and	handle	the	available	bandwidth	as	efficiently	as possible | B/Efficiency | NF | MUST | CTO | Yes |
 RQ-S09 | MQTT	or HTTP	integrations	are	being	used	for	sending	data	from	TTN	to the	application	server	(e.g. Open	Data	API	server). | B/Portability | NF | MUST | CTO | Yes
 
###Backglog
ID | Description | Complexity (hrs) | Priority |



### 4.6 Products

##### Home Base

###### Database

The Home base should have a central database in which all the information collected by the drone and handled by the mobile base is stored.
This database could be either done in SQL or noSQL, but since the categories of data gathered by the drone are limited and not too complexly interwoven setting up a SQL database would keep things as tidy as possible without getting too complicated. This database should be run on some kind of webserver, though we need to avoid having this webserver being only accessible from one person's computer.

###### RESTful API

A RESTful API should also be part of the home base construction, seeing as we need to be able to receive gathered data from the mobile base and have it be placed inside the database. This API should also give outside sources access to the data (if they are allowed access to it). The incoming data will be encrypted symmetrically with AES encryption. The API should decrypt any data before putting it inside the database and possibly encrypt it before responding to any GET requests from outside. Incoming data will be sent via LoRaWAN so the API should be build with that in mind.

##### Mobile base

###### Drone controller

From the mobile base there should be a way to control the drone remotely. Therefore an application needs to be built which can guide the drone and decide where it will collect data. It would be best for this application to be a web application, since it will be able to run any device with a browser we will decide to make it responsive on. If we went for an android application, it would be limited to only running devices with android OS. The drone controller application should communicate with the drone via LoRa P2P.

###### Mobile Data Collection app

Any data collected by the drone should be sent back to the mobile base, where researched would be able to view, analyze and download the data. Depending on how much data is supposed to be handled by this application it might be a good idea to implement a database for it, though this remains to be seen. A possible solution for this application would be to build a server/client structure, where the server handles the incoming data and displays it on a screen with some user friendly UI that gives a clear overview of the data, and a client which all people will have on their own devices in the mobile station, from which they can download data from the central server. This application should send back data to the home base server via LoRaWAN, and should an Arduino/Draguino to accomplish that.

##### Drone data collector

Since no drone is provided to us for this project, we will need to come up with a way to simulate data ourselves. For this we will need to build an application that can come up with data from nothing based on algorithms. As recommended by the teacher we will look to build this using jMAVsim, a tool already built to simulate drone activity with a lot of examples and documentation ready. Multiple jMAVsim drone instances can be controller by the drone controller if it uses MAVSDK.  It will need to be able to simulate gathering data from different location and in many different aspects water quality can be measured. A possible way to do this in a credible sense would be to write algorithms that can determine different normal distributions for different location and able to produce data based on those chances. Data generated from this application should be sent via LoRa P2P to the mobile base.

### 4.7 Quality

### 4.8 Organisations

#### Group meetings
* 10.02. Deciding and splitting research questions
* 12.02  Discussions of following: What to be done for the documentation, plan for next week, splitting hardware, setting up github, architecture diagram
* 15.02. Comparing made architecture diagrams, coming up with final solution
* 17.02. Group roles, plan of approach, discussing scrum process, future meetings, timesheet
* 03.03. Notes from meeting: Discussing following presentation (10min); Everyone needs to talk about something during presentation.
Proof of concept : (30min)
1. Architecture Diagram: Max
2. Databases (Entity relationship diagram): Johan
3. RFC Design: Yang (10Min)
4. LoRa communication: Stephen, Sem, Lucia
5. Bluetooth thing: Ahn, Zuzana
6. Bluetooth/Communication thingsnetwork with Arduino/Connection with your laptop - will be discussed among Ahn, Zuzana and Andrei: Andrei

Deliverables will be discussed Friday after class.

## 5 Sprint reviews

### Sprint retrospectives

#### Sprint 0 (pre-stage)
During this sprint we mainly did the research. The team was split up into subteams to work on searching for information and doing the basic setup of the hardware in some cases. 

- What went well  
  - Communication among the team members was clear and respectful
  - If someone was not able to join the meeting, he/she informed the rest of the team
  - Research and preparation also went well, we managed to find quite the amount of sources for our research.
 
- What went wrong
  - We didn't find any point that didn't go that good, we all keep doing our best to keep it this way.  
 
- What should be improved in the next sprint?
  - We start implementing the design, so we all keep cooperating the way we get used to, it also includes the team meetings.


#### Sprint 1
TODO: write user stories, then divide work

Max, Johan: Swagger API for home office
Johan, Sem, Lucia: Database: 
Yang, Max : RFC
Yang, Anh, Zuzana : Bluetooth connection with arduino
Andrei, Stephen : Drone

Sprint goal: Get main components up and running, no complete integration of all parts

**Server**
(like we done for internet technology): 
Max: AES encryption
Sequence diagram of data flow?
UDP protocol

 1. receive data from drone controller: we need RFC for that need to be clear for everybody; for later first need drone controller
 2. write data to database: Lucia: explain what exactly going to do?
 3. send data to Arduino (the bluetooth part Yang)
 4. Allow other clients to connect to it and download data directly: later one second sprint

**Map UI**
for later

**Data Collection** 
Yang: needs to send data from data collection (server) Bluetooth: LoraWAN communication; Ardunio needs to get data via bluetooth and then transfer via lorawan to home camp

**Drone Controller** 
Andrei, Stephen: Lora sending data from and to drones; drone behaviour communication, data that drone needs to send to basecamp; responsibility for RFC and discuss that

- What went well?
   - Everyone was fairly sure from the start what he/she should be doing and it improved the productivity greatly.
   - Almost everyone showed up for the team meetings.
   - The goals we set out to achieve at the beginning have almost all been achieved.

- What went badly?
   - There was no one exact thing that went badly this sprint. Everyone chipped in and helped with the project in some way.

- What should be improved?
   - Individuals who probably did not know exactly what to do probably should speak to the teacher/project leader for guidence.

#### Sprint 2

- What went well?
    - Everybody attends most of the meetings.
    - Everybody contributes fairly ideas to improve productivity.
    - The whole team finished almost the requirements.
    - People mostly followed up on agreements made.
  
- What went not well?
    - Integration does not go well in the final presentation.

- What could be improved?
    - Each individual can learn more about how to optimize the data to byte array.
    - We should have been more decisive in our design so that integration would have been easier.



## Backlog
### sprint 1
| ID | Actor | Description | Complexity Hours | Priority | team |
| --- | --- | --- | --- | --- | --- |
| DS01 | Drone | The system should be able to fully simulate a working quadcopter drone | 4 | Must | Drone |
| DC01 | Drone Controller | As a drone controller I should be able to send coordinates to a single/group of drone(s) | 4 | Must | Drone |
| DE01 | Data Encryption | As a user I want the data being sent through the system to be encrypted | 3 | Must | Security |
| RFC01 | RFC | As a developer I want a clearly defined RFC to avoid confusion while implementing | 2 | Must | Security |
| HS01 | HomeStation | As external data collector I want to have a homestation server which is connected to database so I can get access to data all the time  | 1 | Must | HomeStation |
| MS01 | MobileStation | As a researcher I want to have a Data colelction server which is connected to database so I can get access to data all the time | 1 | Must | MobileStation |
| DA01 | Database | As a developer I want to be able connect a Home Office server to a database to show data real time | 1 | Must | Database |
| DA02 | Database | As a developer I want to be able connect a Mobile Office server to a database to show dat real time | 1 | Must | Database |
| DA03 | Database | As a user I want to be able to see measurments online so I can have better understanding what data I can filter on | 2 | Must | Swagger |
| SG01 | Swagger | As a developer I want to document the code using Swagger so the code is more readable so I can better follow the code and understand it | 2 | Must | Swagger |
| WEB01 | WebApp | As a user I want to be able to control a drone using web application so it is easier for me to control it | 2 | Must | MobileStation |
| BT01 | Bluetooth | As a developer I want to be able to send data from arduino uno to the TTN network  | 3 | Must | MobileStation |


### sprint 2

| ID | Actor | Description | Complexity Hours | Priority | team |
| --- | --- | --- | --- | --- | --- |
| BT01 | Bluetooth | As a developer I want to make HTTP integration from the TTN network to a swagger api server  | 3 | Must | MobileStation |
| MS01 | MobileStation | As a developer I want to optimize the message being as small as possible  | 4 | Must | MobileStation |
| A01 | Arduino | As a developer I want to follow the ‘1% duty cycle’-rule for using free 868 Mhz band of frequency in EU | 1 | Must | MobileStation |
| DA01 | Database | As a developer I want to be able to store data from TTN network through POST request in the HTTP integration. | 2 | Must | HomeStation |





