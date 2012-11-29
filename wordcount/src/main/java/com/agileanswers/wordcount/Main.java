package com.agileanswers.wordcount;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: Jeffrey M Lutz
 * Date: 11/26/12
 */
public class Main {
    public static void main(String[] arguments) {

        try {
            Class czz = Class.forName("com.agileanswers.wordcount.WordMapper");
            System.out.println("Successfully loaded WordMapper.class!");
        } catch (Throwable e) {
            e.printStackTrace();
        }

        ClassLoader cl = Main.class.getClassLoader();
        while (cl != null) {
            System.out.println("\n\nCL --> " + cl + "\n");
            cl = cl.getParent();
        }

        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml", WordMapper.class);
        ctx.registerShutdownHook();
    }
}