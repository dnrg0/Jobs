package controller;

import model.Registrador;

/**
 * Controlador encargado de gestionar operaciones sobre objetos de tipo Registrador.
 * Permite editar la información básica del registrador como dirección y ciudad.
 */
public class RegistradorController {

    /**
     * Edita la dirección y ciudad de un registrador existente.
     *
     * @param objRegistrador    Objeto Registrador a modificar.
     * @param objNuevaDireccion Nueva dirección del registrador.
     * @param objNuevaCiudad    Nueva ciudad del registrador.
     */
    public void mEditarRegistrador(Registrador objRegistrador, String objNuevaDireccion, String objNuevaCiudad) {
        objRegistrador.mSetDireccion(objNuevaDireccion);
        objRegistrador.mSetCiudad(objNuevaCiudad);
    }
}
