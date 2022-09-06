import Entities.Calculadoras.CalculadoraINSS;
import Entities.Calculadoras.CalculadoraImpRenda;
import Entities.Calculadoras.CalculadoraPlanoSaude;
import Entities.Calculadoras.CalculadoraPrevPriv;
import Entities.Funcionario;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        
        int tam = 8;
        
        Funcionario[] funcionarios = new Funcionario[tam];
        Random random = new Random();
        for(int i = 0; i < tam; i++){
            funcionarios[i] = new Funcionario(random.nextInt(1000, 5001));
        }

        Semaphore impRenda_ok = new Semaphore(0);
        Semaphore inss_ok = new Semaphore(0);
        Semaphore planoSaude_ok = new Semaphore(0);
        Semaphore prevPriv_ok = new Semaphore(0);

        Thread[] threads = new Thread[4];
        threads[0] = new CalculadoraImpRenda(funcionarios, 0, 2, impRenda_ok, new Semaphore[]{inss_ok, planoSaude_ok, prevPriv_ok}, "parte1.txt");
        threads[1] = new CalculadoraINSS(funcionarios, 2, 4, inss_ok, new Semaphore[]{impRenda_ok, planoSaude_ok, prevPriv_ok}, "parte2.txt");
        threads[2] = new CalculadoraPrevPriv(funcionarios, 4, 6, prevPriv_ok, new Semaphore[]{impRenda_ok, planoSaude_ok, inss_ok}, "parte3.txt");
        threads[3] = new CalculadoraPlanoSaude(funcionarios, 6, 8, planoSaude_ok, new Semaphore[]{impRenda_ok, prevPriv_ok, inss_ok}, "parte4.txt");

        for(Thread thread : threads){
            thread.start();
        }
    }
}
