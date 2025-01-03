package suffix.search;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class KSSPartitioner extends Partitioner<Text, Text> {
    @Override
    public int getPartition(Text key, Text subString, int numPartitions) {
        char base = (char)key.charAt(0);
        int partition;
        if(base == 'A'){
            partition = 0;
        } else if (base == 'C') {
            partition = 1;
        } else if (base == 'G') {
            partition = 2;
        }else{
            partition = 3;
        }

        return partition;
    }
}
