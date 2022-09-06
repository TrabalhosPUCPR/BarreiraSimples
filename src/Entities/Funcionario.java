package Entities;

public class Funcionario {
    private static int Nfuncs = 0;
    int id;
    double salario;
    double disc_impostoRenda, disc_inss, disc_prev_privada, disc_planoSaude, disc_total = 0;

    public Funcionario(double salario) {
        Nfuncs += 1;
        this.id = Nfuncs;
        this.salario = salario;
    }

    public void setDisc_impostoRenda(double disc_impostoRenda) {
        this.disc_impostoRenda = disc_impostoRenda;
        updateDisc_total();
    }

    public void setDisc_inss(double disc_inss) {
        this.disc_inss = disc_inss;
        updateDisc_total();
    }

    public void setDisc_prev_privada(double disc_prev_privada) {
        this.disc_prev_privada = disc_prev_privada;
        updateDisc_total();
    }

    public void setDisc_planoSaude(double disc_planoSaude) {
        this.disc_planoSaude = disc_planoSaude;
        updateDisc_total();
    }

    private void updateDisc_total() {
        this.disc_total = this.disc_inss + this.disc_impostoRenda + this.disc_planoSaude + this.disc_prev_privada;
    }

    public double getSalario() {
        return salario;
    }

    public double getSalarioLiquido(){
        return salario-this.disc_total;
    }

    @Override
    public String toString() {
        String string = "ID: " + this.id + "\n";
        string += "Salario bruto: " + this.salario + "\n";
        string += "Imposto de Renda: " + this.disc_impostoRenda + "\n";
        string += "INSS: " + this.disc_inss + "\n";
        string += "Previdencia Privada: " + this.disc_prev_privada + "\n";
        string += "Plano de saude: " + this.disc_planoSaude + "\n";
        string += "Disconto total: " + this.disc_total + "\n";
        string += "Salario liquido: " + this.getSalarioLiquido() + "\n" + "\n";
        return string;
    }
}
