package tech.dirwul.nocoshop.core;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.utility.BotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.dirwul.nocoshop.dispatchers.UpdateDispatcher;

@RestController
public class UpdateListenerConfig {

	private static final Logger log = LoggerFactory.getLogger(UpdateListenerConfig.class);

	private final UpdateDispatcher dispatcher;

	@Autowired
	public UpdateListenerConfig(UpdateDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	@PostMapping("${telegram.webhook.path}")
	public void onUpdate(@RequestBody String json) {
		Update update = BotUtils.parseUpdate(json);
		if (update == null) {	// bad request
			log.warn("Invalid json received, UPDATE is null");
			return;
		}
		if (update.message() == null) {	// bad request
			log.warn("Invalid json received, MESSAGE is null");
			return;
		}

		dispatcher.dispatch(update);
	}
}
