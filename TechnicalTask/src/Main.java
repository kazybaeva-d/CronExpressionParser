import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String cronExp = input.nextLine();

        try {
            CronParser cp = new CronParser(cronExp);
            cp.expressionParsing();
            System.out.println(cp.minute);
            System.out.println(cp.hour);
            System.out.println(cp.day);
            System.out.println(cp.month);
            System.out.println(cp.dayOfWeek);
            System.out.println(cp.command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
