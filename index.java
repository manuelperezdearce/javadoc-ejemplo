import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<String, CuentaCorriente> cuentas;

    public Banco() {
        cuentas = new HashMap<>();
    }

    public void crearCuenta(String numeroCuenta, double saldoInicial) {
        cuentas.put(numeroCuenta, new CuentaCorriente(numeroCuenta, saldoInicial));
    }

    public void transferencia(String cuentaOrigen, String cuentaDestino, double monto) {
        if (!cuentas.containsKey(cuentaOrigen) || !cuentas.containsKey(cuentaDestino)) {
            System.out.println("Una o ambas cuentas no existen.");
            return;
        }

        CuentaCorriente origen = cuentas.get(cuentaOrigen);
        CuentaCorriente destino = cuentas.get(cuentaDestino);

        if (origen.getSaldo() < monto) {
            System.out.println("Saldo insuficiente en la cuenta origen.");
            return;
        }

        origen.debitar(monto);
        destino.abonar(monto);
        System.out.println("Transferencia realizada con éxito.");
    }

    public double consultarSaldo(String numeroCuenta) {
        if (!cuentas.containsKey(numeroCuenta)) {
            System.out.println("La cuenta no existe.");
            return -1;
        }
        return cuentas.get(numeroCuenta).getSaldo();
    }
}