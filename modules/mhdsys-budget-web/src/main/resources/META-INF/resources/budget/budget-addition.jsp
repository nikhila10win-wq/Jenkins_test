<%@page import="com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.19.5/jquery.validate.min.js"></script>
 
<style>
#attachmentsError{
    position: absolute;
    width: 100%;
    left: 0;
    bottom: -27px;
    font-size: 12px;
}
</style>
 
<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.BUDGETADDITION %>" var="addBudgetURL" />
<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.GETMASTERSDATA%>" var="getMasterData" />
<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.GETMASTERSUNIQUEID%>" var="getMasterUniqueId" />

<form id="budget_addition" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="budget-addition" /></h5>
						<div><a href="/group/guest/budget-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
 
                    <form id="GPFDetails" method="POST" enctype="multipart/form-data">
                        <div class="card-body">
                            <div class="card card-background p-0">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="budget-details" />
                                </div>
 
                                <input type="hidden" class="form-control" id="budgetId" name="budgetId" value="${budget.budgetId}" />
                                <input type="hidden" class="form-control" id="mode" name="mode" value="${mode}" />
 
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="financial-year"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="financialYear" id="financialYear" <c:if test="${mode eq 'view'}">disabled</c:if>>
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                    <option value="2021-22" <c:if test="${budget.financialYear eq '2021-22'}">selected</c:if>> <liferay-ui:message key="2021-22"/></option>
                                                    <option value="2022-23" <c:if test="${budget.financialYear eq '2022-23'}">selected</c:if>> <liferay-ui:message key="2022-23"/></option>
                                                    <option value="2023-24" <c:if test="${budget.financialYear eq '2023-24'}">selected</c:if>> <liferay-ui:message key="2023-24"/></option>
                                                    <option value="2024-25" <c:if test="${budget.financialYear eq '2024-25'}">selected</c:if>> <liferay-ui:message key="2024-25"/></option>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="category"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="category" id="category" <c:if test="${mode eq 'view'}">disabled</c:if>>
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                    <option value="Schemes" <c:if test="${budget.category eq 'Schemes'}">selected</c:if>> <liferay-ui:message key="schemes"/></option>
                                                    <option value="Committed" <c:if test="${budget.category eq 'Committed'}">selected</c:if>> <liferay-ui:message key="committed"/></option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
 									
 									<div class="row">
 										<div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="schemes"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="master" id="master" <c:if test="${mode eq 'view'}">disabled</c:if>>
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="account-head"/><sup class="text-danger">*</sup></label>
                                                <input type="text" class="form-control" id="uniqueId" name="uniqueId"
                                                    value="${budget.uniqueId}" readonly />
                                            </div>
                                        </div>
 									</div>
 									
                                    <div class="row">
                                        <div class="col-md-6">
										    <div class="form-group">
										        <label> <liferay-ui:message key="approved-funds-rs" /> <sup class="text-danger">*</sup> </label>
										        <input type="text" class="form-control" id="approvedBudget" name="approvedBudget"  value="${budget.approvedBudget}" 
										               <c:if test="${mode == 'view' || not empty budget.approvedBudget}">readonly</c:if> />
										    </div>
										</div>
                                        
										<div class="col-md-6">
										    <div class="form-group" id="supplementaryDemandWrapper">
											    <label> <liferay-ui:message key="supplementary-demand-rs" /> <sup class="text-danger">*</sup></label>
											
											    <c:if test="${not empty supplementaryDemands}">
											        <c:forEach var="demand" items="${supplementaryDemands}" varStatus="status">
											            <div class="input-group mb-2 supplementary-demand-field">
											                <input type="text" class="form-control supplementaryDemand" name="supplementaryDemand" value="${demand}"
											                       <c:if test="${mode == 'view'}">disabled</c:if> />
											
											                <c:if test="${mode != 'view'}">
											                    <c:if test="${status.first}">
											                        <button type="button" class="btn btn-success add-more">+</button>
											                    </c:if>
											                    <c:if test="${not status.first}">
											                        <button type="button" class="btn btn-danger remove">-</button>
											                    </c:if>
											                </c:if>
											            </div>
											        </c:forEach>
											    </c:if>
											
											    <c:if test="${empty supplementaryDemands}">
											        <div class="input-group mb-2 supplementary-demand-field">
											            <input type="text" class="form-control supplementaryDemand" name="supplementaryDemand"
											                   <c:if test="${mode == 'view'}">disabled</c:if> />
											            <c:if test="${mode != 'view'}">
											                <button type="button" class="btn btn-success add-more">+</button>
											            </c:if>
											        </div>
											    </c:if>
											</div>
										</div>
                                    </div>
 
                                    <div class="row">
                                        <div class="col-md-6">
										    <div class="form-group">
										        <label> <liferay-ui:message key="total-fund-rs" /> <sup class="text-danger">*</sup> </label>
										        <input type="text" class="form-control" id="totalFund" name="totalFund"
										               value="${budget.totalFund}" readonly />
										    </div>
										</div>
                                        
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>  <liferay-ui:message key="funds-recieved-rs" /> <sup class="text-danger">*</sup> </label>
                                                <input type="text" class="form-control" id="recievedAmount" name="recievedAmount"
                                                    value="${budget.recievedAmount}" <c:if test="${mode == 'view'}">disabled</c:if> />
                                            </div>
                                        </div>
                                    </div>
 
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="expense-rs" /> <sup class="text-danger">*</sup> </label>
                                                <input type="text" class="form-control" id="totalSpend" name="totalSpend"
                                                    value="${budget.totalSpend}" <c:if test="${mode == 'view'}">disabled</c:if> />
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label> <liferay-ui:message key="overall-percentage" />  <sup class="text-danger">*</sup> </label>
                                                <input type="text" class="form-control" id="overallPercentage" name="overallPercentage"
                                                   value="${budget.overallPercentage}"  readonly />
                                            </div>
                                        </div>
                                    </div>
 
                                    <div class="row">
                                      <div class="col-md-6">
									    <div class="form-group">
										    <c:if test="${mode eq 'add' or mode eq 'edit'}">
										    	<label><liferay-ui:message key="attachment" /><sup class="text-danger">*</sup>
										                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file-of-size-2mb" />">
										                </em>
										        </label>
										    </c:if>
										    <c:if test="${mode eq 'view'}">
										    	<label><liferay-ui:message key="attachment" /><sup class="text-danger">*</sup></label>
										    </c:if>
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input attachments" id="attachments" name="attachments" multiple
									                    onchange="handleMultipleFileUpload(this, 'attachments', 'attachmentsPreviewContainer', 'attachmentsPreviewList', 'attachmentsError', 'attachment')">
									                <label class="custom-file-label" for="attachments">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="attachmentsError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="attachment" name="attachment" 
									                value='<c:if test="${mode eq 'edit' and not empty budget.attachmentsNames}"><c:forEach var="photoName" items="${application.attachmentsNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="attachmentsPreviewContainer" 
									                style='<c:if test="${mode ne 'edit' or empty budget.attachmentsURLs}">display: none;</c:if>'>
									                <ul id="attachmentsPreviewList" name="attachmentsPreviewList" class="list-group">
									                    <c:if test="${mode eq 'edit' and not empty budget.attachmentsURLs}">
									                        <c:forEach var="photoURL" items="${budget.attachmentsURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${budget.attachmentsNames[status.index]}
									                                </a>
									                                <!-- FIXED: Correct parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" onclick="removeFile(${status.index}, 'attachmentsPreviewContainer', 'attachmentsPreviewList', 'attachmentsError', 'attachment', 'attachments')">
																	    <i class="bi bi-x-circle-fill"></i>
																	</button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <c:if test="${mode eq 'view' and not empty budget.attachmentsURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${budget.attachmentsURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${budget.attachmentsNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if>
									    </div>
									</div>
                                  </div>
                                </div>
                            </div>
                            
                           <%-- <c:if test="${isHoAdmin or not empty budget.remarks}">
	                            <div class="card card-background p-0 mb-4">
	                                <div class="card-header header-card">
	                                    <liferay-ui:message key="remarks" />
	                                </div>
	                                <div class="card-body">
	                                <div class="row">
									    <div class="col-md-6">
									        <div class="form-group">
									            <label><liferay-ui:message key="remarks" /><sup class="text-danger">*</sup></label>
									            <input type="text" class="form-control" id="remarks" name="remarks" value="${budget.remarks}" 
									                  <c:if test="${mode == 'view' or not empty budget.remarks or isDeskOfficer}">disabled</c:if> />
									        </div>
									    </div>
	                                </div>
	                                </div>
	                            </div>
                           </c:if> --%>
                        </div>
                        
                       <c:if test="${mode eq 'add' or mode eq 'edit'}">
				            <div class="card-footer bg-transparent text-right p-4">
					                <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/budget';">
					                    <liferay-ui:message key="cancel" />
					                </button>	
					                <button class="btn btn-primary" type="button" onclick="location.reload();">
					                    <liferay-ui:message key="reset" />
					                </button>	
					                <button class="btn btn-primary" type="button" onclick="submitDetails(event)">
					                 <c:if test="${mode eq 'add'}">
					                    <liferay-ui:message key="submit"/>
					                 </c:if>
					                 <c:if test="${mode eq 'edit'}">
					                     <liferay-ui:message key="update"/>
					                 </c:if>
					                </button>
				            </div>
			           </c:if>
			            
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</form>

