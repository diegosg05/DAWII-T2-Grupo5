package cibertec.edu.pe.api_security.dto;

public class UsuarioSecurity {

    private Integer idUsuario;
    private String nomUsuario;
    private String token;
    private String mensajeError;

    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNomUsuario() {
        return nomUsuario;
    }
    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getMensajeError() {
        return mensajeError;
    }
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }



}
