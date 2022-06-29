package drone_controller.IO;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

public class Serial {

    public SerialPort getSerialPort() {

        SerialPort[] allSerialPorts = SerialPort.getCommPorts();

        System.out.println("List of all available serial ports:");
        for (int i = 0; i < allSerialPorts.length; i++) {
            SerialPort serialPort = allSerialPorts[i];
            System.out.println(i + ". " + serialPort.getDescriptivePortName());
        }

        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a port:");
        choice = scanner.nextInt();
        SerialPort port = allSerialPorts[choice];

        return port;
    }

    public void openPort(SerialPort serialPort) {
        if (serialPort.openPort()) {
            System.out.println("\nOpened serial port: " + serialPort.getDescriptivePortName());
        } else {
            System.out.println("Unable to open port.");
            return;
        }
        serialPort.setBaudRate(115200);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }
}
