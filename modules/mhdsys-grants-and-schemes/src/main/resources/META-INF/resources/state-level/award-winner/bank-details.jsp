<%@ include file="/init.jsp"%>
<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="bank-details" />
		</div>
		<div class="card-body">
			<div class="row">

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="bank-name" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="bankNameA" id="bankNameA"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${awardWinner.certifiedLetter}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="ifsc-code" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="ifscCodeA" id="ifscCodeA"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${awardWinner.certifiedLetter}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="account-number" /><sup
							class="text-danger">*</sup></label> <input type="number"
							class="form-control" name="accountNumberA" id="accountNumberA"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${awardWinner.certifiedLetter}" />
					</div>
				</div>
				

				<div class="col-md-6">
				<div class="form-group">
					<label> 
						<liferay-ui:message key="bank-statement" /> 
						<sup class="text-danger">*</sup> 
						<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardWinner.bankStatementFileURL}">
							<div>
								<a href="${awardWinner.bankStatementFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.bankStatementFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="bankStatementFile" name="bankStatementFile"
								onchange="handleFileUpload(event, 'bankStatementFile', 'bankStatementFileNewPreviewContainer', 'bankStatementFileNewPreviewLink', 'bankStatementFileNewDeleteButton')">
							<label class="custom-file-label" for="bankStatementFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<c:if test="${not empty awardWinner.bankStatementFileURL}">
							<div class="ownerProofid d-flex mt-3" id="bankStatementFilePreviewContainer">
								<a class="bankStatementFileProofCls" id="bankStatementFilePreviewLink"
									href="${awardWinner.bankStatementFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.bankStatementFileName}
								</a>
								<button type="button" id="bankStatementFiledeleteButton"
									class="dltbankStatementFileBtn close" aria-label="Close"
									onclick="deleteFile('bankStatementFilePreviewContainer', 'bankStatementFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3" id="bankStatementFileNewPreviewContainer">
							<a class="bankStatementFileProofCls" id="bankStatementFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="bankStatementFileNewDeleteButton"
								class="dltbankStatementFileBtn close" aria-label="Close"
								onclick="deleteFile('bankStatementFileNewPreviewContainer', 'bankStatementFile')">
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
								id="bankStatementFile" name="bankStatementFile"
								onchange="handleFileUpload(event, 'bankStatementFile', 'bankStatementFileNewPreviewContainer', 'bankStatementFileNewPreviewLink', 'bankStatementFileNewDeleteButton')">
							<label class="custom-file-label" for="bankStatementFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
						<div class="ownerProofid d-none mt-3" id="bankStatementFileNewPreviewContainer">
							<a class="bankStatementFileProofCls" id="bankStatementFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="bankStatementFileNewDeleteButton"
								class="dltbankStatementFileBtn close" aria-label="Close"
								onclick="deleteFile('bankStatementFileNewPreviewContainer', 'bankStatementFile')">
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
							<liferay-ui:message key="bank-pass-book" /> 
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty awardWinner.bankPassbookFileURL}">
								<div>
									<a href="${awardWinner.bankPassbookFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardWinner.bankPassbookFileName}
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="bankPassbookFile" name="bankPassbookFile"
									onchange="handleFileUpload(event, 'bankPassbookFile', 'bankPassbookFileNewPreviewContainer', 'bankPassbookFileNewPreviewLink', 'bankPassbookFileNewDeleteButton')">
								<label class="custom-file-label" for="bankPassbookFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty awardWinner.bankPassbookFileURL}">
								<div class="ownerProofid d-flex mt-3" id="bankPassbookFilePreviewContainer">
									<a class="bankPassbookFileProofCls" id="bankPassbookFilePreviewLink"
										href="${awardWinner.bankPassbookFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardWinner.bankPassbookFileName}
									</a>
									<button type="button" id="bankPassbookFiledeleteButton"
										class="dltbankPassbookFileBtn close" aria-label="Close"
										onclick="deleteFile('bankPassbookFilePreviewContainer', 'bankPassbookFile')">
										<span aria-hidden="true" class="text-danger">
											<em class="bi bi-x-circle-fill"></em>
										</span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="bankPassbookFileNewPreviewContainer">
								<a class="bankPassbookFileProofCls" id="bankPassbookFileNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="bankPassbookFileNewDeleteButton"
									class="dltbankPassbookFileBtn close" aria-label="Close"
									onclick="deleteFile('bankPassbookFileNewPreviewContainer', 'bankPassbookFile')">
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
									id="bankPassbookFile" name="bankPassbookFile"
									onchange="handleFileUpload(event, 'bankPassbookFile', 'bankPassbookFileNewPreviewContainer', 'bankPassbookFileNewPreviewLink', 'bankPassbookFileNewDeleteButton')">
								<label class="custom-file-label" for="bankPassbookFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<div class="ownerProofid d-none mt-3" id="bankPassbookFileNewPreviewContainer">
								<a class="bankPassbookFileProofCls" id="bankPassbookFileNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="bankPassbookFileNewDeleteButton"
									class="dltbankPassbookFileBtn close" aria-label="Close"
									onclick="deleteFile('bankPassbookFileNewPreviewContainer', 'bankPassbookFile')">
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
						<liferay-ui:message key="cancelled-check" /> 
						<sup class="text-danger">*</sup> 
						<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardWinner.cancelledCheckFileURL}">
							<div>
								<a href="${awardWinner.cancelledCheckFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.cancelledCheckFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="cancelledCheckFile" name="cancelledCheckFile"
								onchange="handleFileUpload(event, 'cancelledCheckFile', 'cancelledCheckFileNewPreviewContainer', 'cancelledCheckFileNewPreviewLink', 'cancelledCheckFileNewDeleteButton')">
							<label class="custom-file-label" for="cancelledCheckFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display (if uploaded before) --%>
						<c:if test="${not empty awardWinner.cancelledCheckFileURL}">
							<div class="ownerProofid d-flex mt-3" id="cancelledCheckFilePreviewContainer">
								<a class="cancelledCheckFileProofCls" id="cancelledCheckFilePreviewLink"
									href="${awardWinner.cancelledCheckFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.cancelledCheckFileName}
								</a>
								<button type="button" id="cancelledCheckFiledeleteButton"
									class="dltcancelledCheckFileBtn close" aria-label="Close"
									onclick="deleteFile('cancelledCheckFilePreviewContainer', 'cancelledCheckFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3" id="cancelledCheckFileNewPreviewContainer">
							<a class="cancelledCheckFileProofCls" id="cancelledCheckFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="cancelledCheckFileNewDeleteButton"
								class="dltcancelledCheckFileBtn close" aria-label="Close"
								onclick="deleteFile('cancelledCheckFileNewPreviewContainer', 'cancelledCheckFile')">
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
								id="cancelledCheckFile" name="cancelledCheckFile"
								onchange="handleFileUpload(event, 'cancelledCheckFile', 'cancelledCheckFileNewPreviewContainer', 'cancelledCheckFileNewPreviewLink', 'cancelledCheckFileNewDeleteButton')">
							<label class="custom-file-label" for="cancelledCheckFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
						<div class="ownerProofid d-none mt-3" id="cancelledCheckFileNewPreviewContainer">
							<a class="cancelledCheckFileProofCls" id="cancelledCheckFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="cancelledCheckFileNewDeleteButton"
								class="dltcancelledCheckFileBtn close" aria-label="Close"
								onclick="deleteFile('cancelledCheckFileNewPreviewContainer', 'cancelledCheckFile')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</c:if>
				</div>
			</div>

              

			</div>
		</div>
	</div>
</div>
