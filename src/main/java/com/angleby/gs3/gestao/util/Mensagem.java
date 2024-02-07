package com.angleby.gs3.gestao.util;

import java.util.Locale;
import java.util.ResourceBundle;
public class Mensagem {

    public static String obterMensagemPorLocale(String messageKey, Locale locale) {
        return ResourceBundle.getBundle("messages", locale)
          .getString(messageKey);
    }

}
