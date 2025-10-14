package com.mhdsys.notification.events.util;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
@Component(immediate = true, service = EmailNotificationUtil.class)


public class EmailNotificationUtil{

	private static Log LOGGER = LogFactoryUtil.getLog(EmailNotificationUtil.class);
	
	public void sendEmailNotification(Map<String, String> emailContent, long companyId, String templateName,
			String recieverEmail) {
		String senderEmail = PrefsPropsUtil.getString(getGlobalCompanyGroupId(companyId),PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
		long groupId = getSiteGroupIdByName(companyId,"Guest");
		String subject=StringPool.BLANK;
		String emailBody=StringPool.BLANK;
		JournalArticle article = getJournalArticleByTemplateName(groupId, templateName);
		if (Validator.isNotNull(article)) {
			subject = readXMLData(article.getContent(), "EMAIL_SUBJECT");
			emailBody = readXMLData(article.getContent(), "EMAIL_BODY");
		}
		String[] oldSubs = new String[emailContent.size()];
		String[] newSubs = new String[emailContent.size()];
		int i=0;
		for (Map.Entry<String, String> entry : emailContent.entrySet()) {
			oldSubs[i]= "["+entry.getKey()+"]";
			newSubs[i]= entry.getValue();
			i++;
		}
		subject = StringUtil.replace(subject, oldSubs, newSubs);
		emailBody = StringUtil.replace(emailBody, oldSubs, newSubs);
		
		sendEmail(senderEmail, recieverEmail, subject, emailBody);
		
	}

	public void sendEmail(String senderEmail, String recieverEmail, String subject, String body) {
		try {
			MailMessage mailMessage = new MailMessage();
			LOGGER.info("Mail sending started senderEmail"+senderEmail+" recieverEmail "+recieverEmail+" subject "+subject+"  body  "+body);
			mailMessage.setFrom(new InternetAddress(senderEmail));
			mailMessage.setTo(new InternetAddress(recieverEmail));
			mailMessage.setSubject(subject);
			mailMessage.setBody(body);
			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			LOGGER.info("Mai sending ended");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @param content
	 * @param key
	 * @return
	 */
	private String readXMLData(String content, String key) {
		String fieldValue = StringPool.BLANK;
		try {
			Document document = SAXReaderUtil.read(content);
			Node node = document
					.selectSingleNode("/root/dynamic-element[@field-reference='" + key + "']/dynamic-content");
			if (Validator.isNotNull(node)) {
				return node.getText();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return fieldValue;

	}
	
	/**
	 * 
	 * @param companyId
	 * @return
	 */
	private long getGlobalCompanyGroupId(long companyId) {
		long groupId = 0l;
		Company company = null;
		try {
			company = CompanyLocalServiceUtil.fetchCompany(companyId);
			groupId = company.getGroup().getGroupId();
		} catch (Exception e) {
			LOGGER.error("Exception in getting groupId::" + e.getMessage());
		}
		return groupId;
	}
	
	/**
	 * Get site groupId by site (group) name.
	 *
	 * @param companyId the company ID
	 * @param siteName the name of the site (as shown in UI)
	 * @return the groupId of the site, or 0 if not found
	 */
	private long getSiteGroupIdByName(long companyId, String siteName) {
		long groupId = 0L;

		try {
			List<Group> siteGroups = GroupLocalServiceUtil.getGroups(companyId, 0, true);
			Locale locale = LocaleUtil.fromLanguageId("en_US");
			for (Group group : siteGroups) {
				if (group.getName(locale).equalsIgnoreCase(siteName)) {
					groupId = group.getGroupId();
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in getting site groupId: " + e.getMessage(), e);
		}

		return groupId;
	}
	
	/**
	 * 
	 * @param groupId
	 * @param templateName
	 * @return
	 */
	private JournalArticle getJournalArticleByTemplateName(Long groupId, String templateName) {
		JournalArticle article=null;
		try {
			article = JournalArticleLocalServiceUtil.getArticleByUrlTitle(groupId, templateName);
		} catch (PortalException e) {
			LOGGER.error(e.getMessage());
		}
		return article;
	}
	
	
}