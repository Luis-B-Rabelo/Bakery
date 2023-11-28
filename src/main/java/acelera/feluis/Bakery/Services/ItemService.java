package acelera.feluis.Bakery.Services;

import acelera.feluis.Bakery.Dtos.ItemDto;

import java.util.List;

public interface ItemService
{
    List<ItemDto> getAllItems();
    ItemDto  getItemByName(String name);
    boolean createItem(String name, String description, byte type, int quantity, double unitPrice, int distributorId);
    void decreaseQuant(int item_id, int quantity);

}
