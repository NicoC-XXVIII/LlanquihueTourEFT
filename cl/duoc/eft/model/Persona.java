package cl.duoc.eft.model;


public abstract class Persona implements Registrable {
    protected String rut;
    protected String nombre;
    protected String telefono;

    public Persona(String rut, String nombre, String telefono) {
        this.rut = validarRut(rut);
        this.nombre = (nombre ==
                null || nombre.trim().isEmpty()) ? "No especificado" : nombre.trim();
        this.telefono = (telefono ==
                null || telefono.trim().isEmpty()) ? "No especificado" : telefono.trim();
    }

    public String geRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    private String validarRut(String rut) {
        return (rut == null || rut.trim().isEmpty()) ? "No especificado" : rut;
    }

    @Override
    public String getIdentificador() {
        return rut;
    }

    @Override
    public abstract void mostrarResumen();
}


