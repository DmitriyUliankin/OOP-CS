package Repositories;

import Data.Converters.CarCsvConverter;
import Data.Converters.IEntityConverter;
import Data.Entities.Vehicle.Car;
import Data.Repository.FileRepositoryBase;
import lombok.NonNull;

public class CarCsvRepository extends FileRepositoryBase<Integer, Car> {


    public CarCsvRepository(@NonNull String filepath) {
        super(filepath);
        _entityName = "Car";
    }

    @Override
    protected IEntityConverter<Car, String> get_converter() {
        return new CarCsvConverter();
    }
}
