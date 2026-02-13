package org.example.base.controller;

import org.example.base.dto.RequestModel;
import org.example.base.enums.Type;
import org.example.base.exceptions.BadRequestException;
import org.example.base.exceptions.InternalServerException;
import org.example.base.exceptions.TransformationException;
import org.example.base.service.convert.IConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(BaseController.class);

    private final Map<Type, IConverter> converters;

    public BaseController(List<IConverter> converters) {
        this.converters = converters.stream()
                                    .collect(toMap(IConverter::type, Function.identity()));
    }

    @PostMapping
    public Object convert(@RequestBody RequestModel model) {
        Type type = model.getType();
        log.debug("Пришёл запрос на конвертацию, с типом: {}", type);

        IConverter converter = converters.get(model.getType());
        if (converter == null) {
            throw new BadRequestException(String.format("Тип %s невозможно конвертировать", type));
        }

        try {
            return converter.convert(model.getPayload());
        } catch (TransformationException e) {
            throw new InternalServerException("Сервис не смог обработать запрос. Причина: " + e.getMessage());
        } catch (Exception e) {
            String msg = String.format("При конвертации произошла ошибка: %s", e.getMessage());
            log.error(msg);

            throw new BadRequestException(msg);
        }
    }
}
