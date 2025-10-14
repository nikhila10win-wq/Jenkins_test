<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<!-- DataTables CSS and JS (reused from grievance list setup) -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.7.1/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/vfs_fonts.js"></script>

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
<!-- Grant List UI -->
<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="ncc-grant-list" />
						</h5>
						<div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn"
								href="/group/guest/scout-guide-ncc"
								> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message
									key="back" />
							</a>
						</div>
					</div>
                    <div class="card-body">

                        <!-- Filter -->
                        <div class="filter-container">
                        	<div class="d-flex justify-content-between">
	                            <button class="btn btn-primary m-0" id="grantFilterBtn">
	                                <liferay-ui:message key="filter" />
	                            </button>
	                            <a href="/group/guest/add-grant" class="btn btn-primary m-0" >
	                                <liferay-ui:message key="add" />
	                            </a>
							</div>
                            <div class="card filter-card mt-2" id="grantFilterCard">
                                <div class="card-body p-0">
                                    <div class="card-header">
                                        <h5 class="card-title m-0"><liferay-ui:message key="filter-options" /></h5>
                                    </div>
                                    <form id="grant_filter_form" method="POST">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="financial-year" /></label>
                                                    <input type="text" class="form-control" id="financialYear" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="department" /></label>
                                                    <input type="text" class="form-control" id="department" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="group-name" /></label>
                                                    <input type="text" class="form-control" id="groupName" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="unit-type" /></label>
                                                    <input type="text" class="form-control" id="unitType" />
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="card-footer text-right">
                                        <button type="button" class="btn btn-success" id="grantResetBtn">
                                            <liferay-ui:message key="reset" />
                                        </button>
                                        <button type="submit" class="btn btn-success" id="grantSearchBtn">
                                            <liferay-ui:message key="search" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Table -->
                        <div class="universal-table mt-4">
                        <div class="">
                            <table id="ncc-grant-table" class="table-bordered" width="100%">
                                <thead>
                                    <tr>
                                        <th><liferay-ui:message key="financial-year" /></th>
                                        <th><liferay-ui:message key="department" /></th>
                                        <th><liferay-ui:message key="group-name" /></th>
                                        <th><liferay-ui:message key="unit-type" /></th>
                                        <th><liferay-ui:message key="action" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="grant" items="${nccGrants}">
                                        <portlet:renderURL var="viewGrantURL">
                                            <portlet:param name="mvcRenderCommandName" value="<%=MhdsysAdministrativeWebPortletKeys.NCC_GRANT_MVC_RENDER_COMMAND%>" />
                                            <portlet:param name="nccGrantId" value="${grant.nccGrantId}" />
                                            <portlet:param name="mode" value="view" />
                                        </portlet:renderURL>
                                        <tr>
                                            <td>${grant.financialYear}</td>
                                            <td>${grant.department}</td>
                                            <td>${grant.groupName}</td>
                                            <td>${grant.unitType}</td>
                                            <td>
                                            <div class="tooltip-icon">
						                           <ul class="inline-item">
						                            <li class="list-inline-item">
						                           <a href="${viewGrantURL}" class="btn btn-sm btn-primary">
                                                    <i class="bi bi-eye icons-color" title="<liferay-ui:message key='view' />"></i>
                                                </a>
						                            </li>
						                            </ul>
						                        </div>
                                                
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>
                        <!-- End Table -->

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- DataTable JS -->
<script>
$(document).ready(function () {
    jQuery.noConflict();
    var table = $('#ncc-grant-table').DataTable({
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
        buttons: [
            { extend: 'copy', exportOptions: { columns: ':not(:last-child)' }},
            { extend: 'csv', exportOptions: { columns: ':not(:last-child)' }},
            { extend: 'excel', exportOptions: { columns: ':not(:last-child)' }},
            { extend: 'pdf', exportOptions: { columns: ':not(:last-child)' },
              customize: function (doc) {
                  doc.defaultStyle.fontSize = 8;
                  doc.styles.tableHeader.fontSize = 9;
                  doc.pageMargins = [20, 20, 20, 20];
              }
            },
            { extend: 'print', exportOptions: { columns: ':not(:last-child)' }}
        ],
        initComplete: function () {
            $('.buttons-copy').html('<i class="bi bi-copy" title="Copy"></i>');
            $('.buttons-csv').html('<i class="bi bi-filetype-csv" title="CSV"></i>');
            $('.buttons-excel').html('<i class="bi bi-file-earmark-excel" title="Excel"></i>');
            $('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="PDF"></i>');
            $('.buttons-print').html('<i class="bi bi-printer" title="Print"></i>');
        }
    });

    $('#grantFilterBtn').click(function () {debugger
        const $card = $('#grantFilterCard');
        $card.toggleClass('show');
        $(this).text($card.hasClass('show') ? 'Close' : 'Filter');
        $('#grant_filter_form input').val('');
        table.search('').columns().search('').draw();
    });

    $('#grantResetBtn').click(function () {
        $('#grant_filter_form input').val('');
        table.search('').columns().search('').draw();
    });

    $('#grantSearchBtn').click(function (e) {
        e.preventDefault();
        let financialYear = $('#financialYear').val().trim();
        let department = $('#department').val().trim();
        let groupName = $('#groupName').val().trim();
        let unitType = $('#unitType').val().trim();

        table.columns().search('');
        if (financialYear) table.column(0).search(financialYear);
        if (department) table.column(1).search(department);
        if (groupName) table.column(2).search(groupName);
        if (unitType) table.column(3).search(unitType);

        table.draw();
    });
});
</script>
