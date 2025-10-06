import java.util.ArrayList;

public class Estoque {
    private ArrayList<Produto> produtos;
    
    public Estoque() {
        this.produtos = new ArrayList<>();
    }
    
    public void adicionarProduto(Produto produto) {
        try {
            produtos.add(produto);
            System.out.println("Produto adicionado ao estoque: " + produto.getNome());
        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }
    
    public boolean removerProduto(String codigo) {
        try {
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getCodigo().equals(codigo)) {
                    produtos.remove(i);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
            return false;
        }
    }
    
    public Produto buscarProduto(String codigo) {
        try {
            for (Produto produto : produtos) {
                if (produto.getCodigo().equals(codigo)) {
                    return produto;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }
    
    public ArrayList<Produto> listarProdutosPorTipo(Class<?> tipo) {
        ArrayList<Produto> resultado = new ArrayList<>();
        try {
            for (Produto produto : produtos) {
                if (tipo.isInstance(produto)) {
                    resultado.add(produto);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao filtrar produtos: " + e.getMessage());
        }
        return resultado;
    }
    
    public boolean atualizarEstoque(String codigo, int quantidade) {
        try {
            Produto produto = buscarProduto(codigo);
            if (produto != null) {
                produto.setQuantidadeEstoque(quantidade);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar estoque: " + e.getMessage());
            return false;
        }
    }
}