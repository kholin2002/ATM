package com.kholin.atm.web.controller;

import com.kholin.atm.common.dto.CardDTO;
import com.kholin.atm.common.dto.ClientDTO;
import com.kholin.atm.web.exception.MultipleErrorsException;
import com.kholin.atm.web.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@Log
public class ATMController {
    private final ClientService clientService;

    @GetMapping("/")
    public String getHome(Model model, HttpSession session) {
        if (session.getAttribute("client") != null) {
            return "cabinet";
        }
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("client");
        return "redirect:/";
    }

    @PostMapping(value = "/")
    public String login(Model model, HttpSession session, CardDTO card) {
        log.info("/?" + card.toString());

        ClientDTO client;
        try {
            client = clientService.getClient(card);
        }
        catch (MultipleErrorsException e) {
            model.addAttribute("errors", e.getMessageList());
            model.addAttribute("card", card);
            return "home";
        }

        session.setAttribute("client", client);
        return "redirect:/";
    }
}