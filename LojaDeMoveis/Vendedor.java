public class Vendedor {
    private String codigo;
    private String nome;
    private String cpf;
    private double comissao;
    
    public Vendedor(String codigo, String nome, String cpf, double comissao) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.comissao = comissao;
    }
    
    // Getters e Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public double getComissao() { return comissao; }
    public void setComissao(double comissao) { this.comissao = comissao; }
    
    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}