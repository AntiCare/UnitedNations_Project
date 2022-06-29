package nl.saxion.project.mobilestation;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 * Listens to the messages.
 */
public class MessageListener implements Runnable {

    private MulticastSocket msg;

    public MessageListener(MulticastSocket socket) {
        msg = socket;
    }

    @Override
    public void run() {

        while (true) {
            // Receive message.
            byte[] buf = new byte[52];

            DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
            try {
                msg.receive(msgPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Translate message to string.
            String msg = new String(msgPacket.getData());
            //print message.
            System.out.println(msg);

        }
    }


}