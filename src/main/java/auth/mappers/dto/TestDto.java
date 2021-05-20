package auth.mappers.dto;

public class TestDto {
    private String description;
    private String testType;

    public TestDto(String description, String testType) {
        this.description = description;
        this.testType = testType;
    }
    public String toString() {
        return String.format("This is a %s test and the type of test is %s.", description, testType);
    }
}
