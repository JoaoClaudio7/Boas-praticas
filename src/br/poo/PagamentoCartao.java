package src.br.poo;
public class PagamentoCartao extends Pagamento{
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
