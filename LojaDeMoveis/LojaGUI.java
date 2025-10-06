import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LojaGUI extends JFrame {
    private Estoque estoque;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Venda> vendas;
    private Vendedor vendedorAtual;
    
    // Componentes da interface
    private JTabbedPane abas;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private JComboBox<String> comboTipoProduto;
    private JTextArea areaDetalhes;
    
    public LojaGUI() {
        estoque = new Estoque();
        vendedores = new ArrayList<>();
        vendas = new ArrayList<>();
        inicializarDados();
        configurarInterface();
    }
    
    private void inicializarDados() {
        // Adicionar alguns vendedores
        vendedores.add(new Vendedor("V001", "João Silva", "123.456.789-00", 5.0));
        vendedores.add(new Vendedor("V002", "Maria Santos", "987.654.321-00", 7.0));
        
        // Adicionar produtos de exemplo
        estoque.adicionarProduto(new Eletrodomestico("E001", "Geladeira", "Brastemp", 2500.00, 10, 220, 12));
        estoque.adicionarProduto(new Movel("M001", "Sofá", "Tramontina", 1500.00, 5, "Couro", "2x0.9x0.8m"));
        estoque.adicionarProduto(new Celular("C001", "iPhone 15", "Apple", 5000.00, 8, "iOS", 128, 6.1));
        estoque.adicionarProduto(new Computador("PC001", "Notebook", "Dell", 3500.00, 6, "Intel i5", 8, 512));
        estoque.adicionarProduto(new Vestuario("V001", "Camiseta", "Nike", 89.90, 20, "M", "Azul", "Algodão"));
    }
    
    private void configurarInterface() {
        setTitle("Sistema de Loja - Eletrodomésticos, Móveis, Celulares, Computadores e Vestuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        abas = new JTabbedPane();
        
        // Aba de Produtos
        abas.addTab("Produtos", criarAbaProdutos());
        
        // Aba de Vendas
        abas.addTab("Vendas", criarAbaVendas());
        
        // Aba de Estoque
        abas.addTab("Estoque", criarAbaEstoque());
        
        add(abas);
    }
    
    private JPanel criarAbaProdutos() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Painel superior - Filtros
        JPanel panelFiltros = new JPanel(new FlowLayout());
        panelFiltros.add(new JLabel("Filtrar por tipo:"));
        
        comboTipoProduto = new JComboBox<>(new String[]{"Todos", "Eletrodomésticos", "Móveis", "Celulares", "Computadores", "Vestuário"});
        comboTipoProduto.addActionListener(e -> atualizarTabelaProdutos());
        panelFiltros.add(comboTipoProduto);
        
        JButton btnCadastrar = new JButton("Cadastrar Novo Produto");
        btnCadastrar.addActionListener(e -> mostrarDialogoCadastroProduto());
        panelFiltros.add(btnCadastrar);
        
        panel.add(panelFiltros, BorderLayout.NORTH);
        
        // Tabela de produtos
        String[] colunas = {"Código", "Nome", "Marca", "Preço", "Estoque", "Tipo"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaProdutos = new JTable(modeloTabela);
        
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Painel inferior - Detalhes
        areaDetalhes = new JTextArea(5, 50);
        areaDetalhes.setEditable(false);
        JScrollPane scrollDetalhes = new JScrollPane(areaDetalhes);
        panel.add(scrollDetalhes, BorderLayout.SOUTH);
        
        // Listener para seleção na tabela
        tabelaProdutos.getSelectionModel().addListSelectionListener(e -> {
            int linha = tabelaProdutos.getSelectedRow();
            if (linha >= 0) {
                String codigo = (String) modeloTabela.getValueAt(linha, 0);
                Produto produto = estoque.buscarProduto(codigo);
                if (produto != null) {
                    areaDetalhes.setText("");
                    produto.exibirDetalhes();
                    areaDetalhes.append("Disponível: " + (produto.verificarDisponibilidade() ? "Sim" : "Não") + "\n");
                    areaDetalhes.append("Preço com 10% de desconto: R$ " + String.format("%.2f", produto.calcularDesconto(10)));
                }
            }
        });
        
        atualizarTabelaProdutos();
        return panel;
    }
    
    private JPanel criarAbaVendas() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Painel de seleção de vendedor
        JPanel panelVendedor = new JPanel(new FlowLayout());
        panelVendedor.add(new JLabel("Vendedor:"));
        
        JComboBox<Vendedor> comboVendedores = new JComboBox<>();
        for (Vendedor v : vendedores) {
            comboVendedores.addItem(v);
        }
        panelVendedor.add(comboVendedores);
        
        JButton btnIniciarVenda = new JButton("Iniciar Nova Venda");
        btnIniciarVenda.addActionListener(e -> {
            vendedorAtual = (Vendedor) comboVendedores.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Venda iniciada para: " + vendedorAtual.getNome());
        });
        panelVendedor.add(btnIniciarVenda);
        
        panel.add(panelVendedor, BorderLayout.NORTH);
        
        // Área de informações da venda
        JTextArea areaVenda = new JTextArea(10, 50);
        areaVenda.setEditable(false);
        panel.add(new JScrollPane(areaVenda), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel criarAbaEstoque() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JButton btnAtualizarEstoque = new JButton("Atualizar Estoque");
        btnAtualizarEstoque.addActionListener(e -> mostrarDialogoAtualizarEstoque());
        panel.add(btnAtualizarEstoque, BorderLayout.NORTH);
        
        // Tabela de estoque
        String[] colunas = {"Código", "Nome", "Estoque Atual"};
        DefaultTableModel modeloEstoque = new DefaultTableModel(colunas, 0);
        JTable tabelaEstoque = new JTable(modeloEstoque);
        
        // Preencher tabela
        for (Produto produto : estoque.listarProdutos()) {
            modeloEstoque.addRow(new Object[]{
                produto.getCodigo(),
                produto.getNome(),
                produto.getQuantidadeEstoque()
            });
        }
        
        panel.add(new JScrollPane(tabelaEstoque), BorderLayout.CENTER);
        
        return panel;
    }
    
    private void atualizarTabelaProdutos() {
        modeloTabela.setRowCount(0);
        
        ArrayList<Produto> produtosFiltrados;
        String tipoSelecionado = (String) comboTipoProduto.getSelectedItem();
        
        switch (tipoSelecionado) {
            case "Eletrodomésticos":
                produtosFiltrados = estoque.listarProdutosPorTipo(Eletrodomestico.class);
                break;
            case "Móveis":
                produtosFiltrados = estoque.listarProdutosPorTipo(Movel.class);
                break;
            case "Celulares":
                produtosFiltrados = estoque.listarProdutosPorTipo(Celular.class);
                break;
            case "Computadores":
                produtosFiltrados = estoque.listarProdutosPorTipo(Computador.class);
                break;
            case "Vestuário":
                produtosFiltrados = estoque.listarProdutosPorTipo(Vestuario.class);
                break;
            default:
                produtosFiltrados = estoque.listarProdutos();
        }
        
        for (Produto produto : produtosFiltrados) {
            String tipo = produto.getClass().getSimpleName();
            modeloTabela.addRow(new Object[]{
                produto.getCodigo(),
                produto.getNome(),
                produto.getMarca(),
                String.format("R$ %.2f", produto.getPreco()),
                produto.getQuantidadeEstoque(),
                tipo
            });
        }
    }
    
    private void mostrarDialogoCadastroProduto() {
        JDialog dialog = new JDialog(this, "Cadastrar Novo Produto", true);
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        // Campos comuns
        JTextField txtCodigo = new JTextField();
        JTextField txtNome = new JTextField();
        JTextField txtMarca = new JTextField();
        JTextField txtPreco = new JTextField();
        JTextField txtEstoque = new JTextField();
        
        panel.add(new JLabel("Código:"));
        panel.add(txtCodigo);
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Marca:"));
        panel.add(txtMarca);
        panel.add(new JLabel("Preço:"));
        panel.add(txtPreco);
        panel.add(new JLabel("Estoque:"));
        panel.add(txtEstoque);
        
        // Combo para seleção do tipo
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{
            "Eletrodoméstico", "Móvel", "Celular", "Computador", "Vestuário"
        });
        panel.add(new JLabel("Tipo:"));
        panel.add(comboTipo);
        
        // Campos específicos (serão atualizados dinamicamente)
        JPanel panelCamposEspecificos = new JPanel(new GridLayout(0, 2, 5, 5));
        
        comboTipo.addActionListener(e -> {
            panelCamposEspecificos.removeAll();
            String tipo = (String) comboTipo.getSelectedItem();
            
            switch (tipo) {
                case "Eletrodoméstico":
                    panelCamposEspecificos.add(new JLabel("Voltagem:"));
                    panelCamposEspecificos.add(new JTextField());
                    panelCamposEspecificos.add(new JLabel("Garantia (meses):"));
                    panelCamposEspecificos.add(new JTextField());
                    break;
                case "Móvel":
                    panelCamposEspecificos.add(new JLabel("Material:"));
                    panelCamposEspecificos.add(new JTextField());
                    panelCamposEspecificos.add(new JLabel("Dimensões:"));
                    panelCamposEspecificos.add(new JTextField());
                    break;
                case "Celular":
                    panelCamposEspecificos.add(new JLabel("Sistema Operacional:"));
                    panelCamposEspecificos.add(new JTextField());
                    panelCamposEspecificos.add(new JLabel("Armazenamento (GB):"));
                    panelCamposEspecificos.add(new JTextField());
                    panelCamposEspecificos.add(new JLabel("Tamanho Tela:"));
                    panelCamposEspecificos.add(new JTextField());
                    break;
                case "Computador":
                    panelCamposEspecificos.add(new JLabel("Processador:"));
                    panelCamposEspecificos.add(new JTextField());
                    panelCamposEspecificos.add(new JLabel("Memória RAM (GB):"));
                    panelCamposEspecificos.add(new JTextField());
                    panelCamposEspecificos.add(new JLabel("Armazenamento (GB):"));
                    panelCamposEspecificos.add(new JTextField());
                    break;
                case "Vestuário":
                    panelCamposEspecificos.add(new JLabel("Tamanho:"));
                    panelCamposEspecificos.add(new JTextField());
                    panelCamposEspecificos.add(new JLabel("Cor:"));
                    panelCamposEspecificos.add(new JTextField());
                    panelCamposEspecificos.add(new JLabel("Material:"));
                    panelCamposEspecificos.add(new JTextField());
                    break;
            }
            panelCamposEspecificos.revalidate();
            panelCamposEspecificos.repaint();
        });
        
        // Disparar evento para criar campos iniciais
        comboTipo.setSelectedIndex(0);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                // Validar e salvar produto
                String codigo = txtCodigo.getText();
                String nome = txtNome.getText();
                String marca = txtMarca.getText();
                double preco = Double.parseDouble(txtPreco.getText());
                int quantidade = Integer.parseInt(txtEstoque.getText());
                
                Produto novoProduto = null;
                String tipo = (String) comboTipo.getSelectedItem();
                
                switch (tipo) {
                    case "Eletrodoméstico":
                        int voltagem = Integer.parseInt(((JTextField) panelCamposEspecificos.getComponent(1)).getText());
                        int garantia = Integer.parseInt(((JTextField) panelCamposEspecificos.getComponent(3)).getText());
                        novoProduto = new Eletrodomestico(codigo, nome, marca, preco, quantidade, voltagem, garantia);
                        break;
                    case "Móvel":
                        String material = ((JTextField) panelCamposEspecificos.getComponent(1)).getText();
                        String dimensoes = ((JTextField) panelCamposEspecificos.getComponent(3)).getText();
                        novoProduto = new Movel(codigo, nome, marca, preco, quantidade, material, dimensoes);
                        break;
                    case "Celular":
                        String so = ((JTextField) panelCamposEspecificos.getComponent(1)).getText();
                        int armazenamentoCel = Integer.parseInt(((JTextField) panelCamposEspecificos.getComponent(3)).getText());
                        double tela = Double.parseDouble(((JTextField) panelCamposEspecificos.getComponent(5)).getText());
                        novoProduto = new Celular(codigo, nome, marca, preco, quantidade, so, armazenamentoCel, tela);
                        break;
                    case "Computador":
                        String processador = ((JTextField) panelCamposEspecificos.getComponent(1)).getText();
                        int ram = Integer.parseInt(((JTextField) panelCamposEspecificos.getComponent(3)).getText());
                        int armazenamentoPc = Integer.parseInt(((JTextField) panelCamposEspecificos.getComponent(5)).getText());
                        novoProduto = new Computador(codigo, nome, marca, preco, quantidade, processador, ram, armazenamentoPc);
                        break;
                    case "Vestuário":
                        String tamanho = ((JTextField) panelCamposEspecificos.getComponent(1)).getText();
                        String cor = ((JTextField) panelCamposEspecificos.getComponent(3)).getText();
                        String materialVest = ((JTextField) panelCamposEspecificos.getComponent(5)).getText();
                        novoProduto = new Vestuario(codigo, nome, marca, preco, quantidade, tamanho, cor, materialVest);
                        break;
                }
                
                if (novoProduto != null) {
                    estoque.adicionarProduto(novoProduto);
                    atualizarTabelaProdutos();
                    dialog.dispose();
                    JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro: Verifique se todos os campos numéricos estão preenchidos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panel, BorderLayout.NORTH);
        panelPrincipal.add(panelCamposEspecificos, BorderLayout.CENTER);
        panelPrincipal.add(btnSalvar, BorderLayout.SOUTH);
        
        dialog.add(panelPrincipal);
        dialog.setVisible(true);
    }
    
    private void mostrarDialogoAtualizarEstoque() {
        JDialog dialog = new JDialog(this, "Atualizar Estoque", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        JTextField txtCodigo = new JTextField();
        JTextField txtQuantidade = new JTextField();
        
        panel.add(new JLabel("Código do Produto:"));
        panel.add(txtCodigo);
        panel.add(new JLabel("Nova Quantidade:"));
        panel.add(txtQuantidade);
        
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> {
            try {
                String codigo = txtCodigo.getText();
                int quantidade = Integer.parseInt(txtQuantidade.getText());
                
                if (estoque.atualizarEstoque(codigo, quantidade)) {
                    JOptionPane.showMessageDialog(this, "Estoque atualizado com sucesso!");
                    atualizarTabelaProdutos();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(btnAtualizar);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new LojaGUI().setVisible(true);
        });
    }
}