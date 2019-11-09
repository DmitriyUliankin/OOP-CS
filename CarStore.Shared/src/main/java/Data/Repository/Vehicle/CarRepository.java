package Data.Repository.Vehicle;

import Data.Entities.Vehicle.Car;
import Data.Repository.IEntityRepository;

import java.util.List;

public class CarRepository
    implements IEntityRepository<Car>
{
    private static CarRepository ourInstance = new CarRepository();

    public static CarRepository getInstance() {
        return ourInstance;
    }

    private CarRepository() {
    }

    public Car Get(int id) {
        //todo: Get!
        return null;
    }

    public List<Car> List() {
        //todo: List!
        return null;
    }

    public void Create(Car entity) {
        //todo: Create!
    }

    public void Update(Car entity) {
        //todo: Update!
    }
}