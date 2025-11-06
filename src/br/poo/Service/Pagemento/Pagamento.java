package src.br.poo.Service.Pagemento;

import src.br.poo.Model.Pedido;

public abstract class Pagamento {
    public void processar(double valor, Pedido pedido){};
}
