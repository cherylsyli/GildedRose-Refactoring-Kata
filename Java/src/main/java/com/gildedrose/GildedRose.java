package com.gildedrose;

/*
	- All items have a SellIn value which denotes the number of days we have to sell the item
	- All items have a Quality value which denotes how valuable the item is
	- At the end of each day our system lowers both values for every item

Pretty simple, right? Well this is where it gets interesting:

	- Once the sell by date has passed, Quality degrades twice as fast
	- The Quality of an item is never negative
	- "Aged Brie" actually increases in Quality the older it gets
	- The Quality of an item is never more than 50
	- "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
	- "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
	Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
	Quality drops to 0 after the concert

We have recently signed a supplier of conjured items. This requires an update to our system:

	- "Conjured" items degrade in Quality twice as fast as normal items
 */

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private static int MAX_QUALITY_VALUE = 50;

    private boolean isLegendary(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");   // or contains("Sulfuras")?
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePasses(Item item) {
       return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isConjured(Item item) {
        return item.name.equals("Conjured");
    }

    private void decreaseQuality(Item item, int amount) {
       int value = item.quality - amount;
       if (value > 0) {
           item.quality = value;
       } else {
           item.quality = 0;
       }
    }

    private void increaseQuality(Item item, int amount) {
        int value = item.quality + amount;

        if (value > MAX_QUALITY_VALUE) {
            item.quality = MAX_QUALITY_VALUE;
        } else {
            item.quality = value;
        }
    }

    private void decrementDaysRemaining(Item item) {
        item.sellIn--;
    }

    private void adjustDailyValues(Item item) {
        if (isConjured(item)) {
           decreaseQuality(item, 2);
        } else {
           decreaseQuality(item, 1);   // except Sulfuras though?
        }
        decrementDaysRemaining(item);
    }


    public void updateQuality() {

        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            // 	- "Aged Brie" actually increases in Quality the older it gets
            // sell by date has passed, quality degrades twice as fast
            if (!isLegendary(item)) {
                // - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality

                // 	Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
                //  Quality drops to 0 after the concert

                if (isConjured(item)) {
                   decreaseQuality(item, 1);
                } else if (isAgedBrie(item) || isBackstagePasses(item)) {
                    if (item.sellIn <= 0) {  // = 0 ?
                        item.quality = 0;
                    } else if (item.sellIn <= 5) {
                        increaseQuality(item, 3);
                    } else if (item.sellIn <= 10) {
                        increaseQuality(item, 2);
                    }
                } else if (isAgedBrie(item)) {
                    increaseQuality(item, 1); // by 1 only?
                } else if (item.sellIn < 0) {
                    decreaseQuality(item, 2);
                }

                adjustDailyValues(item);

            }
//            if (!isAgedBrie(item)
//                    && !isBackstagePasses(item)) {
//                if (item.quality > 0) {
//                    if (isLegendary(item)) {
//                        decreaseQuality(item, 1);
//                    }
//                }
//            } else {
//                if (item.quality < 50) {
//                    incrementQuality(item);
//
//                    if (!isBackstagePasses(item)) {
//                        if (item.sellIn < 11) {
//                            if (item.quality < 50) {
//                                incrementQuality(item);
//                            }
//                        }
//
//                        if (item.sellIn < 6) {
//                            if (item.quality < 50) {
//                                incrementQuality(item);
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (isLegendary(item)) {
//                decrementDaysRemaining(item);
//            }
//
//            if (item.sellIn < 0) {
//                if (!item.name.equals("Aged Brie")) {
//                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                        if (item.quality > 0) {
//                            if (!isLegendary(item)) {
//                                item.quality = item.quality - 1;
//                            }
//                        }
//                    } else {
//                        decrementQuality(item);
//                    }
//                } else {
//                    if (item.quality < 50) {
//                        incrementQuality(item);
//                    }
//                }
//            }
//
//            adjustDailyValues(item);
        }
    }
}