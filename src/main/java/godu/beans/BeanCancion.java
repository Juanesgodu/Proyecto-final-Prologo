package godu.beans;

import java.io.Serializable;

public class BeanCancion implements Serializable{
    private String nCancion; 
    private String artista;  
    private String URLSpotify; 

    public BeanCancion() {
    }

    public String getnCancion() {
        return nCancion;
    }

    public void setnCancion(String nCancion) {
        this.nCancion = nCancion;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getURLSpotify() {
        return URLSpotify;
    }

    public void setURLSpotify(String URLSpotify) {
        this.URLSpotify = URLSpotify;
    }

    @Override
    public String toString() {
        return "CancionBean{" +
                "nCancion='" + nCancion + '\'' +
                ", artista='" + artista + '\'' +
                ", URLSpotify='" + URLSpotify + '\'' +
                '}';
    }
}
