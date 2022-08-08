package auf.group.edu.bot;

import auf.group.edu.entity.Subject;
import auf.group.edu.entity.User;
import auf.group.edu.repository.AuthRepository;
import auf.group.edu.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Service
public class BotSettings extends TelegramLongPollingBot {

    final AuthRepository authRepository;
    final SubjectRepository subjectRepository;

    private final ButtonSettings buttonSettings = new ButtonSettings();

    private final List<Subject> getSubject = new ArrayList<>();
    private final Map<Long, String> isTan = new HashMap<>();
    private final Map<Long, String> firstName = new HashMap<>();
    private final Map<Long, String> lastName = new HashMap<>();
    private final Set<Long> userChatId = new HashSet<>();

    public BotSettings(AuthRepository authRepository, SubjectRepository subjectRepository) {
        this.authRepository = authRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/start")) {
                    sendMessage.setReplyMarkup(buttonSettings.getInlineMarkup(Template.START_BUTTON));
                    sendMSG(sendMessage, "Assalomu alekum " + message.getFrom().getFirstName() + " botimizga hush kelipsiz!\n" +
                            "bulimni tanlang", message);
                }
                try {
                    if (isTan.get(chatId).equals("firstName")) {
                        editMsg(message, "famliyangizni kiriting!");
//                        sendMSG(sendMessage, "famliyangizni kiriting!", message);
                        firstName.put(chatId, text);
                        isTan.put(chatId, "lastName");
                    } else if (isTan.get(chatId).equals("lastName")) {
                        lastName.put(chatId, text);
                        isTan.put(chatId, "phoneNumber");
                        buttonPhoneNumber(sendMessage, message);
                    }
                }catch (Exception e) {
                    System.err.println("Exception");
                }
            } else if (message.hasContact()) {
                delMsg(message);
                message.setMessageId(message.getMessageId() - 1);
                delMsg(message);
                saveUser(message.getContact().getPhoneNumber(), message);
                sendMessage.setReplyMarkup(buttonSettings.getInlineMarkup(Template.START_BUTTON));
                sendMSG(sendMessage, "Siz muaffaqiyatle ruyxatdan utdingiz siz bilan adminstratorlarimiz bog'lanishadi!", message);
            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Message message = update.getCallbackQuery().getMessage();
            Long chatId = message.getChatId();
            try {
                if (isTan.get(chatId).equals("getName")) {
                    for (Subject subject : getSubject)
                        if (data.equals(subject.getName())) {
                            sendMessage.setReplyMarkup(buttonSettings.getInlineMarkup(Template.BACK));
                            sendMSG(sendMessage, getDescription(data), message);
                        }
                }
            }catch (Exception e) {
                System.err.println("Exception");
            }
            switch (data) {
                case "Register":
                    if (isRegister(chatId)) {
                        sendMessage.setReplyMarkup(buttonSettings.getInlineMarkup(Template.BACK_MENU));
                    }else {
                        sendMSG(sendMessage, "ismingizni kiriting!", message);
                        isTan.put(chatId, "firstName");
                    }

                    break;
                case "Info":
                    sendMessage.setReplyMarkup(buttonSettings.getInlineMarkup(Template.BACK_MENU));
                    sendMSG(sendMessage, "bu yirga uquv markaz haqida malumot yoziladi", message);
                    break;
                case "Kurslar":
                case "Back":
                    sendMessage.setReplyMarkup(buttonSettings.getInlineMarkup(subjectNameList()));
                    sendMSG(sendMessage, "Kursni tanlang!", message);
                    isTan.put(chatId, "getName");
                    break;
                case "back":
                    sendMessage.setReplyMarkup(buttonSettings.getInlineMarkup(Template.START_BUTTON));
                    sendMSG(sendMessage, "siz bosh menu dasiz!", message);
                    break;
            }
        }
    }

    //Message
    public void sendMSG(SendMessage sendMessage, String text, Message message) {
        try {
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText(text);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("not execute");
        }
    }

    public boolean isRegister(Long id) {
        for (Long chatId : userChatId) if (chatId.equals(id)) return true;
        return false;
    }

    public void editMsg(Message message, String text) {
        delMsg(message);
        EditMessageText messageText = new EditMessageText();
        messageText.setMessageId(message.getMessageId() - 1);
        messageText.setChatId(message.getChatId());
        messageText.setText(text);
        messageText.setParseMode(ParseMode.MARKDOWN);
        messageText.setDisableWebPagePreview(true);
        try {
            execute(messageText);
        } catch (TelegramApiException e) {
            System.err.println("qandaydur xatolik");
        }
    }

    public void delMsg(Message message) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(message.getChatId().toString());
        deleteMessage.setMessageId(message.getMessageId());
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            System.err.println("no del");
        }
    }

    //markup button
    public void buttonPhoneNumber(SendMessage sendMessage, Message message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("Telefon raqam");
        keyboardButton.setRequestContact(true);
        keyboardFirstRow.add(keyboardButton);
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMSG(sendMessage, "Telefon raqamingizni faqat chiqib turgan tugma orqalli yuboring", message);
    }

    public void saveUser(String phoneNumber, Message message) {
        authRepository.save(new User(
                firstName.get(message.getChatId()),
                lastName.get(message.getChatId()),
                phoneNumber,
                message.getChatId(),
                false
        ));
    }

    public void getUser() {
        for (User user : authRepository.findAll()) userChatId.add(user.getChatId());
    }

    public void addSubject() {
        getSubject.addAll(subjectRepository.findAll());
    }

    public List<String> subjectNameList() {
        List<String> getName = new ArrayList<>();
        for (Subject subject : getSubject) getName.add(subject.getName());
        getName.add("back");
        return getName;
    }

    public String getDescription(String name) {
        for (Subject subject : getSubject) return subject.getName().equals(name) ? subject.getDescription() : null;
        return null;
    }

    @Override
    public String getBotUsername() {
        return Template.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return Template.BOT_TOKEN;
    }
}
