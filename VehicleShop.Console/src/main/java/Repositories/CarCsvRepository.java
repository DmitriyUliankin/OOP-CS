package Repositories;

import Data.Converters.CarCsvConverter;
import Data.Converters.IEntityConverter;
import Data.Entities.Vehicle.Car;
import Data.Repository.FileRepositoryBase;
import lombok.NonNull;

public class CarCsvRepository extends FileRepositoryBase<Integer, Car> {
    private IEntityConverter<Car, String> _converter;

    public CarCsvRepository(@NonNull String filepath) {
        super(filepath);
        _converter = new CarCsvConverter();
    }

    @Override
    protected IEntityConverter<Car, String> get_converter() {
        return _converter;
    }
}
