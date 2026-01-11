package tech.dirwul.nocoshop.dispatchers;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Keyboards {

	public static Keyboard startReply() {
		return new ReplyKeyboardMarkup("But1", "But2")
			.resizeKeyboard(true)
			.oneTimeKeyboard(true);
	}

	public static Keyboard servicesReply() {
		return new InlineKeyboardMarkup(
			new InlineKeyboardButton("But1")
				.callbackData("SERVICES:1"),
			new InlineKeyboardButton("But2")
				.callbackData("SERVICES:2")
		);
	}
}
