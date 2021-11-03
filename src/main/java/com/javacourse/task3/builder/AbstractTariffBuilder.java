package com.javacourse.task3.builder;
import com.javacourse.task3.entity.Tariff;
import com.javacourse.task3.exception.TariffException;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public abstract class AbstractTariffBuilder {
   protected HashSet<Tariff> tariffs;

    public AbstractTariffBuilder(){
        tariffs = new HashSet<>();
    }
    public AbstractTariffBuilder(HashSet<Tariff> tariffs){
        this.tariffs = tariffs;
    }
    public HashSet<Tariff> getTariffs(){
        return  (HashSet<Tariff>)tariffs.clone();
    }
    public abstract void buildSetTariffs(String filePath) throws TariffException, IOException, XMLStreamException;
}
