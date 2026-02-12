package org.example.base.service;

import org.example.base.enums.Type;
import org.springframework.stereotype.Service;

@Service
public class AdresToCoordService implements IConverter {
    @Override
    public Object convert(Object data) {


        return "converted ADDRESS -> COORDINATES";
    }

    @Override
    public Type type() {
        return Type.ADDRESS;
    }
}
