package src.br.poo;
public class Frete {
    private double freteTotal;
  
    public double calcularFrete(double peso, String cep) {
        double fretePeso = peso*0.25;
        return fretePeso+10;
    }

    public double calcularFrete(double peso){
        return peso*0.25;
    }

    

    public double getFreteTotal() {
        return freteTotal;
    }

    public void setFreteTotal(double freteTotal) {
        this.freteTotal = freteTotal;
    }

    public double getFretePeso() {
        return freteTotal;
    }

    public void setFretePeso(double fretePeso) {
        this.freteTotal = fretePeso;
    }

    
}
