package org.serg.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.serg.test.base.BaseParamTests;

public class ParameterizedTest extends BaseParamTests {


    /**
     * Заходим на страницу "ипотека"
     * Проверить что находимся именно на этой странице
     */


    @DisplayName("Запуск параметризированного тестового сценария")
    @org.junit.jupiter.params.ParameterizedTest
    @ValueSource(strings = {"Аверин Антон Васильевич","Вишнев Максим Антонович","Крупин Евгений Максимович"})
    public void testScenario(String name) {
        String chooseMortgageXPath = "//a[@class='main-menu__link'][contains(text(),'Ипотека')]";
        WebElement chooseMortgage = driver.findElement(By.xpath(chooseMortgageXPath));
        waitUtilElementToBeClickable(chooseMortgage);
        chooseMortgage.click();
        Assertions.assertEquals("Ипотека", chooseMortgage.getAttribute("innerText"), "Не та страница");
//-------------------------------------
        /**
         * Заходим в под- пункт меню "рефинансирование"
         */
        String chooseRefinancingXPath = "//a[@href='/retail/mortgageloans/refinansirovanie-kreditov-inyh-bankov/']";
        WebElement chooseRefinancing = driver.findElement(By.xpath(chooseRefinancingXPath));
        waitUtilElementToBeClickable(chooseRefinancing);
        chooseRefinancing.click();
//--------------------------------------
        /**
         * Проверить что находимся именно на странице рефинансирование
         */
        String checkingThePageXPath = "//h1";
        WebElement checkingThePage = driver.findElement(By.xpath(checkingThePageXPath));
        Assertions.assertEquals("Рефинансирование ипотеки и других кредитов", checkingThePage.getText(), "Не та страница");
//--------------------------------------
        /**
         * Нажать кнопку "оставить заявку"
         */

        String clickLeaveRequestXPath = "//div[@class='b-intro__block-buttons']//a[@class='button']";
        WebElement clickLeaveRequest = driver.findElement(By.xpath(clickLeaveRequestXPath));
        waitUtilElementToBeClickable(clickLeaveRequest);
        scrollToElementJs(clickLeaveRequest);
        clickLeaveRequest.click();
//--------------------------------------
        /**
         * Проверить что находимся именно на этой странице
         */
        Assertions.assertEquals("ОСТАВИТЬ ЗАЯВКУ", clickLeaveRequest.getText(), "Не та страница");
//--------------------------------------
        /**
         * заполнение полей
         * ФИО
         */
        WebElement fillInputFullName = driver.findElement(By.xpath("//input[contains(@name,'fullName')]"));
        fillInputFullName.click();
        fillInputFullName.sendKeys(name, Keys.DOWN);
        fillInputFullName.sendKeys(Keys.ENTER);
        //проверка
        checkFlag(fillInputFullName, name);
//--------------------------------------
        /**
         *Дата рождения
         */
        String fillInputFieldDateXPath = "//input[contains(@name,'birthDate')]";
        waitUtilElementToBeVisible(By.xpath(fillInputFieldDateXPath));
        WebElement fillInputFieldDate = driver.findElement(By.xpath(fillInputFieldDateXPath));
        fillInputFieldDate.click();
        fillInputFieldDate.sendKeys("19.09.1990");
//--------------------------------------
        /**
         *Место рождения
         */
        String fillInputFieldBirthPlaceXPath = "//input[contains(@name,'birthPlace')]";
        waitUtilElementToBeVisible(By.xpath(fillInputFieldBirthPlaceXPath));
        WebElement fillInputFieldBirthPlace = driver.findElement(By.xpath(fillInputFieldBirthPlaceXPath));
        fillInputFieldBirthPlace.click();
        String city = "Абхазия";
        fillInputFieldBirthPlace.sendKeys(city);
        //проверка
        checkFlag(fillInputFieldBirthPlace, city);
//--------------------------------------
        /**
         *Выбрать пол
         */
        String checkGenderXPath = "//input[@type='radio'][contains(@value,'M')]/..";
        waitUtilElementToBeVisible(By.xpath(checkGenderXPath));
        WebElement checkGender = driver.findElement(By.xpath(checkGenderXPath));
        waitUtilElementToBeClickable(checkGender);
        checkGender.click();
//--------------------------------------
        /**
         * скролл до пол
         */
        WebElement gender = driver.findElement(By.xpath("//div[@data-marker='Fieldset.Label']"));
        scrollToElementJs(gender);
//--------------------------------------
        /**
         *Переключиться на иностранного гражданина
         */
        String checkForeignXPath = "//span[@data-marker='Switcher.Jackdaw']";
        WebElement checkForeign = driver.findElement(By.xpath(checkForeignXPath));
        waitUtilElementToBeVisible(By.xpath(checkForeignXPath));
        waitUtilElementToBeClickable(checkForeign);
        checkForeign.click();
//--------------------------------------
        /**
         * Выбор страны гражданства
         */
        WebElement chooseCountry = driver.findElement(By.xpath("//div[@data-marker='Select.value.Selectarea']"));
        chooseCountry.click();
        driver.findElement(By.xpath("//div[@data-marker='Item.Value'][contains(text(),'Германия')]")).click();
//--------------------------------------
        /**
         *Заполнить серию паспорта
         */
        fillInputField(driver.findElement(By.xpath("//input[@data-marker-field='foreignSeries']")), "111");
//--------------------------------------
        /**
         *Заполнить номер паспорта
         */
        fillInputField(driver.findElement(By.xpath("//input[@data-marker-field='foreignNumber']")), "1111");
//--------------------------------------
        /**
         *Заполнить дата выдачи
         */
        fillInputField(driver.findElement(By.xpath("//input[@data-marker-field='foreignIssuedDate']")), "11.12.1999");
//--------------------------------------
        /**
         *Заполнить кем выдан
         */
        fillInputField(driver.findElement(By.xpath("//input[@data-marker-field='foreignIssuedBy']")), "Где то там");
//--------------------------------------
        /**
         *Заполнить адрес регистрации
         */
        fillInputField(driver.findElement(By.xpath("//input[@data-marker-field='registrationAddress']")), "г Москва, ул Мнёвники");

//        WebElement fillInputRegistration = driver.findElement(By.xpath("//input[@data-marker-field='registrationAddress']"));
//        fillInputRegistration.click();
//        String registration="г Москва, ул Мнёвники";
//        fillInputRegistration.sendKeys(registration, Keys.DOWN);
//        fillInputRegistration.sendKeys(Keys.ENTER);
//        //проверка
//        checkFlag(fillInputRegistration, registration);
//--------------------------------------
        /**
         * скролл до телефона
         *Заполнить номер телефона
         */
        WebElement phone = driver.findElement(By.xpath("//input[@data-marker-field='phone']"));
        scrollToElementJs(phone);
        fillInputField(driver.findElement(By.xpath("//input[@data-marker-field='phone']")), "(999) 999-99-99");
//--------------------------------------
        /**
         *Кликнуть продолжить
         */
        String resumeXPath = "//button[@type='submit']";
        WebElement resume = driver.findElement(By.xpath(resumeXPath));
        waitUtilElementToBeClickable(resume);
        resume.click();
//--------------------------------------
        /**
         *Проверка сообщений об ошибке
         */
        checkErrorMessageAtField("Поле обязательно для заполнения", "salary");
        checkErrorMessageAtField("Поле обязательно для заполнения", "acceptSalary");
        checkErrorMessageAtField("Выберите из списка", "pledgeAddress");
        checkErrorMessageAtField("Поле обязательно для заполнения", "email");
        checkErrorMessageAtField("Поле обязательно для заполнения", "agreement");
    }


