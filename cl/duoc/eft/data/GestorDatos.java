package cl.duoc.eft.data;

import cl.duoc.eft.model.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorDatos {

    // Ruta base para los archivos
    private static final String RESOURCES_PATH = "src/resources/";

    public List<Cliente> cargarClientes(String nombreArchivo) {
        List<Cliente> clientes = new ArrayList<>();
        String ruta = RESOURCES_PATH + nombreArchivo;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");
                if (datos.length >= 5) {
                    Cliente c = new Cliente(
                            datos[0].trim(),  // rut
                            datos[1].trim(),  // nombre
                            datos[2].trim(),  // teléfono
                            datos[3].trim(),  // email
                            datos[4].trim()   // preferencias
                    );
                    clientes.add(c);
                }
            }
            System.out.println("Total: " + clientes.size() + " clientes cargados desde: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("No se pudo leer el archivo: " + ruta);
        }
        return clientes;
    }

    public List<Empleado> cargarEmpleados(String nombreArchivo) {
        List<Empleado> empleados = new ArrayList<>();
        String ruta = RESOURCES_PATH + nombreArchivo;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");
                if (datos.length >= 6) {
                    Empleado e = new Empleado(
                            datos[0].trim(),   // Nombre
                            datos[1].trim(),   // Rut
                            datos[2].trim(),   // Teléfono
                            datos[3].trim(),   // Cargo
                            datos[4].trim(),   // Idioma
                            datos[5].trim()    // Tipo de contrato
                    );
                    empleados.add(e);
                }
            }
            System.out.println("Total: " + empleados.size() + " empleados cargados desde: " + nombreArchivo);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el archivo de empleados: " + ruta);
        }
        return empleados;
    }

    public List<Proveedor> cargarProveedores(String nombreArchivo) {
        List<Proveedor> proveedores = new ArrayList<>();
        String ruta = RESOURCES_PATH + nombreArchivo;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");
                if (datos.length >= 5) {
                    try {
                        Proveedor p = new Proveedor(
                                datos[0].trim(),                    // rut
                                datos[1].trim(),                    // nombre
                                datos[2].trim(),                    // teléfono
                                datos[3].trim(),                    // tipoServicio
                                Double.parseDouble(datos[4].trim()) // calificación
                        );
                        proveedores.add(p);
                    } catch (NumberFormatException e) {
                        System.err.println("Error en calificación de proveedor: " + linea);
                    }
                }
            }
            System.out.println("Total: " + proveedores.size() + " proveedores cargados desde: " +  nombreArchivo);
            return proveedores;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tour> cargarTours(String nombreArchivo, List<Empleado> empDisponibles) {
        List<Tour> tours = new ArrayList<>();
        String ruta = RESOURCES_PATH + nombreArchivo;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");
                if (datos.length >= 5) {
                    Empleado empleados = empDisponibles.isEmpty() ? null : empDisponibles.get(0 % empDisponibles.size());

                    Tour t = new Tour(
                            datos[0].trim(),                    // código
                            datos[1].trim(),                    // nombre
                            datos[2].trim(),                    // destino
                            Double.parseDouble(datos[3].trim()),// precio
                            Integer.parseInt(datos[4].trim()),  // duración en horas
                            empleados
                    );

                    if (datos.length > 5) {
                        String[] actividades = datos[5].split(",");
                        for (String act : actividades) {
                            t.agregarActividad(act.trim());
                        }
                    }
                    tours.add(t);
                }
            }
            System.out.println("Total: " + tours.size() + " tours cargados desde: " + nombreArchivo);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer la lista de tours: " + ruta);
        }
        return tours;
    }

    public List<Reserva> crearReservasDePrueba(List<Cliente> clientes, List<Tour> tours) {
        List<Reserva> reservas = new ArrayList<>();

        if (!clientes.isEmpty() && !tours.isEmpty()) {
            reservas.add(new Reserva("R001", clientes.get(0), tours.get(0), LocalDate.now().plusDays(5), 2));
            if (clientes.size() > 1 && tours.size() > 1) {
                reservas.add(new Reserva("R002", clientes.get(1), tours.get(1), LocalDate.now().plusDays(10), 4));
            }
        }
        return reservas;
    }
}