package cl.duoc.eft.model;

import java.util.ArrayList;
import java.util.List;

public class Tour implements Registrable {
    private String codigo;
    private String nombre;
    private String destino;
    private double precio;
    private int duracionHoras;
    private Empleado empAsignado;
    private List<String> actividades;

    public Tour(String codigo, String nombre, String destino, double precio,
                int duracionHoras, Empleado empAsignado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.destino = destino;
        this.precio = precio;
        this.duracionHoras = duracionHoras;
        this.empAsignado = empAsignado;
        this.actividades = new ArrayList<>();
    }

    public void agregarActividad(String actividad) {
        if (actividad != null) {
            actividades.add(actividad);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public Empleado getEmpAsignado() {
        return empAsignado;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("\n--TOUR--");
        System.out.println("Código: #" + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Destino: " + destino);
        System.out.println("Precio: $" + precio);
        System.out.println("Duración: " + duracionHoras + " horas");
        if (empAsignado != null) {
            System.out.println("Empleado asignado: " + empAsignado.getNombre());
        }
        System.out.println("Actividades: " + actividades.size());
        System.out.println("------------");

    }

    @Override
    public String getIdentificador() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Tour [codigo= " + codigo + " | nombre= " + nombre + "]";
    }


}
