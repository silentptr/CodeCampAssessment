package com.phantasment.codecampassessment;

import com.phantasment.codecampassessment.models.Drink;
import com.phantasment.codecampassessment.models.Order;
import com.phantasment.codecampassessment.models.OrderItem;
import com.phantasment.codecampassessment.models.Pizza;
import com.phantasment.codecampassessment.ui.ContactPage;
import com.phantasment.codecampassessment.ui.MenuPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AssessmentTestSuite
{
    private WebDriver driver;

    @BeforeEach
    public void setup()
    {
        driver = new ChromeDriver();
        driver.get("https://d2yh0qhobk4vxr.cloudfront.net/");
        driver.manage().window().maximize();
    }

    @Test
    public void task1()
    {
        ContactPage contactPage = new ContactPage(driver);
        contactPage.gotoPage();

        contactPage.setEmail("xxx");
        contactPage.setTelephone("xxx");
        contactPage.submit();

        Assertions.assertEquals("Email is invalid", contactPage.getEmailError());
        Assertions.assertEquals("Telephone is invalid", contactPage.getTelephoneError());

        contactPage.clear();
        Assertions.assertEquals("", contactPage.getEmailError());
        Assertions.assertEquals("", contactPage.getTelephoneError());
    }

    @Test
    public void task2()
    {
        MenuPage menuPage = new MenuPage(driver);
        menuPage.gotoPage();
        menuPage.selectTab("DRINKS");
        ArrayList<Drink> drinks = menuPage.getDrinks();
        drinks.forEach(drink ->
        {
            if (drink.getName().equals("Espresso Thickshake"))
            {
                drink.order();
            }
        });

        menuPage.selectTab("PIZZAS");
        ArrayList<Pizza> pizzas = menuPage.getPizzas();
        pizzas.forEach(pizza ->
        {
            if (pizza.getName().equals("Margherita"))
            {
                for (int i = 0; i < 2; ++i)
                {
                    pizza.order();
                }
            }
        });

        Assertions.assertEquals(3, menuPage.getTotalItemsInCart());

        menuPage.getViewOrderButton().click();
        Order order = menuPage.getOrder();

        for (OrderItem orderItem : order.getItems())
        {
            if (orderItem.getItemName().equals("Margherita"))
            {
                Assertions.assertEquals(new BigDecimal("19.98"), orderItem.getSubtotal());
            }
        }
    }

    @AfterEach
    public void cleanup()
    {
        driver.quit();
    }
}