<!-- modal popup for budget -->
<div class="modal fade" id="addBudgetModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="budget"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div> <p id="success-application"></p> </div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/budget-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for budget -->

<script>
document.addEventListener('DOMContentLoaded', function() {
    const receivedInput = document.getElementById('recievedAmount');
    const spendInput = document.getElementById('totalSpend');
    const percentageInput = document.getElementById('overallPercentage');
    
    console.log("Recieved Amount : "+receivedInput+" Spend Amount : "+spendInput+" Percentage : "+percentageInput);
    
    function calculatePercentage() {
        const received = parseFloat(receivedInput.value) || 0;
        const spend = parseFloat(spendInput.value) || 0;
        
        console.log("#########");
        
        if (received <= 0) {
            percentageInput.value = "N/A";
            return;
        }
        
        const percentage = (spend / received) * 100;
        console.log("Percentage %  "+percentage);
        percentageInput.value = percentage.toFixed(2) + '%';
    }
    
    receivedInput.addEventListener('input', calculatePercentage);
    spendInput.addEventListener('input', calculatePercentage);
});


function masterUniqueId(master) {
    console.log("Master :::::    "+master);
    var category = document.getElementById('category').value;
    console.log("Category :::   "+category);
    $.ajax({
        url: '${getMasterUniqueId}',
        type: 'GET',
        data: { category: category,
        	     master: master },
        success: function(data) {
        	console.log("success success ");
        	data = JSON.parse(data);
        	console.log("Data : "+data.uniqueId);
        	
        	var uniqueNo = document.getElementById('uniqueId');
        	
        	uniqueNo.value = data.uniqueId;
        },
    });
}

