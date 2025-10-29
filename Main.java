import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.br.poo.Cliente;
import src.br.poo.Frete;
import src.br.poo.PagamentoCartao;
import src.br.poo.PagamentoPix;
import src.br.poo.Pedido;
import src.br.poo.Produto;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner scannerdoString = new Scanner(System.in);    
        Scanner scannerInt = new Scanner(System.in);  

        List<Produto> produtos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();

        System.out.println("Bem vindo ao JC-commerce\n"+"Escolha as seguntes opções\n");
        int opcao = -1;
        while(opcao != 0){
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar produto");
            System.out.println("3 - Listar clientes");
            System.out.println("4 - Listar produtos");
            System.out.println("5 - Criar pedido");
            System.out.println("6 - Adiconar pedido no carrinho");
            System.out.println("7 - Listar pedidos do carrinho");
            System.out.println("8 - Calcular frete");
            System.out.println("9 - Fazer pagamento");
            System.out.println("0 - Sair");
            opcao = in.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Nome do cliente");
                    String nomeCliente = scannerdoString.nextLine();
                    System.out.println("Digite o CPF do cliente");
                    int cpf = scannerInt.nextInt();
                    Cliente cliente1 = new Cliente(nomeCliente, cpf);
                    clientes.add(cliente1);
                    System.out.println("Cliente cadastrado!");
                    break;
                case 2:
                    System.out.println("Id do  produto");
                    int id = scannerInt.nextInt();
                    System.out.println("Digite o nome do produto");
                    String nomeProduto = scannerdoString.nextLine();
                    System.out.println("Preço do produto");
                    double preco = scannerInt.nextDouble();
                    System.out.println("Quantidade");
                    int quantidade = scannerInt.nextInt();
                    Produto produto = new Produto(id, nomeProduto, preco, quantidade);
                    produtos.add(produto);
                    break;
                case 3:
                    System.out.println("Lista de clientes cadastrados:");
                    for (Cliente cliente : clientes) {
                        System.out.println("Nome: "+cliente.getNome()+" CPF: "+cliente.getCpf());
                    }                    
                    break;
                case 4:
                    System.out.println("Lista de produtos cadastrados:");
                    for (Produto produt : produtos) {
                        System.out.println("Id: "+produt.getId()+" Produto: "+produt.getNomeProduto()+" Preço: "+ produt.getPreco()+" Qunatidade: "+produt.getQuantidade());
                    }
                    break;
                case 5:
                    Pedido itenPedido = new Pedido();
                    System.out.println("Digite seu cpf");
                    int cpfBuscar = scannerInt.nextInt();
                    Cliente clienteBuscado = itenPedido.buscarCliente(clientes, cpfBuscar);
                    if(clienteBuscado != null){
                        itenPedido.setCliente(clienteBuscado);
                        pedidos.add(itenPedido);
                    }
                    break;
                case 6:
                    Pedido itenCarrinho = new Pedido();

                    System.out.println("Digite seu cpf");
                    int buscarCpf = scannerInt.nextInt();
                    Cliente buscarCliente = itenCarrinho.buscarCliente(clientes, buscarCpf);
                    
                    System.out.println("Digite o id do produto");
                    int produtoId = scannerInt.nextInt();
                    Produto buscarProduto = itenCarrinho.buscarProduto(produtos, produtoId);
                    
                    System.out.println("Digite a quantidade");
                    int quantidadeCarrinho = scannerInt.nextInt();

                    System.out.println("Digite o peso so produto");
                    double peso = scannerInt.nextInt();
                    if(buscarCliente == null || buscarProduto == null){
                        System.out.println("CPF ou Id invalido");
                    }else{
                        Produto produtoCarrinho = new Produto(produtoId, buscarProduto.getNomeProduto(), buscarProduto.getPreco(), quantidadeCarrinho, peso);
                        for (Pedido pedido : pedidos) {
                            int cpfParaBuscar = pedido.getCliente().getCpf(); 
                            if (cpfParaBuscar == buscarCpf) {
                                pedido.adicionarProduto(produtoCarrinho);
                            }
                        }
                        
                    }
                    break;
                case 7:
                    Pedido listar = new Pedido();
                    System.out.println("Digite o numero do cpf");
                    int cpfCarrinho = scannerInt.nextInt();
                    Pedido listarPedido = listar.buscarPedido(pedidos, cpfCarrinho);
                    if(listarPedido == null){
                        System.out.println("Não existe pedido neste cpf");
                    }else{
                        System.out.println("Lista do cpf" + cpfCarrinho);
                        listarPedido.listarProdutos();
                    }
                    listarPedido.calcularTotal();
                    if (listarPedido.getFrete() != null) {
                        System.out.println("Total R$ "+(listarPedido.getValorTotal()+listarPedido.getFrete().getFreteTotal()));    
                    }else{
                        System.out.println("Total R$ "+listarPedido.getValorTotal());     
                    }
                    
                    break;
                case 8:
                    Frete frete = new Frete();
                    Pedido f = new Pedido();

                    System.out.println("Digite seu cpf");
                    int cpfFrete = scannerInt.nextInt();

                    Pedido pedidoAdicionarFrete = f.buscarPedido(pedidos, cpfFrete);

                    System.out.println("Se entrega for local digite (s) se não digite (n)");
                    String verificarFrete = scannerdoString.nextLine();
                    
                    if(verificarFrete.equalsIgnoreCase("n")){
                        System.out.println("Digite seu cep");
                        String cep = scannerdoString.nextLine();

                        double valorFrete = frete.calcularFrete(pedidoAdicionarFrete.pesoTotal(), cep);
                        System.out.println(valorFrete);
                        frete.setFreteTotal(valorFrete);
                        pedidoAdicionarFrete.adicionarFrete(frete);
                        pedidoAdicionarFrete.setValorTotal(frete.getFreteTotal()+pedidoAdicionarFrete.getValorTotal());
                    }else if(verificarFrete.equalsIgnoreCase("s")){
                        double valorFrete2 = frete.calcularFrete(pedidoAdicionarFrete.pesoTotal());
                        frete.setFretePeso(valorFrete2);
                        pedidoAdicionarFrete.adicionarFrete(frete);
                        pedidoAdicionarFrete.setValorTotal(frete.getFreteTotal()+pedidoAdicionarFrete.getValorTotal());
                    }else{
                        System.out.println("Escolha uma opção valida");
                    }
                    
                    break;
                case 9:
                    Pedido pagarCliente = new Pedido();
                    System.out.println("Digite o cpf");
                    int cpfPagar = scannerInt.nextInt();
                    
                    Pedido pedidoPagar = pagarCliente.buscarPedido(pedidos, cpfPagar);
                    
                    if(pedidoPagar.getFrete() == null){
                        System.out.println("O frete não foi adicionado");
                    }else if(pedidoPagar != null){
                        
                        System.out.println("Digite (c) para pagar com cartão e (p) para pix");
                        String pagar = scannerdoString.nextLine();
                        if (pagar.equals("c")) {
                            System.out.println("Digite o valor");
                            double valor = scannerInt.nextDouble();
                            PagamentoCartao pagamentoCartao = new PagamentoCartao();
                            pagamentoCartao.processar(valor, pedidoPagar);
                        }else if(pagar.equalsIgnoreCase("p")){
                            System.out.println("Digire o valor");
                            double valor = scannerInt.nextDouble();
                            PagamentoPix pagementoPix = new PagamentoPix();
                            pagementoPix.processar(valor, pedidoPagar);
                        }else{
                            System.out.println("Digite uma opção valida");
                        }
                    }else{
                        System.out.println("Pedido não encontrado");
                    }
                default:
                    break;
            }
        }
    in.close();
    scannerInt.close();
    scannerdoString.close();
    }
}