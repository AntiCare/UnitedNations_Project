package nl.saxion.project.mobilestation.model;

public class Coordinates {
    private float lat;
    private float lon;

    public Coordinates(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }
}
