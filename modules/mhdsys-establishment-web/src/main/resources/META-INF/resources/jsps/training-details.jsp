<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
	<div class="training_details">
		<div class="card-header header-card"><liferay-ui:message key="training-details"/></div>
			<input type="hidden" id="trainingDetailsId" name="trainingDetailsId" value="${trainingDetails.trainingDetailsId}"/>
		
		<div class="card-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="no-of-training"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="numberOfTrainings" id="numberOfTrainings"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${trainingDetails.numberOfTrainings}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="training-name"/></label>
						<input type="text" class="form-control" name ="trainingName" id="trainingName"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${trainingDetails.trainingName}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="training-start-date"/></label>
						<input type="date" class="form-control" name ="trainingStartDate" id="trainingStartDate"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${trainingDetails.trainingStartDateStr}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="training-end-date"/></label>
						<input type="date" class="form-control" name ="trainingEndDate" id="trainingEndDate"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${trainingDetails.trainingEndDateStr}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label>
							<liferay-ui:message key="training-certificate" />
							<em class="bi bi-info-circle-fill"
								title="<liferay-ui:message key='allowed-only-pdf-file' />"></em>
						</label>
				
						<!-- Unified File Input -->
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="trainingCertificate" name="trainingCertificate"
								onchange="handleFileUpload(event, 'trainingCertificate', 'trainingCertificatePreviewContainer', 'trainingCertificatePreviewLink', 'trainingCertificateDeleteButton')">
							<label class="custom-file-label" for="trainingCertificate">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
				
						<!-- Unified File Preview Container -->
						<div class="ownerProofid mt-3 ${empty trainingDetails.trainingCertificateURL ? 'd-none' : 'd-flex'}"
							id="trainingCertificatePreviewContainer">
							<a class="trainingCertificateProofCls text-truncate"
								id="trainingCertificatePreviewLink"
								href="${empty trainingDetails.trainingCertificateURL ? '' : trainingDetails.trainingCertificateURL}"
								target="_blank"
								style="flex-grow: 1; text-decoration: none;">
								${empty trainingDetails.trainingCertificateName ? '' : trainingDetails.trainingCertificateName}
							</a>
							<button type="button" id="trainingCertificateDeleteButton"
								class="dlttrainingCertificateBtn close" aria-label="Close"
								onclick="deleteFile('trainingCertificatePreviewContainer', 'trainingCertificate')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
