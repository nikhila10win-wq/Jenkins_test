<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.9/pdfmake.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.9/vfs_fonts.js"></script>

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

<portlet:resourceURL id="<%=AwardsWebPortletKeys.GENERATE_REPORT%>" var="generateReportURL" />
<portlet:resourceURL id="<%=AwardsWebPortletKeys.GENERATE_FINAL_REPORT%>" var="generateFinalReportURL" />

<style>
    /* Custom styles for smooth transition */
 .filter-card {
     display: none;
     opacity: 0;
     transition: all 0.3s ease-in-out;
 }
 
 .filter-card.show {
     display: block;
     opacity: 1;
 }

#award_application_list_wrapper .top {
        display: flex;
        justify-content: space-between;
}

#award_application_list_wrapper .bottom {
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
				
					<!-- <div class="card-header d-flex align-item-center justify-content-between">
						<h5><liferay-ui:message key="award-application-list" /></h5>						
					</div> -->
					
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="award-application-list" /></h5>
						<div><a href="/group/guest/awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
					
					
					
		<div class="card-body">
					<!-- filter -->
						<div class="filter-container">
							<!-- Filter Toggle Button -->
							<button class="btn btn-primary m-0" id="filterBtn">
								<liferay-ui:message key="filter" />
							</button>

							<!-- Filter Card -->
							<div class="filter-card mt-4" id="filterCard">
								<div class="card card-background p-0">
									<div class="card-header header-card">
											<liferay-ui:message key="filter-options" />
									</div>

							<form id="buyer_purchase" method="POST">
									<div class="card-body">
										<div class="row">
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="applicant-name"/></label>
                                                 <input type="text" class="form-control" id="applicant" name="applicant" />
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="competition-name"/></label>
                                                <input type="text" class="form-control" id="competition" name="competition" />
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label> <liferay-ui:message key="sport-name" /></label>
                                                <select id="sport" name="sport" class="form-control">
													<option value=""><liferay-ui:message key="all" /></option>
													 <c:forEach var="sportsMaster" items="${sportsMaster}">
														<option value="${sportsMaster.name_en }">${sportsMaster.name_en}</option>
													 </c:forEach>
												</select>
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label> <liferay-ui:message key="country-of-competition" /></label>
                                                <input type="text" class="form-control" id="country" name="country" />
                                            </div>
										    </div>
										    
										    <div class="col-md-3">
										      <div class="form-group">
                                                <label> <liferay-ui:message key="role-name" /></label>
                                                <select id="role" name="role" class="form-control">
													<option value=""><liferay-ui:message key="all" /></option>
													<option value="Coach of Para"><liferay-ui:message key="coach-of-para" /></option>
													<option value="Athlete"><liferay-ui:message key="athlete" /></option>
													<option value="Coach"><liferay-ui:message key="coach" /></option>
													<option value="Adventure Person"><liferay-ui:message key="adventure-person" /></option>
													<option value="Para Athlete"><liferay-ui:message key="para-athlete" /></option>
												</select>
                                           	  </div>
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
						<!-- filter  -->
						
						
	<div class="universal-table mt-4">
		<table id="award_application_list" class="table-bordered" cellspacing="0" width="100%">
                <thead>
                    <tr>
                    	<th>
                            <input type="checkbox" id="selectAll" />
                        </th>
                        <th><liferay-ui:message key="applicant-name" /></th>
                        <th><liferay-ui:message key="competition-name" /></th>
                       	<th><liferay-ui:message key="sport-name" /></th>
                        <th><liferay-ui:message key="country-of-competition" /></th>
                        <th><liferay-ui:message key="role-name" /></th>
                        <th><liferay-ui:message key="desk-officer" /></th>
                        <th><liferay-ui:message key="desk-officer-name" /></th>
                       <%--  <th><liferay-ui:message key="action" /></th>
                       <c:if test="${isHO}">
                        <th><liferay-ui:message key="report" /></th>
                       </c:if>
                       <c:if test="${isHO}">
                        <th><liferay-ui:message key="final-report" /></th>
                       </c:if> --%>
                    </tr>
                </thead>
                <tbody>
                    
                    <c:forEach var="application" items="${applList}">
                         <portlet:renderURL var="applicationReviewURL">
                            <portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.OFFICER_REVIEW_APPLICATION%>" />
                            <portlet:param name="applicationId" value="${application.awardApplicationId}" />
                            <portlet:param name="cmd" value="edit" />
                        </portlet:renderURL>
                         
                        <tr>
                            <td>
                                <input type="checkbox" class="row-checkbox" value="${application.awardApplicationId}"/>
                            </td>
                            <td>${application.userName}</td>
                            <td>${application.competitionName}</td>
                           	<td>${application.sportsName}</td>
                            <td>${application.countryOfCompetition}</td>
                            <%-- <td>${application.participationYear}</td> --%>
                            <%-- <td>
                            <c:if test="${application.overallDeskOffRemarks ne '' and isDeskOfficer}">
                            	Submitted
                            </c:if>
                            <c:if test="${application.overallAssoRemarks ne '' and isAssociation}">
                            	Submitted
                            </c:if>
                            <c:if test="${application.overallDeskOffRemarks eq '' and isDeskOfficer}">
                            	Pending
                            </c:if>
                            <c:if test="${application.overallAssoRemarks eq '' and isAssociation}">
                            	Pending
                            </c:if>
                            </td> --%>
                            <td>${application.roleName}</td>
                            <td>${application.deskOffSport}</td>
                            <td>${application.sportsDeskOffName}</td>
                            <%-- <td><a href="${applicationReviewURL}"><liferay-ui:message key="view-verify" /></a>
                            </td>
                            <c:if test="${isHO}">
                            	<td><a href="javascript:void(0);" onclick="generateReport('${application.userId}', '${application.awardApplicationId}')"><liferay-ui:message key="report" /></a></td>
                            </c:if>
                            <c:if test="${isHO}">
                            	<td><a href="javascript:void(0);" onclick="generateFinalReport('${application.userId}', '${application.awardApplicationId}')"><liferay-ui:message key="final-report" /></a></td>
                            </c:if> --%>
                        </tr>
                    </c:forEach>
                    
                </tbody>
            </table>
            </div>
	</div>
					
					<div class="card-footer bg-transparent text-right p-4">
							<form id="approve-form">
								<div class="row mt-3">
									<div class="col-md-12 text-right">
											<button class="btn btn-primary submitBtn"  disabled><liferay-ui:message key="approve" /></button>
									</div>
								</div>
							</form>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>


