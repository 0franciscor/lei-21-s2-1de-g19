package app.controller;

import app.domain.model.Client;
import auth.domain.store.ClientStore;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterClientControllerTest {

    @Test
    public void saveClientTrue() {

        Client cl = new Client("1234567890123456","1234567890", "12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto");

        RegisterClientController rcc = new RegisterClientController();

        boolean result = true;
        boolean expected = rcc.saveClient(cl);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void saveClientFalse() {

        Client cl = new Client("1234567890123456","1234567890", "12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto");

        RegisterClientController rcc = new RegisterClientController();

        boolean result = false;
        rcc.getClientstore().addClient(cl);
        boolean expected = rcc.saveClient(cl);

        Assert.assertEquals(result,expected);
    }
}