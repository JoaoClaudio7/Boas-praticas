package src.br.poo.Model;
public class Produto {
    private String nomeProduto;
    private int codigo;
    private double preco;
    private int quantidade;
    public Produto(){
        
    }
    public Produto(String nomeProduto,int codigo, double preco, int quantidade){
        this.nomeProduto = nomeProduto;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void adicionarQuantidade(int quantidadeAdicional) {
        if (quantidadeAdicional <= 0) return;
        this.quantidade += quantidadeAdicional;
    }
    public boolean reduzirQuantidade(int quantidadeRemover) {
        if (quantidadeRemover <= 0) return false;
        if (quantidadeRemover > this.quantidade) return false;
        this.quantidade -= quantidadeRemover;
        return true;
    }
    
}
