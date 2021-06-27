package com.kholin.atm.web.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MultipleErrorsException extends RuntimeException {
    @Getter
    private final List<String> messageList;

    public MultipleErrorsException(List<String> messageList) {
        super(messageList.stream().reduce((s1, s2) -> s1 + ", " + s2).orElse(""));
        this.messageList = messageList;
    }

    public MultipleErrorsException(String message) {
        super(message);
        messageList = new ArrayList<>();
        messageList.add(message);
    }
}
