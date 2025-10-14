<%@ include file="/init.jsp"%>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css">
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>

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
    
#apply-registered-list_wrapper .top {
        display: flex;
        justify-content: space-between;
}

#apply-registered-list_wrapper .bottom {
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
						<h5 class="mb-0"> <liferay-ui:message key="competition-registered-list" /> </h5>
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
                                                <label><liferay-ui:message key="campaign-level"/></label>
                                                <select class="form-control" name="competitionLevel" id="competitionLevel">
													<option value=""><liferay-ui:message key="select" /></option>
													<c:forEach var="competitionLevel" items="${competitionLevels}">
														<option value="${competitionLevel.name_en}">${competitionLevel.name_en}</option>
													</c:forEach>
												</select>
                                            </div>
										    </div>
										
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label> <liferay-ui:message key="campaign-name" /></label>
                                                <input type="text" class="form-control" id="competitionName" name="competitionName" />
                                            </div>
										    </div>
										    
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label> <liferay-ui:message key="campaign-place" /></label>
                                                <input type="text" class="form-control" id="competitionPlace" name="competitionPlace" />
                                            </div>
										    </div>
										    
										    <div class="col-md-3">
										    	<div class="form-group">
                                                <label> <liferay-ui:message key="sport-name" /></label>
										    	<select class="form-control" name ="sportsName" id="sportsName" >
										    		<option value=""><liferay-ui:message key="select" /></option>
												    <c:forEach var="sportsMaster" items="${sportsMaster}">
												        <option value="${sportsMaster.name_en}">${sportsMaster.name_en}</option>
												    </c:forEach>
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
					<div class="">
						<table id="apply-registered-list" class="table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th><input type="checkbox" id="selectAll" /></th>
									<th><liferay-ui:message key="competition-level" /></th>
									<th><liferay-ui:message key="competition-name" /></th>
									<th><liferay-ui:message key="competition-place" /></th>
									<th><liferay-ui:message key="sport-name" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="awardApplication" items="${awardApplications}">
									<tr>
										<td><input type="checkbox" class="row-checkbox"
											value="${awardApplication.awardApplicationId}" /></td>
										<td>${awardApplication.competitionLevel}</td>
										<td>${awardApplication.competitionName}</td>
										<td>${awardApplication.competitionPlace}</td>
										<td>${awardApplication.sportsName}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
</div>
					</div>
					</div>
					<div class="card-footer bg-transparent text-right p-4">
						<form id="approve-form">
							<div class="row mt-3">
								<div class="col-md-12 text-right">
									<button class="btn btn-primary submitBtn" disabled>
										<liferay-ui:message key="apply" />
									</button>
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
				<h5 class="modal-title" id="exampleModalLongTitle">
					<liferay-ui:message key="apply-to-desk-officer" />
				</h5>
				<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 text-center">
						<div>
							<p>
								<liferay-ui:message key="forwarded-to-desk-officer" />
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer d-flex justify-content-end">
				<a href="/group/guest/awards" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()">
					<liferay-ui:message key="close" />
				</a>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for add competition -->

<script>

$(document).ready(function() {
	   
	jQuery.noConflict();
		
	var table = $('#apply-registered-list').DataTable({
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
		 $('#competitionLevel').val('');
		    $('#competitionName').val('');
		    $('#competitionPlace').val('');
		    $('#sportsName').val('');
	    
	    const $filterCard = $('#filterCard');
	    $filterCard.toggleClass('show');
	    $(this).text($filterCard.hasClass('show') ? '<liferay-ui:message key='close' />' : '<liferay-ui:message key='filter' />');
	});


	$('#resetBtn').click(function() {
	    $('#competitionLevel').val('');
	    $('#competitionName').val('');
	    $('#competitionPlace').val('');
	    $('#sportsName').val('');
	    
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
	    
	    var competitionLevel = $('#competitionLevel').val().trim();
	    var competitionName = $('#competitionName').val().trim();
	    var competitionPlace = $('#competitionPlace').val().trim();
	    var sportsName = $('#sportsName').val().trim();
	    
	    console.log("Searching :", competitionLevel);
	    console.log("Searching :", competitionPlace);
	    
	    if (competitionLevel) table.column(1).search(competitionLevel);
	    if (competitionName) table.column(2).search(competitionName);
	    if (competitionPlace) table.column(3).search(competitionPlace);
	    if (sportsName) table.column(4).search(sportsName);
	    
	    table.draw();
	});


	});



	/* $(document).ready(function() {
		var table = $('#apply-registered-list').DataTable({
			"paging" : true,
			"ordering" : false,
			"searching" : true,
			"responsive" : true,
			"autoWidth" : false,

		});
	}); */

	$('#selectAll').on('change', function() {
		debugger
		console.log("checked: ", $(this).prop('checked'));
		var isChecked = $(this).prop('checked');
		$('.row-checkbox:not(:disabled)').prop('checked', isChecked);
		toggleSubmitButton();
	});

	$(document).on('change', '.row-checkbox', function() {debugger
		var allChecked = $('.row-checkbox:not(:disabled)').length === $('.row-checkbox:checked:not(:disabled)').length;
		$('#selectAll').prop('checked', allChecked);
		toggleSubmitButton();
	});

	function toggleSubmitButton() {
		var atLeastOneChecked = $('.row-checkbox:checked').length > 0;
		$('.submitBtn').prop('disabled', !atLeastOneChecked);
	}

	$('.submitBtn').on('click', function(event) {debugger
		event.preventDefault();
		var formData = new FormData($('#approve-form')[0]);

		var selectedIds = $('.row-checkbox:checked').map(function() {
			return $(this).val();
		}).get();
		formData.append('selectedIds', JSON.stringify(selectedIds));
		$.ajax({
			type : "POST",
			url : '${sendToDeskOfficerFormURL}',
			data : formData,
			contentType : false,
			cache : false,
			processData : false,
			success : function(data) {
				console.log("data: ", typeof data);
				if (typeof data === 'string') {
					try {
						data = JSON.parse(data);
					} catch (e) {
						console
								.error(
										"Failed to parse JSON response: ",
										e);
						return;
					}
				}
				console.log("Parsed data: ", data);
				if (data.applied === true) {
					$("#savePrincipalFormModel").modal(
							'show');
				} else {
					alert("<liferay-ui:message key='response-submission-is-unsucessfull' />");
				}
			},
			error : function(error) {
				console
						.error(
								"Error occurred during submission: ",
								error);
			}
		});
});

function closeModal() {
	debugger
	$("#savePrincipalFormModel").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}
</script>