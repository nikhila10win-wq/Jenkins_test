<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="competition-details" />
		</div>
		<div class="card-body">
			<div class="row">

				<!-- 1. Name of Organization -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="organization-name" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="councilOrganizationName"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 2. Address of Organization -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="organization-address" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="councilOrganizationAddress"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 3. Competition Name -->
				<%-- <div class="col-md-6 form-group">
					<label><liferay-ui:message key="competition-name" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="councilCompetitionName"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div> --%>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-name" /><sup class="text-danger">*</sup></label> 
						<select onchange="getCompetitionDetails()" class="form-control councilCompetitionName" name="councilCompetitionName" id="councilCompetitionName" <c:if test="${mode eq 'view'}">disabled</c:if>>
							<option value=""><liferay-ui:message key="select" /></option>
								<c:forEach var="awardApplication" items="${awardApplications }">
							<option value="${awardApplication.competitionName}">${awardApplication.competitionName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				
				
				
				

				<!-- 4. Competition Level -->
				<%-- <div class="col-md-6 form-group">
					<label><liferay-ui:message key="competition-level" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="councilCompetitionLevel"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value="">-- Select --</option>
						<option value="State">State</option>
						<option value="National">National</option>
					</select>
				</div> --%>
				
				
				<div class="col-md-6">
				  <div class="form-group">
				    <label>
				      <liferay-ui:message key="competition-level" />
				      <sup class="text-danger">*</sup>
				    </label>
				    <input
				      type="text"
				      class="form-control councilCompetitionLevel"
				      name="councilCompetitionLevel"
				      id="councilCompetitionLevel"
				      <%-- value="${competitionLevel}" --%>
				      readonly
				    />
				  </div>
				</div>
				
				
				

				<!-- 5. Name of Competition Sponsor -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="competition-sponsor" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="councilCompetitionSponsor"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 6. Address of Competition Sponsor -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="sponsor-address" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="councilSponsorAddress"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 7. Number of Team Players Participating -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="team-players-count" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="councilTeamPlayersCount"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 8. List of Player Names Attachment -->
				<!-- 8. List of Player Names Attachment -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="player-list-attachment" /><sup
						class="text-danger">*</sup></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilPlayerListAttachment"
							name="councilPlayerListAttachment"
							onchange="handleFileUpload(event, 'councilPlayerListAttachment', 'councilPlayerListAttachmentPreviewContainer', 'councilPlayerListAttachmentPreviewLink', 'councilPlayerListAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label" for="councilPlayerListAttachment"><liferay-ui:message
								key="choose-file" /></label>
					</div>
					<div class="ownerProofid d-none mt-3"
						id="councilPlayerListAttachmentPreviewContainer">
						<a id="councilPlayerListAttachmentPreviewLink" href=""
							target="_blank"></a>
						<button type="button" id="councilPlayerListAttachmentDeleteBtn"
							class="close"
							onclick="deleteFile('councilPlayerListAttachmentPreviewContainer', 'councilPlayerListAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>


				<!-- 9. Competition Venue -->
				<%-- <div class="col-md-6 form-group">
					<label><liferay-ui:message key="competition-venue" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="councilCompetitionVenue"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div> --%>
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-venue" /><sup class="text-danger ">*</sup></label> 
							<input type="text" class="form-control councilCompetitionVenue" name="councilCompetitionVenue" id="councilCompetitionVenue" readonly
							<%-- value="${awardApplication.competitionPlace}" --%> 
							/>
					</div>
				</div>
				
				
				

				<!-- 10. Competition Duration -->
				<%-- <div class="col-md-6 form-group">
					<label><liferay-ui:message key="competition-duration" /><sup
						class="text-danger">*</sup></label> <input type="date"
						class="form-control" name="councilCompetitionDuration"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div> --%>
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-date" /><sup class="text-danger">*</sup></label> 
							<input type="Date" class="form-control councilCompetitionDuration" name="councilCompetitionDuration" id="councilCompetitionDuration" readonly
							<%-- value="${competitionDate}"  --%>
							/>
					</div>
				</div>
				
				
				

				<!-- 11. Estimated Cost -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="estimated-cost" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" step="any" name="councilEstimatedCost"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
					<div class="custom-file mt-1">
						<input type="file" class="custom-file-input"
							id="councilEstimatedCostAttachment"
							name="councilEstimatedCostAttachment"
							onchange="handleFileUpload(event, 'councilEstimatedCostAttachment', 'councilEstimatedCostAttachmentPreviewContainer', 'councilEstimatedCostAttachmentPreviewLink', 'councilEstimatedCostAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label" for="councilEstimatedCostAttachment"><liferay-ui:message
								key="choose-file" /></label>
					</div>
					<div class="ownerProofid d-none mt-3"
						id="councilEstimatedCostAttachmentPreviewContainer">
						<a id="councilEstimatedCostAttachmentPreviewLink" href=""
							target="_blank"></a>
						<button type="button" id="councilEstimatedCostAttachmentDeleteBtn"
							class="close"
							onclick="deleteFile('councilEstimatedCostAttachmentPreviewContainer', 'councilEstimatedCostAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 12. Federation Consent -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="federation-consent" /><sup
						class="text-danger">*</sup></label><br> <label><input
						type="radio" name="councilFederationConsent" value="yes"
						<c:if test="${mode eq 'view'}">disabled</c:if>> Yes</label> <label
						class="ms-2"><input type="radio"
						name="councilFederationConsent" value="no"
						<c:if test="${mode eq 'view'}">disabled</c:if>> No</label>
					<div class="custom-file mt-1">
						<input type="file" class="custom-file-input"
							id="councilFederationConsentAttachment"
							name="councilFederationConsentAttachment"
							onchange="handleFileUpload(event, 'councilFederationConsentAttachment', 'councilFederationConsentAttachmentPreviewContainer', 'councilFederationConsentAttachmentPreviewLink', 'councilFederationConsentAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label"
							for="councilFederationConsentAttachment"><liferay-ui:message
								key="choose-file" /></label>
					</div>
					<div class="ownerProofid d-none mt-3"
						id="councilFederationConsentAttachmentPreviewContainer">
						<a id="councilFederationConsentAttachmentPreviewLink" href=""
							target="_blank"></a>
						<button type="button"
							id="councilFederationConsentAttachmentDeleteBtn" class="close"
							onclick="deleteFile('councilFederationConsentAttachmentPreviewContainer', 'councilFederationConsentAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 13. Organization Registration Number -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="registration-number" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="councilRegistrationNumber"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 14. Approval Number of Directorate -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message
							key="directorate-approval-number" /><sup class="text-danger">*</sup></label>
					<input type="text" class="form-control"
						name="councilDirectorateApprovalNumber"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
					<div class="custom-file mt-1">
						<input type="file" class="custom-file-input"
							id="councilDirectorateApprovalAttachment"
							name="councilDirectorateApprovalAttachment"
							onchange="handleFileUpload(event, 'councilDirectorateApprovalAttachment', 'councilDirectorateApprovalAttachmentPreviewContainer', 'councilDirectorateApprovalAttachmentPreviewLink', 'councilDirectorateApprovalAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label"
							for="councilDirectorateApprovalAttachment"><liferay-ui:message
								key="choose-file" /></label>
					</div>
					<div class="ownerProofid d-none mt-3"
						id="councilDirectorateApprovalAttachmentPreviewContainer">
						<a id="councilDirectorateApprovalAttachmentPreviewLink" href=""
							target="_blank"></a>
						<button type="button"
							id="councilDirectorateApprovalAttachmentDeleteBtn" class="close"
							onclick="deleteFile('councilDirectorateApprovalAttachmentPreviewContainer', 'councilDirectorateApprovalAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 15. District Affiliated to State Organization -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="district-affiliation" /><sup
						class="text-danger">*</sup></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilDistrictAffiliationAttachment"
							name="councilDistrictAffiliationAttachment"
							onchange="handleFileUpload(event, 'councilDistrictAffiliationAttachment', 'councilDistrictAffiliationAttachmentPreviewContainer', 'councilDistrictAffiliationAttachmentPreviewLink', 'councilDistrictAffiliationAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label"
							for="councilDistrictAffiliationAttachment"><liferay-ui:message
								key="choose-file" /></label>
					</div>
					<div class="ownerProofid d-none mt-3"
						id="councilDistrictAffiliationAttachmentPreviewContainer">
						<a id="councilDistrictAffiliationAttachmentPreviewLink" href=""
							target="_blank"></a>
						<button type="button"
							id="councilDistrictAffiliationAttachmentDeleteBtn" class="close"
							onclick="deleteFile('councilDistrictAffiliationAttachmentPreviewContainer', 'councilDistrictAffiliationAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>

				</div>

				<!-- 16. Certificate of Affiliation -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="certificate-affiliation" /><sup
						class="text-danger">*</sup></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilCertificateAffiliationAttachment"
							name="councilCertificateAffiliationAttachment"
							onchange="handleFileUpload(event, 'councilCertificateAffiliationAttachment', 'councilCertificateAffiliationAttachmentPreviewContainer', 'councilCertificateAffiliationAttachmentPreviewLink', 'councilCertificateAffiliationAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label"
							for="councilCertificateAffiliationAttachment"><liferay-ui:message
								key="choose-file" /></label>
					</div>
					<div class="ownerProofid d-none mt-3"
						id="councilCertificateAffiliationAttachmentPreviewContainer">
						<a id="councilCertificateAffiliationAttachmentPreviewLink" href=""
							target="_blank"></a>
						<button type="button"
							id="councilCertificateAffiliationAttachmentDeleteBtn"
							class="close"
							onclick="deleteFile('councilCertificateAffiliationAttachmentPreviewContainer', 'councilCertificateAffiliationAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>

				</div>

			</div>
		</div>
	</div>
</div>
<%@ include	file="/state-level/mahasports-council/organization-details.jsp"%>
<%@ include file="/state-level/mahasports-council/post-competition.jsp"%>