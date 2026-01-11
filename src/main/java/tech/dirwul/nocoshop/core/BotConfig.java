package tech.dirwul.nocoshop.core;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.DeleteWebhook;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {

	private static final Logger log = LoggerFactory.getLogger(BotConfig.class);

	private final String webhook;
	final TelegramBot bot;

	public BotConfig(
		@Value("${telegram.token}") String token,
		@Value("${telegram.webhook.url}") String webhookUrl,
		@Value("${telegram.webhook.path}") String webhookPath
	) {
		this.webhook = webhookUrl + webhookPath;
		this.bot = new TelegramBot(token);
	}

	@Bean
	public TelegramBot telegramBot() {
		return this.bot;
	}

	@PostConstruct
	public void postConstruct() {
		BaseResponse response = bot.execute(
			new SetWebhook().url(webhook)
		);
		log.debug("SetWebhook status: {}", response.isOk());
	}

	@PreDestroy
	public void preDestroy() {
		BaseResponse response = bot.execute(new DeleteWebhook());
		log.debug("Delete webhook status: {}", response.isOk());
	}
}
