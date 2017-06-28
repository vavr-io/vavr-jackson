package io.vavr.jackson.datatype;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Lazy;
import org.junit.Assert;
import org.junit.Test;

public class ServiceLoaderTest {

  /**
   * Since there is no way (without reflection) to interrogate the mapper to see which modules
   * are loaded, we can do so indirectly by simply asking it to handle any vavr type.
   * Non-failure means success
   * @throws Exception only if jackson fails to handle vavr types - meaning auto-register didn't work
   */
  @Test
  public void testAutoDiscovery() throws Exception {
    ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    Lazy<?> src = Lazy.of(() -> 1);
    String json = mapper.writer().writeValueAsString(src);
    Assert.assertEquals("1", json);
    Lazy<?> restored = mapper.readValue(json, Lazy.class);
    Assert.assertEquals(src, restored);
  }
}
