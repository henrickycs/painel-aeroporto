//Árvore binária de busca que armazena números de voos e seus status, 
//permitindo a busca e atualização eficiente do status de um voo.
class BinaryTree {
    TreeNode root;

    public void inserir(int key, String value) {
        root = inserirRec(root, key, value);
    }

    private TreeNode inserirRec(TreeNode root, int key, String value) {
        if (root == null) {
            return new TreeNode(key, value);
        }

        if (key < root.key) {
            root.left = inserirRec(root.left, key, value);
        } else if (key > root.key) {
            root.right = inserirRec(root.right, key, value);
        } else {
            root.value = value;
        }

        return root;
    }

    public TreeNode buscar(int key) {
        return buscarRec(root, key);
    }

    private TreeNode buscarRec(TreeNode root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (key < root.key) {
            return buscarRec(root.left, key);
        }

        return buscarRec(root.right, key);
    }
    
    public void atualizarStatus(int key, String novoStatus) {
        TreeNode vooNode = buscar(key);

        if (vooNode != null) {
            vooNode.value = novoStatus;
        }
    }
}