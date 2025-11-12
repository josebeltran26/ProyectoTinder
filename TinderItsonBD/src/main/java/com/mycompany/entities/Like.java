/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Josel
 */
@Entity
@Table(name = "Estudiante_like")
public class Like implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora", nullable = false)
    private Calendar fechaHora;
    @ManyToOne
    @JoinColumn(name = "emisor_id", nullable = false)
    private Estudiante emisor;

    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private Estudiante receptor;

    public Like() {
        this.fechaHora = Calendar.getInstance(); 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Calendar fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Estudiante getEmisor() {
        return emisor;
    }

    public void setEmisor(Estudiante emisor) {
        this.emisor = emisor;
    }

    public Estudiante getReceptor() {
        return receptor;
    }

    public void setReceptor(Estudiante receptor) {
        this.receptor = receptor;
    }

    @Override
    public String toString() {
        return "com.mycompany.tinderitsonbd.entities.Like[ id=" + id + " ]";
    }

}
