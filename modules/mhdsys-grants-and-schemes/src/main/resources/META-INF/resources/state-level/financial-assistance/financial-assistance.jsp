<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="personal-details" />
		</div>
		<div class="card-body">

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="name-of-applicant" /><sup
							class="text-danger">*</sup></label> 
							<c:set var="fullName" value="${schoolCollegeOfficerRegistration.firstName} ${schoolCollegeOfficerRegistration.lastName}" />
							<input type="text" 
							class="form-control" name="applicantNameF" id="applicantNameF"
							readonly
							value="${fullName}" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="address" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="address" id="address" readonly
							value="${profile.postalAddress}" />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="pincode" /><sup
							class="text-danger">*</sup></label> <input type="number"
							class="form-control" name="pincodeF" id="pincodeF" readonly
							
							value="${profile.pinCode}" />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="contact-number" /><sup
							class="text-danger">*</sup></label> <input type="number"
							class="form-control" name="contactNumber" id="contactNumber" 
							readonly
							value="${schoolCollegeOfficerRegistration.mobileNumber}" />

					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="email-id" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="emailId" id="emailId" readonly
							
							value="${schoolCollegeOfficerRegistration.email}" />
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
<%@ include file="/state-level/financial-assistance/sport-details.jsp"%>
<%@ include file="/state-level/financial-assistance/financial-details.jsp"%>

