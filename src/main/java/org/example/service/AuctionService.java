package org.example.service;

import org.example.model.Auction;

public class AuctionService {

    public AuctionService() {
    }

    void removeAuction(
        final Auction registeredAction) {
        registeredAction.deactivate();

        // RegisteredBidDao.removeAuction(registeredAction);
    }
}