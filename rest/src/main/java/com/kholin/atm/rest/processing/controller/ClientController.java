package com.kholin.atm.rest.processing.controller;

import com.kholin.atm.common.dto.CardDTO;
import com.kholin.atm.common.dto.ClientDTO;
import com.kholin.atm.rest.processing.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@Tag(name = "ClientController", description = "Работа с базой клиентов")
@RestController
@AllArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @Operation(description = "Получить клиента по идентификатору")
    @GetMapping(value = "/clients/{clientId}")
    public ClientDTO getClient(@PathVariable long clientId) {
        log.debug("REQUEST /clients/{}", clientId);
        ClientDTO response = clientService.getClient(clientId);
        log.debug("RESPONSE {}", response);
        return response;
    }

    @Operation(description = "Получить клиента по карте")
    @GetMapping(value = "/clients", params = {"number","expire","cvc2"})
    public ClientDTO getClient(@Valid CardDTO request) {
        log.debug("REQUEST /clients?{}", request);
        ClientDTO response = clientService.getClient(request);
        log.debug("RESPONSE {}", response);
        return response;
    }

    @Operation(description = "Получить список всех клиентов")
    @GetMapping(value = "/clients/all")
    public Set<ClientDTO> getClients() {
        log.debug("REQUEST /clients/all");
        Set<ClientDTO> response = clientService.getClients();
        log.debug("RESPONSE {}", response);
        return response;
    }

}
