package Telegram;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import util.PropertiesUtil;

import java.io.File;

public class TelegramSending implements LongPollingSingleThreadUpdateConsumer {
    private static final TelegramClient telegramClient = new OkHttpTelegramClient(PropertiesUtil.get("bot.token"));

    @Override
    public void consume(Update update) {
        if (update.getMessage().toString().equals("/chat")) {
            String message = update.getMessage().getChatId().toString();
            long chatId = update.getMessage().getChatId();

            SendMessage msg = SendMessage
                    .builder()
                    .chatId(chatId)
                    .text(message)
                    .build();

            try {
                telegramClient.execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendFile(String fileName) {
        String filePath = new StringBuilder()
                .append(PropertiesUtil.get("path"))
                .append("/")
                .append(fileName)
                .toString();
        InputFile file = new InputFile(new File(filePath));

        SendDocument doc = SendDocument
                .builder()
                .chatId(PropertiesUtil.get("bot.chat"))
                .document(file).build();
        try {
            telegramClient.execute(doc);
            System.out.println("File has been sent to Telegram.");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
