// BaseController.java
package org.example.base.web;

import org.example.base.dto.RequestModel;
import org.example.base.enums.Type;
import org.example.base.service.IConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("/convert")
public class BaseController {

    private final Map<Type, IConverter> converters;

    public BaseController(List<IConverter> converters) {
        this.converters = converters.stream()
                .collect(toMap(IConverter::type, Function.identity()));
    }

    @PostMapping
    public Object convert(@RequestBody RequestModel model) {
        IConverter converter = converters.get(model.getType());
        if (converter == null) throw new IllegalArgumentException("Unknown type: " + model.getType());

        return converter.convert(model.getRequest());
    }
}
