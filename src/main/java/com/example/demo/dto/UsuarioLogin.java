package com.example.demo.dto;

public class UsuarioLogin {
    private Integer idUsuario;
    private String clave;

    public UsuarioLogin(Integer idUsuario, String clave) {
        this.idUsuario = idUsuario;
        this.clave = clave;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
