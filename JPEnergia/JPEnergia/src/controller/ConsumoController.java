package controller;

import model.Cliente;
import model.Consumo;
import model.Registrador;

import java.util.Random;

/**
 * Controlador encargado de gestionar los consumos de energía de los clientes.
 * Permite cargar consumos, modificarlos y calcular estadísticas mensuales.
 */
public class ConsumoController {
    private Random objRandom = new Random();

    public void mCargarConsumoMesCliente(Cliente objCliente) {
        for (Registrador objRegistrador : objCliente.mGetRegistradores()) {
            mCargarConsumosEnero(objRegistrador);
        }
    }

    public void mCargarConsumosEnero(Registrador objRegistrador) {
        for (int i = 0; i < 24 * 31; i++) {
            double objValor = mGenerarConsumoPorHora(i % 24);
            objRegistrador.mAgregarConsumo(i, new Consumo(i, objValor));
        }
    }

    public void mCambiarConsumoHora(Registrador objRegistrador, int hora, double objNuevoValor) {
        Consumo[] consumos = objRegistrador.mGetConsumos();
        if (hora >= 0 && hora < consumos.length && consumos[hora] != null) {
            consumos[hora].mSetKwh(objNuevoValor);
        }
    }

    private double mGenerarConsumoPorHora(int hora) {
        if (hora >= 0 && hora <= 6) return 100 + objRandom.nextDouble() * 200;
        if (hora >= 7 && hora <= 17) return 301 + objRandom.nextDouble() * 299;
        return 601 + objRandom.nextDouble() * 398;
    }

    public double mConsumoMinimo(Cliente objCliente) {
        double min = Double.MAX_VALUE;
        for (Registrador objRegistrador : objCliente.mGetRegistradores()) {
            for (Consumo c : objRegistrador.mGetConsumos()) {
                if (c != null && c.mGetKwh() < min) {
                    min = c.mGetKwh();
                }
            }
        }
        return (min == Double.MAX_VALUE) ? 0 : min;
    }

    public double mConsumoMaximo(Cliente objCliente) {
        double max = Double.MIN_VALUE;
        for (Registrador objRegistrador : objCliente.mGetRegistradores()) {
            for (Consumo c : objRegistrador.mGetConsumos()) {
                if (c != null && c.mGetKwh() > max) {
                    max = c.mGetKwh();
                }
            }
        }
        return (max == Double.MIN_VALUE) ? 0 : max;
    }

    public double[] mConsumoPorFranja(Cliente objCliente) {
        double[] objConsumo = new double[3];
        for (Registrador objRegistrador : objCliente.mGetRegistradores()) {
            for (Consumo c : objRegistrador.mGetConsumos()) {
                if (c != null) {
                    int hora = c.mGetHora() % 24;
                    double kwh = c.mGetKwh();
                    if (hora >= 0 && hora <= 6) objConsumo[0] += kwh;
                    else if (hora <= 17) objConsumo[1] += kwh;
                    else objConsumo[2] += kwh;
                }
            }
        }
        return objConsumo;
    }

    public double[] mConsumoPorDia(Cliente objCliente) {
        double[] consumos = new double[31];
        for (Registrador objRegistrador : objCliente.mGetRegistradores()) {
            for (int h = 0; h < 744; h++) {
                Consumo c = objRegistrador.mGetConsumos()[h];
                if (c != null) {
                    int dia = h / 24;
                    consumos[dia] += c.mGetKwh();
                }
            }
        }
        return consumos;
    }

    public double mCalcularFacturaMensual(Cliente objCliente) {
        double total = 0;
        for (Registrador objRegistrador : objCliente.mGetRegistradores()) {
            for (Consumo c : objRegistrador.mGetConsumos()) {
                if (c != null) {
                    total += c.mCalcularPrecio();
                }
            }
        }
        return total;
    }
}