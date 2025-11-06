package src.br.poo.Service.Pagamento;

import src.br.poo.Model.Pedido;
import src.br.poo.Service.PedidoService;

public abstract class Pagamento {
    public void processar(double valor, Pedido pedido, PedidoService pedidoService){};
}
