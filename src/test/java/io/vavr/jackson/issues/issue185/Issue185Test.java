package io.vavr.jackson.issues.issue185;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.vavr.jackson.datatype.VavrModule;
import io.vavr.jackson.datatype.VavrModule.Settings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Issue185Test {
    
  private String loadJson(String path) throws IOException {
    return new String(getClass().getResourceAsStream(path).readAllBytes());
  }

  @Test
  void shouldMegeJavaTypes() throws Exception {
    //Given
    ObjectMapper mapper = new ObjectMapper()
      .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .registerModule(new VavrModule(new Settings().deserializeNullAsEmptyCollection(true)));

    String expected = loadJson("/issue185/result.json");
    ParentJava expectedObject = mapper.readerFor(ParentJava.class).readValue(expected);

    //When
    String parent = loadJson("/issue185/parent.json");
    String toMerge = loadJson("/issue185/to_merge.json");

    ParentJava parentObject = mapper.readerFor(ParentJava.class).readValue(parent);
    ParentJava updated = mapper.readerForUpdating(parentObject).readValue(toMerge);

    //Then
    System.out.println(expectedObject);
    System.out.println(updated);
    Assertions.assertEquals(updated, expectedObject);
  }

  @Test
  void shouldMergeVavrTypes() throws Exception {
    //Given
    ObjectMapper mapper = new ObjectMapper()
      .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .registerModule(new VavrModule(new Settings().deserializeNullAsEmptyCollection(true)));

    String expected = loadJson("/issue185/result.json");
    ParentVavr expectedObject = mapper.readerFor(ParentVavr.class).readValue(expected);

    //When
    String parent = loadJson("/issue185/parent.json");
    String toMerge = loadJson("/issue185/to_merge.json");

    ParentVavr parentObject = mapper.readerFor(ParentVavr.class).readValue(parent);
    ParentVavr updated = mapper.readerForUpdating(parentObject).readValue(toMerge);
    
    //Then
    Assertions.assertEquals(updated,expectedObject);
  }

}
