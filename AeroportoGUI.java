import javax.swing.*;
import java.awt.*;

public class AeroportoGUI extends JFrame {
    private PainelAeroporto painelAeroporto;
    private DadosGUI dadosGUI;

    private JTextField vooField;
    private JTextField destinoField;
    private JTextField portaoField;
    private JTextField partidaField;
    private JTextField statusField;

    public AeroportoGUI() {
        super("Painel de Aeroporto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        setResizable(false); //Desabilita resizable

        painelAeroporto = new PainelAeroporto();
        new Thread(() -> painelAeroporto.verificarExcluirVoos()).start();
        dadosGUI = new DadosGUI(painelAeroporto);

        // Componentes da interface gráfica
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());

        vooField = new JTextField(20);
        destinoField = new JTextField(20);
        portaoField = new JTextField(20);
        partidaField = new JTextField(20);
        statusField = new JTextField(20);

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton atualizarButton = new JButton("Atualizar");
        JButton removerButton = new JButton("Remover");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Numero do Voo:"), gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Destino:"), gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Portao:"), gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Partida (HH:mm):"), gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Status:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(vooField, gbc);
        gbc.gridy++;
        inputPanel.add(destinoField, gbc);
        gbc.gridy++;
        inputPanel.add(portaoField, gbc);
        gbc.gridy++;
        inputPanel.add(partidaField, gbc);
        gbc.gridy++;
        inputPanel.add(statusField, gbc);

        mainPanel.add(inputPanel, BorderLayout.WEST);

        // Adicionando botões ao painel principal
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(atualizarButton);
        buttonPanel.add(removerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adicionando ação ao botão "Cadastrar Voo"
        cadastrarButton.addActionListener(e -> cadastrarVoo());

        // Adicionando ação ao botão "Atualizar Status"
        atualizarButton.addActionListener(e -> atualizarStatusVoo());

        // Adicionando ação ao botão "Remover Voo"
        removerButton.addActionListener(e -> removerVoo());

        // Iniciar a exibição automática
        iniciarExibicaoAutomatica();

        add(mainPanel);
    }

    private void cadastrarVoo() {
        try {
            int numero = Integer.parseInt(vooField.getText());
            String destino = destinoField.getText();
            String portao = portaoField.getText();
            String partida = partidaField.getText();
            String status = statusField.getText();

            painelAeroporto.cadastrarVoo(numero, destino, portao, partida, status);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de voo inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarStatusVoo() {
        try {
            int numero = Integer.parseInt(vooField.getText());
            String novoStatus = statusField.getText();
            String novoPortao = portaoField.getText();

            if(!novoPortao.isEmpty()){
                painelAeroporto.alterarPortao(numero, novoPortao);
            }
            if(!novoStatus.isEmpty()){
                painelAeroporto.atualizarStatusVoo(numero, novoStatus);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de voo inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerVoo() {
        try {
            int numero = Integer.parseInt(vooField.getText());
            painelAeroporto.removerVoo(numero);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de voo inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciarExibicaoAutomatica() {
        Timer timer = new Timer(1000, e -> dadosGUI.atualizarDisplay());
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AeroportoGUI aeroportoGUI = new AeroportoGUI();
            aeroportoGUI.setVisible(true);
            aeroportoGUI.dadosGUI.setVisible(true);
        });
    }
}
