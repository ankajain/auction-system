package org.example.model;

import java.util.Objects;

public class Buyer {
    private final String name;
    public Buyer(final String name){
        this.name =  name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Buyer buyer = (Buyer) o;
        return Objects.equals(name, buyer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
