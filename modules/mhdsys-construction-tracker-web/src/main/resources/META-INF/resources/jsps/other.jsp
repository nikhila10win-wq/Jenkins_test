<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
	<div class="personal_details">
		<div class="card-header header-card"><liferay-ui:message key="other"/></div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="Received-Amount"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name="otherTotalReceivedAmount" id="otherTotalReceivedAmount" readonly="readonly">
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="Expenditure-Amount"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name="otherTotalExpenditureAmount" id="otherTotalExpenditureAmount" readonly="readonly">
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="Total-Amount"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name="otherTotalBalanceAmount" id="otherTotalBalanceAmount" readonly="readonly">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>

function validate_other() {
	var totalReceivedAmount = $('#totalReceivedAmount').val();
	var totalExpenditureAmount = $('#totalExpenditureAmount').val();
	var totalOverallAmount = $('#totalOverallAmount').val();

	var totalGovtReceivedAmount = $('#totalGovtReceivedAmount').val();
	var totalGovtExpenditureAmount = $('#totalGovtExpenditureAmount').val();
	var totalGovtOverallAmount = $('#totalGovtOverallAmount').val();

	console.log("totalReceivedAmount: "+totalReceivedAmount+ " totalExpenditureAmount: "+totalExpenditureAmount+" totalOverallAmount: "+totalOverallAmount)
	console.log("totalGovtReceivedAmount: "+totalGovtReceivedAmount+ " totalGovtExpenditureAmount: "+totalGovtExpenditureAmount+" totalGovtOverallAmount: "+totalGovtOverallAmount)
	
	$("#otherTotalReceivedAmount").val(Number(totalReceivedAmount) + Number(totalGovtReceivedAmount));
	$("#otherTotalExpenditureAmount").val(Number(totalExpenditureAmount) + Number(totalGovtExpenditureAmount));
	$("#otherTotalBalanceAmount").val(Number(totalOverallAmount) + Number(totalGovtOverallAmount));
	
	return true;
}




</script>