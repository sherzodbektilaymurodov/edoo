package auf.group.edu.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ButtonSettings {
    @Autowired
    BotSettings botSettings;

    //inlineButton
    private List<List<InlineKeyboardButton>> getInlineButtonRows(List<String> data) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        int length = data.size() % 2 != 0 ? data.size() - 1 : data.size();
        for (int i = 0; i < length; i += 2) {
            List<InlineKeyboardButton> inlineButton = new ArrayList<>();
            inlineButton.add(getInlineButton(data.get(i), data.get(i)));
            inlineButton.add(getInlineButton(data.get(i + 1), data.get(i + 1)));
            rows.add(inlineButton);
        }
        if (data.size() % 2 != 0) {
            String text = data.get(data.size() - 1);
            rows.add(Collections.singletonList(getInlineButton(text, text)));
        }
        return rows;
    }

    private InlineKeyboardButton getInlineButton(String text, String callback) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setCallbackData(callback);
        inlineKeyboardButton.setText(text);
        return inlineKeyboardButton;
    }

    public InlineKeyboardMarkup getInlineMarkup(List<String> list) {
        return new InlineKeyboardMarkup(getInlineButtonRows(list));
    }

    //keyboardButton
    public void keyboardButton(Message message, SendMessage sendMessage, String text, List<String> data) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        int length = data.size() % 2 != 0 ? data.size() - 1 : data.size();
        for (int i = 0; i < length; i += 2) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(getKeyboardButton(data.get(i)));
            keyboardRow.add(getKeyboardButton(data.get(i + 1)));
            keyboardRows.add(keyboardRow);
        }
        KeyboardRow keyboardRow2 = new KeyboardRow();
        if (data.size() % 2 != 0) {
            keyboardRow2.add(getKeyboardButton(data.get(data.size() - 1)));
        }
        keyboardRows.add(keyboardRow2);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(markup);
        botSettings.sendMSG(sendMessage, text, message);
    }

    private KeyboardButton getKeyboardButton(String text) {
        return new KeyboardButton(text);
    }
}
