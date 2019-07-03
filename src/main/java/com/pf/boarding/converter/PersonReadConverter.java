package com.pf.boarding.converter;

import com.mongodb.DBObject;
import com.pf.boarding.model.Embarque;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonReadConverter implements Converter<DBObject, Embarque> {

    public Embarque convert(DBObject source) {
        return new Embarque((Long) source.get("tiempoMedio"));
    }

}