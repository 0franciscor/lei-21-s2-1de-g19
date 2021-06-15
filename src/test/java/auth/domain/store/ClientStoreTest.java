package auth.domain.store;

import app.domain.model.Client;
import org.junit.Assert;
import org.junit.Test;

public class ClientStoreTest {

    @Test
    public void saveClientInStore() {

        Client cl = new Client("1234567890123456","1234567890","12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto", "Rua das Palmeiras");

        ClientStore cl2 = new ClientStore();

        boolean result = true;
        boolean expected = cl2.saveClient(cl);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void DoesNotsaveClientInStore() {

        Client cl = new Client("1234567890123456","1234567890","12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto", "Rua das Palmeiras");

        ClientStore cl2 = new ClientStore();

        boolean result = false;
        cl2.addClient(cl);
        boolean expected = cl2.saveClient(cl);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void validateClientExists() {

        Client cl = new Client("1234567890123456","1234567890","12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto", "Rua das Palmeiras");

        ClientStore cl2 = new ClientStore();

        boolean result = false;
        cl2.addClient(cl);
        boolean expected = cl2.validateClient(cl);

        Assert.assertEquals(result,expected);

    }

    @Test
    public void validateClientDoesNotExist() {

        Client cl = new Client("1234567890123456","1234567890","12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto", "Rua das Palmeiras");

        ClientStore cl2 = new ClientStore();

        boolean result = true;
        boolean expected = cl2.validateClient(cl);

        Assert.assertEquals(result,expected);

    }

    @Test
    public void getClient (){

        ClientStore clientStore = new ClientStore();

        Client cl = new Client("1234567890123456","1234567890","12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto", "Rua das Palmeiras");
        clientStore.addClient(cl);

        Client clientExpected = cl;
        Client clientResult = clientStore.getClient("1234567890");


        Assert.assertEquals(clientExpected, clientResult);

    }

    @Test
    public void getClientNull (){

        ClientStore clientStore = new ClientStore();

        Client cl = new Client("1234567890123456","1234567890","12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto", "Rua das Palmeiras");
        clientStore.addClient(cl);

        Client clientResult = clientStore.getClient("1234567891");
        Client clientExpected = null;

        Assert.assertEquals(clientExpected, clientResult);

    }
}