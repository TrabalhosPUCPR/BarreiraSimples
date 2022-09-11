import Entities.Calculadoras.CalculadoraINSS;
import Entities.Calculadoras.CalculadoraImpRenda;
import Entities.Calculadoras.CalculadoraPlanoSaude;
import Entities.Calculadoras.CalculadoraPrevPriv;
import Entities.Funcionario;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        
        final int tam = 100_000;
        
        Funcionario[] funcionarios = new Funcionario[tam];
        for(int i = 0; i < tam; i++){
            Random random = new Random();
            funcionarios[i] = new Funcionario(random.nextInt(1000, 5001));
        }

        Semaphore impRenda_ok = new Semaphore(0);
        Semaphore inss_ok = new Semaphore(0);
        Semaphore planoSaude_ok = new Semaphore(0);
        Semaphore prevPriv_ok = new Semaphore(0);

        Thread[] threads = new Thread[4];
        int tamPart = tam/4;
        threads[0] = new CalculadoraImpRenda(funcionarios, 0, tamPart, impRenda_ok, new Semaphore[]{inss_ok, planoSaude_ok, prevPriv_ok}, "parte1.txt");
        threads[1] = new CalculadoraINSS(funcionarios, tamPart, tamPart*2, inss_ok, new Semaphore[]{impRenda_ok, planoSaude_ok, prevPriv_ok}, "parte2.txt");
        threads[2] = new CalculadoraPrevPriv(funcionarios, tamPart*2, tamPart*3, prevPriv_ok, new Semaphore[]{impRenda_ok, planoSaude_ok, inss_ok}, "parte3.txt");
        threads[3] = new CalculadoraPlanoSaude(funcionarios, tamPart*3, tamPart*4, planoSaude_ok, new Semaphore[]{impRenda_ok, prevPriv_ok, inss_ok}, "parte4.txt");

        for(Thread thread : threads){
            thread.start();
        }
    }
}
