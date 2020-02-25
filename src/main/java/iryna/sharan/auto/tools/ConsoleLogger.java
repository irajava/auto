package iryna.sharan.auto.tools;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsoleLogger implements Logger {

    @Override
    public void log(String msg) {
        System.out.println(LocalDateTime.now()+" : "+msg);
    }
}
