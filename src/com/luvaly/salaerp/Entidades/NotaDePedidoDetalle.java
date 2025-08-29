/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luvaly.salaerp.Entidades;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Marcelo Leiva
 */
@Entity
@Table(name = "nota_pedido_detalle")
public class NotaDePedidoDetalle {

    private Long id;
    private Long numero;
    private String sucursal;
    private String sku;
    private Long cantidad;
    private Long preparado;
    private Long despachado;
    private NotaDePedido notaPedido;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_nota_pedido")
    @SequenceGenerator(name = "seq_nota_pedido", sequenceName = "seq_nota_pedido", allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "numero", nullable = false)
    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Column(name = "sucursal", nullable = false)
    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    @Column(name = "sku", nullable = false)
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Column(name = "cantidad", nullable = false)
    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "preparado", nullable = false)
    public Long getPreparado() {
        return preparado;
    }

    public void setPreparado(Long preparado) {
        this.preparado = preparado;
    }

    @Column(name = "despachado", nullable = false)
    public Long getDespachado() {
        return despachado;
    }

    public void setDespachado(Long despachado) {
        this.despachado = despachado;
    }

    @ManyToOne
    @JoinColumn(name = "id_nota_pedido", nullable = false)
    public NotaDePedido getNotaPedido() {
        return notaPedido;
    }

    public void setNotaPedido(NotaDePedido notaPedido) {
        this.notaPedido = notaPedido;
    }
}
