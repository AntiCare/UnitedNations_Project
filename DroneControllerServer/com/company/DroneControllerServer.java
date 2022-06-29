package com.company;


import com.company.Threads.MsgSendToDrone;
import com.company.Threads.MsgSendToMobileStationAndBluetoothServer;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 * drone controller.
 */
public class DroneControllerServer {
    //Address for receiving messages
    private final static String ADDR = "224.0.0.5";
    //Port for receiving messages
    private final static int PORT = 8888;

    public static void main(String[] args) throws IOException {

        //Socket for sending messages to the mobileStation & BluetoothServer
        DatagramSocket serverSocket = new DatagramSocket();
        //Socket for receiving messages from the mobileStation & BluetoothServer
        InetAddress inetAddress = InetAddress.getByName(ADDR);
        MulticastSocket multicastSocket = new MulticastSocket(PORT);
        //Subscribe to the address in order to receive all messages that were sent to that address
        multicastSocket.joinGroup(inetAddress);

        //Initialize listener.
        MsgSendToDrone msgSendToDrone = new MsgSendToDrone(multicastSocket, serverSocket.getLocalPort());
        MsgSendToMobileStationAndBluetoothServer msgSendToMobileStationAndBluetoothServer = new MsgSendToMobileStationAndBluetoothServer(new Scanner(System.in), serverSocket, inetAddress, PORT);

        Thread t1 = new Thread(msgSendToDrone);
        Thread t2 = new Thread(msgSendToMobileStationAndBluetoothServer);
        //Start threads.
        t1.start();
        t2.start();
    }
}

