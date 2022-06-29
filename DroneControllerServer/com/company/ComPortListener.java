package com.company;

import com.fazecast.jSerialComm.*;


public class ComPortListener implements SerialPortDataListener {
    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        byte[] buffer = new byte[serialPortEvent.getSerialPort().bytesAvailable()];
        serialPortEvent.getSerialPort().readBytes(buffer,buffer.length);

        //Do something with data
        //Data should be connected to spring webserver using UDP
        //Unfortunately we did not achieve full integration for this project
        for (byte bit : buffer) {
            System.out.print((char) bit);
        }
    }

}
