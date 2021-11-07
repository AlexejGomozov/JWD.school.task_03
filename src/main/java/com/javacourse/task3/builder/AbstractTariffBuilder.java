package com.javacourse.task3.builder;
import com.javacourse.task3.entity.Tariff;
import com.javacourse.task3.exception.TariffException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTariffBuilder {
   protected List<Tariff> tariffs;

    public AbstractTariffBuilder(){
        tariffs = new ArrayList<>();
    }
    public AbstractTariffBuilder(List<Tariff> tariffs){
        this.tariffs = tariffs;
    }
    public List<Tariff> getTariffs(){
        return  tariffs;
    }
    public abstract List<Tariff> buildSetTariffs(String filePath) throws TariffException;
}
