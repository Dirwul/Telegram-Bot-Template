package tech.dirwul.nocoshop.core;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {

	private static final Logger log = LoggerFactory.getLogger(BotConfig.class);

	private final String webhookUrl;
	final TelegramBot bot;

	public BotConfig(
		@Value("${telegram.bot.token}") String token,
		@Value("${telegram.bot.webhook}") String webhookUrl
	) {
		this.webhookUrl = webhookUrl;
		this.bot = new TelegramBot(token);
	}

	@Bean
	public TelegramBot telegramBot() {
		return this.bot;
	}

	@PostConstruct
	public void postConstruct() {
		BaseResponse response = bot.execute(
			new SetWebhook().url(webhookUrl)
		);
		log.warn("SetWebhook status: {}", response.isOk());
	}
}
