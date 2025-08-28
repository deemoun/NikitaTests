import com.github.javafaker.Faker;

public class Utils {
    public static String getSomeName(){
        Faker faker = new Faker();
        return faker.name().firstName();
    }
}