function masters(category) {
    console.log("Category :::::    "+category);
    var mode = '${mode}'
    console.log("Mode : "+mode);
    var categoryValue = '${budget.category}'
    var master = '${budget.master}'
    console.log("Category ::: "+category+" Master ::::  "+master);
    
    $.ajax({
        url: '${getMasterData}',
        type: 'GET',
        data: { category: category },
        success: function(data) {
        	console.log("success success ");
            const $masterSelect = $("#master");
            $masterSelect.empty();
            let masters = typeof data === "string" ? JSON.parse(data) : data;

            if (masters && masters.length > 0) {
                $masterSelect.append('<option value="">Select</option>');
                $.each(masters, function(index, value) {
                	
                	console.log("Values :::;;    "+value.id);
                	console.log("Second Values ::::::::    "+name);
                    let selectedAttr = (mode === 'view' || mode === 'edit') && value.id == master ? ' selected' : '';
                    console.log("Selected values ::::: ."+selectedAttr +";;");
                    $masterSelect.append('<option value="' + value.id + '"' + selectedAttr + '>' + value.name + '</option>');
               
                });
            } else {
                $masterSelect.append('<option value="">No masters available</option>');
            }
        },
        error: function(xhr, status, error) {
            console.error("Error fetching districts:", error);
            $("#master").empty().append('<option value="">Error loading masters</option>');
        }
    });
}

