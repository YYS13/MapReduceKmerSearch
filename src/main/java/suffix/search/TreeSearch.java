package suffix.search;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URI;

public class TreeSearch {
    public static void main(String[] args) throws IOException, InterruptedException {
        Configuration conf = new Configuration();
        String rootPath = "hdfs://yangyo-System-Product-Name:8020";
        String user = "yangyo";
        FileSystem fs = FileSystem.get(URI.create(rootPath), conf, user);
        TreeNode suffixTree = new TreeNode();
        char[] bases = new char[]{'A', 'C', 'G', 'T'};
        TreeNodeReader tnr = new TreeNodeReader();
        for(int i = 0; i < bases.length; i++){
            String treePath = "/SuffixTree/output/" + bases[i] + ".tree";
            DataInputStream in = fs.open(new Path(treePath));
            TreeNode subTree = tnr.readTree(in);
            tnr.printTree(subTree);
            System.out.print("\n");
            suffixTree.children[i] = subTree;
        }
        tnr.printTree(suffixTree);
    }
}
