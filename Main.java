import java.util.*;
import src.br.poo.Service.*;
import src.br.poo.Service.Pagemento.*;
import src.br.poo.Model.*;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner scannerdoString = new Scanner(System.in);    
        Scanner scannerInt = new Scanner(System.in);  

        List<Produto> produtos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        
        PedidoService pedidoService = new PedidoService();

        System.out.println("Bem vindo ao JC-commerce\n"+"Escolha as seguntes opções\n");
        int opcao = -1;
        while(opcao != 0){
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar produto");
            System.out.println("3 - Listar clientes");
            System.out.println("4 - Listar produtos");
            System.out.println("5 - Adicionar itens no carrinho");
            System.out.println("6 - aq na sequencia do item 5: fechar conta - exibir o valor total, ou cancelar");
            System.out.println("7 - Listar pedidos do carrinho");
            System.out.println("8 - Fazer pagamento");
            System.out.println("0 - Sair");
            opcao = in.nextInt();
            switch (opcao) {
                case 1:
                    //VERIFICAR SE CPF JA CADASTRADO-BOA PRATICA CRIAR A FUNÇÃO PARA ISSO
                    System.out.println("Nome do cliente");
                    String nomeCliente = scannerdoString.nextLine();
                    System.out.println("Digite o CPF do cliente");
                    int cpf = scannerInt.nextInt();                 
                    boolean cpfExistente = false;
                    for (Cliente c : clientes) {
                        if (c.getCpf() == cpf) {
                            cpfExistente = true;
                            break;
                        }
                    }
                    if (cpfExistente) {
                        System.out.println("CPF já cadastrado. Tente novamente.");
                        break;
                    }
                    Cliente cliente1 = new Cliente(nomeCliente, cpf);
                    clientes.add(cliente1);
                    System.out.println("Cliente cadastrado!");
                    break;
                case 2:
                //VERIFICAR SE ID JA CADASTRADO-BOA PRATICA CRIAR A FUNÇÃO PARA ISSO
                    System.out.println("Id do  produto");
                    int id = scannerInt.nextInt();
                    boolean idExistente = false;

                    for (Produto p : produtos) {
                        if (p.getId() == id) {
                            idExistente = true;
                            break;
                        }
                    }

                    if (idExistente) {
                        System.out.println("ID já cadastrado. Tente novamente.");
                        break;
                    }

                    System.out.println("Digite o nome do produto");
                    String nomeProduto = scannerdoString.nextLine();

                    System.out.println("Preço do produto");
                    double preco = scannerInt.nextDouble();

                    Produto produto = new Produto(id, nomeProduto, preco);
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
                        System.out.println("Id: "+produt.getId()+" Produto: "+produt.getNomeProduto()+" Preço: "+ produt.getPreco());
                    }
                    break;
                case 5:
                    Pedido pedidoNovo = new Pedido();
                    
                    System.out.println("Digite seu cpf");
                    int cpfBuscar = scannerInt.nextInt();

                    Cliente clienteBuscado = pedidoNovo.buscarCliente(clientes, cpfBuscar);
                    Pedido pedidoDoCliente = pedidoService.buscarPedido(cpfBuscar);

                    if(clienteBuscado != null ){
                        if(pedidoDoCliente == null){
                            pedidoService.adicionarNovoPedido(pedidoNovo, clienteBuscado);
                            System.out.println("Pedido adicionado");
                        }else{
                            System.out.println("Digite o id do produto");
                            int idProduto = scannerInt.nextInt();
                        }
                    }else{
                        System.out.println("Pedido não encontrado, vefifique o CPF.");
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
                        pedidoService.buscarPedido(buscarCpf);
                        
                        //pedidoService.adicionarProduto(produtoCarrinho);
                    }
                    break;
                case 7:
                    System.out.println("Digite o numero do cpf");
                    int cpfCarrinho = scannerInt.nextInt();

                    Pedido pedidoParaListar = pedidoService.buscarPedido(cpfCarrinho);

                    if(pedidoParaListar == null){
                        System.out.println("Não existe pedido neste cpf");
                    }else{
                        System.out.println("Lista do cpf" + cpfCarrinho);
                        pedidoService.ListarPedido(pedidoParaListar);
                    }

                    pedidoService.calcularTotal(pedidoParaListar.getProdutos());

                    if (pedidoParaListar.getFrete() != null) {
                        System.out.println("Total R$ "+(pedidoParaListar.getValorTotal()+pedidoParaListar.getFrete().getFreteTotal()));    
                    }else{
                        System.out.println("Total R$ "+pedidoParaListar.getValorTotal());     
                    }
                    
                    break;
                case 8:
                    Frete frete = new Frete();
                    Pedido f = new Pedido();

                    System.out.println("Digite seu cpf");
                    int cpfFrete = scannerInt.nextInt();

                    //Pedido pedidoAdicionarFrete = f.buscarPedido(cpfFrete);

                    System.out.println("Se entrega for local digite (s) se não digite (n)");
                    String verificarFrete = scannerdoString.nextLine();
                    /* 
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
                    */
                    break;
                case 9:
                    Pedido pagarCliente = new Pedido();
                    System.out.println("Digite o cpf");
                    int cpfPagar = scannerInt.nextInt();
                    /* 
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
                            pagamentoCartao.processar(valor, pedidoPagar, pedidoService);
                        }else if(pagar.equalsIgnoreCase("p")){
                            System.out.println("Digire o valor");
                            double valor = scannerInt.nextDouble();
                            PagamentoPix pagementoPix = new PagamentoPix();
                            pagementoPix.processar(valor, pedidoPagar, pedidoService);
                        }else{
                            System.out.println("Digite uma opção valida");
                        }
                    }else{
                        System.out.println("Pedido não encontrado");
                    }*/
                default:
                    break;
            }
        }
    in.close();
    scannerInt.close();
    scannerdoString.close();
    }
}