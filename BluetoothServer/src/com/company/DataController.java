package DataCollector.src.com.company;

import com.company.Threads.HC05ToMobileStation;
import com.company.Threads.MobileStationToHC05;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Communication between mobileStation and HC-05(Bluetooth).
 */
public class DataController {

    //Address for receiving messages
    private final static String ADDR = "224.0.0.5";
    //Port for receiving messages
    private final static int PORT = 8888;

    public static void main(String[] args) throws IOException {
        //Start bluetooth connection
        BluetoothManager bluetoothManager = new BluetoothManager();

        //Socket for sending messages to mobileStation
        DatagramSocket serverSocket = new DatagramSocket();

        //Socket for receiving messages from mobileStation
        InetAddress iad = InetAddress.getByName(ADDR);
        MulticastSocket ms = new MulticastSocket(PORT);
        //Subscribe to the address in order to receive all messages that were sent to that address
        ms.joinGroup(iad);

        //Initialize mobileStation listener
        MobileStationToHC05 mobileStationListener = new MobileStationToHC05(bluetoothManager, ms, serverSocket.getLocalPort());
        //Initialize bluetooth listener
        HC05ToMobileStation HC05Listener = new HC05ToMobileStation(bluetoothManager.getInputStream(), serverSocket, iad, PORT);

        Thread mobileStationList = new Thread(mobileStationListener);
        Thread bluetoothList = new Thread(HC05Listener);
        //Start mobileStation listener
        mobileStationList.start();
        //Start bluetooth listener
        bluetoothList.start();
    }
}
