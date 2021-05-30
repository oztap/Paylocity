package paylocity.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class paylocityPage {
    public paylocityPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "Username")
    public WebElement username;
    @FindBy(id = "Password")
    public WebElement password;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement logInButton;
    @FindBy(xpath = "//a[@class='navbar-brand']")
    public WebElement Dashboard;
    @FindBy(id = "add")
    public WebElement addEmployeeButton;
    @FindBy(id = "firstName")
    public WebElement firstName;
    @FindBy(id = "lastName")
    public WebElement lastName;
    @FindBy(id = "dependants")
    public WebElement dependants;
    @FindBy(xpath = "//button[@id='addEmployee']")
    public WebElement addButton;
    @FindBy(xpath = "//*[@id=\"employeesTable\"]/tbody/tr")
    public List<WebElement> tableOfEmployees;
    @FindBy(xpath = "//*[@id=\"employeesTable\"]/tbody/tr/td[5]")
    public WebElement salary;
    @FindBy(xpath = "//*[@id=\"employeesTable\"]/tbody/tr/td[6]")
    public WebElement grossPay;
    @FindBy(xpath = "//*[@id=\"employeesTable\"]/tbody/tr/td[7]")
    public WebElement benefitsPay;
    @FindBy(xpath = "//*[@id=\"employeesTable\"]/tbody/tr/td[8]")
    public WebElement netPay;
    @FindBy(xpath = "//*[@id=\"employeesTable\"]/tbody/tr[1]/td[9]/i[1]")
    public WebElement editButton;
    @FindBy(xpath = "//*[@id=\"updateEmployee\"]")
    public WebElement updateButton;
    @FindBy(xpath = "//*[@id=\"employeesTable\"]/tbody/tr/td[9]/i[2]")
    public WebElement Xbutton;
    @FindBy(xpath = "//*[@id=\"deleteEmployee\"]")
    public WebElement deleteButton;
}
