import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Vector;

public class DadosGUI extends JFrame {
    private PainelAeroporto painelAeroporto;
    private JTable tabela;

    public DadosGUI(PainelAeroporto painelAeroporto) {
        super("Exibicao de Dados");
        this.painelAeroporto = painelAeroporto;

        setResizable(false); //Desabilita resizable
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 400);
        
        // Centralizar no meio da tela
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        // Criando a tabela
        tabela = new JTable();
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desativar o ajuste automático das colunas

        // Adicionando a tabela ao JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER); // Adicionando o JScrollPane ao painel central

        // Inicialmente, atualizar a exibição
        atualizarDisplay();
    }

    public void atualizarDisplay() {
        Vector<String> colunas = new Vector<>();
        colunas.add("Nº");
        colunas.add("Destino");
        colunas.add("Portao");
        colunas.add("Partida");
        colunas.add("Status");

        Vector<Vector<String>> dados = new Vector<>();
        Voo current = painelAeroporto.head;
        while (current != null) {
            Vector<String> linha = new Vector<>();
            linha.add(String.valueOf(current.numero));
            linha.add(current.destino);
            linha.add(current.portao);
            linha.add(current.partida);
            linha.add(current.status);
            dados.add(linha);
            current = current.next;
        }
        

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        tabela.setModel(model);
    
        // Configurar tamanho das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50); // Voo
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150); // Destino
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50); // Portao
        tabela.getColumnModel().getColumn(3).setPreferredWidth(80); // Partida
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100); // Status
    
        // Centralizar o texto nas células
        DefaultTableCellRenderer statusRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Acessando o valor da coluna "Status" na linha atual
                String status = (String) table.getValueAt(row, 4);

                // Configurar as cores com base na variável "status"
                if ("Atrasado".equals(status)) {
                    cellComponent.setBackground(Color.YELLOW);
                    cellComponent.setForeground(Color.BLACK);
                } else if ("Embarque".equals(status)) {
                    cellComponent.setBackground(Color.BLUE);
                    cellComponent.setForeground(Color.BLACK);
                }  else if ("Cancelado".equals(status)) {
                    cellComponent.setBackground(Color.RED);
                    cellComponent.setForeground(Color.BLACK);
                } else if ("Decolando".equals(status)) {
                    cellComponent.setBackground(Color.GREEN);
                    cellComponent.setForeground(Color.BLACK);
                }
                else {
                    // Configuração padrão para outras situações
                    cellComponent.setBackground(table.getBackground());
                    cellComponent.setForeground(table.getForeground());
                }

                return cellComponent;
            }
        };

        // Aplicar o renderizador personalizado a todas as colunas
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(statusRenderer);
        }
    }
}
