
<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="financial-details" />
		</div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-6">
				    <div class="form-group">
				        <c:if test="${mode eq 'add' or mode eq 'edit'}">
				            <label>
				                <liferay-ui:message key="item-budgets" />
				                <sup class="text-danger">*</sup>
				                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-file-of-size-2mb' />"></em>
				            </label>
				        </c:if>
				        <c:if test="${mode eq 'view'}">
				            <label>
				                <liferay-ui:message key="item-budgets" />
				                <sup class="text-danger">*</sup>
				            </label>
				        </c:if>
				
				        <c:if test="${mode eq 'add' or mode eq 'edit'}">
				            <div class="custom-file">
				                <input type="file" class="custom-file-input itemBudgets" id="itemBudgets" name="itemBudgets" multiple
				                    onchange="handleMultipleFileUpload(this, 'itemBudgets', 'itemBudgetsPreviewContainer', 'itemBudgetsPreviewList', 'itemBudgetsError', 'itemBudget')">
				                <label class="custom-file-label" for="itemBudgets">
				                    <liferay-ui:message key="choose-file" />
				                </label>
				            </div>
				
				            <!-- Error message -->
				            <span id="itemBudgetsError"></span>
				
				            <!-- Hidden input to store file details -->
				            <input type="hidden" id="itemBudget" name="itemBudget" 
				                value='<c:if test="${mode eq 'edit' and not empty intSportsComp.itemBudgetsNames}"><c:forEach var="photoName" items="${intSportsComp.itemBudgetsNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
				
				            <!-- Preview and Delete Section -->
				            <div class="mt-3" id="itemBudgetsPreviewContainer" 
				                style='<c:if test="${mode ne 'edit' or empty intSportsComp.itemBudgetsURLs}">display: none;</c:if>'>
				                <ul id="itemBudgetsPreviewList" name="itemBudgetsPreviewList" class="list-group">
				                    <c:if test="${mode eq 'edit' and not empty intSportsComp.itemBudgetsURLs}">
				                        <c:forEach var="photoURL" items="${intSportsComp.itemBudgetsURLs}" varStatus="status">
				                            <li class="list-group-item d-flex justify-content-between align-items-center">
				                                <a href="${photoURL}" target="_blank" class="text-truncate">
				                                    ${intSportsComp.itemBudgetsNames[status.index]}
				                                </a>
				                                <button type="button" class="btn btn-danger btn-sm" onclick="removeFile(${status.index}, 'itemBudgetsPreviewContainer', 'itemBudgetsPreviewList', 'itemBudgetsError', 'itemBudget', 'itemBudgets')">
				                                    <i class="bi bi-x-circle-fill"></i>
				                                </button>
				                            </li>
				                        </c:forEach>
				                    </c:if>
				                </ul>
				            </div>
				        </c:if>
				
				        <c:if test="${mode eq 'view' and not empty intSportsComp.itemBudgetsURLs}">
				            <div>
				                <c:forEach var="photoURL" items="${intSportsComp.itemBudgetsURLs}" varStatus="status">
				                    <a href="${photoURL}" target="_blank" class="text-truncate">
				                        ${intSportsComp.itemBudgetsNames[status.index]}
				                    </a><br>
				                </c:forEach>
				            </div>
				        </c:if>
				    </div>
				</div>
								

				<div class="col-md-12">
					<div class="form-group form-check mb-1">
						<input type="checkbox" class="form-check-input" id="isDeclarationAccepted" name="isDeclarationAccepted"
							<c:if test="${mode == 'view'}">disabled</c:if>
							<c:if test="${intSportsComp.isDeclarationAccepted}">checked</c:if> />
						<label class="form-check-label" for="isDeclarationAccepted">
							<liferay-ui:message key="declaration-authorized-organization" /><sup class="text-danger">*</sup>
						</label>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
