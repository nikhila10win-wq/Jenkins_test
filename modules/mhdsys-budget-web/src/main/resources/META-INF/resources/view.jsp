<%@ include file="/init.jsp" %>

<div class="container-fluid dashboardPage">
	<div class="row">
		<div class="col-12 d-flex justify-content-end mb-2"> 
			<a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
			<i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back"/>
			</a>
		</div>
	</div>

	<div class="row">
		<c:if test="${isDeskOfficer || isHoAdmin}">
			<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/budget-addition"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11476/11476545.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="budget-addition"/>
							</p>
						</div>
					</div>
				</a>
			</div> -->
			
			<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/fund-distribution"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/17694/17694899.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="fund-distribution"/>
							</p>
						</div>
					</div>
				</a>
			</div> -->
			
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/download-report"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/1092/1092000.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="download-report"/>
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
		<c:if test="${isDeskOfficer || isHoAdmin || isDeputyDirector || isDDD}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/budget-list"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/4080/4080716.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="budget-list"/>
							</p>
						</div>
					</div>
				</a>
			</div>
			
			<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/fund-distribution-list"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/17227/17227192.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="fund-distribution-list"/>
							</p>
						</div>
					</div>
				</a>
			</div> -->
		</c:if>
	</div>
</div>

