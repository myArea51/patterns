import factory.Vehicle;
import factory.VehicleFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex Veng on 04.04.2016.
 */
public class FactoryTest {

    @Test
    public void isIndexExpected() {
        Vehicle vehicle = VehicleFactory.getVehicle(1, 12, 2, "Sedan");
        assertEquals(vehicle.toString(), "Sedan");

        Vehicle vehicleTwo = VehicleFactory.getVehicle(2, 12, 2, "MinyVan");
        assertEquals(vehicleTwo.toString(), "MinyVan");
    }
}
