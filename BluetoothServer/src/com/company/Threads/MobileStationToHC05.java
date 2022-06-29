package com.company.Threads;

import com.company.BluetoothManager;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 * Listens to messages from the mobileStation and send them to Hc-5 via bluetooth
 */
public class MobileStationToHC05 implements Runnable {

    private BluetoothManager bluetoothManager;
    private MulticastSocket ms;
    private int sendSocketPort;

    public MobileStationToHC05(BluetoothManager bluetoothManager, MulticastSocket socket, int mySendingSocket) {
        ms = socket;
        this.bluetoothManager = bluetoothManager;
        this.sendSocketPort = mySendingSocket;
    }

    @Override
    public void run() {

        for (;;) {
            // Receive packet from the mobileStation
            byte[] buf = new byte[52];
            DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
            try {
                ms.receive(msgPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (sendSocketPort != msgPacket.getPort()) {
                //turn bytes into string
                String msg = new String(msgPacket.getData());
                System.out.println(msg);


                try {
                    // Send the message to HC-05 via bluetooth
                    bluetoothManager.writeToHC05(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

