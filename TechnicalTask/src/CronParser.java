public class CronParser {
    final static int minuteMin = 0, minuteMax = 59;
    final static int hourMin = 0, hourMax = 23;
    final static int dayMin = 1, dayMax = 31;
    final static int monthMin = 1, monthMax = 12;
    final static int dayOfWeekMin = 0, dayOfWeekMax = 6;

    StringBuilder minute, hour, day, month, dayOfWeek, command;
    String[] arr;
    final int numOfFields = 6;

    CronParser(String cronExp) throws Exception {
        arr = cronExp.trim().split("\\s+");

        if (arr.length != numOfFields) {
            throw new Exception(
                    "Invalid input format: " +
                    "The standard cron format consists of 6 fields " +
                    "minute, hour, day of month, month, and day of week, " +
                    "command");
        }
    }

    public void expressionParsing() throws Exception {
        String minuteTemp = unitParsing("minute", arr[0], minuteMin, minuteMax);
        String hourTemp = unitParsing("hour", arr[1], hourMin, hourMax);
        String dayTemp = unitParsing("day", arr[2], dayMin, dayMax);
        String monthTemp = unitParsing("month", arr[3], monthMin, monthMax);
        String dayOfWeekTemp = unitParsing("dayOfWeek", arr[4], dayOfWeekMin, dayOfWeekMax);
        String commandTemp = arr[5];

        minute = new StringBuilder(String.format("%-14s", "minute")).append(minuteTemp);
        hour = new StringBuilder(String.format("%-14s", "hour")).append(hourTemp);
        day = new StringBuilder(String.format("%-14s", "day of month")).append(dayTemp);
        month = new StringBuilder(String.format("%-14s", "month")).append(monthTemp);
        dayOfWeek = new StringBuilder(String.format("%-14s", "day of week")).append(dayOfWeekTemp);
        command = new StringBuilder(String.format("%-14s", "command")).append(commandTemp);
    }

    public String unitParsing(String field, String s, int min, int max) throws Exception {
        String parsedUnit;

        if (s.contains("/")) {
            parsedUnit = forwardSlash(field, s, min, max);
        } else if (s.contains(",")) {
            parsedUnit = comma(field, s, min, max);
        } else if (s.contains("-")) {
            parsedUnit = hyphen(field, s, min, max);
        } else if (s.contains("*")) {
            parsedUnit = asterisk(min, max);
        } else {
            isWithinRange(field, Integer.parseInt(s), min, max);
            parsedUnit = s;
        }

        return parsedUnit;
    }

    public static void isWithinRange(String field, int value, int min, int max) throws Exception {
        if (value < min || value > max) {
            throw new Exception("Incorrect value: " + value + ". The " + field + " value must be within the range [" + min + ", " + max + "]");
        }
    }

    public static String asterisk(int min, int max) {
        StringBuilder res = new StringBuilder();

        for (int i = min; i <= max; i++) {
            res.append(i).append(" ");
        }

        return res.toString();
    }

    public static String comma(String field, String s, int min, int max) throws Exception {
        String[] values = s.split(",");
        StringBuilder res = new StringBuilder();

        for (String value : values) {
            isWithinRange(field, Integer.parseInt(value), min, max);
            res.append(value).append(" ");
        }

        return res.toString();
    }

    public static String hyphen(String field, String s, int min, int max) throws Exception {
        int minOfRange = Integer.parseInt(s.substring(0, s.indexOf("-")));
        isWithinRange(field, minOfRange, min, max);

        int maxOfRange = Integer.parseInt(s.substring(s.indexOf("-") + 1));
        isWithinRange(field, maxOfRange, min, max);

        StringBuilder res = new StringBuilder();

        for (int i = minOfRange; i <= maxOfRange; i++) {
            res.append(i).append(" ");
        }

        return res.toString();
    }

    public static String forwardSlash(String field, String s, int min, int max) throws Exception {
        StringBuilder res = new StringBuilder();
        String startValue = s.substring(0, s.indexOf("/"));
        int start;
        int end = max;

        if (startValue.length() > 2) { //check for val1-val2 presence
            String range = hyphen(field, startValue, min, max);
            String[] rangeValues = range.split("\\s+");
            start = Integer.parseInt(rangeValues[0]);
            end = Integer.parseInt(rangeValues[rangeValues.length - 1]);
        } else {
            if (startValue.equals("*")) {
                if (field.equals("minute") || field.equals("hour") || field.equals("dayOfWeek")) {
                    start = 0;
                } else {
                    start = 1;
                }
            } else {
                start = Integer.parseInt(startValue);
                isWithinRange(field, start, min, max);
            }
        }

        int step = Integer.parseInt(s.substring(s.indexOf("/") + 1));
        isWithinRange(field, step, min, max);

        for (int i = start; i <= end; i += step) {
            res.append(i).append(" ");
        }

        return res.toString();
    }
}
