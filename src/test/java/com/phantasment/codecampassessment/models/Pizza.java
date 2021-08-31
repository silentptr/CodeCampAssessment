package com.phantasment.codecampassessment.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

public class Pizza extends MenuItem
{
    private WebElement webElement;
    private String name;
    private String description;
    private int stars;
    private float kilojoules;
    private BigDecimal price;

    public Pizza(WebElement webElement)
    {
        this.webElement = webElement;
        name = webElement.findElement(By.className("name")).getText();
        description = webElement.findElement(By.className("description")).getText();
        stars = 0;

        for (WebElement starButton : webElement.findElement(By.className("v-rating")).findElements(By.tagName("button")))
        {
            if (starButton.getText().equals("star"))
            {
                ++stars;
            }
        }

        String kilojoulesStr = webElement.findElement(By.className("kilojoules")).getText();
        kilojoules = Float.parseFloat(kilojoulesStr.substring(0, kilojoulesStr.length() - 3));
        price = new BigDecimal(webElement.findElement(By.className("price")).getText().replace("$", ""));
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getStars()
    {
        return stars;
    }

    public float getKilojoules()
    {
        return kilojoules;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void order()
    {
        webElement.findElement(By.cssSelector("button[aria-label='Add to order']")).click();
    }
}
