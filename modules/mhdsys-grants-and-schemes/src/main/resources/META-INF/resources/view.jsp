<%@ include file="/init.jsp"%>

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0">
							<liferay-ui:message key="grants-and-schemes" />
						</h5>
						<div>
							<a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"> 
								<i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
							</a>
						</div>
					</div>
					<div class="card-body">
						<div class="card card-background p-0">
							<div class="card-header header-card">
								<liferay-ui:message key="scheme-level" />
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="scheme" /><sup class="text-danger">*</sup></label>
											 <select class="form-control" name="sceme" id="scheme">
												<option value=""><liferay-ui:message key="select" /></option>
												<option value="/group/guest/apply-state-level-grants-and-schemes"> <liferay-ui:message key="state-level" /></option>
												<c:if test="${!isSportsCoach && !isSportsPerson}">
												<option value="/group/guest/district-level"> <liferay-ui:message key="district-level" /></option>
												</c:if>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>

$(document).ready(function() {
    $('#scheme').change(function() {
        var selectedValue = $(this).val();
        if (selectedValue) {
            window.location.href = selectedValue;
        }
    });
});

</script>