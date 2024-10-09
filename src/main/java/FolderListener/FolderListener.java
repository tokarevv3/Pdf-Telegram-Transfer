package FolderListener;

import Telegram.TelegramSending;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;

public class FolderListener {

    /**
     * Creating WatchService to folder to watch created file if it is pdf.
     * Then send it to Telegram
     * @param path a full path to requiring folder
     */
    public static void folderListener(String path)  {

        Path dir = Path.of(path);
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            // Create folderListener on new files.
            dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;

            do {
                key = watchService.take();
                Kind<?> kind;

                for (WatchEvent<?> event : key.pollEvents()) {
                    kind = event.kind();


                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        String fileName = event.context().toString();
                        System.out.println("Found a new file in directory: " + fileName);
                        if (isPdf(fileName)) { // Check if new file is pdf
                            TelegramSending.sendFile(event.context().toString()); // Send file to Telegram dialog
                        } else {
                            System.out.println("File is not pdf. Skipped from transfer.");
                        }
                    }
                }
            } while (key.reset());

        } catch (IOException ignored) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check if this file has pdf extension.
     * @param fullName name for a file include extension
     * @return <code>True</code> if this file is pdf, else <code>False</code>
     */
    private static boolean isPdf(String fullName) {
        return fullName.endsWith(".pdf");
    }
}
