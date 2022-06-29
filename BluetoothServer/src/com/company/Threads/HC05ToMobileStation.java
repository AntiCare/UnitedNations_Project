package com.company.Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Listens to messages from HC-05 and send them to the mobileStation.
 */
public class HC05ToMobileStation implements Runnable {

    private BufferedReader reader;
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public HC05ToMobileStation(InputStream bluetoothInput, DatagramSocket socket, InetAddress address, int port) {
        this.reader = new BufferedReader(new InputStreamReader(bluetoothInput));
        this.socket = socket;
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        String message = "";
        for (;;) {
            try {
                //Get the message from HC-05.
                message = "Bluetooth:" + reader.readLine();
                System.out.println(message);
                //Wrap the massage into packet
                DatagramPacket msgPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, address, port);
                //send the message to mobileStation.
                socket.send(msgPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}