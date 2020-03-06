package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StreamItem {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<List<List<Item>>> TYPE_REF = new TypeReference<List<List<Item>>>() {
    };
    private final Queue<Item> items;

    private StreamItem(List<Item> items) {
        this.items = new LinkedList<>(items);
    }

    public Item peek() {
        return items.peek();
    }

    public Item next() {
        return items.poll();
    }

    public int size() {
        return items.size();
    }

    public String consume() {
        StringJoiner log = new StringJoiner("\n");

        List<Long> total = new ArrayList<>();
        while (!items.isEmpty()) {

            for (int index = 0; index < 5; index++) {
                Item item = items.poll();
                if (item == null) {
                    break;
                }
                log.add(item.toString());
                total.add(item.getValue());
            }

            LongSummaryStatistics statistics = total.stream().mapToLong(l -> l).summaryStatistics();
            String summary = String.format("count: %d, sum: %d, average: %f",
                    statistics.getCount(),
                    statistics.getSum(),
                    statistics.getAverage());
            log.add(summary);
        }

        return log.toString();
    }

    @Override
    public String toString() {
        return "Stream{" + "items=" + items + '}';
    }

    public static StreamItem of(String file) {
        return of(new String[]{file});
    }

    public static StreamItem of(String[] files) {
        List<Item> items = Stream.of(files).map(StreamItem::getLists)
                .flatMap(List::stream)
                .flatMap(List::stream).sorted()
                .sorted().collect(Collectors.toList());
        return new StreamItem(items);
    }

    private static List<List<Item>> getLists(String file) {
        try {
            URL url = new URL("file:" + file);
            return MAPPER.readValue(url, TYPE_REF);
        } catch (IOException exp) {
            throw new RuntimeException("An error to load JSON file", exp);
        }
    }
}
