package tech.dirwul.nocoshop.dispatchers;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.dirwul.nocoshop.dispatchers.handlers.WrongMessageHandler;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateDispatcher {

	private final List<UpdateHandler> handlers;

	private final WrongMessageHandler wrongMessageHandler;

	public void dispatch(Update update) {
		for (UpdateHandler handler : handlers) {
			if (handler.supports(update)) {
				handler.handle(update);
				return;
			}
		}
		// if no handler candidates
		wrongMessageHandler.handle(update);
	}
}