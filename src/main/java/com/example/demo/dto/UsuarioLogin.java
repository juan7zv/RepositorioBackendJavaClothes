package com.example.demo.dto;

public class UsuarioLogin {
    private String idUsuario;
    private String clave;

    public UsuarioLogin(String idUsuario, String clave) {
        this.idUsuario = idUsuario;
        this.clave = clave;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
