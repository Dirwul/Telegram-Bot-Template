package tech.dirwul.nocoshop.dispatchers.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import tech.dirwul.nocoshop.dispatchers.UpdateHandler;

@Component
public class EchoHandler extends UpdateHandler {

	@Override
	public boolean supports(Update update) {
		return false; // todo rework
	}

	@Override
	public void handle(Update update) {
		sender.sendAsync(new SendMessage(
			update.message().chat().id(),
			update.message().text()
		));
	}
}