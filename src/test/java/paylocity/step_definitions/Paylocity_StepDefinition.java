package paylocity.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import paylocity.pages.paylocityPage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Paylocity_StepDefinition {
    paylocity.pages.paylocityPage paylocityPage = new paylocityPage();



    @Given("an employer")
    public void an_employer() {
        Driver.getDriver().get("https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/Account/Login");
        paylocityPage.username.sendKeys(ConfigurationReader.getProperty("username"));
        paylocityPage.password.sendKeys(ConfigurationReader.getProperty("password"));
        paylocityPage.logInButton.click();

    }


    @Given("I am on the benefits dashboard page")
    public void i_am_on_the_benefits_dashboard_page() {
        Assert.assertEquals(paylocityPage.Dashboard.getText(), "Paylocity Benefits Dashboard");
    }

    @When("I select Add Employee")
    public void i_select_add_employee() {
        paylocityPage.Dashboard.click();
        paylocityPage.addEmployeeButton.click();
    }

    @Then("I should be able to enter employee details")
    public void i_should_be_able_to_enter_employee_details() {
        paylocityPage.firstName.sendKeys("Sergii");
        paylocityPage.lastName.sendKeys("Ostapchuk");
        paylocityPage.dependants.sendKeys("1");
        paylocityPage.addButton.click();
    }

    @And("The employee should save")
    public void the_employee_should_save() {

        List<WebElement> list = paylocityPage.tableOfEmployees;
        for (WebElement each : list) {
            if(each.equals("Sergii")) {
                Assert.assertTrue(each.getText().contains("Sergii"));
            }
        }
    }

    @And("I should see the employee in the table")
    public void i_should_see_the_employee_in_the_table() {

        List<WebElement> list = paylocityPage.tableOfEmployees;
        for (WebElement each : list) {
            Assert.assertTrue(each.isDisplayed());
        }

    }

    @And("the benefit cost calculations are correct")
    public void the_benefit_cost_calculations_are_correct() throws InterruptedException {
        //All employees are paid $2000 per paycheck before deductions
        //• There are 26 paychecks in a year
        //• The cost of benefits is $1000/year for each employee
        //• Each dependent incurs a cost of $500/year
        int numberofdependant = 1;
        double dependent = numberofdependant * 500;
        double paycheck = 2000;
        double salary = paycheck * numberofdependant;
        int numberOfPaychecks = 26, costOfBenefits = 1000;
        double result = (costOfBenefits + dependent) / 26;
        double benCost = paycheck - result;
        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.println(formatter.format(benCost));

        salary = Double.valueOf(paylocityPage.salary.getText());

        double grosspay = Double.valueOf(paylocityPage.grossPay.getText());

        double benefitPay = Double.valueOf(paylocityPage.benefitsPay.getText());

        double actualnetPay = Double.valueOf(paylocityPage.netPay.getText());

        double expectednetPay = grosspay - result;
        double Expect = Double.valueOf(formatter.format(expectednetPay));
        Thread.sleep(3000);
        String expectedRes = Double.toString(Expect);
        String actualRes = Double.toString(actualnetPay);
        Assert.assertEquals(expectedRes, actualRes);
    }


    @When("I select the Action Edit")
    public void iSelectTheActionEdit() {
        Assert.assertTrue(paylocityPage.editButton.isEnabled());

    }

    @Then("I can edit employee details")
    public void iCanEditEmployeeDetails() throws InterruptedException {
        paylocityPage.editButton.click();
        Thread.sleep(3000);
        paylocityPage.firstName.clear();
        paylocityPage.firstName.sendKeys("Serg");
        paylocityPage.lastName.clear();
        paylocityPage.lastName.sendKeys("Ostap");
        paylocityPage.dependants.clear();
        paylocityPage.dependants.sendKeys("2");
        Thread.sleep(3000);
        paylocityPage.updateButton.click();

    }

    @And("the data should change in the table")
    public void theDataShouldChangeInTheTable() {
        WebElement getLastNameValue= Driver.getDriver().findElement(By.xpath("//*[@id='employeesTable']/tbody/tr/td[2]"));
        WebElement firstNameValue= Driver.getDriver().findElement(By.xpath("//*[@id='employeesTable']/tbody/tr/td[3]"));
        WebElement getDependantsValue= Driver.getDriver().findElement(By.xpath("//*[@id='employeesTable']/tbody/tr/td[4]"));

        Assert.assertEquals(getLastNameValue.getText(),"Serg");
        Assert.assertEquals(firstNameValue.getText(),"Ostap");
        Assert.assertEquals(getDependantsValue.getText(),"2");


    }



    @When("I click the Action X")
    public void iClickTheActionX() {
        paylocityPage.Xbutton.click();
        paylocityPage.deleteButton.click();
    }

    @Then("the employee should be deleted")
    public void theEmployeeShouldBeDeleted() {
        List<WebElement> list = paylocityPage.tableOfEmployees;
        //for (WebElement each : list) {
        Assert.assertTrue(!list.contains("Serg"));

    }
}
