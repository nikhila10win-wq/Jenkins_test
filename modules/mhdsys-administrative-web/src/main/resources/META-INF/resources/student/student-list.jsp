<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<!-- DataTables JS -->
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

    #construction-tracker-list_wrapper .top {
        display: flex;
        justify-content: space-between;
    }

    #aconstruction-tracker-list_wrapper .bottom {
        display: flex;
        justify-content: space-between;
        align-items: baseline;
    }

   
</style>
<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                     <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="student-list" />
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
	                            <button class="btn btn-primary m-0" id="filterBtn">
	                                <liferay-ui:message key="filter" />
	                            </button>
	                            <c:if test="${isPtTeacher}">
									<a class="btn btn-primary m-0" 
										href="/group/guest/student-registration"
										> <liferay-ui:message
											key="add" />
									</a>
								</c:if>
							</div>
                            <div class="filter-card mt-2" id="filterCard">
                                <div class="card card-background p-0">
                                    <div class="card-header header-card">
                                       
                                            <liferay-ui:message key="filter-options" />
                                        
                                    </div>
                                    <form id="student_filter_form" method="POST">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="first-name" /></label>
                                                    <input type="text" class="form-control" id="firstName" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="last-name" /></label>
                                                    <input type="text" class="form-control" id="lastName" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="gender" /></label>
                                                    <input type="text" class="form-control" id="gender" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="standard" /></label>
                                                    <input type="text" class="form-control" id="standard" />
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="card-footer text-right bg-white">
                                        <button type="button" class="btn btn-success" id="resetBtn">
                                            <liferay-ui:message key="reset" />
                                        </button>
                                        <button type="submit" class="btn btn-success" id="searchBtn">
                                            <liferay-ui:message key="search" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Filter End -->

                        <!-- Table -->
                        <div class="universal-table mt-4">
                        	<div class="">
                            <table id="student-list" class="table-bordered" width="100%">
                                <thead>
                                    <tr>
                                        <th><liferay-ui:message key="first-name" /></th>
                                        <th><liferay-ui:message key="last-name" /></th>
                                        <th><liferay-ui:message key="gender" /></th>
                                        <th><liferay-ui:message key="standard" /></th>
                                        <th><liferay-ui:message key="school-college-name" /></th>
                                        <th><liferay-ui:message key="action" /></th>
                                     </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="student" items="${studentList}">
                                         <portlet:renderURL var="registerStudentURL">
                                            <portlet:param name="mvcRenderCommandName"
                                                value="<%=MhdsysAdministrativeWebPortletKeys.STUDENT_DETAILS_MVC_RENDER_COMMAND%>" />
                                            <portlet:param name="studentRegistrationId"
                                                value="${student.studentRegistrationId}" />
                                            <portlet:param name="mode"
                                                value="view" />
                                        </portlet:renderURL> 
										<portlet:resourceURL id="<%=MhdsysAdministrativeWebPortletKeys.GENERATE_ID_CARD_MVC_RESOURCE_COMMAND %>" var="generateIdCardURL" />
										
                                        <tr>
                                            <td>${student.firstName}</td>
                                            <td>${student.lastName}</td>
                                            <td>${student.gender}</td>
                                            <td>${student.standard}</td>
                                            <td>${student.schoolCollegeName}</td>
                                             <td>
                                             <div class="tooltip-icon">
						                           <ul class="inline-item">
                                            <c:if
											    test="${(empty student.registerTo and not empty student.unitType and empty student.transferTo)
											        or (empty student.registerTo and student.transferTo eq 'Guide' and guideMaster)
											        or (empty student.registerTo and student.transferTo eq 'Scout' and scoutMaster)
											        or (empty student.registerTo and student.transferTo eq 'NCC' and commandingOfficers)}">
											<c:if test='${commandingOfficers or guideMaster or scoutMaster}'>
											    <li class="list-inline-item"><a href="${registerStudentURL}" class="btn btn-sm btn-primary">
													<i class="bi bi-card-checklist icons-color"
													   title="<c:if test='${guideMaster}'><liferay-ui:message key='register-to-guide-master' /></c:if>
													          <c:if test='${scoutMaster}'><liferay-ui:message key='register-to-scout-master' /></c:if>
													          <c:if test='${commandingOfficers}'><liferay-ui:message key='register-to-commanding-officer' /></c:if>">
													</i>
											    </a>
											    </li>
											 </c:if>
											</c:if>

                                            <c:if
											    test="${not ((empty student.registerTo and not empty student.unitType and empty student.transferTo)
											        or (empty student.registerTo and student.transferTo eq 'Guide' and guideMaster)
											        or (empty student.registerTo and student.transferTo eq 'Scout' and scoutMaster)
											        or (empty student.registerTo and student.transferTo eq 'NCC' and commandingOfficers))
											        or(isPtTeacher or isHOAdmin)}">
											    
											    <li class="list-inline-item"><a href="${registerStudentURL}" class="btn btn-sm btn-primary">
											        <i class="bi bi-eye icons-color" title="<liferay-ui:message key='view' />"></i>
											    </a>
											    </li>
											</c:if>
												<li class="list-inline-item">
												    <a onclick="generateIdCard(${student.studentRegistrationId})" class="btn btn-sm btn-secondary" target="_blank">
												        <i class="bi bi-printer icons-color" title="<liferay-ui:message key="generate-id-card" />"></i>
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
                        <!-- Table End -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
