package src.br.poo.Service.Pagamento;

import src.br.poo.Model.Pedido;
import src.br.poo.Service.PedidoService;

public class PagamentoPix extends Pagamento{
    @Override
    public void processar(double valor, Pedido pedido, PedidoService pedidoService) {

        if(valor == pedido.getValorTotal()+pedido.getFrete().getFreteTotal()){
            pedidoService.removerPedido(pedido);
            System.out.println("Pagamento feito com PIX");
        }else{
            System.out.println("Valor invalido");
        }
    }
}
