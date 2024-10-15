# Pdf-Telegram Transfer
MacOS application using Java, Automator and Shortcuts apps to save .pages file as pdf and transfer into Telegram.\
Required an application.properties file in "resources" folder which contains:
1. path - A path to folder of new PDF-files.
2. bot.token - Token for telegram bot.
3. bot.chat - Id of chat of user and bot. May use "/chat" command to get id from bot while app is running.
##### Example:
```
path=/Users/tokarevv3/Results/
bot.token=213Reqs412Sr421
bot.chat=442143242
```
# How to use
1. Create folder "resources" in src/main/ folder.
2. Create application.properties file and fill it.
3. Build project into Pdf-Telegram-Transfer-jar-with-dependencies.jar file (may use 'mvn clean package'). It will auto appears in Pages-to-Pdf folder.
4. Edit and save "start.command" file to set your path to Pdf-Telegram-Transfer-jar-with-dependencies.jar file.
5. Edit and save "pagesToTelegramCommand.workflow" in Automator: set your "start.command" path in first action, your folder to select files in second action, your "Results" folder in.
6. Put .workflow into "Shortcuts" app and save in docker.