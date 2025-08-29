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
@Table(name = "nota_pedido_bitacora")
public class NotaDePedidoBitacora {

    private Long id;
    private String numero;
    private String sucursal;
    private String nombreAccion;
    private String descripcion;
    private NotaDePedido notaPedido;
    private NotaDePedidoDetalle notaPedidoDetalle;

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
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "sucursal", nullable = false)
    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    @Column(name = "nombre_accion", nullable = false)
    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @ManyToOne
    @JoinColumn(name = "id_nota_pedido_detalle", nullable = false)
    public NotaDePedidoDetalle getNotaPedidoDetalle() {
        return notaPedidoDetalle;
    }

    public void setNotaPedidoDetalle(NotaDePedidoDetalle notaPedidoDetalle) {
        this.notaPedidoDetalle = notaPedidoDetalle;
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
