package com.javacourse.task3.builder;
import com.javacourse.task3.entity.Tariff;
import com.javacourse.task3.exception.TariffException;

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
    public Set<Tariff> getTariffs(){
        return  (HashSet<Tariff>)tariffs.clone();
    }
    public abstract void buildSetTariffs(String filePath) throws TariffException;
}
