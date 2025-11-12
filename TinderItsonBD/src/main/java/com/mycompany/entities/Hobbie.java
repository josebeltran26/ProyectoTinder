/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Josel
 */
@Entity
public class Hobbie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "hobbie", cascade = CascadeType.ALL)
    private List<EstudianteHobbie> estudianteHobbies;

    public Hobbie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EstudianteHobbie> getEstudianteHobbies() {
        return estudianteHobbies;
    }

    public void setEstudianteHobbies(List<EstudianteHobbie> estudianteHobbies) {
        this.estudianteHobbies = estudianteHobbies;
    }

    
    @Override
    public String toString() {
        return "com.mycompany.tinderitsonbd.entities.Hobbie[ id=" + id + " ]";
    
}}
