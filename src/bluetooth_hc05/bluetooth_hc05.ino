
#include <SoftwareSerial.h>

SoftwareSerial BTSerial(11, 10); // TX / RX

void setup() {
  Serial.begin(9600);
  Serial.println("--AT Mode--");
  BTSerial.begin(9600);
  
}

void loop() {
  if (BTSerial.available()) {
    Serial.write(BTSerial.read());
  }
  if (Serial.available()) {
    BTSerial.write(Serial.read());
  }
}
