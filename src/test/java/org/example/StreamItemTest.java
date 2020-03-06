package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StreamItemTest {


    @Test
    public void shouldCreateStream() {
        StreamItem streamItem = StreamItem.of("test/01_input.json");
        assertNotNull(streamItem);
    }

    @Test
    public void shouldPeek() {
        StreamItem stream = StreamItem.of("test/01_input.json");
        Item peek = stream.peek();
        assertEquals(peek, stream.peek());
        assertEquals(3, stream.size());
    }

    @Test
    public void shouldNext() {
        StreamItem stream = StreamItem.of("test/01_input.json");
        Item peek = stream.next();
        assertNotEquals(peek, stream.next());
        assertEquals(1, stream.size());
    }

    @Test
    public void shouldConsume() {
        StreamItem stream = StreamItem.of("test/01_input.json");
        String log = stream.consume();
        assertNotNull(log);
    }

    @Test
    public void shouldConsume2() {
        StreamItem stream = StreamItem.of("test/02_input.json");
        String log = stream.consume();
        assertNotNull(log);
    }


}