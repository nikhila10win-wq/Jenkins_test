<%@ include file="/init.jsp" %>
<%@ page import="mhdsys.construction.tracker.web.constants.MhdsysConstructionTrackerWebPortletKeys" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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


<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdsysConstructionTrackerWebPortletKeys.REDIRECT %>" />
</portlet:renderURL>

<portlet:resourceURL id="<%=MhdsysConstructionTrackerWebPortletKeys.ADMINREVIEWRESOURCECOMMAND %>" var="adminReviewUrl" />

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

                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="construction-tracker-list" /></h5>
						<div><a href="#" class="btn btn-primary btn-sm rounded-pill back-btn-cn" onclick="backHomeUrl()"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
	
                    <div class="card-body">
						<!-- filter -->

						<div class="filter-container">
							<!-- Filter Toggle Button -->
							<button class="btn btn-primary m-0" id="filterBtn">
								<liferay-ui:message key="filter" />
							</button>

							<!-- Filter Card -->
							<div class="filter-card mt-2" id="filterCard">
								<div class="card card-background p-0">
									<div class="card-header header-card">
									
											<liferay-ui:message key="filter-options" />
						
									</div>
									<form id="construction_tracker_filter_form" method="POST">
									    <div class="card-body">
									        <div class="row">
									            <div class="col-md-3">
									                <label><liferay-ui:message key="construction-tracker-id" /></label>
									                <input type="text" class="form-control" id="constructionTrackerId" />
									            </div>
									            <div class="col-md-3">
									                <label><liferay-ui:message key="division" /></label>
									                <input type="text" class="form-control" id="division" />
									            </div>
									            <div class="col-md-3">
									                <label><liferay-ui:message key="district" /></label>
									                <input type="text" class="form-control" id="district" />
									            </div>
									            <div class="col-md-3">
									                <label><liferay-ui:message key="taluka" /></label>
									                <input type="text" class="form-control" id="taluka" />
									            </div>
									            <div class="col-md-3 mt-2">
									                <label><liferay-ui:message key="address" /></label>
									                <input type="text" class="form-control" id="address" />
									            </div>
									            <div class="col-md-3 mt-2">
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
						
                    <!-- Table -->
                    <div id="datatableWrapper" style="display: none;">
                    <div class="universal-table mt-4">
                    <form id="reviewForm" method="POST" enctype="multipart/form-data">
                    	<input type="hidden" name="adminAction" id="adminAction">
                    	<input type="hidden" name="TrackerId" id="constructionTrackerId">
                    	<div class="">
                        <table id="construction-tracker-list" class="table-bordered" width="100%">
						    <thead>
						        <tr>
						            <th><liferay-ui:message key="S.NO" /></th>
						            <th><liferay-ui:message key="construction-tracker-id" /></th>
						            <th><liferay-ui:message key="division" /></th>
						            <th><liferay-ui:message key="district" /></th>
						            <th><liferay-ui:message key="taluka" /></th>
						            <!-- <th><liferay-ui:message key="address" /></th> -->
						            <th><liferay-ui:message key="creation-date" /></th>
						            <th><liferay-ui:message key="status" /></th>
						            <th><liferay-ui:message key="action" /></th>
						        </tr>
						    </thead>
						    <tbody>
						        <c:forEach var="tracker" items="${List}" varStatus="status">
						            <c:set var="editMode" value="${tracker.isSaveAsDraft eq true ? 'draft' : 'edit'}" />
						            <tr>
						                 <td>${status.index + 1}</td> 
						                <td>${tracker.constructionTrackerUniqueId}</td>
						                <td>${divisionMap[tracker.division]}</td>
						                <td>${districtMap[tracker.district]}</td>
						                <td>${talukaMap[tracker.taluka]}</td>
						               <%--  <td>${tracker.address}</td> --%>
										<td><fmt:formatDate value='${tracker.createdDate}' pattern='yyyy-MM-dd'/></td>
						              <td>
										    ${(isDSO || isTSO) ? (tracker.isSaveAsDraft ? 'Draft' : 
										        (tracker.applicationStatus == 0 ? 'Pending' : 
										        tracker.applicationStatus == 1 ? 'Approved by Director' : 
										        tracker.applicationStatus == 2 ? 'Approved By HO' : 'Rejected')) :
										     (isDDD || isHOAdmin) ? 
										        (tracker.applicationStatus == 0 ? 'Pending' : 
										        tracker.applicationStatus == 1 ? 'Approved by Director' : 
										        tracker.applicationStatus == 2 ? 'Approved By HO' : 'Rejected') : ''}
										</td>
						
						
						                <td>
						                    <!-- DDD actions -->
						                    <c:if test="${isDDD}">
						                        <div class="mb-2 tooltip-icon">
						                          <ul class="inline-item">
						                            <li class="list-inline-item">
						                            <a href="${redirectUrl}&mode=view&constructionTrackerId=${tracker.constructionTrackerId}" class="btn btn-primary">
						                                <i class="bi bi-eye icons-color" title="<liferay-ui:message key='view' />"></i>
						                            </a>
						                            </li>
						                           <%--  <c:if test="${tracker.applicationStatus == 0}">
						                            	<li class="list-inline-item">
							                                <button type="button" class="btn btn-primary" onclick="reviewConstructionTracker('approve', ${tracker.constructionTrackerId})">
							                                    <i class="bi bi-check2-circle icons-color" title="<liferay-ui:message key='approve' />"></i>
							                                </button>
						                                </li>
						                                <li class="list-inline-item">
							                                <button type="button" class="btn btn-danger" onclick="reviewConstructionTracker('reject', ${tracker.constructionTrackerId})">
							                                    <i class="bi bi-x-circle icons-color" title="<liferay-ui:message key='reject' />"></i>
							                                </button>
						                                </li>
						                            </c:if> --%>
						                            
						                          <c:choose>
													    <c:when test="${tracker.applicationStatus == 0}">
														    <li class="list-inline-item">
								                                <button type="button" class="btn btn-primary" onclick="reviewConstructionTracker('approve', ${tracker.constructionTrackerId})">
								                                    <i class="bi bi-check2-circle icons-color" title="<liferay-ui:message key='approve' />"></i>
								                                </button>
							                                </li>
							                                <li class="list-inline-item">
								                                <button type="button" class="btn btn-danger" onclick="reviewConstructionTracker('reject', ${tracker.constructionTrackerId})">
								                                    <i class="bi bi-x-circle icons-color" title="<liferay-ui:message key='reject' />"></i>
								                                </button>
							                                </li>
													    </c:when>
													    <c:otherwise>
													    	<li class="list-inline-item">
							                                <button type="button" class="btn btn-primary" disabled="disabled">
							                                    <i class="bi bi-check2-circle icons-color" title="<liferay-ui:message key='approve' />"></i>
							                                </button>
							                                </li>
							                                <li class="list-inline-item">
								                                <button type="button" class="btn btn-danger" disabled="disabled">
								                                    <i class="bi bi-x-circle icons-color" title="<liferay-ui:message key='reject' />"></i>
								                                </button>
							                                </li>
													    </c:otherwise>
													</c:choose>
						                            
						                            </ul>
						                        </div>
						                    </c:if>
						
						                    <!-- HO Admin actions -->
						                    <c:if test="${isHOAdmin}">
						                        <div class="mb-2 tooltip-icon">
						                           <ul class="inline-item">
						                            <li class="list-inline-item">
						                            <a href="${redirectUrl}&mode=view&constructionTrackerId=${tracker.constructionTrackerId}" class="btn btn-primary">
						                                <i class="bi bi-eye icons-color" title="<liferay-ui:message key='view' />"></i>
						                            </a>
						                            </li>
						                            <%-- <c:if test="${tracker.applicationStatus == 1}">
						                                <li class="list-inline-item">
						                                <button type="button" class="btn btn-primary" onclick="reviewConstructionTracker('approve', ${tracker.constructionTrackerId})">
						                                    <i class="bi bi-check2-circle icons-color" title="<liferay-ui:message key='approve' />"></i>
						                                </button>
						                                </li>
						                                <li class="list-inline-item">
						                                <button type="button" class="btn btn-danger" onclick="reviewConstructionTracker('reject', ${tracker.constructionTrackerId})">
						                                    <i class="bi bi-x-circle icons-color" title="<liferay-ui:message key='reject' />"></i>
						                                </button>
						                                </li>
						                            </c:if> --%>
						                            
						                               <c:choose>
													    <c:when test="${tracker.applicationStatus == 1}">
														     <li class="list-inline-item">
								                                <button type="button" class="btn btn-primary" onclick="reviewConstructionTracker('approve', ${tracker.constructionTrackerId})">
								                                    <i class="bi bi-check2-circle icons-color" title="<liferay-ui:message key='approve' />"></i>
								                                </button>
								                                </li>
								                                <li class="list-inline-item">
								                                <button type="button" class="btn btn-danger" onclick="reviewConstructionTracker('reject', ${tracker.constructionTrackerId})">
								                                    <i class="bi bi-x-circle icons-color" title="<liferay-ui:message key='reject' />"></i>
								                                </button>
								                                </li>
													    </c:when>
													    <c:otherwise>
													    	 	<li class="list-inline-item">
								                                <button type="button" class="btn btn-primary" disabled="disabled">
								                                    <i class="bi bi-check2-circle icons-color" title="<liferay-ui:message key='approve' />"></i>
								                                </button>
								                                </li>
								                                <li class="list-inline-item">
								                                <button type="button" class="btn btn-danger" disabled="disabled">
								                                    <i class="bi bi-x-circle icons-color" title="<liferay-ui:message key='reject' />"></i>
								                                </button>
								                              	</li>
													    </c:otherwise>
													</c:choose>
													
						                            </ul>
						                        </div>
						                    </c:if>
						
						                    <!-- DSO actions -->
						                    <c:if test="${isDSO || isTSO}">
						                        <div class=" mb-2 tooltip-icon">
						                        <ul class="inline-item">
						                        
						                            <%-- <c:if test="${!tracker.isSaveAsDraft}">
							                           <li class="list-inline-item">
								                                <a href="${redirectUrl}&mode=view&constructionTrackerId=${tracker.constructionTrackerId}" class="btn btn-primary">
								                                    <i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i>
								                                </a>
								                        </li>
						                            </c:if>
						                            
						                            <c:if test="${tracker.applicationStatus == 9 || tracker.isSaveAsDraft }">
							                            <li class="list-inline-item">
							                            <a href="${redirectUrl}&mode=${editMode}&constructionTrackerId=${tracker.constructionTrackerId}" class="btn btn-primary">
							                                <i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key="edit" />"></i>
							                            </a>
							                            </li>
						                            </c:if> --%>
						                            
						                            
						                            <c:choose>
													    <c:when test="${!tracker.isSaveAsDraft}">
														    <li class="list-inline-item">
								                                <a href="${redirectUrl}&mode=view&constructionTrackerId=${tracker.constructionTrackerId}" class="btn btn-primary">
								                                    <i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i>
								                                </a>
								                        	</li>
													    </c:when>
													    <c:otherwise>
												    	 	<li class="list-inline-item">
								                                <button class="btn btn-primary" disabled="disabled">
								                                    <i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i>
								                                </button>
							                        		</li>
													    </c:otherwise>
													</c:choose>
													
						                            <c:choose>
													    <c:when test="${tracker.applicationStatus == 9 || tracker.isSaveAsDraft }">
														   <li class="list-inline-item">
								                            <a href="${redirectUrl}&mode=${editMode}&constructionTrackerId=${tracker.constructionTrackerId}" class="btn btn-primary">
								                                <i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key="edit" />"></i>
								                            </a>
								                            </li>
													    </c:when>
													    <c:otherwise>
												    	 	<li class="list-inline-item">
									                            <button class="btn btn-primary" disabled="disabled">
									                                <i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key="edit" />"></i>
									                            </button>
									                         </li>
													    </c:otherwise>
													</c:choose>
													
						                             
						                          </ul>
						                        </div>
						                    </c:if>
						                </td>
						            </tr>
						        </c:forEach>
						    </tbody>
						</table>
					</div>
                        
                        <div class="modal fade" id="saveConstructionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog modal-dialog-centered" role="document">
						    <div class="modal-content modal-bg">
						      <div class="modal-header justify-content-center align-items-center">
						        <h5 class="modal-title"><liferay-ui:message key="review" /></h5>
						      </div>
						      <div class="modal-body">
						           <div class="row">
								      <div class="col-md-12">
								     	 <div class="form-group">
									        <label><liferay-ui:message key="review" /><sup class="text-danger">*</sup></label>
									        <input type="text" class="form-control" name="review" id="review" />
									   	 </div>
								      </div>
								      <div class="col-md-12">
								      	<div class="form-group">
								      		<label for="">
										      <liferay-ui:message key="review-document" />
										      <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
										    </label>
										
										    <div class="custom-file">
										      <input type="file" class="custom-file-input reviewDoc mb-2" id="reviewDoc" name="reviewDoc"> 
										      <label class="custom-file-label" for="reviewDoc"><liferay-ui:message key="choose-file" /></label>
										    </div>
								      	</div>
								      </div>
								    </div>
						      </div>
						      <div class="card-footer bg-transparent text-right p-4">
							      <div class="d-flex justify-content-end">
								        <button type="button" class="btn btn-primary maha-save-btn" id="approvalSaveBtn">
								          <liferay-ui:message key="save"/>
								        </button>
								        <button type="button" class="btn btn-secondary" onclick="closeModal()">
								          <liferay-ui:message key="close"/>
								        </button>
							      </div>
							  </div>
						    </div>
						  </div>
						</div>

                        </form>
                    </div>
                    </div>
                  </div>
                </div>
            </div>
        </div>
