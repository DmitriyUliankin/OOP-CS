package Data.Entities.Accounting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Customer {
    private @Getter int _id;
    private @Getter @Setter String _name;
    private @Getter @Setter String _DOB;
}
