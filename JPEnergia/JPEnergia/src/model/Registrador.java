package model;

/**
 * Clase que representa un registrador de consumos horarios de energía.
 * Cada registrador está asociado a una dirección, ciudad e identificador único,
 * y almacena consumos por hora en un mes (usualmente enero).
 */
public class Registrador {
    private String objId;
    private String objDireccion;
    private String objCiudad;
    private Consumo[] objConsumos;

    /**
     * Constructor que inicializa un registrador con su ID, dirección y ciudad.
     *
     * @param objId        Identificador del registrador.
     * @param objDireccion Dirección física donde se ubica el registrador.
     * @param objCiudad    Ciudad donde se encuentra el registrador.
     */
    public Registrador(String objId, String objDireccion, String objCiudad) {
        this.objId = objId;
        this.objDireccion = objDireccion;
        this.objCiudad = objCiudad;
        this.objConsumos = new Consumo[744]; // 31 días * 24 horas
    }

    public String mGetId() {
        return objId;
    }

    public String mGetDireccion() {
        return objDireccion;
    }

    public String mGetCiudad() {
        return objCiudad;
    }

    public void mSetDireccion(String objDireccion) {
        this.objDireccion = objDireccion;
    }

    public void mSetCiudad(String objCiudad) {
        this.objCiudad = objCiudad;
    }

    /**
     * Devuelve el arreglo de consumos horarios del registrador.
     *
     * @return Arreglo de 744 posiciones con objetos Consumo.
     */
    public Consumo[] mGetConsumos() {
        return objConsumos;
    }

    /**
     * Agrega un consumo en la posición correspondiente a la hora.
     *
     * @param objHora    Hora del mes (0 a 743).
     * @param objConsumo Objeto Consumo con el valor correspondiente.
     */
    public void mAgregarConsumo(int objHora, Consumo objConsumo) {
        if (objHora >= 0 && objHora < 744) {
            objConsumos[objHora] = objConsumo;
        }
    }
}
