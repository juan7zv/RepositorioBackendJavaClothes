package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioLogin {
    @NotBlank  private Integer idUsuario;
    @NotBlank  private String clave;
    @NotBlank  private Integer rolUsuario;

    public UsuarioLogin(Integer idUsuario, String clave, Integer rolUsuario) {
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.rolUsuario = rolUsuario;
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

    public Integer getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Integer rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

}
