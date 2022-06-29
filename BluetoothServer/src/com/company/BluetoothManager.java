package DataCollector.src.com.company;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.*;

/**
 * Manages bluetooth (connect to HC-05).
 */
public class BluetoothManager {


    //bluetooth URL
    private final String bluetoothUrl ="btspp://98D361F5FEB5:1;authenticate=false;encrypt=false;master=false";
    private OutputStream outputStream;
    private InputStream inputStream;


    public BluetoothManager() throws IOException {
        //Start bluetooth connection
        StreamConnection conn = (StreamConnection) Connector.open(bluetoothUrl);

        //Open output stream
        outputStream = conn.openDataOutputStream();
        //Open input stream
        inputStream = conn.openInputStream();
    }

    /**
     * Sends strings to HC-05
     *
     * @param message message to be sent
     * @throws IOException exception
     */
    public void writeToHC05(String message) throws IOException {
        outputStream.write(message.getBytes());
    }

    /**
     * Returns input stream of HC-05
     *
     * @return input stream
     */
    public InputStream getInputStream() {
        return inputStream;
    }
}

