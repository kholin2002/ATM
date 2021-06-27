package com.kholin.atm.rest.processing.service;

import com.kholin.atm.common.dto.CardDTO;
import com.kholin.atm.common.dto.ClientDTO;
import com.kholin.atm.rest.processing.entity.Client;
import com.kholin.atm.rest.processing.exception.CardNotFoundException;
import com.kholin.atm.rest.processing.exception.ClientNotFoundException;
import com.kholin.atm.rest.processing.mapper.ClientMapper;
import com.kholin.atm.rest.processing.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDTO getClient(long id) {
        Client clientEntity = clientRepository
                .findById(id)
                .orElseThrow(ClientNotFoundException::new);
        return clientMapper.toDTO(clientEntity);
    }

    public ClientDTO getClient(CardDTO cardDTO) {
        Client clientEntity = clientRepository
                .findByCard(cardDTO.getNumber(), Date.valueOf(cardDTO.getExpire()), cardDTO.getCvc2())
                .orElseThrow(CardNotFoundException::new);
        return clientMapper.toDTO(clientEntity);
    }

    public Set<ClientDTO> getClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(clientMapper::toDTO)
                .collect(Collectors.toSet());
    }

}
