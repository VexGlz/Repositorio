/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.videojugeosjpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Erick
 */
public class VideojuegosJPA {

    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("VideojuegosPU");
        EntityManager em=emf.createEntityManager();
    }
}
