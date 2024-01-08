package com.eaapp.pages;

import com.eaapp.utilities.BrowserUtils;
import com.eaapp.utilities.ConfigurationReader;
import com.eaapp.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class EmployeePage extends BasePage {

    @FindBy(xpath = "//a[text()='Login']")
    public WebElement login_Btn;
    @FindBy(id = "UserName")
    public WebElement usernameInputBox;
    @FindBy(id = "Password")
    public WebElement passwordInputBox;
    @FindBy(xpath = "//input[@value='Log in']")
    public WebElement loginButtonAfterCredentials;
    @FindBy(xpath = "//a[text()='Employee List']")
    public WebElement employeeList_Btn;
    @FindBy(xpath = "//a[text()='Create New']")
    public WebElement createNew_Btn;
    @FindBy(id = "Name")
    public WebElement nameInputBox;
    @FindBy(id = "Salary")
    public WebElement salaryInputBox;
    @FindBy(id = "DurationWorked")
    public WebElement durationWorkedInputBox;
    @FindBy(id = "Grade")
    public WebElement gradeInputBox;
    @FindBy(id = "Email")
    public WebElement emailInputBox;
    @FindBy(xpath = "//input[@value='Create']")
    public WebElement create_BtnInEmployee;
    @FindBy(xpath = "(//td[contains(.,'a@aa.com')]/../td/a)[3]")
    public WebElement delete_BtnInEmployee;
    @FindBy(xpath = "//input[@value='Delete']")
    public WebElement delete2_BtnInEmployee;
    @FindBy(xpath = "//th[contains(.,'Grade')]/../../tr")
    public List<WebElement> employeeList;

    String emailCheck;

    public void login() {
        Driver.get().get(ConfigurationReader.get("url"));
        login_Btn.click();
        usernameInputBox.sendKeys(ConfigurationReader.get("username"));
        passwordInputBox.sendKeys(ConfigurationReader.get("password"));
        loginButtonAfterCredentials.click();
    }
    public void enterEmployeeCredentials(String name, String salary, String durationWorked, String grade, String email) {
        createNew_Btn.click();
        nameInputBox.sendKeys(name);
        salaryInputBox.sendKeys(salary);
        durationWorkedInputBox.sendKeys(durationWorked);
        Select select = new Select(gradeInputBox);
        select.selectByVisibleText(grade);
        emailInputBox.sendKeys(email);
        create_BtnInEmployee.click();
        emailCheck=email;
    }
    public void verifyEmployeeCreated() {
        int countOfEmployeeList=employeeList.size();
        System.out.println("employeeList.size() = " + employeeList.size());
        System.out.println("Eklenen Employee'nin emaili = " + Driver.get().findElement(By.xpath(
                "((//th[contains(.,'Grade')]/../../tr)[" + countOfEmployeeList + "]/td)[5]")).getText());
        BrowserUtils.waitFor(1);
        Assert.assertTrue(Driver.get().findElement(By.xpath(
                "(//th[contains(.,'Grade')]/../../tr)["+countOfEmployeeList+"]")).getText().contains(emailCheck));
    }
    public void deleteEmployeeMtd(){
        int countOfEmployeeList=employeeList.size();
        BrowserUtils.waitFor(2);
        BrowserUtils.scrollToElement(Driver.get().findElement(By.xpath("(((//th[contains(.,'Grade')]/../../tr)[" + countOfEmployeeList +"]/td)[6]/a)[3]")));
        BrowserUtils.waitFor(2);
        Driver.get().findElement(By.xpath("(((//th[contains(.,'Grade')]/../../tr)[" + countOfEmployeeList +"]/td)[6]/a)[3]")).click();
        BrowserUtils.waitFor(1);
        delete2_BtnInEmployee.click();
        BrowserUtils.waitFor(1);
    }
    public void verifyEmployeeDeleted(){
        int countOfEmployeeList=employeeList.size();
        BrowserUtils.scrollToElement(Driver.get().findElement(By.xpath("(((//th[contains(.,'Grade')]/../../tr)[" + countOfEmployeeList +"]/td)[6]/a)[3]")));
        BrowserUtils.waitFor(3);
        System.out.println("Silindikten sonraki Son Employee'nin emaili = " + Driver.get().findElement(By.xpath(
                "((//th[contains(.,'Grade')]/../../tr)[" + countOfEmployeeList + "]/td)[5]")).getText());
        Assert.assertFalse(Driver.get().findElement(By.xpath(
                "(//th[contains(.,'Grade')]/../../tr)["+countOfEmployeeList+"]")).getText().contains(emailCheck));
    }


}