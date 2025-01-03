package suffix.search;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TreeNode implements Writable {
    public char val;
    public long count;
    public TreeNode[] children = new TreeNode[4];

    public TreeNode(){}
    public TreeNode(char val){
        this.val = val;
    }

    // Serialize Suffix Tree
    private void writeTree(DataOutput dataOutput, TreeNode[] children) throws IOException {
        for(TreeNode child: children){
            if(child == null){
                dataOutput.writeBoolean(false);
            }else{
                dataOutput.writeBoolean(true);
                child.write(dataOutput);
            }
        }
    }
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeChar(val);

        writeTree(dataOutput, this.children);
    }

    // Unserialize Suffix Tree
    private TreeNode[] readTree(DataInput dataInput) throws IOException {
        TreeNode[] children = new TreeNode[4];
        for(int i = 0; i < 4; i++){
            if(!dataInput.readBoolean()){
                children[i] = null;
            }else{
                children[i] = new TreeNode();
                children[i].readFields(dataInput);
            }
        }
        return children;
    }
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.val = dataInput.readChar();

        this.children = readTree(dataInput);
    }
}
