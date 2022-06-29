# Bluetooth SMART

## Introduction
- **Bluetooth Low Energy (BLE)**, or “Bluetooth Smart”, is a light-weight subset of classic Bluetooth and was introduced as part of the Bluetooth 4.0 core specification.
- Another approach is that **BLE** is a wireless personal area network technology aiming in healthcare, beacons, home entertainment industries etc.
- Originally, **BLE** was started by Nokia under the name “Wibree” (2006) before being marketed by the Bluetooth SIG. 
- There are plenty of wireless protocols, however the interesting point of **BLE** is how it can communicate out to any modern mobile platform (iOS, Android, Windows phones, etc..). 
- There will be an overview of **BLE**, how it organizes data and advertises itself to devices for connections and back-and-forth passing data. 

## Connection Establishment in GAP
- **GAP** stands for **Generic Access Profile**, and it manages connection and advertising processes in Bluetooth. GAP could make your device visible to the world and determine whether two devices can interact with each other or not. 
- **GAP** defines various roles for devices, however, there are two key concepts stated in a study of Kevin.T (2020) to consider: 
  
  + **Peripheral devices** are small, low power, resource constrained devices that can connect to a more powerful central device. These devices are like a heart rate monitor, a **BLE** enabled proximity (closeness) tag, etc.
  + **Central devices** are usually the mobile phone or tablet that you connect to with far more processing power and memory.
   
- There are two ways to send advertising out with **GAP**. They are advertising data and scanning responses.
- Only the advertising data is mandatory when it is constantly sending itself existing signals to central devices in their certain range.
### Advertising
- A **peripheral** will establish a specific **advertising interval** and re-send the advertising data in every new **advertising interval**. Note that there will be a short delay between **advertising intervals**.
- If there is a central device accept in **scanning responses**, then the **peripheral** will send additional data to respond to the central device’s request. 

### Broadcasting topology
- To send data to more than one device at a time, you can use the advertising packet data. Data is only observed when two devices are connected. 
- Particularly, you can use a low cost **BLE peripheral** to send data (could be up to 31 bytes) one-way to any devices in the listening range. This way is known as **Broadcasting** in **BLE**. 

- Since your peripheral connected a central device, the advertising process will stop. You will then need **GATT services and characteristics** to communicate in both directions. 

## Interaction by GATT services
- **GATT** stands for the **Generic Attribute Profile**. It defines the way that two **BLE** devices transfer data back and forth using concepts called **Services** and **Characteristics**.
- It uses **Attribute Protocol (ATT)** to store **Services, Characteristics** and related data in a table. Each table will have its own 16-bit ID. 
- Whenever **GATT** services take charge. That means a connection is established and advertising is stopped. 
- The most important thing is that a **BLE** peripheral can only connected to one central device at a time until the connection breaks. 
### Connected Network Topology
- In the working environment, a **central** device can connect to more than one **peripheral** device, however, there is only one **central** device for a **peripheral** or the connection will be broken. 
- If two **peripherals** are to interact, a mailbox system will need to be implemented in the **central** device (or passing messages via the **central** device). 
- Note that the **central** device can interact with the **peripherals** and vice versa.

### GATT Transactions

- To understand interaction between devices in GATT, we can understand the relationship between the server (peripheral) and the client (central).
- The client or the central device (the phone/tablet) can send a request to its server and receive the server’s corresponding response. This way of interaction is known as a connection interval and it always starting from the client. 
- GATT transactions in BLE are based on nested objects called Profiles, Services and Characteristics. 
#### Profile

- A profile is simply defined as a collection of Services, that has been compiled by either the Bluetooth SIG or the peripheral designers.

#### Services
- Services are used to break data into logic entities and contain specific chunks of data called characteristics.
- A service can have one or more characteristics.
- Each service differs from other services by means of a unique numeric ID or UUID, which can be either 16-bit (for officially adopted BLE Services) or 128-bit (for custom services).

#### Characteristics
- The lowest level concept in GATT transactions is the Characteristics, which encapsulates a single data point. 
- Characteristics also distinguishes from others via a 16-bit or 128-bit UUID. 
- Note that Characteristics are the main point that you will interact with your server (BLE peripheral) and vice versa. 

## References
K. Townsend (2020). Introduction to Bluetooth Low Energy. Adafruit Learning System. Retrieved from

https://learn.adafruit.com/introduction-to-bluetooth-low-energy/introduction

Wikipedia (2021). Bluetooth Low Energy. Retrieved from

https://en.wikipedia.org/wiki/Bluetooth_Low_Energy 