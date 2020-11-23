package org.example.model;

public class Bid {

    private final Buyer buyer;
    private final Auction auction;
    private Double amount;

    public Bid(
        final Buyer buyer,
        final Auction auction,
        final double amount) {
        this.buyer = buyer;
        this.auction = auction;
        this.amount = amount;

    }

    public Double getAmount() {
        return amount;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAmount(
        final double amount) {
        this.amount = amount;
    }
}
