package org.example.service;

import org.example.exception.InvalidAuction;
import org.example.exception.SellerNotRegistered;
import org.example.model.Auction;
import org.example.model.Seller;
import org.junit.Assert;
import org.junit.Test;

public class SellerServiceTest {

    /*@Test(expected = SellerNotRegistered.class)
    public void closeAuction_SellerNotRegistered() {
        new SellerService().closeAuction(new Seller(""), new Auction(""));
    }

    @Test(expected = InvalidAuction.class)
    public void closeAuction_InvalidAuction() {
        new SellerService().closeAuction(new Seller("registeredSeller1"), new Auction(""));
    }

    @Test
    public void closeAuction_RegisteredBuyer() {
        Assert.assertEquals("registeredBuyer2", new SellerService().closeAuction(new Seller("registeredSeller1"), new Auction("registeredAuction1")));
    }

    @Test(expected = InvalidAuction.class)
    public void closeAuction_RegisteredBuyerDuplicateAuctionClosing() {
        final SellerService sellerService = new SellerService();
        final String closingBuyer1 = sellerService.closeAuction(new Seller("registeredSeller1"), new Auction("registeredAuction1"));
        final String closingBuyer2 = sellerService.closeAuction(new Seller("registeredSeller1"), new Auction("registeredAuction1"));
        Assert.assertEquals("registeredBuyer2", closingBuyer2);
    }

     @Test
     public void getProfit() {
         final SellerService sellerService = new SellerService();
         sellerService.getProfit(new Seller(""), new Auction(""));
     }*/
}