package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fact_id;

    @OneToOne
    @JoinColumn(name = "pedi_id", nullable = false)
    private Pedido pedido;

    @Column(nullable = false)
    private LocalDate fecha_fact;

    @Column(nullable = false)
    private Double total_pagar;

    // Constructor por defecto (necesario para JPA y Jackson)
    public Factura() {
    }

    // getters and setters


    public Integer getFact_id() {
        return fact_id;
    }

    public void setFact_id(Integer fact_id) {
        this.fact_id = fact_id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LocalDate getFecha_fact() {
        return fecha_fact;
    }

    public void setFecha_fact(LocalDate fecha_fact) {
        this.fecha_fact = fecha_fact;
    }

    public Double getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(Double total_pagar) {
        this.total_pagar = total_pagar;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "fact_id=" + fact_id +
                ", pedido=" + pedido +
                ", fecha_fact=" + fecha_fact +
                ", total_pagar=" + total_pagar +
                '}';
    }
}
