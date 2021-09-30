package com.kholin.atm.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kholin.atm.common.ErrorMessage;
import com.kholin.atm.common.dto.CardDTO;
import com.kholin.atm.common.dto.ClientDTO;
import com.kholin.atm.web.exception.MultipleErrorsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ClientService {

    @Value("${atm.rest.getClientByCard}")
    private String getClientURI;

    public ClientDTO getClient(CardDTO card) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ClientDTO> responseEntity;
        log.debug("GET " + getClientURI, card.getNumber(), card.getExpire(), card.getCvc2());
        try {
            responseEntity = restTemplate
                    .getForEntity(getClientURI, ClientDTO.class, card.getNumber(), card.getExpire(), card.getCvc2());
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            log.debug("{}: {}", e.getStatusCode(), e.getResponseBodyAsString());
            try {
                throw new MultipleErrorsException(
                        new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorMessage.class).getErrors());
            }
            catch (JsonProcessingException e2) {
                throw new MultipleErrorsException("Ответ сервера нераспознан");
            }
        }

        log.debug("{}: {}", responseEntity.getStatusCode(), responseEntity.getBody());
        return responseEntity.getBody();
    }

}
