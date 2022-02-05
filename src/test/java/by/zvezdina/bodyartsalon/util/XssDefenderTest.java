package by.zvezdina.bodyartsalon.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XssDefenderTest {

    private XssDefender xssDefender;
    private String withTags;
    private String expectedWithoutTags;
    private String blankString;
    private String expectedForBlankInput;

    @BeforeEach
    public void setUp() {
        xssDefender = XssDefender.getInstance();
        withTags = "<script>";
        expectedWithoutTags = "script";
        blankString = "  ";
        expectedForBlankInput = blankString;
    }

    @Test
    public void safeFormDataTest() {
        String actual = xssDefender.safeFormData(withTags);
        assertThat(actual).isEqualTo(expectedWithoutTags);
    }

    @Test
    public void safeFormDataForBlankStringTest() {
        String actual = xssDefender.safeFormData(blankString);
        assertThat(actual).isEqualTo(expectedForBlankInput);
    }
}