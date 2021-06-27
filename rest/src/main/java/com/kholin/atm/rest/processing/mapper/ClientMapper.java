package com.kholin.atm.rest.processing.mapper;

import com.kholin.atm.common.dto.ClientDTO;
import com.kholin.atm.rest.processing.entity.Client;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientMapper {
    private final ModelMapper mapper;

    public ClientDTO toDTO(Client entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ClientDTO.class);
    }
}
