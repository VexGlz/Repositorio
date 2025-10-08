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
import java.io.Serializable;

/**
 *
 * @author Erick
 */
@Entity
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    private Long id;
    private String calle;
    private String numero;
    private String colonia;

    public Direccion() {
    }

    public Direccion(Long id, String calle, String numero, String colonia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

}
