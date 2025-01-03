package suffix.search;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class SuffixTreeOutputFormat extends FileOutputFormat<Text, TreeNode> {

    @Override
    public RecordWriter<Text, TreeNode> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        TreeNodeWriter tnw = new TreeNodeWriter(taskAttemptContext);
        return tnw;
    }
}
