package ru.itmo.wm4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import ru.itmo.wm4.form.UserCredentials;
import ru.itmo.wm4.form.validator.UserCredentialsEnterValidator;
import ru.itmo.wm4.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wm4.form.NoticeCredentials;
import ru.itmo.wm4.form.validator.NoticeCredentialsValidator;
import ru.itmo.wm4.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AddNoticePage extends Page {
    private final NoticeService noticeService;
    private final NoticeCredentialsValidator noticeCredentialsValidator;

    public AddNoticePage(NoticeService noticeService, NoticeCredentialsValidator noticeCredentialsValidator) {
        this.noticeService = noticeService;
        this.noticeCredentialsValidator = noticeCredentialsValidator;
    }

    @InitBinder("NoticeCredentials")
    public void initSubmitFormBinder(WebDataBinder binder) {
        binder.addValidators(noticeCredentialsValidator);
    }

    @GetMapping(path = "/notice")
    public String submitGet(Model model, HttpSession httpSession) {
        if (getUser(httpSession) == null) {
            return "redirect:/";
        }
        model.addAttribute("noticeForm", new NoticeCredentials());
        return "AddNoticePage";
    }

    @PostMapping(path = "/notice")
    public String submitPost(@Valid @ModelAttribute("noticeForm") NoticeCredentials submitForm,
                             BindingResult bindingResult, HttpSession httpSession) {
        if (getUser(httpSession) == null) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            return "AddNoticePage";
        }

        noticeService.save(submitForm);

        return "redirect:/";
    }
}

