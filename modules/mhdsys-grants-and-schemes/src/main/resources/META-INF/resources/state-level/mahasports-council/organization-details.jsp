<%@ include file="/init.jsp"%>
<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="organization-details" />
		</div>
		<div class="card-body">
			<div class="row">

				<!-- 1. Government Approval Rule 3 -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="rule-3-approval" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="councilRule3Approval"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value="">-- Select --</option>
						<option value="eligible">Eligible</option>
						<option value="ineligible">Ineligible</option>
					</select>
				</div>

				<!-- 2. Directorate Approval -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="directorate-approval" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="councilDirectorateApproval"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value="">-- Select --</option>
						<option value="yes">Yes</option>
						<option value="no">No</option>
					</select>
					<div class="custom-file mt-1">
						<input type="file" class="custom-file-input"
							id="councilDirectorateApprovalAttachment"
							name="councilDirectorateApprovalAttachment"
							onchange="handleFileUpload(event, 'councilDirectorateApprovalAttachment', 'councilDirectorateApprovalAttachmentPreviewContainer', 'councilDirectorateApprovalAttachmentPreviewLink', 'councilDirectorateApprovalAttachmentDeleteButton')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label"
							for="councilDirectorateApprovalAttachment"> <liferay-ui:message
								key="choose-file" />
						</label>
					</div>
					<div class="d-none mt-3"
						id="councilDirectorateApprovalAttachmentPreviewContainer">
						<a id="councilDirectorateApprovalAttachmentPreviewLink" href=""
							target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
						<button type="button"
							id="councilDirectorateApprovalAttachmentDeleteButton"
							class="close"
							onclick="deleteFile('councilDirectorateApprovalAttachmentPreviewContainer', 'councilDirectorateApprovalAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 3. Prescribed Format with DSO -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="prescribed-format-dso" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="councilPrescribedFormatDSO"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value="">-- Select --</option>
						<option value="yes">Yes</option>
						<option value="no">No</option>
					</select>
					<div class="custom-file mt-1">
						<input type="file" class="custom-file-input"
							id="councilPrescribedFormatAttachment"
							name="councilPrescribedFormatAttachment"
							onchange="handleFileUpload(event, 'councilPrescribedFormatAttachment', 'councilPrescribedFormatAttachmentPreviewContainer', 'councilPrescribedFormatAttachmentPreviewLink', 'councilPrescribedFormatAttachmentDeleteButton')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label" for="councilPrescribedFormatAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="d-none mt-3"
						id="councilPrescribedFormatAttachmentPreviewContainer">
						<a id="councilPrescribedFormatAttachmentPreviewLink" href=""
							target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
						<button type="button"
							id="councilPrescribedFormatAttachmentDeleteButton" class="close"
							onclick="deleteFile('councilPrescribedFormatAttachmentPreviewContainer', 'councilPrescribedFormatAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 4. CA Certified Statement -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="ca-certified-statement" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="councilCaStatement"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value="">-- Select --</option>
						<option value="added">Added</option>
						<option value="not-added">Not Added</option>
					</select>
					<div class="custom-file mt-1">
						<input type="file" class="custom-file-input"
							id="councilCaStatementAttachment"
							name="councilCaStatementAttachment"
							onchange="handleFileUpload(event, 'councilCaStatementAttachment', 'councilCaStatementAttachmentPreviewContainer', 'councilCaStatementAttachmentPreviewLink', 'councilCaStatementAttachmentDeleteButton')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label" for="councilCaStatementAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="d-none mt-3"
						id="councilCaStatementAttachmentPreviewContainer">
						<a id="councilCaStatementAttachmentPreviewLink" href=""
							target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
						<button type="button"
							id="councilCaStatementAttachmentDeleteButton" class="close"
							onclick="deleteFile('councilCaStatementAttachmentPreviewContainer', 'councilCaStatementAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 5. Office Bearers List -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="office-bearers-list" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="councilOfficeBearersList"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value="">-- Select --</option>
						<option value="added">Added</option>
						<option value="not-added">Not Added</option>
					</select>
					<div class="custom-file mt-1">
						<input type="file" class="custom-file-input"
							id="councilOfficeBearersAttachment"
							name="councilOfficeBearersAttachment"
							onchange="handleFileUpload(event, 'councilOfficeBearersAttachment', 'councilOfficeBearersAttachmentPreviewContainer', 'councilOfficeBearersAttachmentPreviewLink', 'councilOfficeBearersAttachmentDeleteButton')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label" for="councilOfficeBearersAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="d-none mt-3"
						id="councilOfficeBearersAttachmentPreviewContainer">
						<a id="councilOfficeBearersAttachmentPreviewLink" href=""
							target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
						<button type="button"
							id="councilOfficeBearersAttachmentDeleteButton" class="close"
							onclick="deleteFile('councilOfficeBearersAttachmentPreviewContainer', 'councilOfficeBearersAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 6. Organization Certificate -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="organization-certificate" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="councilOrganizationCertificate"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value="">-- Select --</option>
						<option value="yes">Yes</option>
						<option value="no">No</option>
					</select>
					<div class="custom-file mt-1">
						<input type="file" class="custom-file-input"
							id="councilOrganizationCertificateAttachment"
							name="councilOrganizationCertificateAttachment"
							onchange="handleFileUpload(event, 'councilOrganizationCertificateAttachment', 'councilOrganizationCertificateAttachmentPreviewContainer', 'councilOrganizationCertificateAttachmentPreviewLink', 'councilOrganizationCertificateAttachmentDeleteButton')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label"
							for="councilOrganizationCertificateAttachment"> <liferay-ui:message
								key="choose-file" />
						</label>
					</div>
					<div class="d-none mt-3"
						id="councilOrganizationCertificateAttachmentPreviewContainer">
						<a id="councilOrganizationCertificateAttachmentPreviewLink"
							href="" target="_blank"
							style="flex-grow: 1; text-decoration: none;"></a>
						<button type="button"
							id="councilOrganizationCertificateAttachmentDeleteButton"
							class="close"
							onclick="deleteFile('councilOrganizationCertificateAttachmentPreviewContainer', 'councilOrganizationCertificateAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 7. Sports Training in Previous Year -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="sports-training-previous" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="councilSportsTrainingPrevious"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value="">-- Select --</option>
						<option value="organized">Organized</option>
						<option value="not-organized">Not Organized</option>
					</select> <input type="text" class="form-control mt-1"
						name="councilSportsTrainingDetails" placeholder="Enter details"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 8. Maharashtra Sports Grants Form -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="maharashtra-sports-grants" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="councilSportsGrantFormDetails"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 9. Concern Letter -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="concern-letter" /><sup
						class="text-danger">*</sup></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilConcernLetterAttachment"
							name="councilConcernLetterAttachment"
							onchange="handleFileUpload(event, 'councilConcernLetterAttachment', 'councilConcernLetterAttachmentPreviewContainer', 'councilConcernLetterAttachmentPreviewLink', 'councilConcernLetterAttachmentDeleteButton')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label" for="councilConcernLetterAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="ownerProofid d-none mt-3"
						id="councilConcernLetterAttachmentPreviewContainer">
						<a class="councilConcernLetterAttachmentProofCls"
							id="councilConcernLetterAttachmentPreviewLink" href=""
							target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
						<button type="button"
							id="councilConcernLetterAttachmentDeleteButton"
							class="dltConcernLetterBtn close" aria-label="Close"
							onclick="deleteFile('councilConcernLetterAttachmentPreviewContainer', 'councilConcernLetterAttachment')">
							<span aria-hidden="true" class="text-danger"> <em
								class="bi bi-x-circle-fill"></em>
							</span>
						</button>
					</div>
				</div>

				<!-- 10. Cost Estimation -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="cost-estimation" /><sup
						class="text-danger">*</sup></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilCostEstimationAttachment"
							name="councilCostEstimationAttachment"
							onchange="handleFileUpload(event, 'councilCostEstimationAttachment', 'councilCostEstimationAttachmentPreviewContainer', 'councilCostEstimationAttachmentPreviewLink', 'councilCostEstimationAttachmentDeleteButton')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label" for="councilCostEstimationAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="ownerProofid d-none mt-3"
						id="councilCostEstimationAttachmentPreviewContainer">
						<a class="councilCostEstimationAttachmentProofCls"
							id="councilCostEstimationAttachmentPreviewLink" href=""
							target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
						<button type="button"
							id="councilCostEstimationAttachmentDeleteButton"
							class="dltCostEstimationBtn close" aria-label="Close"
							onclick="deleteFile('councilCostEstimationAttachmentPreviewContainer', 'councilCostEstimationAttachment')">
							<span aria-hidden="true" class="text-danger"> <em
								class="bi bi-x-circle-fill"></em>
							</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
