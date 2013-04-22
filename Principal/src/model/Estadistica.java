package model;

public class Estadistica {
	private String grupo;
    private int puntuacion;
    private String racha;
    private String posicion;
    
    public Estadistica(String posicion, String grupo, int puntuacion, String racha) {
    	this.grupo = grupo;
    	this.puntuacion = puntuacion;
    	this.racha = racha;
    	this.posicion = posicion;
    }
    
    public String getPosicion() {
        return posicion;
    }
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    public String getGrupo() {
        return grupo;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public int getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    public String getRacha() {
        return racha;
    }
    public void setRacha(String racha) {
        this.racha = racha;
    }
}
