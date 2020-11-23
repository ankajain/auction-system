package org.example.dao;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.example.model.Auction;
import org.example.model.Seller;

public final class AuctionDao {

    private static final Set<Auction> auctions;

    static {
        auctions = new HashSet<>();
        final Seller registeredSeller = RegisteredSellerDao.getRegisteredSeller("registeredSeller1")
            .get();
        auctions.add(new Auction("registeredAuction1", 10, 20, 1, registeredSeller));
        auctions.add(new Auction("registeredAuction2", 50, 90, 1, registeredSeller));
    }

    public static Optional<Auction> getAuction(
        final String auctionName) {
        return auctions.stream()
            .filter(auction -> auctionName.equals(auction.getName()))
            .findAny();
    }
    public static Optional<Auction> getActiveAuction(
            final String auctionName) {
        return auctions.stream()
                .filter(auction -> auctionName.equals(auction.getName()))
                .filter(Auction::isActive)
                .findAny();
    }


    public static Auction addAuction(
        final Auction auction) {
        auctions.add(auction);
        return auction;
    }

    public static Optional<Auction> getAuctionForSeller(
        final Seller seller,
        final String auctionName) {
        return auctions.stream()
            .filter(auction -> seller.equals(auction.getSeller()))
            .filter(auction -> auctionName.equals(auction.getName()))
            .findAny();
    }

    public static void removeAuction(
            final Auction auction) {
         auctions.remove(auction);
    }
}
