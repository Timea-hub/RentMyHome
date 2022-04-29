package org.loose.fis.sre.services;


        import org.dizitart.no2.Nitrite;
        import org.dizitart.no2.objects.ObjectRepository;
        import org.loose.fis.sre.exceptions.DestinationIncompleteException;
        import org.loose.fis.sre.exceptions.IncorrectPasswordException;
        import org.loose.fis.sre.exceptions.UsernameDoesNotExistsException;
        import org.loose.fis.sre.model.Renting;
        import org.loose.fis.sre.model.User;


        import java.util.ArrayList;
        import java.util.List;
        import java.util.Objects;

        import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
        import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class RentingService {

    private static ObjectRepository<Renting> rentingRepository;
    private static Nitrite database;

    public static void initDatabase() {

        database = Nitrite.builder()
                .filePath(getPathToFile("renting.db").toFile())
                .openOrCreate("test", "test");

        rentingRepository = database.getRepository(Renting.class);
    }

    public static void addDestination(String city, String hotel, String typeOfTransport, double price) throws DestinationIncompleteException {
        if ((city.equals("")) || (hotel.equals("")) || (typeOfTransport.equals(""))) throw new DestinationIncompleteException();
        rentingRepository.insert(new Renting(city, hotel, typeOfTransport, price));
    }

    public static List<Renting> getAllUsers() {
        return rentingRepository.find().toList();
    }

    public static ArrayList<Renting> getAllRents() {
        ArrayList<Renting> list = new ArrayList<>();
        for(Renting renting : rentingRepository.find()) {
            list.add(renting);
        }
        return list;
    }

    public static void removeRent(Renting renting) {
        rentingRepository.remove(eq("Available house",renting.getRoom()));
    }


    public static List<Renting> getHousebyCity(String city) {
        ArrayList<Renting> list = new ArrayList<>();
        for(Renting renting : rentingRepository.find()) {
            if(renting.getCity().equals(city))list.add(renting);
        }
        return list;
    }

    public static void close() {
        rentingRepository.close();
        database.close();
    }
}
