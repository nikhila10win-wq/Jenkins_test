<%@page
	import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp"%>
<div class="container-fluid dashboardPage">
<div class="row">
		<div class="col-12 d-flex justify-content-end mb-2">
			<a href="/group/guest/dashboard"
				class="btn btn-primary btn-sm rounded-pill back-btn-cn"> <i
				class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
			</a>
		</div>
	</div>
	<div class="row">
		<c:if test="${isHoAdmin}">
			<!-- Unit Registration -->
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/unit-registration" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img src="https://cdn-icons-png.flaticon.com/128/15309/15309806.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="unit-registration" />
							</p>
						</div>
					</div>
				</a>
			</div>

			<!-- Scout Master Registration -->
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/scout-guide-master-registration"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img src="https://cdn-icons-png.flaticon.com/128/7897/7897219.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="scout-and-guide-master-registration" />
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>


		<!-- Student Application -->
		<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/student-registration"
				class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/14600/14600942.png"
							alt="">
						<p class="mb-0">
							<liferay-ui:message key="student-registration" />
						</p>
					</div>
				</div>
			</a>
		</div> -->

		<!-- Student ID Generation -->
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/student-list" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/18679/18679272.png"
							alt="">
						<p class="mb-0">
							<liferay-ui:message key="student-list" />
						</p>
					</div>
				</div>
			</a>
		</div>
		<c:if test="${scoutMaster || guideMaster}">
			<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/event" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/2494/2494589.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="event" />
							</p>
						</div>
					</div>
				</a>
			</div> -->
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/event-list" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/7152/7152924.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="event-list" />
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
		<c:if test="${commandingOfficer}">
			<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/add-grant" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/17729/17729524.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="add-grants" />
							</p>
						</div>
					</div>
				</a>
			</div> -->
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/grant-list" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/18944/18944484.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="ncc-grant-list" />
							</p>
						</div>
					</div>
				</a>
			</div>
			<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/grant-request" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11000/11000717.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="ncc-grant-request" />
							</p>
						</div>
					</div>
				</a>
			</div> -->
			<portlet:renderURL var="grantRequestListURL">
				<portlet:param name="mvcRenderCommandName"
					value="<%=MhdsysAdministrativeWebPortletKeys.NCC_GRANT_REQUEST_LIST_MVC_RENDER_COMMAND%>" />
			</portlet:renderURL>
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="${grantRequestListURL}" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/12824/12824819.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="ncc-grant-request-list" />
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
	</div>
</div>
