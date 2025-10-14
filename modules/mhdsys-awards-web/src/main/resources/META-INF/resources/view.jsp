<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="awardsRedirectURL">
    <portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.AWARDS %>" />
</portlet:renderURL>
<portlet:renderURL var="applyAwardApplicationRedirectURL">
    <portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.AWARD_APPLICATION_REGISTERED_LIST_MVC_RENDER_COMMAND %>" />
</portlet:renderURL>

<div class="container-fluid dashboardPage">
	<div class="row">
		<div class="col-12 d-flex justify-content-end mb-2"> 
			<a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
			<i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back"/>
			</a>
		</div>
	</div>

	<div class="row">
	
		<c:if test="${isHOAdmin}">
			<%-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${awardsRedirectURL}&type=awardsPointCreation"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="awards-point-creation"/>
							</p>
						</div>
					</div>
				</a>
			</div> --%>
			
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/awards-points-list"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="awards-point-list"/>
							</p>
						</div>
					</div>
				</a>
			</div>
			
		</c:if>
		
		<c:if test="${isSportsPerson}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${awardsRedirectURL}&type=awardsApplicationSportsPerson"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="competition-registration"/>
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
		
		<c:if test="${isSportsCoach}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${awardsRedirectURL}&type=awardsApplicationCoach" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img src="https://cdn-icons-png.flaticon.com/128/8165/8165803.png"
								alt="">
							<p class="mb-0"><liferay-ui:message key="competition-registration"/></p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
		
		<c:if test="${isDeskOfficer}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${awardsRedirectURL}&type=reviewApplication"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="distribute-application-torespective-sports-desk-officers"/>
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
			
		<c:if test="${isDeskOfficer || isHOAdmin}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/association-verification-by-desk-officer"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="association-verification"/>
							</p>
						</div>
					</div>
				</a>
			</div>
			
		</c:if>
		
		
		<c:if test="${isDeputyDirector || isAssistantDirector}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/approve-desk-officers-assignment"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="approve-desk-officer-assignment"/>
							</p>
						</div>
					</div>
				</a>
			</div>
			
		</c:if>
		
		<c:if test="${isSportsDeskOfficer || isDeputyDirector || isAssistantDirector || isAssociation}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/verify-award-applications"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="verify-award-application"/>
							</p>
						</div>
					</div>
				</a>
			</div>
			
		</c:if>
		
		
		<c:if test="${isSportsPerson || isSportsCoach}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${applyAwardApplicationRedirectURL}&type=objection"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="competition-registered-list"/>
							</p>
						</div>
					</div>
				</a>
			</div>
			
		</c:if>
		
	<%-- <c:if test="${isAssistantDirector || isSportsCoach || isSportsPerson || isHOAdmin}">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${awardsRedirectURL}&type=objection"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="suggestion-objection"/>
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if> --%>
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/objection-list"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="objection-list"/>
							</p>
						</div>
					</div>
				</a>
			</div>
	</div>
</div>

					