/**
 * Representa una cuenta corriente con número y saldo.
 * Permite abonar, debitar y consultar el saldo.
 *
 * @author Manuel Pérez de Arce
 * @version 1B- 28/09/2025
 */
public class CuentaCorriente {
    private final String numeroCuenta;
    private double saldo;

    /**
     * Crea una cuenta con número y saldo inicial.
     * @param numeroCuenta identificador único de la cuenta
     * @param saldoInicial saldo inicial (>= 0)
     * @throws IllegalArgumentException si el saldo inicial es negativo
     */
    public CuentaCorriente(String numeroCuenta, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial negativo");
        }
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    /**
     * Suma un monto al saldo.
     * @param monto monto a abonar (>= 0)
     * @throws IllegalArgumentException si el monto es negativo
     */
    public void abonar(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("Monto inválido");
        }
        saldo += monto;
    }

    /**
     * Resta un monto del saldo si hay fondos suficientes.
     * @param monto monto a debitar (>= 0)
     * @throws IllegalArgumentException si el monto es negativo
     * @throws IllegalStateException si no hay fondos suficientes
     */
    public void debitar(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("Monto inválido");
        }
        if (saldo < monto) {
            throw new IllegalStateException("Saldo insuficiente");
        }
        saldo -= monto;
    }

    /** @return saldo actual */
    public double getSaldo() { return saldo; }

    /** @return número de cuenta */
    public String getNumeroCuenta() { return numeroCuenta; }
}
