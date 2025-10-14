package com.mhdsys.notification.events.listener;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.notification.events.util.EmailNotificationUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Lenovo
 */
@Component(
		immediate = true, 
		property = {
		"destination.name=mhdsys/email-parallel-message"
		}, service = MessageListener.class)
public class EmailNotificationMessageListener implements MessageListener {
	private static Log LOGGER = LogFactoryUtil.getLog(EmailNotificationMessageListener.class);

	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			  LOGGER.debug("EmailNotificationMessageListener:::::::::::::::");
			  if(Validator.isNotNull(message)) {
				  LOGGER.debug("message is not null.");
				  Map<String, String> emailContent = (Map<String, String>) message.get("emailContent");
				  EmailNotificationUtil emailNotificationUtil = new EmailNotificationUtil();
		    	  emailNotificationUtil.sendEmailNotification(emailContent, message.getLong("companyId"), 
		    			  message.getString("templateName"), message.getString("recieverEmail"));
			  }else {
				  LOGGER.error("Message is null.");
			  }
			} catch (Exception e) {
				LOGGER.error("Exception in sending email notification -" + e.getMessage());
			}
		}
}