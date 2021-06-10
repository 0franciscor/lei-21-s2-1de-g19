package app.domain.model;

import app.domain.shared.ExternalModuleBloodWithKey;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExternalModuleBloodWithKeyTest {

    @Test
    public void testString() {
        assertEquals("External Module Blood (with key).", new ExternalModuleBloodWithKey().toString());
    }
}