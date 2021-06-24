package com.kholin.atm.server.processing.mapper;

import com.kholin.atm.common.dto.CardDTO;
import com.kholin.atm.server.processing.entity.Card;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CardMapper {

    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        Converter<Date, LocalDate> toLocalDate = ctx -> ctx.getSource().toLocalDate();
        mapper.createTypeMap(Card.class, CardDTO.class)
                .addMapping(Card::getNum, CardDTO::setNumber)
                .addMappings(m -> m.using(toLocalDate).map(Card::getExpire, CardDTO::setExpire));
    }

    public CardDTO toDTO(Card entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CardDTO.class);
    }

}
