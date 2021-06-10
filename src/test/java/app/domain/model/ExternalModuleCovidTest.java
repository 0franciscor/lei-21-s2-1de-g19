package app.domain.model;

import app.domain.shared.ExternalModuleCovid;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExternalModuleCovidTest {

    @Test
    public void testToString() {
        assertEquals("External Module Covid (with key).", new ExternalModuleCovid().toString());
    }
}