<header>
  <div class="top-bar">
    <div class="container-fluid">
      <div class="top-bar-slid">
        <div>
          <div class="phone-data">
            <div class="phone d-flax align-items-center">
              <i>
                <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 512 512" style="enable-background:new 0 0 512 512;" xml:space="preserve">
                    <path d="M437.15,74.817C388.895,26.571,324.561,0,256,0C187.587,0,123.279,26.65,74.92,75.041
                      C26.559,123.435-0.048,187.766,0,256.184c0.048,68.507,27.005,132.938,75.905,181.425C124.335,485.629,188.219,512,255.997,512
                      c0.677,0,1.357-0.002,2.035-0.008c44.288-0.345,87.858-12.192,126.001-34.262l-15.024-25.967
                      c-33.653,19.472-72.109,29.925-111.21,30.23c-60.48,0.456-117.575-22.858-160.77-65.688C53.847,373.49,30.043,316.616,30,256.163
                      C29.958,195.762,53.447,138.97,96.141,96.247C138.832,53.527,195.605,30,256,30c124.595,0,225.979,101.365,226,225.959
                      c0.008,49.387-15.621,96.298-45.198,135.661c-2.573,3.424-6.37,5.478-10.692,5.784c-4.368,0.308-8.658-1.291-11.756-4.388
                      l-20.406-20.406l9.06-9.06l-70.711-70.711l-28.284,28.284c-58.885-7.935-105.202-54.252-113.137-113.137l28.284-28.284
                      l-70.711-70.711l-39.054,39.054c-3.826,66.249,19.552,133.776,70.167,184.391s118.142,73.993,184.391,70.167l8.782-8.781
                      l20.406,20.406c9.247,9.247,22.033,14.022,35.082,13.1c12.935-0.913,24.803-7.36,32.563-17.688
                      C494.3,365.039,512.01,311.895,512,255.954C511.988,187.393,485.406,123.064,437.15,74.817z"></path>
                </svg>
              </i>
              <a class="me-3" href="callto:9529318972"> 9529318972</a>
            </div>
            <div class="phone">
              <i>
                <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 512 512" style="enable-background:new 0 0 512 512;" xml:space="preserve">
                <path d="M0,81v350h512V81H0z M456.952,111L256,286.104L55.047,111H456.952z M30,128.967l134.031,116.789L30,379.787V128.967z
                   M51.213,401l135.489-135.489L256,325.896l69.298-60.384L460.787,401H51.213z M482,379.788L347.969,245.756L482,128.967V379.788z"></path>
                </svg>
              </i>
              <a href="mallto:desk14.dsys-mh@gov.in"> desk14.dsys-mh@gov.in</a>
            </div>
          </div>
        </div>
        
        <div>
          <div class="social-media">
            <ul class="social-media-icon mb-0">
            	<li class='d-none'> 
            	<a href="javascript:;">
                    <i class="bi bi-brightness-high-fill icon" id="theme-icon"></i>  
                    </a>    
                </li>
                
          		<li class="languageDropdown position-relative">

    				<@liferay_portlet["runtime"]
     					 portletProviderAction=portletProviderAction.VIEW
     					 portletProviderClassName="com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry"
     					 instanceId="sports_search"
   					 />
  		 				
				</li>
          		
                
                <li> 
                	<a href="https://www.facebook.com/people/Sports-Maharashtra/61555724847239/" target="_blank">
                   <i class="bi bi-facebook icon"></i>
                   </a>
                </li>
                
                <li>
                  <a href="https://x.com/Dsys_MHOfficial?t=b4gzc84jRKteLtX_HTNOKg&amp;s=09" target="_blank">
                    <i class="bi bi-twitter-x icon"></i>    
                  </a>
                </li>
                
                <li>
                  <a href="https://www.instagram.com/dsys_maharashtraofficial/?igshid=OGQ5ZDc2ODk2ZA%3D%3D" target="_blank"><i class="bi bi-instagram icon"></i></a>
                </li> 
            </ul>
           <#if is_signed_in>
           <div class="mx-3 boder"></div>
				<div class="profile dropdown">
					<div class="userProfile dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> 
						<h6 class="mx-2 mt-2">${user_name}</h6>
					</div>  
					 
                    <div class="dropdown-menu dropdown-menu-right shadow border-0 rounded-0 animate__animated animate__fadeIn p-0"> 
                     	<a href="/group/guest/profile" class="text-decoration-none dropdown-item p-3">
                          <span class="icon">
                          <i class="bi bi-person"></i>
                          </span>
                         <@liferay.language key="my-profile" />
                      </a> 
                      <a href="/group/guest/dashboard" class="text-decoration-none dropdown-item p-3">
                          <span class="icon">
                            <i class="bi bi-ui-checks-grid"></i>
                          </span>
                          <@liferay.language key="dashboard" />
                      </a> 
                      
                    <a href="/c/portal/logout" class="text-decoration-none dropdown-item p-3">
                          <span class="icon">
                            <i class="bi bi-door-open"></i>
                          </span>
                          <@liferay.language key="log-out" />
                      </a>              
                    </div> 
				</div>
			
			<#else>
			
			<div class="mx-3 boder"></div>
            <ul class="list-inline mb-0">
            	<li class="list-inline-item">
            		<a href="/login" class="text-decoration-none text-white">
            			<i class="bi bi-box-arrow-in-left mr-2"></i> <@liferay.language key="login" />
            		</a>
            	</li>
            	
            	
            </ul>
            <div class="mx-3 boder"></div>
            <ul class="m-0 p-0">
            	<li class="list-inline-item">  
					<a href="/registration" class="text-decoration-none text-white">
				 		<i class="bi bi-person mr-2"></i> <@liferay.language key="registration" />
				 	</a>
            	</li>
            </ul> 
  </#if> 
     
          </div>
        </div>
      </div>
    </div>
  </div> 
  
  <#include "${full_templates_path}/navigation.ftl" /> 
  
  <#--Mobile Navigation-->
  <div class="mobileNavigaiton p-3 bg-white">
  	<div class="align-items-center d-flex justify-content-between">
	  <div class="mobile-logo">
		  <a href="/">
		    <img alt="logo" src="${site_logo}" class="white-logo">              
		  </a>
	  </div>
	  
	  <div class="mNavbar">
	  	<#include "${full_templates_path}/navigation.ftl" />
	  </div> 
	  </div>   	
  </div>
  <#--Mobile Navigation-->
</header>







<style>
.languageDropdown .dropdown-menu .dropdown-item {
	width: 0;
    height: 0;
    border: 0;
    margin: 0;
    overflow: visible;
}

.languageDropdown .dropdown-menu a.drop-down:before {
    background: transparent;
}
</style>