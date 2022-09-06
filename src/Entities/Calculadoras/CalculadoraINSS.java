package Entities.Calculadoras;

import Entities.Funcionario;

import java.util.concurrent.Semaphore;

public class CalculadoraINSS extends Calculadora{

    public CalculadoraINSS(Funcionario[] funcionarios, int[] partes, int parteInicial, Semaphore semaphore, Semaphore[] rendezvousSemaphores, String outputPath) {
        super("INSS", funcionarios, partes, parteInicial, semaphore, rendezvousSemaphores, outputPath);
    }

    @Override
    public void calculate_disc(Funcionario funcionario) {
        funcionario.setDisc_inss(funcionario.getSalario() * 0.08);
    }
}
