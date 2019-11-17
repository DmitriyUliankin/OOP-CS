package Services.Shop.Models;

import lombok.Getter;

public class ProductListItem
{
    private @Getter int _id;
    private @Getter String _type;
    private @Getter String _name;
    private @Getter double _price;

    public ProductListItem(int id, String type, String name, double price)
    {
        _id = id;
        _type = type;
        _name = name;
        _price = price;
    }
}
