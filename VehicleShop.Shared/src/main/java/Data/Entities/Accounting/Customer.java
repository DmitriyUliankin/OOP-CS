package Data.Entities.Accounting;

import Data.Entities.IEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Customer
    implements IEntity<Integer>
{
    public Customer(String name, String dob)
    {
        _name = name;
        _DOB = dob;
    }

    private int _id;
    private @Getter @Setter String _name;
    private @Getter @Setter String _DOB;

    public Integer get_key() {
        return _id;
    }
}
