package src.br.poo.Service.Pagemento;

import src.br.poo.Model.Pedido;

public class PagamentoPix extends Pagamento{
    @Override
    public void processar(double valor, Pedido pedido) {
        if(valor == pedido.getValorTotal()+pedido.getFrete().getFreteTotal()){
            pedido.removerPedido();
            System.out.println("Pagamento feito com Cartão");
        }else{
            System.out.println("Valor invalido");
        }
    }
}
