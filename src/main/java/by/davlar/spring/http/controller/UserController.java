package by.davlar.spring.http.controller;

import by.davlar.spring.database.entity.Role;
import by.davlar.spring.service.CompanyService;
import by.davlar.spring.service.UserService;
import by.davlar.spring.service.dto.UserCreateEditDto;
import by.davlar.spring.service.dto.UserReadDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR') || #id.equals(authentication.principal.id)")
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
        return "user/login";
    }


    @GetMapping("/registration")
    public String registration(HttpSession session, Model model) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("user", UserCreateEditDto.newEmptyObject());
        return "user/registration";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated UserCreateEditDto userCreateEditDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", userCreateEditDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        var userReadDto = userService.create(userCreateEditDto);
        return "redirect:/users/" + userReadDto.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR') || #id.equals(authentication.principal.id)")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute @Validated UserCreateEditDto userCreateEditDto) {
        var userReadDto = userService.update(id, userCreateEditDto);
        return "redirect:/users/" + userReadDto.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') || #id.equals(authentication.principal.id)")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
