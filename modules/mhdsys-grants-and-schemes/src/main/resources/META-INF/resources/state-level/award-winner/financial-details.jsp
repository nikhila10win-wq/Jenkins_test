<%@ include file="/init.jsp"%>
<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="financial-details" />
		</div>


		<div class="card-body">

			<div class="row">
				
				
				
				<div class="col-md-6">
				<div class="form-group">
					<label> 
						<liferay-ui:message key="player's-consent-letter-to-be-original-guide" /> 
						<sup class="text-danger">*</sup> 
						<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardWinner.playersConsentLetterFileURL}">
							<div>
								<a href="${awardWinner.playersConsentLetterFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.playersConsentLetterFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="playersConsentLetterFile" name="playersConsentLetterFile"
								onchange="handleFileUpload(event, 'playersConsentLetterFile', 'playersConsentLetterFileNewPreviewContainer', 'playersConsentLetterFileNewPreviewLink', 'playersConsentLetterFileNewDeleteButton')">
							<label class="custom-file-label" for="playersConsentLetterFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<c:if test="${not empty awardWinner.playersConsentLetterFileURL}">
							<div class="ownerProofid d-flex mt-3" id="playersConsentLetterFilePreviewContainer">
								<a class="playersConsentLetterFileProofCls" id="playersConsentLetterFilePreviewLink"
									href="${awardWinner.playersConsentLetterFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.playersConsentLetterFileName}
								</a>
								<button type="button" id="playersConsentLetterFiledeleteButton"
									class="dltplayersConsentLetterFileBtn close" aria-label="Close"
									onclick="deleteFile('playersConsentLetterFilePreviewContainer', 'playersConsentLetterFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<div class="ownerProofid d-none mt-3" id="playersConsentLetterFileNewPreviewContainer">
							<a class="playersConsentLetterFileProofCls" id="playersConsentLetterFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="playersConsentLetterFileNewDeleteButton"
								class="dltplayersConsentLetterFileBtn close" aria-label="Close"
								onclick="deleteFile('playersConsentLetterFileNewPreviewContainer', 'playersConsentLetterFile')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</c:if>
			
					<%-- Add/Other Modes --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="playersConsentLetterFile" name="playersConsentLetterFile"
								onchange="handleFileUpload(event, 'playersConsentLetterFile', 'playersConsentLetterFileNewPreviewContainer', 'playersConsentLetterFileNewPreviewLink', 'playersConsentLetterFileNewDeleteButton')">
							<label class="custom-file-label" for="playersConsentLetterFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
						<div class="ownerProofid d-none mt-3" id="playersConsentLetterFileNewPreviewContainer">
							<a class="playersConsentLetterFileProofCls" id="playersConsentLetterFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="playersConsentLetterFileNewDeleteButton"
								class="dltplayersConsentLetterFileBtn close" aria-label="Close"
								onclick="deleteFile('playersConsentLetterFileNewPreviewContainer', 'playersConsentLetterFile')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</c:if>
				</div>
			</div>

				


             <div class="col-md-6">
					<div class="form-group">
						<label> 
							<liferay-ui:message key="affidavit" /> 
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty awardWinner.affidavitFileURL}">
								<div>
									<a href="${awardWinner.affidavitFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardWinner.affidavitFileName}
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="affidavitFile" name="affidavitFile"
									onchange="handleFileUpload(event, 'affidavitFile', 'affidavitFileNewPreviewContainer', 'affidavitFileNewPreviewLink', 'affidavitFileNewDeleteButton')">
								<label class="custom-file-label" for="affidavitFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<c:if test="${not empty awardWinner.affidavitFileURL}">
								<div class="ownerProofid d-flex mt-3" id="affidavitFilePreviewContainer">
									<a class="affidavitFileProofCls" id="affidavitFilePreviewLink"
										href="${awardWinner.affidavitFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardWinner.affidavitFileName}
									</a>
									<button type="button" id="affidavitFiledeleteButton"
										class="dltaffidavitFileBtn close" aria-label="Close"
										onclick="deleteFile('affidavitFilePreviewContainer', 'affidavitFile')">
										<span aria-hidden="true" class="text-danger">
											<em class="bi bi-x-circle-fill"></em>
										</span>
									</button>
								</div>
							</c:if>
				
							<div class="ownerProofid d-none mt-3" id="affidavitFileNewPreviewContainer">
								<a class="affidavitFileProofCls" id="affidavitFileNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="affidavitFileNewDeleteButton"
									class="dltaffidavitFileBtn close" aria-label="Close"
									onclick="deleteFile('affidavitFileNewPreviewContainer', 'affidavitFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="affidavitFile" name="affidavitFile"
									onchange="handleFileUpload(event, 'affidavitFile', 'affidavitFileNewPreviewContainer', 'affidavitFileNewPreviewLink', 'affidavitFileNewDeleteButton')">
								<label class="custom-file-label" for="affidavitFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<div class="ownerProofid d-none mt-3" id="affidavitFileNewPreviewContainer">
								<a class="affidavitFileProofCls" id="affidavitFileNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="affidavitFileNewDeleteButton"
									class="dltaffidavitFileBtn close" aria-label="Close"
									onclick="deleteFile('affidavitFileNewPreviewContainer', 'affidavitFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
					</div>
				</div>

              
              
              <div class="col-md-12">
					<div class="form-group form-check mb-1">
						<input type="checkbox" class="form-check-input"
							id="isDeclarationAcceptedA" name="isDeclarationAcceptedA"
							<c:if test="${mode == 'view'}">disabled</c:if>
							<c:if test="${awardWinner.isDeclarationAccepted}">checked</c:if> />
						<label class="form-check-label" for="isDeclarationAcceptedA">
							<liferay-ui:message key="declaration-authorized-organization" /><sup
							class="text-danger">*</sup>
						</label>
					</div>
				</div>
				

			</div>
		</div>
	</div>
</div>
