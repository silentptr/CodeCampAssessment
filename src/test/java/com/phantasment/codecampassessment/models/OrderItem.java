package com.phantasment.codecampassessment.models;

import java.math.BigDecimal;

public class OrderItem
{
    private String itemName;
    private int quantity;
    private BigDecimal subtotal;

    public OrderItem(String itemName, int quantity, BigDecimal subtotal)
    {
        this.itemName = itemName;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public BigDecimal getSubtotal()
    {
        return subtotal;
    }
}
