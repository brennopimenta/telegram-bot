package gov.goias.bot.telegram.example;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Classe Principal da aplicação
 * Inicializa o SpringBoot
 */
@SpringBootApplication
public class Application {
    private Logger log = Logger.getLogger(Application.class);

    private TelegramBotsApi telegram;

    /**
     * Método Inicial da aplicação
     * @param args Argumentos de inicialização (não estão sendo utilizados).
     */
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(Application.class, args);
    }

    /**
     *  Este método registrará seu bot no telegram, nesse exemplo eu estou usando a API para registar um bot, mas
     *  poderia ser vários bots rodando juntos segue alguns exemplos: todos os bots de um sistema X ou de um Orgão ou de um Assunto específico.
     *  A vantagem e que serão todos uma mesma aplicaçaõ, a desvantagem e que se precisar reiniciar ou desligar um afetará todos.
     *
     * @param bot Bot a ser injetado
     */
    @Autowired
    private void initBotGoias(final ExemploBot bot) {
        try {
            telegram = new TelegramBotsApi();
            log.info("Inicializando Goiás Exemplo Bot...");
            telegram.registerBot(bot);
            log.info("Goiás Exemplo Bot iniciado.");
        } catch (TelegramApiException e) {
            log.error(e);
        }
    }

}