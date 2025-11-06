package src.br.poo.Model;
public class Cliente {
    private String nomeCliente;
    private int cpf;
    
    public Cliente(){

    }
    public Cliente(String nomeCliente, int cpf){
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nomeCliente;
    }
    public void setNome(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public int getCpf() {
        return cpf;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
}
