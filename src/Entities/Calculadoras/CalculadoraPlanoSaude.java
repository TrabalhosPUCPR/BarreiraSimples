package Entities.Calculadoras;

import Entities.Funcionario;

import java.util.concurrent.Semaphore;

public class CalculadoraPlanoSaude extends Calculadora{

    public CalculadoraPlanoSaude(Funcionario[] funcionarios, int parteIni, int parteFim, Semaphore semaphore, Semaphore[] rendezvousSemaphores, String outputPath) {
        super("Plano de Saude", funcionarios, parteIni, parteFim, semaphore, rendezvousSemaphores, outputPath);
    }

    @Override
    public void calculate_disc(Funcionario funcionario) {
        funcionario.setDisc_planoSaude(funcionario.getSalario() * 0.02);
    }
}
