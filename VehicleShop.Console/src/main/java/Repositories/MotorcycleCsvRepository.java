package Repositories;

import Data.Converters.IEntityConverter;
import Data.Converters.MotorcycleCsvConverter;
import Data.Entities.Vehicle.Motorcycle;
import Data.Repository.FileRepositoryBase;
import lombok.NonNull;

public class MotorcycleCsvRepository extends FileRepositoryBase<Integer, Motorcycle> {

    public MotorcycleCsvRepository(@NonNull String filepath) {
        super(filepath);
    }

    @Override
    protected IEntityConverter<Motorcycle, String> get_converter() {
        return new MotorcycleCsvConverter();
    }
}
