package Entities.Calculadoras;

import Entities.Funcionario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public abstract class Calculadora extends Thread{

    Semaphore semaphore;
    Semaphore[] rendezvousSemaphores;
    String desc;
    File file;
    Funcionario[] funcionarios;
    int[] partes;
    int parteInicial;
    private int tamParte;

    protected Calculadora(String desc, Funcionario[] funcionarios, int[] partes, int parteInicial, Semaphore semaphore, Semaphore[] rendezvousSemaphores, String outputPath){
        this.desc = desc;
        this.semaphore = semaphore;
        this.rendezvousSemaphores = rendezvousSemaphores;
        this.file = new File(outputPath);
        this.funcionarios = funcionarios;
        this.partes = partes;
        this.parteInicial = parteInicial;
        this.tamParte = partes[1] - partes[0];
        try{
            this.file.createNewFile();
        }catch (Exception ignored){}
    }

    abstract public void calculate_disc(Funcionario funcionario);

    public void run(){
        try {
            for(int i = this.parteInicial; i < this.funcionarios.length + this.parteInicial; i++){
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
        writer.write("PARTE " + this.parteInicial + 1 + "\n\n");
        for(int i = this.parteInicial; i < this.parteInicial + this.tamParte; i++){
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
