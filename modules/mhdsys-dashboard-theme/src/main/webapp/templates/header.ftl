<#assign UserNotificationEventLocalService=serviceLocator.findService("com.liferay.portal.kernel.service.UserNotificationEventLocalService") />
<#assign profileImageURL = user.getPortraitURL(themeDisplay)>
<#assign preferences = freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />
<#assign user = themeDisplay.getUser() />
<#assign UserNotificationEventLocalService=serviceLocator.findService("com.liferay.portal.kernel.service.UserNotificationEventLocalService") />
<#--Header changes-->
<header id="banner" role="banner" class="maharashtraSports-header">
    <div class="deshbordHeaderLogo">
        <div class="logo" role="heading">
            <a class="${logo_css_class}" href="/" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
                <img alt="${logo_description}" src="${images_folder}/logos/logo.png" class="img-fluid" />
            </a> 
        </div>
        <div class="mob-logo d-none">
            <a href="/" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
                <img alt="${logo_description}" src="${images_folder}/logos/mg-logo.png" class="img-fluid" />
            </a> 
        </div>
    </div>

    <div class="deshbordHeader-content">
		<div class="headerContent-left">
			<button type="button" class="hamburger " id="hamburger-6">
              <span class="line"></span>
              <span class="line"></span>
              <span class="line"></span>
	          </button>
			<h3>${the_title}</h3>
		</div> 	
		
		<div class="headerContent-right">
			<div class="lr-search">  
                <@liferay_portlet["runtime"]
                    defaultPreferences="${preferences}"
                    portletName="com_liferay_portal_search_web_search_bar_portlet_SearchBarPortlet"
                />
            </div>

            <div class="lang-convert">  
                <@liferay_portlet["runtime"]
                    portletProviderAction=portletProviderAction.VIEW
                    portletProviderClassName="com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry"
                />
            </div>
			
			<div class="">
				<img src="${images_folder}/theme-swither.png" alt="" class="headerIcon pointer" id="darkModeToggle" title="Theme Select">
			</div>
			
			<div class="dropdown">
				<div class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					<img src="${images_folder}/noti-bell.png" alt="" class="headerIcon pointer" />      
				</div>  
				 
                <div class="dropdown-menu notification shadow border-0 rounded-0 animate__animated animate__fadeIn p-0 dropdown-menu-right"> 
                	<@liferay_portlet["runtime"]
							defaultPreferences="${preferences}"
							portletName="com_liferay_notifications_web_portlet_NotificationsPortlet"
						/> 
                </div> 
			</div> 
			
			
			<#if is_signed_in>
				<div class="profile dropdown">
					<div class="userProfile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						<div class="profileImg">						 
							 <img src="${profileImageURL}" title="My Profile" data-toggle="dropdown" aria-expanded="false" alt="${user_name}" class="rounded-circle dropdown-toggle pointer" /> 
						</div>
						<div class="profileName d-none d-lg-block">
							<h5>${user_name}</h5>
							<p class="m-0">${user_email_address}</p>
						</div>
					</div>  
					 
                    <div class="dropdown-menu dropdown-menu-right shadow border-0 rounded-0 animate__animated animate__fadeIn p-0"> 
                     	<a href="/group/guest/profile" class="text-decoration-none dropdown-item p-3">
                          <span class="icon">
                          <i class="bi bi-person"></i>
                          </span>
                         <@liferay.language key="my-profile" />
                      </a>  
                      
                    <a href="/c/portal/logout" class="text-decoration-none dropdown-item p-3">
                          <span class="icon">
                            <i class="bi bi-door-open"></i>
                          </span>
                          <@liferay.language key="log-out" />
                      </a>              
                    </div> 
				</div>
			</#if> 
		</div>
	</div>  
</header>