$(document).ready(function () {
	
	var mode = '${mode}'
	console.log("Mode :::  "+mode);
	if(mode === 'view' || mode === 'edit'){
		var categoryValue = '${budget.category}'
		var masterValue = '${budget.master}'
		console.log("Category and Master ::::   "+categoryValue+" , "+masterValue);
		masters(categoryValue);
		masterUniqueId(masterValue);
	}
	
	
	 calculateTotal();
	 
    function calculateTotal() {
        let approved = parseFloat($("#approvedBudget").val()) || 0;
        let supplementarySum = 0;

        $(".supplementaryDemand").each(function () {
            let val = parseFloat($(this).val()) || 0;
            supplementarySum += val;
        });

        let total = approved + supplementarySum;
        $("#totalFund").val(total);
    }

    $(document).on("input", "#approvedBudget, .supplementaryDemand", function () {
        calculateTotal();
    });

    $(document).on("click", ".add-more", function () {
        let field = `
            <div class="input-group mb-2 supplementary-demand-field">
                <input type="text" class="form-control supplementaryDemand" name="supplementaryDemand" />
                <button type="button" class="btn btn-danger remove">-</button>
            </div>`;
        $("#supplementaryDemandWrapper").append(field);
    });

    $(document).on("click", ".remove", function () {
        $(this).closest(".supplementary-demand-field").remove();
        calculateTotal();
    });

	
	
	$('select[name="category"]').on('change', function() {
	    masters($(this).val());
	});
	
	$('select[name="master"]').on('change', function() {
		masterUniqueId($(this).val());
	});
	
	
    var isHoAdmin = ${isHoAdmin}; 
    var isDeskOfficer = ${isDeskOfficer}; 

    // j-Query validation start
    $('#budget_addition').validate({
        onkeyup: function (element) {
            $(element).valid();
        },
        onchange: function (element) {
            $(element).valid();
        },
        
        rules: {
        	financialYear: {
                required: true, 
            },
            category: {       
                required: true,
            },
            master: {
                required: true,
            },
            approvedBudget: {       
                required: true,
                pattern: /^\d+(\.\d{1,2})?$/,
            },
            supplementaryDemand: {
                required: true,
                pattern: /^\d+(\.\d{1,2})?$/,
            },
            recievedAmount: {       
                required: true,
                pattern: /^\d+(\.\d{1,2})?$/,
            },
            totalSpend: {
                required: true,
                pattern: /^\d+(\.\d{1,2})?$/,
            },
        },
        messages: {
        	financialYear: {
                required: "<liferay-ui:message key='please-select-financial-year'/>",
            },
            category: {       
                required: "<liferay-ui:message key='please-select-category'/>",
            },
            master: {
                required: "<liferay-ui:message key='please-select-scheme'/>",
            },
            approvedBudget: {       
                required: "<liferay-ui:message key='please-enter-approved-budget'/>",
                pattern: "<liferay-ui:message key='enter-a-valid-amount-(only digits with up to 2 decimals)'/>",
            },
            supplementaryDemand: {
                required: "<liferay-ui:message key='please-enter-supplementary-demand'/>",
                pattern: "<liferay-ui:message key='enter-a-valid-amount-(only digits with up to 2 decimals)'/>",
            },
            recievedAmount: {       
                required: "<liferay-ui:message key='please-enter-recieved-amount'/>",
                pattern: "<liferay-ui:message key='enter-a-valid-amount-(only digits with up to 2 decimals)'/>",
            },
            totalSpend: {
                required: "<liferay-ui:message key='please-enter-total-spend'/>",
                pattern: "<liferay-ui:message key='enter-a-valid-amount-(only digits with up to 2 decimals)'/>",
            },
        },
    }); 
    
 // Validate PDF extension
    $.validator.addMethod("pdfOnly", function (value, element) {
        if (element.files.length === 0) return true; 
        const fileName = element.files[0].name;
        const ext = fileName.split('.').pop().toLowerCase();
        return ext === "pdf";
    }, "<liferay-ui:message key='allowed-only-pdf-file' />");
 
    $.validator.addMethod("maxFileSize", function (value, element) {
        if (element.files.length === 0) return true;
        return element.files[0].size <= 2 * 1024 * 1024; 
    }, "<liferay-ui:message key='file-size-limit' />");
    
    $.validator.addMethod("startEndSpace", function(value, element) {
        return this.optional(element) || /^[^\s].*[^\s]$/.test(value);
    }, "<liferay-ui:message key='leading-or-trailing-spaces-are-not-allowed' />");

    $.validator.addMethod("validateName", function(value, element) {
        return this.optional(element) || /^[A-Za-z]+(?: [A-Za-z]+)*$/.test(value);
    }, "<liferay-ui:message key='only-alphabets-and-space-are-allowed' />");
    
    $.validator.addMethod("validAddress", function (value, element) {
        value = $.trim(value); 
        return this.optional(element) || (/^(?!.*\s{2,})[a-zA-Z0-9\s,.\-/#]{3,250}$/.test(value));
    }, "<liferay-ui:message key='please-enter-valid-address' />");
    
    $.validator.addMethod("validateEmail", function(value, element) {
 		const regex =  /^(?!.*\.\.)(?!.*__)(?!.*[._][._])(?![_\.])[a-zA-Z0-9._]*[a-zA-Z][a-zA-Z0-9._]*(?<![_\.])@[a-zA-Z0-9][a-zA-Z0-9-]*\.[a-zA-Z]{2,}$/
	    return this.optional(element) || regex.test(value);
	}, "<liferay-ui:message key='please-enter-valid-email-address'/>");
    
    $.validator.addMethod("validateRemarks", function(value, element) {
    	   const regex = /^(?!0+$)(?!.*  )[A-Za-z0-9]+(?:[ ]?[,.\- ]?[ ]?[A-Za-z0-9]+)*(?: \.|\.)?$/i;
    	    return this.optional(element) || regex.test(value);
    	}, "<liferay-ui:message key='please-enter-valid-remarks' />");
    
    $.validator.addMethod("generalFieldsValidation", function(value, element) {
  	   const regex = /^(?!0+$)(?!.* {2})(?!.*(\s[\/-]|[\/-]\s))(?!.*([,.\-/])\2)[A-Za-z0-9]+(?:[ ]?[,\.]?[ ]?[A-Za-z0-9]+|[\/-][A-Za-z0-9]+)*$/i;
  	    return this.optional(element) || regex.test(value);
  	}, "<liferay-ui:message key='only-letters-numbers-symbols-allowed' />");

    $.validator.addMethod("singleSpaceBetweenWords", function(value) {
	        return !/ {2,}/.test(value);
	    }, '<liferay-ui:message key="only-one-space-allowed-between-words"/>');

	 $.validator.addMethod("noConsecutiveSpecialChars", function(value, element) {
	    var hasLetterOrDigit = /[A-Za-z0-9]/.test(value);

	    var repeatedSpecials = /([.,\/\-])\1+/;

	    var mixedSpecials = /[.,\/\-]{2,}/;

	    if (hasLetterOrDigit && (repeatedSpecials.test(value) || mixedSpecials.test(value))) {
	        return false;
	    }
	    return true;
	}, "<liferay-ui:message key='no-consecutive-special-characters-allowed' />");

	$.validator.addMethod("noSpaceAroundDashSlash", function (value, element) {
	    const regex = /(\s[-]|[-]\s|\/\s|\s\/)/;
	    return this.optional(element) || !regex.test(value);
	}, "<liferay-ui:message key='no-space-around-hyphen-or-slash-allowed' />");

	$.validator.addMethod("noStartEndSpecialChar", function (value, element) {
	    return this.optional(element) || !/^[.,\/\-]|[.,\/\-]$/.test(value);
	}, "<liferay-ui:message key='should-not-start-or-end-with-special-characters' />");
    // j-Query validation end
});



function submitDetails(event){debugger
	console.log('inside submit ')
	var form = $("#budget_addition")[0];
	var formData = new FormData(form);
	
	if (event) {
        event.preventDefault();
    }
	
	if($('#budget_addition').valid()){
		
		uploadedFilesAttachments.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingAttachementsFiles", fileObj.name);
	                formData.append("existingAttachementsFilesURLs", fileObj.url);
	                formData.append("existingAttachementsIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("actualAttachements", fileObj.file);
	                formData.append("actualAttachementsNames", fileObj.name);
	            }
	        }
	    });
		
	       $.ajax({
	        type: "POST",
	        url: '${addBudgetURL}' ,
	        data:  formData,
	        contentType : false,
			cache : false,
			processData : false,
	        success: function(data){
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
	        	/* if(data.budget == true){
	        		var msg = "<liferay-ui:message key="the-budget-details-are-saved-successfully"/>";
	        	    $('#success-application').html(msg);
	        		 $("#addBudgetModal").modal('show');
	        	} */
	        	
	        	if (data.budget == true) {
	        	    var msg = "";
	        	    
	        	    if (data.mode == 'edit') {
	        	        if (data.isHoAdmin) {
	        	            msg = "<liferay-ui:message key='the-budget-details-verified-successfully'/>";
	        	        } else {
	        	            msg = "<liferay-ui:message key='the-budget-details-updated-successfully'/>";
	        	        }
	        	    } else { 
	        	        msg = "<liferay-ui:message key='the-budget-details-are-saved-successfully'/>";
	        	    }
	        	    
	        	    $('#success-application').html(msg);
	        	    $("#addBudgetModal").modal('show');
	        	}else{
	        		var msg = "<liferay-ui:message key="the-budget-details-are-failed-to-submit"/>";
	        	    $('#success-application').html(msg);
	        		 $("#addBudgetModal").modal('show');
	        	}
	        }
      });
   }
};
	
	
function closeModal() {debugger
    $("#addBudgetModal").modal('hide');
 	$(".modal-backdrop").remove();
 	$("body").removeClass("modal-open");
}



