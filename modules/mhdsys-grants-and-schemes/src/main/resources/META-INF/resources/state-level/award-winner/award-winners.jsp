<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="athlete-profile" />
		</div>


		<div class="card-body">

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="full-name" /><sup
							class="text-danger">*</sup></label> <input type="text"
							<c:set var="fullName" value="${schoolCollegeOfficerRegistration.firstName} ${schoolCollegeOfficerRegistration.lastName}" />
							class="form-control" name="fullNameA" id="fullNameA" readonly
							value="${fullName}" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="date-of-birth" /><sup
							class="text-danger">*</sup></label> <input type="date"
							class="form-control" name="dobA" id="dobA" readonly
							
							value="${dob}" />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="mobile-number" /><sup
							class="text-danger">*</sup></label> <input type="number"
							class="form-control" name="mobileNumberA" id="mobileNumberA" readonly
							value="${schoolCollegeOfficerRegistration.mobileNumber}" 
							 />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="email-id" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="emailIdA" id="emailIdA" readonly
							
							value="${schoolCollegeOfficerRegistration.email}"   />

					</div>
				</div>
				
				<div class="col-md-6">
				<div class="form-group">
					<label>
						<liferay-ui:message key="aadhaar-card" />
						<sup class="text-danger">*</sup>
						<em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardWinner.aadhaarCardFileURL}">
							<div>
								<a href="${awardWinner.aadhaarCardFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.aadhaarCardFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="aadhaarCardFile" name="aadhaarCardFile"
								onchange="handleFileUpload(event, 'aadhaarCardFile', 'aadhaarCardFileNewPreviewContainer', 'aadhaarCardFileNewPreviewLink', 'aadhaarCardFileNewDeleteButton')">
							<label class="custom-file-label" for="aadhaarCardFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display (if uploaded before) --%>
						<c:if test="${not empty awardWinner.aadhaarCardFileURL}">
							<div class="ownerProofid d-flex mt-3"
								id="aadhaarCardFilePreviewContainer">
								<a class="aadhaarCardFileProofCls"
									id="aadhaarCardFilePreviewLink"
									href="${awardWinner.aadhaarCardFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.aadhaarCardFileName}
								</a>
								<button type="button" id="aadhaarCardFiledeleteButton"
									class="dltaadhaarCardFileBtn close" aria-label="Close"
									onclick="deleteFile('aadhaarCardFilePreviewContainer', 'aadhaarCardFile')">
									<span aria-hidden="true" class="text-danger"><em
										class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3"
							id="aadhaarCardFileNewPreviewContainer">
							<a class="aadhaarCardFileProofCls"
								id="aadhaarCardFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="aadhaarCardFileNewDeleteButton"
								class="dltaadhaarCardFileBtn close" aria-label="Close"
								onclick="deleteFile('aadhaarCardFileNewPreviewContainer', 'aadhaarCardFile')">
								<span aria-hidden="true" class="text-danger"><em
									class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
			
					<%-- Add Mode --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="aadhaarCardFile" name="aadhaarCardFile"
								onchange="handleFileUpload(event, 'aadhaarCardFile', 'aadhaarCardFileNewPreviewContainer', 'aadhaarCardFileNewPreviewLink', 'aadhaarCardFileNewDeleteButton')">
							<label class="custom-file-label" for="aadhaarCardFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Empty container for new file upload preview --%>
						<div class="ownerProofid d-none mt-3"
							id="aadhaarCardFileNewPreviewContainer">
							<a class="aadhaarCardFileProofCls"
								id="aadhaarCardFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="aadhaarCardFileNewDeleteButton"
								class="dltaadhaarCardFileBtn close" aria-label="Close"
								onclick="deleteFile('aadhaarCardFileNewPreviewContainer', 'aadhaarCardFile')">
								<span aria-hidden="true" class="text-danger"><em
									class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
				</div>
			</div>

				


             <div class="col-md-6">
				<div class="form-group">
					<label>
						<liferay-ui:message key="pan-card" />
						<sup class="text-danger">*</sup>
						<em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardWinner.panCardFileURL}">
							<div>
								<a href="${awardWinner.panCardFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.panCardFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="panCardFile" name="panCardFile"
								onchange="handleFileUpload(event, 'panCardFile', 'panCardFileNewPreviewContainer', 'panCardFileNewPreviewLink', 'panCardFileNewDeleteButton')">
							<label class="custom-file-label" for="panCardFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display (if uploaded before) --%>
						<c:if test="${not empty awardWinner.panCardFileURL}">
							<div class="ownerProofid d-flex mt-3"
								id="panCardFilePreviewContainer">
								<a class="panCardFileProofCls"
									id="panCardFilePreviewLink"
									href="${awardWinner.panCardFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.panCardFileName}
								</a>
								<button type="button" id="panCardFiledeleteButton"
									class="dltpanCardFileBtn close" aria-label="Close"
									onclick="deleteFile('panCardFilePreviewContainer', 'panCardFile')">
									<span aria-hidden="true" class="text-danger"><em
										class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3"
							id="panCardFileNewPreviewContainer">
							<a class="panCardFileProofCls"
								id="panCardFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="panCardFileNewDeleteButton"
								class="dltpanCardFileBtn close" aria-label="Close"
								onclick="deleteFile('panCardFileNewPreviewContainer', 'panCardFile')">
								<span aria-hidden="true" class="text-danger"><em
									class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
			
					<%-- Add Mode --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="panCardFile" name="panCardFile"
								onchange="handleFileUpload(event, 'panCardFile', 'panCardFileNewPreviewContainer', 'panCardFileNewPreviewLink', 'panCardFileNewDeleteButton')">
							<label class="custom-file-label" for="panCardFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Empty container for new file upload preview --%>
						<div class="ownerProofid d-none mt-3"
							id="panCardFileNewPreviewContainer">
							<a class="panCardFileProofCls"
								id="panCardFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="panCardFileNewDeleteButton"
								class="dltpanCardFileBtn close" aria-label="Close"
								onclick="deleteFile('panCardFileNewPreviewContainer', 'panCardFile')">
								<span aria-hidden="true" class="text-danger"><em
									class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
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
						<c:if test="${not empty awardWinner.domicileCertificateFileURL}">
							<div>
								<a href="${awardWinner.domicileCertificateFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.domicileCertificateFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="domicileCertificateFile" name="domicileCertificateFile"
								onchange="handleFileUpload(event, 'domicileCertificateFile', 'domicileCertificateFileNewPreviewContainer', 'domicileCertificateFileNewPreviewLink', 'domicileCertificateFileNewDeleteButton')">
							<label class="custom-file-label" for="domicileCertificateFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display (if uploaded before) --%>
						<c:if test="${not empty awardWinner.domicileCertificateFileURL}">
							<div class="ownerProofid d-flex mt-3"
								id="domicileCertificateFilePreviewContainer">
								<a class="domicileCertificateFileProofCls"
									id="domicileCertificateFilePreviewLink"
									href="${awardWinner.domicileCertificateFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.domicileCertificateFileName}
								</a>
								<button type="button" id="domicileCertificateFiledeleteButton"
									class="dltdomicileCertificateFileBtn close" aria-label="Close"
									onclick="deleteFile('domicileCertificateFilePreviewContainer', 'domicileCertificateFile')">
									<span aria-hidden="true" class="text-danger"><em
										class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3"
							id="domicileCertificateFileNewPreviewContainer">
							<a class="domicileCertificateFileProofCls"
								id="domicileCertificateFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="domicileCertificateFileNewDeleteButton"
								class="dltdomicileCertificateFileBtn close" aria-label="Close"
								onclick="deleteFile('domicileCertificateFileNewPreviewContainer', 'domicileCertificateFile')">
								<span aria-hidden="true" class="text-danger"><em
									class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
			
					<%-- Add Mode --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="domicileCertificateFile" name="domicileCertificateFile"
								onchange="handleFileUpload(event, 'domicileCertificateFile', 'domicileCertificateFileNewPreviewContainer', 'domicileCertificateFileNewPreviewLink', 'domicileCertificateFileNewDeleteButton')">
							<label class="custom-file-label" for="domicileCertificateFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Empty container for new file upload preview --%>
						<div class="ownerProofid d-none mt-3"
							id="domicileCertificateFileNewPreviewContainer">
							<a class="domicileCertificateFileProofCls"
								id="domicileCertificateFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="domicileCertificateFileNewDeleteButton"
								class="dltdomicileCertificateFileBtn close" aria-label="Close"
								onclick="deleteFile('domicileCertificateFileNewPreviewContainer', 'domicileCertificateFile')">
								<span aria-hidden="true" class="text-danger"><em
									class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
				</div>
			</div>

				

			</div>
		</div>
	</div>
</div>

<%@ include file="/state-level/award-winner/competition-details.jsp"%>
<%@ include file="/state-level/award-winner/bank-details.jsp"%>
<%@ include file="/state-level/award-winner/financial-details.jsp"%>
<%-- <%@ include file="/state-level/competition-aspiring-athetes/competition-details.jsp"%> --%>

