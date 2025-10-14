<%@ include file="/init.jsp" %>
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
							class="form-control" name="bankName" id="bankName"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${personalDetails.certifiedLetter}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="ifsc-code" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="ifscCode" id="ifscCode"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${personalDetails.certifiedLetter}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="account-number" /><sup
							class="text-danger">*</sup></label> <input type="number"
							class="form-control" name="accountNumber" id="accountNumber"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${personalDetails.certifiedLetter}" />
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
							<c:if test="${not empty awardYouthOrgCommonDTO.bankStatementURL}">
								<div>
									<a href="${awardYouthOrgCommonDTO.bankStatementURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardYouthOrgCommonDTO.bankStatementFileName}
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="bankStatement" name="bankStatement"
									onchange="handleFileUpload(event, 'bankStatement', 'bankStatementNewPreviewContainer', 'bankStatementNewPreviewLink', 'bankStatementNewDeleteButton')">
								<label class="custom-file-label" for="bankStatement">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<c:if test="${not empty awardYouthOrgCommonDTO.bankStatementURL}">
								<div class="ownerProofid d-flex mt-3" id="bankStatementPreviewContainer">
									<a class="bankStatementProofCls" id="bankStatementPreviewLink"
										href="${awardYouthOrgCommonDTO.bankStatementURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardYouthOrgCommonDTO.bankStatementFileName}
									</a>
									<button type="button" id="bankStatementDeleteButton"
										class="dltbankStatementBtn close" aria-label="Close"
										onclick="deleteFile('bankStatementPreviewContainer', 'bankStatement')">
										<span aria-hidden="true" class="text-danger">
											<em class="bi bi-x-circle-fill"></em>
										</span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="bankStatementNewPreviewContainer">
								<a class="bankStatementProofCls" id="bankStatementNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="bankStatementNewDeleteButton"
									class="dltbankStatementBtn close" aria-label="Close"
									onclick="deleteFile('bankStatementNewPreviewContainer', 'bankStatement')">
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
									id="bankStatement" name="bankStatement"
									onchange="handleFileUpload(event, 'bankStatement', 'bankStatementNewPreviewContainer', 'bankStatementNewPreviewLink', 'bankStatementNewDeleteButton')">
								<label class="custom-file-label" for="bankStatement">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<div class="ownerProofid d-none mt-3" id="bankStatementNewPreviewContainer">
								<a class="bankStatementProofCls" id="bankStatementNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="bankStatementNewDeleteButton"
									class="dltbankStatementBtn close" aria-label="Close"
									onclick="deleteFile('bankStatementNewPreviewContainer', 'bankStatement')">
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
						<c:if test="${not empty awardYouthOrgCommonDTO.affidavitURL}">
							<div>
								<a href="${awardYouthOrgCommonDTO.affidavitURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.affidavitFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="affidavit" name="affidavit"
								onchange="handleFileUpload(event, 'affidavit', 'affidavitNewPreviewContainer', 'affidavitNewPreviewLink', 'affidavitNewDeleteButton')">
							<label class="custom-file-label" for="affidavit">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<c:if test="${not empty awardYouthOrgCommonDTO.affidavitURL}">
							<div class="ownerProofid d-flex mt-3" id="affidavitPreviewContainer">
								<a class="affidavitProofCls" id="affidavitPreviewLink"
									href="${awardYouthOrgCommonDTO.affidavitURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.affidavitFileName}
								</a>
								<button type="button" id="affidavitDeleteButton"
									class="dltaffidavitBtn close" aria-label="Close"
									onclick="deleteFile('affidavitPreviewContainer', 'affidavit')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3" id="affidavitNewPreviewContainer">
							<a class="affidavitProofCls" id="affidavitNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="affidavitNewDeleteButton"
								class="dltaffidavitBtn close" aria-label="Close"
								onclick="deleteFile('affidavitNewPreviewContainer', 'affidavit')">
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
								id="affidavit" name="affidavit"
								onchange="handleFileUpload(event, 'affidavit', 'affidavitNewPreviewContainer', 'affidavitNewPreviewLink', 'affidavitNewDeleteButton')">
							<label class="custom-file-label" for="affidavit">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
						<div class="ownerProofid d-none mt-3" id="affidavitNewPreviewContainer">
							<a class="affidavitProofCls" id="affidavitNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="affidavitNewDeleteButton"
								class="dltaffidavitBtn close" aria-label="Close"
								onclick="deleteFile('affidavitNewPreviewContainer', 'affidavit')">
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
							<liferay-ui:message key="affidavit-500-bond-paper" />
						<sup class="text-danger">*</sup> 
						<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardYouthOrgCommonDTO.affidavitBondpaperURL}">
							<div>
								<a href="${awardYouthOrgCommonDTO.affidavitBondpaperURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.affidavitBondpaperFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="affidavitBondpaper" name="affidavitBondpaper"
								onchange="handleFileUpload(event, 'affidavitBondpaper', 'affidavitBondpaperNewPreviewContainer', 'affidavitBondpaperNewPreviewLink', 'affidavitBondpaperNewDeleteButton')">
							<label class="custom-file-label" for="affidavitBondpaper">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<c:if test="${not empty awardYouthOrgCommonDTO.affidavitBondpaperURL}">
							<div class="ownerProofid d-flex mt-3" id="affidavitBondpaperPreviewContainer">
								<a class="affidavitBondpaperProofCls" id="affidavitBondpaperPreviewLink"
									href="${awardYouthOrgCommonDTO.affidavitBondpaperURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.affidavitBondpaperFileName}
								</a>
								<button type="button" id="affidavitBondpaperDeleteButton"
									class="dltaffidavitBondpaperBtn close" aria-label="Close"
									onclick="deleteFile('affidavitBondpaperPreviewContainer', 'affidavitBondpaper')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3" id="affidavitBondpaperNewPreviewContainer">
							<a class="affidavitBondpaperProofCls" id="affidavitBondpaperNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="affidavitBondpaperNewDeleteButton"
								class="dltaffidavitBondpaperBtn close" aria-label="Close"
								onclick="deleteFile('affidavitBondpaperNewPreviewContainer', 'affidavitBondpaper')">
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
								id="affidavitBondpaper" name="affidavitBondpaper"
								onchange="handleFileUpload(event, 'affidavitBondpaper', 'affidavitBondpaperNewPreviewContainer', 'affidavitBondpaperNewPreviewLink', 'affidavitBondpaperNewDeleteButton')">
							<label class="custom-file-label" for="affidavitBondpaper">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
						<div class="ownerProofid d-none mt-3" id="affidavitBondpaperNewPreviewContainer">
							<a class="affidavitBondpaperProofCls" id="affidavitBondpaperNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="affidavitBondpaperNewDeleteButton"
								class="dltaffidavitBondpaperBtn close" aria-label="Close"
								onclick="deleteFile('affidavitBondpaperNewPreviewContainer', 'affidavitBondpaper')">
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
							id="isDeclarationAcceptedCa" name="isDeclarationAcceptedCa"
							<c:if test="${mode == 'view'}">disabled</c:if>
							<c:if test="${awardYouthOrgCommonDTO.isDeclarationAcceptedCa}">checked</c:if> />
						<label class="form-check-label" for="isDeclarationAccepted">
							<liferay-ui:message key="declaration-authorized-organization" /><sup
							class="text-danger">*</sup>
						</label>
					</div>
				</div>


			</div>
		</div>
	</div>
</div>
