package com.kholin.atm.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kholin.atm.common.ErrorMessage;
import com.kholin.atm.common.dto.CardDTO;
import com.kholin.atm.common.dto.ClientDTO;
import com.kholin.atm.web.exception.MultipleErrorsException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Log
public class ClientService {

    @Value("${atm.host.getClientByCard}")
    private String getClientURI;

    public ClientDTO getClient(CardDTO card) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ClientDTO> responseEntity;
        try {
            responseEntity = restTemplate
                    .getForEntity(getClientURI, ClientDTO.class, card.getNumber(), card.getExpire(), card.getCvc2());
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            log.info("response: " + e.getStatusCode() + " - " + e.getMessage());
            try {
                throw new MultipleErrorsException(
                        new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorMessage.class).getErrors());
            }
            catch (JsonProcessingException e2) {
                throw new MultipleErrorsException("Ответ сервера нераспознан");
            }
        }

        log.info("response: " + responseEntity.getBody());
        return responseEntity.getBody();
    }

}