//Multiple File Upload
var uploadedFilesAttachments = [];

document.addEventListener('DOMContentLoaded', function() {
    <c:if test="${mode eq 'edit' and not empty budget.attachmentsNames}">
    uploadedFilesAttachments = [
	        <c:forEach var="photoName" items="${budget.attachmentsNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${budget.attachmentsURLs[status.index]}',
	                id: '${budget.attachmentsIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Geo Tag Photos :", uploadedFilesAttachments);
	    updateHiddenInput('attachment', uploadedFilesAttachments);
	</c:if>
	
});

function updateHiddenInput(inputId, filesArray) {
    const activeFiles = filesArray.filter(f => !f.markedForDelete).map(f => f.name);
    document.getElementById(inputId).value = activeFiles.join(',');
}

function handleMultipleFileUpload(eventOrInput, inputId, previewContainerId, previewListId, errorSpanId, hiddenInputId) {
	
    const fileInput = eventOrInput.target ? eventOrInput.target : eventOrInput;
    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    let uploadedFiles = uploadedFilesAttachments;

    const newFiles = Array.from(fileInput.files);
    const activeFilesCount = uploadedFiles.filter(f => !f.markedForDelete).length;
    const totalFiles = activeFilesCount + newFiles.length;

    if (totalFiles > 5) {
        errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="You can upload a maximum of 4 files." /></span>';
        errorSpan.style.display = "block";
        fileInput.value = "";
        return;
    }

    for (let file of newFiles) {
        const ext = file.name.split('.').pop().toLowerCase();

        if (uploadedFiles.some(f => f.name === file.name && !f.markedForDelete)) {
            errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="this-file-is-already-uploaded" /></span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }
 
        
        if (inputId === 'attachments') {
            if (!['pdf'].includes(ext)) {
                errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="allowed-only-pdf-file" /></span>';
                errorSpan.style.display = "block";
                fileInput.value = "";
                uploadedFilesAttachments.length = 1;
                return;
            }
        } 

        if (file.size >= 2 * 1024 * 1024) {
            errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="file-size-limit" /></span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }
    }

    errorSpan.textContent = "";
    errorSpan.style.display = "none";

    newFiles.forEach(file => {
        uploadedFiles.push({
            file: file,
            name: file.name,
            isExisting: false,
            markedForDelete: false
        });
    });

    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);
    fileInput.value = "";
}

