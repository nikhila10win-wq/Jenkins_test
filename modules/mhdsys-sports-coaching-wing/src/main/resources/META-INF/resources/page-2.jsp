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
				<a href="/group/guest/dashboard"
					class="btn btn-primary btn-sm rounded-pill back-btn-cn"> <i
					class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
				</a>
			</c:if>
		</div>
	</div>
	<div class="row">
	<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/sports-coaching-wing-list"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/18832/18832058.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="coaching-wing" />
							</p>
						</div>
					</div>
				</a>
			</div>
		<!-- Unit Registration -->
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/sports-coaching-wing-reports" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/15309/15309806.png"
							alt="">
						<p class="mb-0">
							<liferay-ui:message key="reports" />
						</p>
					</div>
				</div>
			</a>
		</div>
	</div>
</div>