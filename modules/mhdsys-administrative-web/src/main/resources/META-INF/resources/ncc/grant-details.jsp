<!-- Grants Details -->
<div class="tab-pane fade" id="grantsDetails" role="tabpanel"
	aria-labelledby="grantsDetails-tab">
	<div class="card card-background p-0">
		<div class="card-header header-card">
			<liferay-ui:message key="grants-details" />
		</div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="grants-received" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="grantsReceived"
						value="${nccGrant.grantsReceived}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="grant-surrender-reapp" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="grantSurrenderReapp"
						value="${nccGrant.grantSurrenderReapp}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="grants-withdrawn" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="grantsWithdrawn"
						value="${nccGrant.grantsWithdrawn}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="grants-withdrawn-by" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="grantsWithdrawnBy"
						value="${nccGrant.grantsWithdrawnBy}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="grants-surrender" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="grantsSurrender"
						value="${nccGrant.grantsSurrender}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="grants-surrender-to" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="grantsSurrenderTo"
						value="${nccGrant.grantsSurrenderTo}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="grants-allowed" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="grantsAllowed"
						value="${nccGrant.grantsAllowed}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="grantRecievedReapp" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="grantRecievedReapp"
						value="${nccGrant.grantRecievedReapp}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="balance" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="balance" value="${nccGrant.balance}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="expenses" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="expenses" value="${nccGrant.expenses}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="actual-expense" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="actualExpense"
						value="${nccGrant.actualExpense}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
				</div>

				<div class="col-md-6 form-group">
					<label><liferay-ui:message key="balance-with-ddo" /><sup
						class="text-danger">*</sup></label> <input type="text"
						class="form-control" name="balanceWithDdo"
						value="${nccGrant.balanceWithDdo}"
						<c:if test="${mode eq 'view'}">disabled</c:if> />
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
			onclick="saveGrantForm('Draft')">
			<liferay-ui:message key="save-as-draft" />
		</button>
		<button type="button" class="btn btn-primary" onclick="setActiveTab('unitDetails')">
             <liferay-ui:message key="previous" />
        </button>
         
        <button type="button" class="btn btn-primary" onclick="validateTabFields('remarkDetails')">
             <liferay-ui:message key="next" />
         </button>
		</c:if>
	</div>

</div>