package tech.dirwul.nocoshop.dispatchers;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Keyboards {

	public static Keyboard mainReply() {
		return new ReplyKeyboardMarkup("Услуги", "Консультация")
			.resizeKeyboard(true)
			.oneTimeKeyboard(true);
	}

	public static Keyboard servicesReply() {
		return new InlineKeyboardMarkup(
			new InlineKeyboardButton("1")
				.callbackData("SERVICES:1"),
			new InlineKeyboardButton("2")
				.callbackData("SERVICES:2"),
			new InlineKeyboardButton("3")
				.callbackData("SERVICES:3")
		);
	}
}
