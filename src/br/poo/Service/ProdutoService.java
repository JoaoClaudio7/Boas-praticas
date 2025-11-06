package src.br.poo.Service;
import java.util.*;
import src.br.poo.Model.Produto;

public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("Lista de Produtos:");
        for (Produto p : produtos) {
            System.out.println("Nome: " + p.getNomeProduto() + " | Codigo: " + p.getCodigo() + " | Preço: " + p.getPreco());
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
}
