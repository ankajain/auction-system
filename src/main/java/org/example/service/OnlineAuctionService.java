package org.example.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.dao.AuctionDao;
import org.example.dao.RegisteredBidDao;
import org.example.dao.RegisteredBuyerDao;
import org.example.dao.RegisteredSellerDao;
import org.example.exception.*;
import org.example.model.Auction;
import org.example.model.Bid;
import org.example.model.Buyer;
import org.example.model.Seller;

public class OnlineAuctionService {

    public Buyer addBuyer(
        final String name) {
        final Buyer buyer = new Buyer(name);
        return RegisteredBuyerDao.registeredBuyer(buyer);
    }

    public Seller addSeller(
        final String name) {
        final Seller seller = new Seller(name);
        return RegisteredSellerDao.registeredSeller(seller);
    }

    public Auction createAuction(
        final String auctionName,
        final double minAmount,
        final double maxAmount,
        final double participationCost,
        final String sellerName) {
        final Auction auction = RegisteredSellerDao.getRegisteredSeller(sellerName)
            .map(seller -> new Auction(auctionName, minAmount, maxAmount, participationCost, seller))
            .orElseThrow(SellerNotRegistered::new);
        AuctionDao.addAuction(auction);
        return auction;
    }

    public Bid createBid(
        final String buyerName,
        final String auctionName,
        final double amount) {
        final Buyer buyer = RegisteredBuyerDao.getRegisteredBuyer(buyerName)
            .orElseThrow(BuyerNotRegistered::new);
        final Auction auction = AuctionDao.getActiveAuction(auctionName)
            .orElseThrow(InvalidAuction::new);

        if (amount >= auction.getMinAmount() && amount <= auction.getMaxAmount()) {
            return RegisteredBidDao.registerBid(new Bid(buyer, auction, amount));
        } else {
            throw new NotValidAmountForAuction("Valid Amount between " + auction.getMinAmount() + " " + auction.getMaxAmount());
        }
    }

    public Bid updateBid(
        final String buyerName,
        final String auctionName,
        final double amount) {
        final Buyer buyer = RegisteredBuyerDao.getRegisteredBuyer(buyerName)
            .orElseThrow(BuyerNotRegistered::new);
        final Auction auction = AuctionDao.getActiveAuction(auctionName)
            .orElseThrow(InvalidAuction::new);

        if (amount >= auction.getMinAmount() && amount <= auction.getMaxAmount()) {
            final Bid bid = RegisteredBidDao.getBuyerBidForAuction(buyer, auction)
                .orElseThrow(BidNotFound::new);
            return RegisteredBidDao.updateBid(bid, amount);
        } else {
            throw new NotValidAmountForAuction("Valid Amount between " + auction.getMinAmount() + " " + auction.getMaxAmount());
        }

    }

    public Bid withdrawBid(
        final String buyerName,
        final String auctionName) {
        final Buyer buyer = RegisteredBuyerDao.getRegisteredBuyer(buyerName)
            .orElseThrow(BuyerNotRegistered::new);
        final Auction auction = AuctionDao.getActiveAuction(auctionName)
            .orElseThrow(InvalidAuction::new);
        final Bid bid = RegisteredBidDao.getBuyerBidForAuction(buyer, auction)
            .orElseThrow(BidNotFound::new);
        return RegisteredBidDao.withdrawBid(bid);
    }

    public String closeAuction(
        final String sellerName,
        final String auctionName) {
        final Seller registeredSeller = RegisteredSellerDao.getRegisteredSeller(sellerName)
            .orElseThrow(SellerNotRegistered::new);
        final Auction registeredAction = AuctionDao.getAuctionForSeller(registeredSeller, auctionName)
            .orElseThrow(InvalidAuction::new);
        final List<Bid> bids = RegisteredBidDao.getAuctionBuyers(registeredAction);
        final Optional<Bid> collect = bids.stream()
            .collect(Collectors.maxBy(Comparator.comparing(Bid::getAmount)));
        new AuctionService().removeAuction(registeredAction);
        return collect.map(Bid::getBuyer)
            .map(Buyer::getName)
            .orElse("No Winner");
    }

    public Double getProfit(
        final Seller seller,
        final Auction auction) {
        /*
         * final Seller registeredSeller = RegisteredSellerDao.getRegisteredSeller(seller.getName()) .orElseThrow(SellerNotRegistered::new);
         */
        /*
         * final Auction eligibleAuction = AuctionDao.getAuctionForSeller(registeredSeller, auction.getName()) .filter(Predicate.not(Auction::isActive))
         *//*
            * .orElseThrow(InvalidAuction::new); final List<Bid> bids = RegisteredBidDao.getAuctionBuyers(eligibleAuction);
            */
        /*
         * Bid bid= nbids BidAmount -> 100+2; for(int i =1; i< bids.size() ++i) { if(bid.getAmount()>bid) { } }
         */

        // final Optional<Bid> collect =
        // bids.stream().collect(Collectors.maxBy(Comparator.comparing(Bid::getAmount));
        // // winning auction price + participation cost-share(no_of_bidders * 0.2 * participation cost) - an average of the lowest and highest bid limits
        return 0d;
    }

}
