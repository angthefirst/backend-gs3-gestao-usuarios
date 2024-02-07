package com.angleby.gs3.gestao.exception;


import com.angleby.gs3.gestao.util.Mensagem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoginInvalidoException extends RuntimeException {

	private static final String MESSAGE_KEY = "exception.login.LoginInvalidoException";
    private Locale locale;

    public LoginInvalidoException() {
        this(Locale.getDefault());
    }

    public LoginInvalidoException(Locale locale) {
        this.locale = locale;
    }

    public String getMessage() {
        return Mensagem.obterMensagemPorLocale(MESSAGE_KEY, locale);
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
