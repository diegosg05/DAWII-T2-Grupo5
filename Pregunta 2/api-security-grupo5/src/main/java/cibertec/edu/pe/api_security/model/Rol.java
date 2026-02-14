package cibertec.edu.pe.api_security.model;

import jakarta.persistence.*;

@Table(name = "rol")
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol")
    private Integer id;

    private String nomrol;

    public Rol() {
    }

    public Rol(Integer id, String nomrol) {
        this.id = id;
        this.nomrol = nomrol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomrol() {
        return nomrol;
    }

    public void setNomrol(String nomrol) {
        this.nomrol = nomrol;
    }
}
