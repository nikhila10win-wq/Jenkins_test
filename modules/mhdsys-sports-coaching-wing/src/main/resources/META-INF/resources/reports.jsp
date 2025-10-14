<%@ include file="/init.jsp" %>
<div class="container-fluid dashboardPage">
<div class="row">
		<div class="col-12 d-flex justify-content-end mb-2">
			<c:if test="${isCoach}">
				<a href="/group/guest/dashboard"
					class="btn btn-primary btn-sm rounded-pill back-btn-cn"> <i
					class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
				</a>
			</c:if>
			<c:if test="${isDSO or isDDD or ishoadmin}">
				<a href="/group/guest/sports-coaching-wing"
					class="btn btn-primary btn-sm rounded-pill back-btn-cn"> <i
					class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
				</a>
			</c:if>
		</div>
	</div>
<div class="row">
	
	<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/sports-coaching-wing-monthly-reports"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/18832/18832058.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="coaching-wing-monthly-reports" />
							</p>
						</div>
					</div>
				</a>
		</div>
		<c:if test="${isDSO  or isDDD or ishoadmin}">
				<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="/group/guest/district-sport-talent-development-centre-reports" class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img src="https://cdn-icons-png.flaticon.com/128/15309/15309806.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="district-dport-talent-development-centre-reports" />
								</p>
							</div>
						</div>
					</a>
				</div>
		
				<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="/group/guest/district-coaching-centre-report"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img src="https://cdn-icons-png.flaticon.com/128/7897/7897219.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="district-coaching-centre-reports" />
								</p>
							</div>
						</div>
					</a>
				</div>
		
				<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="/group/guest/khelo-india-district-centre-report"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img src="https://cdn-icons-png.flaticon.com/128/14600/14600942.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="khelo-india-district-centre-reports" />
								</p>
							</div>
						</div>
					</a>
				</div>
				<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="/group/guest/taluka-coaching-centre-report" class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img src="https://cdn-icons-png.flaticon.com/128/18679/18679272.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="taluka-coaching-centre-reports" />
								</p>
							</div>
						</div>
					</a>
				</div>
		</c:if>
	</div>
</div>