package Entities.Calculadoras;

import Entities.Funcionario;

import java.util.concurrent.Semaphore;

public class CalculadoraINSS extends Calculadora{

    public CalculadoraINSS(Funcionario[] funcionarios, int parteIni, int parteFim, Semaphore semaphore, Semaphore[] rendezvousSemaphores, String outputPath) {
        super("INSS", funcionarios, parteIni, parteFim, semaphore, rendezvousSemaphores, outputPath);
    }

    @Override
    public void calculate_disc(Funcionario funcionario) {
        funcionario.setDisc_inss(funcionario.getSalario() * 0.08);
    }
}
