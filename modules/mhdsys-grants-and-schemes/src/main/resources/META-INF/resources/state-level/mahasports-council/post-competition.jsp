<%@ include file="/init.jsp"%>
<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="post-competition-details" />
		</div>
		<div class="card-body">
			<div class="row">

				<!-- 1. Detailed Report of Competition -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="detailed-report" /><sup class="text-danger">*</sup></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilDetailedReportAttachment"
							name="councilDetailedReportAttachment"
							onchange="handleFileUpload(event, 'councilDetailedReportAttachment', 'councilDetailedReportAttachmentPreviewContainer', 'councilDetailedReportAttachmentPreviewLink', 'councilDetailedReportAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>>
						<label class="custom-file-label" for="councilDetailedReportAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="ownerProofid d-none mt-3" id="councilDetailedReportAttachmentPreviewContainer">
						<a id="councilDetailedReportAttachmentPreviewLink" href="" target="_blank"></a>
						<button type="button" id="councilDetailedReportAttachmentDeleteBtn" class="close"
							onclick="deleteFile('councilDetailedReportAttachmentPreviewContainer', 'councilDetailedReportAttachment')">
							<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 2. Audited Statement of Expenses -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="audited-statement-expenses" /><sup class="text-danger">*</sup></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilAuditedStatementAttachment"
							name="councilAuditedStatementAttachment"
							onchange="handleFileUpload(event, 'councilAuditedStatementAttachment', 'councilAuditedStatementAttachmentPreviewContainer', 'councilAuditedStatementAttachmentPreviewLink', 'councilAuditedStatementAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>>
						<label class="custom-file-label" for="councilAuditedStatementAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="ownerProofid d-none mt-3" id="councilAuditedStatementAttachmentPreviewContainer">
						<a id="councilAuditedStatementAttachmentPreviewLink" href="" target="_blank"></a>
						<button type="button" id="councilAuditedStatementAttachmentDeleteBtn" class="close"
							onclick="deleteFile('councilAuditedStatementAttachmentPreviewContainer', 'councilAuditedStatementAttachment')">
							<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 3. List of Team Players -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="team-players-list" /><sup class="text-danger">*</sup></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilTeamPlayersListAttachment"
							name="councilTeamPlayersListAttachment"
							onchange="handleFileUpload(event, 'councilTeamPlayersListAttachment', 'councilTeamPlayersListAttachmentPreviewContainer', 'councilTeamPlayersListAttachmentPreviewLink', 'councilTeamPlayersListAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>>
						<label class="custom-file-label" for="councilTeamPlayersListAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="ownerProofid d-none mt-3" id="councilTeamPlayersListAttachmentPreviewContainer">
						<a id="councilTeamPlayersListAttachmentPreviewLink" href="" target="_blank"></a>
						<button type="button" id="councilTeamPlayersListAttachmentDeleteBtn" class="close"
							onclick="deleteFile('councilTeamPlayersListAttachmentPreviewContainer', 'councilTeamPlayersListAttachment')">
							<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 4. Funds Raised by Committee -->
				<div class="col-md-6 form-group">
					<label for="councilFundsRaised">
						<liferay-ui:message key="funds-raised" />
						<sup class="text-danger">*</sup>
					</label>
					<input type="number" step="any" class="form-control" id="councilFundsRaised" name="councilFundsRaised" <c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 5. Actual Loss Incurred -->
				<div class="col-md-6 form-group">
					<label for="councilActualLoss">
						<liferay-ui:message key="actual-loss" />
						<sup class="text-danger">*</sup>
					</label>
					<input type="number" step="any" class="form-control" id="councilActualLoss" name="councilActualLoss" <c:if test="${mode eq 'view'}">disabled</c:if>>
				</div>

				<!-- 6. Souvenir Issued -->
				<div class="col-md-6 form-group">
					<label>
						<liferay-ui:message key="souvenir-issued" />
						<sup class="text-danger">*</sup>
					</label><br>
					<label><input type="radio" name="councilSouvenirIssued" value="yes" <c:if test="${mode eq 'view'}">disabled</c:if>> Yes</label>
					<label class="ms-2"><input type="radio" name="councilSouvenirIssued" value="no" <c:if test="${mode eq 'view'}">disabled</c:if>> No</label>
					
					<div class="custom-file mt-1">
						<input type="file" class="custom-file-input"
							id="councilSouvenirAttachment"
							name="councilSouvenirAttachment"
							onchange="handleFileUpload(event, 'councilSouvenirAttachment', 'councilSouvenirAttachmentPreviewContainer', 'councilSouvenirAttachmentPreviewLink', 'councilSouvenirAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>>
						<label class="custom-file-label" for="councilSouvenirAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="ownerProofid d-none mt-3" id="councilSouvenirAttachmentPreviewContainer">
						<a id="councilSouvenirAttachmentPreviewLink" href="" target="_blank"></a>
						<button type="button" id="councilSouvenirAttachmentDeleteBtn" class="close"
							onclick="deleteFile('councilSouvenirAttachmentPreviewContainer', 'councilSouvenirAttachment')">
							<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

				<!-- 7. Photos from Competition -->
				<%-- <div class="col-md-6 form-group">
					<label><liferay-ui:message key="competition-photos" /><sup class="text-danger">*</sup></label>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilCompetitionPhotos"
							name="councilCompetitionPhotos" multiple
							onchange="handleFileUpload(event, 'councilCompetitionPhotos', 'councilCompetitionPhotosPreviewContainer', 'councilCompetitionPhotosPreviewLink', 'councilCompetitionPhotosDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>>
						<label class="custom-file-label" for="councilCompetitionPhotos">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<small class="form-text text-muted">
						<liferay-ui:message key="minimum-10-photos-required" />
					</small>
					<div class="ownerProofid d-none mt-3" id="councilCompetitionPhotosPreviewContainer">
						<a id="councilCompetitionPhotosPreviewLink" href="" target="_blank"></a>
						<button type="button" id="councilCompetitionPhotosDeleteBtn" class="close"
							onclick="deleteFile('councilCompetitionPhotosPreviewContainer', 'councilCompetitionPhotos')">
							<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div> --%>
				<!-- Only show upload input in 'add' or 'edit' mode -->
				<c:if test="${mode eq 'add' or mode eq 'edit'}">
					<div class="form-group col-md-6">
						<label> <liferay-ui:message key="competition-photos" /> <sup
							class="text-danger">*</sup>
						</label>

						<div class="custom-file">
							<input type="file"
								class="custom-file-input councilCompetitionPhotos"
								id="councilCompetitionPhotos" name="councilCompetitionPhotos"
								multiple
								onchange="handleMultipleFileUpload(this, 'councilCompetitionPhotos', 'councilCompetitionPhotosPreviewContainer', 'councilCompetitionPhotosPreviewList', 'councilCompetitionPhotosError', 'councilCompetitionPhotosHidden')">
							<label class="custom-file-label" for="councilCompetitionPhotos">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>

						<!-- Error message -->
						<span id="councilCompetitionPhotosError"></span>

						<!-- Hidden input to store file names -->
						<input type="hidden" id="councilCompetitionPhotosHidden"
							name="councilCompetitionPhotosHidden"
							value='<c:if test="${mode eq 'edit' and not empty councilDTO.competitionPhotosNames}">
                       <c:forEach var="photoName" items="${councilDTO.competitionPhotosNames}" varStatus="status">
                           ${photoName}<c:if test="${not status.last}">,</c:if>
                       </c:forEach>
                   </c:if>'>

						<!-- Preview and Delete Section -->
						<div class="mt-3" id="councilCompetitionPhotosPreviewContainer"
							style='<c:if test="${mode ne 'edit' or empty councilDTO.competitionPhotosURLs}">display: none;</c:if>'>
							<ul id="councilCompetitionPhotosPreviewList" class="list-group">
								<c:if
									test="${mode eq 'edit' and not empty councilDTO.competitionPhotosURLs}">
									<c:forEach var="photoURL"
										items="${councilDTO.competitionPhotosURLs}" varStatus="status">
										<li
											class="list-group-item d-flex justify-content-between align-items-center">
											<a href="${photoURL}" target="_blank" class="text-truncate">
												${councilDTO.competitionPhotosNames[status.index]} </a>
											<button type="button" class="btn btn-danger btn-sm"
												onclick="removeFile(${status.index}, 'councilCompetitionPhotosPreviewContainer', 'councilCompetitionPhotosPreviewList', 'councilCompetitionPhotosError', 'councilCompetitionPhotosHidden', 'councilCompetitionPhotos')">
												<i class="bi bi-x-circle-fill"></i>
											</button>
										</li>
									</c:forEach>
								</c:if>
							</ul>
						</div>

						<!-- Optional note -->
						<!-- <small class="form-text text-muted"> <liferay-ui:message
								key="minimum-10-photos-required" />
						</small> -->
					</div>
				</c:if>

				<!-- Only show preview in view mode -->
				<c:if
					test="${mode eq 'view' and not empty councilDTO.competitionPhotosURLs}">
					<div class="form-group col-md-6">
						<label> <liferay-ui:message key="competition-photos" />
						</label>
						<div>
							<c:forEach var="photoURL"
								items="${councilDTO.competitionPhotosURLs}" varStatus="status">
								<a href="${photoURL}" target="_blank"
									class="text-truncate d-block">
									${councilDTO.competitionPhotosNames[status.index]} </a>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<!-- 8. Affidavit -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="affidavit" /><sup
						class="text-danger">*</sup></label>
					<p>
						<a href="" target="_blank"><liferay-ui:message
								key="download-affidavit-format" /></a>
					</p>
					<div class="custom-file">
						<input type="file" class="custom-file-input"
							id="councilAffidavitAttachment" name="councilAffidavitAttachment"
							onchange="handleFileUpload(event, 'councilAffidavitAttachment', 'councilAffidavitAttachmentPreviewContainer', 'councilAffidavitAttachmentPreviewLink', 'councilAffidavitAttachmentDeleteBtn')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label" for="councilAffidavitAttachment">
							<liferay-ui:message key="choose-file" />
						</label>
					</div>
					<div class="ownerProofid d-none mt-3"
						id="councilAffidavitAttachmentPreviewContainer">
						<a id="councilAffidavitAttachmentPreviewLink" href=""
							target="_blank"></a>
						<button type="button" id="councilAffidavitAttachmentDeleteBtn"
							class="close"
							onclick="deleteFile('councilAffidavitAttachmentPreviewContainer', 'councilAffidavitAttachment')">
							<span aria-hidden="true" class="text-danger"><em
								class="bi bi-x-circle-fill"></em></span>
						</button>
					</div>
				</div>

			</div>

			<!-- Declaration -->
			<div class="row">
				<div class="col-md-12">
					<div class="form-group form-check">
						<input type="checkbox" class="form-check-input" id="isDeclarationAccepted" name="isDeclarationAccepted"
							<c:if test="${mode == 'view'}">disabled</c:if> />
						<label class="form-check-label" for="isDeclarationAccepted">
							<liferay-ui:message key="declaration-authorized-organization" /><sup class="text-danger">*</sup>
						</label>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
