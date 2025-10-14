<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
<div class="roaster_details">
		<input type="hidden" id="roasterStatusId" name="roasterStatusId" value="${roasterDetails.roasterStatusId}"/>

		<div class="card-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="nomination-codre-wise"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="nominationCodreWisePostWise" id="nominationCodreWisePostWise"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${roasterDetails.nominationCodreWisePostWise}"/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="promotion-codre-wise"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="promotionCodreWisePostWise" id="promotionCodreWisePostWise"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${roasterDetails.promotionCodreWisePostWise}"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

