package Data.Converters;

import Data.Entities.Accounting.Enums.SaleableStatus;
import Data.Entities.Vehicle.Enums.FuelType;
import Data.Entities.Vehicle.Motorcycle;
import com.opencsv.CSVParser;

public class MotorcycleCsvConverter implements IEntityConverter<Motorcycle, String> {

    @Override
    public Motorcycle ConvertReverse(String csv) {
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
            return new Motorcycle(serialNumber, name, year, fuelType, price, status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String Convert(Motorcycle motorcycle) {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", motorcycle.get_key(), motorcycle.get_name(), motorcycle.get_year(), motorcycle.get_fuelType(), motorcycle.get_price(), motorcycle.get_status());
    }
}
