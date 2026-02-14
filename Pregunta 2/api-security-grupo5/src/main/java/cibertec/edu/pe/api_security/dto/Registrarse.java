package cibertec.edu.pe.api_security.dto;

public class Registrarse {
    private String nomusuario;
    private String password;
    private Integer rol;

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

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
}
