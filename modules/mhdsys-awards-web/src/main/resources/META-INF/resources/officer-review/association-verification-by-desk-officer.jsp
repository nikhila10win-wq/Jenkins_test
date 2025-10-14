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
					
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="association-list" />
						</h5>
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
										    <div class="col-md-6">
										        <div class="form-group">
                                                <label><liferay-ui:message key="association-name"/></label>
                                                 <input type="text" class="form-control" id="associationName" name="associationName" />
                                            </div>
										    </div>
										
										    <div class="col-md-6">
										        <div class="form-group">
                                                <label><liferay-ui:message key="name"/></label>
                                                <input type="text" class="form-control" id="name" name="name" />
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
                    	<!-- <th>
                            <input type="checkbox" id="selectAll" />
                        </th> -->
                        <th><liferay-ui:message key="association-name" /></th>
                        <th hidden="hidden"><liferay-ui:message key="association-id" /></th>
                        <th><liferay-ui:message key="name" /></th>
                       	<th><liferay-ui:message key="appointment-letter" /></th>
                        <th><liferay-ui:message key="verification" /></th>
                        <th><liferay-ui:message key="remarks" /></th>
                       <c:if test="${!isHOAdmin}">
                        <th><liferay-ui:message key="action" /></th>
                       </c:if>
                    </tr>
                </thead>
                <tbody>
                    
                    <%-- <c:forEach var="application" items="${applList}">
                    
                        <tr>
                            <td>${application.associationName}</td>
                            <td>${application.name}</td>
                           	<td>
                           	<a href="${application.appointmentLetterUrl}" target="_blank" style="flex-grow: 1; text-decoration: none;">
	                            ${application.appointmentLetterName}
	                        </a>
                           	</td>
                            <td>
                            
                            <div class="form-group">
								<div>
									<label> 
										<input type="radio" name="mainDeskOffVerification" id="mainDeskOffVerification" value="Approve"
										<c:if test="${application.mainDeskOffVerification == 'Approve'}">checked</c:if>
										<c:if test="${not empty application.mainDeskOffVerification}">disabled</c:if>> Approve
									</label> 
									<label> 
										<input type="radio" name="mainDeskOffVerification" id="mainDeskOffVerification" value="Reject"
										<c:if test="${application.mainDeskOffVerification == 'Reject'}">checked</c:if>
										<c:if test="${not empty application.mainDeskOffVerification}">disabled</c:if> > Reject
									</label>
								</div>
							</div>
                            
                            </td>
                            <td>
                            
                            <div class="form-group">
								<input type="text" class="form-control" name="mainDeskOffRemarks" id="mainDeskOffRemarks" value="${application.mainDeskOffRemarks}" 
								<c:if test="${isHO || not empty application.mainDeskOffRemarks}">disabled</c:if> />
							</div>
                            
                            </td>
                            <td>
                            <form id="approve-form">
                           		<button class="btn btn-primary submitBtn" type="button" > <liferay-ui:message key="submit" />
								</button>
							</form>
                            </td>
                        </tr>
                        
                    </c:forEach> --%>
                    
                    
                    <c:forEach var="application" items="${applList}">
					    <tr>
					    	<td hidden="hidden" >
					    	
					    	<input type="hidden" 
					                       class="form-control associationUserId-input"  
					                       name="associationUserId_${application.awardApplicationId}" 
					                       value="${application.associationUserId}" 
					                       <c:if test="${isHO || not empty application.associationUserId}">disabled</c:if> />
					    	
					        <td style="border-bottom-left-radius: 15px;">${application.associationName}</td>
					        <td>${application.name}</td>
					        <td>
					            <a href="${application.appointmentLetterUrl}" target="_blank" style="flex-grow: 1; text-decoration: none;">
					                ${application.appointmentLetterName}
					            </a>
					        </td>
					        <td>
					            <div class="form-group mb-0">
					                <div>
					                    <label> 
					                        <input type="radio" 
					                               name="mainDeskOffVerification_${application.awardApplicationId}"  
					                               value="Approve"
					                               <c:if test="${application.mainDeskOffVerification == 'Approve'}">checked</c:if>
					                               <c:if test="${isHOAdmin || not empty application.mainDeskOffVerification}">disabled</c:if>> Approve
					                    </label> 
					                    <label> 
					                        <input type="radio" 
					                               name="mainDeskOffVerification_${application.awardApplicationId}"
					                               value="Reject"
					                               <c:if test="${application.mainDeskOffVerification == 'Reject'}">checked</c:if>
					                               <c:if test="${isHOAdmin || not empty application.mainDeskOffVerification}">disabled</c:if>> Reject
					                    </label>
					                </div>
					            </div>
					        </td>
					        <td>
					            <div class="form-group mb-0 ">
					                <input type="text" 
					                       class="form-control remarks-input"  
					                       name="mainDeskOffRemarks_${application.awardApplicationId}" 
					                       value="${application.mainDeskOffRemarks}" 
					                       <c:if test="${isHOAdmin || not empty application.mainDeskOffVerification}">disabled</c:if> />
					            </div>
					        </td>
					        
					      <c:if test="${!isHOAdmin}">
					        <td class="no-wrap">
					            <form class="approve-form" data-application-id="${application.awardApplicationId}">
					                <button class="btn btn-primary submitBtn" type="button" <c:if test="${not empty application.mainDeskOffVerification}">disabled</c:if>> 
					                    <liferay-ui:message key="submit" />
					                </button>
					            </form>
					        </td>
					      </c:if>
					        
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


