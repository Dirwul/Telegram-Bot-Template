package tech.dirwul.nocoshop.dispatchers.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import tech.dirwul.nocoshop.dispatchers.Keyboards;
import tech.dirwul.nocoshop.dispatchers.UpdateHandler;

import java.util.Set;

@Component
public class StartHandler extends UpdateHandler {

	@Override
	public boolean supports(Update update) {
		return Set.of("/start").contains(update.message().text());
	}

	@Override
	public void handle(Update update) {
		sender.sendAsync(new SendMessage(
			(long) update.message().chat().id(),
			"А вот и темплейт для бота от Dirwul'a")
			.disableNotification(true)
			.parseMode(ParseMode.HTML)
			.replyMarkup(Keyboards.startReply())
		);
	}
}
