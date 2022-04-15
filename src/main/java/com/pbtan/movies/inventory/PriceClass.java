package com.pbtan.movies.inventory;

import java.math.BigDecimal;

enum PriceClass {
    NEW(new BigDecimal("5"), 0L),
    REGULAR(new BigDecimal("3.49"), 52L),
    OLD(new BigDecimal("1.99"), 156L);

    private BigDecimal price;
    private long weeksFromRelease;

    /**
     * Movie price class that divides movies into 3 price categories by release date.
     *
     * Constants with bigger releasedAfterWeeks are top level.
     * E.g. movies released 55 weeks ago are considered regular not new.
     *
     * @param price of movie in that category.
     * @param weeksFromRelease - weeks passed after movie was released (not including).
     */
    PriceClass(BigDecimal price, long weeksFromRelease) {
        this.price = price;
        this.weeksFromRelease = weeksFromRelease;
    }

    public long getWeeksFromRelease() {
        return weeksFromRelease;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
