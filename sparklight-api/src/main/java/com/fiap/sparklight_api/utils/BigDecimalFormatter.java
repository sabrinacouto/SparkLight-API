package com.fiap.sparklight_api.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;
import java.math.BigDecimal;

public class BigDecimalFormatter extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        DecimalFormat df = new DecimalFormat("#,##0.00");
        gen.writeString(df.format(value));
    }
}

