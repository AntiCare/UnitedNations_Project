#include "heltec.h"
#include "logo.h"
#include <ctime>

#define BAND    868E6  //you can set band here directly,e.g. 868E6,915E6

String rssi = "RSSI --";
String packet;
String pSize = "--";

const byte numChars = 17;
char receivedChars[numChars];
boolean newData = false;
unsigned char buf[numChars];

const int droneID = 0;


void logo()
{
  Heltec.display->clear();
  Heltec.display->drawXbm(0,5,logo_width,logo_height,logo_bits);
  Heltec.display->display();
}


void setup()
{
   //WIFI Kit series V1 not support Vext control
  Heltec.begin(true /*DisplayEnable Enable*/, true /*Heltec.Heltec.Heltec.LoRa Disable*/, true /*Serial Enable*/, true /*PABOOST Enable*/, BAND /*long BAND*/);
  Heltec.display->init();
  Heltec.display->flipScreenVertically();  
  Heltec.display->setFont(ArialMT_Plain_10);
  logo();
  delay(200);
  Heltec.display->clear();
  Heltec.display->drawString(0, 0, "Heltec.LoRa Initial success!");
  Heltec.display->drawString(0, 10,"Drone Controller");
  Heltec.display->display();
  LoRa.receive();
}

void loop() {
//  check if there is a lora packet available. (returns 0 if none available)
  int packetSize = LoRa.parsePacket();
  if(packetSize){ 
    processLora(packetSize);
    }
//  if there is a packet available that means a command/measurement has been received send to serial for processing
  
  if(Serial.available()){
      receiveCommands();
  }
}

// method gets called automatically on LoRa receive
void processLora(int packetSize) {
  //get the byte array from serial
  int bufSize = LoRa.readBytes(buf, numChars);
  String incoming = "";
  for(int i = 0; i < numChars; i++) {
    incoming += (char) buf[i];
  }
  //check if the array id matches this droneID;
  int firstByte = buf[0];
  int droneId = firstByte >> 4;
  Heltec.display->clear();
  Heltec.display->drawString(0,0,String(droneId));
  Heltec.display->drawString(0,10,incoming);
  Heltec.display->display();  
}

void sendToSerial(int packetSize) {
  //create variable to store packet
  packet = "";
  //get the size of the received packet in bytes
  pSize = String(packetSize,DEC);
  //get the char read from lora and store in string value;
  for(int i = 0; i < packetSize; i++) {
    packet += (char) LoRa.read();
  }
  //pass data over serial
  Serial.print("DATA: ");
  Serial.println(packet);
  rssi = "RSSI " + String(LoRa.packetRssi(),DEC);
  Serial.println(rssi);
}

//Method to get bytes from serial and do something with it
void receiveCommands() {
  if(Serial.available()) {
    int bufSize = Serial.readBytes(buf, numChars);
    sendToLoRa();
  }
}

// command = The message to send to the drone over lora.
void sendToLoRa() {  

   // send packet
    LoRa.beginPacket();
      
    /*
     * LoRa.setTxPower(txPower,RFOUT_pin);
     * txPower -- 0 ~ 20
     * RFOUT_pin could be RF_PACONFIG_PASELECT_PABOOST or RF_PACONFIG_PASELECT_RFO
     *   - RF_PACONFIG_PASELECT_PABOOST -- LoRa single output via PABOOST, maximum output 20dBm
     *   - RF_PACONFIG_PASELECT_RFO     -- LoRa single output via RFO_HF / RFO_LF, maximum output 14dBm
    */
      LoRa.setTxPower(14,RF_PACONFIG_PASELECT_PABOOST);
      
      for(int i = 0; i < numChars; i++) {
        LoRa.write(buf[i]);
      }
      
      LoRa.endPacket();

      Heltec.display->clear();
      // Change to "Sending to mobile station" on one of the Heltec devices.
      Heltec.display->drawString(0,0,"Sending to drone");
      Heltec.display->display();
    
      digitalWrite(LED, HIGH);   // turn the LED on (HIGH is the voltage level)
      delay(1000);                       // wait for a second
      digitalWrite(LED, LOW);    // turn the LED off by making the voltage LOW
      delay(1000);                       // wait for a second
}
