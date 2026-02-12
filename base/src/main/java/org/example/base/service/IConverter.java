package org.example.base.service;

import org.example.base.enums.Type;

public interface IConverter {
    Type type();
    Object convert(Object data);
}
