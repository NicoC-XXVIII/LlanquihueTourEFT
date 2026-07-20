package cl.duoc.eft.model;

public class Cliente extends Persona {

    private String email;
    private String preferencias; // Ej: "gastronómico", "paseo en bote", etc

    public Cliente(String rut, String nombre, String telefono,
                   String email, String preferencias) {
        super(rut, nombre, telefono);
        this.email = email != null ? email : "No especificado";
        this.preferencias = preferencias != null ? preferencias : "General";
    }

    public String getEmail() {
        return email;
    }

    public String getPreferencias() {
        return preferencias;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("\n--CLIENTE--");
        System.out.println("Rut: " + rut);
        System.out.println("Nombre: " + nombre);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
        System.out.println("Preferencias: " + preferencias + "");
        System.out.println("------------");
    }

    @Override
    public String toString() {
        return "Cliente [nombre= " + nombre + " | email= " + email + "]";
    }
}
