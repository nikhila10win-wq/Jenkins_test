<div class="tab-pane fade" id="remarkDetails" role="tabpanel"
	aria-labelledby="remarkDetails-tab">
	<div class="card card-background p-0">
		<div class="card-header header-card">
			<liferay-ui:message key="remark-details" />
		</div>
		<div class="card-body">
			<!-- Remarks -->
			<div class="row">
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="remarks" /></label>
					<textarea class="form-control" name="remarks"
						placeholder="<liferay-ui:message key='enter-remarks' />"
						<c:if test="${mode eq 'view'}">disabled</c:if>>${nccGrant.remarks}</textarea>
				</div>

				<div class="col-md-6 form-group">
					<label> <liferay-ui:message key="other-upload" /> <em
						class="bi bi-info-circle-fill"
						title="<liferay-ui:message key='allowed-only-pdf-file' />"></em>
					</label>

					<!-- File Input -->
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="otherUpload"
							name="otherUpload" accept="application/pdf"
							onchange="handleFileUpload(event, 'otherUpload', 'otherUploadPreviewContainer', 'otherUploadPreviewLink', 'otherUploadDeleteButton')"
							<c:if test="${mode eq 'view'}">disabled</c:if>> <label
							class="custom-file-label" for="otherUpload"> <liferay-ui:message
								key="choose-file" />
						</label>
					</div>

					<!-- File Preview Container -->
					<div
						class="ownerProofid mt-3 ${empty nccGrant.otherUploadURL ? 'd-none' : 'd-flex'}"
						id="otherUploadPreviewContainer">
						<a class="otherUploadProofCls" id="otherUploadPreviewLink"
							href="${empty nccGrant.otherUploadURL ? '' : nccGrant.otherUploadURL}"
							target="_blank" style="flex-grow: 1; text-decoration: none;">
							${empty nccGrant.otherUploadName ? '' : nccGrant.otherUploadName}
						</a>
						<button type="button" id="otherUploadDeleteButton"
							class="dltOtherUploadBtn close" aria-label="Close"
							onclick="deleteFile('otherUploadPreviewContainer', 'otherUpload')"
							<c:if test="${mode eq 'view'}">style="display:none;"</c:if>>
							<span aria-hidden="true" class="text-danger"> <em
								class="bi bi-x-circle-fill"></em>
							</span>
						</button>
					</div>
				</div>
			</div>

		</div>
	</div>
	  <div class="card-footer bg-transparent text-right p-4">
		<button type="button" class="btn btn-primary"
			onclick="location.href='/group/guest/scout-guide-ncc';">
			<liferay-ui:message key="cancel" />
		</button>
		<c:if test="${mode ne 'view'}">
			<button class="btn btn-primary" type="button"
				onclick="location.reload();">
				<liferay-ui:message key="reset" />
			</button>
			<button type="button" class="btn btn-primary" onclick="setActiveTab('grantsDetails')">
             <liferay-ui:message key="previous" />
        	</button>
			<button type="button" class="btn btn-primary"
				onclick="saveGrantForm('Save')">
				<liferay-ui:message key="submit" />
			</button>
		</c:if>
	</div>  

</div>