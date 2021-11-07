package com.javacourse.task3.builder;

import com.javacourse.task3.exception.TariffException;

public class TariffBuilderFactory {

    private enum ParserForm{
        DOM, SAX, STAX
    }
    private TariffBuilderFactory(){
    }
    public static AbstractTariffBuilder createTariffBuilder(String parserForm) throws TariffException {
        ParserForm form = ParserForm.valueOf(parserForm.toUpperCase());
        switch (form) {
            case DOM -> {
                return new TariffDomBuilder();
            }
            case STAX -> {
                return new TariffStaxBuilder();
            }
            case SAX -> {
                return new TariffSaxBuilder();
            }
            default -> throw new TariffException(String.format("No such constant (%s)", parserForm));
        }
    }
}
