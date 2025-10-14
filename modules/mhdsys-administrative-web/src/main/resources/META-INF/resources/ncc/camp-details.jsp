<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<portlet:resourceURL id="<%=MhdsysAdministrativeWebPortletKeys.SAVE_CAMP_DETAILS_MVC_RESOURCE_COMMAND%>"
var="saveMonitoringReportURL" />
<div class="common-forms-div">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="card shadow border-0">

                    <!-- Header with Back Button -->
                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
                        <h5 class="mb-0">
                            <liferay-ui:message key="monitoring-reports" />
                        </h5>
                        <div>
                            <a class="btn btn-primary btn-sm rounded-pill back-btn-cn"
                               href="/group/guest/administrative-dashboard"
                               style="background-color: #26268E; border-color: #fff;">
                                <i class="bi bi-arrow-left-circle"></i>
                                <liferay-ui:message key="back" />
                            </a>
                        </div>
                    </div>

                    <!-- Form Starts -->
                    <form id="monitoringReportForm">
                        <div class="card-body">
                            <div class="card card-background p-0 mb-4">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="camp-details" />
                                </div>

                                <div class="card-body">
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <label for="financialYear">
                                                <liferay-ui:message key="financial-year" />
                                                <sup class="text-danger">*</sup>
                                            </label>
                                           <select class="form-control"
														name="financialYear"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<option value="2022-2023"
															${campDetails.financialYear == '2022-2023' ? 'selected' : ''}>2022-2023</option>
														<option value="2023-2024"
															${campDetails.financialYear == '2023-2024' ? 'selected' : ''}>2023-2024</option>
														<option value="2024-2025"
															${campDetails.financialYear == '2024-2025' ? 'selected' : ''}>2024-2025</option>
													</select>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="department">
                                                <liferay-ui:message key="department" />
                                                <sup class="text-danger">*</sup>
                                            </label>
                                            <select class="form-control"
														name="department"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<option value="department1"
															${campDetails.department == 'department1' ? 'selected' : ''}>Department
															1</option>
														<option value="department2"
															${campDetails.department == 'department2' ? 'selected' : ''}>Department
															2</option>
													</select>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <label for="groupName">
                                                <liferay-ui:message key="group-name" />
                                            </label>
                                            <select class="form-control"
														name="groupName"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<option value="group1"
															${campDetails.groupName == 'group1' ? 'selected' : ''}>Group
															1</option>
														<option value="group2"
															${campDetails.groupName == 'group2' ? 'selected' : ''}>Group
															2</option>
													</select>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="unitName">
                                                <liferay-ui:message key="unit-name" />
                                                <sup class="text-danger">*</sup>
                                            </label>
                                            <select class="form-control"
														name="unitType"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<c:forEach var="unitReg" items="${unitReg}">
															<option value="${unitReg.unitType}"
																${unitReg.unitType == campDetails.unitType ? 'selected' : ''}>${unitReg.unitType}</option>
														</c:forEach>
													</select>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <label for="commandingOfficer">
                                                <liferay-ui:message key="commanding-officer" />
                                                <sup class="text-danger">*</sup>
                                            </label>
                                            <select class="form-control"
														name="commandingOfficer"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<option value="officer1"
															${campDetails.commandingOfficer == 'officer1' ? 'selected' : ''}>Officer
															1</option>
														<option value="officer2"
															${campDetails.commandingOfficer == 'officer2' ? 'selected' : ''}>Officer
															2</option>
													</select>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="schemeNumber">
                                                <liferay-ui:message key="scheme-number" />
                                                <sup class="text-danger">*</sup>
                                            </label>
                                            <input type="text" class="form-control" id="schemeNumber" name="schemeNumber"
                                                   placeholder="Numeric" required />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Footer Buttons -->
                            <div class="card-footer bg-transparent text-right p-4">
                                <button type="button" class="btn btn-primary"
                                        onclick="location.href='/group/guest/administrative-dashboard';">
                                    <liferay-ui:message key="cancel" />
                                </button>
                                <button type="reset" class="btn btn-primary">
                                    <liferay-ui:message key="reset" />
                                </button>
                                <button type="submit" class="btn btn-primary">
                                    <liferay-ui:message key="submit" />
                                </button>
                            </div>
                        </div>
                    </form>
                    <!-- Form Ends -->

                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal popup for Monitoring Report Save -->
<div class="modal fade" id="reportSaveModal" tabindex="-1" role="dialog" aria-labelledby="reportModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content modal-bg">
            <div class="modal-header justify-content-center align-items-center">
                <h5 class="modal-title" id="reportModalTitle">
                    <liferay-ui:message key="monitoring-report-submission" />
                </h5>
                <button type="button" class="btn-close d-none" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <div>
                            <%-- Optional success icon/image can go here --%>
                            <p id="successReportSave"></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer d-flex justify-content-end">
                <a href="/group/guest/administrative-dashboard" type="button"
                   class="btn btn-secondary maha-save-btn"
                   data-bs-dismiss="modal" onclick="closeModal('reportSaveModal')">
                    <liferay-ui:message key="close" />
                </a>
            </div>
        </div>
    </div>
</div>

<script>
function saveMonitoringReportForm(status) {
    debugger;
    event.preventDefault();

    // Optional: Use jQuery Validate if needed
    // if ($('#monitoringReportForm').valid()) {
    var form = $('#monitoringReportForm')[0];
    var formData = new FormData(form);
    formData.append("status", status);

    $.ajax({
        url: "${saveMonitoringReportURL}", // Replace with your actual Liferay action URL
        method: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
            debugger;
            var data = typeof response === "string" ? JSON.parse(response) : response;

            if (data.reportSaved) {
                $('#successReportSave').html('<div class="text-success">Monitoring Report saved successfully!</div>');
                $('#reportSaveModal').modal('show');
            } else {
                $('#successReportSave').html('<div class="text-danger">Save failed. Please check inputs and try again.</div>');
                $('#reportSaveModal').modal('show');
            }
        },
        error: function (xhr) {
            console.error("Something went wrong while saving the monitoring report.");
            $('#successReportSave').html('<div class="text-danger">Error occurred while saving. Please try again later.</div>');
            $('#reportSaveModal').modal('show');
        }
    });
    // }
}

</script>