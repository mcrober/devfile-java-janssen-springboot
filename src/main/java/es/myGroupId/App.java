package es.myGroupId;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        Map<String, String> map = System.getenv();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().contains("cwi")) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }
        }

        SpringApplication.run(App.class, args);

    }

}
