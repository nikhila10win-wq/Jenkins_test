<%@page import="com.mhdsys.grievance.complaint.web.constants.MhdsysGrievanceComplaintWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<portlet:resourceURL id="<%=MhdsysGrievanceComplaintWebPortletKeys.SAVE_HO_APPLICATION_MVC_RESOURCE_COMMAND%>"
var="transferApplicationSubmitURL" />
<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
				<div class="border-0 card shadow">
					<div
						class="card-header d-flex align-items-center justify-content-between">
						<h5>
							<liferay-ui:message key="review-for-publish" />
						</h5>
						<span class="text-right text-primary"><liferay-ui:message
								key="indicates-mandatory-fields" /></span>
					</div>

					<form id="applicationTransferForm" method="POST">
					   <input type="hidden" class="form-control" id=mode name="mode" value="view" />
						<!-- Review Section -->
						<div class="card card-background p-0 mt-4">
							<div class="card-header header-card">
								<liferay-ui:message key="review" />
							</div>
							<div class="card-body">
								<div class="row">
									<!-- HO Section -->
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="solution-by-ho" /> <sup
												class="text-danger">*</sup></label> <input type="text"
												class="form-control" id="solutionByHo" name="solution"
												placeholder="Solution by HO" readonly
												value="${application.solutionByHo != null ? application.solutionByHo : ''}" />
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="review-by-ho" /> <sup
												class="text-danger">*</sup></label> <input type="text"
												class="form-control" id="reviewByHo" name="review" readonly
												placeholder="Review by HO"
												value="${application.reviewByHo != null ? application.reviewByHo : ''}" />
										</div>
									</div>
								</div>

								<!-- DSO Section -->
								<c:if
									test="${not empty application.solutionByDSO or not empty application.reviewByDSO}">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="solution-by-dso" /></label>
												<input type="text" class="form-control" id="solutionByDSO"
													value="${application.solutionByDSO}" readonly />
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="review-by-dso" /></label> <input
													type="text" class="form-control" id="reviewByDSO"
													value="${application.reviewByDSO}" readonly />
											</div>
										</div>
									</div>
								</c:if>

								<!-- TSO Section -->
								<c:if
									test="${not empty application.solutionByTSO or not empty application.reviewByTSO}">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="solution-by-tso" /></label>
												<input type="text" class="form-control" id="solutionByTSO"
													value="${application.solutionByTSO}" readonly />
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="review-by-tso" /></label> <input
													type="text" class="form-control" id="reviewByTSO"
													value="${application.reviewByTSO}" readonly />
											</div>
										</div>
									</div>
								</c:if>



							</div>
						</div>

						<!-- Transfer Details -->
				</div>

				<!-- Footer -->
				<div class="card-footer bg-transparent text-right p-4">
					<!-- <button type="button" class="btn btn-secondary maha-save-btn"
						onclick="location.href='/group/guest/ho-application-list;">
						<liferay-ui:message key="cancel" />
					</button> -->
					<button type="submit" class="btn btn-primary maha-save-btn"
						onclick="createTransferApplication(event)">
						<liferay-ui:message key="publish" />
					</button>
				</div>
				</form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Popup for Transfer of Application Save Status -->
<div class="modal fade" id="saveTransferModal" tabindex="-1" role="dialog" aria-labelledby="transferModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="transferModalTitle">
					<liferay-ui:message key="transfer-status" />
				</h5>
				<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 text-center">
						<div>
							<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3">
							<p id="transfer-save-message" class="text-primary"></p>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer d-flex justify-content-end">
				<a href="/group/guest/grievance-list" type="button" id="closeTransferModal"
					class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveTransferModal')">
					<liferay-ui:message key="close" />
				</a>
			</div>
		</div>
	</div>
</div>
<script>
function createTransferApplication(event) {
    debugger;

    var form = $("#applicationTransferForm")[0]; // Your form ID
    var formData = new FormData(form);

    if (event) {
        event.preventDefault(); // Prevent default form submission
    }

    if ($('#applicationTransferForm').valid()) { // Assuming form uses jQuery Validation
        $.ajax({
            type: "POST",
            url: '${transferApplicationSubmitURL}', // Liferay action URL from JSP
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            success: function (data) {
                console.log("Raw response: ", data);

                if (typeof data === 'string') {
                    try {
                        data = JSON.parse(data);
                    } catch (e) {
                        console.error("JSON parse error: ", e);
                        return;
                    }
                }

                console.log("Parsed response: ", data);

                if (data.createTransfer === true) {
                    $('#transfer-save-message').html("<liferay-ui:message key='application-published-successfully' />");
                } else {
                    $('#transfer-save-message').html("<liferay-ui:message key='application-published-failed' />");
                }

                $('#saveTransferModal').modal('show');
            },
            error: function (xhr, status, error) {
                console.error("AJAX Error: ", error);
            }
        });
    }
}
function closeModal(id) {debugger
    $("#"+id).modal('hide');
 $(".modal-backdrop").remove();
 $("body").removeClass("modal-open");
}
</script>