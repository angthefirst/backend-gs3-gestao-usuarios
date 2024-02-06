package com.angleby.gs3.gestao.domain.usuario;

public enum Perfil {
    ADMIN("admin"),
    COMUM("comum");

    private String role;

    Perfil(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
