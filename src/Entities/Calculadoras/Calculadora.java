package Entities.Calculadoras;

import Entities.Funcionario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public abstract class Calculadora extends Thread{

    static int printParte = 1;
    Semaphore semaphore;
    Semaphore[] rendezvousSemaphores;
    String desc;
    File file;
    Funcionario[] funcionarios;
    int parteIni;
    int parteFim;
    int parte;

    protected Calculadora(String desc, Funcionario[] funcionarios, int parteIni, int parteFim, Semaphore semaphore, Semaphore[] rendezvousSemaphores, String outputPath){
        this.desc = desc;
        this.semaphore = semaphore;
        this.rendezvousSemaphores = rendezvousSemaphores;
        this.file = new File(outputPath);
        this.funcionarios = funcionarios;
        this.parteIni = parteIni;
        this.parteFim = parteFim;
        this.parte = Calculadora.printParte;
        Calculadora.printParte++;
        try{
            this.file.createNewFile();
        }catch (Exception ignored){}
    }

    abstract public void calculate_disc(Funcionario funcionario);

    @Override
    public void run(){
        try {
            for(int i = this.parteIni; i < this.funcionarios.length + this.parteIni; i++){
                this.calculate_disc(this.funcionarios[i % this.funcionarios.length]);
            }
            System.out.println(this.desc + " terminou!");
            this.rendezvous();
            this.print();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void print() throws IOException {
        FileWriter writer = new FileWriter(this.file);
        writer.write(""); // limpa o arquivo
        writer = new FileWriter(this.file, true);
        writer.write("PARTE " + this.parte + "\n\n");
        for(int i = this.parteIni; i < this.parteIni + (this.parteFim - this.parteIni); i++){
            writer.write(this.funcionarios[i].toString() + "\n");
        }
        writer.close();
    }
    protected void rendezvous() throws InterruptedException {
        this.semaphore.release(this.rendezvousSemaphores.length);
        for(Semaphore sem : this.rendezvousSemaphores){
            sem.acquire();
        }
    }
}
