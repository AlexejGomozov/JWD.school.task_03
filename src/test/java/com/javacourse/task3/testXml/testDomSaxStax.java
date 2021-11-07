package com.javacourse.task3.testXml;

import com.javacourse.task3.builder.*;
import com.javacourse.task3.exception.TariffException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDomSaxStax {

    private static final String RELATIVE_FILE_PATH  = "resources/tariffsTest.xml";
    String expected;

    @BeforeEach
    void prepare () {
    expected = "a1";
    }

     @Test
    void parseDom() {
    TariffDomBuilder  domBuilder = new TariffDomBuilder();
    String actual = domBuilder.buildSetTariffs(RELATIVE_FILE_PATH ).get(0).getOperatorName().toString();
    assertEquals( actual, expected);
    }

    @Test
    void parseSax() {
        TariffSaxBuilder saxBuilder = new TariffSaxBuilder();
        String actual = saxBuilder.buildSetTariffs(RELATIVE_FILE_PATH ).get(0).getOperatorName().toString();
        assertEquals( actual, expected);
    }

    @Test
    void parseStax() throws TariffException {
        TariffStaxBuilder staxBuilder = new TariffStaxBuilder();
        String actual = staxBuilder.buildSetTariffs(RELATIVE_FILE_PATH ).get(0).getOperatorName().toString();
        assertEquals( actual, expected);
    }
}
