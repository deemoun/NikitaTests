import com.github.javafaker.Faker;

public class Utils {
    public static String getSomeName(){
        return new Faker().name().firstName();
    }
}