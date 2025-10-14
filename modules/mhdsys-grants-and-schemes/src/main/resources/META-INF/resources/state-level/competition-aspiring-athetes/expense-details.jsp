<%@ include file="/init.jsp" %>
<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="expense-details" />
		</div>

		<div class="card-body">

			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="travel-cost" />
						 </label> <input type="text"
							class="form-control" name="travelCost" id="travelCost" 
							value="${personalDetails.travelCost}" />
					</div>
				</div>


				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="entry-fees" /></label> <input type="text"
							class="form-control" name="entryFees" id="entryFees"
							<c:if test="${mode eq 'view'}">readonly</c:if>
							value="${personalDetails.emailId}" />

					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="hotel-fees" /></label>
						<input type="text" class="form-control" name="hotelFees" id="hotelFees"
							<c:if test="${mode eq 'view'}">readonly</c:if>
							value="${financialDetails.hotelFees}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="meal-cost" /></label>
						<input type="text" class="form-control" name="mealCost" id="mealCost"
							<c:if test="${mode eq 'view'}">readonly</c:if>
							value="${financialDetails.mealCost}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="pre-competition-training-fees" /></label>
						<input type="text" class="form-control" name="preCompetitionTrainingFees" id="preCompetitionTrainingFees"
							<c:if test="${mode eq 'view'}">readonly</c:if>
							value="${financialDetails.preCompetitionTrainingFees}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="training-equipment-cost" /></label>
						<input type="text" class="form-control" name="trainingEquipmentCost" id="trainingEquipmentCost"
							<c:if test="${mode eq 'view'}">readonly</c:if>
							value="${financialDetails.trainingEquipmentCost}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="guidance-fee-from-expert" /></label>
						<input type="text" class="form-control" name="guidanceFeeFromExpert" id="guidanceFeeFromExpert"
							<c:if test="${mode eq 'view'}">readonly</c:if>
							value="${financialDetails.guidanceFeeFromExpert}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="training-fee" /></label>
						<input type="text" class="form-control" name="trainingFee" id="trainingFee"
							<c:if test="${mode eq 'view'}">readonly</c:if>
							value="${financialDetails.trainingFee}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="cost-of-import-purchase-sport-equipment" /></label>
						<input type="text" class="form-control" name="costOfImportSportEquipment" id="costOfImportSportEquipment"
							<c:if test="${mode eq 'view'}">readonly</c:if>
							value="${financialDetails.costOfImportSportEquipment}" />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="total-cost" /></label>
						<input type="text" class="form-control" name="totalCost" id="totalCost"
							<c:if test="${mode eq 'view'}">readonly</c:if>
							value="${financialDetails.totalCost}" />
					</div>
				</div>
				

			</div>
		</div>
	</div>
</div>