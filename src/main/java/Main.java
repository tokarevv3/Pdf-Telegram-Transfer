import Telegram.TelegramSending;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import util.PropertiesUtil;
import static FolderListener.FolderListener.folderListener;

public class Main {
    public static void main(String[] args) {

        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            String token = PropertiesUtil.get("bot.token");
            botsApplication.registerBot(token, new TelegramSending());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        folderListener(PropertiesUtil.get("path"));
    }
}
