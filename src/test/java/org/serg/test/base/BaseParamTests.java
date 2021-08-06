package org.serg.test.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseParamTests {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Запуск браузера");

        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 11, 1000);
        String baseUrl = "https://www.raiffeisen.ru/";
        driver.get(baseUrl);

    }


    @AfterAll
    static void afterAll() {
        System.out.println("Выход из браузера");
        driver.quit();
    }

    @BeforeEach
    void beforeEach() {
        String baseUrl = "https://www.raiffeisen.ru/";
        driver.get(baseUrl);
    }


}
