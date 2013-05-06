package hw08;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class InverterCounter2 extends Configured implements Tool {

    public static class MapClass extends MapReduceBase
            implements Mapper<Text, Text, Text, Text> {

        public void map(Text key, Text value,
                        OutputCollector<Text, Text> output,
                        Reporter reporter) throws IOException {

            output.collect(value, key);
        }
    }
    public static class Reduce extends MapReduceBase
            implements Reducer<Text, Text, Text, IntWritable> {

        public void reduce(Text key, Iterator<Text> values,
                           OutputCollector<Text, IntWritable> output,
                           Reporter reporter) throws IOException {

            int count = 0;
            while (values.hasNext()) {
                values.next();
                count++;
            }
            output.collect(key, new IntWritable(count));
        }
    }
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        JobConf job = new JobConf(conf, InverterCounter2.class);
        Path in = new Path(args[0]);
        Path out = new Path("outputHW08/prob4a");
        Path out2 = new Path(args[1]);
        FileInputFormat.setInputPaths(job, in);
        FileOutputFormat.setOutputPath(job, out);

        job.setJobName("InverterCounter2");
        job.setMapperClass(MapClass.class);
        job.setReducerClass(Reduce.class);
        job.setInputFormat(KeyValueTextInputFormat.class);
        job.setOutputFormat(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.set("key.value.separator.in.input.line", ",");
        JobClient.runJob(job);

        // After running the first map/reduce.
        // Time to configure and run a second map/reduce
        // Watch that we use output of first as input of second!
        Configuration conf2 = getConf();
        JobConf job2 = new JobConf(conf2, CitationHistogram.class);
        FileInputFormat.setInputPaths(job2, out);
        FileOutputFormat.setOutputPath(job2, out2);
        job2.setJobName("CitationHistogram");
        job2.setMapperClass(MapClass.class);
        job2.setReducerClass(Reduce.class);
        job2.setInputFormat(KeyValueTextInputFormat.class);
        job2.setOutputFormat(TextOutputFormat.class);
//        job2.setMap
        job2.setOutputKeyClass(IntWritable.class);
        job2.setOutputValueClass(IntWritable.class);
        JobClient.runJob(job2);


        return 0;
    }
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new InverterCounter2(), args);
        System.exit(res);
    }
}