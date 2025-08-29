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
@Table(name = "tipo_documento_relacionado")

public class TipoDocumentoRelacionado {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long numero;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "nombre", nullable = false, length = 3)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "descripcion", nullable = false, length = 3)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "numero", nullable = false)
    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
}
