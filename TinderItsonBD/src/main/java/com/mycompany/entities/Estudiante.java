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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josel
 */
@Entity
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "edad")
    private int edad;
    @Column(name = "genero")
    private String genero;
    @Enumerated(EnumType.STRING)
    @Column(name = "carrera")
    private Carrera carrera;
    @Column(name = "foto_usuario_url", length = 500)
    private String fotoUsuarioUrl;
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Correo> correos;
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Telefono> telefonos;
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<EstudianteHobbie> estudianteHobbies;
    @OneToMany(mappedBy = "emisor", cascade = CascadeType.ALL)
    private List<Like> likesEnviados;
    @OneToMany(mappedBy = "receptor", cascade = CascadeType.ALL)
    private List<Like> likesRecibidos;
    @OneToMany(mappedBy = "estudiante1", cascade = CascadeType.ALL)
    private List<Match> matchesComoEstudiante1;
    @OneToMany(mappedBy = "estudiante2", cascade = CascadeType.ALL)
    private List<Match> matchesComoEstudiante2;
    @OneToMany(mappedBy = "emisor", cascade = CascadeType.ALL)
    private List<Mensaje> mensajesEnviados;

    public Estudiante() {
        this.correos = new ArrayList<>();
        this.telefonos = new ArrayList<>();
        this.estudianteHobbies = new ArrayList<>();
        this.likesEnviados = new ArrayList<>();
        this.likesRecibidos = new ArrayList<>();
        this.matchesComoEstudiante1 = new ArrayList<>();
        this.matchesComoEstudiante2 = new ArrayList<>();
        this.mensajesEnviados = new ArrayList<>();
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

    public String getFotoUsuarioUrl() {
        return fotoUsuarioUrl;
    }

    public void setFotoUsuarioUrl(String fotoUsuarioUrl) {
        this.fotoUsuarioUrl = fotoUsuarioUrl;
    }

    public List<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<EstudianteHobbie> getEstudianteHobbies() {
        return estudianteHobbies;
    }

    public void setEstudianteHobbies(List<EstudianteHobbie> estudianteHobbies) {
        this.estudianteHobbies = estudianteHobbies;
    }

    public List<Like> getLikesEnviados() {
        return likesEnviados;
    }

    public void setLikesEnviados(List<Like> likesEnviados) {
        this.likesEnviados = likesEnviados;
    }

    public List<Like> getLikesRecibidos() {
        return likesRecibidos;
    }

    public void setLikesRecibidos(List<Like> likesRecibidos) {
        this.likesRecibidos = likesRecibidos;
    }

    public List<Match> getMatchesComoEstudiante1() {
        return matchesComoEstudiante1;
    }

    public void setMatchesComoEstudiante1(List<Match> matchesComoEstudiante1) {
        this.matchesComoEstudiante1 = matchesComoEstudiante1;
    }

    public List<Match> getMatchesComoEstudiante2() {
        return matchesComoEstudiante2;
    }

    public void setMatchesComoEstudiante2(List<Match> matchesComoEstudiante2) {
        this.matchesComoEstudiante2 = matchesComoEstudiante2;
    }

    public List<Mensaje> getMensajesEnviados() {
        return mensajesEnviados;
    }

    public void setMensajesEnviados(List<Mensaje> mensajesEnviados) {
        this.mensajesEnviados = mensajesEnviados;
    }

    @Override
    public String toString() {
        return "com.mycompany.tinderitsonbd.entities.Estudiante[ id=" + id + " ]";
    }

}
