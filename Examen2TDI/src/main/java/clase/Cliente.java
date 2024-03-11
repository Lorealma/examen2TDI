package clase;

public class Cliente {

    private String nombre;

    private String sexo;

    private Integer peso;

    private Integer edad;

    private Integer talla;

    private String tipoActividad;

    private String observaciones;

    public Cliente() {
    }

    public Cliente(String nombre, String sexo, Integer peso, Integer edad, Integer talla, String tipoActividad, String observaciones) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.peso = peso;
        this.edad = edad;
        this.talla = talla;
        this.tipoActividad = tipoActividad;
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getTalla() {
        return talla;
    }

    public void setTalla(Integer talla) {
        this.talla = talla;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


}
