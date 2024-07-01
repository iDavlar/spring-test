package by.davlar.spring.http.controller;

import by.davlar.spring.database.entity.Role;
import by.davlar.spring.service.CompanyService;
import by.davlar.spring.service.UserService;
import by.davlar.spring.service.dto.UserCreateEditDto;
import by.davlar.spring.service.dto.UserReadDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping
    public String findAll(HttpSession session, Model model) {
        return Optional.ofNullable(session.getAttribute("user"))
                .map(value -> {
                    model.addAttribute("users", userService.findAll());
                    return "user/users";
                })
                .orElse("redirect:/users/login");
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies", companyService.findAll());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        return Optional.ofNullable(session.getAttribute("user"))
                .map(value -> {
                    return "redirect:/users/" + ((UserReadDto) value).getId();
                })
                .orElse("user/login");
    }

    @PostMapping("/login")
    public String loginPost(HttpSession session,
                            RedirectAttributes redirectAttributes,
                            @RequestParam String username,
                            @RequestParam String password) {
        return userService.findByUsername(username)
                .map(userReadDto -> {
                    session.setAttribute("user", userReadDto);
                    if (userReadDto.getRole().equals(Role.ADMIN)) {
                        return "redirect:/users";
                    }
                    return "redirect:/users/" + userReadDto.getId();
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("username", username);
                    redirectAttributes.addFlashAttribute("password", password);
                    return "redirect:/users/login";
                });

    }

    @GetMapping("/registration")
    public String registration(HttpSession session, Model model) {
        return Optional.ofNullable(session.getAttribute("user"))
                .map(value -> {
                    model.addAttribute("users", userService.findAll());
                    return "redirect:/users/" + ((UserReadDto) value).getId();
                })
                .orElseGet(() -> {
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies", companyService.findAll());
                    model.addAttribute("user", UserCreateEditDto.newEmptyObject());
                    return "user/registration";
                });
    }

    @PostMapping
    public String create(@ModelAttribute UserCreateEditDto userCreateEditDto, RedirectAttributes redirectAttributes) {
        if (true) {
            redirectAttributes.addFlashAttribute("user", userCreateEditDto);
            return "redirect:/users/registration";
        }
        var userReadDto = userService.create(userCreateEditDto);
        return "redirect:/users/" + userReadDto.getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute UserCreateEditDto userCreateEditDto) {
        var userReadDto = userService.update(id, userCreateEditDto);
        return "redirect:/users/" + userReadDto.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
