package io.vavr.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import io.vavr.collection.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class SerializationFeatureTest {

    private static CsvMapper getMapper() {
        final CsvMapper csvMapper = new CsvMapper();
        csvMapper.registerModule(new JodaModule());
        csvMapper.registerModule(new VavrModule());
        return csvMapper;
    }

    @Test
    public void vavr_List_DateTime_serialization_should_use_SerializationFeature() throws IOException {
        final DateTime dateTime = new DateTime(2016, 6, 6, 8, 0, DateTimeZone.forID("CET"));
        final io.vavr.collection.List<DateTime> dateTimeList = List.of(dateTime);
        final java.util.List<DateTime> dateTimeJavaList = new ArrayList<>();
        dateTimeJavaList.add(dateTime);

        final CsvMapper mapper = getMapper();
        final ObjectWriter writer = mapper.writer()
                .without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        final String serializedDateTime = writer
                .writeValueAsString(dateTime);
        final String serializedDateTimeJavaList = writer
                .writeValueAsString(dateTimeJavaList);
        final String serializedDateTimeList = writer
                .writeValueAsString(dateTimeList);

        Assertions.assertEquals(serializedDateTime, serializedDateTimeJavaList);
        Assertions.assertEquals(serializedDateTimeJavaList, serializedDateTimeList);

        List<DateTime> restored = mapper.readValue(serializedDateTimeList, new TypeReference<List<DateTime>>() {});
        Assertions.assertEquals(restored.head().getMillis(), dateTime.getMillis());

    }
}
