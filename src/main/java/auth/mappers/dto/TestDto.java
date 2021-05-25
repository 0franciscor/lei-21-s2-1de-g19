package auth.mappers.dto;

public class TestDto {
    private String description;
    private String testType;
    private String code;

    public TestDto(String description, String testType, String code) {
        this.description = description;
        this.testType = testType;
        this.code = code;
    }
    public String toString() {
        return String.format("This is a %s test and the type of test is %s.", description, testType);
    }
    public String getTestCode() {
        return code;
    }
}
