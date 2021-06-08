package com.kholin.atm.server.processing.mapper;

import com.kholin.atm.common.dto.AccountDTO;
import com.kholin.atm.server.processing.entity.Account;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Currency;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AccountMapper {
    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        PropertyMap<Account, AccountDTO> balanceMap = new PropertyMap<Account, AccountDTO>() {
            protected void configure() {
                map().setNumber(source.getNum());
                map().getBalance().setValue(source.getBalance());
                Converter<String, Currency> toCurrency = ctx -> Currency.getInstance(ctx.getSource());
                using(toCurrency).map().getBalance().setCurrency(source("currency"));
            }
        };
        mapper.createTypeMap(Account.class, AccountDTO.class)
                .addMappings(balanceMap);
    }

    public Account toEntity(AccountDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Account.class);
    }

    public AccountDTO toDTO(Account entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AccountDTO.class);
    }
}
