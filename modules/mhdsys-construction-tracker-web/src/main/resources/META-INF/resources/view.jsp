<%@page import="mhdsys.construction.tracker.web.constants.MhdsysConstructionTrackerWebPortletKeys"%>
<%@ include file="/init.jsp"%>

<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdsysConstructionTrackerWebPortletKeys.REDIRECT %>" />
</portlet:renderURL>

<div class="container-fluid dashboardPage">

<div class="row">
	<div class="col-12 d-flex justify-content-end mb-2"> 
		<a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
			<i class="bi bi-arrow-left-circle"></i> Back
		</a>
	</div>
</div>
 
	<div class="row">
		  <c:if test="${isDSO || isTSO}">
		  
			 <c:if test="${!isAlreadySubmitted}"> 
				<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="${redirectUrl}&type=initiateForm"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/512/11287/11287228.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="new-construction-from" />
								</p>
							</div>
						</div>
					</a>
				</div>
			</c:if>
			<c:if test="${isAlreadySubmitted}"> 
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${redirectUrl}&type=dsoView"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/12020/12020371.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="construction-details" />
							</p>
						</div>
					</div>
				</a>
			</div>
			</c:if> 
		</c:if>
		
		  <c:if test="${isDDD || isHOAdmin }">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${redirectUrl}&type=dddHoView"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/12020/12020371.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="construction-tracker-list" />
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
		
	</div>
</div>

