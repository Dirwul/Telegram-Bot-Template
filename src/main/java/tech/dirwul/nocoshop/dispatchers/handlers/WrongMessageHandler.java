package tech.dirwul.nocoshop.dispatchers.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import tech.dirwul.nocoshop.dispatchers.UpdateHandler;

@Component
public class WrongMessageHandler extends UpdateHandler {

	@Override
	protected boolean supports(Update update) {
		return false;
	}

	@Override
	public void handle(Update update) {
		sender.sendAsync(new SendMessage(
			(long) update.message().chat().id(),
			"Неизвестная команда..."
		));
	}
}
