<%@page import="com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<!-- jQuery FIRST -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- DataTables CSS -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<!-- DataTables + Buttons JS -->
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.print.min.js"></script>

<!-- Export Dependencies -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.7.1/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/vfs_fonts.js"></script>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

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

    #facility-list_wrapper .top {
        display: flex;
        justify-content: space-between;
    }

    #afacility-list_wrapper .bottom {
        display: flex;
        justify-content: space-between;
        align-items: baseline;
    }

   @media (max-width: 998px) {
	  .universal-table table {
		    overflow-x: scroll;
		    display: block;
		}
}
</style>

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5><liferay-ui:message key="pt-teacher-requested-list"/></h5>
						<div>
						  <a href="/group/guest/competition-dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn">  <i class="bi bi-arrow-left-circle mr-1"></i> <liferay-ui:message key="back" /> </a>
						</div>
					</div>
				
					<div class="card-body">
					
					<!-- filter STARTS -->

						<div class="filter-container">
							<!-- Filter Toggle Button -->
							<button class="btn btn-primary m-0" id="filterBtn">
								<liferay-ui:message key="filter" />
							</button>

							<!-- Filter Card -->
							<div class="filter-card mt-4 card-background p-0" id="filterCard">
								<div class="card card-background p-0">
									<div class="card-header header-card">
											<liferay-ui:message key="filter-options" />
						 			
									</div>
									<form id="filter_form" method="POST">
										<div class="card-body">
											 <div class="row">
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="application-number" /></label>
			                                        <input type="text" class="form-control" id="application-number" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="sport-name" /></label>
			                                        <input type="text" class="form-control" id="sport-name" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="count-of-team-or-individual" /></label>
			                                        <input type="text" class="form-control" id="count-of-team-or-individual" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="total-fees" /></label>
			                                        <input type="text" class="form-control" id="total-fees" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="status" /></label>
			                                        <input type="text" class="form-control" id="status" />
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
						
					<div class="universal-table mt-4">
				   <table id="principal-approve-list" class="table-bordered" cellspacing="0" width="100%">
                <thead>
                    <tr>
                    <c:if test="${isPrincipal || isDSORole}">
                        <th>
                            <input type="checkbox" id="selectAll" />
                        </th>
                    </c:if>
                   		<th><liferay-ui:message key="s.no" /></th>
                        <th><liferay-ui:message key="application-number" /></th>
                        <th><liferay-ui:message key="sport-name" /></th>
                        <th><liferay-ui:message key="count-of-team-or-individual" /></th>
                        <th><liferay-ui:message key="total-fees" /></th>
                        <th><liferay-ui:message key="status" /></th>
                        <th><liferay-ui:message key="action" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ptTeacherApplication" items="${ptTeacherApplications}" varStatus="status">
                        <portlet:renderURL var="viewPtTeacherApplicationURL">
                            <portlet:param name="mvcRenderCommandName" value="<%=CompetitionManagementWebPortletKeys.PT_TEACHER_APPLICATION_FORM_MVC_RENDER_COMMAND%>" />
                            <portlet:param name="competitionInitiationId" value="${ptTeacherApplication.competitionInitiationId}" />
                            <portlet:param name="ptTeacherApplicationId" value="${ptTeacherApplication.ptTeacherApplicationId}" />
                            <portlet:param name="cmd" value="view" />
                            <portlet:param name="isPtTeacher" value="${isPtTeacher}" />
                            <portlet:param name="isPrincipal" value="${isPrincipal}" />
                            <portlet:param name="isDSORole" value="${isDSORole}" />
                        </portlet:renderURL>
                        <portlet:renderURL var="editApplicationParticipantURL">
                            <portlet:param name="mvcRenderCommandName" value="<%=CompetitionManagementWebPortletKeys.PT_TEACHER_APPLICATION_FORM_MVC_RENDER_COMMAND%>" />
                            <portlet:param name="competitionInitiationId" value="${ptTeacherApplication.competitionInitiationId}" />
                            <portlet:param name="ptTeacherApplicationId" value="${ptTeacherApplication.ptTeacherApplicationId}" />
                            <portlet:param name="cmd" value="editParticipant" />
                        </portlet:renderURL>
                        <tr>
                         <c:if test="${isPrincipal || isDSORole}">
                            <td>
                                <input type="checkbox" class="row-checkbox" value="${ptTeacherApplication.ptTeacherApplicationId}"
                                <c:if test="${(isPrincipal && (ptTeacherApplication.status == 3 || ptTeacherApplication.status == 4))}">disabled</c:if> />
                            </td>
                         </c:if>
                         	<td>${status.index + 1}</td>
                            <td>${ptTeacherApplication.applicationNumber}</td>
                            <td>${ptTeacherApplication.sportName}</td>
                            <td>${ptTeacherApplication.countOfTeamOrIndividual}</td>
                            <td>${ptTeacherApplication.totalFees}</td>
                            <td>${ptTeacherApplication.statusName}</td>
                            <td>
                            <%-- <a href="${viewPtTeacherApplicationURL}" class="btn btn-primary"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a> --%>
	                           
	                           <div class="tooltip-icon">
									<ul class="inline-item">
									<li class="list-inline-item">
									<a href="${viewPtTeacherApplicationURL}" class="btn btn-primary"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a>
									</li>
									<c:if test="${isPtTeacher && ptTeacherApplication.status == 4}">
									<li class="list-inline-item">
										    <a href="${editApplicationParticipantURL}" class="btn btn-primary"><liferay-ui:message key="update-applicant" /></a>
									</li>
										   </c:if>
									</ul>
									</div>
 
	                            <%-- <c:if test="${isPtTeacher && ptTeacherApplication.status == 4}">
	                            	<a href="${editApplicationParticipantURL}" class="btn btn-primary"><liferay-ui:message key="update-applicant" /></a>
	                            </c:if> --%>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
				</div>		
						
					<div class="card-footer bg-transparent text-right p-4">
					    <c:if test="${isPrincipal}">
							    <form id="principal-approve-form">
							        <div class="row mt-3">
							            <div class="col-md-12 text-right">
							                <button id="submitBtn" class="btn btn-primary" disabled><liferay-ui:message key="approve" /></button>
							            </div>
							        </div>
							    </form>
						    </c:if>
					</div>
					
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for add competition -->
<div class="modal fade" id="savePrincipalFormModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="principal-approval"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup>
                                    <liferay-ui:message key="principal-approval-has-been-successfully-submitted"/></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/principal-approved-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for add competition -->


