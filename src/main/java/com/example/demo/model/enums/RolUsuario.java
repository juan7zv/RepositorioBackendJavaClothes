package com.example.demo.model.enums;

public enum RolUsuario {
    Cliente(3),
    Vendedor(2),
    Administrador(1);

    Integer rolId;

    private RolUsuario(Integer rolId) {

    }
}
