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
						<h5><liferay-ui:message key="scheduled-competition-list"/></h5>	
						<div><a href="/group/guest/competition-dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
				
					<div class="card-body ">
					
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
			                                        <label><liferay-ui:message key="sport-name" /></label>
			                                        <input type="text" class="form-control" id="sport-name" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="competition-name" /></label>
			                                        <input type="text" class="form-control" id="competition-name" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="registration-start-date" /></label>
			                                        <input type="text" class="form-control" id="registration-start-date" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="registration-end-date" /></label>
			                                        <input type="text" class="form-control" id="registration-end-date" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="competition-start-date" /></label>
			                                        <input type="text" class="form-control" id="competition-start-date" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="competition-end-date" /></label>
			                                        <input type="text" class="form-control" id="competition-end-date" />
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
					  <table id="competition-schedule-list" class="table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th><liferay-ui:message key="sr-no" /></th>
									<th><liferay-ui:message key="sport-name" /></th>
									<th><liferay-ui:message key="competition-name" /></th>
									<th><liferay-ui:message key="registration-start-date" /></th>
									<th><liferay-ui:message key="registration-end-date" /></th>
									<th><liferay-ui:message key="competition-start-date" /></th>
									<th><liferay-ui:message key="competition-end-date" /></th>
									<th><liferay-ui:message key="action" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="competitionSchedule" items="${competitionSchedules}">
									 <portlet:renderURL var="viewCompetitionScheduleURL">
										<portlet:param name="mvcRenderCommandName" value="<%=CompetitionManagementWebPortletKeys.ADD_COMPETITION_SCHEDULE_MVC_RENDER_COMMAND%>" />
										<portlet:param name="competitionScheduledId" value="${competitionSchedule.competitionScheduledId}" />
										<portlet:param name="competitionInitiationId" value="${competitionSchedule.competitionInitiationId}" />
										<portlet:param name="ptTeacherApplicationId" value="${competitionSchedule.ptTeacherApplicationId}" />
										<portlet:param name="cmd" value="view" />
										<portlet:param name="isPtTeacher" value="${isPtTeacher}" />
										<portlet:param name="isPrincipal" value="${isPrincipal}" />
										<portlet:param name="isDSORole" value="${isDSORole}" />
									</portlet:renderURL>
									 <portlet:renderURL var="resultUploadURL">
										<portlet:param name="mvcRenderCommandName" value="<%=CompetitionManagementWebPortletKeys.RESULT_UPLOAD_MVC_RENDER_COMMAND%>" />
										<portlet:param name="competitionScheduledId" value="${competitionSchedule.competitionScheduledId}" />
										<portlet:param name="competitionInitiationId" value="${competitionSchedule.competitionInitiationId}" />
										<portlet:param name="ptTeacherApplicationId" value="${competitionSchedule.ptTeacherApplicationId}" />
										<portlet:param name="cmd" value="add" />
									</portlet:renderURL>
									<tr>
										<td>${competitionSchedule.srNumber}</td>
										<td>${competitionSchedule.sportName}</td>
										<td>${competitionSchedule.competitionName}</td>
										<td>${competitionSchedule.registrationStartDateStr}</td>
										<td>${competitionSchedule.registrationEndDateStr}</td>
										<td>${competitionSchedule.competitionStartDateStr}</td>
										<td>${competitionSchedule.competitionEndDateStr}</td>
										<td><a class="btn btn-primary" href="${viewCompetitionScheduleURL}"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a>
										<c:if test="${isDSORole && !competitionSchedule.resultUpload}">
										  <a class="btn btn-primary" href="${resultUploadURL}"><i class="bi bi-file-earmark-arrow-up icons-color" title="<liferay-ui:message key="result-upload" />"></i></a>
										</c:if>
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

<script>
 
 jQuery(document).ready(function($) {
		var table = $('#competition-schedule-list').DataTable({
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
		
		        let sport_name = $('#sport-name').val().trim();
		        let competition_name = $('#competition-name').val().trim();
		        let registration_start_date = $('#registration-start-date').val().trim();
		        let registration_end_date = $('#registration-end-date').val().trim();
		        let competition_start_date = $('#competition-start-date').val().trim();
		        let competition_end_date = $('#competition-end-date').val().trim();
		       
		        table.columns().search('');
		        if (sport_name) table.column(1).search(sport_name);
		        if (competition_name) table.column(2).search(competition_name);
		        if (registration_start_date) table.column(3).search(registration_start_date);
		        if (registration_end_date) table.column(4).search(registration_end_date);
		        if (competition_start_date) table.column(5).search(competition_start_date);
		        if (competition_end_date) table.column(6).search(competition_end_date);
		        table.draw();
		    });
		 
	});
</script>
