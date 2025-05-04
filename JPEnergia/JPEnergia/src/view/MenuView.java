package view;

import controller.*;
import model.*;

import java.util.Scanner;

/**
 * Clase encargada de mostrar el menú de interacción con el usuario y
 * manejar las operaciones básicas del sistema de gestión de consumos eléctricos.
 * Proporciona opciones para crear y editar registradores, cargar consumos,
 * y calcular estadísticas relacionadas al consumo energético.
 * 
 */
public class MenuView {
    private final Scanner objSc = new Scanner(System.in);
    private final ClienteController objClienteController = new ClienteController();
    private final RegistradorController objRegistradorController = new RegistradorController();
    private final ConsumoController objConsumoController = new ConsumoController();

    /**
     * Muestra el menú principal del sistema y gestiona la interacción con el usuario.
     * Las operaciones disponibles incluyen creación de registradores, edición,
     * carga de consumos y cálculos de estadísticas de consumo.
     */
    public void mMostrarMenu() {
        int objOp;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Crear registrador");
            System.out.println("2. Editar registrador");
            System.out.println("3. Cargar consumos todos los clientes");
            System.out.println("4. Cargar consumos un cliente");
            System.out.println("5. Cambiar consumo de una hora");
            System.out.println("6. Consumo mínimo de un cliente");
            System.out.println("7. Consumo máximo de un cliente");
            System.out.println("8. Consumo por franjas");
            System.out.println("9. Consumo por días");
            System.out.println("10. Calcular valor factura de un cliente");
            System.out.println("0. Salir");
            System.out.print("Por favor digite el número de la opción deseada: ");
            objOp = objSc.nextInt();
            objSc.nextLine();

            switch (objOp) {
                case 1 -> {
                    mCrearRegistrador();
                    System.out.println("Registrador creado correctamente.");
                }
                case 2 -> {
                    mEditarRegistrador();
                    System.out.println("Registrador editado correctamente.");
                }
                case 3 -> {
                    for (Cliente cliente : objClienteController.mGetClientes()) {
                        if (cliente != null) {
                            objConsumoController.mCargarConsumoMesCliente(cliente);
                        }
                    }
                    System.out.println("Se han cargado consumos a todos los clientes.");
                }
                case 4 -> {
                    System.out.print("ID del cliente: ");
                    String objId = objSc.nextLine();
                    if (objClienteController.mExisteCliente(objId)) {
                        objConsumoController.mCargarConsumoMesCliente(
                                objClienteController.mGetCliente(objId));
                        System.out.println("Se han cargado consumos al cliente con ID: " + objId);
                    } else {
                        System.out.println("El cliente con ID " + objId + " no existe.");
                    }
                }
                case 5 -> {
                    mCambiarConsumoHora();
                    System.out.println("Consumo actualizado correctamente.");
                }
                case 6 -> {
                    Cliente objCliente = mPedirCliente();
                    System.out.println("Consumo mínimo: " + objConsumoController.mConsumoMinimo(objCliente));
                    System.out.println("Cálculo finalizado.");
                }
                case 7 -> {
                    Cliente objCliente = mPedirCliente();
                    System.out.println("Consumo máximo: " + objConsumoController.mConsumoMaximo(objCliente));
                    System.out.println("Cálculo finalizado.");
                }
                case 8 -> {
                    Cliente objCliente = mPedirCliente();
                    double[] objFranjas = objConsumoController.mConsumoPorFranja(objCliente);
                    System.out.printf("Franja 1: %.2f, Franja 2: %.2f, Franja 3: %.2f\n",
                            objFranjas[0], objFranjas[1], objFranjas[2]);
                    System.out.println("Cálculo de franjas completado.");
                }
                case 9 -> {
                    Cliente objCliente = mPedirCliente();
                    double[] objDias = objConsumoController.mConsumoPorDia(objCliente);
                    for (int i = 0; i < objDias.length; i++) {
                        System.out.printf("Día %d: %.2f\n", i + 1, objDias[i]);
                    }
                    System.out.println("Cálculo de consumo por días finalizado.");
                }
                case 10 -> {
                    Cliente objCliente = mPedirCliente();
                    double objTotalFactura = objConsumoController.mCalcularFacturaMensual(objCliente);
                    System.out.printf("Valor total a pagar del mes de enero: $%.2f\n", objTotalFactura);
                    System.out.println("Factura calculada correctamente.");
                }
            }
        } while (objOp != 0);
    }

    /**
     * Solicita al usuario el ID de un cliente y lo obtiene desde el controlador.
     * 
     * @return Cliente correspondiente al ID ingresado por el usuario.
     */
    private Cliente mPedirCliente() {
        System.out.print("ID del cliente: ");
        String objId = objSc.nextLine();
        Cliente cliente = objClienteController.mGetCliente(objId);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
        }
        return cliente;
    }
    
    /**
     * Permite al usuario crear un nuevo registrador para un cliente.
     * Si el cliente no existe, también se crea.
     */
    private void mCrearRegistrador() {
        System.out.print("ID del cliente: ");
        String objId = objSc.nextLine();
        Cliente objCliente = objClienteController.mGetCliente(objId);
    
        if (objCliente == null) {
            System.out.print("Tipo ID: ");
            String objTipo = objSc.nextLine();
            System.out.print("Correo: ");
            String objCorreo = objSc.nextLine();
            System.out.print("Dirección: ");
            String objDir = objSc.nextLine();
            objClienteController.mCrearCliente(objId, objTipo, objCorreo, objDir);
            objCliente = objClienteController.mGetCliente(objId);
        }
    
        System.out.print("ID del registrador: ");
        String objRid = objSc.nextLine();
        System.out.print("Dirección: ");
        String objDirR = objSc.nextLine();
        System.out.print("Ciudad: ");
        String objCiudad = objSc.nextLine();
        objCliente.mAgregarRegistrador(new Registrador(objRid, objDirR, objCiudad));
    }

    /**
     * Permite editar la dirección y ciudad de un registrador existente.
     */
    private void mEditarRegistrador() {
        Cliente objCliente = mPedirCliente();
        for (Registrador objReg : objCliente.mGetRegistradores()) {
            System.out.println("ID: " + objReg.mGetId());
        }
        System.out.print("ID del registrador a editar: ");
        String objRid = objSc.nextLine();
        for (Registrador objReg : objCliente.mGetRegistradores()) {
            if (objReg.mGetId().equals(objRid)) {
                System.out.print("Nueva dirección: ");
                String objNuevaDir = objSc.nextLine();
                System.out.print("Nueva ciudad: ");
                String objNuevaCiudad = objSc.nextLine();
                objRegistradorController.mEditarRegistrador(objReg, objNuevaDir, objNuevaCiudad);
                break;
            }
        }
    }

    /**
     * Permite cambiar el valor de consumo kWh para una hora específica
     * de un registrador de un cliente determinado.
     */
    private void mCambiarConsumoHora() {
        Cliente objCliente = mPedirCliente();
        System.out.print("ID del registrador: ");
        String objRid = objSc.nextLine();
        Registrador objReg = objCliente.mGetRegistradores().stream()
                .filter(reg -> reg.mGetId().equals(objRid))
                .findFirst()
                .orElse(null);
        if (objReg != null) {
            System.out.print("Día del mes (1 a 31): ");
            int objDia = objSc.nextInt();
            System.out.print("Hora del día (0 a 23): ");
            int objHoraDia = objSc.nextInt();
            int objHoraAbsoluta = (objDia - 1) * 24 + objHoraDia;
            System.out.print("Nuevo valor kWh: ");
            double objVal = objSc.nextDouble();
            objConsumoController.mCambiarConsumoHora(objReg, objHoraAbsoluta, objVal);
        }
    }
}