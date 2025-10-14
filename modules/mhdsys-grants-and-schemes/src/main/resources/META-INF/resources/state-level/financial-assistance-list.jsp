<%@page import="com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys"%>
<%@ include file="/init.jsp" %>

<style>
    .filter-card {
        display: none;
        opacity: 0;
        transition: all 0.3s ease-in-out;
    }
    .filter-card.show {
        display: block;
        opacity: 1;
    }
</style>

<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
                        <h5 class="mb-0"><liferay-ui:message key="application-list" /></h5>
                        <!-- <div>
                            <a href="/group/guest/state-level" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
                                <i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
                            </a>
                        </div> -->
                    </div>

                    <div class="card-body">
                        <!-- Filter -->
                        <div class="filter-container">
                            <button class="btn btn-primary m-0" id="filterBtn">
                                <liferay-ui:message key="filter" />
                            </button>

                            <div class="filter-card mt-4" id="filterCard">
                                <div class="card card-background p-0">
                                    <div class="card-header header-card">
                                        <liferay-ui:message key="filter-options" />
                                    </div>

                                    <form id="financial-assistance-filter" method="POST">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label><liferay-ui:message key="full-name" /></label>
                                                        <input type="text" class="form-control" id="fullName" name="fullName" />
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label><liferay-ui:message key="email-id" /></label>
                                                        <input type="text" class="form-control" id="emailId" name="emailId" />
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label><liferay-ui:message key="mobile-number" /></label>
                                                        <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" />
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label><liferay-ui:message key="address" /></label>
                                                        <input type="text" class="form-control" id="address" name="address" />
                                                    </div>
                                                </div>

                                                <div class="col-md-3 mt-3">
                                                    <div class="form-group">
                                                        <label><liferay-ui:message key="sports-performance-detail" /></label>
                                                        <input type="text" class="form-control" id="sportsPerformance" name="sportsPerformance" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>

                                    <div class="card-footer text-right bg-white">
                                        <button type="button" class="btn btn-success" id="resetBtn">
                                            <liferay-ui:message key="reset" />
                                        </button>
                                        <button type="button" class="btn btn-success" id="searchBtn">
                                            <liferay-ui:message key="search" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End Filter -->

                        <div class="universal-table mt-4">
                            <table id="financial-assistance-table" class="table-bordered" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th><liferay-ui:message key="full-name" /></th>
                                        <th><liferay-ui:message key="email-id" /></th>
                                        <th><liferay-ui:message key="mobile-number" /></th>
                                        <th><liferay-ui:message key="address" /></th>
                                        <th><liferay-ui:message key="sports-performance-detail" /></th>
                                        <th><liferay-ui:message key="action" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="financeAssistance" items="${financeAssistanceList}">
                                        <portlet:renderURL var="financeAssistanceViewURL">
                                            <portlet:param name="mvcRenderCommandName" value="<%=MhdsysGrantsAndSchemesPortletKeys.STATE_LEVEL_GRANTS_AND_SCHEMES_RENDER%>" />
                                            <portlet:param name="financialAssistanceId" value="${financeAssistance.financialAssistanceId}" />
                                            <portlet:param name="mode" value="view" />
                                            <portlet:param name="application" value="financeAssistance" />
                                        </portlet:renderURL>
                                        <tr>
                                            <td>${financeAssistance.fullName}</td>
                                            <td>${financeAssistance.emailId}</td>
                                            <td>${financeAssistance.mobileNumber}</td>
                                            <td>${financeAssistance.address}</td>
                                            <td>${financeAssistance.sportsPerformanceDetails}</td>
                                            <td>
                                                <div class="tooltip-icon">
                                                    <ul class="inline-item">
                                                        <c:if test="${!isDeskOfficer && !isDeputyDirector}">
                                                            <li class="list-inline-item">
                                                                <a class="btn btn-sm btn-primary" href="${financeAssistanceViewURL}">
                                                                    <i class="bi bi-eye icons-color" title="<liferay-ui:message key='view' />"></i>
                                                                </a>
                                                            </li>
                                                        </c:if>
                                                        <c:if test="${isDeskOfficer || isDeputyDirector}">
                                                            <li class="list-inline-item">
                                                                <a class="btn btn-sm btn-primary" href="${financeAssistanceViewURL}">
                                                                    <i class="bi bi-check-circle icons-color" title="<liferay-ui:message key='verify' />"></i>
                                                                </a>
                                                            </li>
                                                        </c:if>
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css">
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>

<script>
$(document).ready(function () {
    jQuery.noConflict();

    var table = $('#financial-assistance-table').DataTable({
        paging: true,
        ordering: false,
        searching: true,
        responsive: true,
        autoWidth: false,
        language: {
            search: '<liferay-ui:message key="search" />',
            emptyTable: '<liferay-ui:message key="no-data-available-in-table" />',
            infoFiltered: "",
            lengthMenu: '_MENU_',
            info: "<liferay-ui:message key='Showing-total-entries' />",
            zeroRecords: "<liferay-ui:message key='No-matching-records-found' />",
            infoEmpty: "<liferay-ui:message key='Showing-total-entries' />",
            oPaginate: {
                sNext: '<em class="bi bi-chevron-right"></em>',
                sPrevious: '<em class="bi bi-chevron-left"></em>',
            }
        },
        dom: '<"top"Bf>rt<"bottom"lip><"clear">',
        buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
        initComplete: function () {
            $('.buttons-copy').html('<i class="bi bi-copy" title="<liferay-ui:message key="copy" />"></i>');
            $('.buttons-csv').html('<i class="bi bi-filetype-csv" title="<liferay-ui:message key="csv" />"></i>');
            $('.buttons-excel').html('<i class="bi bi-file-earmark-excel" title="<liferay-ui:message key="excel" />"></i>');
            $('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
            $('.buttons-print').html('<i class="bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
        }
    });

    $('#filterBtn').click(function () {
        $('#filterCard').toggleClass('show');
    });

    $('#resetBtn').click(function () {
        $('#financial-assistance-filter')[0].reset();
        table.columns().search('').draw();
    });

    $('#searchBtn').click(function () {
        table.column(0).search($('#fullName').val().trim());
        table.column(1).search($('#emailId').val().trim());
        table.column(2).search($('#mobileNumber').val().trim());
        table.column(3).search($('#address').val().trim());
        table.column(4).search($('#sportsPerformance').val().trim());
        table.draw();
    });
});
</script>
