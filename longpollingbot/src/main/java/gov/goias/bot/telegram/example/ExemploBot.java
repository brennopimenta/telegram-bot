package gov.goias.bot.telegram.example;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Classe de implementação do Bot Exemplo LongPolling.
 */
@Component
public class ExemploBot extends TelegramLongPollingBot{
    private final Logger log = Logger.getLogger(getClass());

    private static final String COMMAND_START = "/start";

    /**
     * Método obrigatório que será invocado sempre que houver uma atualização no servidor.

     * @param update  Atualização Corrente.
     */
    @Override
    public void onUpdateReceived(final Update update) {
        if(update.hasMessage()){
            switch (update.getMessage().getText()){
                case COMMAND_START :
                    actionStart(update.getMessage());
                    break;
                default:
                    actionDefault(update.getMessage());
                    break;
            }
        }
    }

    /**
     * Metodo que atende a ação START(inicial) do bot.
     *
     * @param message Mensagem inicial.
     */
    private void actionStart(final Message message){
        try{
            final SendMessage send = new SendMessage();
            send.setChatId(message.getChatId());
            send.setText(String.format("Ola, %s seja bem vindo ao bot exemplo", message.getFrom().getFirstName()));
            sendMessage(send);
        }catch(TelegramApiException e){
            log.error(e);
        }
    }


    /**
     * Método que atende as demais ações repassadas para o bot.
     *
     * @param message Mensagem do usuário.
     */
    private void actionDefault(final Message message){
        try{
            final SendMessage send = new SendMessage();
            send.setChatId(message.getChatId());
            send.setText(String.format("%s, no momento ainda não consigo fazer nada para te ajudar, mas aguarde logo serei útil", message.getFrom().getFirstName()));
            sendMessage(send);
        }catch(TelegramApiException e){
            log.error(e);
        }
    }


    /**
     * Método Obrigatório que retorna o token do bot (chamado pela API).
     *
     * @return String token.
     */
    @Override
    public String getBotToken() {
        return "364272917:AAG5LZ5zvlNSSKarJGVUGxZ1ZACNVe9JlQw";
    }

    /**
     * Método obrigatório que retorna o usuário do bot.
     *
     * @return String usuario.
     */
    @Override
    public String getBotUsername() {
        return "goiasexemplo";
    }

}