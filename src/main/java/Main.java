import FolderListener.FolderListener;
import Telegram.TelegramSending;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import util.PropertiesUtil;

public class Main {
    public static void main(String[] args) {

        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            String token = PropertiesUtil.get("bot.token");
            botsApplication.registerBot(token, new TelegramSending());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        FolderListener.folderListener(PropertiesUtil.get("path"));
    }
}
