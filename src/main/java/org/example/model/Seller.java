package org.example.model;

import java.util.Objects;

public class Seller {

    private final String name;

    public Seller(
        final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Seller seller = (Seller) o;
        return name.equals(seller.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
