package com.phantasment.codecampassessment.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order
{
    private ArrayList<OrderItem> items;

    public Order(ArrayList<OrderItem> items)
    {
        this.items = items;
    }

    public List<OrderItem> getItems()
    {
        return Collections.unmodifiableList(items);
    }
}
