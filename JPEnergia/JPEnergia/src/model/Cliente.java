package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un cliente del sistema.
 * Cada cliente puede tener múltiples registradores de consumo.
 * Contiene información básica como ID, tipo de ID, correo y dirección.
 * 
 */
public class Cliente {
    private String objId;
    private String objTipoId;
    private String objEmail;
    private String objDireccion;
    private List<Registrador> objRegistradores;

    /**
     * Constructor para crear un nuevo cliente.
     *
     * @param objId        Identificador único del cliente.
     * @param objTipoId    Tipo de identificación del cliente (CC, NIT, etc.).
     * @param objEmail     Correo electrónico del cliente.
     * @param objDireccion Dirección física del cliente.
     */
    public Cliente(String objId, String objTipoId, String objEmail, String objDireccion) {
        this.objId = objId;
        this.objTipoId = objTipoId;
        this.objEmail = objEmail;
        this.objDireccion = objDireccion;
        this.objRegistradores = new ArrayList<>();
    }

    /**
     * Agrega un nuevo registrador de consumo al cliente.
     *
     * @param objRegistrador Objeto Registrador a asociar con el cliente.
     */
    public void mAgregarRegistrador(Registrador objRegistrador) {
        this.objRegistradores.add(objRegistrador);
    }

    /**
     * Obtiene la lista de registradores asociados al cliente.
     *
     * @return Lista de objetos Registrador.
     */
    public List<Registrador> mGetRegistradores() {
        return objRegistradores;
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return Identificador del cliente.
     */
    public String mGetId() {
        return objId;
    }

    /**
     * Obtiene el tipo de identificación del cliente.
     *
     * @return Tipo de identificación (CC, NIT, etc.).
     */
    public String mGetTipoId() {
        return objTipoId;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return Correo electrónico.
     */
    public String mGetEmail() {
        return objEmail;
    }

    /**
     * Obtiene la dirección del cliente.
     *
     * @return Dirección física.
     */
    public String mGetDireccion() {
        return objDireccion;
    }

    /**
     * Establece un nuevo correo electrónico para el cliente.
     *
     * @param objEmail Nuevo correo electrónico.
     */
    public void mSetEmail(String objEmail) {
        this.objEmail = objEmail;
    }

    /**
     * Establece una nueva dirección para el cliente.
     *
     * @param objDireccion Nueva dirección física.
     */
    public void mSetDireccion(String objDireccion) {
        this.objDireccion = objDireccion;
    }
}