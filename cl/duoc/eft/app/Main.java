package cl.duoc.eft.app;

import cl.duoc.eft.model.*;
import cl.duoc.eft.data.*;
import java.util.*;
import java.time.*;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorDatos gestorDatos = new GestorDatos();
        GestorReservas gestorReservas = new GestorReservas();

        // Cargar datos iniciales
        List<Cliente> clientes = gestorDatos.cargarClientes("clientes.txt");
        List<Empleado> empleados = gestorDatos.cargarEmpleados("empleados.txt");
        List<Tour> tours = gestorDatos.cargarTours("tours.txt", empleados);
        List<Proveedor>  proveedores = gestorDatos.cargarProveedores("proveedores.txt");


        System.out.println("\n==Llanquihue Tour Management System ==\n");

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();        // Limpiar buffer de entrada

            switch (opcion) {
                case 1:
                    mostrarClientes(clientes);
                    break;
                case 2:
                    mostrarEmpleados(empleados);
                    break;
                case 3:
                    mostrarTours(tours);
                    break;
                case 4:
                    mostrarProveedores(proveedores);
                    break;
                case 5:
                    gestorReservas.mostrarTodasLasReservas();
                    break;
                case 6:
                    crearReserva(sc, clientes, tours, gestorReservas);
                    break;
                case 7:
                    System.out.println("Ingreso total estimado: $" +
                            gestorReservas.calcularMontoTotalReservas());
                    break;
                case 0:
                    System.out.println("¡Gracias por usar Llanquihue Tour Management System!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        sc.close();
    }

    // Menú principal del sistema:
    private static void mostrarMenu() {
        System.out.println("\n--MENÚ PRINCIPAL--");
        System.out.println("1. Mostrar clientes");
        ;
        System.out.println("2. Mostrar empleados");
        System.out.println("3. Mostrar tours");
        System.out.println("4. Mostrar proveedores");
        System.out.println("5. Mostrar todas las reservas");
        System.out.println("6 Crear reserva");
        System.out.println("7. Informe de ingresos");
        System.out.println("0. Salir");
        System.out.println("\nSeleccione una opción");
    }

    // Método para mostrar todos los clientes registrados:
    private static void mostrarClientes(List<Cliente> clientes) {
        System.out.println("\n--LISTADO DE CLIENTES--");
        for (Cliente c : clientes) {
            c.mostrarResumen();
        }
    }

    // Método para mostrar la lista de empleados:
    private static void mostrarEmpleados(List<Empleado> empleados) {
        System.out.println("\n--LISTADO DE EMPLEADOS--");
        for (Empleado e : empleados) {
            e.mostrarResumen();
        }
    }

    // Método para mostrar la lista de proveedores:
    private static void mostrarProveedores(List<Proveedor> proveedores) {
        System.out.println("\n--LISTADO DE PROVEEDORES--");
        if (proveedores.isEmpty()) {
            System.out.println("Ningún proveedor registrado");
            return;
        }
        for (Proveedor p : proveedores) {
            p.mostrarResumen();
        }
    }

    // Método para mostrar la lista de tours disponibles:
    private static void mostrarTours(List<Tour> tours) {
        System.out.println("\n--LISTADO DE TOURS--");
        for (Tour t : tours) {
            t.mostrarResumen();
        }
    }

    // Método para crear reserva desde la Terminal:
    private static void crearReserva(Scanner sc, List<Cliente> clientes,
                                     List<Tour> tours, GestorReservas gestorReservas) {
        if (clientes.isEmpty() || tours.isEmpty()) {
            System.out.println("Datos incompletos. No se puede crear la reserva.");
            return;
        }

        System.out.println("\nClientes disponibles:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i+1) + "." + clientes.get(i).getNombre());
        }

        System.out.println("Seleccionar un cliente:");
        int idCliente = sc.nextInt() - 1;
        sc.nextLine();

        System.out.println("\nTours disponibles:");
        for (int i = 0; i < tours.size(); i++) {
            System.out.println((i+1) + "." + tours.get(i).getNombre());
        }
        System.out.println("Seleccionar un tour:");
        int idTour = sc.nextInt() - 1;
        sc.nextLine();

        System.out.println("Cantidad de personas:");
        int cantidadPersonas = sc.nextInt();
        sc.nextLine();

        String codigoReserva = "R" + System.currentTimeMillis() % 10000;

        Reserva nueva = new Reserva(codigoReserva, clientes.get(idCliente),
                tours.get(idTour), LocalDate.now(), cantidadPersonas);
        System.out.println("Reserva creada exitosamente.");

        gestorReservas.agregarReserva(nueva);
    }
}

