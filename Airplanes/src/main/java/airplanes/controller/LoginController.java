package airplanes.controller;

import airplanes.entity.User;
import airplanes.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Order(value = 1)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "redirect:login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session){
        User user = userService.findByUsernameAndPassword(username, new ShaPasswordEncoder().encodePassword(password, "SHA-1"));
        if (user == null)
            return "redirect:login?error";
        else
            session.setAttribute("active-user", user);

        if (user.getRole().equals("ADMIN"))
            return "redirect:admin/profile";

        else if (user.getRole().equals("CLIENT"))
            return "redirect:user/profile";

        else if (user.getRole().equals("PILOT"))
            return "redirect:pilot/profile";

        return null;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
