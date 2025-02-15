# Issue 185

## Description

```bash
jwebserver -p 9000 -d "$(pwd)/build/reports/problems/"
./gradlew clean test
```

```bash
Issue185Test > shouldMergeVavrTypes() FAILED
    org.opentest4j.AssertionFailedError: expected: 
<ParentVavr{
    list=List(first, third), 
    map=HashMap((third_key, third_value), (first_key, first_overridden_value)), 
    deepMap=HashMap(
        (third_key, HashMap((third_nested_key, third_nested_value))), 
        (first_key, HashMap((first_overridden_nested_key, first_overridden_nested_value)))), 
    child=Child{name='null', description='null'}}
> but was: 
<ParentVavr{
    list=List(first, third), 
    map=HashMap((third_key, third_value), (first_key, first_overridden_value), (second_key, second_value)), 
    deepMap=HashMap(
        (third_key, HashMap((third_nested_key, third_nested_value))), 
        (first_key, HashMap((first_nested_key, first_nested_value), (first_overridden_nested_key, first_overridden_nested_value))), (second_key, HashMap((second_nested_key, second_nested_value)))), 
    child=Child{name='null', description='null'}}
>
at app//org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
at app//org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
at app//org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
at app//org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
at app//org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
at app//org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
at app//io.vavr.jackson.issues.issue185.Issue185Test.shouldMergeVavrTypes(Issue185Test.java:54)
```