</div>
</div>

<script>

var $reviewForm = $('#reviewForm');

$(document).ready(function () {
	$('#reviewForm').validate({
		 onkeyup: function (element) {
	   	        $(element).valid();
	   	    },
	   	    onchange: function (element) {
	   	        $(element).valid();
	   	    },
	    ignore: [],
	    rules: {
	        review: {
	           /*  required: function () {
	                return $("#adminAction").val() === "reject";
	            } */
	        	required:true, alphanumericWithSpecialChars:true, noEdgeSpaces:true, singleSpaceOnly:true, noConsecutiveSpecials:true, 
	        	onlyDotAtEnd:true,  minlength: 3, maxlength: 250,
	        },
	        reviewDoc: {
	            pdfOnly: true,
	            maxFileSize: 2 * 1024 * 1024
	        }
	    },
	    messages: {
	        review: {
	            required: "<liferay-ui:message key='please-enter-remarks' />",
	            minlength: "<liferay-ui:message key='please-enter-min-3-characters' />",
	            maxlength: "<liferay-ui:message key='please-enter-max-250-characters' />",
	        }
	    },
	    errorClass: "text-danger",
	});
	
	 $.validator.addMethod("alphanumericWithSpecialChars", function(value, element) {
	 		// Allows letters, numbers, dot (.), comma (,), hyphen (-), and space
	 		    return this.optional(element) || /^[A-Za-z0-9.,\- ]+$/.test(value);
	 	    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
	 
	$.validator.addMethod("pdfOnly", function (value, element) {
	    if (element.files.length === 0) return true; // allow empty
	    return element.files[0].type === "application/pdf";
	}, "<liferay-ui:message key='please-upload-pdf' />");

	$.validator.addMethod("maxFileSize", function (value, element, param) {
	    if (element.files.length === 0) return true;
	    return element.files[0].size <= param;
	}, "<liferay-ui:message key='file-size-must-be-less-than-2mb' />");

	$.validator.addMethod("noEdgeSpaces", function(value, element) {
		  return this.optional(element) || value === value.trim();
		}, "<liferay-ui:message key='no-leading-trailing-spaces-allowed' />");

	$.validator.addMethod("singleSpaceOnly", function(value, element) {
	  return this.optional(element) || !/\s{2,}/.test(value);
	}, "<liferay-ui:message key='only-one-space-between-words-allowed' />");

	$.validator.addMethod("noConsecutiveSpecials", function(value, element) {
		  return this.optional(element) || !/([.,/#-]\s*){2,}/.test(value);
	}, "<liferay-ui:message key='no-consecutive-specials-allowed' />");

	$.validator.addMethod("onlyDotAtEnd", function(value, element) {
		  return this.optional(element) || /\.$/.test(value) || /[A-Za-z0-9]$/.test(value);
	}, "<liferay-ui:message key='only-dot-at-end-allowed' />");

	$.validator.addMethod("validReviewChars", function(value, element) {
	    return this.optional(element) || /^[A-Za-z0-9\s.,-]+$/.test(value);
	}, "<liferay-ui:message key='please-enter-valid-review' />");
});


$("#approvalSaveBtn").click(function(){
	
	 if (!$('#reviewForm').valid()) {
         return;
     }
	
	var adminAction = $('#adminAction').val()
	var constructionTrackerId = $('#constructionTrackerId').val();
	console.log("adminAction:: ",adminAction ," constructionTrackerId: ",constructionTrackerId);
	  	const reviewInput = document.getElementById("review");
	    const fileInput = document.getElementById("reviewDoc");
	    const file = fileInput.files[0];

	    const formData = new FormData();
	    formData.append("review", reviewInput.value);
	    formData.append("constructionTrackerId", constructionTrackerId);
	    formData.append("action", adminAction);
	    if (file) {
	        formData.append("reviewDoc", file);
	    }

	    console.log("validations are passed -------- ")
	     $.ajax({
	        type: "POST",
	        url: "${adminReviewUrl}",
	        data: formData,
	        contentType: false,
	        processData: false,
	        success: function (data) {
	            console.log("Success:", data);
	            $("#saveConstructionModal").modal('hide');
	            if (data.redirectUrl) {
	            	    const a = document.createElement("a");
	            	    a.href = data.redirectUrl;
	            	    a.style.display = "none";
	            	    document.body.appendChild(a);
	            	    a.click();
	            }
	        },
	        error: function () {
	            console.error("Submission failed.");
	        }
	    }); 
	
});
	
function closeModal() {
    $("#saveConstructionModal").modal('hide');
    $('#review').val('');
    $('#reviewDoc').val('');
    $('.custom-file-label[for="reviewDoc"]').text('<liferay-ui:message key="choose-file" />');
    $('#reviewForm').validate().resetForm();
}


function reviewConstructionTracker(action, constructionTrackerId) {
	$('#adminAction').val(action)
	$('#constructionTrackerId').val(constructionTrackerId)
	$("#saveConstructionModal").modal('show');
}

function backHomeUrl(){
	    window.location.href = "/group/guest/construction-tracker?clear=true";
}

jQuery(document).ready(function($) {
var table = $('#construction-tracker-list').DataTable({
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
		        $('#construction_tracker_filter_form input').val('');
		        table.search('').columns().search('').draw();
		        $(this).text('Filter');
		    } else {
		        $(this).text('Close');
		    }
		});

        // Reset
        $('#resetBtn').click(function () {
            $('#construction_tracker_filter_form input').val('');
            table.search('').columns().search('').draw();
        });

        // Search
        $('#searchBtn').click(function (e) {
            e.preventDefault();

            let constructionTrackerId = $('#constructionTrackerId').val().trim();
            let division = $('#division').val().trim();
            let district = $('#district').val().trim();
            let taluka = $('#taluka').val().trim();
            let address = $('#address').val().trim();
            let status = $('#status').val().trim();

            // Clear previous column searches
            table.columns().search('');

            // Apply search filters by column index
            if (constructionTrackerId) table.column(0).search(constructionTrackerId);
            if (division) table.column(1).search(division);
            if (district) table.column(2).search(district);
            if (taluka) table.column(3).search(taluka);
            if (address) table.column(4).search(address);
            if (status) table.column(5).search(status);

            table.draw();
        });

  
})
    
</script>