<script>
 
 jQuery(document).ready(function($) {
	var table = $('#principal-approve-list').DataTable({
	    "paging": true,
	    "ordering": false,
	    "searching": true,
	    "responsive": true,
	    "autoWidth": false,
	    "language": {
			  "search": '<liferay-ui:message key="search" />',
				"emptyTable": '<liferay-ui:message key="no-data-available-in-table" />',
			  "infoFiltered": "",
			  "lengthMenu": '_MENU_',  
			  "info": "<liferay-ui:message key="Showing-total-entries" />",
			  "zeroRecords": "<liferay-ui:message key="No-matching-records-found" />",
			  "infoEmpty": "<liferay-ui:message key='Showing-total-entries' />",
			   oPaginate: {
				    sNext: '<em class="bi bi-chevron-right"></em>',
				    sPrevious: '<em class="bi bi-chevron-left"></em>',
				    }
		    },
		    "layout": {
		        topStart: {
		            buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
		        }
		    },
	    "dom": '<"top"Bf>rt<"bottom"lip><"clear">',
	    "buttons": [
	        {
	            extend: 'copy',
	            exportOptions: {
	                columns: ':not(:last-child)'
	            }
	        },
	        {
	            extend: 'csv',
	            exportOptions: {
	                columns: ':not(:last-child)'
	            }
	        },
	        {
	            extend: 'excel',
	            exportOptions: {
	                columns: ':not(:last-child)'
	            },
	            customize: function(xlsx) {
	                var sheet = xlsx.xl.worksheets['sheet1.xml'];
	                // Additional excel formatting if needed
	            }
	        },
	        {
	            extend: 'pdf',
	            exportOptions: {
	                columns: ':not(:last-child)'
	            },
	            customize: function(doc) {
	                doc.defaultStyle.fontSize = 8;
	                doc.styles.tableHeader.fontSize = 9;
	                doc.pageMargins = [20, 20, 20, 20];
	            }
	        },
	        {
	            extend: 'print',
	            exportOptions: {
	                columns: ':not(:last-child)'
	            }
	        }
	    ],
	    "initComplete": function() {
	    	$('#datatableWrapper').show(); // once dataTable loads only we will shows
	    	$('.buttons-copy').html('<i class="bi bi-copy" title="<liferay-ui:message key="copy" />"></i>');
	        $('.buttons-csv').html('<i class="bi bi-filetype-csv" title="<liferay-ui:message key="csv" />"></i>');
	        $('.buttons-excel').html('<i class="bi bi-file-earmark-excel" title="<liferay-ui:message key="excel" />"></i>');
	        $('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
	        $('.buttons-print').html('<i class="bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
	    }
	});

	  // Toggle filter
	    $('#filterBtn').click(function () {
		    const $filterCard = $('#filterCard');
		    $filterCard.toggleClass('show');
		
		    if (!$filterCard.hasClass('show')) {
		        $('#filter_form input').val('');
		        table.search('').columns().search('').draw();
		        $(this).text('Filter');
		    } else {
		        $(this).text('Close');
		    }
		});
	
	    // Reset
	    $('#resetBtn').click(function () {
	        $('#filter_form input').val('');
	        table.search('').columns().search('').draw();
	    });
	
	 // Search
	    $('#searchBtn').click(function (e) {
	        e.preventDefault();
	
	        let application_number = $('#application-number').val().trim();
	        let sport_name = $('#sport-name').val().trim();
	        let count_of_team_or_individual = $('#count-of-team-or-individual').val().trim();
	        let total_fees = $('#total-fees').val().trim();
	        let status = $('#status').val().trim();
	       
	        table.columns().search('');
	        if (application_number) table.column(1).search(application_number);
	        if (sport_name) table.column(2).search(sport_name);
	        if (count_of_team_or_individual) table.column(3).search(count_of_team_or_individual);
	        if (total_fees) table.column(4).search(total_fees);
	        if (status) table.column(5).search(status);
	        table.draw();
	    });

});
	
        // "Select All" functionality
        $('#selectAll').on('change', function () {debugger
        	console.log("checked: ",$(this).prop('checked'));
            var isChecked = $(this).prop('checked');
            $('.row-checkbox:not(:disabled)').prop('checked', isChecked);
            toggleSubmitButton();
        });

        // Update "Select All" checkbox state
        $(document).on('change', '.row-checkbox', function () {debugger
            var allChecked = $('.row-checkbox:not(:disabled)').length === $('.row-checkbox:checked:not(:disabled)').length;
            $('#selectAll').prop('checked', allChecked);
            toggleSubmitButton();
        });

        // Enable/Disable Submit Button based on selected checkboxes
        function toggleSubmitButton() {
            var atLeastOneChecked = $('.row-checkbox:checked').length > 0;
            $('#submitBtn').prop('disabled', !atLeastOneChecked);
        }

        // Submit button click event
        $('#submitBtn').on('click', function (event) {
            event.preventDefault(); // Prevent default form submission
            var formData = new FormData($('#principal-approve-form')[0]);

            // Add selected IDs to form data
            var selectedIds = $('.row-checkbox:checked').map(function () {
                return $(this).val();
            }).get();
            formData.append('selectedIds', JSON.stringify(selectedIds));

            // AJAX call
            $.ajax({
                type: "POST",
                url: '${principalApproveFormURL}',
                data: formData,
                contentType: false,
                cache: false,
                processData: false,
                success: function (data) {
                    console.log("data: ", typeof data);
                    if (typeof data === 'string') {
                        try {
                            data = JSON.parse(data);
                        } catch (e) {
                            console.error("Failed to parse JSON response: ", e);
                            return;
                        }
                    }
                    console.log("Parsed data: ", data);
                    if (data.principalApproval === true) {
                        $("#savePrincipalFormModel").modal('show');
                    } else {
                        alert("<liferay-ui:message key='the-principal-approval-is-unsucessfull' />");
                    }
                },
                error: function (error) {
                    console.error("Error occurred during submission: ", error);
                }
            });
        });
        function closeModal() {debugger
            $("#savePrincipalFormModel").modal('hide');
        	$(".modal-backdrop").remove();
        	$("body").removeClass("modal-open");
        }
</script>