function removeFile(index, previewContainerId, previewListId, errorSpanId, hiddenInputId, inputId) {
    let uploadedFiles = uploadedFilesAttachments;
    

    if (index < 0 || index >= uploadedFiles.length) {
        console.error("Invalid file index:", index);
        return;
    }

    if (uploadedFiles[index].isExisting) {
        uploadedFiles[index].markedForDelete = true;
    } else {
        uploadedFiles.splice(index, 1);
    }

    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    if (!previewContainer || !previewList || !errorSpan || !hiddenInput) {
        console.error("Required DOM elements not found");
        return;
    }

    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);
    const hasActiveFiles = uploadedFiles.some(f => !f.markedForDelete);
    errorSpan.style.display = hasActiveFiles ? "none" : "block";
    errorSpan.innerHTML = hasActiveFiles ? "" : '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="please-upload-at-least-one-file" /></span>';
}

function renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput) {
    let uploadedFiles = uploadedFilesAttachments;

    previewList.innerHTML = "";

    uploadedFiles.forEach((fileObj, index) => {
        if (fileObj.markedForDelete) return;

        const fileItem = document.createElement("li");
        fileItem.className = "list-group-item d-flex justify-content-between align-items-center";

        const fileLink = document.createElement("a");
        fileLink.href = fileObj.isExisting ? (fileObj.url || "#") : URL.createObjectURL(fileObj.file);
        fileLink.textContent = fileObj.name;
        fileLink.target = "_blank";
        fileLink.style.cssText = "flex-grow: 1; text-decoration: none; word-wrap: break-word; white-space: nowrap; overflow: hidden; max-width: 200px;";

        const deleteBtn = document.createElement("button");
        deleteBtn.type = "button";
        deleteBtn.className = "btn btn-danger btn-sm";
        deleteBtn.innerHTML = '<i class="bi bi-x-circle-fill"></i>';
        deleteBtn.onclick = () => removeFile(
            index,
            previewContainer.id,
            previewList.id,
            errorSpan.id,
            hiddenInput.id,
            inputId
        );

        fileItem.appendChild(fileLink);
        fileItem.appendChild(deleteBtn);
        previewList.appendChild(fileItem);
    });

    previewContainer.style.display = uploadedFiles.some(f => !f.markedForDelete) ? "block" : "none";
    
    hiddenInput.value = uploadedFiles
        .filter(f => !f.markedForDelete)
        .map(f => f.name)
        .join(',');
}
// Multiple File Upload

</script>