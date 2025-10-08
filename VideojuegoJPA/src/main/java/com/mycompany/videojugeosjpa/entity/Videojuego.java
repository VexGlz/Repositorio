/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojugeosjpa.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Erick
 */
@Entity
public class Videojuego implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic
    private String nombre;
    private int puntaje;
    private String desarrolladora;
    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL)
    private Set<Logro> logros;
    @ManyToMany
    @JoinTable(
            name = "Videojuego_jugador",
            joinColumns = @JoinColumn(name = "videojuego_id"),
            inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private List<Jugador> jugadores;

    public Videojuego() {
    }

    public Videojuego(Long id, String nombre, int puntaje, String desarrolladora, Set<Logro> logros, List<Jugador> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.desarrolladora = desarrolladora;
        this.logros = logros;
        this.jugadores = jugadores;
    }

   

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }


    

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public Set<Logro> getLogros() {
        return logros;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public void setLogros(Set<Logro> logros) {
        this.logros = logros;
    }

}
