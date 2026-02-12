package org.example.base.service;

import org.example.base.enums.Type;
import org.springframework.stereotype.Service;

@Service
public class CoordToAdresService implements IConverter {
    @Override public Type type() { return Type.COORDINATES; }

    @Override public Object convert(Object data) {
        return "converted COORDINATES -> ADDRESS"; // заглушка
    }
}
