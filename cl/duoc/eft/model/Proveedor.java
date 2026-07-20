package cl.duoc.eft.model;

public class Proveedor extends Persona {
    private String tipoServicio; // "transporte", "alojamiento", etc.
    private double resenias;

    public Proveedor(String rut, String nombre, String telefono,
                     String tipoServicio, double resenias) {
        super(rut, nombre, telefono);
        this.tipoServicio = tipoServicio;
        this.resenias = Math.max(1.0, Math.min(5.0, resenias));
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public double getResenias() {
        return resenias;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("\n--PROVEEDOR--");
        System.out.println("Nombre: " + nombre);
        System.out.println("Rut: " + rut);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Tipo de servicio: " + tipoServicio);
        System.out.println("Reseñas: " + resenias + " estrellas");
        System.out.println("------------");
    }
}
