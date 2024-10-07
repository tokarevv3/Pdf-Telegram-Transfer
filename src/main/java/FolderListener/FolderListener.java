package FolderListener;

import Telegram.TelegramSending;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;

public class FolderListener {

    public static void folderListener(String path)  {

        Path dir = Path.of(path);
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;

            do {
                key = watchService.take();
                Kind<?> kind;

                for (WatchEvent<?> event : key.pollEvents()) {
                    kind = event.kind();

                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("Created a new file in directory: " + event.context().toString());
                        TelegramSending.sendFile(event.context().toString());
                    }
                }
            } while (key.reset());

        } catch (IOException ignored) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
