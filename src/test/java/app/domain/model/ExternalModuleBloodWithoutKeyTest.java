package app.domain.model;

import app.domain.shared.ExternalModuleBloodWithoutKey;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExternalModuleBloodWithoutKeyTest {

    @Test
    public void testToString() {
        assertEquals("External Module Blood (without key).", new ExternalModuleBloodWithoutKey().toString());
    }
}