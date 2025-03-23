package io.vavr.jackson.datatype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import io.vavr.collection.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class SerializationFeatureTest {

    @Test
    void shouldApplySerializationFeatureListDateTime() throws IOException {
        DateTime dateTime = new DateTime(2016, 6, 6, 8, 0, DateTimeZone.forID("CET"));
        io.vavr.collection.List<DateTime> dateTimeList = List.of(dateTime);
        java.util.List<DateTime> dateTimeJavaList = new ArrayList<>();
        dateTimeJavaList.add(dateTime);

        CsvMapper mapper = new CsvMapper();
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new VavrModule());
        ObjectWriter writer = mapper.writer().without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String serializedDateTime = writer.writeValueAsString(dateTime);
        String serializedDateTimeJavaList = writer.writeValueAsString(dateTimeJavaList);
        String serializedDateTimeList = writer.writeValueAsString(dateTimeList);

        assertThat(serializedDateTimeJavaList).isEqualTo(serializedDateTime);
        assertThat(serializedDateTimeList).isEqualTo(serializedDateTimeJavaList);

        List<DateTime> restored = mapper.readValue(serializedDateTimeList, new TypeReference<List<DateTime>>() {
        });
        assertThat(dateTime.getMillis()).isEqualTo(restored.head().getMillis());
    }
}
