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
@Table(name = "persona") //Nombre de la tabla en la base de datos

//QUERY
@NamedQuery(name = "Persona.listar", query = "SELECT p FROM Persona p")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Timestamp fechaNacimento;

    @Column(name = "rut", nullable = false)
    private String rut;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Hijo> hijos = new ArrayList<>();

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fechaNacimento
     */
    public Timestamp getFechaNacimento() {
        return fechaNacimento;
    }

    /**
     * @param fechaNacimento the fechaNacimento to set
     */
    public void setFechaNacimento(Timestamp fechaNacimento) {
        this.fechaNacimento = fechaNacimento;
    }

    /**
     * @return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    public List<Hijo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Hijo> hijos) {
        this.hijos = hijos;
    }

}
