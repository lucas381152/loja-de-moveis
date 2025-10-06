import java.util.ArrayList;
import java.util.Date;

public class Venda {
    private String codigoVenda;
    private Date dataVenda;
    private Vendedor vendedor;
    private ArrayList<Produto> itens;
    private double valorTotal;
    
    public Venda(String codigoVenda, Vendedor vendedor) {
        this.codigoVenda = codigoVenda;
        this.vendedor = vendedor;
        this.dataVenda = new Date();
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }
    
    public void adicionarItem(Produto produto, int quantidade) {
        try {
            if (produto.getQuantidadeEstoque() >= quantidade) {
                for (int i = 0; i < quantidade; i++) {
                    itens.add(produto);
                    valorTotal += produto.getPreco();
                }
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
            } else {
                System.out.println("Estoque insuficiente para " + produto.getNome());
            }
        } catch (Exception e) {
            System.out.println("Erro ao adicionar item: " + e.getMessage());
        }
    }
    
    public double calcularComissao() {
        return valorTotal * (vendedor.getComissao() / 100);
    }
    
    // Getters
    public String getCodigoVenda() { return codigoVenda; }
    public Date getDataVenda() { return dataVenda; }
    public Vendedor getVendedor() { return vendedor; }
    public ArrayList<Produto> getItens() { return new ArrayList<>(itens); }
    public double getValorTotal() { return valorTotal; }
}