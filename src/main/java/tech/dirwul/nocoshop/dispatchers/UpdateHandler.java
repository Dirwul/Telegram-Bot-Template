package tech.dirwul.nocoshop.dispatchers;

import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import tech.dirwul.nocoshop.core.TSender;

public abstract class UpdateHandler {

	@Autowired
	public TSender sender;

	protected abstract boolean supports(Update update);

	protected abstract void handle(Update update);
}