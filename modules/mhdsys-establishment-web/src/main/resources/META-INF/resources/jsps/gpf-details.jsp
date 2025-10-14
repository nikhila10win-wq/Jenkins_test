<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
	<div class="gpf_details">
		<div class="card-header header-card"><liferay-ui:message key="gpf-details"/></div>
		<input type="hidden" id="gPFDetailsId" name="gPFDetailsId" value="${gpfDetails.gPFDetailsId}"/>
 		<div class="card-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="gpf-no"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="gpfNumber" id="gpfNumber"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${gpfDetails.gpfNumber}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="refundable-amount"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="refundableAmount" id="refundableAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${gpfDetails.refundableAmount}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="refundable-amount-date"/><sup class="text-danger">*</sup></label>
						<input type="date" class="form-control" name ="refundableAmountDate" id="refundableAmountDate"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${gpfDetails.refundableAmountDateStr}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="non-refundable-amount"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="nonRefundableAmount" id="nonRefundableAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${gpfDetails.nonRefundableAmount}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="non-refundable-amount-date"/><sup class="text-danger">*</sup></label>
						<input type="date" class="form-control" name ="nonRefundableAmountDate" id="nonRefundableAmountDate"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${gpfDetails.nonRefundableAmountDateStr}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="monthly-deduction-amount"/></label>
						<input type="text" class="form-control" name ="monthlyDeductionAmount" id="monthlyDeductionAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${gpfDetails.monthlyDeductionAmount}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="monthly-installment-amount"/></label>
						<input type="text" class="form-control" name ="monthlyInstallmentAmount" id="monthlyInstallmentAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${gpfDetails.monthlyInstallmentAmount}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="no-of-monthly-installment"/></label>
						<input type="text" class="form-control" name ="numberOfMonthlyInstallment" id="numberOfMonthlyInstallment"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${gpfDetails.numberOfMonthlyInstallment}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="balance-amount"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="balanceAmount" id="balanceAmount"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${gpfDetails.balanceAmount}"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
