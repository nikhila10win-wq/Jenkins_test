<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>MDSYS : ${the_title}</title>
	
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
	
	<@liferay_util["include"] page=top_head_include />	
	
	<link rel="stylesheet" href="${css_folder}/plugins/bootstrap.min.css">
	
	<link rel="stylesheet" href="${css_folder}/bootstrap-icons.min.css">
	
	<link rel="stylesheet" href="${css_folder}/plugins/animate.min.css">
		
		
</head>

<body class="${css_class} lfr-tooltip-scope">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<#if is_signed_in && permissionChecker.isOmniadmin()>
	<@liferay.control_menu /> 
</#if>

<div class="maharashtraSports-dashboard-wrapper closed-sidebar" id="wrapper">	 
	<#include "${full_templates_path}/header.ftl" /> 	 

	<div class="maharashtraSports-main-content">
		<#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if> 
	

		<div class="maharashtraSports-body-main">		
			<section id="content" class="maharashtraSports-page-main">		
			<div class="maharashtraSports-breadcrumbs d-none">
				<@liferay.breadcrumbs />
			</div>	
				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}
		
					${portletDisplay.setTitle(the_title)}
		
					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>
			</section>  
		</div>
	</div> 
</div>

 

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<script src="${javascript_folder}/plugins/jquery.min.js" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="${javascript_folder}/plugins/bootstrap.bundle.min.js" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="${javascript_folder}/plugins/jquery.validate.js" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="${javascript_folder}/plugins/additional-methods.js" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>

</html>