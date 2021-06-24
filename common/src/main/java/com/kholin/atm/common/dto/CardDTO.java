package com.kholin.atm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    @Pattern(regexp = "^\\d{16}$", message = "Номер карты должен состоять из 16-ти цифр")
    private String number;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "Некорректная дата окончания")
    private LocalDate expire;
    @Pattern(regexp = "^\\d{3}$", message = "CVC2 должен состоять из 3-ех цифр")
    private String cvc2;
}
