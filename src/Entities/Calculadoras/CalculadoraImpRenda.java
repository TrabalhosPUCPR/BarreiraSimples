package Entities.Calculadoras;

import Entities.Funcionario;

import java.util.concurrent.Semaphore;

public class CalculadoraImpRenda extends Calculadora{

    public CalculadoraImpRenda(Funcionario[] funcionarios, int[] partes, int parteInicial, Semaphore semaphore, Semaphore[] rendezvousSemaphores, String outputPath) {
        super("Imposto de Renda", funcionarios, partes, parteInicial, semaphore, rendezvousSemaphores, outputPath);
    }

    @Override
    public void calculate_disc(Funcionario funcionario) {
        funcionario.setDisc_impostoRenda(funcionario.getSalario() * 0.20);
    }
}
