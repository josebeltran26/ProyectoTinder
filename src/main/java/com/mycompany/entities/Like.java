/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

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
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "liker_id")
    private Estudiante likeD;

    @ManyToOne
    @JoinColumn(name = "liked_id")
    private Estudiante likeR;

    public Like() {
    }

    public Like(Long id, String tipo, Estudiante likeD, Estudiante likeR) {
        this.id = id;
        this.tipo = tipo;
        this.likeD = likeD;
        this.likeR= likeR;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Estudiante getLikeD() {
        return likeD;
    }

    public void setLikeR(Estudiante likeD) {
        this.likeD = likeD;
    }

    public Estudiante getLikeR() {
        return likeR;
    }

    public void setLikeD(Estudiante likeR) {
        this.likeR = likeR;
    }
}
