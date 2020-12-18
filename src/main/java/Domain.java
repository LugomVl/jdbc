import bl.Util;
import entity.Address;
import service.AddressService;

import java.sql.SQLException;

public class Domain {

    public static void main(String[] args) throws SQLException {
        Address address = new Address();
        address.setId(1l);
        address.setCountry("Belarus");
        address.setCity("Gotham");
        address.setStreet("Blyat");
        address.setPostCode("220006");

        AddressService addressService = new AddressService();
        //addressService.add(address);
        //System.out.println(addressService.getAll());
        //address.setCity("Minsk");
        //addressService.update(address);
        System.out.println(addressService.getAll());
    }
}
