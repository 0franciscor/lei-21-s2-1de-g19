package app.controller;

import app.domain.model.Client;
import org.junit.Assert;
import org.junit.Test;

public class RegisterClientControllerTest {

    @Test
    public void saveClientTrue() {

        Client cl = new Client("1234567890123456","1234567890", "12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto", "Rua das Palmeiras");

        RegisterClientController rcc = new RegisterClientController();

        boolean result = true, expected = false;
        try {
            expected = rcc.saveClient(cl);
        } catch (Exception e){

        }

        Assert.assertEquals(result,expected);
    }

    @Test
    public void saveClientFalse() {

        Client cl = new Client("1234567890123456","1234567890", "12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto", "Rua das Palmeiras");

        RegisterClientController rcc = new RegisterClientController();

        boolean result = false, expected = false;
        rcc.getClientstore().addClient(cl);
        try {
            expected = rcc.saveClient(cl);
        } catch (Exception e){

        }

        Assert.assertEquals(result,expected);
    }
}