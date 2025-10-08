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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author Erick
 */
@Entity
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic
    private String psedunimo;
    private String sexo;
    private LocalDate fecha_nac;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private Direccion direccion;
    @ManyToMany(mappedBy = "jugadores")
    private Set<Videojuego> videojuegos;

    public Jugador() {
    }

    public Jugador(Long id, String psedunimo, String sexo, LocalDate fecha_nac, Direccion direccion, Set<Videojuego> videojuegos) {
        this.id = id;
        this.psedunimo = psedunimo;
        this.sexo = sexo;
        this.fecha_nac = fecha_nac;
        this.direccion = direccion;
        this.videojuegos = videojuegos;
    }

    public Set<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(Set<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getPsedunimo() {
        return psedunimo;
    }

    public String getSexo() {
        return sexo;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPsedunimo(String psedunimo) {
        this.psedunimo = psedunimo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
