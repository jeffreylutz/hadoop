package hw08;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class PiCalc extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new PiCalc(), args);
        System.exit(res);
    }

    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        Job job = new Job(conf, "PiCalc");
        job.setJarByClass(PiCalc.class);
        Path in = new Path(args[0]);
        Path out = new Path(args[1]);
        FileInputFormat.setInputPaths(job, in);
        FileOutputFormat.setOutputPath(job, out);
        job.setJobName("PiCalc");

//        ChainMapper.addMapper(job, );


        job.setMapperClass(MapClass.class);
        job.setReducerClass(Reduce.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(LongWritable.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(1);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
        return 0;
    }

    public static class MapClass extends Mapper<Object, Text, LongWritable, LongWritable> {
        private LongWritable insideCircle = new LongWritable();
        private LongWritable insideSquare = new LongWritable();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            int ITERATIONS = 10000000;
            long inCircle = 0;
            long n = 0;

            double x, y;

            for (int i = 0; i < ITERATIONS; i++) {
                x = Math.random();
                y = Math.random();
                n++;
                if (x * x + y * y <= 1.0D) {
                    inCircle++;
                }
            }

            insideCircle.set(inCircle);
            insideSquare.set(n);

            context.write(insideSquare, insideCircle);
        }
    }

    public static class Reduce extends Reducer<LongWritable, LongWritable, Text, DoubleWritable> {

        private long sumCircle, sumSquare;

        @Override
        protected void reduce(LongWritable inSquare, Iterable<LongWritable> inCircles, Context context) throws IOException, InterruptedException {
            for (LongWritable inCircle : inCircles) {
                sumSquare = sumSquare + inSquare.get();
                sumCircle = sumCircle + inCircle.get();
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);    //To change body of overridden methods use File | Settings | File Templates.

            double pi = (4.0D * sumCircle) / sumSquare;
            context.write(new Text("Pi: "), new DoubleWritable(pi));
        }

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }
}
