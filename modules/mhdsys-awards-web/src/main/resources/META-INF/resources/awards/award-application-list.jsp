<%@ include file="/init.jsp"%>

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
				
					<!-- <div class="card-header d-flex align-item-center justify-content-between">
						<h5>
							<liferay-ui:message key="sport-person-coach-list" />
						</h5>
					</div> -->
					
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="sport-person-coach-list" /></h5>
						<div><a href="/group/guest/awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>

					<div class="card-body universal-table">
						<div id="sport-person-coach-div">
							<div class="" style="background: #eeeef6;border-radius: 15px;">
							<table id="sport-person-coach-list" class="table-bordered mt-0" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th hidden="hidden"></th>
										<c:if test="${userType eq 'awardsApplicationCoach'}">
											<th><liferay-ui:message key="sport-person-name" /></th>
										</c:if>
										<th>
										<c:if test="${roleName eq 'Adventure Person'}">
											<liferay-ui:message key="campaign-level" />
										</c:if> <c:if test="${roleName ne 'Adventure Person'}">
											<liferay-ui:message key="competition-level" />
										</c:if>
										</th>
										<th>
										<c:if test="${roleName eq 'Adventure Person'}">
												<liferay-ui:message key="campaign-name" />
											</c:if> <c:if test="${roleName ne 'Adventure Person'}">
												<liferay-ui:message key="competition-name" />
											</c:if>
										</th>
										<th>
										<c:if test="${roleName eq 'Adventure Person'}">
											<liferay-ui:message key="campaign-place" />
										</c:if> <c:if test="${roleName ne 'Adventure Person'}">
											<liferay-ui:message key="competition-place" />
										</c:if>
										</th>
										<th><liferay-ui:message key="sport-name" /></th>
										<c:if test="${roleName eq 'Para Athlete'}">
											<th><liferay-ui:message key="sport-name-for-para-athlete" /></th>
										</c:if>
										<th><liferay-ui:message key="participation-year" /></th>
										<th><liferay-ui:message key="status" /></th>
										<th><liferay-ui:message key="action" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="awardApplication" items="${awardApplications}">
										<tr>
											<td hidden="hidden">${awardApplication.awardApplicationId}</td>
											<c:if test="${userType eq 'awardsApplicationCoach'}">
												<td>${awardApplication.sportpersonName}</td>
											</c:if>
											<td>${awardApplication.competitionLevel}</td>
											<td>${awardApplication.competitionName}</td>
											<td>${awardApplication.competitionPlace}</td>
											<td>${awardApplication.sportsName}</td>
											<c:if test="${roleName eq 'Para Athlete'}">
												<td>${awardApplication.otherSportName}</td>
											</c:if>
											<td>${awardApplication.participationYear}</td>
											<td>${awardApplication.status}</td>
											<td>
											<c:if test="${awardApplication.status eq 'Draft' || awardApplication.status eq 'Pending'}">
											<div class="tooltip-icon">
												<ul class="inline-item">
												<li class="list-inline-item">
												<a class="btn btn-primary" onclick="editAwardApplication(this,'${awardApplication.awardApplicationId}')">
													<i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key='edit' />"></i>
												</a>
												</li>
												<li class="list-inline-item">
												<a class="btn btn-primary" onclick="deleteConfirmation(this,'${awardApplication.awardApplicationId}')">
													<i class="bi bi-trash icons-color" title="<liferay-ui:message key='delete' />"></i>
												</a>
												</li>
												</ul>
												</div>
												
												
											</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>

					</div>


				</div>
			</div>
		</div>
	</div>
</div>
<!-- </div>
	</div>
</div> -->

<!--  <script>
 $(document).ready(function () {
        // Initialize DataTable
        var table = $('#sport-person-coach-list').DataTable({
            "paging": true,
            "ordering": false,
            "searching": true,
        });
 });
      </script>
 -->