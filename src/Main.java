import Entities.Calculadoras.CalculadoraINSS;
import Entities.Calculadoras.CalculadoraImpRenda;
import Entities.Calculadoras.CalculadoraPlanoSaude;
import Entities.Calculadoras.CalculadoraPrevPriv;
import Entities.Funcionario;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        
        int tam = 8;
        
        Funcionario[] funcionarios = new Funcionario[tam];
        funcionarios[0] = new Funcionario(1000);
        funcionarios[1] = new Funcionario(2500);
        funcionarios[2] = new Funcionario(15000);
        funcionarios[3] = new Funcionario(600);
        funcionarios[4] = new Funcionario(3000);
        funcionarios[5] = new Funcionario(8900);
        funcionarios[6] = new Funcionario(1450);
        funcionarios[7] = new Funcionario(6000);

        Semaphore impRenda_ok = new Semaphore(0);
        Semaphore inss_ok = new Semaphore(0);
        Semaphore planoSaude_ok = new Semaphore(0);
        Semaphore prevPriv_ok = new Semaphore(0);

        int[] partes = new int[4];
        partes[0] = (tam/4);
        partes[1] = (tam/4) + partes[0];
        partes[2] = (tam/4) + partes[1];
        partes[3] = (tam/4) + partes[2];

        Thread[] threads = new Thread[4];
        threads[0] = new CalculadoraImpRenda(funcionarios, partes, 0, impRenda_ok, new Semaphore[]{inss_ok, planoSaude_ok, prevPriv_ok}, "parte1.txt");
        threads[1] = new CalculadoraINSS(funcionarios, partes, 1, inss_ok, new Semaphore[]{impRenda_ok, planoSaude_ok, prevPriv_ok}, "parte2.txt");
        threads[2] = new CalculadoraPrevPriv(funcionarios, partes, 2, prevPriv_ok, new Semaphore[]{impRenda_ok, planoSaude_ok, inss_ok}, "parte3.txt");
        threads[3] = new CalculadoraPlanoSaude(funcionarios, partes, 3, planoSaude_ok, new Semaphore[]{impRenda_ok, prevPriv_ok, inss_ok}, "parte4.txt");

        for(Thread thread : threads){
            thread.start();
        }
    }
}
