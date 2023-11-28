package acelera.feluis.Bakery.Controllers;

import acelera.feluis.Bakery.Dtos.SaleDto;
import acelera.feluis.Bakery.Models.ItemModel;
import acelera.feluis.Bakery.Services.ItemService;
import acelera.feluis.Bakery.Services.SaleService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CashierController
{
    private final SaleService sale_service;
    private final ItemService item_service;

    @Autowired
    public CashierController(SaleService sale_service, ItemService item_service)
    {
        this.sale_service = sale_service;
        this.item_service = item_service;
    }

    @GetMapping("/caixa")
    public String cashierHomePage(SaleDto sale_dto, HttpSession session, Model model)
    {
        model.addAttribute("items", item_service.getAllItems());
        model.addAttribute("sale", sale_dto);

        return "caixa/home";
    }

    @PostMapping("/caixa/register-sale")
    public String registerSale(@RequestParam(name = "sale-item") List<String> item, SaleDto sale_dto, HttpSession session, Model model)
    {
        model.addAttribute("sale", sale_dto);

        ArrayList<Integer> sale_items = new ArrayList<>();
        byte payment_type = sale_dto.getPaymentType();
        double total_price = sale_dto.getTotalPrice();
        boolean sale = false;

        for(int i = 0; i < item.size(); i += 2)
        {
            sale_items.add(Integer.parseInt(item.get(i)));
            item_service.decreaseQuant(Integer.parseInt(item.get(i)), Integer.parseInt(item.get(i+1)));
        }

        if(sale_service.createSale((Integer)session.getAttribute("user_id"), payment_type, total_price, sale_items))
        {
            return "redirect:/caixa";
        }



        return "error";
    }
}
