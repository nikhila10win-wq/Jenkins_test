<%@ include file="/init.jsp" %>
<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="athlete-profile" />
		</div>


		<div class="card-body">

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="name-of-applicant" /><sup
							class="text-danger">*</sup></label> <input type="text"
							<c:set var="fullName" value="${schoolCollegeOfficerRegistration.firstName} ${schoolCollegeOfficerRegistration.lastName}" />
							class="form-control" name="nameOfApplicant" id="nameOfApplicant" readonly
							value="${fullName}" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="address" /><sup
							class="text-danger">*</sup></label> <input type="String"
							class="form-control" name="address" id="address" readonly
							value="${profile.postalAddress}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="pin-code" /><sup
							class="text-danger">*</sup></label> <input type="number"
							class="form-control" name="pinCode" id="pinCode" readonly
							value="${profile.pinCode}" />
					</div>
				</div>
				

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="contact-number" /><sup
							class="text-danger">*</sup></label> <input type="number"
							class="form-control" name="contactNumber" id="contactNumber" readonly
							value="${schoolCollegeOfficerRegistration.mobileNumber}" 
							/>
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="email-id" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="emailId" id="emailId" readonly
							
							value="${schoolCollegeOfficerRegistration.email}"  />

					</div>
				</div>
				
			
			
				<div class="col-md-6">
				<div class="form-group">
					<label>
						<liferay-ui:message key="domicile-certificate" />
						<sup class="text-danger">*</sup>
						<em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardYouthOrgCommonDTO.domicileFileURL}">
							<div>
								<a href="${awardYouthOrgCommonDTO.domicileFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.domicileFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="domicileFile" name="domicileFile"
								onchange="handleFileUpload(event, 'domicileFile', 'domicileFileNewPreviewContainer', 'domicileFileNewPreviewLink', 'domicileFileNewDeleteButton')">
							<label class="custom-file-label" for="domicileFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display (if uploaded before) --%>
						<c:if test="${not empty awardYouthOrgCommonDTO.domicileFileURL}">
							<div class="ownerProofid d-flex mt-3" id="domicileFilePreviewContainer">
								<a class="domicileFileProofCls" id="domicileFilePreviewLink"
									href="${awardYouthOrgCommonDTO.domicileFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.domicileFileName}
								</a>
								<button type="button" id="domicileFileDeleteButton"
									class="dltdomicileFileBtn close" aria-label="Close"
									onclick="deleteFile('domicileFilePreviewContainer', 'domicileFile')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3" id="domicileFileNewPreviewContainer">
							<a class="domicileFileProofCls" id="domicileFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="domicileFileNewDeleteButton"
								class="dltdomicileFileBtn close" aria-label="Close"
								onclick="deleteFile('domicileFileNewPreviewContainer', 'domicileFile')">
								<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
			
					<%-- Add Mode --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="domicileFile" name="domicileFile"
								onchange="handleFileUpload(event, 'domicileFile', 'domicileFileNewPreviewContainer', 'domicileFileNewPreviewLink', 'domicileFileNewDeleteButton')">
							<label class="custom-file-label" for="domicileFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<div class="ownerProofid d-none mt-3" id="domicileFileNewPreviewContainer">
							<a class="domicileFileProofCls" id="domicileFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="domicileFileNewDeleteButton"
								class="dltdomicileFileBtn close" aria-label="Close"
								onclick="deleteFile('domicileFileNewPreviewContainer', 'domicileFile')">
								<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
				</div>
			</div>

			</div>
		</div>
	</div>
</div>


<%@ include file="/state-level/competition-aspiring-athetes/competition-details.jsp"%>
<%@ include file="/state-level/competition-aspiring-athetes/expense-details.jsp"%>
<%@ include file="/state-level/competition-aspiring-athetes/bank-details.jsp"%>



