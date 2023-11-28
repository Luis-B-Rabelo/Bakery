package acelera.feluis.Bakery.Controllers;

import acelera.feluis.Bakery.Dtos.UserDto;
import acelera.feluis.Bakery.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController
{
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home()
    {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model)
    {
        String url = "login/login";

        if(session.getAttribute("user_id") == null)
        {
            model.addAttribute("login", new UserDto());
        }
        else
        {
            switch (Byte.parseByte(session.getAttribute("user_id").toString()))
            {
                case 1:
                    url = "redirect:/gerente";
                    break;
                case 2:
                    url = "redirect:/caixa";
                    break;
                default:
                    url= "redirect:/error";
                    break;
            }
        }

        return url;
    }

    @PostMapping("/login")
    public String logged(@ModelAttribute UserDto user, HttpServletRequest request, Model model)
    {
        String url = "redirect:/error";

        model.addAttribute("login", user);

        if(userService.confirmUserLogin(user.getId(), user.getPassword()))
        {
            System.out.println("Logged!");

            request.getSession().setAttribute("user_id", user.getId());

            switch (userService.getUserById(user.getId()).getRole())
            {
                case 1:
                    url = "redirect:/gerente";
                    break;
                case 2:
                    url = "redirect:/caixa";
                    break;
                default:
                    url= "redirect:/error";
                    break;
            }
        }
        else
        {
            System.out.println("Error!");
        }

        return url;
    }

    @GetMapping("/sair")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/login";
    }

}
