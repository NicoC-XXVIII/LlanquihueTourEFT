package cl.duoc.eft.model;

import java.time.LocalDate;

public class Reserva implements Registrable{
    private String codigoReserva;
    private Cliente cliente;
    private Tour tour;
    private LocalDate fechaReserva;
    private int cantidadPersonas;
    private double montoTotal;

    public Reserva(String codigoReserva, Cliente cliente, Tour tour,
                   LocalDate fechaReserva, int cantidadPersonas) {
        this.codigoReserva = codigoReserva;
        this.cliente = cliente;
        this.tour = tour;
        this.fechaReserva = fechaReserva;
        this.cantidadPersonas = cantidadPersonas;
        this.montoTotal = calcularMontoTotal();
    }

    private double calcularMontoTotal() {
        return tour.getPrecio() * cantidadPersonas;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Tour getTour() {
        return tour;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("\n--RESERVA--");
        System.out.println("Código de reserva: " + codigoReserva);
        System.out.println("Cliente:" + cliente.getNombre());
        System.out.println("Tour: " + tour.getNombre());
        System.out.println("Fecha de reserva: " + fechaReserva);
        System.out.println("Cantidad de personas: " + cantidadPersonas);
        System.out.println("Monto total: $" + montoTotal);
        System.out.println("------------");
    }

    @Override
    public String getIdentificador() {
        return codigoReserva;
    }

    @Override
    public String toString() {
        return "Reserva [codigoReserva= " + codigoReserva +
                " | " + cliente.getNombre() +"]";
    }
}
