package model;

/**
 * Clase que representa el consumo de energía en una hora específica.
 * Incluye métodos para obtener, modificar y calcular el precio del consumo.
 * 
 * La tarifa depende de la franja horaria y del valor en kWh:
 * - Madrugada (0–6): entre 100 y 300 kWh → $200 por kWh.
 * - Diurna (7–17): entre 301 y 600 kWh → $300 por kWh.
 * - Noche (18–23): entre 601 y 999 kWh → $500 por kWh.
 * 
 * Cualquier otro valor retorna precio 0.
 * 
 */
public class Consumo {
    private int objHora; 
    private double objKwh;

    /**
     * Constructor de la clase Consumo.
     *
     * @param objHora Hora del mes (0 a 743) en la que se registró el consumo.
     * @param objKwh  Cantidad de energía consumida en kWh.
     */
    public Consumo(int objHora, double objKwh) {
        this.objHora = objHora;
        this.objKwh = objKwh;
    }

    /**
     * Obtiene la hora del mes asociada al consumo.
     *
     * @return Hora (0 a 743).
     */
    public int mGetHora() {
        return objHora;
    }

    /**
     * Obtiene el valor del consumo en kWh.
     *
     * @return Consumo en kWh.
     */
    public double mGetKwh() {
        return objKwh;
    }

    /**
     * Establece un nuevo valor de consumo en kWh.
     *
     * @param objKwh Nuevo consumo en kWh.
     */
    public void mSetKwh(double objKwh) {
        this.objKwh = objKwh;
    }

    /**
     * Calcula el precio del consumo según la hora del día y el rango de consumo.
     *
     * @return Precio calculado en pesos colombianos. Retorna 0 si no cumple con los rangos establecidos.
     */
    public double mCalcularPrecio() {
        int objHoraDia = objHora % 24;
        if (objHoraDia >= 0 && objHoraDia <= 6 && objKwh >= 100 && objKwh <= 300) {
            return objKwh * 200;
        } else if (objHoraDia >= 7 && objHoraDia <= 17 && objKwh > 300 && objKwh <= 600) {
            return objKwh * 300;
        } else if (objHoraDia >= 18 && objHoraDia <= 23 && objKwh > 600 && objKwh < 1000) {
            return objKwh * 500;
        } else {
            return 0;
        }
    }
}
