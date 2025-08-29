/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luvaly.salaerp.DAO;

import Utilidades.JpaUtil;
import com.luvaly.salaerp.Entidades.NotaDePedido;
import com.luvaly.salaerp.Entidades.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Marcelo Leiva
 */
public class NotaPedidoDAO {

    public void create(NotaDePedido notaDePedido) {
        //CREATE
        EntityManager em = JpaUtil.getEntityManager();
        try {

            em.getTransaction().begin();
            em.persist(notaDePedido);
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
    public NotaDePedido getById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(NotaDePedido.class, id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
    }

    //UPDATE
    public void update(NotaDePedido notaDePedido) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(notaDePedido);
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
            NotaDePedido ndp = em.find(NotaDePedido.class, id);
            if (ndp != null) {
                em.remove(ndp);
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
    public List<NotaDePedido> listar() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query query = em.createNamedQuery("Persona.listar");
            @SuppressWarnings("unchecked")
            List<NotaDePedido> notasDePedido = (List<NotaDePedido>) query.getResultList();
            return notasDePedido;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}
