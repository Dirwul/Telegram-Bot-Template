package tech.dirwul.nocoshop.dispatchers;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateDispatcher {

	private final Logger log = LoggerFactory.getLogger(UpdateDispatcher.class);

	private final List<UpdateHandler> handlers;

	public void dispatch(Update update) {
		for (UpdateHandler handler : handlers) {
			if (handler.supports(update)) {
				handler.handle(update);
				return;
			}
		}
		// if no handle candidate
		log.warn("No handler found for update {}", update);
	}
}