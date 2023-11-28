package acelera.feluis.Bakery.Services;

import acelera.feluis.Bakery.Dtos.SaleDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface SaleService
{
    List<SaleDto> getAllSales();
    List<SaleDto>  getSaleByDate(LocalDate date);
    boolean createSale(int cashier_id, byte payment_type, double total_price, ArrayList<Integer> items_ids);
}