<!-- modal popup for add competition -->
<div class="modal fade" id="savePrincipalFormModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="association-verification"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
                                   		 <p><liferay-ui:message key="association-verified-successfully"/></p>
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
	    
	    $('#associationName').val('');
	    $('#name').val(''); 
	    
	    const $filterCard = $('#filterCard');
	    $filterCard.toggleClass('show');
	    $(this).text($filterCard.hasClass('show') ? '<liferay-ui:message key='close' />' : '<liferay-ui:message key='filter' />');
	});


	$('#resetBtn').click(function() {
		$('#associationName').val('');
	    $('#name').val(''); 
	    
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
	    
	    var associationName = $('#associationName').val().trim();
	    var name = $('#name').val().trim();
	    
	    console.log("Searching :", associationName);
	    console.log("Searching :", name);
	    
	    if (associationName) table.column(0).search(associationName);
	    if (name) table.column(2).search(name);
	    
	    table.draw();
	});


	});


 $('.submitBtn').on('click', function (event) {
	    event.preventDefault();
	    
	    var $form = $(this).closest('.approve-form');
	    var applicationId = $form.data('application-id');
	    
	    var verification = $('input[name="mainDeskOffVerification_' + applicationId + '"]:checked').val();
	    
	    var remarks = $('input[name="mainDeskOffRemarks_' + applicationId + '"]').val();
	    
	    var associationUserId = $('input[name="associationUserId_' + applicationId + '"]').val();
	    
	    console.log("SUBMITTING DATA:", {
	        applicationId: applicationId,
	        verification: verification,
	        remarks: remarks,
	        associationUserId: associationUserId
	    });

	    if (!verification) {
	        alert("Please select Approve or Reject!");
	        return;
	    }

	    var formData = new FormData();
	    formData.append('applicationId', applicationId);
	    formData.append('verification', verification || ""); 
	    formData.append('remarks', remarks || ""); 
	    formData.append('associationUserId', associationUserId || ""); 

	    $.ajax({
	        type: "POST",
	        url: '${associationVerificationURL}',
	        data: formData,
	        contentType: false,
	        cache: false,
	        processData: false,
	        success: function (data) {
	            if (typeof data === 'string') {
	                try {
	                    data = JSON.parse(data);
	                } catch (e) {
	                    console.error("Failed to parse JSON response: ", e);
	                    alert("Server returned invalid data. Please try again.");
	                    return;
	                }
	            }
	            if (data.verified === true) {
	                $("#savePrincipalFormModel").modal('show');
	            } else {
	                alert("<liferay-ui:message key='response-submission-is-unsucessfull' />");
	            }
	        },
	        error: function (error) {
	            console.error("AJAX Error:", error);
	            alert("Failed to submit. Please check console for details.");
	        }
	    });
	});
 
 
 function closeModal() {debugger
     $("#savePrincipalFormModel").modal('hide');
 	$(".modal-backdrop").remove();
 	$("body").removeClass("modal-open");
 }

</script>