$(document).ready(function () {
    jQuery.noConflict();
    var table = $('#student-list').DataTable({
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
            {
                extend: 'copy',
                exportOptions: { columns: ':not(:last-child)' }
            },
            {
                extend: 'csv',
                exportOptions: { columns: ':not(:last-child)' }
            },
            {
                extend: 'excel',
                exportOptions: { columns: ':not(:last-child)' }
            },
            {
                extend: 'pdf',
                exportOptions: { columns: ':not(:last-child)' },
                customize: function (doc) {
                    doc.defaultStyle.fontSize = 8;
                    doc.styles.tableHeader.fontSize = 9;
                    doc.pageMargins = [20, 20, 20, 20];
                }
            },
            {
                extend: 'print',
                exportOptions: { columns: ':not(:last-child)' }
            }
        ],
        initComplete: function () {
            $('.buttons-copy').html('<i class="bi bi-copy" title="Copy"></i>');
            $('.buttons-csv').html('<i class="bi bi-filetype-csv" title="CSV"></i>');
            $('.buttons-excel').html('<i class="bi bi-file-earmark-excel" title="Excel"></i>');
            $('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="PDF"></i>');
            $('.buttons-print').html('<i class="bi bi-printer" title="Print"></i>');
        }
    });

    // Toggle filter
    $('#filterBtn').click(function () {
        const $filterCard = $('#filterCard');
        $filterCard.toggleClass('show');
        $(this).text($filterCard.hasClass('show') ? 'Close' : 'Filter');
        $('#student_filter_form input').val('');
        table.search('').columns().search('').draw();
    });

    // Reset filters
    $('#resetBtn').click(function () {
        $('#student_filter_form input').val('');
        table.search('').columns().search('').draw();
    });

    // Apply filter on search button click
    $('#searchBtn').click(function (e) {
        e.preventDefault();
        let firstName = $('#firstName').val().trim();
        let lastName = $('#lastName').val().trim();
        let gender = $('#gender').val().trim();
        let standard = $('#standard').val().trim();

        table.columns().search(''); // clear all
        if (firstName) table.column(0).search(firstName);
        if (lastName) table.column(1).search(lastName);
        if (gender) table.column(2).search(gender);
        if (standard) table.column(3).search(standard);

        table.draw();
    });
});
function generateIdCard(studentRegistrationId) {debugger
	debugger;
	console.log('Generating Student ID Card...');

	var formData = new FormData();
	formData.append("studentRegistrationId", studentRegistrationId);

		$.ajax({
			type: "POST",
			url: '${generateIdCardURL}',
			data: formData,
			contentType: false,
			cache: false,
			processData: false,
			success: function (data) {debugger
				console.log("Response data:", data);
	            console.log("Download URL : "+data.downloadURL);
				if (data && data.downloadURL) {
					window.open(data.downloadURL, '_blank');
/* 					window.location.href = "/group/guest/administrative-dashboard"; // Redirect after open
 */				} else {
					console.log("Failed to generate ID card.");
				}
			},
			error: function (xhr, status, error) {
				console.error("AJAX Error:", error);
				console.log("Something went wrong while generating the ID card.");
			}
		});
}

</script>