    /**
     * Явное ожидание того что элемент станет кликабельным
     */
    private void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * Явное ожидание того что элемент станет видимым
     *
     * @param locator - локатор до веб элемент который мы ожидаем найти и который виден на странице
     */
    private void waitUtilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Скрол до элемента на js коде
     *
     * @param element - веб элемент до которого нужно проскролить
     */
    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    /**
     * Заполнение полей определённым значений
     *
     * @param element - веб элемент (поле какое-то) которое планируем заполнить
     * @param value   - значение которы мы заполняем веб элемент (поле какое-то)
     */
    private void fillInputField(WebElement element, String value) {
        waitUtilElementToBeClickable(element);
        element.click();
        element.sendKeys(value);
        checkFlag(element, value);
    }

    /**
     * Проверка на корректность заполненных полей
     *
     * @param element
     * @param value
     */
    private void checkFlag(WebElement element, String value) {
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
    }
    /**
     * Проверка ошибка именно у конкретного поля
     *
     * @param       веб элемент (поле какое-то) которое не заполнили
     * @param errorMessage - ожидаемая ошибка под полем которое мы не заполнили
     */

    /**
     * @param errorMessage- ожидаемая ошибка под полем которое мы не заполнили
     * @param name-         место где ищем ошибку
     */
    private void checkErrorMessageAtField(String errorMessage, String name) {
        String xPath = "//*[@name='" + name + "' or text()='" + name + "']/ancestor::div[contains(@data-marker, 'Fieldset.value.Root') or contains(@data-marker, 'Fieldset.Root')]/div[@data-marker='Fieldset.value.Error']";
        WebElement webElement = driver.findElement(By.xpath(xPath));
        Assertions.assertEquals(webElement.getText(), errorMessage, "Поле не заполнено");
    }


    private void time() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
