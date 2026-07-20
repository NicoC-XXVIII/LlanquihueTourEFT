package cl.duoc.eft.model;

public class Empleado extends Persona {
    private String cargo;
    private String idioma;
    private String tipoContrato;

    public Empleado(String rut, String nombre, String telefono,
                    String cargo, String idioma, String tipoContrato) {
        super(rut, nombre, telefono);
        this.cargo = cargo != null ? cargo : "Practicante";
        this.idioma = idioma != null ? idioma : "Español";
        this.tipoContrato = tipoContrato != null ? tipoContrato : "A plazo fijo";
    }

    public String getCargo() {
        return cargo;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("\n--EMPLEADO--");
        System.out.println("Nombre: " + nombre);
        System.out.println("Rut: " + rut);
        System.out.println("Telefono: " + telefono);
        System.out.println("Cargo: " + cargo);
        System.out.println("Idioma: " + idioma);
        System.out.println("Tipo de contrato: " + tipoContrato);
        System.out.println("------------");
    }


}
