package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class Item implements Comparable<Item> {

    private final long timestamp;

    private final long value;

    @JsonCreator
    public Item(@JsonProperty("timestamp") long timestamp,
                @JsonProperty("value") long value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return timestamp == item.timestamp &&
                value == item.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, value);
    }

    @Override
    public String toString() {
        return "timestamp: " + timestamp + ", value: "+ value;
    }

    @Override
    public int compareTo(Item item) {
        return Long.compare(timestamp, item.timestamp);
    }
}
