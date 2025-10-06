// Eletrodomésticos
public class Eletrodomestico extends Produto {
    private int voltagem;
    private int garantiaMeses;
    
    public Eletrodomestico(String codigo, String nome, String marca, double preco, 
                          int quantidadeEstoque, int voltagem, int garantiaMeses) {
        super(codigo, nome, marca, preco, quantidadeEstoque);
        this.voltagem = voltagem;
        this.garantiaMeses = garantiaMeses;
    }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("Eletrodoméstico: " + getNome() + " - Voltagem: " + voltagem + 
                          "V - Garantia: " + garantiaMeses + " meses");
    }
    
    // Getters e Setters específicos
    public int getVoltagem() { return voltagem; }
    public void setVoltagem(int voltagem) { this.voltagem = voltagem; }
    
    public int getGarantiaMeses() { return garantiaMeses; }
    public void setGarantiaMeses(int garantiaMeses) { this.garantiaMeses = garantiaMeses; }
}

// Móveis
public class Movel extends Produto {
    private String material;
    private String dimensoes;
    
    public Movel(String codigo, String nome, String marca, double preco, 
                int quantidadeEstoque, String material, String dimensoes) {
        super(codigo, nome, marca, preco, quantidadeEstoque);
        this.material = material;
        this.dimensoes = dimensoes;
    }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("Móvel: " + getNome() + " - Material: " + material + 
                          " - Dimensões: " + dimensoes);
    }
    
    // Getters e Setters específicos
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    
    public String getDimensoes() { return dimensoes; }
    public void setDimensoes(String dimensoes) { this.dimensoes = dimensoes; }
}

// Celulares
public class Celular extends Produto {
    private String sistemaOperacional;
    private int armazenamentoGB;
    private double tamanhoTela;
    
    public Celular(String codigo, String nome, String marca, double preco, 
                  int quantidadeEstoque, String sistemaOperacional, int armazenamentoGB, double tamanhoTela) {
        super(codigo, nome, marca, preco, quantidadeEstoque);
        this.sistemaOperacional = sistemaOperacional;
        this.armazenamentoGB = armazenamentoGB;
        this.tamanhoTela = tamanhoTela;
    }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("Celular: " + getNome() + " - SO: " + sistemaOperacional + 
                          " - Armazenamento: " + armazenamentoGB + "GB - Tela: " + tamanhoTela + "\"");
    }
    
    // Getters e Setters específicos
    public String getSistemaOperacional() { return sistemaOperacional; }
    public void setSistemaOperacional(String sistemaOperacional) { this.sistemaOperacional = sistemaOperacional; }
    
    public int getArmazenamentoGB() { return armazenamentoGB; }
    public void setArmazenamentoGB(int armazenamentoGB) { this.armazenamentoGB = armazenamentoGB; }
    
    public double getTamanhoTela() { return tamanhoTela; }
    public void setTamanhoTela(double tamanhoTela) { this.tamanhoTela = tamanhoTela; }
}

// Computadores
public class Computador extends Produto {
    private String processador;
    private int memoriaRAM;
    private int armazenamentoGB;
    
    public Computador(String codigo, String nome, String marca, double preco, 
                     int quantidadeEstoque, String processador, int memoriaRAM, int armazenamentoGB) {
        super(codigo, nome, marca, preco, quantidadeEstoque);
        this.processador = processador;
        this.memoriaRAM = memoriaRAM;
        this.armazenamentoGB = armazenamentoGB;
    }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("Computador: " + getNome() + " - Processador: " + processador + 
                          " - RAM: " + memoriaRAM + "GB - HD: " + armazenamentoGB + "GB");
    }
    
    // Getters e Setters específicos
    public String getProcessador() { return processador; }
    public void setProcessador(String processador) { this.processador = processador; }
    
    public int getMemoriaRAM() { return memoriaRAM; }
    public void setMemoriaRAM(int memoriaRAM) { this.memoriaRAM = memoriaRAM; }
    
    public int getArmazenamentoGB() { return armazenamentoGB; }
    public void setArmazenamentoGB(int armazenamentoGB) { this.armazenamentoGB = armazenamentoGB; }
}

// Vestuário
public class Vestuario extends Produto {
    private String tamanho;
    private String cor;
    private String material;
    
    public Vestuario(String codigo, String nome, String marca, double preco, 
                    int quantidadeEstoque, String tamanho, String cor, String material) {
        super(codigo, nome, marca, preco, quantidadeEstoque);
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("Vestuário: " + getNome() + " - Tamanho: " + tamanho + 
                          " - Cor: " + cor + " - Material: " + material);
    }
    
    // Getters e Setters específicos
    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}