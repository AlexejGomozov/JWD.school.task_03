package com.javacourse.task3.builder;

import com.javacourse.task3.entity.Tariff;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.Set;

public class TariffHandler extends DefaultHandler {
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
    private  Set<Tariff> tariff;
    private final EnumSet<Tariff> withText;
}
