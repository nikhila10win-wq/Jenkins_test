<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
<div class="posting_details">
		<input type="hidden" id="postingStatusId" name="postingStatusId" value="${postingStatus.postingStatusId}"/>

		<div class="card-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="posting-status"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name ="postingStatus" id="postingStatus"
						<c:if test="${mode eq 'view'}">disabled</c:if> value="${postingStatus.postingStatus}"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
