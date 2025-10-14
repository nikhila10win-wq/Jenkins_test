
<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="financial-details" />
		</div>
		<div class="card-body">
			<div class="row">

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="assistance-matter" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="assistanceMatter" id="assistanceMatter"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${financeAssistance.certifiedLetter}" />
					</div>
				</div>

				<div class="col-md-6">
				<div class="form-group">
					<label> 
						<liferay-ui:message key="assistance-certificate" /> 
						<sup class="text-danger">*</sup> 
						<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty financeAssistance.assistanceMatterFileURL}">
							<div>
								<a href="${financeAssistance.assistanceMatterFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${financeAssistance.assistanceMatterFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="assistanceMatterFile" name="assistanceMatterFile"
								onchange="handleFileUpload(event, 'assistanceMatterFile', 'assistanceMatterFileNewPreviewContainer', 'assistanceMatterFileNewPreviewLink', 'assistanceMatterFileNewDeleteButton')">
							<label class="custom-file-label" for="assistanceMatterFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display (if uploaded before) --%>
						<c:if test="${not empty financeAssistance.assistanceMatterFileURL}">
							<div class="ownerProofid d-flex mt-3" id="assistanceMatterFilePreviewContainer">
								<a class="assistanceMatterFileProofCls" id="assistanceMatterFilePreviewLink"
									href="${financeAssistance.assistanceMatterFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${financeAssistance.assistanceMatterFileName}
								</a>
								<button type="button" id="assistanceMatterFiledeleteButton"
									class="dltassistanceMatterFileBtn close" aria-label="Close"
									onclick="deleteFile('assistanceMatterFilePreviewContainer', 'assistanceMatterFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3" id="assistanceMatterFileNewPreviewContainer">
							<a class="assistanceMatterFileProofCls" id="assistanceMatterFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="assistanceMatterFileNewDeleteButton"
								class="dltassistanceMatterFileBtn close" aria-label="Close"
								onclick="deleteFile('assistanceMatterFileNewPreviewContainer', 'assistanceMatterFile')">
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
								id="assistanceMatterFile" name="assistanceMatterFile"
								onchange="handleFileUpload(event, 'assistanceMatterFile', 'assistanceMatterFileNewPreviewContainer', 'assistanceMatterFileNewPreviewLink', 'assistanceMatterFileNewDeleteButton')">
							<label class="custom-file-label" for="assistanceMatterFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
						<div class="ownerProofid d-none mt-3" id="assistanceMatterFileNewPreviewContainer">
							<a class="assistanceMatterFileProofCls" id="assistanceMatterFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="assistanceMatterFileNewDeleteButton"
								class="dltassistanceMatterFileBtn close" aria-label="Close"
								onclick="deleteFile('assistanceMatterFileNewPreviewContainer', 'assistanceMatterFile')">
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
						<label><liferay-ui:message
								key="estimate-and-detailed-information" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="itemsEstimate" id="itemsEstimate"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${financeAssistance.certifiedLetter}" />
					</div>
				</div>

				<div class="col-md-6">
						<div class="form-group">
							<label> 
								<liferay-ui:message key="estimate-document" /> 
								<sup class="text-danger">*</sup> 
								<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
							</label>
					
							<%-- View Mode --%>
							<c:if test="${mode eq 'view'}">
								<c:if test="${not empty financeAssistance.itemsEstimateFileURL}">
									<div>
										<a href="${financeAssistance.itemsEstimateFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
											${financeAssistance.itemsEstimateFileName}
										</a>
									</div>
								</c:if>
							</c:if>
					
							<%-- Edit Mode --%>
							<c:if test="${mode eq 'edit'}">
								<div class="custom-file">
									<input type="file" class="custom-file-input"
										id="itemsEstimateFile" name="itemsEstimateFile"
										onchange="handleFileUpload(event, 'itemsEstimateFile', 'itemsEstimateFileNewPreviewContainer', 'itemsEstimateFileNewPreviewLink', 'itemsEstimateFileNewDeleteButton')">
									<label class="custom-file-label" for="itemsEstimateFile">
										<liferay-ui:message key="choose-file" />
									</label>
								</div>
					
								<%-- Existing file display (if uploaded before) --%>
								<c:if test="${not empty financeAssistance.itemsEstimateFileURL}">
									<div class="ownerProofid d-flex mt-3" id="itemsEstimateFilePreviewContainer">
										<a class="itemsEstimateFileProofCls" id="itemsEstimateFilePreviewLink"
											href="${financeAssistance.itemsEstimateFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
											${financeAssistance.itemsEstimateFileName}
										</a>
										<button type="button" id="itemsEstimateFiledeleteButton"
											class="dltitemsEstimateFileBtn close" aria-label="Close"
											onclick="deleteFile('itemsEstimateFilePreviewContainer', 'itemsEstimateFile')">
											<span aria-hidden="true" class="text-danger">
												<em class="bi bi-x-circle-fill"></em>
											</span>
										</button>
									</div>
								</c:if>
					
								<%-- New file preview (after selection) --%>
								<div class="ownerProofid d-none mt-3" id="itemsEstimateFileNewPreviewContainer">
									<a class="itemsEstimateFileProofCls" id="itemsEstimateFileNewPreviewLink"
										href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
									<button type="button" id="itemsEstimateFileNewDeleteButton"
										class="dltitemsEstimateFileBtn close" aria-label="Close"
										onclick="deleteFile('itemsEstimateFileNewPreviewContainer', 'itemsEstimateFile')">
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
										id="itemsEstimateFile" name="itemsEstimateFile"
										onchange="handleFileUpload(event, 'itemsEstimateFile', 'itemsEstimateFileNewPreviewContainer', 'itemsEstimateFileNewPreviewLink', 'itemsEstimateFileNewDeleteButton')">
									<label class="custom-file-label" for="itemsEstimateFile">
										<liferay-ui:message key="choose-file" />
									</label>
								</div>
								<div class="ownerProofid d-none mt-3" id="itemsEstimateFileNewPreviewContainer">
									<a class="itemsEstimateFileProofCls" id="itemsEstimateFileNewPreviewLink"
										href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
									<button type="button" id="itemsEstimateFileNewDeleteButton"
										class="dltitemsEstimateFileBtn close" aria-label="Close"
										onclick="deleteFile('itemsEstimateFileNewPreviewContainer', 'itemsEstimateFile')">
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
						<label><liferay-ui:message
								key="expense-budge-and-undertaking" /><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name="expenseBudget"
							id="expenseBudget"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${financeAssistance.certifiedLetter}" />
					</div>
				</div>

				<div class="col-md-6">
				<div class="form-group">
					<label> 
						<liferay-ui:message key="expense-budget" /> 
						<sup class="text-danger">*</sup> 
						<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty financeAssistance.expenseBudgetFileURL}">
							<div>
								<a href="${financeAssistance.expenseBudgetFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${financeAssistance.expenseBudgetFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="expenseBudgetFile" name="expenseBudgetFile"
								onchange="handleFileUpload(event, 'expenseBudgetFile', 'expenseBudgetFileNewPreviewContainer', 'expenseBudgetFileNewPreviewLink', 'expenseBudgetFileNewDeleteButton')">
							<label class="custom-file-label" for="expenseBudgetFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display (if uploaded before) --%>
						<c:if test="${not empty financeAssistance.expenseBudgetFileURL}">
							<div class="ownerProofid d-flex mt-3" id="expenseBudgetFilePreviewContainer">
								<a class="expenseBudgetFileProofCls" id="expenseBudgetFilePreviewLink"
									href="${financeAssistance.expenseBudgetFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${financeAssistance.expenseBudgetFileName}
								</a>
								<button type="button" id="expenseBudgetFiledeleteButton"
									class="dltexpenseBudgetFileBtn close" aria-label="Close"
									onclick="deleteFile('expenseBudgetFilePreviewContainer', 'expenseBudgetFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3" id="expenseBudgetFileNewPreviewContainer">
							<a class="expenseBudgetFileProofCls" id="expenseBudgetFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="expenseBudgetFileNewDeleteButton"
								class="dltexpenseBudgetFileBtn close" aria-label="Close"
								onclick="deleteFile('expenseBudgetFileNewPreviewContainer', 'expenseBudgetFile')">
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
								id="expenseBudgetFile" name="expenseBudgetFile"
								onchange="handleFileUpload(event, 'expenseBudgetFile', 'expenseBudgetFileNewPreviewContainer', 'expenseBudgetFileNewPreviewLink', 'expenseBudgetFileNewDeleteButton')">
							<label class="custom-file-label" for="expenseBudgetFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
						<div class="ownerProofid d-none mt-3" id="expenseBudgetFileNewPreviewContainer">
							<a class="expenseBudgetFileProofCls" id="expenseBudgetFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="expenseBudgetFileNewDeleteButton"
								class="dltexpenseBudgetFileBtn close" aria-label="Close"
								onclick="deleteFile('expenseBudgetFileNewPreviewContainer', 'expenseBudgetFile')">
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
						<label><liferay-ui:message
								key="price-list-of-the-equipment" /><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name="priceList"
							id="priceList"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${financeAssistance.certifiedLetter}" />
					</div>
				</div>

				<div class="col-md-6">
				<div class="form-group">
					<label> 
						<liferay-ui:message key="price-list" /> 
						<sup class="text-danger">*</sup> 
						<em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty financeAssistance.priceListFileURL}">
							<div>
								<a href="${financeAssistance.priceListFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${financeAssistance.priceListFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="priceListFile" name="priceListFile"
								onchange="handleFileUpload(event, 'priceListFile', 'priceListFileNewPreviewContainer', 'priceListFileNewPreviewLink', 'priceListFileNewDeleteButton')">
							<label class="custom-file-label" for="priceListFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display (if uploaded before) --%>
						<c:if test="${not empty financeAssistance.priceListFileURL}">
							<div class="ownerProofid d-flex mt-3" id="priceListFilePreviewContainer">
								<a class="priceListFileProofCls" id="priceListFilePreviewLink"
									href="${financeAssistance.priceListFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									${financeAssistance.priceListFileName}
								</a>
								<button type="button" id="priceListFiledeleteButton"
									class="dltpriceListFileBtn close" aria-label="Close"
									onclick="deleteFile('priceListFilePreviewContainer', 'priceListFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3" id="priceListFileNewPreviewContainer">
							<a class="priceListFileProofCls" id="priceListFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="priceListFileNewDeleteButton"
								class="dltpriceListFileBtn close" aria-label="Close"
								onclick="deleteFile('priceListFileNewPreviewContainer', 'priceListFile')">
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
								id="priceListFile" name="priceListFile"
								onchange="handleFileUpload(event, 'priceListFile', 'priceListFileNewPreviewContainer', 'priceListFileNewPreviewLink', 'priceListFileNewDeleteButton')">
							<label class="custom-file-label" for="priceListFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
						<div class="ownerProofid d-none mt-3" id="priceListFileNewPreviewContainer">
							<a class="priceListFileProofCls" id="priceListFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="priceListFileNewDeleteButton"
								class="dltpriceListFileBtn close" aria-label="Close"
								onclick="deleteFile('priceListFileNewPreviewContainer', 'priceListFile')">
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
						<label><liferay-ui:message
								key="estimate-of-the-expenditure" /><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name="expenditureEstimate"
							id="expenditureEstimate"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${financeAssistance.expenditureEstimate}" />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label> 
							<liferay-ui:message key="estimate-expenditure" />
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill"
								title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty financeAssistance.expenditureEstimateFileURL}">
								<div>
									<a href="${financeAssistance.expenditureEstimateFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${financeAssistance.expenditureEstimateFileName}
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="expenditureEstimateFile" name="expenditureEstimateFile"
									onchange="handleFileUpload(event, 'expenditureEstimateFile', 'expenditureEstimateFileNewPreviewContainer', 'expenditureEstimateFileNewPreviewLink', 'expenditureEstimateFileNewDeleteButton')">
								<label class="custom-file-label" for="expenditureEstimateFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty financeAssistance.expenditureEstimateFileURL}">
								<div class="ownerProofid d-flex mt-3" id="expenditureEstimateFilePreviewContainer">
									<a class="expenditureEstimateFileProofCls" id="expenditureEstimateFilePreviewLink"
										href="${financeAssistance.expenditureEstimateFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${financeAssistance.expenditureEstimateFileName}
									</a>
									<button type="button" id="expenditureEstimateFiledeleteButton"
										class="dltexpenditureEstimateFileBtn close" aria-label="Close"
										onclick="deleteFile('expenditureEstimateFilePreviewContainer', 'expenditureEstimateFile')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="expenditureEstimateFileNewPreviewContainer">
								<a class="expenditureEstimateFileProofCls" id="expenditureEstimateFileNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="expenditureEstimateFileNewDeleteButton"
									class="dltexpenditureEstimateFileBtn close" aria-label="Close"
									onclick="deleteFile('expenditureEstimateFileNewPreviewContainer', 'expenditureEstimateFile')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="expenditureEstimateFile" name="expenditureEstimateFile"
									onchange="handleFileUpload(event, 'expenditureEstimateFile', 'expenditureEstimateFileNewPreviewContainer', 'expenditureEstimateFileNewPreviewLink', 'expenditureEstimateFileNewDeleteButton')">
								<label class="custom-file-label" for="expenditureEstimateFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<div class="ownerProofid d-none mt-3" id="expenditureEstimateFileNewPreviewContainer">
								<a class="expenditureEstimateFileProofCls" id="expenditureEstimateFileNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="expenditureEstimateFileNewDeleteButton"
									class="dltexpenditureEstimateFileBtn close" aria-label="Close"
									onclick="deleteFile('expenditureEstimateFileNewPreviewContainer', 'expenditureEstimateFile')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="report-on-the-work-done" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control" name="workReport" id="workReport"
							<c:if test="${mode eq 'view'}">disabled</c:if>
							value="${financeAssistance.certifiedLetter}" />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label> 
							<liferay-ui:message key="report" /> 
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill"
								title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty financeAssistance.workReportFileURL}">
								<div>
									<a href="${financeAssistance.workReportFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${financeAssistance.workReportFileName}
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="workReportFile" name="workReportFile"
									onchange="handleFileUpload(event, 'workReportFile', 'workReportFileNewPreviewContainer', 'workReportFileNewPreviewLink', 'workReportFileNewDeleteButton')">
								<label class="custom-file-label" for="workReportFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty financeAssistance.workReportFileURL}">
								<div class="ownerProofid d-flex mt-3" id="workReportFilePreviewContainer">
									<a class="workReportFileProofCls" id="workReportFilePreviewLink"
										href="${financeAssistance.workReportFileURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${financeAssistance.workReportFileName}
									</a>
									<button type="button" id="workReportFiledeleteButton"
										class="dltworkReportFileBtn close" aria-label="Close"
										onclick="deleteFile('workReportFilePreviewContainer', 'workReportFile')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="workReportFileNewPreviewContainer">
								<a class="workReportFileProofCls" id="workReportFileNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="workReportFileNewDeleteButton"
									class="dltworkReportFileBtn close" aria-label="Close"
									onclick="deleteFile('workReportFileNewPreviewContainer', 'workReportFile')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="workReportFile" name="workReportFile"
									onchange="handleFileUpload(event, 'workReportFile', 'workReportFileNewPreviewContainer', 'workReportFileNewPreviewLink', 'workReportFileNewDeleteButton')">
								<label class="custom-file-label" for="workReportFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<div class="ownerProofid d-none mt-3" id="workReportFileNewPreviewContainer">
								<a class="workReportFileProofCls" id="workReportFileNewPreviewLink"
									href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="workReportFileNewDeleteButton"
									class="dltworkReportFileBtn close" aria-label="Close"
									onclick="deleteFile('workReportFileNewPreviewContainer', 'workReportFile')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
					</div>
				</div>

				<div class="col-md-12">
					<div class="form-group form-check mb-1">
						<input type="checkbox" class="form-check-input"
							id="isDeclarationAccepted" name="isDeclarationAccepted"
							<c:if test="${mode == 'view'}">disabled</c:if>
							<c:if test="${financeAssistance.isDeclarationAccepted}">checked</c:if> />
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