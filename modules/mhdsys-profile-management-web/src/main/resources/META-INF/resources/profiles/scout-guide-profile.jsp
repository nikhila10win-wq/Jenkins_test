<%@ include file="/init.jsp" %>
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between"
						style="">
						<h5 class="mb-0"><liferay-ui:message key="scou-and-guide" /></h5>
						<div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn" href="/group/guest/dashboard"
								style="background-color: #26268E; border-color: #fff;"><i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a>
						</div>
					</div>

					<form>
						<div class="card-body">

							<!-- Personal Details -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="personal-details" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="first-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="firstName"
													value="${dto.firstName}" readonly />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="last-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="lastName"
													value="${dto.lastName}" readonly />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="mother-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="motherName"
													value="${dto.motherName}" readonly />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="father-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="fatherName"
													value="${dto.fatherName}" readonly />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="gender" /><sup
													class="text-danger">*</sup></label><br /> <input type="radio"
													name="gender" value="Male"
													${dto.gender == 'Male' ? 'checked' : ''} disabled />
												<liferay-ui:message key="male" />
												<input type="radio" name="gender" value="Female"
													class="ms-3" ${dto.gender == 'Female' ? 'checked' : ''}
													disabled />
												<liferay-ui:message key="female" />
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- Employee Details -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="employee-details" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="current-designation" /><sup
													class="text-danger">*</sup></label> <select class="form-control"
													name="designation" disabled>
													<option value=""><liferay-ui:message key="select" /></option>
													<option value="Scout Master"
														${dto.designation == 'Scout Master' ? 'selected' : ''}>Scout
														Master</option>
													<option value="Guide Master"
														${dto.designation == 'Guide Master' ? 'selected' : ''}>Guide
														Master</option>
												</select>
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message
														key="school-name-or-college-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="schoolName"
													value="${dto.schoolName}" readonly />
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- Verification -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="verification" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="aadhar-number" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="aadharNumber"
													value="${dto.aadharNumber}" readonly />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="email-id" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="emailId" value="${dto.emailId}"
													readonly />
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="mobile-number" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="mobileNumber"
													value="${dto.mobileNumber}" readonly />
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="aadhar-card-upload" />
												<sup class="text-danger">*</sup></label>
											<c:if test="${not empty dto}">
												<div class="mt-2">
													<a href="${dto.aadharCardFileURL}" target="_blank">${dto.aadharCardFileName}</a>
												</div>
											</c:if>
										</div>
									</div>

								</div>
							</div>

						</div>

						<!-- No Save Button in Readonly Mode -->
						<div class="modal-footer d-flex justify-content-end">
							<%-- <a
								href="<portlet:renderURL><portlet:param name='mvcRenderCommandName' value='/home' /></portlet:renderURL>"
								class="btn btn-secondary"> <liferay-ui:message key="back" />
							</a> --%>
						</div>

					</form>

				</div>
			</div>
		</div>
	</div>
</div>
