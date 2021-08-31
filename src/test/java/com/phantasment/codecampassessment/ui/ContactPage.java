package com.phantasment.codecampassessment.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage extends Page
{
    private WebDriver driver;

    public ContactPage(WebDriver driver)
    {
        this.driver = driver;
    }

    @Override
    public void gotoPage()
    {
        driver.findElement(By.cssSelector("a[aria-label='contact']")).click();
    }

    public void setEmail(String email)
    {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setTelephone(String telephone)
    {
        WebElement telephoneField = driver.findElement(By.id("telephone"));
        telephoneField.clear();
        telephoneField.sendKeys(telephone);
    }

    public void submit()
    {
        driver.findElement(By.cssSelector("a[aria-label='submit']")).click();
    }

    public void clear()
    {
        driver.findElement(By.cssSelector("a[aria-label='clear']")).click();
    }

    public String getEmailError()
    {
        try
        {
            WebElement element = driver.findElement(By.id("email-err"));
            return element.getText();
        }
        catch (Throwable t)
        {
            return "";
        }
    }

    public String getTelephoneError()
    {
        try
        {
            return driver.findElement(By.id("telephone-err")).getText();
        }
        catch (Throwable t)
        {
            return "";
        }
    }
}
