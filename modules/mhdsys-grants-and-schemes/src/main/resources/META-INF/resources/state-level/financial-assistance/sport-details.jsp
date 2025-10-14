<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="sports-details" />
		</div>
		<div class="card-body">
			<div class="row">
			
			<div class="col-md-6">
			    <div class="form-group">
			        <label>
			            <liferay-ui:message key="sports-performance-details" />
			            <sup class="text-danger">*</sup>
			        </label>
			        <input 
			            type="text" 
			            class="form-control" 
			            name="sportsPerformanceDetails" 
			            id="sportsPerformanceDetails"
			            value="${competitionLevel}" readonly
			            />
			    </div>
			</div>
			
			
				<%-- <div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="sports-performance-details" /><sup class="text-danger">*</sup></label>
						<select class="form-control" name="sportsPerformanceDetails"
							id="sportsPerformanceDetails"
							<c:if test="${mode eq 'view'}">disabled</c:if>>
							<option value=""><liferay-ui:message key="select" /></option>
							<option value="Category 1"
								<c:if test="${financeAssistance.sportsPerformanceDetails eq 'State'}">selected</c:if>>State</option>
							<option value="Category 2"
								<c:if test="${financeAssistance.sportsPerformanceDetails eq 'National'}">selected</c:if>>National</option>
							<option value="Category 3"
								<c:if test="${financeAssistance.sportsPerformanceDetails eq 'International'}">selected</c:if>>International</option>
						</select>
					</div>
				</div>
 --%>
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="certified-by" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="certifiedLetter" id="certifiedLetter"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${financeAssistance.certifiedLetter}" />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label> <liferay-ui:message
								key="performance-certificate" /> <sup
							class="text-danger">*</sup> <em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
						</label>

						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty financeAssistance.certifiedLetterFileURL}">
								<div>
									<a href="${financeAssistance.certifiedLetterFileURL}"
										target="_blank" style="flex-grow: 1; text-decoration: none;">
										${financeAssistance.certifiedLetterFileName} </a>
								</div>
							</c:if>
						</c:if>

						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="certifiedLetterFile" name="certifiedLetterFile"
									onchange="handleFileUpload(event, 'certifiedLetterFile', 'certifiedLetterFileNewPreviewContainer', 'certifiedLetterFileNewPreviewLink', 'certifiedLetterFileNewDeleteButton')">
								<label class="custom-file-label" for="certifiedLetterFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>

							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty financeAssistance.certifiedLetterFileURL}">
								<div class="ownerProofid d-flex mt-3"
									id="certifiedLetterFilePreviewContainer">
									<a class="certifiedLetterFileProofCls"
										id="certifiedLetterFilePreviewLink"
										href="${financeAssistance.certifiedLetterFileURL}"
										target="_blank" style="flex-grow: 1; text-decoration: none;">
										${financeAssistance.certifiedLetterFileName} </a>
									<button type="button" id="certifiedLetterFiledeleteButton"
										class="dltcertifiedLetterFileBtn close" aria-label="Close"
										onclick="deleteFile('certifiedLetterFilePreviewContainer', 'certifiedLetterFile')">
										<span aria-hidden="true" class="text-danger"><em
											class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>

							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3"
								id="certifiedLetterFileNewPreviewContainer">
								<a class="certifiedLetterFileProofCls"
									id="certifiedLetterFileNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="certifiedLetterFileNewDeleteButton"
									class="dltcertifiedLetterFileBtn close" aria-label="Close"
									onclick="deleteFile('certifiedLetterFileNewPreviewContainer', 'certifiedLetterFile')">
									<span aria-hidden="true" class="text-danger"><em
										class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>


						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="certifiedLetterFile" name="certifiedLetterFile"
									onchange="handleFileUpload(event, 'certifiedLetterFile', 'certifiedLetterFileNewPreviewContainer', 'certifiedLetterFileNewPreviewLink', 'certifiedLetterFileNewDeleteButton')">
								<label class="custom-file-label" for="customFile"> <liferay-ui:message
										key="choose-file" />
								</label>
							</div>
							<%-- Empty container for new file upload preview --%>
							<div class="ownerProofid d-none mt-3"
								id="certifiedLetterFileNewPreviewContainer">
								<a class="certifiedLetterFileProofCls"
									id="certifiedLetterFileNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="certifiedLetterFileNewDeleteButton"
									class="dltcertifiedLetterFileBtn close" aria-label="Close"
									onclick="deleteFile('certifiedLetterFileNewPreviewContainer', 'certifiedLetterFile')">
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