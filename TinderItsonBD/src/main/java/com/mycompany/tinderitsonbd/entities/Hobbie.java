/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tinderitsonbd.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Josel
 */
@Entity
public class Hobbie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column
    private String descripcion;

    @OneToMany(mappedBy = "hobbie", cascade = CascadeType.ALL)
    private Set<EstudianteHobbie> estudianteHobbies;

    public Hobbie() {
    }

    public Hobbie(Long id, String nombre, String descripcion, Set<EstudianteHobbie> estudianteHobbies) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estudianteHobbies = estudianteHobbies;
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

    public Set<EstudianteHobbie> getEstudianteHobbies() {
        return estudianteHobbies;
    }

    public void setEstudianteHobbies(Set<EstudianteHobbie> estudianteHobbies) {
        this.estudianteHobbies = estudianteHobbies;
    }
}
