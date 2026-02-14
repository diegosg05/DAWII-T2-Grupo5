package cibertec.edu.pe.api_security.model;

import jakarta.persistence.*;

@Table(name = "usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombres;

    private String apellidos;

    private String nomusuario;

    private String password;

    @ManyToOne
    @JoinColumn(name = "idrol")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(Integer id, String nombres, String apellidos, String nomusuario, String password, Rol rol) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nomusuario = nomusuario;
        this.password = password;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
