package src.br.poo.Service;
import java.util.*;

import src.br.poo.Model.Pedido;
import src.br.poo.Model.Produto;

public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : produtos) {
            System.out.println("Nome: " + p.getNomeProduto() + " | Codigo: " + p.getCodigo() + " | Preço: " + p.getPreco()+ " | Estoque: " + p.getQuantidade());
        }
    }

    public Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public void atualizarProduto(int codigo, String novoNome, double novoPreco) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.setNomeProduto(novoNome);
            produto.setPreco(novoPreco);
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void removerProduto(int codigo) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produtos.remove(produto);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public boolean aumentarQuantidade(int quantidade, Produto produto ) {
        if(quantidade < 0){
            return false;
        }
        produto.adicionarQuantidade(quantidade);
        return true;
    }

    public boolean verificarProdutoExistente(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public void listarTodosProdutos() {
        for (Produto produto : produtos) {
            System.out.println("Produto: " + produto.getNomeProduto() + " | Quantidade: " + produto.getQuantidade());
        }
    }

    public boolean registrarVenda(Pedido pedido, int codigoProduto, int quantidade) {
        Produto produto = buscarProdutoPorCodigo(codigoProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return false;
        }

        if (quantidade > produto.getQuantidade()) {
            System.out.println("Estoque insuficiente! Estoque atual: " + produto.getQuantidade());
            return false;
        }

        boolean subtraiu = produto.reduzirQuantidade(quantidade);
        if (!subtraiu) {
            System.out.println("Erro ao debitar do estoque.");
            return false;
        }

        pedido.adicionarItem(produto, quantidade);

        System.out.println("Venda registrada: " + quantidade + "x " + produto.getNomeProduto());
        System.out.println("Estoque restante: " + produto.getQuantidade());
        System.out.println("Valor total até agora: R$ " + pedido.getValorTotal());
        return true;
    }

}