package acelera.feluis.Bakery.Controllers;

import acelera.feluis.Bakery.Dtos.DistributorDto;
import acelera.feluis.Bakery.Dtos.ItemDto;
import acelera.feluis.Bakery.Dtos.SaleDto;
import acelera.feluis.Bakery.Dtos.UserDto;
import acelera.feluis.Bakery.Models.UserModel;
import acelera.feluis.Bakery.Services.DistributorService;
import acelera.feluis.Bakery.Services.ItemService;
import acelera.feluis.Bakery.Services.SaleService;
import acelera.feluis.Bakery.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ManagerController
{
    private final UserService user_service;
    private final ItemService item_service;
    private final DistributorService distributor_service;
    private final SaleService sale_service;

    @Autowired
    public ManagerController(UserService user_service, ItemService item_service, DistributorService distributor_service, SaleService sale_service)
    {
        this.user_service = user_service;
        this.item_service = item_service;
        this.distributor_service = distributor_service;
        this.sale_service = sale_service;
    }

    @GetMapping("/gerente")
    public String managerHomePage(Model model)
    {
        return "gerente/home";
    }

    @GetMapping("/gerente/funcionarios")
    public String listUsersPage(Model model)
    {
        model.addAttribute("users", user_service.getAllUsers());
        return "gerente/funcionario/list";
    }

    @GetMapping("/gerente/cadastrar-funcionario")
    public String registerUserPage(Model model)
    {
        model.addAttribute("user", new UserModel());
        return "gerente/funcionario/register";
    }

    @PostMapping("/gerente/register-user")
    public String registerUserFunc(UserDto user, Model model)
    {
        model.addAttribute("registerUser", user);

        if(user_service.createUser(user.getRole(), user.getPassword()))
        {
            System.out.println("User registered!");

            user = null;

            return "redirect:/gerente/funcionarios";
        }
        else
        {
            System.out.println("Error!");
            return "error";
        }
    }

    @GetMapping("/gerente/itens")
    public String listItems(Model model)
    {
        model.addAttribute("items", item_service.getAllItems());
        return "gerente/item/list";
    }

    @GetMapping("/gerente/cadastrar-item")
    public String registerItemPage(ItemDto item, Model model)
    {
        model.addAttribute("item", item);
        model.addAttribute("distributors", distributor_service.getAllDistributors());
        return "gerente/item/register";
    }

    @PostMapping("/gerente/register-item")
    public String registerItemFunc(ItemDto item, Model model)
    {
        model.addAttribute("item", item);

        if(item_service.createItem(
                item.getName(),
                item.getDescription(),
                item.getType(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getDistributorId()
                ))
        {
            System.out.println("Item registered!");

            item = null;

            return "redirect:/gerente/itens";
        }
        else
        {
            System.out.println("Error!");
            return "error";
        }
    }

    @GetMapping("/gerente/fornecedores")
    public String listDistributors(Model model)
    {
        model.addAttribute("distributors", distributor_service.getAllDistributors());
        return "gerente/fornecedor/list";
    }

    @GetMapping("/gerente/cadastrar-fornecedor")
    public String registerDistributorPage(DistributorDto distributor, Model model)
    {
        model.addAttribute("distributor", distributor);
        return "gerente/fornecedor/register";
    }

    @PostMapping("/gerente/register-distributor")
    public String registerDistributorFunc(DistributorDto distributor, Model model)
    {
        model.addAttribute("distributor", distributor);

        if(distributor_service.createDistributor(
                distributor.getName(),
                distributor.getEmail(),
                distributor.getCellphone(),
                distributor.getPhone()
        ))
        {
            System.out.println("Distributor registered!");

            distributor = null;

            return "redirect:/gerente/fornecedores";
        }
        else
        {
            System.out.println("Error!");
            return "error";
        }
    }

    @GetMapping("/gerente/vendas")
    public String listSales(Model model)
    {
        model.addAttribute("sales", sale_service.getAllSales());
        return "gerente/venda/list";
    }
    

}
