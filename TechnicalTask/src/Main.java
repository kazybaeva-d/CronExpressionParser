import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String cronExp = input.nextLine();
        System.out.println(Arrays.toString(cronExp.trim().split("\\s+")));
    }
}
