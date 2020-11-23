package org.example.dao;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.example.model.Auction;
import org.example.model.Buyer;

public final class RegisteredBuyerDao {

    private static final Set<Buyer> registeredBuyers;

    static {
        registeredBuyers = new HashSet<>();
        registeredBuyers.add(new Buyer("registeredBuyer1"));
        registeredBuyers.add(new Buyer("registeredBuyer2"));
        registeredBuyers.add(new Buyer("registeredBuyer3"));
    }

    public static Optional<Buyer> getRegisteredBuyer(
        final String buyerName) {
        return registeredBuyers.stream()
            .filter(buyer -> buyerName.equals(buyer.getName()))
            .findAny();
    }

    public static Buyer registeredBuyer(
        final Buyer buyer) {
        registeredBuyers.add(buyer);
        return buyer;
    }
}
