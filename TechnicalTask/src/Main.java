import java.util.Scanner;

public class Main {
    //input sample
    //*/15 0 1,15 * 1-5 /usr/bin/find
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String cronExp = input.nextLine();
        String[] arr = new String[6];
        arr = cronExp.trim().split("\\s+");

        System.out.println(asterisk(arr[3], 1, 12));
        System.out.println(comma(arr[2]));
        System.out.println(hyphen(arr[4]));
        System.out.println(forwardSlash(arr[0], 59));
    }

    public static String asterisk(String s, int min, int max) {
        //apriori check for the presence of asterisk
        StringBuilder res = new StringBuilder();

        for (int i = min; i <= max; i++) {
            res.append(i).append(" ");
        }

        return res.toString();
    }

    public static String comma(String s) {
        String[] values = s.split(",");
        StringBuilder res = new StringBuilder();

        for (String value : values) {
            res.append(value).append(" ");
        }

        return res.toString();
    }

    public static String hyphen(String s) {
        int minOfRange = Integer.parseInt(s.substring(0, s.indexOf("-")));
        int maxOfRange = Integer.parseInt(s.substring(s.indexOf("-") + 1));
        StringBuilder res = new StringBuilder();

        for (int i = minOfRange; i <= maxOfRange; i++) {
            res.append(i).append(" ");
        }

        return res.toString();
    }

    public static String forwardSlash(String s, int max) {
        int start = Integer.parseInt(s.substring(0, s.indexOf("/")));
        int step = Integer.parseInt(s.substring(s.indexOf("/") + 1));
        StringBuilder res = new StringBuilder();

        for (int i = start; i <= max; i += step) {
            res.append(i).append(" ");
        }

        return res.toString();
    }
}
