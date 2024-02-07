package com.angleby.gs3.gestao.domain.constante;

public class Permissao {
    public static final String ADMIN = "ADMIN";
    public static final String COMUM = "COMUM";

    public static final String PERFIL_ADMIN = "hasAuthority('" + ADMIN + "')";
    public static final String PERFIL_COMUM = "hasAuthority('" + COMUM + "')";
    public static final String PERFIL_COMUM_E_ADMIN = "hasAuthority('" + ADMIN + "') or hasAuthority('" + COMUM + "')";
}
