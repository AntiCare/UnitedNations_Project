package com.company.Threads;

import com.company.ComPortListener;
import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 *Listens to the messages from the mobileStation & Bluetooth Server. Send to drone.
 */
public class MsgSendToDrone implements Runnable {

    private MulticastSocket multicastSocket;
    private int socketPort;
    private static int SIZE_OF_BYTE_ARRAY = 17;

    public MsgSendToDrone(MulticastSocket socket, int socketPort) {
        multicastSocket = socket;
        this.socketPort = socketPort;
    }

    @Override
    public void run() {
        OutputStream os;
        /*  Make sure Heltec ESP32 is plugged in and running a sketch that reads data via serial */
        SerialPort[] allPorts = SerialPort.getCommPorts();
        System.out.println("List of all available serial ports: ");
        for (SerialPort eachComPort : allPorts)
            System.out.println(" -  " + eachComPort.getDescriptivePortName());

        // Open the COM port at index 1 (May differ on different PC'c)
        SerialPort heltecPort = allPorts[1];
        if (heltecPort.openPort()) {
            // set the port parameters
            heltecPort.setBaudRate(115200);
            heltecPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING,0,0);
            System.out.println("COM port opened: " + heltecPort.getDescriptivePortName());
            // get outputstream for writing to Heltec
            os = heltecPort.getOutputStream();

            //listen for incoming data on the heltec port
            ComPortListener comPortListener = new ComPortListener();
            heltecPort.addDataListener(comPortListener);

            while (true) {
                // Receive udp message when available.
                byte[] buf = new byte[SIZE_OF_BYTE_ARRAY];
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                try {
                    multicastSocket.receive(msgPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (msgPacket.getPort() != socketPort) {
                    //create byte array of datagram packet.
                    byte[] message = msgPacket.getData();

                    for (int i = 0; i < message.length; i++) {
                        System.out.print(message[i] +", ");
                    }
                    // send to serialport
                    try {
                        os.write(message);
                        System.out.println("Sending bytes to drone..");
                    } catch (IOException e) {
                        System.out.println("Error sending: " + e.getMessage());
                    }
                }
            }
        } else {
            System.out.println("Failed to open COM port");
        }
    }
}