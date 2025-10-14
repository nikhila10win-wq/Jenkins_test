<#assign preferences = freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />
<@liferay_portlet["runtime"]
    defaultPreferences="${preferences}"
    instanceId="sports_footer"
    portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
/>