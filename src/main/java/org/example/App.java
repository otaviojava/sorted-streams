package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        StreamItem stream = StreamItem.of(args);
        System.out.println(stream.consume());
    }
}
