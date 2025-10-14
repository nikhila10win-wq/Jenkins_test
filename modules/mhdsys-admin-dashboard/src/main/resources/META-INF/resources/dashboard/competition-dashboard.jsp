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
				<c:if test="${isHOAdmin or isDeskOfficer}">
					<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<a href="/group/guest/competition-master" class="text-decoration-none">
							<div class="card">
								<div class="d-flex justify-content-start align-items-center">
									<img
										src="https://cdn-icons-png.flaticon.com/128/16989/16989163.png"
										alt="">
									<p class="mb-0">
										<liferay-ui:message key="competition-master" />
									</p>
								</div>
							</div>
						</a>
					</div>
				</c:if>
				<c:if test="${isDSORole or isHOAdmin or isDeskOfficer}">
					<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<a href="/group/guest/competition-master-list" class="text-decoration-none">
							<div class="card">
								<div class="d-flex justify-content-start align-items-center">
									<img
										src="https://cdn-icons-png.flaticon.com/128/2778/2778349.png"
										alt="">
									<p class="mb-0">
										<liferay-ui:message key="competition-master-list" />
									</p>
								</div>
							</div>
						</a>
					</div>
				</c:if>
				<c:if test="${isDSORole or isPtTeacher or isHOAdmin or isDeskOfficer}">
					<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<a href="/group/guest/competition-initiated-list"
							class="text-decoration-none">
							<div class="card">
								<div class="d-flex justify-content-start align-items-center">
									<img
										src="https://cdn-icons-png.flaticon.com/128/3511/3511497.png"
										alt="">
									<p class="mb-0">
										<liferay-ui:message key="competition-application" />
									</p>
								</div>
							</div>
						</a>
					</div>
				</c:if>
				<c:if test="${isPrincipal or isPtTeacher or isHOAdmin or isDeskOfficer}">
					<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<a href="/group/guest/pt-teacher-requested-list"
							class="text-decoration-none">
							<div class="card">
								<div class="d-flex justify-content-start align-items-center">
									<img
										src="https://cdn-icons-png.flaticon.com/128/10455/10455354.png"
										alt="">
									<p class="mb-0">
										<liferay-ui:message key="competition-applied-list" />
									</p>
								</div>
							</div>
						</a>
					</div>
				</c:if>
				<c:if test="${isPrincipal or isPtTeacher or isDSORole or isHOAdmin or isDeskOfficer}">
					<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<a href="/group/guest/principal-approved-list"
							class="text-decoration-none">
							<div class="card">
								<div class="d-flex justify-content-start align-items-center">
									<img
										src="https://cdn-icons-png.flaticon.com/128/2830/2830571.png"
										alt="">
									<p class="mb-0">
										<liferay-ui:message key="principal-approved-list" />
									</p>
								</div>
							</div>
						</a>
					</div>
				</c:if>
				<%-- <c:if test="${isPrincipal or isPtTeacher or isDSORole}">
					<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<a href="/group/guest/scheduled-competition-list"
							class="text-decoration-none">
							<div class="card">
								<div class="d-flex justify-content-start align-items-center">
									<img
										src="https://cdn-icons-png.flaticon.com/128/2314/2314571.png"
										alt="">
									<p class="mb-0">
										<liferay-ui:message key="competition-scheduled-list" />
									</p>
								</div>
							</div>
						</a>
					</div>
				</c:if>
				<c:if test="${isPrincipal or isPtTeacher or isDSORole}">
					<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<a href="/group/guest/uploaded-result-list"
							class="text-decoration-none">
							<div class="card">
								<div class="d-flex justify-content-start align-items-center">
									<img
										src="https://cdn-icons-png.flaticon.com/128/1092/1092216.png"
										alt="">
									<p class="mb-0">
										<liferay-ui:message key="uploaded-result-list" />
									</p>
								</div>
							</div>
						</a>
					</div>
				</c:if> --%>
			</div>
		</div>
