package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Auction {

    private final Double participationCost;
    private final String name;
    private final Double minAmount;
    private final Double maxAmount;
    private final Seller seller;
    private final List<Bid> bids = new ArrayList<>();
    private boolean active;

    public Auction(
        final String auctionName,
        final double minAmount,
        final double maxAmount,
        final double participationCost,
        final Seller seller) {
        this.name = auctionName;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.participationCost = participationCost;
        this.seller = seller;
        this.active = true;
    }

    public Auction(
        final String auctionName) {
        this(auctionName, 0.0, 0.0, 0.0, null);
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public Seller getSeller() {
        return seller;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public String getName() {
        return this.name;
    }
}
