package javaslang.jackson.datatype;

import com.fasterxml.jackson.core.JsonProcessingException;
import javaslang.control.Option;
import org.junit.Assert;
import org.junit.Test;

public class OptionTest extends BaseTest {
    @Test
    public void optionSomeSerialize() throws JsonProcessingException {
        Assert.assertEquals("1", mapper().writer().writeValueAsString(Option.of(1)));
    }

    @Test
    public void optionNoneSerialize() throws JsonProcessingException {
        Assert.assertEquals("null", mapper().writer().writeValueAsString(Option.none()));
    }
}
