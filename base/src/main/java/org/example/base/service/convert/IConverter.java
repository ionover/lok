package org.example.base.service.convert;

import org.example.base.enums.Type;

public interface IConverter {
    Type type();
    Object convert(Object data);
}
