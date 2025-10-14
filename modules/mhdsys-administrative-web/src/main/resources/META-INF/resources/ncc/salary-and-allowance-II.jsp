<%@ include file="/init.jsp"%>
<div class="tab-pane fade" id="salaryTab2" role="tabpanel"
	aria-labelledby="salaryTab2-tab">
	<div class="card card-background p-0">
		<div class="card-header header-card">
			<liferay-ui:message key="salary-and-allowance-ii" />
		</div>
		<div class="card-body">
			<div class="row">
				<!-- Rent -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="rent" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="rent"
						value="${nccGrant.rent}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Rates -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="rates" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="rates"
						value="${nccGrant.rates}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Taxes -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="taxes" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="taxes"
						value="${nccGrant.taxes}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Training Activities -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="training-activities" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="trainingActivities"
						value="${nccGrant.trainingActivities}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- ATG -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="atg" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="atg"
						value="${nccGrant.atg}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Washing and Polishing Allowance -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message
							key="washing-polishing-allowance" /><sup class="text-danger">*</sup></label>
					<input type="text" class="form-control grant-amount"
						name="washingAndPolishingAllowance"
						value="${nccGrant.washingAndPolishingAllowance}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Petrol Oil Lubricants -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="petrol-oil-lubricants" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="petrolOilLubricants"
						value="${nccGrant.petrolOilLubricants}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Minor Work -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="minor-work" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="minorWork"
						value="${nccGrant.minorWork}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Honorarium To ANO -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="honorarium-anos-to" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="honorariumToAno"
						value="${nccGrant.honorariumToAno}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Outfit Maintenance Allowance -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message
							key="outfit-maintenance-allowance" /><sup class="text-danger">*</sup></label>
					<input type="text" class="form-control grant-amount"
						name="outfitMaintenanceAllowance"
						value="${nccGrant.outfitMaintenanceAllowance}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Others if any -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="others-if-any" /></label> <input type="text"
						class="form-control grant-amount" name="othersIfAny"
						value="${nccGrant.othersIfAny}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Grants in Aid -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="grants-in-aid-non-salary" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="grantsInAid"
						value="${nccGrant.grantsInAid}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Scholarship or Stipend -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="scholarship-stipend" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="scholarshipOrStipend"
						value="${nccGrant.scholarshipOrStipend}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<!-- Total (Readonly) -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="total" /></label> <input
						type="text" class="form-control bg-light" name="totalexp"
						value="${nccGrant.total}" disabled />
						<input
						type="hidden" class="form-control bg-light" name="total"
						value="${nccGrant.total}"  />
				</div>
				<c:if test="${ mode eq 'view'}"> 
					<div class="col-md-6 form-group">
						<label><liferay-ui:message key="actual-total" /></label> <input
							type="text" class="form-control bg-light" name="actualTotal"
							value="${nccGrant.actualTotal}" 
							<c:if test="${not empty nccGrant.actualTotal}">disabled</c:if>  />
					</div>
				</c:if>
			</div>


		</div>
	</div>
	<c:if test="${isDeskOfficer && mode eq 'view'}"> 
		<div class="card card-background p-0">
			<div class="card-header header-card">
				<liferay-ui:message key="salary-and-allowance-ii" />
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col-md-6 form-group">
						<label class="form-label d-block"><liferay-ui:message
								key="approve-reject" /><sup class="text-danger">*</sup></label>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="approvalStatus"
								id="approve" value="Approved"
								${nccGrant.approvalStatus == 'Approved' ? 'checked' : ''}
								<c:if test="${mode eq 'view'}"></c:if> /> <label
								class="form-check-label fw-bold" for="approve"><liferay-ui:message
									key="approve" /></label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="approvalStatus"
								id="reject" value="Rejected"
								${nccGrant.approvalStatus == 'Rejected' ? 'checked' : ''}
								<c:if test="${mode eq 'view'}"></c:if> /> <label
								class="form-check-label fw-bold" for="reject"><liferay-ui:message
									key="reject" /></label>
						</div>
					</div>
					<div class="col-md-6 form-group">
					<label for="remarks"><liferay-ui:message key="remarks" /><sup class="text-danger">*</sup></label>
					<textarea name="remarks" id="remarks" rows="3" class="form-control"
						placeholder="Remarks" <c:if test="${mode eq 'view'}"></c:if>>${nccGrant.remarks}</textarea>
				</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="card-footer bg-transparent text-right p-4">
		<button type="button" class="btn btn-primary"
			onclick="location.href='/group/guest/scout-guide-ncc';">
			<liferay-ui:message key="cancel" />
		</button>
		<c:if test="${mode ne 'view' }">
			<button class="btn btn-primary" type="button"
				onclick="location.reload();">
				<liferay-ui:message key="reset" />
			</button>
			<button type="button" class="btn btn-primary" onclick="setActiveTab('salaryTab1')">
             <liferay-ui:message key="previous" />
       		 </button>
		</c:if>
		<c:if test="${mode ne 'view' || (isDeskOfficer && mode eq 'view') || (empty nccGrant.actualTotal && mode eq 'view')}">
			<button type="button" class="btn btn-primary"
				onclick="saveGrantRequestForm('Save')">
				<liferay-ui:message key="submit" />
			</button>
		</c:if>
	</div>
</div>