<!-- modal popup for add competition -->
<div class="modal fade" id="savePrincipalFormModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="approve-sports-desk-officers-assignment"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<%-- <img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup> --%>
                                   		 <p><liferay-ui:message key="sports-desk-officers-assignment-approved"/></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/awards" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for add competition -->

<script>

$(document).ready(function() {
	   
	jQuery.noConflict();
		
	var table = $('#award_application_list').DataTable({
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
	    	 $('.buttons-copy').html('<i class="bi bi-copy" title="<liferay-ui:message key="copy" />"></i>');
		        $('.buttons-csv').html('<i class="bi bi-filetype-csv" title="<liferay-ui:message key="csv" />"></i>');
		        $('.buttons-excel').html('<i class="bi bi-file-earmark-excel" title="<liferay-ui:message key="excel" />"></i>');
		        $('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
		        $('.buttons-print').html('<i class="bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
	    }
	});



	$('#filterBtn').click(function() {
	    
	    $('#applicant').val('');
	    $('#competition').val(''); 
	    $('#sport').val('');
	    $('#country').val('');
	    $('#role').val('');
	    
	    const $filterCard = $('#filterCard');
	    $filterCard.toggleClass('show');
	    $(this).text($filterCard.hasClass('show') ? '<liferay-ui:message key='close' />' : '<liferay-ui:message key='filter' />');
	});


	$('#resetBtn').click(function() {
		$('#applicant').val('');
	    $('#competition').val(''); 
	    $('#sport').val('');
	    $('#country').val('');
	    $('#role').val('');
	    
	    if (typeof table !== 'undefined') {
	        table.search('').columns().search('').draw();
	        $('.dataTables_filter input').val('');
	    }
	});


	$(document).on('click', '#searchBtn', function () {
	    table.search('').columns().search('').draw();
	    $('.dataTables_filter input').val('');
	    
	    var getDisplayText = function(selectElement) {
	        return selectElement.options[selectElement.selectedIndex].text.trim();
	    };
	    
	    var applicant = $('#applicant').val().trim();
	    var competition = $('#competition').val().trim();
	    var sport = $('#sport').val().trim();
	    var country = $('#country').val().trim();
	    var role = $('#role').val().trim();
	    
	    console.log("Searching :", role);
	    console.log("Searching :", sport);
	    
	    if (applicant) table.column(1).search(applicant);
	    if (competition) table.column(2).search(competition);
	    if (sport) table.column(3).search(sport);
	    if (country) table.column(4).search(country);
	    if (role) table.column(5).search(role);
	    
	    table.draw();
	});


	});

/*  $(document).ready(function () {
	 jQuery.noConflict();
        // Initialize DataTable
        var table = $('#award_application_list').DataTable({
            "paging": true,
            "ordering": false,
            "searching": true,
            "responsive": true,
			"autoWidth": false,
        });
        $('#filterBtn').on('click', function () {debugger
            var roleNameValue = $('#roleNameFilter').val(); 
            var sportNameValue = $('#sportNameFilter').val();
            if (roleNameValue || sportNameValue) {
                table.column(4).search(roleNameValue).column(2).search(sportNameValue).draw();
            } else {
                table.column(4).search('').column(2).search('').draw(); 
            }
        });
 }); */
 
 function generateReport(userId, applicationId) {
	 console.log("USER ID ::: "+userId);
	    $.ajax({
	        type: "POST", 
	        url: '${generateReportURL}', 
	        data: { userId: userId, applicationId: applicationId }, 
	        success: function(data) { 
	            console.log("Response data:", data);
	            console.log("Download URL : "+data.downloadURL);
	            
                window.open(data.downloadURL, '_blank');
	            
	        },
	        error: function(xhr, status, error) {
	            console.error("Error occurred:", error);
	        }
	    });
	}
 
 function generateFinalReport(userId, applicationId) {
	 console.log("USER ID ::: "+userId);
	    $.ajax({
	        type: "POST", 
	        url: '${generateFinalReportURL}', 
	        data: { userId: userId, applicationId: applicationId }, 
	        success: function(data) { 
	            console.log("Response data:", data);
	            
				console.log("Download URL : "+data.downloadURL);
	            
                window.open(data.downloadURL, '_blank');
	            
	        },
	        error: function(xhr, status, error) {
	            console.error("Error occurred:", error);
	        }
	    });
	}
 
 $('#selectAll').on('change', function () {debugger
 	console.log("checked: ",$(this).prop('checked'));
     var isChecked = $(this).prop('checked');
     $('.row-checkbox:not(:disabled)').prop('checked', isChecked);
     toggleSubmitButton();
 });

 $(document).on('change', '.row-checkbox', function () {debugger
     var allChecked = $('.row-checkbox:not(:disabled)').length === $('.row-checkbox:checked:not(:disabled)').length;
     $('#selectAll').prop('checked', allChecked);
     toggleSubmitButton();
 });

 function toggleSubmitButton() {
     var atLeastOneChecked = $('.row-checkbox:checked').length > 0;
     $('.submitBtn').prop('disabled', !atLeastOneChecked);
 }

 $('.submitBtn').on('click', function (event) {debugger
     event.preventDefault(); // Prevent default form submission
     var formData = new FormData($('#approve-form')[0]);

     var selectedIds = $('.row-checkbox:checked').map(function () {
         return $(this).val();
     }).get();
     formData.append('selectedIds', JSON.stringify(selectedIds));
     $.ajax({
         type: "POST",
         url: '${approveDeskOfficerAssignmentFormURL}',
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
             if (data.applied === true) {
                 $("#savePrincipalFormModel").modal('show');
             } else {
                 alert("<liferay-ui:message key='response-submission-is-unsucessfull' />");
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
