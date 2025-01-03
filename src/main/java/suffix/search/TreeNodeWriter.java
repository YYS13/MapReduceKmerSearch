package suffix.search;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class TreeNodeWriter extends RecordWriter<Text, TreeNode> {

    private FSDataOutputStream outStream;
    private FileSystem fs;
    public TreeNodeWriter(TaskAttemptContext taskAttemptContext){
        // create stream
        try {
            fs = FileSystem.get(taskAttemptContext.getConfiguration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void write(Text text, TreeNode treeNode) throws IOException, InterruptedException {
        String outPath = "/SuffixTree/output/" + text.toString() + ".tree";
        outStream = fs.create(new Path(outPath));

        treeNode.write(outStream);
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStream(outStream);
    }
}
