package Data.Converters;

import Data.Entities.Accounting.Enums.SaleableStatus;
import Data.Entities.Vehicle.Car;
import Data.Entities.Vehicle.Enums.FuelType;
import com.opencsv.CSVParser;

public class CarCsvConverter implements IEntityConverter<Car, String> {

    @Override
    public Car ConvertReverse(String csv) {
        CSVParser parser = new CSVParser();
        String[] csvList;
        try {
            csvList = parser.parseLine(csv);
            int serialNumber = Integer.parseInt(csvList[0]);
            String name = csvList[1];
            int year = Integer.parseInt(csvList[2]);
            FuelType fuelType = FuelType.valueOf(csvList[3]);
            double price = Double.parseDouble(csvList[4]);
            SaleableStatus status = SaleableStatus.valueOf(csvList[5]);
            return new Car(serialNumber, name, year, fuelType, price, status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String Convert(Car car) {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", car.get_key(), car.get_name(), car.get_year(), car.get_fuelType(), car.get_price(), car.get_status());
    }
}

