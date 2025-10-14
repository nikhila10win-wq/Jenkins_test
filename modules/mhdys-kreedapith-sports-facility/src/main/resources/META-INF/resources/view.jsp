<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys"%>
<%@ include file="/init.jsp"%>

<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdysKreedapithSportsFacilityPortletKeys.REDIRECT %>" />
</portlet:renderURL>

<%-- <liferay-portlet:renderURL var="facilityRedirectUrl">
	<liferay-portlet:param name="mvcRenderCommandName" value="<%= MhdysKreedapithSportsFacilityPortletKeys.FACILITYBOOKINGREDIRECT %>" />
</liferay-portlet:renderURL>

 <liferay-portlet:renderURL var="facilityRedirectUrl" plid="${layoutObject.getString('organizationPlid','141') }" portletName="mhdys_MhdysFacilityBookingPortlet" windowState="<%=LiferayWindowState.NORMAL.toString()%>">
    <liferay-portlet:param name="mvcRenderCommandName" value="<%= MhdysKreedapithSportsFacilityPortletKeys.FACILITYBOOKINGREDIRECT %>" />
    <liferay-portlet:param name="type" value="fpoOrganizationDashboardView" />
    <liferay-portlet:param name="BACK_URL" value="/group/guest/admin-dashboard" />
</liferay-portlet:renderURL> --%>
 
 
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
				<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="${redirectUrl}&type=initiateForm"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/128/3703/3703609.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="approve-available-facility" />
								</p>
							</div>
						</div>
					</a>
				</div>
				<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="${redirectUrl}&type=dsoTsoFacilityList"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/128/2029/2029830.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="facility-list" />
								</p>
							</div>
						</div>
					</a>
				</div>
		</c:if>
		
		  <c:if test="${isHOAdmin }">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${redirectUrl}&type=initiateForm"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/3703/3703609.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="add-facility" />
							</p>
						</div>
					</div>
				</a>
			</div>
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${redirectUrl}&type=hoFacilityList"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/2029/2029830.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="facility-list" />
							</p>
						</div>
					</div>
				</a>
			</div>
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="${redirectUrl}&type=adminBokingList"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/128/2664/2664673.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="facility-booking-list" />
								</p>
							</div>
						</div>
					</a>
				</div>
				
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="${redirectUrl}&type=adminRatingReview"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/128/5702/5702796.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="view-user-ratings" />
								</p>
							</div>
						</div>
					</a>
				</div>
		</c:if>
		
		 <c:if test="${!isDSO && !isTSO && !isHOAdmin}">
			 <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="${redirectUrl}&type=monthlyFacility"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/128/12727/12727879.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="monthly-facility-booking" />
								</p>
							</div>
						</div>
					</a>
				</div>
			 <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="${redirectUrl}&type=rentalFacility"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/128/2603/2603619.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="rental-facility-booking" />
								</p>
							</div>
						</div>
					</a>
				</div>
			 <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="${redirectUrl}&type=userBokingList"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/128/2664/2664673.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="facility-booking-list" />
								</p>
							</div>
						</div>
					</a>
				</div>
			 <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="${redirectUrl}&type=userRating"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/128/5702/5702796.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="facility-rating" />
								</p>
							</div>
						</div>
					</a>
				</div>
			</c:if>
		
	</div>
</div>

