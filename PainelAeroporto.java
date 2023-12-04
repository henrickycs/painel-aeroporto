import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.time.LocalTime;

class PainelAeroporto extends JPanel{
    Voo head; //Inicio da lista encadeada
    BinaryTree binaryTree; //Armazenar numero do voo e status na arvore binária

    public PainelAeroporto(){
        this.head = null;
        this.binaryTree = new BinaryTree(); //instanciar a arvore
    }

    public void verificarExcluirVoos() {
        while (true) {
            Voo current = head;
            Voo previous = null;

            while (current != null) {
                // Obtenha o horário de partida convertendo a string para LocalTime
                //Formato em HH:mm
                LocalTime horarioPartida = LocalTime.parse(current.partida, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime horarioAtual = LocalTime.now();

                // Verificar se o voo está decolando ou cancelado e se já passaram 1 minuto após o horário de partida
                if ((current.status.equals("Decolando") || current.status.equals("Cancelado")) &&
                        horarioAtual.isAfter(horarioPartida.plusMinutes(1))) {
                    // O voo está no passado, então remova da lista encadeada
                    if (previous != null) {
                        previous.next = current.next;
                    } else {
                        head = current.next;
                    }
                } else if (!(current.status.equals("Decolando") || current.status.equals("Cancelado")) && 
                        horarioAtual.isAfter(horarioPartida.plusMinutes(2))) {
                        // Verificar se o voo não está decolando ou cancelado e já passou do horário de partida
                        // Atualizar o status para "Atrasado"
                        current.status = "Atrasado";
                }
                // O voo está no futuro ou não atende aos critérios, mantenha a referência
                previous = current;
                current = current.next;
            }

            try {
                // Aguarde 10 segundos antes de verificar novamente
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Adiciona um novo voo na lista encadeada, priorizando o horário de partida
    public void cadastrarVoo(int numero, String destino, String portao, String partida, String status) {
        // Verificar se o número do voo já existe antes de cadastrar
        if (buscarVooPorNumero(numero) != null) {
            System.out.println("Ja existe um voo com o numero " + numero);
            JOptionPane.showMessageDialog(this, "Já existe um voo com o numero" + numero, "Erro", JOptionPane.ERROR_MESSAGE);
            
            return;
        }

        Voo novoVoo = new Voo(numero, destino, portao, partida, status);

        // Inserir na árvore binária
        binaryTree.inserir(numero, status);

        if (head == null || partida.compareTo(head.partida) < 0) {
            novoVoo.next = head;
            head = novoVoo;
        } else {
            Voo current = head;
            Voo previous = null;
            
            while (current != null && partida.compareTo(current.partida) > 0) {
                previous = current;
                current = current.next;
            }

            if (previous != null) {
                previous.next = novoVoo;
            } else {
                head = novoVoo;
            }

            novoVoo.next = current;
        }
    }

    // Método auxiliar para buscar um voo por número na lista encadeada
    private Voo buscarVooPorNumero(int numero) {
        // Chamar o método buscar da árvore binária
        TreeNode vooNode = binaryTree.buscar(numero);
        
        if (vooNode != null) {
            return new Voo(vooNode.key, null, null, null, vooNode.value);
        }
    
        return null;
    }
    
    public void atualizarStatusVoo(int numero, String novoStatus) {
        Voo current = head;
        while (current != null) {
            if (current.numero == numero) {
                current.status = novoStatus;

                // Atualizar na árvore binária
                binaryTree.atualizarStatus(numero, novoStatus);
                return;
            }
            current = current.next;
        }
        System.out.println("Voo não encontrado com o numero " + numero);
        JOptionPane.showMessageDialog(this, "Número de voo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);

    }

    public void alterarPortao(int numero, String novoPortao){
        Voo current = head;
        while(current != null){
            if(current.numero == numero){
                current.portao = novoPortao;
                return;
            }
            current = current.next;
        }
        //System.out.println("Voo não encontrado com o numero " + numero);
    }

    public void removerVoo(int numero) {
        Voo current = head;
        Voo previous = null;
        while (current != null) {
            if (current.numero == numero) {
                if (previous != null) {
                    previous.next = current.next;
                } else {
                    head = current.next;
                }
                return;
            }
            previous = current;
            current = current.next;
        }
        JOptionPane.showMessageDialog(this, "Número de voo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    // public void exibirPainel() {
    //     Voo current = head;
    //     while (current != null) {
    //         System.out.println("Voo " + current.numero + " com Destino - " + current.destino + ", Portao - " + current.portao + ", Partida - " + current.partida + ", Status - " + current.status);
    //         current = current.next;
    //     }
    // }
}
