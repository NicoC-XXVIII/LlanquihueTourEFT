package cl.duoc.eft.data;

import cl.duoc.eft.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class GestorReservas {
    private List<Reserva> reservas;

    public GestorReservas() {
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        if (reserva != null) {
            reservas.add(reserva);
            System.out.println("Reserva " + reserva.getIdentificador() + " agregada exitosamente.");
        }
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    // Buscar reserva por código:
    public Reserva buscarReserva(String codigo) {
        return reservas.stream()
                .filter(r -> r.getCodigoReserva().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    // Reservas de un cliente específico:
    public List<Reserva> getReservasPorCliente(Cliente cliente) {
        return reservas.stream()
                .filter(r -> r.getCliente().getIdentificador()
                .equals(cliente.getIdentificador()))
                .collect(Collectors.toList());
    }

    // Reservas para un tour específico:
    public List<Reserva> getReservasPorTour(Tour tour) {
        return reservas.stream()
                .filter(r -> r.getTour().equals(tour.getCodigo()))
                .collect(Collectors.toList());
    }

    // Mostrar todas las reservas:
    public void mostrarTodasLasReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas disponibles.");
            return;
        }

        System.out.println("\n==LISTADO DE RESERVAS==\n:");
        for (Reserva r : reservas) {
            r.mostrarResumen();
        }
        System.out.println("Total de reservas: " + reservas.size());
    }

    // Estadísticas:
    public double calcularMontoTotalReservas() {
        return reservas.stream()
                .mapToDouble(Reserva::getMontoTotal)
                .sum();
    }

    public int totalReservas() {
        return reservas.size();
    }
}




