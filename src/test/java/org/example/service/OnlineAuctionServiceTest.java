package org.example.service;

import org.example.exception.BuyerNotRegistered;
import org.example.exception.InvalidAuction;
import org.example.exception.NotValidAmountForAuction;
import org.example.exception.SellerNotRegistered;
import org.example.model.Bid;
import org.example.model.Buyer;
import org.example.model.Seller;
import org.junit.Assert;
import org.junit.Test;

public class OnlineAuctionServiceTest {

    private final OnlineAuctionService onlineAuctionService = new OnlineAuctionService();

    @Test
    public void test_newBuyer() {
        final String buyer1Name = "buyer1";
        Assert.assertEquals("Buyer Added " + buyer1Name, buyer1Name, onlineAuctionService.addBuyer(buyer1Name)
            .getName());
        Assert.assertEquals("Buyer Added " + buyer1Name, buyer1Name, onlineAuctionService.addBuyer(buyer1Name)
                .getName());
        final String buyer2Name = "buyer2";
        Assert.assertEquals("Buyer Added " + buyer2Name, buyer2Name, onlineAuctionService.addBuyer(buyer2Name)
            .getName());
        final String buyer3Name = "buyer3";
        Assert.assertEquals("Buyer Added " + buyer3Name, buyer3Name, onlineAuctionService.addBuyer(buyer3Name)
            .getName());
    }

    @Test
    public void integratedtest()
    {
        onlineAuctionService.addBuyer("B1");
        onlineAuctionService.addBuyer("B2");
        onlineAuctionService.addBuyer("B3");
        onlineAuctionService.addSeller("S1");
        onlineAuctionService.createAuction("A1", 10d,50d,1,"S1");
        onlineAuctionService.createBid("B1", "A1",50d);
        onlineAuctionService.createBid("B2", "A1",15);
        onlineAuctionService.updateBid("B2", "A1",19);
        onlineAuctionService.createBid("B3", "A1",19d);
        final String s = onlineAuctionService.closeAuction("S1", "A1");

    }

    @Test
    public void test_newSeller() {

        final String seller1Name = "seller1Name";
        Assert.assertEquals("Buyer Added " + seller1Name, seller1Name, onlineAuctionService.addSeller(seller1Name)
            .getName());
        final String seller2Name = "seller2";
        Assert.assertEquals("Buyer Added " + seller2Name, seller2Name, onlineAuctionService.addSeller(seller2Name)
            .getName());
        final String seller3Name = "seller3";
        Assert.assertEquals("Buyer Added " + seller3Name, seller3Name, onlineAuctionService.addSeller(seller3Name)
            .getName());
    }

    @Test
    public void test_createAuction_RegisteredSeller() {

        final String sellerName = "registeredSeller1";

        final String auctionName = "A1";
        Assert.assertEquals("Auction Added " + auctionName, auctionName, onlineAuctionService.createAuction(auctionName, 10, 50, 1, sellerName)
            .getName());
    }

    @Test(expected = SellerNotRegistered.class)
    public void test_createAuction_UnRegisteredSeller() {
        final String sellerName = "seller4";
        final String auctionName = "A1";
        onlineAuctionService.createAuction(auctionName, 10, 50, 1, sellerName)
            .getName();

    }

    @Test
    public void test_createBid_RegisteredBuyer() {
        final String buyerName = "registeredBuyer1";
        final String auctionName = "registeredAuction1";
        final double amount = 17d;
        final Bid bid = onlineAuctionService.createBid(buyerName, auctionName, amount);
        Assert.assertEquals("Bid Added for auction " + auctionName + "by buyer " + buyerName + " ", buyerName, bid.getBuyer()
            .getName());

    }

    @Test(expected = InvalidAuction.class)
    public void test_createBid_RegisteredWrongAuctionBuyer() {
        final String buyerName = "registeredBuyer1";
        final String auctionName = "auction1";
        final double amount = 17d;
        final Bid bid = onlineAuctionService.createBid(buyerName, auctionName, amount);
        Assert.assertEquals("Bid Added for auction " + auctionName + "by buyer " + buyerName + " ", buyerName, bid.getBuyer()
            .getName());

    }

    @Test(expected = BuyerNotRegistered.class)
    public void test_createBid_UnRegisteredBuyer() {
        final String buyerName = "buyer10";
        final String auctionName = "A1";
        final double amount = 17d;
        onlineAuctionService.createBid(buyerName, auctionName, amount);
    }

    @Test(expected = BuyerNotRegistered.class)
    public void test_updateBid_UnRegisteredBuyer() {
        final String buyerName = "buyer10";
        final String auctionName = "A1";
        final double amount = 17d;
        onlineAuctionService.updateBid(buyerName, auctionName, amount);
    }

    @Test(expected = InvalidAuction.class)
    public void test_updateBid_RegisteredWrongAuctionBuyer() {
        final String buyerName = "registeredBuyer1";
        final String auctionName = "auction1";
        final double amount = 17d;
        onlineAuctionService.updateBid(buyerName, auctionName, amount);
    }

    @Test(expected = NotValidAmountForAuction.class)
    public void test_updateBid_RegisteredAuction_InvalidAmount() {
        final String buyerName = "registeredBuyer1";
        final String auctionName = "registeredAuction1";
        final double amount = 23d;
        onlineAuctionService.updateBid(buyerName, auctionName, amount);
    }

    @Test
    public void test_updateBidSucess() {
        final String buyerName = "registeredBuyer1";
        final String auctionName = "registeredAuction1";
        final double amount = 17d;
        final Bid bid = onlineAuctionService.updateBid(buyerName, auctionName, amount);
        Assert.assertEquals("Bid Updated " + auctionName + "by buyer " + buyerName + " ", buyerName, bid.getBuyer()
            .getName());

    }

    @Test
    public void test_withdrawBidSucess() {
        final String buyerName = "registeredBuyer1";
        final String auctionName = "registeredAuction1";
        final Bid bid = onlineAuctionService.withdrawBid(buyerName, auctionName);
        Assert.assertEquals("Bid Updated " + auctionName + "by buyer " + buyerName + " ", buyerName, bid.getBuyer()
            .getName());

    }

    @Test(expected = InvalidAuction.class)
    public void test_withdrawBid_RegisteredWrongAuctionBuyer() {
        final String buyerName = "registeredBuyer1";
        final String auctionName = "auction1";
        final Bid bid = onlineAuctionService.withdrawBid(buyerName, auctionName);
        Assert.assertEquals("Bid Updated " + auctionName + "by buyer " + buyerName + " ", buyerName, bid.getBuyer()
            .getName());

    }

    @Test(expected = BuyerNotRegistered.class)
    public void test_withdrawBid_UnRegisteredBuyer() {
        final String buyerName = "buyer10";
        final String auctionName = "A1";
        onlineAuctionService.withdrawBid(buyerName, auctionName);
    }
}