<%@ include file="/init.jsp" %>

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<!-- Header -->
					<div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0"><liferay-ui:message key="view-profile" /></h5>
						<div>
							<a href="/group/guest/athelet-performance" class="btn btn-primary btn-sm rounded-pill back-btn-cn"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
							</a>
						</div>
					</div>

					<!-- Body -->
					<div class="card-body">
						<div class="row">
							<!-- Profile Image Card -->
							<div class="col-md-3">
								<div class="card card-background p-0 mb-4">
									<div class="card-header header-card text-center"><liferay-ui:message key="profile-image" />
										</div>
									<div class="card-body text-center">
										<img id="profileImagePreview"
											src="${portraitURL}"
											class="img-fluid rounded mb-2 border rounded"
											alt="Profile Image"> 
									</div>
								</div>
							</div>

							<!-- Profile Info Card -->
							<div class="col-md-9">
								<div class="card card-background p-0 mb-4">
									<div class="card-header header-card"><liferay-ui:message key="profile-details" /></div>
									<div class="card-body">
										<!-- First and Last Name -->
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="first-name" /> <sup class="text-danger">*</sup></label>
													<input type="text" class="form-control" id="firstName" disabled value="${schoolCollege.firstName}"
														name="firstName">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="last-name" /> <sup class="text-danger">*</sup></label> <input
														type="text" class="form-control" id="lastName" value="${schoolCollege.lastName}" disabled
														name="lastName">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="mothers-name" /> <sup class="text-danger">*</sup></label>
													<input type="text" class="form-control" id="mothersName" value="${schoolCollege.mothersName}" disabled
														name="mothersName">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="fathers-name" /> <sup class="text-danger">*</sup></label> <input
														type="text" class="form-control" id="fathersName" value="${schoolCollege.fathersName}" disabled
														name="fathersName">
												</div>
											</div>
										</div>

										<!-- Other Complaint Details -->
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="school-or-college-name" /> <sup class="text-danger">*</sup></label> <input
														type="text" class="form-control" id="schoolName" disabled
														name="schoolName" value="${schoolCollege.schoolOrCollegeName }">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="aadhaar-number" /><sup class="text-danger">*</sup></label> <input
														type="text" class="form-control" id="aadharNumber" disabled
														name="aadharNumber" value="${schoolCollege.aadharNumber }">
												</div>
											</div>
										</div>

									</div>
								</div>
								<!-- End Profile Details Card -->
							</div>
							<!-- End col-md-9 -->
						</div>
						<!-- End row -->
					</div>

					<!-- Footer -->
					<!-- <div class="card-footer bg-transparent text-right p-4">
						<button type="submit" class="btn btn-primary"
							onclick="createProfile(event)">Submit</button>
					</div> -->

				</div>
			</div>
		</div>
	</div>
</div>
