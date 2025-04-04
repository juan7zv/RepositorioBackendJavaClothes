package com.example.demo.model;

public class Notificacion {

    private String idNotificacion;
    private String mensaje;
    private String fecha;
    private Usuario cliente;
    private Pedido pedido; //pedido asociado a la notificación

    public Notificacion() {
        
    }

    public Notificacion(String idNotificacion, String mensaje, String fecha, Usuario cliente, Pedido pedido) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.cliente = cliente;
        this.pedido = pedido;
    }

    public String getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(String idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Notificacion [idNotificacion=" + idNotificacion + ", mensaje=" + mensaje + ", fecha=" + fecha
                + ", cliente=" + cliente + ", pedido=" + pedido + "]";
    }

}
