package com.task.automation.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.automation.objects.BillingAddress;
import org.testng.annotations.DataProvider;
import com.task.automation.utiities.DatabaseManager;
import com.task.automation.utiities.ExcelUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomHelper {

    public static String getSimpeDateFormat(String format) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formatedDate = formatter.format(date);
        return formatedDate;
    }

    public static void getEmployeeName() throws SQLException, ClassNotFoundException {
        String query = "select Emp_Name from Employee limit 1";
        ResultSet resultSet = DatabaseManager.getData(query);
        resultSet.next();
        System.out.println(resultSet.getString("Emp_Name"));
    }

    public static BillingAddress generateBillingData(){
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setFirstName("demo");
        billingAddress.setLastName("iser");
        billingAddress.setCity("Delhi");
        billingAddress.setAddressLineOne("C-37 Old ");
        billingAddress.setPostalCode("110009");
        billingAddress.setEmail("demouser@gmail.com");
        billingAddress.setCompany("ABC_Company");
        billingAddress.setCountry("India");
        billingAddress.setState("Delhi");
        String product = "Blue Shoes";
        return billingAddress;
    }

    public static BillingAddress  generateBillingDataviJSON(InputStream is, BillingAddress billingAddress) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is,billingAddress.getClass());
    }

    public static <T> T  genericDeserialiser(String file, Class<T> T) throws IOException {
        InputStream is = CustomHelper.class.getResourceAsStream("/testdata/"+file);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is,T);
    }

    @DataProvider(name="input")
    public Object[][] dataFromExcel(String file){
        Object[][] obj =  ExcelUtils.getExcelData(file);//shoppingData.xlsx
        return obj;
    }
}
