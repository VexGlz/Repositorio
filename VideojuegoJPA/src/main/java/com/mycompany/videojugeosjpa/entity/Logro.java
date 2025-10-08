/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojugeosjpa.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/**
 *
 * @author Erick
 */
@Entity
public class Logro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic
    private String nombre;
    private int puntos;
    @ManyToOne
    @JoinColumn(name="videojuego_id",referencedColumnName = "id")
    private Videojuego videojuego;

    public Logro() {
    }

    public Logro(Long id, String nombre, int puntos, Videojuego videojuego) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = puntos;
        this.videojuego = videojuego;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }
    
}