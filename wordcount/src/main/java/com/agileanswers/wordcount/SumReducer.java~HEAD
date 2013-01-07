package com.agileanswers.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * User: Jeffrey M Lutz
 * Date: 11/26/12
 */
public class SumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    protected static final String TARGET_WORD = "Watson";

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int wordCount = 0;
        for (IntWritable value : values) {
            wordCount += value.get();
        }
        context.write(key, new IntWritable(wordCount));
    }
}
