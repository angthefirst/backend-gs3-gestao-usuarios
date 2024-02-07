package com.angleby.gs3.gestao.exception;


import com.angleby.gs3.gestao.util.Mensagem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException {

	private static final String MESSAGE_KEY = "exception.usuario.NotFoundException";
    private Locale locale;

    public UsuarioNotFoundException() {
        this(Locale.getDefault());
    }

    public UsuarioNotFoundException(Locale locale) {
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
