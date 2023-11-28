package acelera.feluis.Bakery.Implementations;

import acelera.feluis.Bakery.Dtos.ItemDto;
import acelera.feluis.Bakery.Models.ItemModel;
import acelera.feluis.Bakery.Repositories.DistributorRepository;
import acelera.feluis.Bakery.Repositories.ItemRepository;
import acelera.feluis.Bakery.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService
{
    private final ItemRepository item_repository;
    private final DistributorRepository distributor_repository;

    @Autowired
    public ItemServiceImpl(ItemRepository item_repository, DistributorRepository distributorRepository)
    {
        this.item_repository = item_repository;
        this.distributor_repository = distributorRepository;
    }


    private ItemDto mapToItemDto(ItemModel item)
    {
        ItemDto itemDto = ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .unitPrice(item.getUnitPrice())
                .quantity(item.getQuantity())
                .type(item.getType())
                .distributorId(item.getDistributor().getId())
                .build();

        return itemDto;
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<ItemModel> items = item_repository.findAll();

        return items.stream().map((item) -> mapToItemDto(item)).collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemByName(String name) {

        ItemModel item = item_repository.findByName(name);

        return this.mapToItemDto(item);
    }

    @Override
    public boolean createItem(String name, String description, byte type, int quantity, double unitPrice, int distributorId)
    {
        boolean confirmation = true;

        ItemModel item = new ItemModel();
        item.setName(name);
        item.setDescription(description);
        item.setType(type);
        item.setQuantity(quantity);
        item.setUnitPrice(unitPrice);
        item.setDistributor(distributor_repository.findById(distributorId));

        if(item_repository.save(item) != item)
        {
            confirmation = false;
        }

        return confirmation;
    }

    @Override
    public void decreaseQuant(int item_id, int quantity) {
        item_repository.updateItemQuantity(
                item_repository.findById(item_id).getQuantity() - quantity,
                item_id);
    }

}
