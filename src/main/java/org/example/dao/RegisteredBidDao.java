package org.example.dao;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.example.model.Auction;
import org.example.model.Bid;
import org.example.model.Buyer;

public final class RegisteredBidDao {

    private static final Set<Bid> registeredBids;
    private static final Map<Buyer, Integer> bidPerBuyer; //Most
//    private static final Map<Auction, Buyer> closedAuctions;

    static {
        registeredBids = new HashSet<>();
        bidPerBuyer = new HashMap<>();
        final Optional<Auction> registeredAuction = AuctionDao.getActiveAuction("registeredAuction1");

        final Optional<Buyer> buyer1 = RegisteredBuyerDao.getRegisteredBuyer("registeredBuyer1");
        registeredBids.add(new Bid(buyer1.get(), registeredAuction.get(), 30));
        bidPerBuyer.compute(buyer1.get(), (
            k,
            v) -> (v == null) ? 1 : v + 1);
        final Optional<Buyer> buyer2 = RegisteredBuyerDao.getRegisteredBuyer("registeredBuyer2");
        registeredBids.add(new Bid(buyer2.get(), registeredAuction.get(), 40));
        bidPerBuyer.compute(buyer2.get(), (
            k,
            v) -> (v == null) ? 1 : v + 1);
    }

    public static Optional<Bid> getBuyerBidForAuction(
        final Buyer buyer,
        final Auction auction) {
        return registeredBids.stream()
            .filter(bid -> buyer.equals(bid.getBuyer()))
            .filter(bid -> auction.equals(bid.getAuction()))
            .findAny();
    }

    public static List<Bid> getBuyerBids(
        final String buyerName) {
        return registeredBids.stream()
            .filter(bid -> buyerName.equals(bid.getBuyer()
                .getName()))
            .collect(Collectors.toList());

    }

    public static Bid registerBid(
        final Bid bid) {
        registeredBids.add(bid);
        bidPerBuyer.compute(bid.getBuyer(), (
            k,
            v) -> (v == null) ? 1 : v + 1);
        return bid;

    }

    public static Bid updateBid(
        final Bid bid,
        final Double amount) {
        deleteBid(bid);
        bid.setAmount(amount);
        return registerBid(bid);
    }

    private static void deleteBid(
        final Bid bid) {
        registeredBids.remove(bid);
    }

    public static Bid withdrawBid(
        final Bid bid) {
        deleteBid(bid);
        return bid;
    }

    public static List<Bid> getAuctionBuyers(
        final Auction auction) {
        return registeredBids.stream()
            .filter(bid -> auction.equals(bid.getAuction()))
            .collect(Collectors.toList());
    }

//    public static void removeAuction(
//        final Auction registeredAction) {
//        final Predicate<Bid> bidPredicate = bid -> registeredAction.equals(bid.getAuction());
//        final List<Bid> optionalBid = registeredBids.stream()
//            .filter(bidPredicate)
//            .collect(Collectors.toList());
////        if (optionalBid.isPresent()) {
////            final Bid bid = optionalBid.get();
////            closedAuctions.put(registeredAction, bid.getBuyer());
////            registeredBids.remove(bid);
//        }
//
//    }
}
