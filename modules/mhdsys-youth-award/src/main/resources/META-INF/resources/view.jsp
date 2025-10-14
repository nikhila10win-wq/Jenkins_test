
<%@ include file="/init.jsp"%>
<div class="container-fluid dashboardPage">
	<div class="row">
		<div class="col-12 d-flex justify-content-end mb-2"> 
		<a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
		<i class="bi bi-arrow-left-circle"></i> Back
		</a>
		</div>
	</div>
	<div class="row">
	
	
	 
	<c:if test="${!isDeskOfficer and !isAssistantDirector and !isHOAdmin}">
<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/awards-youth" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/8165/8165803.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="awards-youth" /></p>
					</div>
				</div>
			</a>
		</div>
		
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/awards-organization" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/8165/8165803.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="awards-organization" /></p>
					</div>
				</div>
			</a>
		</div>
		</c:if>
		
		<c:if test="${isDeskOfficer or isAssistantDirector or isHOAdmin}">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/awards-youth-list" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/8165/8165803.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="awards-youth-list" /></p>
					</div>
				</div>
			</a>
		</div>
		
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/awards-organization-list" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/8165/8165803.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="awards-organization-list" /></p>
					</div>
				</div>
			</a>
		</div>
		</c:if>
	</div>
</div>

