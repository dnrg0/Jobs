package controller;

import model.Cliente;

/**
 * Controlador encargado de gestionar los clientes del sistema.
 * Permite crear, obtener y verificar la existencia de clientes.
 * 
 * @author
 */
public class ClienteController {
    private Cliente[] objClientes = new Cliente[100]; // Capacidad máxima
    private int objCantidadClientes = 0;

    /**
     * Crea un nuevo cliente y lo agrega al sistema.
     *
     * @param id        Identificador único del cliente.
     * @param tipoId    Tipo de identificación (ej: CC, NIT).
     * @param email     Correo electrónico del cliente.
     * @param direccion Dirección física del cliente.
     */
    public void mCrearCliente(String id, String tipoId, String email, String direccion) {
        if (!mExisteCliente(id) && objCantidadClientes < objClientes.length) {
            objClientes[objCantidadClientes++] = new Cliente(id, tipoId, email, direccion);
        }
    }

    /**
     * Obtiene un cliente registrado a partir de su identificador.
     *
     * @param id Identificador del cliente.
     * @return Objeto Cliente correspondiente al ID, o null si no existe.
     */
    public Cliente mGetCliente(String id) {
        for (int i = 0; i < objCantidadClientes; i++) {
            if (objClientes[i].mGetId().equals(id)) {
                return objClientes[i];
            }
        }
        return null;
    }

    /**
     * Verifica si un cliente existe en el sistema.
     *
     * @param id Identificador del cliente.
     * @return true si el cliente existe, false en caso contrario.
     */
    public boolean mExisteCliente(String id) {
        return mGetCliente(id) != null;
    }

    /**
     * Retorna todos los clientes registrados en el sistema.
     *
     * @return Array de clientes registrados.
     */
    public Cliente[] mGetClientes() {
        Cliente[] objResultado = new Cliente[objCantidadClientes];
        System.arraycopy(objClientes, 0, objResultado, 0, objCantidadClientes);
        return objResultado;
    }
}