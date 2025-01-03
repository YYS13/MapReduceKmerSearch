package suffix.search;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class KSSMapper extends Mapper<LongWritable, Text, Text, Text> {
    private int kmer = 3;
    private Text outK = new Text();
    private Text outV = new Text();
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String sequence = value.toString();
        for(int i = 0; i <= sequence.length() - kmer; i++){
            outK.set(sequence.substring(i, i+1));
            outV.set(sequence.substring(i, i+kmer));
            context.write(outK, outV);
        }
    }
}
