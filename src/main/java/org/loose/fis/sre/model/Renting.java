package org.loose.fis.sre.model;
import org.dizitart.no2.objects.Id;

public class Renting {

    @Id
    private String city;
    private String rent;
    private int capacity;
    private String cleaningService;
    private double cleaningServicePrice;
    private double price;

    public Renting(String city, String rent, int capacity, String cleaningService, double cleaningServicePrice, double price) {
        this.city = city;
        this.rent = rent;
        this.capacity = capacity;
        this.cleaningService = cleaningService;
        this.cleaningServicePrice = cleaningServicePrice;
        this.price = price;
    }

    public Renting(){
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public String getCleaningService() {
        return cleaningService;
    }

    public void setCleaningService(String cleaningService) {
        this.cleaningService = cleaningService;
    }

    public double getCleaningServicePrice() {
        return cleaningServicePrice;
    }

    public void setCleaningServicePrice(double cleaningServicePrice) {
        this.cleaningServicePrice = cleaningServicePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
