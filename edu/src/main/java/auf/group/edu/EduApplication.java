package auf.group.edu;

import auf.group.edu.bot.BotSettings;
import auf.group.edu.repository.AuthRepository;
import auf.group.edu.repository.SubjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class EduApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EduApplication.class, args);
        AuthRepository authRepository = run.getBean(AuthRepository.class);
        SubjectRepository subjectRepository = run.getBean(SubjectRepository.class);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new BotSettings(authRepository, subjectRepository));
        } catch (TelegramApiException e) {
            System.err.println("bot not run ${balkim internet yaxshimsdur\uD83E\uDD14}");
        }
    }

}
