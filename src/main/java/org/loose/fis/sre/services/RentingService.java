package org.loose.fis.sre.services;


        import org.dizitart.no2.Nitrite;
        import org.dizitart.no2.NitriteBuilder;
        import org.dizitart.no2.objects.ObjectRepository;
        import org.loose.fis.sre.exceptions.RentIncompleteException;
        import org.loose.fis.sre.model.Renting;


        import java.util.ArrayList;
        import java.util.List;
        import java.util.Objects;

        import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
        import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class RentingService {

    private static ObjectRepository<Renting> rentingRepository;
    private static Nitrite database;
    //NitriteBuilder builder = Nitrite.builder();

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("rent.db").toFile())
                .openOrCreate("test", "test");

        database = Nitrite.builder()
                .filePath(getPathToFile("rentingRepository.db").toFile())
                .openOrCreate("test", "test");

        rentingRepository = database.getRepository(Renting.class);
    }

    public static void addRent(String city, String rent, int capacity, String cleaningService, double cleaningServicePrice, double price) throws RentIncompleteException {
        if ((city.equals("")) || (rent.equals("")) || (cleaningService.equals(""))) throw new RentIncompleteException();
        Renting r=new Renting(city, rent, capacity, cleaningService, cleaningServicePrice, price);
        rentingRepository.insert(r);
    }

    public static List<Renting> getAllUsers() {
        return rentingRepository.find().toList();
    }

    public static ArrayList<Renting> getAllRents() {
        ArrayList<Renting> list = new ArrayList<>();
        for(Renting rent : rentingRepository.find()) {
            list.add(rent);
        }
        return list;
    }

    public static void removeRent(Renting rent) {
        rentingRepository.remove(eq("rent",rent.getRent()));
    }


    public static List<Renting> getHousebyCity(String city) {
        ArrayList<Renting> list = new ArrayList<>();
        for(Renting rent : rentingRepository.find()) {
            if(rent.getCity().equals(city))list.add(rent);
        }
        return list;
    }

    public static void close() {
        rentingRepository.close();
        database.close();
    }
}
