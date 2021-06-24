package com.kholin.atm.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private List<String> errors;

    public ErrorMessage(String error) {
        errors = new ArrayList<>();
        errors.add(error);
    }
}
