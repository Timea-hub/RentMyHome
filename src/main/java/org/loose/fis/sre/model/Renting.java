package org.loose.fis.sre.model;
import org.dizitart.no2.objects.Id;

public class Renting {

    @Id
    private String city;
    private String room;
    private String cleaningService;
    private double pricePerson;

    public Renting(String city, String room, String cleaningService, double pricePerson) {
        this.city = city;
        this.room = room;
        this.cleaningService = cleaningService;
        this.pricePerson = pricePerson;
    }

    public Renting(){
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCleaningService() {
        return cleaningService;
    }

    public void setCleaningService(String cleaningService) {
        this.cleaningService = cleaningService;
    }

    public double getPricePerson() {
        return pricePerson;
    }

    public void setPricePerson(double pricePerson) {
        this.pricePerson = pricePerson;
    }
}
