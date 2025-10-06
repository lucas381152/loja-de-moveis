import java.io.Serializable;

public abstract class Produto  {
    private String codigo;
    private String nome;
    private String marca;
    private double preco;
    private int quantidadeEstoque;
    
    public Produto(String codigo, String nome, String marca, double preco, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    // Getters e Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { 
        this.quantidadeEstoque = quantidadeEstoque; 
    }
    
    @Override
    public boolean verificarDisponibilidade() {
        return quantidadeEstoque > 0;
    }
    
    @Override
    public double calcularDesconto(double percentual) {
        return preco * (1 - percentual / 100);
    }
    
    @Override
    public abstract void exibirDetalhes();
    
    @Override
    public String toString() {
        return nome + " - " + marca + " - R$ " + preco;
    }
}