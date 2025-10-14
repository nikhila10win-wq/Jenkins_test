<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
	<div class="nps_details">
		<div class="card-header header-card"><liferay-ui:message key="nps-details"/></div>
		<input type="hidden" id="nPSDetailsId" name="nPSDetailsId" value="${npsDetails.nPSDetailsId}"/>
		<div class="card-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="nps-id"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="npsId" id="npsId"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${npsDetails.npsId}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="refundable-amount"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="npsRefundableAmount" id="npsRefundableAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${npsDetails.npsRefundableAmount}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="refundable-amount-date"/><sup class="text-danger">*</sup></label>
						<input type="date" class="form-control" name ="npsRefundableAmountDate" id="npsRefundableAmountDate"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${npsDetails.npsRefundableAmountDateStr}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="non-refundable-amount"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="npsNonRefundableAmount" id="npsNonRefundableAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${npsDetails.npsNonRefundableAmount}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="non-refundable-amount-date"/><sup class="text-danger">*</sup></label>
						<input type="date" class="form-control" name ="npsNonRefundableAmountDate" id="npsNonRefundableAmountDate"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${npsDetails.npsNonRefundableAmountDateStr}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="monthly-deduction-amount"/></label>
						<input type="text" class="form-control" name ="npsMonthlyDeductionAmount" id="npsMonthlyDeductionAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${npsDetails.npsMonthlyDeductionAmount}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="monthly-installment-amount"/></label>
						<input type="text" class="form-control" name ="npsMonthlyInstallmentAmount" id="npsMonthlyInstallmentAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${npsDetails.npsMonthlyInstallmentAmount}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="no-of-monthly-installment"/></label>
						<input type="text" class="form-control" name ="npsNumberOfMonthlyInstallment" id="npsNumberOfMonthlyInstallment"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${npsDetails.npsNumberOfMonthlyInstallment}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="balance-amount"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="npsBalanceAmount" id="npsBalanceAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${npsDetails.npsBalanceAmount}"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>