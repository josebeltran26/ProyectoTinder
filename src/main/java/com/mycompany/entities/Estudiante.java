/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    private int edad;
    private String genero;
    @Enumerated(EnumType.STRING)
    @Column(name = "carrera")
    private Carrera carrera;
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private Set<Correo> correos;
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private Set<Telefono> telefonos;
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private Set<EstudianteHobbie> estudianteHobbies;
    @OneToMany(mappedBy = "likeD", cascade = CascadeType.ALL)
    private Set<Like> likesDados;
    @OneToMany(mappedBy = "likeR", cascade = CascadeType.ALL)
    private Set<Like> likesRecibidos;
    @OneToMany(mappedBy = "mensaje", cascade = CascadeType.ALL)
    private Set<Mensaje> mensajesEnviados;

    public Estudiante() {
    }

    public Estudiante(Long id, String nombre, String descripcion, int edad, String genero, Carrera carrera, Set<Correo> correos, Set<Telefono> telefonos, Set<EstudianteHobbie> estudianteHobbies, Set<Like> likesDados, Set<Like> likesRecibidos, Set<Mensaje> mensajesEnviados) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.edad = edad;
        this.genero = genero;
        this.carrera = carrera;
        this.correos = correos;
        this.telefonos = telefonos;
        this.estudianteHobbies = estudianteHobbies;
        this.likesDados = likesDados;
        this.likesRecibidos = likesRecibidos;
        this.mensajesEnviados = mensajesEnviados;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Set<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(Set<Correo> correos) {
        this.correos = correos;
    }

    public Set<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Set<EstudianteHobbie> getEstudianteHobbies() {
        return estudianteHobbies;
    }

    public void setEstudianteHobbies(Set<EstudianteHobbie> estudianteHobbies) {
        this.estudianteHobbies = estudianteHobbies;
    }

    public Set<Like> getLikesDados() {
        return likesDados;
    }

    public void setLikesDados(Set<Like> likesDados) {
        this.likesDados = likesDados;
    }

    public Set<Like> getLikesRecibidos() {
        return likesRecibidos;
    }

    public void setLikesRecibidos(Set<Like> likesRecibidos) {
        this.likesRecibidos = likesRecibidos;
    }

    public Set<Mensaje> getMensajesEnviados() {
        return mensajesEnviados;
    }

    public void setMensajesEnviados(Set<Mensaje> mensajesEnviados) {
        this.mensajesEnviados = mensajesEnviados;
    }
}
