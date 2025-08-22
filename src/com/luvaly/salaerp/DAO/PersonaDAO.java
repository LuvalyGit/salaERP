/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luvaly.salaerp.DAO;

import Utilidades.JpaUtil;
import com.luvaly.salaerp.Entidades.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Marcelo Leiva
 */
public class PersonaDAO {

    public void create(Persona persona) {
        //CREATE
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }
    }

    //READ por ID
    public Persona getById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Persona.class, id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
    }

    //UPDATE
    public void update(Persona persona) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(persona);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }
    }

    //DELETE by ID
    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Persona p = em.find(Persona.class, id);
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }
    }

    //LISTAR todas las personas
    public List<Persona> listar() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Persona> query = em.createNamedQuery("Persona.listar", Persona.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

}
