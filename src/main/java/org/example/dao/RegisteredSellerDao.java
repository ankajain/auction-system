package org.example.dao;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.example.model.Seller;

public final class RegisteredSellerDao {

    private static final Set<Seller> registeredSellers;

    static {
        registeredSellers = new HashSet<>();
        registeredSellers.add(new Seller("registeredSeller1"));
    }

    public static Optional<Seller> getRegisteredSeller(
        final String sellerName) {
        return registeredSellers.stream()
            .filter(seller -> sellerName.equals(seller.getName()))
            .findAny();
    }

    public static Seller registeredSeller(
        final Seller seller) {
        registeredSellers.add(seller);
        return seller;

    }
}
