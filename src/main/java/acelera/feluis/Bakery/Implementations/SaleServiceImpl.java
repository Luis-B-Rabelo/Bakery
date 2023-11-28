package acelera.feluis.Bakery.Implementations;

import acelera.feluis.Bakery.Dtos.SaleDto;
import acelera.feluis.Bakery.Models.ItemModel;
import acelera.feluis.Bakery.Models.SaleModel;
import acelera.feluis.Bakery.Repositories.ItemRepository;
import acelera.feluis.Bakery.Repositories.SaleRepository;
import acelera.feluis.Bakery.Repositories.UserRepository;
import acelera.feluis.Bakery.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService
{
    private final UserRepository user_repository;
    private final SaleRepository sale_repository;

    private final ItemRepository item_repository;


    @Autowired
    public SaleServiceImpl(UserRepository userRepository, SaleRepository sale_repository, ItemRepository item_repository)
    {
        this.user_repository = userRepository;
        this.sale_repository = sale_repository;
        this.item_repository = item_repository;
    }

    private SaleDto mapToSaleDto(SaleModel sale)
    {
        SaleDto saleDto = SaleDto.builder()
                .id(sale.getId())
                .cashierId(sale.getCashier().getId())
                .totalPrice(sale.getTotalPrice())
                .paymentType(sale.getPaymentType())
                .date(sale.getDate())
                .time(sale.getTime())
                .build();

        return saleDto;
    }

    @Override
    public List<SaleDto> getAllSales()
    {
        List<SaleModel> sales = sale_repository.findAll();

        return sales.stream().map((sale) -> mapToSaleDto(sale)).collect(Collectors.toList());
    }

    @Override
    public List<SaleDto> getSaleByDate(LocalDate date)
    {
        List<SaleModel> sales = sale_repository.findByDate(date);

        return sales.stream().map((sale) -> mapToSaleDto(sale)).collect(Collectors.toList());
    }

    @Override
    public boolean createSale(int cashier_id, byte payment_type, double total_price, ArrayList<Integer> items_ids)
    {
        boolean confirmation = true;

        SaleModel sale = new SaleModel();
        sale.setCashier(user_repository.findById(cashier_id));
        sale.setTotalPrice(total_price);
        sale.setPaymentType(payment_type);
        List<ItemModel> items = new ArrayList<>();

        for(int item_id : items_ids)
        {
            items.add(item_repository.findById(item_id));
        }

        sale.setItems(items);
        if(sale_repository.save(sale) != sale)
        {
            confirmation = false;
        }

        return confirmation;
    }


}
