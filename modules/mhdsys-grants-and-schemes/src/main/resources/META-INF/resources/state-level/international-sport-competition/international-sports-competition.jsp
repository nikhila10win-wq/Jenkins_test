
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
						<label><liferay-ui:message key="full-name" /><sup class="text-danger">*</sup></label> 
							<c:set var="fullName" value="${schoolCollegeOfficerRegistration.firstName} ${schoolCollegeOfficerRegistration.lastName}" />
							<input type="text" class="form-control" name="fullName" id="fullName" readonly
							value="${fullName}" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="dob" /><sup class="text-danger">*</sup></label>
						 <input type="text" class="form-control" name="intSportsComp" id="intSportsComp" readonly
							value="${dob}" />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="mobile-no" /><sup class="text-danger">*</sup></label> 
							<input type="text" class="form-control" name="mobileNo" id="mobileNo"  readonly
							value="${schoolCollegeOfficerRegistration.mobileNumber}" />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="email-id" /><sup class="text-danger">*</sup></label> 
							<input type="text" class="form-control" name="emailId" id="emailId" readonly
							value="${schoolCollegeOfficerRegistration.email}" />

					</div>
				</div>

				
				<div class="col-md-6">
					<div class="form-group">
						<label> 
							<liferay-ui:message key="aadhaar-card" /> 
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty intSportsComp.aadhaarCardURL}">
								<div>
									<a href="${intSportsComp.aadhaarCardURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.aadhaarCardName} 
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="aadhaarCard" name="aadhaarCard"
									onchange="handleFileUpload(event, 'aadhaarCard', 'aadhaarCardNewPreviewContainer', 'aadhaarCardNewPreviewLink', 'aadhaarCardNewDeleteButton')">
								<label class="custom-file-label" for="aadhaarCard">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty intSportsComp.aadhaarCardURL}">
								<div class="ownerProofid d-flex mt-3" id="aadhaarCardPreviewContainer">
									<a class="aadhaarCardProofCls" id="aadhaarCardPreviewLink"
										href="${intSportsComp.aadhaarCardURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.aadhaarCardName} 
									</a>
									<button type="button" id="aadhaarCarddeleteButton" class="dltaadhaarCardBtn close" aria-label="Close"
										onclick="deleteFile('aadhaarCardPreviewContainer', 'aadhaarCard')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="aadhaarCardNewPreviewContainer">
								<a class="aadhaarCardProofCls" id="aadhaarCardNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="aadhaarCardNewDeleteButton" class="dltaadhaarCardBtn close" aria-label="Close"
									onclick="deleteFile('aadhaarCardNewPreviewContainer', 'aadhaarCard')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="aadhaarCard" name="aadhaarCard"
									onchange="handleFileUpload(event, 'aadhaarCard', 'aadhaarCardNewPreviewContainer', 'aadhaarCardNewPreviewLink', 'aadhaarCardNewDeleteButton')">
								<label class="custom-file-label" for="aadhaarCard"> 
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<%-- Empty container for new file upload preview --%>
							<div class="ownerProofid d-none mt-3" id="aadhaarCardNewPreviewContainer">
								<a class="aadhaarCardProofCls" id="aadhaarCardNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="aadhaarCardNewDeleteButton" class="dltaadhaarCardBtn close" aria-label="Close" onclick="deleteFile('aadhaarCardNewPreviewContainer', 'aadhaarCard')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
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
							<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty intSportsComp.panCardURL}">
								<div>
									<a href="${intSportsComp.panCardURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.panCardName} 
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="panCard" name="panCard"
									onchange="handleFileUpload(event, 'panCard', 'panCardNewPreviewContainer', 'panCardNewPreviewLink', 'panCardNewDeleteButton')">
								<label class="custom-file-label" for="panCard">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty intSportsComp.panCardURL}">
								<div class="ownerProofid d-flex mt-3" id="panCardPreviewContainer">
									<a class="panCardProofCls" id="panCardPreviewLink"
										href="${intSportsComp.panCardURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.panCardName} 
									</a>
									<button type="button" id="panCarddeleteButton" class="dltpanCardBtn close" aria-label="Close"
										onclick="deleteFile('panCardPreviewContainer', 'panCard')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="panCardNewPreviewContainer">
								<a class="panCardProofCls" id="panCardNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="panCardNewDeleteButton" class="dltpanCardBtn close" aria-label="Close"
									onclick="deleteFile('panCardNewPreviewContainer', 'panCard')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="panCard" name="panCard"
									onchange="handleFileUpload(event, 'panCard', 'panCardNewPreviewContainer', 'panCardNewPreviewLink', 'panCardNewDeleteButton')">
								<label class="custom-file-label" for="panCard"> 
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<%-- Empty container for new file upload preview --%>
							<div class="ownerProofid d-none mt-3" id="panCardNewPreviewContainer">
								<a class="panCardProofCls" id="panCardNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="panCardNewDeleteButton" class="dltpanCardBtn close" aria-label="Close" onclick="deleteFile('panCardNewPreviewContainer', 'panCard')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
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
							<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty intSportsComp.domicileCertificateURL}">
								<div>
									<a href="${intSportsComp.domicileCertificateURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.domicileCertificateName} 
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="domicileCertificate" name="domicileCertificate"
									onchange="handleFileUpload(event, 'domicileCertificate', 'domicileCertificateNewPreviewContainer', 'domicileCertificateNewPreviewLink', 'domicileCertificateNewDeleteButton')">
								<label class="custom-file-label" for="domicileCertificate">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty intSportsComp.domicileCertificateURL}">
								<div class="ownerProofid d-flex mt-3" id="domicileCertificatePreviewContainer">
									<a class="domicileCertificateProofCls" id="domicileCertificatePreviewLink"
										href="${intSportsComp.domicileCertificateURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.domicileCertificateName} 
									</a>
									<button type="button" id="domicileCertificatedeleteButton" class="dltdomicileCertificateBtn close" aria-label="Close"
										onclick="deleteFile('domicileCertificatePreviewContainer', 'domicileCertificate')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="domicileCertificateNewPreviewContainer">
								<a class="domicileCertificateProofCls" id="domicileCertificateNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="domicileCertificateNewDeleteButton" class="dltdomicileCertificateBtn close" aria-label="Close"
									onclick="deleteFile('domicileCertificateNewPreviewContainer', 'domicileCertificate')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="domicileCertificate" name="domicileCertificate"
									onchange="handleFileUpload(event, 'domicileCertificate', 'domicileCertificateNewPreviewContainer', 'domicileCertificateNewPreviewLink', 'domicileCertificateNewDeleteButton')">
								<label class="custom-file-label" for="domicileCertificate"> 
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<%-- Empty container for new file upload preview --%>
							<div class="ownerProofid d-none mt-3" id="domicileCertificateNewPreviewContainer">
								<a class="domicileCertificateProofCls" id="domicileCertificateNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="domicileCertificateNewDeleteButton" class="dltdomicileCertificateBtn close" aria-label="Close"
									onclick="deleteFile('domicileCertificateNewPreviewContainer', 'domicileCertificate')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
					</div>
				</div>
				
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label> 
							<liferay-ui:message key="pass-book" /> 
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty intSportsComp.passBookURL}">
								<div>
									<a href="${intSportsComp.passBookURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.passBookName} 
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="passBook" name="passBook"
									onchange="handleFileUpload(event, 'passBook', 'passBookNewPreviewContainer', 'passBookNewPreviewLink', 'passBookNewDeleteButton')">
								<label class="custom-file-label" for="passBook">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty intSportsComp.passBookURL}">
								<div class="ownerProofid d-flex mt-3" id="passBookPreviewContainer">
									<a class="passBookProofCls" id="passBookPreviewLink"
										href="${intSportsComp.passBookURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.passBookName} 
									</a>
									<button type="button" id="passBookdeleteButton" class="dltpassBookBtn close" aria-label="Close"
										onclick="deleteFile('passBookPreviewContainer', 'passBook')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="passBookNewPreviewContainer">
								<a class="passBookProofCls" id="passBookNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="passBookNewDeleteButton" class="dltpassBookBtn close" aria-label="Close"
									onclick="deleteFile('passBookNewPreviewContainer', 'passBook')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="passBook" name="passBook"
									onchange="handleFileUpload(event, 'passBook', 'passBookNewPreviewContainer', 'passBookNewPreviewLink', 'passBookNewDeleteButton')">
								<label class="custom-file-label" for="passBook"> 
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<%-- Empty container for new file upload preview --%>
							<div class="ownerProofid d-none mt-3" id="passBookNewPreviewContainer">
								<a class="passBookProofCls" id="passBookNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="passBookNewDeleteButton" class="dltpassBookBtn close" aria-label="Close"
									onclick="deleteFile('passBookNewPreviewContainer', 'passBook')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
					</div>
				</div>
								
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label> 
							<liferay-ui:message key="cancelled-check" /> 
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty intSportsComp.cancelledCheckURL}">
								<div>
									<a href="${intSportsComp.cancelledCheckURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.cancelledCheckName} 
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="cancelledCheck" name="cancelledCheck"
									onchange="handleFileUpload(event, 'cancelledCheck', 'cancelledCheckNewPreviewContainer', 'cancelledCheckNewPreviewLink', 'cancelledCheckNewDeleteButton')">
								<label class="custom-file-label" for="cancelledCheck">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty intSportsComp.cancelledCheckURL}">
								<div class="ownerProofid d-flex mt-3" id="cancelledCheckPreviewContainer">
									<a class="cancelledCheckProofCls" id="cancelledCheckPreviewLink"
										href="${intSportsComp.cancelledCheckURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.cancelledCheckName} 
									</a>
									<button type="button" id="cancelledCheckdeleteButton" class="dltcancelledCheckBtn close" aria-label="Close"
										onclick="deleteFile('cancelledCheckPreviewContainer', 'cancelledCheck')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="cancelledCheckNewPreviewContainer">
								<a class="cancelledCheckProofCls" id="cancelledCheckNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="cancelledCheckNewDeleteButton" class="dltcancelledCheckBtn close" aria-label="Close"
									onclick="deleteFile('cancelledCheckNewPreviewContainer', 'cancelledCheck')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="cancelledCheck" name="cancelledCheck"
									onchange="handleFileUpload(event, 'cancelledCheck', 'cancelledCheckNewPreviewContainer', 'cancelledCheckNewPreviewLink', 'cancelledCheckNewDeleteButton')">
								<label class="custom-file-label" for="cancelledCheck"> 
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<%-- Empty container for new file upload preview --%>
							<div class="ownerProofid d-none mt-3" id="cancelledCheckNewPreviewContainer">
								<a class="cancelledCheckProofCls" id="cancelledCheckNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="cancelledCheckNewDeleteButton" class="dltcancelledCheckBtn close" aria-label="Close"
									onclick="deleteFile('cancelledCheckNewPreviewContainer', 'cancelledCheck')">
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
<%-- <%@ include file="/state-level/competition-aspiring-athetes/competition-details.jsp"%>
 --%><%@ include file="/state-level/international-sport-competition/competition-details.jsp"%>
<%@ include file="/state-level/international-sport-competition/financial-details.jsp"%>