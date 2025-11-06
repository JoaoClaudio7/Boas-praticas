import java.util.Scanner;
import src.br.poo.Model.Produto;

/*import src.br.poo.Service.ClienteService;
import src.br.poo.Service.Frete;
import src.br.poo.Service.Pagamento.PagamentoCartao;
import src.br.poo.Service.Pagamento.PagamentoPix;
import src.br.poo.Service.PedidoService;
import src.br.poo.Service.ProdutoService;*/
import src.br.poo.Service.ProdutoService;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner scannerdoString = new Scanner(System.in);    
        Scanner scannerInt = new Scanner(System.in);  

        ProdutoService produtoService = new ProdutoService();
        

        System.out.println("Bem vindo ao e-commerce\n"+"Escolha as seguntes opções\n");
        int opcao = -1;
        while(opcao != 0){

            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listagem produtos");
            System.out.println("3 - Entrada de estoque");
            System.out.println("4 - Registrar venda");
            System.out.println("5 - Listagem de vendas");
            System.out.println("6 - Relatorio consolidado de vendas");
            System.out.println("7 - Relatorio de estoque");
            System.out.println("0 - Sair");
            opcao = in.nextInt(); 

            switch (opcao) {
                case 1:
                //VERIFICAR SE ID JA CADASTRADO-BOA PRATICA CRIAR A FUNÇÃO PARA ISSO
                    System.out.println("Codigo do  produto");
                    int codigo = scannerInt.nextInt();

                    boolean codigoExistente = produtoService.verificarProdutoExistente(codigo);
                    
                    if (codigoExistente) {
                        System.out.println("Produto já cadastrado. Tente novamente.");
                        break;
                    }

                    System.out.println("Digite o nome do produto");
                    String nomeProduto = scannerdoString.nextLine();

                    System.out.println("Preço do produto");
                    double preco = scannerInt.nextDouble();

                    System.out.println("Digite a quantidade");
                    int quantidade = scannerInt.nextInt();

                    Produto produto = new Produto(nomeProduto, codigo, preco, quantidade);
                    produtoService.adicionarProduto(produto);
                    
                    break;
                case 2:
                    System.out.println("Lista de produtos:");
                    produtoService.listarProdutos();
                    break;
                case 3:
                    System.out.println("Digite o codigo do produto");
                    int codigoProduto = scannerInt.nextInt();   
                    
                    Produto produtoParaAumentar = produtoService.buscarProdutoPorCodigo(codigoProduto);

                    if(produtoParaAumentar == null){
                        System.out.println("Produto não encontrado");
                        break;
                    }

                    System.out.println("Digite a quantidade para ser adicionada");
                    int quantidadeAdicional = scannerInt.nextInt();

                    produtoService.aumentarQuantidade(quantidadeAdicional, produtoParaAumentar);
                    
                    break;
                case 7:
                    produtoService.listarTodosProdutos();
                    /* 
                case 5:
                    Pedido itenPedido = new Pedido();
                    System.out.println("Digite seu cpf");
                    int cpfBuscar = scannerInt.nextInt();
                    Cliente clienteBuscado = itenPedido.buscarCliente(clientes, cpfBuscar);
                    if(clienteBuscado != null){
                        itenPedido.setCliente(clienteBuscado);
                        pedidos.add(itenPedido);
                    }
                    else {
                        System.out.println("Cliente nao encontrado!");
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
                    if(buscarCliente == null || buscarProduto == null){
                        System.out.println("CPF ou Id invalido");
                    }else{
                        Produto produtoCarrinho = new Produto(produtoId, buscarProduto.getNomeProduto(), buscarProduto.getPreco());
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
                    break;*/
            }
        }
    in.close();
    scannerInt.close();
    scannerdoString.close();
    }
}