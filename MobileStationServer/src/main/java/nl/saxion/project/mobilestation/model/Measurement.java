package nl.saxion.project.mobilestation.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "measurements")
public class Measurement {

    @Id
    private int id;
    private int droneId;
    private String material;
    private int dayId;
    private int level;
    private String color;
    private Coordinates coordinates;
    private Date date;
    private List<String> objectsDetected;
    private int oilDetection;
    private int radiation;
    private int surfaceTemperature;

    public Measurement(int id, int droneId, String material, int dayId, int level, String color, Coordinates coordinates, Date date, List<String> objectsDetected, int oilDetection, int radiation, int surfaceTemperature) {
        this.id = id;
        this.droneId = droneId;
        this.material = material;
        this.dayId = dayId;
        this.level = level;
        this.color = color;
        this.coordinates = coordinates;
        this.date = date;
        this.objectsDetected = objectsDetected;
        this.oilDetection = oilDetection;
        this.radiation = radiation;
        this.surfaceTemperature = surfaceTemperature;
    }

    private int getNumb(){

        int numb = (int) Math.floor(Math.random() * 100);

        return numb;
    }
    private int getCaseNumb(){

        int numb = (int) Math.floor(Math.random() * 5)+1;

        return numb;
    }
    public int getId() {
        return id;
    }
    public void addObject(String object){
        objectsDetected.add(object);
    }

    public String getMaterial() {
        return material;
    }

    public int getDayId() {
        return dayId;
    }

    public String getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getDate() {
        return date;
    }

    public List<String> getObjectsDetected() {
        return objectsDetected;
    }

    public int getOilDetection() {
        return oilDetection;
    }

    public int getRadiation() {
        return radiation;
    }

    public int getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public void addData(int datatype, float value){
        switch (datatype){
            case 1:
                setColorFromValue(value);
                break;
            case 2:
                setRadiation((int)value);
                break;
            case 3:
                setMaterialFromValue(value);
                break;
            case 4:
                setOilDetection((int) value);
                break;
            case 5:
                setSurfaceTemperature((int) value);
                break;
            case 6:
                setObjectsFromValue((int) value);
                break;
        }
    }

    private void setColorFromValue(float value) {
        int colorId = (int) value;
        switch (colorId){
            case 1: setColor("BLUE");
            case 2: setColor("GREEN");
            case 3: setColor("BLACK");
            case 4: setColor("WHITE");
            case 5: setColor("PINK");
        }
    }

    private String setMaterialFromValue(float value){
        int materialId = (int) value;
        String material = null;
        switch (materialId){
            case 1: material = ("ICE");
            case 2: material = ("LIQUID");
            case 3: material = ("GAS");
            case 4: material = ("SAND");
            case 5: material = ("STONE");
        }
        return material;
    }

    private void setObjectsFromValue(float value){
        int objectId = (int) value;
        switch (objectId){
            case 1: addObject("NONE");
            case 2: addObject("HUMAN");
            case 3: addObject("BIKE");
            case 4: addObject("ATLANTIS");
            case 5: addObject("OTHER");
        }
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setObjectsDetected(List<String> objectsDetected) {
        this.objectsDetected = objectsDetected;
    }

    public void setOilDetection(int oilDetection) {
        this.oilDetection = oilDetection;
    }

    public void setRadiation(int radiation) {
        this.radiation = radiation;
    }

    public void setSurfaceTemperature(int surfaceTemperature) {
        this.surfaceTemperature = surfaceTemperature;
    }

    public int getDroneId() {
        return droneId;
    }

    public int getLevel() {
        return level;
    }
}
