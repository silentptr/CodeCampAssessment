package com.phantasment.codecampassessment.ui;

import com.phantasment.codecampassessment.models.Drink;
import com.phantasment.codecampassessment.models.Order;
import com.phantasment.codecampassessment.models.OrderItem;
import com.phantasment.codecampassessment.models.Pizza;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuPage extends Page
{
    private WebDriver driver;

    public MenuPage(WebDriver driver)
    {
        this.driver = driver;
    }

    @Override
    public void gotoPage()
    {
        driver.findElement(By.cssSelector("a[aria-label='menu']")).click();
    }

    public void selectTab(String name)
    {
        for (WebElement tab : driver.findElements(By.cssSelector("div[role='tab']")))
        {
            if (tab.getText().toUpperCase().contains(name.toUpperCase()))
            {
                tab.click();
            }
        }

        new WebDriverWait(driver, 10L).until(ExpectedConditions.elementToBeClickable(By.className(name.toLowerCase() + "-tab")));
    }

    public WebElement getViewOrderButton()
    {
        return driver.findElement(By.cssSelector("a[aria-label='your order']"));
    }

    public ArrayList<Drink> getDrinks()
    {
        ArrayList<Drink> drinks = new ArrayList<>();

        for (WebElement drinkElement : driver.findElement(By.className("drinks-tab")).findElements(By.className("menuItem")))
        {
            drinks.add(new Drink(drinkElement));
        }

        return drinks;
    }

    public ArrayList<Pizza> getPizzas()
    {
        ArrayList<Pizza> pizzas = new ArrayList<>();

        for (WebElement pizzaElement : driver.findElement(By.className("pizzas-tab")).findElements(By.className("menuItem")))
        {
            pizzas.add(new Pizza(pizzaElement));
        }

        return pizzas;
    }

    public int getTotalItemsInCart()
    {
        return Integer.parseInt(driver.findElement(By.className("order-count")).getText());
    }

    public Order getOrder()
    {
        new WebDriverWait(driver, 5L).until(ExpectedConditions.visibilityOfElementLocated(By.className("order-items")));
        ArrayList<OrderItem> items = new ArrayList<>();

        for (WebElement row : driver.findElement(By.className("order-items")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")))
        {
            List<WebElement> td = row.findElements(By.tagName("td"));
            String itemName = td.get(1).getText();
            int quantity = Integer.parseInt(td.get(0).findElement(By.tagName("span")).getText());
            BigDecimal subtotal = new BigDecimal(td.get(2).getText());
            items.add(new OrderItem(itemName, quantity, subtotal));
        }

        return new Order(items);
    }
}
