class TreeNode {
    int key; //numero do voo
    String value; //status
    TreeNode left, right;

    public TreeNode(int key, String value) {
        this.key = key;
        this.value = value;
        this.left = this.right = null;
    }
}