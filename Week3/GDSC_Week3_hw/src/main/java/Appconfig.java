package GDSC.backend.GDSC_3rd_HW;


@Configuration
public class AppConfig {

    @Bean
    public ClassA classA() {
        return new ClassA();
    }
}
