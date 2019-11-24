package Repositories;

import Data.Converters.IEntityConverter;
import Data.Converters.MotorcycleCsvConverter;
import Data.Entities.Vehicle.Motorcycle;
import Data.Repository.FileRepositoryBase;
import lombok.NonNull;

public class MotorcycleCsvRepository extends FileRepositoryBase<Integer, Motorcycle> {
    private IEntityConverter<Motorcycle, String> _converter;

    public MotorcycleCsvRepository(@NonNull String filepath) {
        super(filepath);
        _converter = new MotorcycleCsvConverter();
    }

    @Override
    protected IEntityConverter<Motorcycle, String> get_converter() {
        return _converter;
    }
}
