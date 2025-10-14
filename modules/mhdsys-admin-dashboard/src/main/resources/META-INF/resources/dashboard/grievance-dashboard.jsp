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

		<!-- Grievance Application -->
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/grievance" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img
							src="https://cdn-icons-png.flaticon.com/128/9375/9375697.png"
							alt="">
						<p class="mb-0">
							<liferay-ui:message key="grievance-application" />
						</p>
					</div>
				</div>
			</a>
		</div>
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/grievance-published-list"
				class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img
							src="https://cdn-icons-png.flaticon.com/128/11331/11331294.png"
							alt="">
						<p class="mb-0">
							<liferay-ui:message key="grievance-published-list" />
						</p>
					</div>
				</div>
			</a>
		</div>

		<!-- Grievance List -->
		<c:if test="${isHOAdmin}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/grievance-list" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/9070/9070335.png"
								alt="">
							<p class="mb-0"> 
								<liferay-ui:message key="grievance-list" />
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
		<!-- HO Application List -->
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/ho-application-list"
				class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/2519/2519057.png"
							alt="">
						<p class="mb-0">
							<liferay-ui:message key="ho-application-list" />
						</p>
					</div>
				</div>
			</a>
		</div>

	</div>
</div>
