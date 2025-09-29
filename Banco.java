// Archivo: Banco.java
import java.util.HashMap;
import java.util.Map;

/**
 * Gestiona un conjunto de cuentas corrientes en memoria.
 * Permite crear cuentas, realizar transferencias y consultar saldos.
 *
 * <p>Esta implementación usa un {@code Map} en memoria y está pensada
 * para fines didácticos. En un entorno real, se sugiere un repositorio
 * persistente y manejo de excepciones más robusto.</p>
 *
 * @author Manuel Pérez de Arce
 * @version 1B- 28/09/2025
 */
public class Banco {
    private final Map<String, CuentaCorriente> cuentas;

    /**
     * Crea un banco con un contenedor de cuentas vacío.
     */
    public Banco() {
        this.cuentas = new HashMap<>();
    }

    /**
     * Crea una nueva cuenta y la registra en el banco.
     *
     * @param numeroCuenta identificador único de la cuenta
     * @param saldoInicial saldo con el que se abre la cuenta (>= 0)
     * @throws IllegalArgumentException si ya existe la cuenta
     */
    public void crearCuenta(String numeroCuenta, double saldoInicial) {
        if (cuentas.containsKey(numeroCuenta)) {
            throw new IllegalArgumentException("La cuenta ya existe: " + numeroCuenta);
        }
        cuentas.put(numeroCuenta, new CuentaCorriente(numeroCuenta, saldoInicial));
    }

    /**
     * Transfiere dinero entre dos cuentas registradas.
     *
     * @param cuentaOrigen número de cuenta origen
     * @param cuentaDestino número de cuenta destino
     * @param monto monto a transferir (>= 0)
     * @throws IllegalArgumentException si alguna cuenta no existe o el monto es negativo
     * @throws IllegalStateException si la cuenta origen no tiene saldo suficiente
     */
    public void transferencia(String cuentaOrigen, String cuentaDestino, double monto) {
        if (!cuentas.containsKey(cuentaOrigen) || !cuentas.containsKey(cuentaDestino)) {
            throw new IllegalArgumentException("Cuenta origen/destino inexistente.");
        }
        if (monto < 0) {
            throw new IllegalArgumentException("Monto inválido.");
        }
        CuentaCorriente origen = cuentas.get(cuentaOrigen);
        CuentaCorriente destino = cuentas.get(cuentaDestino);
        origen.debitar(monto);
        destino.abonar(monto);
    }

    /**
     * Devuelve el saldo de una cuenta registrada.
     *
     * @param numeroCuenta número de cuenta a consultar
     * @return saldo actual de la cuenta
     * @throws IllegalArgumentException si la cuenta no existe
     */
    public double consultarSaldo(String numeroCuenta) {
        if (!cuentas.containsKey(numeroCuenta)) {
            throw new IllegalArgumentException("La cuenta no existe: " + numeroCuenta);
        }
        return cuentas.get(numeroCuenta).getSaldo();
    }
}