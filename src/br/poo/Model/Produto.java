package src.br.poo.Model;
public class Produto {
    private int id;
    private String nomeProduto;
    private double preco;
    //private int quantidade;
    //private double peso;
    public Produto(){
        
    }
    public Produto(int id, String nomeProduto, double preco){
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        //this.quantidade = quantidade;
    }

    public Produto(int id, String nomeProduto, double preco, double peso){
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        //this.quantidade = quantidade;
        //this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    /* 
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    */
    
}