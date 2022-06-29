package com.company.Threads;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
/**
 * Listen message from drone. send to MobileStation & BluetoothServer.
 * The message from drone is fake.
 */
public class MsgSendToMobileStationAndBluetoothServer implements Runnable {

    private Scanner scanner;
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public MsgSendToMobileStationAndBluetoothServer(Scanner scanner, DatagramSocket socket, InetAddress address, int port) {
        this.scanner = scanner;
        this.socket = socket;
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        String messageFromDrone = "";

        while (scanner.hasNext()) {
            //Get the fake message from the drone.
            messageFromDrone = "Drone: " + scanner.nextLine();

            //Wrap the message into packet.
            DatagramPacket msgPacket =
                    new DatagramPacket(messageFromDrone.getBytes(), messageFromDrone.getBytes().length, address, port);
            try {
                //Broadcast the packet.
                socket.send(msgPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
