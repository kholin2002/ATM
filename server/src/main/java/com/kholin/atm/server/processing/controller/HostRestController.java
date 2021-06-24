package com.kholin.atm.server.processing.controller;

import com.kholin.atm.common.dto.CardDTO;
import com.kholin.atm.common.dto.ClientDTO;
import com.kholin.atm.server.processing.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@AllArgsConstructor
@Log
public class HostRestController {

    private final ClientService clientService;

    @GetMapping(value = "/clients/{clientId}")
    public ClientDTO getClient(@PathVariable long clientId) {
        log.info("/clients/" + clientId);
        ClientDTO response = clientService.getClient(clientId);
        log.info(response.toString());
        return response;
    }

    @GetMapping(value = "/clients", params = {"number","expire","cvc2"})
    public ClientDTO getClient(@Valid CardDTO request) {
        log.info("/clients?" + request.toString());
        ClientDTO response = clientService.getClient(request);
        log.info(response.toString());
        return response;
    }

    @GetMapping(value = "/clients/all")
    public Set<ClientDTO> getClients() {
        log.info("/clients/all");
        Set<ClientDTO> response = clientService.getClients();
        log.info(response.toString());
        return response;
    }

}
