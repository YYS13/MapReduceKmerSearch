package suffix.search;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class KSSReducer extends Reducer<Text, Text, Text, TreeNode> {
    private TreeNode suffixTree = new TreeNode();
    private Text outKey = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, TreeNode>.Context context) throws IOException, InterruptedException {
        outKey.set(key.toString());
        suffixTree.val = (char)key.charAt(0);
        TreeNode temp = suffixTree;
        for(Text seq: values){
            String sequence = seq.toString();
            for(int i = 1; i < sequence.length(); i++){
                char base = sequence.charAt(i);
                int idx;
                if(base == 'A'){
                    idx = 0;
                } else if (base == 'C') {
                    idx = 1;
                } else if (base == 'G') {
                    idx = 2;
                }else{
                    idx = 3;
                }

                if(temp.children[idx] != null){
                    temp = temp.children[idx];
                }else{
                    TreeNode newNode = new TreeNode();
                    newNode.val = base;
                    temp.children[idx] = newNode;
                    temp = newNode;
                }
            }
            temp.count++;
            temp = suffixTree;
        }
        context.write(outKey, suffixTree);
    }
}
