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
@Entity //Marca clase como entidad en JPA
@Table(name = "nota_pedido") //Nombre de la tabla en la base de datos

public class NotaDePedido {

    private Long id;
    private Long numero;
    private String sucursal;
    private Boolean estado;
    private String usuario;
    private String destino;
    private Timestamp fecha;

    

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

    @Column(name = "estado", nullable = false)
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Column(name = "usuario", nullable = false)
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Column(name = "destino", nullable = false)
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento_relacionado", nullable = false)
    public TipoDocumentoRelacionado getTipoDocumentoRelacionado() {
        return tipoDocumentoRelacionado;
    }

    public void setTipoDocumentoRelacionado(TipoDocumentoRelacionado tipoDocumentoRelacionado) {
        this.tipoDocumentoRelacionado = tipoDocumentoRelacionado;
    }
    
    @Column(name = "fecha", nullable = false)
    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    private TipoDocumentoRelacionado tipoDocumentoRelacionado;

}
