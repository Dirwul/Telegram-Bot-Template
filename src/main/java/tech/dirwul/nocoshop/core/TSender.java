package tech.dirwul.nocoshop.core;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TSender {

	private static final Logger log = LoggerFactory.getLogger(TSender.class);

	@Autowired
	private TelegramBot bot;

	public <T extends BaseRequest<T, R>, R extends BaseResponse> void sendAsync(T request) {
		bot.execute(request, new Callback<T, R>() {
			@Override
			public void onResponse(T request, R response) {
				log.info("Сообщение доставлено!");
			}
			@Override
			public void onFailure(T request, IOException e) {
				log.error("Сообщение не доставлено :(\nОшибка: {}", e.getMessage());

				sendAsync(new SendMessage(
					request.getParameters().get("chat_id"),
					"Извините, при попытке вам ответить произошла ошибка на сервере: "
						+ e.getMessage())
					.parseMode(ParseMode.HTML)
					.disableNotification(true)
				);
			}
		});
	}
}
