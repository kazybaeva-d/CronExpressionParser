import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CronParserTest {

    //Test asterisk method to give the proper range
    @Test
    public void asteriskMethodShouldReturnRangeFromOneToFive() throws Exception {
        assertEquals("1 2 3 4 5 ", CronParser.asterisk(1, 5));
    }

    //Test comma method to give the proper range
    @Test
    public void commaMethodShouldReturnOneThreeFive() throws Exception {
        assertEquals("1 3 5 ", CronParser.comma("minute", "1,3,5", 0, 59));
    }

    //Test comma method to throw Exception while having the input value out of range
    @Test
    public void commaMethodShouldReturnException() throws Exception {
        assertThrows(Exception.class, () -> {CronParser.comma("minute", "-1, 1, 3, 5", 0, 59);});
    }

    //Test hyphen method to give the proper range
    @Test
    public void hyphenMethodShouldReturnRangeFromFiveToTen() throws Exception {
        assertEquals("5 6 7 8 9 10 ", CronParser.hyphen("month", "5-10", 1, 12));
    }

    //Test hyphen method to throw Exception while having the input value out of range
    @Test
    public void hyphenMethodShouldReturnException() throws Exception {
        assertThrows(Exception.class, () -> {CronParser.hyphen("month", "5-15", 1, 12);});
    }

    //Test forwardSlash method
    @Test
    public void forwardSlashMethodShouldReturnFifteenTwentyFive() throws Exception {
        assertEquals("15 25 ", CronParser.forwardSlash("day", "15/10", 1, 31));
    }

    @Test
    public void forwardSlashMethodShouldReturnException() throws Exception {
        assertThrows(Exception.class, () -> {CronParser.forwardSlash("dayOfWeek", "1/7", 0, 6);});
    }

    @Test
    public void forwardSlashMethodWithAsteriskOperator() throws Exception {
        assertEquals("1 16 31 ", CronParser.forwardSlash("day", "*/15", 1, 31));
    }

    @Test
    public void forwardSlashMethodWithHyphenOperator() throws Exception {
        assertEquals("15 20 25 30 35 40 ", CronParser.forwardSlash("minute", "15-40/5", 0, 59));
    }
}