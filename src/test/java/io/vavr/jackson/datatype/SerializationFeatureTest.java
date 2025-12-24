package io.vavr.jackson.datatype;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectWriter;
import tools.jackson.dataformat.csv.CsvMapper;
import tools.jackson.datatype.joda.JodaModule;
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

        CsvMapper mapper = new CsvMapper().rebuild().addModules(new JodaModule(), new VavrModule()).build();
        ObjectWriter writer = mapper.writer();

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
