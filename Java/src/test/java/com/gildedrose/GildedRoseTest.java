package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 9, 0),
            new Item("Aged Brie", 5, 0),
            new Item("Aged Brie", 0, 3),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 0),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0),
            new Item("Sulfuras, Hand of Ragnaros", 80, 100),
            new Item("Conjured", 5, 50)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        for (int i=0; i<items.length; i++) {
            System.out.println(items[i]);
        }
        assertEquals("fixme", app.items[0].name);
    }

}
