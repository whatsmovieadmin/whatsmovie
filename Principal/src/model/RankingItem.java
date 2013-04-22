package model;

public class RankingItem {
	
	private String posicion;
	private String nombre;
    private int puntuacion;
    
    public RankingItem(String posicion, String nombre, int puntuacion) {
    	this.nombre = nombre;
    	this.puntuacion = puntuacion;
    	this.posicion = posicion;
    }
    public String getPosicion() {
        return posicion;
    }
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}