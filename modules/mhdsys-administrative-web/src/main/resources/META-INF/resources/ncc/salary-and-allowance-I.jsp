<%@ include file="/init.jsp"%>
<div class="tab-pane fade" id="salaryTab1" role="tabpanel"
	aria-labelledby="salaryTab1-tab">
	<div class="card card-background p-0">
		<div class="card-header header-card">
			<liferay-ui:message key="salary-and-allowance-i" />
		</div>
		<div class="card-body">
			<div class="row">
				<!-- Contractor Allowance -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="caretaker-allowance" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="caretakerAllowance"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value=""><liferay-ui:message key="select" /></option>
						<option value="allowance-1"
							<c:if test="${nccGrant.caretakerAllowance == 'allowance-1'}">selected</c:if>>
							<liferay-ui:message key="allowance-1" />
						</option>
						<option value="allowance-2"
							<c:if test="${nccGrant.caretakerAllowance == 'allowance-2'}">selected</c:if>>
							<liferay-ui:message key="allowance-2" />
						</option>
					</select>
				</div>

				<!-- Refreshment Allowance -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="refreshment-allowance" /><sup
						class="text-danger">*</sup></label> <select class="form-control"
						name="refreshmentAllowance"
						<c:if test="${mode eq 'view'}">disabled</c:if>>
						<option value=""><liferay-ui:message key="select" /></option>
						<option value="allowance-1"
							<c:if test="${nccGrant.refreshmentAllowance == 'allowance-1'}">selected</c:if>>
							<liferay-ui:message key="allowance-1" />
						</option>
						<option value="allowance-2"
							<c:if test="${nccGrant.refreshmentAllowance == 'allowance-2'}">selected</c:if>>
							<liferay-ui:message key="allowance-2" />
						</option>
					</select>
				</div>

				<!-- Salaries -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="salaries" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="salaries"
						value="${nccGrant.salaries}"
						<c:if test="${mode eq 'view'}">readonly</c:if> />
				</div>

				<!-- Overtime Allowance -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="overtime-allowance" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="overTimeAllowance"
						value="${nccGrant.overTimeAllowance}"
						<c:if test="${mode eq 'view'}">readonly</c:if> />
				</div>

				<!-- Telephone -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="telephone" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="telephone"
						value="${nccGrant.telephone}"
						<c:if test="${mode eq 'view'}">readonly</c:if> />
				</div>

				<!-- Electricity -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="electricity" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="electricity"
						value="${nccGrant.electricity}"
						<c:if test="${mode eq 'view'}">readonly</c:if> />
				</div>

				<!-- Water Charges -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="water-charges" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="waterCharges"
						value="${nccGrant.waterCharges}"
						<c:if test="${mode eq 'view'}">readonly</c:if> />
				</div>

				<!-- Contractual Services -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="contractual-services" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="contractualServices"
						value="${nccGrant.contractualServices}"
						<c:if test="${mode eq 'view'}">readonly</c:if> />
				</div>

				<!-- Domestic Travelling Expenses -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message
							key="domestic-travelling-expenses" /><sup class="text-danger">*</sup></label>
					<input type="text" class="form-control grant-amount"
						name="domesticTravellingExpenses"
						value="${nccGrant.domesticTravellingExpenses}"
						<c:if test="${mode eq 'view'}">readonly</c:if> />
				</div>

				<!-- Office Expenses -->
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="office-expenses" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control grant-amount" name="officeExpenses"
						value="${nccGrant.officeExpenses}"
						<c:if test="${mode eq 'view'}">readonly</c:if> />
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
			<button type="button" class="btn btn-primary"
				onclick="saveGrantRequestForm('Draft')">
				<liferay-ui:message key="save-as-draft" />
			</button>
			<button type="button" class="btn btn-primary" onclick="setActiveTab('unitDetails')">
             <liferay-ui:message key="previous" />
       		 </button>
			<button type="button" class="btn btn-primary" onclick="validateTabFields('salaryTab2')">
                <liferay-ui:message key="next" />
            </button>
		</c:if>
	</div>
</div>