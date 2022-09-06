package Entities.Calculadoras;

import Entities.Funcionario;

import java.util.concurrent.Semaphore;

public class CalculadoraPrevPriv extends Calculadora{

    public CalculadoraPrevPriv(Funcionario[] funcionarios, int[] partes, int parteInicial, Semaphore semaphore, Semaphore[] rendezvousSemaphores, String outputPath) {
        super("Previdencia Privada", funcionarios, partes, parteInicial, semaphore, rendezvousSemaphores, outputPath);
    }

    @Override
    public void calculate_disc(Funcionario funcionario) {
        funcionario.setDisc_prev_privada(funcionario.getSalario() * 0.04);
    }
}
