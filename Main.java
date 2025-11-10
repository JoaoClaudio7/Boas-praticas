import java.util.List;
import java.util.Scanner;

import src.br.poo.Model.Vendas;
import src.br.poo.Model.Produto;
import src.br.poo.Service.VendaService;
import src.br.poo.Service.ProdutoService;
import src.br.poo.Service.RelatorioService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProdutoService produtoService = new ProdutoService();
        VendaService vendaService = new VendaService();

        System.out.println("Bem vindo ao e-commerce\nEscolha as seguintes opções\n");
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n1 - Cadastrar produto");
            System.out.println("2 - Listagem produtos");
            System.out.println("3 - Entrada de estoque");
            System.out.println("4 - Registrar venda");
            System.out.println("5 - Listagem de vendas");
            System.out.println("6 - Relatorio consolidado de vendas");
            System.out.println("7 - Relatorio de estoque");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String linha = scanner.nextLine().trim();
            if (linha.isEmpty()) continue;
            try {
                opcao = Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: {
                    System.out.print("Código do produto: ");
                    int codigo;

                    try {
                        codigo = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException ex) {
                        System.out.println("Código inválido.");
                        break;
                    }

                    if (produtoService.verificarProdutoExistente(codigo)) {
                        System.out.println("Produto já cadastrado. Tente novamente.");
                        break;
                    }

                    System.out.print("Nome do produto: ");
                    String nomeProduto = scanner.nextLine().trim();

                    System.out.print("Preço do produto: ");
                    double preco;
                    try {
                        preco = Double.parseDouble(scanner.nextLine().trim());
                    } catch (NumberFormatException ex) {
                        System.out.println("Preço inválido.");
                        break;
                    }

                    System.out.print("Quantidade inicial: ");
                    int quantidade;
                    try {
                        quantidade = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException ex) {
                        System.out.println("Quantidade inválida.");
                        break;
                    }

                    Produto produto = new Produto(nomeProduto, codigo, preco, quantidade);
                    produtoService.adicionarProduto(produto);
                    break;
                }

                case 2:
                    System.out.println("Lista de produtos:");
                    produtoService.listarProdutos();
                    break;

                case 3: {
                    System.out.print("Digite o código do produto: ");
                    int codigoProduto;
                    try {
                        codigoProduto = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException ex) {
                        System.out.println("Código inválido.");
                        break;
                    }

                    Produto produtoParaAumentar = produtoService.buscarProdutoPorCodigo(codigoProduto);
                    if (produtoParaAumentar == null) {
                        System.out.println("Produto não encontrado");
                        break;
                    }

                    System.out.print("Quantidade a adicionar: ");
                    int quantidadeAdicional;
                    try {
                        quantidadeAdicional = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException ex) {
                        System.out.println("Quantidade inválida.");
                        break;
                    }

                    boolean ok = produtoService.aumentarQuantidade(quantidadeAdicional, produtoParaAumentar);
                    if (ok) System.out.println("Estoque atualizado.");
                    else System.out.println("Falha ao atualizar estoque.");
                    break;
                }

                case 4: {
                    Produto produto = new Produto();
                    Vendas venda = new Vendas();
                    boolean continuarVenda = true;

                    while (continuarVenda) {
                        System.out.println("Digite o código do produto (0 para finalizar):");
                        int codigoProdutoVenda = Integer.parseInt(scanner.nextLine().trim());
                        if (codigoProdutoVenda == 0) break;

                        System.out.println("Digite a quantidade:");
                        int quantidadeVenda = Integer.parseInt(scanner.nextLine().trim());
                        
                        produto = produtoService.buscarProdutoPorCodigo(codigoProdutoVenda);

                        boolean registrado = vendaService.registrarVenda(produto, venda, quantidadeVenda);
                        
                        if (!registrado) {
                            System.out.println("Não foi possível registrar o item. Deseja tentar outro produto? (s/n)");
                            String tenta = scanner.nextLine().trim();
                            if (!tenta.equalsIgnoreCase("s")) break;
                        } else {
                            System.out.println("Produto adicionado à venda. Deseja adicionar mais produtos? (s/n)");
                            String mais = scanner.nextLine().trim();
                            if (!mais.equalsIgnoreCase("s")) continuarVenda = false;
                        }
                    }

                    System.out.println("\n=== VENDA FINALIZADA ===");
                    System.out.println("Valor total da venda: R$ " + venda.getValorTotal());
                    
                    break;
                }
                case 5:
                    vendaService.listarTodasVendas();
                    break;

                case 6: {
                    List<Vendas> listaDePedidos = vendaService.getPedidos();
                    RelatorioService relatorioService = new RelatorioService(listaDePedidos);
                    relatorioService.gerarRelatorioConsolidado();
                    break;
                }
                case 7:
                    produtoService.listarTodosProdutos();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }
}
