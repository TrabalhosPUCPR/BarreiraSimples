package Entities.Calculadoras;

import Entities.Funcionario;

import java.util.concurrent.Semaphore;

public class CalculadoraPrevPriv extends Calculadora{

    public CalculadoraPrevPriv(Funcionario[] funcionarios, int parteIni, int parteFim, Semaphore semaphore, Semaphore[] rendezvousSemaphores, String outputPath) {
        super("Previdencia Privada", funcionarios, parteIni, parteFim, semaphore, rendezvousSemaphores, outputPath);
    }

    @Override
    public void calculate_disc(Funcionario funcionario) {
        funcionario.setDisc_prev_privada(funcionario.getSalario() * 0.04);
    }
}
