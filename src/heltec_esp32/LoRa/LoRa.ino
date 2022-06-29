#include "heltec.h"
#include "logo.h"
#include <ctime>

#define BAND    868E6  //you can set band here directly,e.g. 868E6,915E6

String rssi = "RSSI --";
String packSize = "--";

const byte numChars = 32;
char receivedChars[numChars];
boolean newData = false;


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
  delay(1000);
  Heltec.display->clear();
  
  Heltec.display->drawString(0, 0, "Heltec.LoRa Initial success!");
  Heltec.display->display();
}

void loop() {
//  receiveData();
//  showNewData();
  receiveCommands();
}

void receiveData() {
  static boolean receiveInProgress = false;
  static byte indx = 0;
  char startSym = '<';
  char endSym = '>';
  char rc;
  
  
  // If there is data on serial waiting to be read and if newdata bool is currently false
  while (Serial.available() > 0 && newData == false) {
    // char currently being read
    rc = Serial.read();

    // read all characters and add to char array
    if(receiveInProgress == true) {
      if(rc != endSym) {
        receivedChars[indx] = rc;
        indx++;
        if(indx >= numChars) {
          indx = numChars - 1;
        }
      }
      
      
      else {
        receivedChars[indx] = '\0'; //end the line
        receiveInProgress = false;
        indx = 0;
        newData = true;
      }
    }

    else if(rc == startSym) {
      receiveInProgress = true;
    }
  }
}

void showNewData() {
  if(newData == true) {
    // Send data back to serial
//    Serial.print("This just in...  ");
//    Serial.println(receivedChars);
    newData = false;
    //Display on screen
    Heltec.display->clear();
    Heltec.display->drawString(0,0, String(receivedChars));
    Heltec.display->display();
  }
}

// command = The string message to send to the drone over lora.
void sendToDrone(String command ) {
//      
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
      LoRa.print(command);
      LoRa.endPacket();

      Heltec.display->clear();
      Heltec.display->drawString(0,0,"Sending to drone");
      Heltec.display->display();
    
      digitalWrite(LED, HIGH);   // turn the LED on (HIGH is the voltage level)
      delay(500);                       // wait for a second
      digitalWrite(LED, LOW);    // turn the LED off by making the voltage LOW
      delay(500);                       // wait for a second
}

void receiveCommands() {
  if(Serial.available()) {
    String command = Serial.readStringUntil('\n');

    if(command.equals("<256>")){
      sayHi();
    } else if(command.startsWith("1")){
        sendToDrone(command);
    } else {
      Heltec.display->clear();
      Heltec.display->drawString(0,0,String(command));
      Heltec.display->display();
    }
  }
}

void sayHi() {
  Heltec.display->clear();
  Heltec.display->drawString(0,0,"Hi!");
  Heltec.display->display();
}







//byte buf[command.length()];
////      command.getBytes(buf,command.length()+1);
////      Serial.println("Sending bytes to drone..");
////      Serial.write(buf, command.length()+1);
//        
//      
