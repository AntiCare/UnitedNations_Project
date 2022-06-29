package nl.saxion.project.mobilestation;

import nl.saxion.project.mobilestation.model.Coordinates;
import nl.saxion.project.mobilestation.model.Measurement;
import nl.saxion.project.mobilestation.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MobileStationServerApplication implements ApplicationRunner {

    //our db repository, needed for writing data into database
    @Autowired
    private MeasurementRepository repository;

    //Address for receiving messages.
    private final static String ADDR = "224.0.0.5";
    //Port for receiving messages.
    private final static int PORT = 8888;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MobileStationServerApplication.class, args);
    }




    @Override
    public void run(ApplicationArguments args) throws Exception {
        //this part of code is for receiving msg from bluetooth NOT FINISHED -------------
        float a = 5.5f;
        int intbits = Float.floatToIntBits(a);
        byte[] bytes = new byte[] {(byte) (intbits >> 24),(byte) (intbits >> 16),(byte) (intbits >> 8),(byte) (intbits )};

        int unixTime = (int)(System.currentTimeMillis()/1000);
        byte[] unixBytes = new byte[] {(byte) (unixTime >> 24),(byte) (unixTime >> 16),(byte) (unixTime >> 8),(byte) (unixTime)};
        System.out.println(unixTime);
        ByteBuffer buffer = ByteBuffer.wrap(unixBytes);
        int unixtimestamp = buffer.getInt();
        System.out.println(unixtimestamp);

        byte[] droneMeasurement = new byte[17];
        int droneId = 3;
        int dataType = 2;
        int combined =  droneId << 4;
        combined |=  dataType;
        System.out.println("combined = " + combined);
        byte[] latitude = getBytesFromFloat( 55.555f);
        byte[] longitude = getBytesFromFloat( -120.222f);
        byte[] time = getBytesFromInt((int)(System.currentTimeMillis()/1000));
        byte[] value = getBytesFromFloat(69.69f);

        droneMeasurement[0] = (byte) combined;
        int loopcount = 0;
        for (int i = 1 ; i < 5 ; i++) {
            droneMeasurement[i] = latitude[loopcount];
            loopcount++;
        }
        loopcount = 0;
        for (int i = 5 ; i < 9 ; i++) {
            droneMeasurement[i] = longitude[loopcount];
            loopcount++;
        }
        loopcount = 0;
        for (int i = 9 ; i < 13 ; i++) {
            droneMeasurement[i] = time[loopcount];
            loopcount++;
        }
        loopcount = 0;
        for (int i = 13 ; i < 17 ; i++) {
            droneMeasurement[i] = value[loopcount];
            loopcount++;
        }
        loopcount = 0;

        for (int i = 0; i < droneMeasurement.length; i++) {
            System.out.println(droneMeasurement[i]);
        }

        Measurement m = getMeasurementFromBytes(droneMeasurement);
        System.out.println(m.getDate().toString() + " " +  m.getRadiation());
        //drone id

        //datatype

        // coordinates

        // time

        // value

        //Socket for receiving messages.
        InetAddress ia = InetAddress.getByName(ADDR);
        MulticastSocket ms = new MulticastSocket(PORT);
        //Subscribe to the address in order to receive all messages that were sent to that address
        ms.joinGroup(ia);

        //Initialize mobile station listener.
        MessageListener listener = new MessageListener(ms);
        Thread thread = new Thread(listener);
        //Start mobile station listener
        thread.start();
        repository.deleteAll();
        for (int i = 0; i < 30 ; i++) {
            addMeasurementsToDatabase();
        }

    }

    //for now hardcoded, this method needs to receive msg once we are able to encode upcoming message
    public void addMeasurementsToDatabase() {

        //this part of code is for adding the data we received to database this will need to run everytime we receive data
        List<String> objectsDetected = new ArrayList<>();
        int numb = (int) Math.floor(Math.random() * 5);
        for (int i = 0; i < numb ; i++) {
            objectsDetected.add(getRandomMaterial());
        }


        Coordinates coordinates = new Coordinates(1.2f,2.4f);
        Measurement newMeasurement = new Measurement(getNumb(),getNumb(),"stone",getNumb(),getNumb(),"blue",coordinates,Date.from(Instant.now()),objectsDetected,getNumb(),getNumb(),getNumb());
        repository.save(newMeasurement);
    }

    public String getRandomMaterial(){

        int numb = (int) Math.floor(Math.random() * 10)+1;
        String material = "nothing";

        if(numb == 1) {
            material = "Bike";
        }
        else if (numb == 2){
            material = "Corona mask";
        }
        else if (numb == 3){
            material = "Corona Vaccine";
        }
        else if (numb == 4){
            material = "Phone";
        }
        else if (numb == 5){
            material = "Dishwasher";
        }
        else if (numb == 6){
            material = "Keys";
        }
        else if (numb == 7){
            material = "Bitcoin";
        }
        else if (numb == 8){
            material = "Scissors ";
        }
        else if (numb == 9){
            material = "Headphones";
        }
        else if (numb == 10){
            material = "Bag";
        }


        return material;

    }

    public float getFloatFromBytes(byte[] floatBytes){
        ByteBuffer buffer = ByteBuffer.wrap(floatBytes);
        return  buffer.getFloat();
    }

    public int getIntFromBytes(byte[] unixBytes){
        ByteBuffer buffer = ByteBuffer.wrap(unixBytes);
        return  buffer.getInt();
    }

    public byte[] getBytesFromFloat(float f){
        int intbits = Float.floatToIntBits(f);
        byte[] bytes = new byte[] {(byte) (intbits >> 24),(byte) (intbits >> 16),(byte) (intbits >> 8),(byte) (intbits )};
        return bytes;
    }

    public byte[] getBytesFromInt(int i){
        byte[] bytes = new byte[]{(byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) (i)};
        return bytes;
    }

    private int getNumb(){

        int numb = (int) Math.floor(Math.random() * 100);

        return numb;
    }

    public Measurement getMeasurementFromBytes(byte[] droneMeasurement){
        int dataType = droneMeasurement[0] & 0xF;
        int droneId = droneMeasurement[0] >> 4;
        byte[] latitude = new byte[4];
        for (int i = 1; i < 5 ; i++) {
            latitude[i-1] = droneMeasurement[i];
        }
        byte[] longitude = new byte[4];
        for (int i = 5; i < 9; i++) {
            longitude[i-5] = droneMeasurement[i];
        }
        byte[] time = new byte[4];
        for (int i = 9; i < 13; i++) {
            time[i-9] = droneMeasurement[i];
        }
        byte[] value = new byte[4];
        for (int i = 13; i < 17; i++) {
            value[i-13] = droneMeasurement[i];
        }

        Date date = new Date( getIntFromBytes(time) * 1000L);



        List<String> objectsDetected = new ArrayList<>();
        objectsDetected.add("alien");
        objectsDetected.add(("face mask"));

        Coordinates coordinates = new Coordinates(getFloatFromBytes(latitude), getFloatFromBytes(longitude));

        Measurement measurement = new Measurement(getNumb(),getNumb(),"ei",getNumb(),getNumb(),"blue",coordinates,Date.from(Instant.now()),objectsDetected,getNumb(),getNumb(),getNumb());
        measurement.addData(dataType,getFloatFromBytes(value));
        measurement.addData(dataType,getFloatFromBytes(value));

        return measurement;
    }


}
