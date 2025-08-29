/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luvaly.salaerp.Entidades;

import java.sql.Timestamp;
import javax.persistence.*;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Marcelo Leiva
 */
@Entity //Marca clase como entidad en JPA
@Table(name = "hijo") //Nombre de la tabla en la base de datos

//QUERY
@NamedQuery(name = "listar", query = "SELECT h FROM Hijo h")
public class Hijo {

    private Long id;
    private String nombre;
    private String rut;
    private Timestamp fecha_nacimiento;
    private Persona persona;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "rut", nullable = false)
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    @Column(name = "fecha_nacimiento", nullable = false)
    public Timestamp getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Timestamp fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
