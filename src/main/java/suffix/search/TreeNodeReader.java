package suffix.search;

import java.io.DataInputStream;
import java.io.IOException;

public class TreeNodeReader {
    public TreeNodeReader(){}

    public TreeNode readTree(DataInputStream inputStream) throws IOException {
        TreeNode subTree = new TreeNode();
        subTree.readFields(inputStream);
        return subTree;
    }

    public void printTree(TreeNode tree){
        if(tree != null){
            System.out.print(tree.val + " ");
            printTree(tree.children[0]);
            printTree(tree.children[1]);
            printTree(tree.children[2]);
            printTree(tree.children[3]);
        }
    }
}
