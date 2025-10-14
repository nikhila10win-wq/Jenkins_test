<%@page
	import="com.mhdsys.admin.dashboard.constants.AdminDashboardPortletKeys"%>
<%@ include file="/init.jsp"%>
<div class="container-fluid dashboardPage">
	<div class="row">
	
	<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/entrypass"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/6993/6993222.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="entry-pass" />
							</p>
						</div>
					</div>
				</a>
			</div>
		
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/sports-facility"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/3511/3511371.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="sports-facility" />
							</p>
						</div>
					</div>
				</a>
			</div>
		<c:if test="${isDSO || isTSO || isDDD || isHOAdmin }">	
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/construction-tracker"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/512/11287/11287228.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="construction-tracker" />
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
		<c:if test="${isDSO || isTSO || isDDD || isHOAdmin || isCoach}">	
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="/group/guest/sports-coaching-wing"
						class="text-decoration-none">
						<div class="card">
							<div class="d-flex justify-content-start align-items-center">
								<img
									src="https://cdn-icons-png.flaticon.com/128/14250/14250109.png"
									alt="">
								<p class="mb-0">
									<liferay-ui:message key="sports-coaching-wing" />
								</p>
							</div>
						</div>
					</a>
				</div>
		</c:if>
		
		<c:if test="${!isPtTeacher && !isSportsPerson}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/establishment"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/18832/18832058.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="establishment" />
							</p>
						</div>
					</div>
				</a>
			</div> 
	    </c:if>	
			
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/competition-dashboard"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/11126/11126024.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="sports-competition-management" />
							</p>
						</div>
					</div>
				</a>
			</div>
	
		<c:if test="${isSchoolCollege}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/school-college-officer-application"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/3135/3135711.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="school-college-officer" />
							</p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
		
		<c:if test="${isDDD}">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/application-certificate-verification-list" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/16949/16949711.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="certificate-verification-validation" /></p>
					</div>
				</div>
			</a>
		</div>
		</c:if>
		
		<c:if test="${isSportsPerson}">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/application-certificate-verification" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/16949/16949711.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="certificate-verification-validation" /></p>
					</div>
				</div>
			</a>
		</div>
		</c:if>
		
	<c:if test="${!isPtTeacher}">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/youth-awards" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/8165/8165803.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="youth-service" /></p>
					</div>
				</div>
			</a>
		</div>
	</c:if>

	<c:if test="${isSportsDeskOfficer || isDeputyDirector || isCoach || isDeskOfficer || isHOAdmin || isAssociation || isSportsPerson}">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/awards" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/1090/1090339.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="sports-awards" /></p>
					</div>
				</div>
			</a>
		</div>
	</c:if>
	
	<c:if test="${isPtTeacher}">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/objection"
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
	</c:if>

		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/athelet-performance" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/1248/1248343.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="athelet-performance-monitoring" /></p>
					</div>
				</div>
			</a>
		</div>

		<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/3511/3511371.png"
							alt="">
						<p class="mb-0">Sports Facilities</p>
					</div>
				</div>
			</a>
		</div>

		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/3254/3254935.png"
							alt="">
						<p class="mb-0">Kridapeeth Application</p>
					</div>
				</div>
			</a>
		</div> 

		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/6993/6993222.png"
							alt="">
						<p class="mb-0">Entry Pass</p>
					</div>
				</div>
			</a>
		</div>-->

		<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/4609/4609771.png"
							alt="">
						<p class="mb-0">Implementation and Monitoring of Various
							Schemes and Grants</p>
					</div>
				</div>
			</a>
		</div> -->

		<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/3254/3254935.png"
							alt="">
						<p class="mb-0">Website & Mobile application with integrated
							Decision Support system portal</p>
					</div>
				</div>
			</a>
		</div> -->

		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/grievance-dashboard" class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img src="https://cdn-icons-png.flaticon.com/128/792/792114.png"
							alt="">
						<p class="mb-0"><liferay-ui:message key="grievance-and-complaint-redressal" /></p>
					</div>
				</div>
			</a>
		</div>
		
		<c:if test="${!isSportsPerson}">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/administrative-dashboard" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img src="https://cdn-icons-png.flaticon.com/128/11269/11269058.png"
								alt="">
							<p class="mb-0"><liferay-ui:message key="administrative" /></p>
						</div>
					</div>
				</a>
			</div>
		</c:if>
		<c:if test="${isAssociation || isHOAdmin || isDeskOfficer }">
			<!-- <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/objection" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img src="https://cdn-icons-png.flaticon.com/128/17873/17873502.png"
								alt="">
							<p class="mb-0"><liferay-ui:message key="objection" /></p>
						</div>
					</div>
				</a>
			</div>
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/objection-list" class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img src="https://cdn-icons-png.flaticon.com/128/14785/14785145.png"
								alt="">
							<p class="mb-0"><liferay-ui:message key="objection-list" /></p>
						</div>
					</div>
				</a>
			</div> -->
			
		</c:if>
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<a href="/group/guest/grants-and-scheme"
					class="text-decoration-none">
					<div class="card">
						<div class="d-flex justify-content-start align-items-center">
							<img
								src="https://cdn-icons-png.flaticon.com/128/10236/10236154.png"
								alt="">
							<p class="mb-0">
								<liferay-ui:message key="grants-and-scheme" />
							</p>
						</div>
					</div>
				</a>
			</div>
			
	<c:if test="${isHOAdmin || isDeskOfficer || isDeputyDirector || isDDD}">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/budget"
				class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img
							src="https://cdn-icons-png.flaticon.com/128/11476/11476545.png"
							alt="">
						<p class="mb-0">
							<liferay-ui:message key="budget" />
						</p>
					</div>
				</div>
			</a>
		</div>
	</c:if>
		
		<c:if test="${!isSportsPerson}">
		<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<a href="/group/guest/scout-guide-ncc"
				class="text-decoration-none">
				<div class="card">
					<div class="d-flex justify-content-start align-items-center">
						<img
							src="https://cdn-icons-png.flaticon.com/128/11476/11476545.png"
							alt="">
						<p class="mb-0">
							<liferay-ui:message key="scout-guide-and-ncc" />
						</p>
					</div>
				</div>
			</a>
		</div>
		</c:if>
	</div>
</div>

