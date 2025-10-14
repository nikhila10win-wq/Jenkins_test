<%@page import="mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand"%>
<%@page import="mhdsys.sports.coaching.wing.portlet.mvc.SaveSportCoachingWingMVCResourceCommand"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@ include file="init.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" defer />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js" defer></script>

  
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
	<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    
    <style>
    		.newTabs .error-container{
    			color: red;
    		}

        .newTabs .form-input, .newTabs .form-control {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid rgba(37, 37, 141, 0.32);
    		border-radius: 5px;
    		height: 45px;
    		font-size: 13px;
    		font-family: Poppins-Meduim;
        }
        .newTabs .form-textarea {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid rgba(37, 37, 141, 0.32);
    		border-radius: 5px;
    		font-size: 13px;
    		font-family: Poppins-Meduim;
        }
        .newTabs .form-input:focus,.newTabs .form-control:focus,.newTabs .form-textarea:focus {
            outline: none;
            border-color: #3b82f6;
            box-shadow: 0 0 0 2px #bfdbfe;
        }
        .newTabs .form-attachment {
            display: block;
            width: 100%;
            padding: 0.75rem;
            border: 1px dashed #d1d5db;
            border-radius: 0.5rem;
            background-color: #f9fafb;
            cursor: pointer;
        }
        .newTabs .attachment-group .form-label {
        	margin: 0px;
    		color: #26268E;
    		font-family: Poppins-Semibold;
    		font-size: 13px;
        }
       
       .newTabs .attachment-group {
            border: 1px solid #e5e7eb;
            padding: 1rem;
            border-radius: 0.5rem;
            margin-top: 1rem;
        }
        /* Styles for validation errors */
        .newTabs .error {
            color: #dc2626; /* red-600 */
            font-size: 0.875rem; /* text-sm */
            margin-top: 0.25rem;
        }
       
		.select2-container .select2-selection--single .select2-selection__rendered {
		    display: block;
		    overflow: hidden;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		}
		.select2-container--default .select2-selection--single .select2-selection__arrow b {
		    border-color: #888 transparent transparent transparent;
		    border-style: solid;
		    border-width: 5px 4px 0 4px;
		    height: 0;
		    left: 50%;
		    margin-left: -8px;
		    margin-top: -2px;
		    position: absolute;
		    top: 90%;
		    width: 0;
		}
		.select2-container--default .select2-selection--single {
		    background-color: #fff;
		    border: 1px solid #aaa;
		    border-radius: 4px;
		    height: 45px;
		    padding-top: 0.55rem;
            padding-left: 0.25rem;
		    font-size: 13px;
		}
		.select2-container--default .select2-selection--single .select2-selection__rendered {
           color: #000000;
          /* line-height: 28px; */
         }
         .select2-container--default .select2-selection--single .select2-selection__placeholder {
		    color: #000;
		 }
		.select2-container {
		  width: 100% !important;
		}
		
    </style>

<%
//List<DivisionMaster>divisionMaster=SportsCoachingWingUtil.getDivision();
//System.out.println("divisionMaster ::"+divisionMaster);
//SportsCoachingWingUtil.getCoachList(themeDisplay.getCompanyId());
%>

<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.SAVE_CAOCHIN_WING_RESOURCE_URL%>" var="saveCoachingWingURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_DISTRICT_BY_DIVISION%>" var="getDistrictByDivisionURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_ALL_SPORTS_PRESONS%>" var="getallSportsPersonsURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_ALL_COACH%>" var="getallCoachURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_SPORTS_PERSONS%>" var="getSportsPersonsURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_COACH%>" var="getCoachURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_ALL_DISTRICT_URL%>" var="getallDistrictURL" />

 <%-- <header class="text-center mb-8">
                <h1 class="text-3xl font-bold text-gray-800"><liferay-ui:message key="sports-training-centre-information" /></h1>
                <p class="text-gray-600 mt-2"><liferay-ui:message key="form-to-be-filled-by-the-district-sports-officer" /></p>
</header> --%>
<%--  <form id="trainingCentreForm" method="POST" enctype="multipart/form-data"  action="${saveTrainingCenterActionCommand}"> --%>
 <form id="trainingCentreForm" method="POST" enctype="multipart/form-data"  action="${saveTrainingCenterActionCommand}">
<div class="common-forms-div">
    <div class="container my-5 newTabs">
        <div class="row">
        <!--  <div class="col-md-12"> -->
            <div class="border-0 card shadow">
                <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
                    <h5 class="mb-0"><liferay-ui:message key="sports-training-centre-information" /></h5>
                    <div>
						<!-- <div class="col-12 d-flex justify-content-end mb-2"> --> 
									<a  href="/group/guest/sports-coaching-wing-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn mr-1"><i class="bi bi-arrow-left-circle mr-1"></i><liferay-ui:message key="back"/></a>
						<!-- </div> -->
					</div>	
                </div>
                <div class="card-body">
                     <ul class="nav nav-pills nav-justified shadow-sm mb-4 p-2" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active border-0" id="existingtrainingcentredetails-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#existingtrainingcentredetails">
                               <liferay-ui:message key="existing-training-centre-details" /> 
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link border-0" id="coach-detail-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#coach-detail">
                                <liferay-ui:message key="coach-detail" /> 
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link border-0" id="sports-person-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#sports-person">
                               <liferay-ui:message key="sports-person-detail" /> 
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link border-0" id="servant-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#servant">
                               <liferay-ui:message key="servant-details" /> 
                            </button>
                        </li>
                         <li class="nav-item" role="presentation">
                            <button class="nav-link border-0" id="financial-details-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#financial-details">
                              <liferay-ui:message key="financial-details" />
                            </button>
                        </li>
                         <li class="nav-item" role="presentation">
                            <button class="nav-link border-0" id="reports-and-attachments-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#reports-and-attachments">
                               <liferay-ui:message key="reports-and-attachments" />
                            </button>
                        </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                     <input class="trainingCenterId" type="hidden" name="trainingCenterId" id="trainingCenterId" value="${trainingCentre.trainingCentreId}"/>
                        <!-- Existing Training Centre Details -->
                        <%@include file="trainingcenter.jsp" %>
                        
                        <!-- coach-details -->
                        <%@include file="coach-details.jsp" %>
                        
                        <!-- sports-person -->
                         <%@include file="sports-person.jsp" %>
                         
                         <!-- servant -->
                         <%@include file="servant.jsp" %>
                         
                          <!-- financial-details -->
                         <%@include file="financial-details.jsp" %>
                         
                       		 <!-- reports-and-attachments -->
                         <%@include file="reports-and-attachments.jsp" %>
                         
                        <%--  <!-- Save -->
                         <div class="card-footer bg-transparent text-right p-4">
	                          <button type="button" class="btn btn-primary" onclick="saveTrainingCenterDetails(event,'save','reports')">
	                                <liferay-ui:message key="save" />
	                          </button>
                          </div> --%>
                    </div>
                </div>
            </div>
            
            
            
            
           <!--  </div> -->
        </div>
    </div>
</div>
</form>
<script>
	var mode = '${mode}';
   	var maxRequestedAmount1=0;
   	var maxRequestedAmount2=0;
   	var maxRequestedAmount3=0;
   	var maxRequestedAmount12=0;
   	
  	var coachCount = 1;
   	var sportsPersonCount = 1;
   	
   	//Financial Details Variable
    var totalRequestedAmount=0;
    var reqAmount1=0;
    var reqAmount2=0;
    var reqAmount3=0;
    var reqAmount4=0;
    var reqAmount5=0;
    var reqAmount6=0;
    var reqAmount7=0;
    var reqAmount8=0;
    var reqAmount9=0;
    var reqAmount10=0;
    var reqAmount11=0;
    var reqAmount12=0;
   	
  //Single file upload
   	function handleFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn) {
	  
   	    $("#dprDocPath").val(0);
	  
   	    const fileInput = event.target;
   	    const file = fileInput.files[0]; // Get the uploaded file
   	    const previewContainer = document.getElementById(filePreviewContainer);
   	    const previewLink = document.getElementById(filePreviewLink);
   	    const deleteButton = document.getElementById(deleteBtn);

   	    previewContainer.classList.add('d-none');
   	    previewContainer.classList.remove('d-flex');
   	    previewLink.textContent = '';
   	    previewLink.href = '';
   	    deleteButton.dataset.filename = '';
   	    
   	    if (file && $('#' + id).val() !== '' && (typeof $('#' + id).valid === 'function' ? $('#' + id).valid() : true)) {   
   	        const fileName = file.name;

   	        previewContainer.classList.remove('d-none');
   	        previewContainer.classList.add('d-flex');

   	        previewLink.textContent = fileName;
   	        previewLink.href = URL.createObjectURL(file); 
   	        previewLink.target = "_blank";
   	        
   	        Object.assign(previewLink.style, {
   	            textDecoration: "none",
   	           // wordWrap: "break-word",
   	           // whiteSpace: "normal",
   	          //  overflow: "hidden",
   	          //  maxWidth: "200px"
   	        });

   	        deleteButton.dataset.filename = fileName; // Store file name in button dataset
   	    }
   	}
   	
   	var tabValidationRules = {
   			existingtrainingcentredetails: [
   		        "#division", "#district"
   		    ],
   		    workDetails:[
   		    	"#attestedCopyUpload", "#constitutionUpload", "#officersListUpload"
   		    ]
   		};
   	
   	function validateTabFields(tabId) {
   	    $('#trainingCentreForm').validate().resetForm();
   	    let isValid = true;

   	    const fields = tabValidationRules[tabId] || [];

   	    fields.forEach((selector) => {
   	        if (!$(selector).valid()) {
   	            isValid = false;
   	        }
   	    });

   	    return handleValidationResult(isValid, tabId);
   	}

   	function handleValidationResult(isValid, nextTabId) {
   		
   		//console.log("isValid :::" +isValid);
   		
   	    if (isValid) {
   	        setActiveTab1(nextTabId);
   	        return true;
   	    } else {
   	        $('html, body').animate({
   	            scrollTop: $(".error:visible").first().offset().top - 100
   	        }, 500);
   	        return false;
   	    }
   	}
   	
   	function validateNumber(elem) {
   		//console.log("validate number called");
     	var validNumber = new RegExp(/^\d*\.?\d*$/);
     	var lastValid = elem.value;
     	//console.log("validNumber.test(elem.value) :::" +validNumber.test(elem.value));
       if (validNumber.test(elem.value)) {
         lastValid = elem.value;
       } else {
         elem.value = "";
       }
     }
   	
    $(document).ready(function () {
    	 //console.log("mode ::"+mode)
    	//Tab function
    	 if(mode=='view'){
	    	$('.nav-link').click(function(e) {
	    		var targetTabId = $(this).attr('id');
	            //console.log(targetTabId)
	            // Remove active classes
	            $('.nav-link').removeClass('active');
	            $('.tab-pane').removeClass('show active');
	            // Activate the clicked tab
	            $(this).addClass('active');
	            $('#' + targetTabId.replace('-tab', '')).addClass('show active');
	            //scrollToTop();
	    	});
    	}else{
    		
    		$('.nav-link').click(function(e) {
    		    var targetTabId = $(this).attr('id');
    		    console.log("Inside else tab switch event:::");
    		    console.log("Tab id in nav::::"+targetTabId);
    		    
    		    switch (targetTabId) {
    	        case 'coach-detail-tab':
    	            var errorMessageETC=validateTrainingCenter('existingtrainingcentredetails');
    	        	if (errorMessageETC.length>0) return e.preventDefault();
    	            break;
    	        case 'sports-person-tab':
    	        	var errorMessageETC=validateTrainingCenter('existingtrainingcentredetails');
    	        	var errorMessageCDT=validateCoachDetail("coach-detail");
    	        	if (errorMessageETC.length>0 || errorMessageCDT.length>0) return e.preventDefault();
    	            break;
    	        case 'servant-tab':
    	        	var errorMessageETC=validateTrainingCenter('existingtrainingcentredetails');
    	        	var errorMessageCDT=validateCoachDetail("coach-detail");
    	        	var errorMessageSP=validateSportsPerson("sports-person");
    	        	if (errorMessageETC.length>0 || errorMessageCDT.length>0 || errorMessageSP.length>0) return e.preventDefault();
    	            break;
    	        case 'financial-details-tab':
    	        	var errorMessageETC=validateTrainingCenter('existingtrainingcentredetails');
    	        	var errorMessageCDT=validateCoachDetail("coach-detail");
    	        	var errorMessageSP=validateSportsPerson("sports-person");
    	        	var errorMessageSER=validateServant("servant");
    	        	if (errorMessageETC.length>0 || errorMessageCDT.length>0 || errorMessageSP.length>0 || errorMessageSER.length>0) return e.preventDefault();
    	            break;
    	        case 'reports-and-attachments-tab':
    	        	var errorMessageETC=validateTrainingCenter('existingtrainingcentredetails');
    	        	var errorMessageCDT=validateCoachDetail("coach-detail");
    	        	var errorMessageSP=validateSportsPerson("sports-person");
    	        	var errorMessageSER=validateServant("servant");
    	        	if (errorMessageETC.length>0 || errorMessageCDT.length>0 || errorMessageSP.length>0 || errorMessageSER.length>0) return e.preventDefault();
    	            break; 
    	    }
    		    
    		 console.log($('#' + targetTabId.replace('-tab', '')).length);
    		 if ($('#' + targetTabId.replace('-tab', '')).length) {
                 // Remove active classes
                 $('.nav-link').removeClass('active');
                 $('.tab-pane').removeClass('show active');

                 // Activate the clicked tab
                 $(this).addClass('active');
                 console.log("targetTabId :::"+targetTabId);
                 
                 if(targetTabId=='financial-details-tab'){
                	 //calculateFinancialDetailsByTab();
                 }
                 
                 
                 console.log(targetTabId.replace('-tab', ''));
                 
                 $('#' + targetTabId.replace('-tab', '')).addClass('show active');

                 if (typeof scrollToTop === "function") {
                     scrollToTop();
                 }
             } else {
                 e.preventDefault();
             }
    		 
    		});
    	}
    	 
    	 //validate form 
    	 
        // --- Initialize Containers and Counters ---
        const coachesContainer = $('#coachesContainer');
        const sportsPersonsContainer = $('#sportsPersonsContainer');
        const financialDetailsContainer = $('#financialDetailsContainer');
        const reportsContainer = $('#reportsContainer');

        // --- Initialize jQuery Validator ---
        const validator = $('#trainingCentreForm').validate({
            errorElement: 'div',
            errorClass: 'error',
            errorPlacement: function(error, element) {
                if (element.attr("name") == "centre_type") {
                 //   error.appendTo("#centreTypeContainer");
                } else if (element.is(':radio')) {
                  //  error.insertAfter(element.closest('.flex'));
                }
                else {
                    //error.insertAfter(element);
                }
            }/* ,
            submitHandler: function(form) {
               // console.log('Form is valid and submitted!');
               // alert('Form submitted successfully! (Check console for data)');
            } */
        });

        // --- Add Rules for Static Fields ---
        $('#division').rules('add', { required: true, messages: { required: "Please select a division." } });
        $('#district').rules('add', { required: true, messages: { required: "Please select a district." } });
        $('input[name="centre_type"]').rules('add', { required: true, messages: { required: "Please select a centre type." } });
        $('#sports_type').rules('add', { required: true, messages: { required: "Please select a sports type." } });
        $('#gis_map').rules('add', { url: true, messages: { url: "Please enter a valid URL." } });
        
        // remove coach form
        $(document).on('click','.remove-coach-btn',function(){
        	//console.log("remove coach clicked :::");
        	--coachCount;
        	$('#coachCount').val(coachCount);
        	maxRequestedAmount1=coachCount*50000;
        	$("#req_amount_1").val(maxRequestedAmount1);
        });
        
        // --- Coach Details ---
        function addCoach() {
        	if(coachCount<6){
        		var removebtnhtml="";
        		if(coachCount>1){
        			removebtnhtml='<button type="button" class=" remove-coach-btn remove-btn"><i class="bi bi-x-circle text-danger"></i></button>';	
        		}
        		
                const coachHtml = '<div class="coach-entry">'+
                					'<div class="text-right">'+removebtnhtml+'</div>'+
                					'<div class="row">'+
                					'<div class="col-md-6">'+
                					 '<div class="form-group">'+
                					 '<label for="coachselect_'+coachCount+'" class="form-label"><liferay-ui:message key="select-coach" /><sup class="text-danger">*</sup></label>'+
                					 '<select class="form-control coachcls coachselect" id="coachselect_'+coachCount+'" name="coachselect_'+coachCount+'"></select>'+
                					 ' <p class="error-container" id="coach-error_'+coachCount+'"></p>'+
                					 '</div>'+
                					'</div>'+
                					'<div class="col-md-6">'+
	               					 '<div class="form-group">'+
	               						'<label class="form-label"><liferay-ui:message key="type" /><sup class="text-danger">*</sup></label>'+
		                                '<div class="flex gap-4">'+
		                                   '<label class="flex items-center"><input type="radio" name="coach_type_'+coachCount+'" id="government_'+coachCount+'" value="government" class="mr-2 coachtypecls"> Government</label>'+
		                                   '<label class="flex items-center"><input type="radio" name="coach_type_'+coachCount+'" id="private_'+coachCount+'" value="private" class="mr-2  coachtypecls"> Private</label>'+
		                                   '</div>'+
		                                   ' <p class="error-container" id="coachtype-error_'+coachCount+'"></p>'+
	               					'</div>'+
               					'</div>'+
               					'<div class="col-md-6">'+
	              					 '<div class="form-group">'+
	              						'<label for="coach_name_'+coachCount+'" class="form-label"><liferay-ui:message key="coach-full-name" /><sup class="text-danger">*</sup></label>'+
	                               		 '<input type="text" id="coach_name_'+coachCount+'" name="coach_name_'+coachCount+'" class="form-input coachNamecls" placeholder="Enter full name" onkeydown="return /[a-zA-Z .]/i.test(event.key)" maxlength="75">'+
	                               		'<p class="error-container" id="coachname-error_'+coachCount+'"></p>'+
	                               		 '</div>'+
          						'</div>'+
          						
          						'<div class="col-md-6">'+
	             					 '<div class="form-group">'+
	             					 	'<label for="coach_mobile_'+coachCount+'" class="form-label"><liferay-ui:message key="mobile-number" /><sup class="text-danger">*</sup></label>'+
	                                	'<input onkeypress="if(this.value.length==10) return false;" type="tel" id="coach_mobile_'+coachCount+'" name="coach_mobile_'+coachCount+'" class="form-input mobcls" placeholder="Enter mobile number" maxlength="10">'+
	                                	' <p class="error-container" id="coachmobile-error_'+coachCount+'"></p>'+
	                                	'</div>'+
     							'</div>'+
     							
     							'<div class="col-md-6">'+
	            					 '<div class="form-group">'+
	            					 	'<label for="coach_email_'+coachCount+'" class="form-label"><liferay-ui:message key="email" /><sup class="text-danger">*</sup></label>'+
	                                 	'<input type="email" id="coach_email_'+coachCount+'" name="coach_email_'+coachCount+'" class="form-input emailcls" placeholder="Enter email address">'+
	                                 	' <p class="error-container" id="coachemail-error_'+coachCount+'"></p>'+
	                                 	'</div>'+
								'</div>'+
								
								'<div class="col-md-6">'+
	           						 '<div class="form-group">'+
	           					 		'<label for="coach_sports_name_'+coachCount+'" class="form-label"><liferay-ui:message key="sports-name" /><sup class="text-danger">*</sup></label>'+
	                             		'<input type="text" id="coach_sports_name_'+coachCount+'" name="coach_sports_name_'+coachCount+'" class="form-input coachsportsCls" placeholder="e.g., Archery, Boxing" maxlength="75">'+
	                             		' <p class="error-container" id="coachsports-error_'+coachCount+'"></p>'+
	                             		'</div>'+
								'</div>'+
								
								'<div class="col-md-6">'+
	          						 '<div class="form-group">'+
	          						 	'<label for="coach_address_'+coachCount+'" class="form-label"><liferay-ui:message key="residence-address" /><sup class="text-danger">*</sup></label>'+
	                                 	'<textarea id="coach_address_'+coachCount+'" name="coach_address_'+coachCount+'" rows="2" class="form-textarea coachaddresscls" placeholder="Enter residence address" maxlength="250"></textarea>'+
	                                 	' <p class="error-container" id="coach_address_-error_'+coachCount+'"></p>'+
	                                 	'</div>'+
								'</div>'+
								
								'<div class="col-md-6">'+
	         						 '<div class="form-group">'+
		         						 	'<label class="form-label"><liferay-ui:message key="sports-uniform-size" /><sup class="text-danger">*</sup><span class="ml-2"><i class="bi bi-chat-square-text"></i></span></label>'+
			                                 '<div class="grid grid-cols-2 md:grid-cols-4 gap-4">'+
			                                 '<label class="form-label"><liferay-ui:message key="tracksuit-size"/><sup class="text-danger">*</sup></label>'+  
			                                 	'<input type="text" id="tracksuit_size_'+coachCount+'"  name="tracksuit_size_'+coachCount+'" class="form-input coachtracksuitcls" placeholder="Tracksuit" maxlength="5">'+
			                                     ' <p class="error-container" id="tracksuit_size_-error_'+coachCount+'"></p>'+
			                                     '<label class="form-label"><liferay-ui:message key="tshirt-size"/><sup class="text-danger">*</sup></label>'+ 
			                                     '<input type="text" id="tshirt_size_'+coachCount+'" name="tshirt_size_'+coachCount+'" class="form-input coachtshirsscls" placeholder="T-shirt" maxlength="5">'+
			                                     ' <p class="error-container" id="tshirt_size_-error_'+coachCount+'"></p>'+
			                                     '<label class="form-label"><liferay-ui:message key="shorts-size"/><sup class="text-danger">*</sup></label>'+
			                                     '<input type="text" id="shorts_size_'+coachCount+'" name="shorts_size_'+coachCount+'" class="form-input coachshortscls" placeholder="Shorts" maxlength="5">'+
			                                     ' <p class="error-container" id="shorts_size_-error_'+coachCount+'"></p>'+
			                                     '<label class="form-label"><liferay-ui:message key="shoes-size"/><sup class="text-danger">*</sup></label>'+
			                                     '<input type="text" id="shoes_size_'+coachCount+'" name="shoes_size_'+coachCount+'" oninput="validateNumber(this);" class="form-input coachshoescls" placeholder="Shoes" maxlength="5">'+
			                                     ' <p class="error-container" id="shoes_size-error_'+coachCount+'"></p>'+
			                                 '</div>'+
										'</div>'+
								'</div>'+
							
								'<div class="col-md-6">'+
		     						 '<div class="form-group">'+
		     						 	'<label for="coachdistrictselect_'+coachCount+'" class="form-label"><liferay-ui:message key="district" /><sup class="text-danger">*</sup></label>'+
		     						 	'<select class="form-control coachdistrictcls coachdistrictselect" id="coachdistrictselect_'+coachCount+'" name="coachdistrictselect_'+coachCount+'"></select>'+
		     						 	 '<p class="error-container" id="coachdistrictselect_-error_'+coachCount+'"></p>'+
		     						 	'</div>'+
								'</div>'+	
								
							'<div class="col-md-6">'+
	     						 '<div class="form-group">'+
	     						 	'<label for="coach_remarks_'+coachCount+'" class="form-label"><liferay-ui:message key="remarks-by-dso" /></label>'+
	                                '<textarea id="coach_remarks_'+coachCount+'" name="coach_remarks_'+coachCount+'" rows="2" class="form-textarea coachremarkscls" placeholder="Enter any remarks" maxlength="250"></textarea>'+
	                                '<p class="error-container" id="coachdsoremarks_error_'+coachCount+'"></p>'+
	                             '</div>'+
							'</div>'+
                    '</div>';
                coachesContainer.append(coachHtml);
                getAllCoach("coachselect_"+coachCount);
                getAllDistrict("coachdistrictselect_"+coachCount);
                
                // Add validation rules for the new coach fields
                $('input[name="coach_type_'+coachCount+'"]').rules('add', { required: true, messages: { required: 'Please select coach type.' } });
                //$('#coach_name_'+coachCount).rules('add', { required: true, messages: { required: 'Please enter a name.' } });
                //$('#coach_mobile_'+coachCount).rules('add', { required: true, digits: true, minlength: 10, maxlength: 10, messages: { required: 'Mobile number is required.', digits: 'Please enter only digits.', minlength: 'Must be 10 digits.', maxlength: 'Must be 10 digits.' } });
            	$('#coachCount').val(coachCount);
        	}else{
        		alert("Can't add more than 5");
        	}
        }
        
        
        $(document).on("input",'.mobcls', function() {
    	 	var currentval=$(this).val();
    	 	var newval=currentval.replace(/\D+/g, '');
    	 	$(this).val(newval);
    	});
        
    	 /*$('#coach_name_1').on("input", function() {
    	 	var currentval=$(this).val();
    	 	$(this).val($.trim(currentval));
    	}); */
        
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        $(document).on("keyup",'.emailcls', function() {
    	 	var currentval=$(this).val();
    	 	//console.log("valida email ::"+regex.test(currentval));
    	 	var currentId=$(this).attr('id');
    	 	var tempEmailCount=currentId.split("_");
	 		//console.log("id ::" +currentId);
	 		//console.log("tempEmailCount ::" +tempEmailCount[2]);
    	 	if(!regex.test(currentval)){
    	 		document.getElementById("coachemail-error_"+tempEmailCount[2]).textContent = "<liferay-ui:message key='please-enter-valid-email' />";
    			//errorMessage.push("<liferay-ui:message key='please-select-gis-map-location' />");
    	 	}else{
    	 		document.getElementById("coachemail-error_"+tempEmailCount[2]).textContent = "";
    	 	}
    	});
        
        
        // remove sports person form
        $(document).on('click','.remove-sports-person-btn',function(){
        	--coachCount;
        	$('#sportsPersonCount').val(coachCount);
        });
        
        
        function getAllDistrict(id){
        	//console.log("getAllDistrict :::id :::" +id);
        	$.ajax({
     			type: "POST",
     			datatype:"json",
     	        url: '${getallDistrictURL}', 
     	        data: {
     	        },
     			async : false,
     	        success: function(data) {
     	            	//console.log("inside  success  getAllDistrict :: "+data);
     	            	var response=JSON.parse(data);
     	            	if(response!=null && response.length>0){
     	            		var coachhtml="";
     	            		$.each(response,function(k,v){
     	            			//console.log("sports k::",k);
     	            			//console.log("sports v::",v);
     	            			coachhtml=coachhtml+'<option value="'+v.id+'">'+v.name+'</option>'
     	            		});
     	            		
     	            		//$('#sportspersonselect_1').html('').append('<option>-select-</option>').append(sportsPersontml);
     	            		$('#'+id).html('').append('<option value=""><liferay-ui:message key="select-district" /></option>').append(coachhtml);
     	            	}
     	        },error:function(resp){
     	        	//console.log("inside  division error :: ");
     	        }
     		});
        }
        
        
        
		function getAllCoach(id){
        	//console.log("getAllCoach id :::" +id);
        	$.ajax({
     			type: "POST",
     			datatype:"json",
     	        url: '${getallCoachURL}', 
     	        data: {
     	        },
     			async : false,
     	        success: function(data) {
     	            	//console.log("inside  sports persons  succeess :: "+data);
     	            	var response=JSON.parse(data);
     	            	if(response!=null && response.length>0){
     	            		var coachhtml="";
     	            		$.each(response,function(k,v){
     	            			//console.log("sports k::",k);
     	            			//console.log("sports v::",v);
     	            			coachhtml=coachhtml+'<option value="'+v.id+'">'+v.name+'</option>'
     	            		});
     	            		
     	            		//$('#sportspersonselect_1').html('').append('<option>-select-</option>').append(sportsPersontml);
     	            		$('#'+id).html('').append('<option value=""><liferay-ui:message key="select-coach" /></option>').append(coachhtml);
     	            	}
     	        },error:function(resp){
     	        	//console.log("inside  division error :: ");
     	        }
     		});
        }
        
        
        //Get Default sports person in dropdown
        //getAllSportsPersons(sportspersonselect_1);
        
        function getAllSportsPersons(id){
        	
        	//console.log("id :::" +id);
        	
        	$.ajax({
     			type: "POST",
     			datatype:"json",
     	        url: '${getallSportsPersonsURL}', 
     	        data: {
     	        },
     			async : false,
     	        success: function(data) {
     	            	//console.log("inside  sports persons  succeess :: "+data);
     	            	var response=JSON.parse(data);
     	            	if(response!=null && response.length>0){
     	            		var sportsPersontml="";
     	            		$.each(response,function(k,v){
     	            			//console.log("sports k::",k);
     	            			//console.log("sports v::",v);
     	            			sportsPersontml=sportsPersontml+'<option value="'+v.id+'">'+v.name+'</option>'
     	            		});
     	            		
     	            		//$('#sportspersonselect_1').html('').append('<option>-select-</option>').append(sportsPersontml);
     	            		$('#'+id).html('').append('<option value=""><liferay-ui:message key="select-sports-person" /></option>').append(sportsPersontml);
     	            	}
     	        },error:function(resp){
     	        	//console.log("inside  division error :: ");
     	        }
     		});
        }
        
        
        // --- Sports Person Details ---
        function addSportsPerson() {
        	 var today = new Date();
         	var year = today.getFullYear();
         	var month = String(today.getMonth() + 1).padStart(2, '0'); // Months are 0-indexed
         	var day = String(today.getDate()).padStart(2, '0');
         	var formattedDate = year+'-'+month+'-'+day;
           //  console.log("formattedDate :::" +formattedDate);
        	if(sportsPersonCount<51){
        	//	console.log("Inside add sports person ");
        		var removebtnhtml="";
        		if(sportsPersonCount>1){
        			removebtnhtml='<button type="button" class=" remove-sports-person-btn remove-btn"><i class="bi bi-x-circle text-danger"></i></button>';	
        		}
        		
        		const personHtml = '<div class="coach-entry ">'+
        		'<div class="text-right">'+removebtnhtml+'</div>'+
        		'<div class="row">'+

        			'<div class="col-md-6">'+
	 					 '<div class="form-group">'+
	 						'<label for="sportspersonselect_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="select-sports-person" /><sup class="text-danger">*</sup></label>'+
	 						'<select class="form-control soprts-personscls sportspersonselect" id="sportspersonselect_'+sportsPersonCount+'" name="sportspersonselect_'+sportsPersonCount+'"></select>'+
	 						'<p class="error-container" id="sportspersonselect_-error_'+sportsPersonCount+'"></p>'+	
	 						'</div>'+
					'</div>'+
        			
					'<div class="col-md-6">'+
						 '<div class="form-group">'+
						 	'<label for="sp_name_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="full-name" /><sup class="text-danger">*</sup></label>'+
	                     	'<input type="text" id="sp_name_'+sportsPersonCount+'" name="sp_name_'+sportsPersonCount+'" class="form-input spfnamecls" placeholder="Enter full name">'+
	                     	'<p class="error-container" id="sportspersonfullname_-error_'+sportsPersonCount+'"></p>'+
	                     	'</div>'+
					'</div>'+
        			
					'<div class="col-md-6">'+
						 '<div class="form-group">'+
						 	'<label for="sp_dob_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="dob" /><sup class="text-danger">*</sup></label>'+
	                     	'<input type="date" id="sp_dob_'+sportsPersonCount+'" name="sp_dob_'+sportsPersonCount+'" class="form-input spdobcls" max='+formattedDate+'>'+
	                     	'<p class="error-container" id="sportspersondob_-error_'+sportsPersonCount+'"></p>'+
						'</div>'+
					'</div>'+
					
					'<div class="col-md-6">'+
						 '<div class="form-group">'+
						 	'<label for="sp_school_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="school-name" /><sup class="text-danger">*</sup></label>'+
	                     	'<input type="text" id="sp_school_'+sportsPersonCount+'" name="sp_school_'+sportsPersonCount+'" class="form-input spschoolcls" placeholder="Enter school name">'+
	                     	'<p class="error-container" id="sportspersonschoolname_-error_'+sportsPersonCount+'"></p>'+
	                     	'</div>'+
					'</div>'+
					
					'<div class="col-md-6">'+
						 '<div class="form-group">'+
						 	'<label for="sp_mobile_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="mobile-number" /><sup class="text-danger">*</sup></label>'+
	                     	'<input onkeypress="if(this.value.length==10) return false;" type="tel" id="sp_mobile_'+sportsPersonCount+'" name="sp_mobile_'+sportsPersonCount+'" class="form-input spemobcls" placeholder="Enter mobile number" maxlength="10">'+
	                     	'<p class="error-container" id="sportspersonmobile_-error_'+sportsPersonCount+'"></p>'+
	                     	'</div>'+
					'</div>'+
					
					'<div class="col-md-6">'+
					 	'<div class="form-group">'+
						 	'<label for="sp_address_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="residence-address" /><sup class="text-danger">*</sup></label>'+
                     		'<textarea id="sp_address_'+sportsPersonCount+'" name="sp_address_'+sportsPersonCount+'" rows="2" class="form-textarea spaddresscls" placeholder="Enter residence address"></textarea>'+
                     		'<p class="error-container" id="sportspersonaddress_-error_'+sportsPersonCount+'"></p>'+
                     		'</div>'+
					'</div>'+
					
					'<div class="col-md-6">'+
					 	'<div class="form-group">'+
					 	 	'<label for="sp_entry_date_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="entry-date" /><sup class="text-danger">*</sup></label>'+
	                     	'<input type="date" id="sp_entry_date_'+sportsPersonCount+'" name="sp_entry_date_'+sportsPersonCount+'" class="form-input spedcls" max='+formattedDate+'>'+
	                     	'<p class="error-container" id="sportspersonentrydate_-error_'+sportsPersonCount+'"></p>'+
	                     	'</div>'+
					'</div>'+
					
					'<div class="col-md-6">'+
					 	'<div class="form-group">'+
					 	 	 '<label for="sp_ranking_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="ranking" /><sup class="text-danger">*</sup></label>'+
	                    	 
					 	 	/*  '<input type="text" id="sp_ranking_'+sportsPersonCount+'" name="sp_ranking_'+sportsPersonCount+'" class="form-input sprankingcls" placeholder="Enter ranking" maxlength="5">'+ */
	                    	 '<select class="form-control sprankingcls rankingselect" id="sp_ranking_'+sportsPersonCount+'" name="sp_ranking_'+sportsPersonCount+'">'+
	                    	 	'<option value=""><liferay-ui:message key="select-ranking" /></option>'+
	                    	 	'<option value="gold">Gold</option>'+
	                    	 	'<option value="silver">Silver</option>'+
	                    	 	'<option value="bronze">Bronze</option>'+
	                    	 '</select>'+
					 	 	 '<p class="error-container" id="sportspersonranking_-error_'+sportsPersonCount+'"></p>'+
	                    	 '</div>'+
					'</div>'+
					
					'<div class="col-md-6">'+
					 	'<div class="form-group">'+
					 	 	'<label for="sp_achievement_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="achievement-level" /><sup class="text-danger">*</sup></label>'+
	                     	/* '<textarea id="sp_achievement_'+sportsPersonCount+'" name="sp_achievement_'+sportsPersonCount+'" rows="2" class="form-textarea spachievementcls" placeholder="e.g., district, division, state, national, international"></textarea>'+ */
	                     	'<select id="sp_achievement_'+sportsPersonCount+'" name="sp_achievement_'+sportsPersonCount+'" rows="2" class="form-textarea spachievementcls">'+
	                     	'<option value=""><liferay-ui:message key="select-achievement" /></option>'+
	                     	'<option value="Taluka">Taluka</option>'+
	                     	'<option value="district">District</option>'+
	                     	'<option value="divisonal">Divisonal</option>'+
	                     	'<option value="state">State</option>'+
	                     	'<option value="national">National</option>'+
	                     	'<option value="international">International</option>'+
	                     	'</select>'+
	                     	'<p class="error-container" id="sp_achievement_-error_'+sportsPersonCount+'"></p>'+
	                     	'</div>'+
					'</div>'+
					
					'<div class="col-md-6">'+
				 	'<div class="form-group">'+
				 	 	'<label for="sp_remarks_'+sportsPersonCount+'" class="form-label"><liferay-ui:message key="remarks-by-coach" /></label>'+
                     	'<textarea id="sp_remarks_'+sportsPersonCount+'" name="sp_remarks_'+sportsPersonCount+'" rows="2" class="form-textarea spcremarkscls" placeholder="Enter coach remarks"></textarea>'+
                     	'<p class="error-container" id="spc_remarks_error_'+sportsPersonCount+'"></p>'+
                     	'</div>'+
				'</div>'+
        			
        		'</div>'+
                    '</div>';
                sportsPersonsContainer.append(personHtml);
                getAllSportsPersons("sportspersonselect_"+sportsPersonCount);   
                // Add validation rules for new sports person fields
                //$('#sp_name_'+sportsPersonCount).rules('add', { required: true, messages: { required: 'Please enter a name.' } });
                $('#sp_dob_'+sportsPersonCount).rules('add', { required: true, messages: { required: 'Please select a date of birth.' } });
                //$('#sp_mobile_'+sportsPersonCount).rules('add', { required: true, digits: true, minlength: 10, maxlength: 10, messages: { required: 'Mobile number is required.', digits: 'Please enter only digits.', minlength: 'Must be 10 digits.', maxlength: 'Must be 10 digits.' } });
                $('#sportsPersonCount').val(sportsPersonCount);
        	}
        }
        
       
        // --- Financial Details ---
        const financialItems = [
            { desc: "Sports material and sports training equipment (5 sports Coaches at Rs. 50,000/- each)", gr_amount: "250000" },
            { desc: "5 sports Coaches will provide track suits, tshirts, shorts, shoes etc. to 50 players each who practice regularly at the center (55 x Rs. 1500/-)", gr_amount: "412500" },
            { desc: "Honorarium of 3 part time maidan servants (Rs. 6000/- x 12 x 3)", gr_amount: "216000" },
            { desc: "Sports literature, CDs, Books, periodicals, Monthly etc", gr_amount: "25000" },
            { desc: "Office/ General expenses", gr_amount: "40000" },
            { desc: "Honorarium to private trainers, 10 Trainers for 5 sports for 2 months (Rs. 10,000 x 10 x 2)", gr_amount: "200000" },
            { desc: "Coach's travel Expense", gr_amount: "30000" },
            { desc: "Playground maintenance and repair", gr_amount: "75000" },
            { desc: "Seminar/ Comradeship/ Revision class (at least twice a year)", gr_amount: "60000" },
            { desc: "Counselling", gr_amount: "30000" },
            { desc: "Preparation of brochures, flex boards, posters, local newspaper/ cable channels for publicity", gr_amount: "10000" },
            { desc: "Training camp 2 times a year (100 players for 10 days, Rs. 50 per snack)", gr_amount: "100000" },
            { desc: "Total", gr_amount: "1448500" }
        ];

        $.each(financialItems, function(index, item) {
        	var desc=item.desc; 
            var idSuffix = index + 1;
            const itemHtml = 
                '<div class="financial-item form-group grid grid-cols-1 md:grid-cols-12 gap-4 items-center">'+
                '<input type="hidden" value="" name="fdId_'+idSuffix+'" id="fdId_'+idSuffix+'"/>'+
                	'<div class="md:col-span-7">'+
                        '<label class="form-label !mb-0 text-sm">'+item.desc+'</label>'+
                    '</div>'+
                    '<div class="md:col-span-2">'+
                        '<label for="gr_amount_'+idSuffix+'" class="form-label md:hidden">Amount as per GR</label>'+
                        '<input type="text" id="gr_amount_'+idSuffix+'" name="gr_amount_'+idSuffix+'" class="form-input bg-gray-100" value="'+item.gr_amount+'"  readonly>'+
                    '</div>'+
                    '<div class="md:col-span-3">'+
                        '<label for="req_amount_'+idSuffix+'" class="form-label md:hidden">Requested Amount</label>'+
                        '<input type="text" id="req_amount_'+idSuffix+'" name="req_amount_'+idSuffix+'"  class="form-input fdinpt" placeholder="Requested Amount">'+
                    '</div>'+
                '</div>';
            financialDetailsContainer.append(itemHtml);
            $(`#req_amount_${idSuffix}`).rules('add', { number: true, messages: { number: 'Please enter a valid number.' } });
        });
        
        $("#req_amount_13").attr("disabled",true);
        
        $('.fdinpt').on("input", function() {
    	 	var currentval=$(this).val();
    	 	var newval=currentval.replace(/\D+/g, '');
    	 	$(this).val(newval);
    	});
        
        
        $("#req_amount_1").keyup(function(){
        	//console.log("keyup called");
        	var currentVal=$(this).val();
        	if(currentVal>250000){
        		currentVal=250000;
        	}
        	$(this).val(currentVal);
        	
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        $("#req_amount_1").blur(function(){
        	//console.log("blur  called");
        	reqAmount1=0;
        	var currentVal=$(this).val();
        	if(currentVal>250000){
        		currentVal=250000;
        	}
        	reqAmount1=currentVal;
        	
        });
        
        $("#req_amount_2").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>412500){
        		currentVal=412500;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        $("#req_amount_2").blur(function(){
        	reqAmount2=0;
        	var currentVal=$(this).val();
        	if(currentVal>412500){
        		currentVal=412500;
        	}
        	$(this).val(currentVal);
        	reqAmount2=currentVal;
        });
        
        
        
        $("#req_amount_3").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>216000){
        		currentVal=216000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	///console.log("finalAmount -----===>"+finalAmount);
        });
        
        $("#req_amount_3").blur(function(){
        	reqAmount3=0;
        	var currentVal=$(this).val();
        	if(currentVal>216000){
        		currentVal=216000;
        	}
        	$(this).val(currentVal);
        	reqAmount3=currentVal;
        });
        
        $("#req_amount_4").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>25000){
        		currentVal=25000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        
        $("#req_amount_4").blur(function(){
        	reqAmount4=0;
        	var currentVal=$(this).val();
        	if(currentVal>25000){
        		currentVal=25000;
        	}
        	$(this).val(currentVal);
        	reqAmount4=currentVal;
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
		
        $("#req_amount_5").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>40000){
        		currentVal=40000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        
        $("#req_amount_5").blur(function(){
        	reqAmount5=0;
        	var currentVal=$(this).val();
        	if(currentVal>40000){
        		currentVal=40000;
        	}
        	$(this).val(currentVal);
        	reqAmount5=currentVal;
        });
        
        $("#req_amount_6").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>200000){
        		currentVal=200000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        $("#req_amount_6").blur(function(){
        	reqAmount6=0;
        	var currentVal=$(this).val();
        	if(currentVal>200000){
        		currentVal=200000;
        	}
        	$(this).val(currentVal);
        	reqAmount6=currentVal;
        });
        
        
        $("#req_amount_7").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>30000){
        		currentVal=30000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        $("#req_amount_7").blur(function(){
        	reqAmount7=0;
        	var currentVal=$(this).val();
        	if(currentVal>30000){
        		currentVal=30000;
        	}
        	$(this).val(currentVal);
        	reqAmount7=currentVal;
        });
        
        
        $("#req_amount_8").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>75000){
        		currentVal=75000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        $("#req_amount_8").blur(function(){
        	reqAmount8=0;
        	var currentVal=$(this).val();
        	if(currentVal>75000){
        		currentVal=75000;
        	}
        	$(this).val(currentVal);
        	reqAmount8=currentVal;
        });
        

        $("#req_amount_9").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>60000){
        		currentVal=60000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        
        $("#req_amount_9").blur(function(){
        	reqAmount9=0;
        	var currentVal=$(this).val();
        	if(currentVal>60000){
        		currentVal=60000;
        	}
        	$(this).val(currentVal);
        	reqAmount9=currentVal;
        });
        
        
        $("#req_amount_10").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>30000){
        		currentVal=30000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        $("#req_amount_10").blur(function(){
        	reqAmount10=0;
        	var currentVal=$(this).val();
        	if(currentVal>30000){
        		currentVal=30000;
        	}
        	$(this).val(currentVal);
        	reqAmount10=currentVal;
        });
        
        
        $("#req_amount_11").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>10000){
        		currentVal=10000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        $("#req_amount_11").blur(function(){
        	reqAmount11=0;
        	var currentVal=$(this).val();
        	if(currentVal>10000){
        		currentVal=10000;
        	}
        	$(this).val(currentVal);
        	reqAmount11=currentVal;
        });
        
        
        $("#req_amount_12").keyup(function(){
        	var currentVal=$(this).val();
        	if(currentVal>100000){
        		currentVal=100000;
        	}
        	$(this).val(currentVal);
        	var finalAmount=calculateFinancialDetails();
        	//console.log("finalAmount -----===>"+finalAmount);
        });
        
        $("#req_amount_12").blur(function(){
        	reqAmount12=0;
        	var currentVal=$(this).val();
        	if(currentVal>100000){
        		currentVal=100000;
        	}
        	$(this).val(currentVal);
        	reqAmount12=currentVal;
        });
        
        
        // --- Reports and Attachments ---
        const reportItems = [
            "1. Detailed report of the training program organised last year through the district training sports center/District (Duration of training, place, time, clippings, photos, etc.)",
            "a. Sports wise list of participating government/ private/ honorary sports guides who guided in the training program organized",
            "b. Number of players who participated in the training program organized by district last year",
            "2. Year wise list of sports materials (durable/ non-durable) and training equipment purchased for the training centre in last 3 years with price and feedback on the current status of the materials.",
            "a. List of furniture and office supplies purchased in the last 3 years with year wise prices",
            "b. List of sports equipment and magazines etc. purchased in last 3 years with their prices",
            "c. Details of total income and expenditure for the last 2 years",
            "d. Project report of the schemes implemented in the training centre in last year (seminars/revision classes/ counselling/ two sports training camps in a year)",
            "3. List of new sports equipment (durable/non durable) and training equipment required by sport to be purchased for district sports training centre for this year with price",
            "4. List of new furniture and office supplies etc. to be purchased for this year with their cost (office/general expenses)",
            "5. List of new sports equipment, periodicals, etc. to be purchased for this year along with their prices",
            "6. Details of the space required to safely store available sports material and training equipments",
            "7. Feedback from district sports officer regarding the proper utilization of the available sports equipment and facilities for training",
            "8. Feedback on the need for new materials, taking into account the currently available sports material, training equipment, sports literature, magzines, and fumiture",
            "9. Action taken in the current year to promote the training centre by preparing brochures, flex boards. posters, local newspapers/cable channels",
            "10. Project report of the plans to be implemented in the training centre in the current year (seminar/ symposium/revision class/ counselling/ teo sports camps in the year)",
            "11. List of players regularly attending the current training center as per Annexure-A attached",
            "12. Complete personal information of the government State/ Honorary Sports Guides at the training centre during the current year as per Annexure-B",
            "13. Complete personal information of the Private trainers appointed for training (duration of 12 months) in the current year as per Annexure-c",
            "14. The Appropriation certificate of the grant given to district sports training ceriter in the previous year should be attached",
            "15. Audited accounts of the grants spent last year.",
            "a. Reasons for not fully utilizing the grant given to the district sports traing centre last year",
            "16. Names and dates of the appointments of groundskeepers appointed in 17 district sports training centres (if any, certified copy of appointment order)",
            "17. General Remarks",
            "18. Photos of flexes, name boards, brochures, and boards with information about the district and training centre's accomplished athletes, training centre room, materials, and office supply",
            "19. Your office's bank account number, branch code number, MICR and IFSC code number etc, detailed information should be given separately along with the proposal",
            "20. The training centre should ensure that there is a need for new materials by considering the sports materials, training equipments, sports literature, magazines, furniture available in the training centre.",
            "21. Others"
        ];
        

        $.each(reportItems, function(index, item) {
            const idSuffix = index + 1; // Start numbering from 3
            const itemHtml = 
                '<div class="attachment-group form-group">'+
                    '<label class="form-label">'+item+'</label>'+
                    '<textarea name="remarks_'+idSuffix+'" rows="3"  id="remarks_'+idSuffix+'" class="form-textarea rattachmentRemarks" placeholder="Enter remarks..." maxlength="250"></textarea>'+
                    '<p class="error-container" id="attachment_remarks_error_'+idSuffix+'"></p>'+
                    '<div class="mt-2">'+
                        '<label class="form-label text-sm">Attachment (if any)</label>'+
                        '<input type="file" id="attachment_'+idSuffix+'" name="attachment_'+idSuffix+'" class="form-attachment rattachment">'+
                        '<p class="error-container" id="attachment_error_'+idSuffix+'"></p>'+
                    '</div>'+
                '</div>';
            reportsContainer.append(itemHtml);
        });
        
        
       $(document).on('change','.rattachment',function(){
    	  // console.log("on change clicked for report attachment ::");
    	   var currentId=$(this).attr("id");
    	   var rattachmentFile=$('#'+currentId)[0].files[0];
    	  // console.log("rattachmentFile :::" +rattachmentFile);
    	   var allowedExtensions = /(\.pdf)$/i;
    	   var maxSize = 2*1024*1024; // 2 MB
    	   var counterReport=currentId.split("_");
    	   if(rattachmentFile){
    		   //console.log("counterReport :::" +counterReport);
    		   var rattachmentFileSize = $('#'+currentId)[0].files[0].size;
    		   if (!allowedExtensions.exec($('#'+currentId)[0].files[0].name)) {
		        	//console.log("inside   attachment is present :::");
		        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "<liferay-ui:message key='please-select-pdf-file-only' />";
		    		//errorMessage.push("<liferay-ui:message key='please-select-pdf-file-only' />");
		        }else if (rattachmentFileSize>maxSize) {
		        	//console.log("inside else  if dpr");
		        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "<liferay-ui:message key='select-document-size-less-than-2-mb' />";
		    		//errorMessage.push("<liferay-ui:message key='select-document-size-less-than-2-mb' />");
		        }else{
		        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "";
		        }
    	   }else{
	        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "";
	        }
    	   
       });
        
       
       $(document).on('input','.rattachmentRemarks111',function(){
    	   this.value = this.value.replace(/\s{2,}/g, ' ');
    	   $(this).val(this.value);
       });
       
       $(document).on('change','.rattachmentRemarks',function(){
       				//console.log("remarks ");
       			var remarksValue=$(this).val();
       			var currentId=$(this).attr("id");
       			$(this).val($.trim(remarksValue.replace(/\s{2,}/g, ' ')));	
       			var trimmedValue = $.trim(remarksValue.replace(/\s{2,}/g, ' '));
       			//var regex = /^[-\/.,]*$/;
       			//var regex = /^[-\/.,\s]*$/
       			//var regex = /^[a-zA-Z0-9]*(?:[-/.,][a-zA-Z0-9]+)*$/;
       			//var regex = /^[a-zA-Z0-9 ]*$/;
       			//var regex =/^[a-zA-Z0-9\s,-.]*$/;   //workgin only slach not allowd
       			//var regex =/^[A-Za-z0-9]+(?:[ .\/-][A-Za-z0-9]+)*$/
       			var counterRattachment=currentId.split("_");
       			
       			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
       			
       			var actualValue=$(this).val();
				
				//console.log("actualValue length:::",actualValue.length);
				//console.log("actualValue :::",regex.test(actualValue));
				if(actualValue.length>0){
					
					if(actualValue.length<2){
						document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "<liferay-ui:message key='please-enter-minimum-2-character' />";
					}else if (!regex.test(actualValue)) {
	       	           // console.log("1 not allowed special character  ::");
	       	         	document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
	       	        	 return false;
	       			}else if(/([.\/ -,])\1+/.test(actualValue)){
	       	         	//console.log("2 not allowed special character  ::");
	       	      		document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
	       	        	return false;
	       	        } else {
	       	         //	console.log("allowed special character  ::");
	       	            document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "";
	       	        }	
				}else {
       	            document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "";
       	        }	
       			
       });
        

        // --- Event Listeners ---
        $('#addCoachBtn').on('click',function(){
        	coachCount++;
        		addCoach();
        });
        
        $('#addSportsPersonBtn').on('click',function(){
        	console.log("addSportsPersonBtn :: clicked :::: ::");
        	sportsPersonCount++;
        	
        	console.log("sportsPersonCount :: clicked :::: ::"+sportsPersonCount);
        	
        	addSportsPerson();	
        });

        // Add initial entries
        if(mode=='add'){
        	addCoach();
            addSportsPerson();	
        }
        
        //Get Default sports person in dropdown
        //getAllSportsPersons("sportspersonselect_1");

        // Handle removal of dynamic entries
        $('#trainingCentreForm').on('click', '.remove-btn', function() {
            $(this).closest('.coach-entry, .sp-entry').find('input, textarea, select').each(function() {
                $(this).rules('remove');
            });
            $(this).closest('.coach-entry, .sp-entry').remove();
        });
        
      //Submit form action
    	$('#addcoachingwing1').click(function(){
    		//console.log("submit button clicked ::");
            var form = document.getElementById('trainingCentreForm');//	$("#trainingCentreForm")[0];
    		var formData = new FormData(form);	 
    		formData.append('name', 'John Doe');
    		    	
  		  //  console.log("form:::" +form);	
  		   // console.log("formData:::" +formData);	
    		    
    		    uploadedFilesGeoTagPhotos.forEach((fileObj) => {
    		        if (fileObj && !fileObj.markedForDelete) {
    		            if (fileObj.isExisting) {
    		                formData.append("existingGeoTagPhotos", fileObj.name);
    		                formData.append("existingGeoTagPhotosURLs", fileObj.url);
    		                formData.append("existingGeoTagPhotosIds", fileObj.id);
    		               // console.log("Id : "+fileObj.id);
    		            } else {
    		                formData.append("actualGeoTagPhotos", fileObj.file);
    		                formData.append("actualGeoTagPhotosNames", fileObj.name);
    		            }
    		        }
    		    });
    		    	
    		    var url='${saveCoachingWingURL}';
    		   // console.log("url:"+url);
	     		 $.ajax({
	     			type: "POST",
	     	        url: '${saveCoachingWingURL}', 
	     	        data: formData,
		     	       processData: false, 
		     	      contentType: false, 
	     			async : false,
	     	        success: function(data) {
	     	            	//console.log("inside succeess :: ");
	     	        },error:function(resp){
	     	        	//console.log("inside error :: ");
	     	        }
	     		});
    	});
      
      
      
      //Geotag photo on change
      $("#geoTagPhotos").change(function(){
    	//  console.log("geo tag on change called::");
    	  var files = $(this)[0].files;
    	//  console.log("geo tag on change files::" +files);
    	//  console.log("geo tag on change  files.length::" + files.length);
      });
      
      
      $(".coachselect").select2({
		    placeholder: "<liferay-ui:message key='select' />",
		    language: {
		        noResults: function() {
		            return "<liferay-ui:message key='no-results-found' />";
		        }
		    }
		}).on('change', function() {
		    $(this).valid();
		});
      
      
    });
    
    // Map Integration
    let map;
    let marker;
    let latElement = document.getElementById('latitude');
    let lonElement = document.getElementById('longitude');

    let storedLat = parseFloat(latElement.value);
    let storedLon = parseFloat(lonElement.value);
    let isViewMode = "${mode}" === "view";
    let isEditMode = "${mode}" === "edit";

    if (!isNaN(storedLat) && !isNaN(storedLon)) {
        $("#map").removeClass("d-none");
        if(isViewMode){
        	initializeMap(storedLat, storedLon, false); 
        }else{
        	initializeMap(storedLat, storedLon, true); 
        }
    }

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(position => {
                const lat = position.coords.latitude;
                const lon = position.coords.longitude;
                $("#map").removeClass("d-none");
                initializeMap(lat, lon, true); // Edit mode
            }, showError);
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

    function initializeMap(lat, lon, isEditable) {
        //console.log("Map clicked :::");
    	if (!map) {
            map = L.map('map').setView([lat, lon], 13);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
            latElement.value = lat.toFixed(6);
            lonElement.value = lon.toFixed(6);
        } else {
            map.setView([lat, lon], 13);
        }
        if (marker) {
            map.removeLayer(marker);
        }
        marker = L.marker([lat, lon], { draggable: isEditable }).addTo(map);

        if (isEditable) {
            map.on('click', function (e) {
                updateCoordinates(e.latlng.lat, e.latlng.lng);
            });
            marker.on('dragend', function (event) {
                let updatedLatLng = event.target.getLatLng();
                updateCoordinates(updatedLatLng.lat, updatedLatLng.lng);
            });
        }
        
        var latitudeInputValue=$('#latitude').val();
        var latitudeInputValue=$('#longitude').val();
        
        if(latitudeInputValue){
        	document.getElementById("latitude-error").textContent = "";
        }
        if(latitudeInputValue){
        	document.getElementById("longitude-error").textContent = "";
        }
    }

    function updateCoordinates(lat, lon) {
        latElement.value = lat.toFixed(6);
        lonElement.value = lon.toFixed(6);
        marker.setLatLng([lat, lon]);
    }       
    
    function showError(error) {
        switch (error.code) {
            case error.PERMISSION_DENIED:
                alert("User denied the request for Geolocation.");
                break;
            case error.POSITION_UNAVAILABLE:
                alert("Location information is unavailable.");
                break;
            case error.TIMEOUT:
                alert("The request to get user location timed out.");
                break;
            case error.UNKNOWN_ERROR:
                alert("An unknown error occurred.");
                break;
        }
    }
    //End Map Integration
    
    
// Multiple File Upload
var uploadedFilesGeoTagPhotos = [];
var uploadedFilesSupportedDocuments = [];

document.addEventListener('DOMContentLoaded', function() {
    <c:if test="${mode eq 'edit' and not empty application.geoTagPhotosNames}">
    uploadedFilesGeoTagPhotos = [
	        <c:forEach var="photoName" items="${application.geoTagPhotosNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${application.geoTagPhotosURLs[status.index]}',
	                id: '${application.geoTagPhotosIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	   // console.log("Initialized Geo Tag Photos :", uploadedFilesGeoTagPhotos);
	    updateHiddenInput('geoTagPhoto', uploadedFilesGeoTagPhotos);
	</c:if>
	
	<c:if test="${mode eq 'edit' and not empty application.supportedDocumentsNames}">
	uploadedFilesSupportedDocuments = [
	        <c:forEach var="photoName" items="${application.supportedDocumentsNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${application.supportedDocumentsURLs[status.index]}',
	                id: '${application.supportedDocumentsIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    //console.log("Initialized Supported Documents files :", uploadedFilesSupportedDocuments);
	    updateHiddenInput('supportedDocument', uploadedFilesSupportedDocuments);
	</c:if>
});

function updateHiddenInput(inputId, filesArray) {
    const activeFiles = filesArray.filter(f => !f.markedForDelete).map(f => f.name);
    document.getElementById(inputId).value = activeFiles.join(',');
}
var gtnames = [];
function handleMultipleFileUpload(eventOrInput, inputId, previewContainerId, previewListId, errorSpanId, hiddenInputId) {
	
    const fileInput = eventOrInput.target ? eventOrInput.target : eventOrInput;
    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);
	
    //console.log("fileInput ::::"+fileInput);
   // console.log("fileInput.value ::::"+fileInput.value);
    
    let uploadedFiles;
    if (inputId === 'geoTagPhotos') {
        uploadedFiles = uploadedFilesGeoTagPhotos;
    } else if (inputId === 'supportedDocuments') {
        uploadedFiles = uploadedFilesSupportedDocuments;
    } 
   
    
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

        if (inputId === 'geoTagPhotos') {
            if (!['png', 'jpg', 'jpeg'].includes(ext)) {
                errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="allowed-only-jpg-jpeg-png-file" /></span>';
                errorSpan.style.display = "block";
                fileInput.value = "";
                return;
            }
        } 
        
        if (inputId === 'supportedDocuments') {
            if (!['pdf'].includes(ext)) {
                errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="allowed-only-pdf-file" /></span>';
                errorSpan.style.display = "block";
                fileInput.value = "";
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
    
    newFiles.forEach(file => {
    	gtnames.push(file.name);
    });
    
    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);
    fileInput.value = "";
   // console.log("uploadedFiles ::::" +uploadedFiles);
   // console.log("gtnames ::::" +gtnames);
    //For submit form
   // $('#geoAddTagPhotos').val(fileInput.value);
   
}

function removeFile(index, previewContainerId, previewListId, errorSpanId, hiddenInputId, inputId) {
    let uploadedFiles;
    if (inputId === 'geoTagPhotos') {
        uploadedFiles = uploadedFilesGeoTagPhotos;
    } else if (inputId === 'supportedDocuments') {
        uploadedFiles = uploadedFilesSupportedDocuments;
    } 

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
    let uploadedFiles;
    if (inputId === 'geoTagPhotos') {
        uploadedFiles = uploadedFilesGeoTagPhotos;
    } else if (inputId === 'supportedDocuments') {
        uploadedFiles = uploadedFilesSupportedDocuments;
    } 

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


//Switch to tab
function setActiveTab(tabId,currentTab,action) {
	
		console.log("action :::" +action);
		console.log("tabId :::" +tabId);
		console.log("currentTab :::" +currentTab);
	
    	//Addion Financial detail tab
    	var mode='${mode}';
    	
    	//console.log("mode :::" +mode);
    	
    	if(mode== 'view'){
    		$('.nav-link').removeClass('active');
    	    $('.tab-pane').removeClass('show active');

    	    $('#' + tabId + '-tab').addClass('active');
    	    $('#' + tabId).addClass('show active');

    	    if (typeof scrollToTop === "function") {
    	        scrollToTop();
    	    }
    	}else if(mode=='add' && tabId=='financial-details'){
    		console.log("Inside servant tab please save as draft to calculate the financial ::::");
    		 $("#servantModel").modal('show');
    	}else if(action=='previous'){
    		$('.nav-link').removeClass('active');
    	    $('.tab-pane').removeClass('show active');

    	    $('#' + tabId + '-tab').addClass('active');
    	    $('#' + tabId).addClass('show active');

    	    if (typeof scrollToTop === "function") {
    	        scrollToTop();
    	    }
    	}else{
    		//console.log("inside mode is not view  :::" +mode);
    		
    		if(tabId=='financial-details'){
    			//calculateFinancialDetailsByTab();
    			
    			//alert("Please save as draft to calculate financial");
    			console.log("inside fd tab 1::::");
    			event.preventDefault();
    			console.log("inside fd tab 2::::");
    			var currenttrainingCenterId=$('#trainingCenterId').val();
    			
    			console.log("currenttrainingCenterId :::"+currenttrainingCenterId);
    			
    			//ajax call
    			//code update 
    			/* $.ajax({
         			type: "POST",
         			datatype:"json",
         	        url: '${getFinancialDetailsDataURL}', 
         	        data: {
         	        	trainingCenterId:currenttrainingCenterId
         	        },
         			async : false,
         	        success: function(data) {
         	        	console.log("financial data :::" +data);
         	        	var response=JSON.parse(data);
         	        	console.log("financial details :::response :::" +response);
         	        	
         	        	$.each(response,function(k,v){
         	        		console.log("k ::"+k)
         	        		console.log("v ::"+v)
         	        		var desc=v.desc;
         	        		
         	        		if(desc=='Sports material and sports training equipment (5 sports Coaches at Rs. 50,000/- each)'){
         	        			$('#req_amount_1').val(v.requestedAmount);
         	        			$('#fdId_1').val(v.id);
         	        		}else if(desc=='5 sports Coaches will provide track suits, tshirts, shorts, shoes etc. to 50 players each who practice regularly at the center (55 x Rs. 1500/-)'){
         	        			$('#req_amount_2').val(v.requestedAmount);
         	        			$('#fdId_2').val(v.id);
         	        		}else if(desc=='Honorarium of 3 part time maidan servants (Rs. 6000/- x 12 x 3)'){
         	        			$('#req_amount_3').val(v.requestedAmount);
         	        			$('#fdId_3').val(v.id);
         	        		}else if(desc=='Sports literature, CDs, Books, periodicals, Monthly etc'){
         	        			$('#req_amount_4').val(v.requestedAmount);
         	        			$('#fdId_4').val(v.id);
         	        		}else if(desc=='Office/ General expenses'){
         	        			$('#req_amount_5').val(v.requestedAmount);
         	        			$('#fdId_5').val(v.id);
         	        		}else if(desc=='Honorarium to private trainers, 10 Trainers for 5 sports for 2 months (Rs. 10,000 x 10 x 2)'){
         	        			$('#req_amount_6').val(v.requestedAmount);
         	        			$('#fdId_6').val(v.id);
         	        		}else if(desc=="Coach's travel Expense"){
         	        			$('#req_amount_7').val(v.requestedAmount);
         	        			$('#fdId_7').val(v.id);
         	        		}else if(desc=="Playground maintenance and repair"){
         	        			$('#req_amount_8').val(v.requestedAmount);
         	        			$('#fdId_8').val(v.id);
         	        		}else if(desc=="Seminar/ Comradeship/ Revision class (at least twice a year)"){
         	        			$('#req_amount_9').val(v.requestedAmount);
         	        			$('#fdId_9').val(v.id);
         	        		}else if(desc=="Counselling"){
         	        			$('#req_amount_10').val(v.requestedAmount);
         	        			$('#fdId_10').val(v.id);
         	        		}else if(desc=="Preparation of brochures, flex boards, posters, local newspaper/ cable channels for publicity"){
         	        			$('#req_amount_11').val(v.requestedAmount);
         	        			$('#fdId_11').val(v.id);
         	        		}else if(desc=="Training camp 2 times a year (100 players for 10 days, Rs. 50 per snack)"){
         	        			$('#req_amount_12').val(v.requestedAmount);
         	        			$('#fdId_12').val(v.id);
         	        		}
         	        		
         	        	});
         	        	
         	        	
         	        	
         	        },error:function(resp){
         	        	//console.log("inside  division error :: ");
         	        }
         		}); */
    			//code update 
    			
    			
    			errorMessage=validateServant("servant");
    			
    			if(errorMessage.length>0){
        			return false;
        		}else{
        			$('.nav-link').removeClass('active');
        		    $('.tab-pane').removeClass('show active');

        		    $('#' + tabId + '-tab').addClass('active');
        		    $('#' + tabId).addClass('show active');

        		    if (typeof scrollToTop === "function") {
        		        scrollToTop();
        		    }
        		}
    			
    		}else{
    			//Tab Validation On Next Button
        		var errorMessage=[];
        		if(currentTab=="existingtrainingcentredetails"){
        			//console.log("validating trainingcented tab");
        			errorMessage=validateTrainingCenter("existingtrainingcentredetails");
        		}else if(currentTab=="coach-detail"){
        			//console.log("validating coach tab");
        			errorMessage=validateCoachDetail("coach-detail");
        		}else if(currentTab=="sports-person"){
        			//console.log("validating sports person tab");
        			errorMessage=validateSportsPerson("sports-person");
        		}else if(currentTab=="servant"){
        			//console.log("validating servant tab");
        			errorMessage=validateServant("servant");
        		}
        		
        		console.log("errorMessage in  setActiveTab:::" +errorMessage);
        		//e.preventDefault();
        		if(errorMessage.length>0){
        			return false;
        		}else{
        			$('.nav-link').removeClass('active');
        		    $('.tab-pane').removeClass('show active');

        		    $('#' + tabId + '-tab').addClass('active');
        		    $('#' + tabId).addClass('show active');

        		    if (typeof scrollToTop === "function") {
        		        scrollToTop();
        		    }
        		}
    		}
    		
    	}
}

function calculateFinancialDetailsByTab(){
	console.log("All tab calculation for calculateFinancialDetailsByTab called::");
	//calculate financial details 
	//Get no of coach count
	if(coachCount==6){
		coachCount=5;
	}
	
	//get no of sports person count
	if(sportsPersonCount==51){
		sportsPersonCount=50;
	}
	
	console.log("sportsPersonCount ::"+sportsPersonCount);	
	console.log("coachCount ::"+coachCount);
	
	//based on no of coach calculate the requested amount1
	maxRequestedAmount1=coachCount*50000
	if(maxRequestedAmount1>250000){
		maxRequestedAmount1=250000;
	}
	$('#req_amount_1').val(maxRequestedAmount1);
	
	
	//requested amount2
	maxRequestedAmount2=(sportsPersonCount+coachCount)*1500;
	
	if(maxRequestedAmount2>412500){
		maxRequestedAmount2=412500;
	}
	
	//Servent details (requested amount3)
	var d = new Date();
	var currentMonth=d.getMonth()+1;
	var currentDate=d.getDate();
	console.log("Servent details Current month::" +(d.getMonth()+1));
	console.log("Servent details Current date::" +d.getDate());
	console.log("Servent details Current date::" +servantCount);
	
	var today = new Date();
	var monthArray = [4,5,6,7,8,9,10,11,12,1,2,3];
	
	for(var i=1;i<=servantCount;i++){
		var servantEntryDate= $('#servantdoj_'+i).val();
		console.log("servantEntryDate ::: "+servantEntryDate);
		 var servantEntryDateValue = new Date(servantEntryDate);
		 console.log("servantEntryDateValue ::: "+servantEntryDateValue);
		 console.log("servantEntryDateValue getDate()::: "+servantEntryDateValue.getDate());
		 console.log("servantEntryDateValue.getMonth() ::: "+(servantEntryDateValue.getMonth()+1));
		 var index = $.inArray((servantEntryDateValue.getMonth()+1), monthArray); 
		 console.log("index ::: "+index);
		 var servantAmount=servantCalculation(index,servantEntryDateValue.getDate());
		 console.log("servantAmount :::"+servantAmount);
		 maxRequestedAmount3=maxRequestedAmount3+servantAmount;
	}
	console.log("Final Amount for servant is ::::"+maxRequestedAmount3);
	//maxRequestedAmount3=6000;
	if(maxRequestedAmount3>216,000){
		maxRequestedAmount3=216,000;
	}
	
	$('#req_amount_2').val(maxRequestedAmount2);
	$('#req_amount_3').val(maxRequestedAmount3);
	
	//No calculation 
	$('#req_amount_6').val((coachCount*2*20000));
	
	maxRequestedAmount12=sportsPersonCount*10*50*2;
	if(maxRequestedAmount12>100000){
		maxRequestedAmount12=100000;
	}
	$('#req_amount_12').val(maxRequestedAmount12);
	
	//Coach travel expence
	var trainingcenterdistrictval=$("#district").val();
	//console.log("trainingcenterdistrictval :::" +trainingcenterdistrictval);
	for(var i=1;i<=coachCount;i++){
		var coachDistrictVal= $('#coachdistrictselect_'+i).val();
		var isSameDistrict=false;
		//console.log("coachDistrictVal ::::"+coachDistrictVal);
		if(coachDistrictVal==trainingcenterdistrictval){
			$("#req_amount_7").prop("disabled",true);
		}else{
			$("#req_amount_7").prop("disabled",false);
		}
	}
	
	//Financial detail total count
	for(var i=1;i<=12;i++){
		var value=$('#req_amount_'+i).val();
		//console.log("Value :::" +value);
		if(value>0){
			totalRequestedAmount=totalRequestedAmount+parseFloat(value);
		}
	}
	//totalRequestedAmount=reqAmount1+reqAmount2+reqAmount3+reqAmount4+reqAmount5+reqAmount6+reqAmount7+reqAmount8+reqAmount9+reqAmount10+reqAmount11+reqAmount12;
	console.log("totalRequestedAmount ::" +totalRequestedAmount);
	$('#req_amount_13').val(totalRequestedAmount);
	
	//Financial Details Input value total amount calculation
	calculateFinancialDetails();
}

function servantCalculation(index,dateValue){
	console.log("inside servant calculation");
	var totalAmount=0;
	var startIndex=(index+1);
	
	console.log("startIndex ::" +startIndex);
	var myArray = [4,5,6,7,8,9,10,11,12,1,2,3];
	for(var i=startIndex;i<myArray.length;i++){
		totalAmount=totalAmount+6000;
	}
	console.log("totalAmount servant is::"+totalAmount);
	
	//month and date based calculation
	if(dateValue>15){
		totalAmount=totalAmount+6000;
	}else if(dateValue<15){
		totalAmount=totalAmount+3000;
	}
	console.log("dateValue :::"+dateValue);
	return totalAmount;
} 


//Calculate total requested amount by all input fields
function calculateFinancialDetails(){
	console.log("Entry into financlal details calculation function:::");
	//calculate financial details all values
	var totalFinancialInputsValues=0;
	for(var i=1;i<=12;i++){
		var value=$('#req_amount_'+i).val();
		//console.log("Value :::" +value);
		if(value>0){
			totalFinancialInputsValues=totalFinancialInputsValues+parseFloat(value);
		}
	}
	console.log("totalFinancialInputsValues for financial tab::" +totalFinancialInputsValues);
	$('#req_amount_13').val(totalFinancialInputsValues);
	return 	totalFinancialInputsValues;
}

function setActiveTab1(tabId) {
	//Addion Financial detail tab
if(tabId=='financial-details'){
	//console.log("sportsPersonCount ::"+sportsPersonCount);	
	//console.log("coachCount ::"+coachCount);	
	
	maxRequestedAmount2=(sportsPersonCount+coachCount)*1500;
	
	if(maxRequestedAmount2>412500){
		maxRequestedAmount2=412500;
	}
	
	//Servent details
	maxRequestedAmount3=6000;
	if(maxRequestedAmount3>216,000){
		maxRequestedAmount3=216,000;
	}
	
	$('#req_amount_2').val(maxRequestedAmount2);
	$('#req_amount_3').val(maxRequestedAmount3);
	
	//No calculation 
	//$('#req_amount_4').val(25000);
	//$('#req_amount_5').val(40000);
	$('#req_amount_6').val((coachCount*2*20000));
	
	maxRequestedAmount12=sportsPersonCount*10*50*2;
	if(maxRequestedAmount12>100000){
		maxRequestedAmount12=100000;
	}
	$('#req_amount_12').val(maxRequestedAmount12);
}
//Tab Validation On Next Button
var errorMessage=validateTrainingCenter("existingtrainingcentredetails");
//console.log("errorMessage in  setActiveTab:::" +errorMessage);
//e.preventDefault();
if(errorMessage.length>0){
	return false;
}else{
	$('.nav-link').removeClass('active');
    $('.tab-pane').removeClass('show active');

    $('#' + tabId + '-tab').addClass('active');
    $('#' + tabId).addClass('show active');

    if (typeof scrollToTop === "function") {
        scrollToTop();
    }
}

}

/* $('.nav-link').click(function(e) {
	var targetTabId = $(this).attr('id');
    console.log(targetTabId)
    // Remove active classes
    $('.nav-link').removeClass('active');
    $('.tab-pane').removeClass('show active');
    
    // Activate the clicked tab
    $(this).addClass('active');
    $('#' + targetTabId.replace('-tab', '')).addClass('show active');
    
    //scrollToTop();
}); */


function deleteFile(filePreviewContainer,id) {
    const previewContainer = document.getElementById(filePreviewContainer);
    const fileInput = document.getElementById(id);

    fileInput.value = "";
	$(".custom-file-input").siblings(".custom-file-label").addClass("selected").html(" <liferay-ui:message key='choose-file' />"); 
    previewContainer.classList.add('d-none');
    previewContainer.classList.remove('d-flex');
}


function validateTrainingCenter(tabname){
	//console.log("same tab");
	var errorMessage=[];
	var divisionval=$('#division').val();
	//console.log("divisionval ::" +divisionval);
	var districtval=$('#district').val();
	//console.log("districtval ::" +districtval);
	
	var traingingcentertype=$('input[name="centre_type"]:checked').val();
	//console.log("traingingcentertype ::" +traingingcentertype);
	
	
	var sportsTypevalue=$('#sports_type').val();
	//console.log("sportsTypevalue ::" +sportsTypevalue);
	
	
	var latitudeval=$("#latitude").val();
	//console.log("latitudeval ::" +latitudeval);
	
	var longitudeVal=$("#longitude").val();
	//console.log("longitudeVal ::" +longitudeVal);
	
	
	//dpr document
	//console.log("mode ::::"+mode);
	
	
	var dprDocs=$("#dpr_doc")[0].files[0];
	var fileValue=$('#dprDocumentPreviewLink').text();
	//console.log("dpr docs fileValue ::::"+fileValue);
		if(mode=='add'){
			//console.log("inside add mode");
			if(dprDocs){
			 	//console.log("dprDocs:::" +dprDocs);
				var dprdoc=$('#dpr_doc')[0];
				var dprdocSize = $("#dpr_doc")[0].files[0].size;
				var dprdoclength=$('#dpr_doc')[0].files.length;
				 var maxSize = 2*1024*1024; // 2 MB
				 //console.log("dprdocSize 2:::" +dprdocSize);
				 //console.log("maxSize> :::" +dprdocSize>maxSize);
				var allowedExtensions = /(\.pdf)$/i;
			
			 if(dprdoclength==0){
		        	//console.log("inside if dpr");
		        	document.getElementById("dpr_doc-error").textContent = "<liferay-ui:message key='please-select-dpr_doc' />";
		    		errorMessage.push("<liferay-ui:message key='please-select-dpr_doc' />");
		    		$(this).focus();
		        }else if (!allowedExtensions.exec(dprdoc.files[0].name)) {
		        	//console.log("inside else  if dpr");
		        	document.getElementById("dpr_doc-error").textContent = "<liferay-ui:message key='please-select-pdf-file-only' />";
		    		errorMessage.push("<liferay-ui:message key='please-select-pdf-file-only' />");
		    		
		        }else if (dprdocSize>maxSize) {
		        	//console.log("inside else  if dpr");
		        	document.getElementById("dpr_doc-error").textContent = "<liferay-ui:message key='select-document-size-less-than-2-mb' />";
		    		errorMessage.push("<liferay-ui:message key='select-document-size-less-than-2-mb' />");
		        }else{
		        	document.getElementById("dpr_doc-error").textContent = "";
		        }
			}else{
				document.getElementById("dpr_doc-error").textContent = "<liferay-ui:message key='please-select-dpr_doc' />";
	    		errorMessage.push("<liferay-ui:message key='please-select-dpr_doc' />");
	    		$(this).focus();
			}
		}else if(mode=='edit'){
			//console.log("inside edit mode");
			var fileValue=$('#dprDocumentPreviewLink').text();
			var newFileName=jQuery.trim(fileValue);
			//console.log("newFileName dpr docs:::" +newFileName );
			//file validation pending currently
			
		}
	
       
	if(!divisionval){
		document.getElementById("division-error").textContent = "<liferay-ui:message key='please-select-division' />";
		errorMessage.push("<liferay-ui:message key='please-select-division' />");
		$(this).focus();
	}else{
		document.getElementById("division-error").textContent = "";
	}
	
	if(!districtval){
		document.getElementById("district-error").textContent = "<liferay-ui:message key='please-select-district' />";
		errorMessage.push("<liferay-ui:message key='please-select-district' />");
	}else{
		document.getElementById("district-error").textContent = "";
	}
	
	if(!traingingcentertype){
		document.getElementById("training-center-type-error").textContent = "<liferay-ui:message key='please-choose-trainging-center-type' />";
		errorMessage.push("<liferay-ui:message key='please-choose-trainging-center-type' />");
    }else{
    	document.getElementById("training-center-type-error").textContent = "";
    }    
	
	if(!sportsTypevalue){
		document.getElementById("sports_type-error").textContent = "<liferay-ui:message key='please-select-sports-type' />";
		errorMessage.push("<liferay-ui:message key='please-select-sports-type' />");
		$(this).focus();
	}else{
		document.getElementById("sports_type-error").textContent = "";
	}
		
	if(!latitudeval){
		document.getElementById("latitude-error").textContent = "<liferay-ui:message key='please-select-gis-map-location' />";
		errorMessage.push("<liferay-ui:message key='please-select-gis-map-location' />");
		$(this).focus();
	}else{
		document.getElementById("latitude-error").textContent = "";
	}
	
	if(!longitudeVal){
		document.getElementById("longitude-error").textContent = "<liferay-ui:message key='please-select-gis-map-location' />";
		errorMessage.push("<liferay-ui:message key='please-select-gis-map-location' />");
		$(this).focus();
	}else{
		document.getElementById("longitude-error").textContent = "";
	}
	
	//geo tag validation 
	var ulElement = document.getElementById("geoTagPhotosPreviewList");
	// Get all li elements within that ul
	var  liElements = ulElement.getElementsByTagName("li");
	// Get the number of li elements
	var numberOfLi = liElements.length;
	//console.log("Number of list items:" +numberOfLi);
	if(numberOfLi==0){
		document.getElementById("geoTagPhotosError").textContent = "<liferay-ui:message key='please-select-geo-tag-photos' />";
		errorMessage.push("<liferay-ui:message key='please-select-geo-tag-photos' />");
		$('#geoTagPhotos').focus();
	}else{
		document.getElementById("geoTagPhotosError").textContent = "";
	}
	
     /* var geoTagPhotos=$('#geoTagPhotos')[0]; 
     var newFiles = Array.from(geoTagPhotos.files);
     console.log("newFiles ::" +newFiles);
     console.log("geoTagPhotos ::" +geoTagPhotos);
     console.log("geoTagPhotos :: geoTagPhotos.files.length ::" +geoTagPhotos.files.length); */
	
	
	
	//console.log("errorMessage :::" +errorMessage);
	//console.log("errorMessage :::" +errorMessage.length);
	
	return errorMessage;
	/* if(errorMessage.lenght>0){
		return false;
	}else{
		return true;
	} */
	
}

function validateCoachDetail(tabname){
	//console.log("inside coach tab save as draft :::");
	var count=$('#coachCount').val();
	//console.log("coach count :::" +coachCount);
	var errorMessage=[];
	
	for(var i=1;i<=count;i++){
		
		var nameValue=$('#coach_name_'+i).val();
		
		//console.log("nameValue :::"+nameValue);
		
		if(!nameValue){
			document.getElementById("coachname-error_"+i).textContent = "<liferay-ui:message key='please-enter-name' />";
			errorMessage.push("<liferay-ui:message key='please-enter-name' />");
			$('#coach_name_'+i).focus();
		}else{
			//Validate coach name value
			//console.log("nameValue ::" +nameValue);
			var coachNameVal=$.trim(nameValue.replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z .]*$/; 
			
			if(coachNameVal.length<3){
				document.getElementById("coachname-error_"+i).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				errorMessage.push("<liferay-ui:message key='please-enter-atleast-3-character' />");
				$('#coach_name_'+i).focus();
			}else if(!regex.test(coachNameVal)){
				//console.log("Error 2");
				document.getElementById("coachname-error_"+i).textContent ='<liferay-ui:message key="please-enter-alphabates-only" />';
				errorMessage.push("<liferay-ui:message key='please-enter-alphabates-only' />");
				$('#coach_name_'+i).focus();
			}else if(/([.\/ ])\1+/.test(coachNameVal)){
				document.getElementById("coachname-error_"+i).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				errorMessage.push("<liferay-ui:message key='two-special-character-not-allowed' />");
				$('#coach_name_'+i).focus();
			}else{
				//console.log("No error ");
				document.getElementById("coachname-error_"+i).textContent ='<liferay-ui:message key="" />';
			}
			//document.getElementById("coachname-error_"+i).textContent = "";
		}
		
		var mobileValue=$('#coach_mobile_'+i).val();
		
		if(!mobileValue){
			document.getElementById("coachmobile-error_"+i).textContent = "<liferay-ui:message key='please-enter-mobile' />";
			errorMessage.push("<liferay-ui:message key='please-enter-mobile' />");
			$('#coach_mobile_'+i).focus();
		}else{
			//validate coach mobile number 
			var mobileNumberPattern = /^[6-9][0-9]{0,9}$/; 
		
			if(!mobileNumberPattern.test(mobileValue)){
				document.getElementById("coachmobile-error_"+i).textContent ='<liferay-ui:message key="please-enter-a-valid-mobile" />';
				errorMessage.push("<liferay-ui:message key='please-enter-a-valid-mobile' />");
				$('#coach_mobile_'+i).focus();
			}else if(mobileValue.length<10){
				document.getElementById("coachmobile-error_"+i).textContent ='<liferay-ui:message key="please-enter-10-digit-mobile" />';
				errorMessage.push("<liferay-ui:message key='please-enter-10-digit-mobile' />");
				$('#coach_mobile_'+i).focus();
			}else{
				document.getElementById("coachmobile-error_"+i).textContent ='';
			}
		}
		
		//Validate Coach email
		var emailValue=$('#coach_email_'+i).val();
		if(!emailValue){
			document.getElementById("coachemail-error_"+i).textContent = "<liferay-ui:message key='please-enter-email' />";
			errorMessage.push("<liferay-ui:message key='please-enter-email' />");
			$('#coach_email_'+i).focus();
		}else{
			var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
			if(!pattern.test(emailValue)){
				document.getElementById("coachemail-error_"+i).textContent ='<liferay-ui:message key="please-enter-a-valid-email" />';
				errorMessage.push("<liferay-ui:message key='please-enter-a-valid-email' />");
				$('#coach_email_'+i).focus();
			}else{
				document.getElementById("coachemail-error_"+i).textContent ='';
			}
		}
		
		var coachtype=$('input[name="coach_type_'+i+'"]:checked').val();
		//console.log("coachtype ::" +coachtype);
		
		
		//Validate sports name
		var coachsportsname=$('#coach_sports_name_'+i).val();
		if(!coachsportsname){
			document.getElementById("coachsports-error_"+i).textContent = "<liferay-ui:message key='please-enter-sports-name' />";
			errorMessage.push("<liferay-ui:message key='please-enter-sports-name' />");
			$('#coach_sports_name_'+i).focus();
	    }else{
				var coachSportsVal=$.trim(coachsportsname.replace(/\s{2,}/g, ' '));
				var regex =/^[a-zA-Z -]*$/; 	    		
				if(coachSportsVal.length<2){
					document.getElementById("coachsports-error_"+i).textContent ='<liferay-ui:message key="please-enter-atleast-1-character" />';
					errorMessage.push("<liferay-ui:message key='please-enter-atleast-1-character' />");
					$('#coach_sports_name_'+i).focus();
				}else if(!regex.test(coachSportsVal)){
					//console.log("Error 2");
					document.getElementById("coachsports-error_"+i).textContent ='<liferay-ui:message key="please-enter-alphabates-only" />';
					errorMessage.push("<liferay-ui:message key='please-enter-alphabates-only' />");
					$('#coach_sports_name_'+i).focus();
				}else if(/([-\/ ])\1+/.test(coachSportsVal)){
					document.getElementById("coachsports-error_"+i).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
					errorMessage.push("<liferay-ui:message key='two-special-character-not-allowed' />");
					$('#coach_sports_name_'+i).focus();
				}else{
					//console.log("No error ");
					document.getElementById("coachsports-error_"+i).textContent ='<liferay-ui:message key="" />';
				}
	    } 
		
		
		if(!coachtype){
			document.getElementById("coachtype-error_"+i).textContent = "<liferay-ui:message key='please-choose-coach-type' />";
			errorMessage.push("<liferay-ui:message key='please-choose-coach-type' />");
	    }else{
	    	document.getElementById("coachtype-error_"+i).textContent = "";
	    } 
		
		var coachValue=$('#coachselect_'+i).val();
		if(!coachValue){
			document.getElementById("coach-error_"+i).textContent = "<liferay-ui:message key='please-select-coach' />";
			errorMessage.push("<liferay-ui:message key='please-select-coach' />");
	    }else{
	    	document.getElementById("coach-error_"+i).textContent = "";
	    } 
		
		//validate track suit size
		var tracksuitValue=$('#tracksuit_size_'+i).val();
		//console.log("tracksuitValue :: " +tracksuitValue);
		if(!tracksuitValue){
			document.getElementById("tracksuit_size_-error_"+i).textContent = "<liferay-ui:message key='please-enter-track-suit-size' />";
			errorMessage.push("<liferay-ui:message key='please-enter-track-suit-size' />");
			$('#tracksuit_size_'+i).focus();
	    }else{
	    	  $('#tracksuit_size_'+i).val(tracksuitValue.replace(/[^a-zA-Z0-9]/g, '')); 
			  var trachsuitValue=tracksuitValue.replace(/[^a-zA-Z0-9]/g, '');
			  if(!trachsuitValue){
				  document.getElementById("tracksuit_size_-error_"+i).textContent = "<liferay-ui:message key='please-enter-track-suit-size' />";
				  errorMessage.push("<liferay-ui:message key='please-enter-track-suit-size' />");
				  $('#tracksuit_size_'+i).focus();
			  }else{
				  document.getElementById("tracksuit_size_-error_"+i).textContent = "";
			  }
	    	//document.getElementById("tracksuit_size_-error_"+i).textContent = "";
	    } 
		
		
		//validate tshirts size
		
		var tshirtsValue=$('#tshirt_size_'+i).val();
		if(!tshirtsValue){
			document.getElementById("tshirt_size_-error_"+i).textContent = "<liferay-ui:message key='please-enter-tshirs-size' />";
			errorMessage.push("<liferay-ui:message key='please-enter-tshirs-size' />");
			$('#tshirt_size_'+i).focus();
	    }else{
	    	document.getElementById("tshirt_size_-error_"+i).textContent = "";
	    	
	    	 $('#tshirt_size_'+i).val(tshirtsValue.replace(/[^a-zA-Z0-9]/g, '')); 
			  var tshirtsValue=tshirtsValue.replace(/[^a-zA-Z0-9]/g, '');
			  if(!tshirtsValue){
				  document.getElementById("tshirt_size_-error_"+i).textContent = "<liferay-ui:message key='please-enter-tshirts-size' />";
				  errorMessage.push("<liferay-ui:message key='please-enter-tshirts-size' />");
				  $('#tshirt_size_'+i).focus();
			  }else{
				  document.getElementById("tshirt_size_-error_"+i).textContent = "";
			  }
	    } 
		
		//validate shorts size
		var shortsValue=$('#shorts_size_'+i).val();
		if(!shortsValue){
			document.getElementById("shorts_size_-error_"+i).textContent = "<liferay-ui:message key='please-enter-shorts-size' />";
			errorMessage.push("<liferay-ui:message key='please-enter-shorts-size' />");
			$('#shorts_size_'+i).focus();
	    }else{
	    	//document.getElementById("shorts_size_-error_"+i).textContent = "";
	    	$('#shorts_size_'+i).val(shortsValue.replace(/[^a-zA-Z0-9]/g, '')); 
			  var shortsValue=shortsValue.replace(/[^a-zA-Z0-9]/g, '');
			  if(!shortsValue){
				  document.getElementById("shorts_size_-error_"+i).textContent = "<liferay-ui:message key='please-enter-shorts-size' />";
				  errorMessage.push("<liferay-ui:message key='please-enter-shorts-size' />");
				  $('#shorts_size_'+i).focus();
			  }else{
				  document.getElementById("shorts_size_-error_"+i).textContent = "";
			  }
	    } 
		
		
		//validate shoe size
		var shoesValue=$('#shoes_size_'+i).val();
		if(!shoesValue){
			//console.log("111111111111111111");
			document.getElementById("shoes_size-error_"+i).textContent = "<liferay-ui:message key='please-enter-shoe-size' />";
			errorMessage.push("<liferay-ui:message key='please-enter-shoe-size' />");
			$('#shoes_size_'+i).focus();
	    }else{
	    	//document.getElementById("shoes_size-error_"+i).textContent = "";
	    	//console.log("222222222222222222222");
	    	$('#shoes_size_'+i).val(shoesValue.replace(/[^a-zA-Z0-9]/g, '')); 
			  var shoesValue=shoesValue.replace(/[^a-zA-Z0-9]/g, '');
			  if(!shoesValue){
				 // console.log("333333333333333333");
				  document.getElementById("shoes_size-error_"+i).textContent = "<liferay-ui:message key='please-enter-shoe-size' />";
				  errorMessage.push("<liferay-ui:message key='please-enter-shoe-size' />");
				  $('#shoes_size_'+i).focus();
			  }else{
				  document.getElementById("shoes_size-error_"+i).textContent = "";
				  //console.log("44444444444444444444");
			  }
	    } 
		
		
		//validate coach address
		var coachAddress=$('#coach_address_'+i).val();
		if(!coachAddress){
			document.getElementById("coach_address_-error_"+i).textContent = "<liferay-ui:message key='please-enter-address' />";
			errorMessage.push("<liferay-ui:message key='please-enter-address' />");
			$('#coach_address_'+i).focus();
	    }else{
	    	var trimmedValue = $.trim(coachAddress.replace(/\s{2,}/g, ' '));
	    	var regex =/^[a-zA-Z0-9 .,/-]*$/; 
   			var actualValue=trimmedValue.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
   			if(actualValue.length<10){
				document.getElementById("coach_address_-error_"+i).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
				errorMessage.push("<liferay-ui:message key='please-enter-minimum-10-character' />");
				$('#coach_address_'+i).focus();
			}else if (!regex.test(actualValue)) {
   	            //console.log("1 not allowed special character  ::");
   	         	document.getElementById("coach_address_-error_"+i).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
   	         	errorMessage.push("<liferay-ui:message key='given-special-character-not-allowed' />");
   	        	 $('#coach_address_'+i).focus();
   			}else if(/([.\/ -,])\1+/.test(actualValue)){
   	         	//console.log("2 not allowed special character  ::");
   	      		document.getElementById("coach_address_-error_"+i).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
   	      	    errorMessage.push("<liferay-ui:message key='consecutive-special-characters-not-allowed' />");
   	      		$('#coach_address_'+i).focus();
   	        }else {
   	         	//console.log("allowed special character  ::");
   	         document.getElementById("coach_address_-error_"+i).textContent = "";
   	        }
	    	//document.getElementById("coach_address_-error_"+i).textContent = "";
	    } 
		
		var coachDistrict=$('#coachdistrictselect_'+i).val();
		if(!coachDistrict){
			document.getElementById("coachdistrictselect_-error_"+i).textContent = "<liferay-ui:message key='please-select-district' />";
			errorMessage.push("<liferay-ui:message key='please-select-district' />");
	    }else{
	    	document.getElementById("coachdistrictselect_-error_"+i).textContent = "";
	    } 
		
		//validate dso remarks in coach details
		var coach_remarks=$('#coach_remarks_'+i).val();
		if(coach_remarks){
   			var trimmedValue = $.trim(coach_remarks.replace(/\s{2,}/g, ' '));
   			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
   			var actualValue1=trimmedValue;
   			var actualValue=actualValue1.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
   			$('#coach_remarks_'+i).val(actualValue);
			//console.log("actualValue.length :::" +actualValue.length);
			if(actualValue){
				if(actualValue.length<10){
					document.getElementById("coachdsoremarks_error_"+i).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
					errorMessage.push("<liferay-ui:message key='please-enter-minimum-10-character' />");
					$('#coach_remarks_'+i).focus();
				}else if (!regex.test(actualValue)) {
       	           // console.log("1 not allowed special character  ::");
       	         	document.getElementById("coachdsoremarks_error_"+i).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
       	         	errorMessage.push("<liferay-ui:message key='given-special-character-not-allowed' />");
       	         	$('#coach_remarks_'+i).focus();
       			}else if(/([.\/ -,])\1+/.test(actualValue)){
       	         	//console.log("2 not allowed special character  ::");
       	      		document.getElementById("coachdsoremarks_error_"+i).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
       	      		errorMessage.push("<liferay-ui:message key='consecutive-special-characters-not-allowed' />");
       	      	$('#coach_remarks_'+i).focus();
       	        }else {
       	         	//console.log("allowed special character  ::");
       	         	document.getElementById("coachdsoremarks_error_"+i).textContent = "";
       	        }
			}else {
       	         document.getElementById("coachdsoremarks_error_"+i).textContent = "";
   	        }
	    }else{
	    	document.getElementById("coachdsoremarks_error_"+i).textContent = "";
	    } 
		
		
	}
	return errorMessage;
}

function validateServant(tabname){
	var servantcountvar=$('#servantcountvar').val();
	//console.log("servantcountvar :::" +servantcountvar);
	var errorMessage=[];
	
	for(var i=1;i<=servantcountvar;i++){
		
		//validate servant name
		var servantname=$('#servantname_'+i).val();
		if(servantname){
			var servantNameVal=$.trim(servantname.replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z . ]*$/; 
			if(servantNameVal.length<3){
				document.getElementById("servantname_-error_"+i).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				errorMessage.push("<liferay-ui:message key='please-enter-atleast-3-character' />");
				$('#servantname_'+i).focus();
			}else if(!regex.test(servantNameVal)){
				document.getElementById("servantname_-error_"+i).textContent ='<liferay-ui:message key="please-enter-alphabate-only" />';
				errorMessage.push("<liferay-ui:message key='please-enter-alphabate-only' />");
				$('#servantname_'+i).focus();
			}else if(/([.\/ ])\1+/.test(servantNameVal)){
				document.getElementById("servantname_-error_"+i).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				errorMessage.push("<liferay-ui:message key='two-special-character-not-allowed' />");
				$('#servantname_'+i).focus();
			}else{
				document.getElementById("servantname_-error_"+i).textContent ='<liferay-ui:message key="" />';
			}
		}else{
			document.getElementById("servantname_-error_"+i).textContent = "<liferay-ui:message key='please-enter-name' />";
			errorMessage.push("<liferay-ui:message key='please-enter-name' />");
			$('#servantname_'+i).focus();
		}
		
		
		//Validate servant surname
		var servantsurname=$('#servantsurname_'+i).val();
		if(servantsurname){
			var servantSurNameVal=$.trim(servantsurname.replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z . ]*$/; 
			
			if(servantSurNameVal.length<3){
				document.getElementById("servantsurname_-error_"+i).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				errorMessage.push("<liferay-ui:message key='please-enter-atleast-3-character' />");
				$('#servantsurname_'+i).focus();
				console.log("1");
			}else if(!regex.test(servantSurNameVal)){
				document.getElementById("servantsurname_-error_"+i).textContent ='<liferay-ui:message key="please-enter-alphabate-only" />';
				errorMessage.push("<liferay-ui:message key='please-enter-alphabate-only' />");
				$('#servantsurname_'+i).focus();
				console.log("2");
			}else if(/([.\/ ])\1+/.test(servantSurNameVal)){
				document.getElementById("servantsurname_-error_"+i).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				errorMessage.push("<liferay-ui:message key='two-special-character-not-allowed' />");
				$('#servantsurname_'+i).focus();
				console.log("3");
			}else{
				document.getElementById("servantsurname_-error_"+i).textContent ='<liferay-ui:message key="" />';
				console.log("4");
			}
			
		}else{
			document.getElementById("servantsurname_-error_"+i).textContent = "<liferay-ui:message key='please-enter-surname' />";
			errorMessage.push("<liferay-ui:message key='please-enter-surname' />");
			$('#servantsurname_'+i).focus();
			console.log("5");
		}
		
		//validate servant address
		var servantaddress=$('#servantaddress_'+i).val();
		if(servantaddress){
				
			var trimmedValue = $.trim(servantaddress.replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z0-9 .,/#-]*$/; 
			var actualValue=trimmedValue.replace(/[^a-zA-Z0-9\s.,/#-]|(?<!\.)$/g, '');
			if(actualValue.length<10){
				document.getElementById("servantaddress_-error_"+i).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
				errorMessage.push("<liferay-ui:message key='please-enter-address' />");
				$('#servantaddress_'+i).focus();
			}else if (!regex.test(actualValue)) {
   	            console.log("1 not allowed special character  ::");
   	         	document.getElementById("servantaddress_-error_"+i).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
	   	         errorMessage.push("<liferay-ui:message key='please-enter-address' />");
	 			$('#servantaddress_'+i).focus();
			}else if(/([.\/ -,])\1+/.test(actualValue)){
   	         	console.log("2 not allowed special character  ::");
   	      		document.getElementById("servantaddress_-error_"+i).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
	   	      	errorMessage.push("<liferay-ui:message key='please-enter-address' />");
				$('#servantaddress_'+i).focus();
			}else {
   	         	console.log("allowed special character  ::");
   	         document.getElementById("servantaddress_-error_"+i).textContent = "";
   	        }
		}else{
			document.getElementById("servantaddress_-error_"+i).textContent = "<liferay-ui:message key='please-enter-address' />";
			errorMessage.push("<liferay-ui:message key='please-enter-address' />");
			$('#servantaddress_'+i).focus();
		}
		
		//validate servant dob
		var servantdob=$('#servantdob_'+i).val();
		if(!servantdob){
			document.getElementById("servantdob_-error_"+i).textContent = "<liferay-ui:message key='please-select-dob' />";
			errorMessage.push("<liferay-ui:message key='please-select-dob' />");
			$('#servantdob_'+i).focus();
		}else{
			document.getElementById("servantdob_-error_"+i).textContent = "";
		}
		
		//validate servant doj
		var servantdoj=$('#servantdoj_'+i).val();
		if(!servantdoj){
			document.getElementById("servantdoj_-error_"+i).textContent = "<liferay-ui:message key='please-select-doj' />";
			errorMessage.push("<liferay-ui:message key='please-select-doj' />");
			$('#servantdoj_'+i).focus();
		}else{
			document.getElementById("servantdoj_-error_"+i).textContent = "";
		}
	}
	return errorMessage;
}

function validateSportsPerson(tabname){
	//console.log("inside sports-person tab validation :::");
	
	var sportsPersonCount=$('#sportsPersonCount').val();
	console.log("validateSportsPerson ::sportsPersonCount :::" +sportsPersonCount);
	var errorMessage=[];
	
	for(var i=1;i<=sportsPersonCount;i++){
		
		var sportspersonselect=$('#sportspersonselect_'+i).val();
		
		console.log("sportspersonselect :::"+sportspersonselect);
		
		if(!sportspersonselect){
			document.getElementById("sportspersonselect_-error_"+i).textContent = "<liferay-ui:message key='please-select-sports-person' />";
			errorMessage.push("<liferay-ui:message key='please-select-district' />");
			$('#sportspersonselect_'+i).focus();
	    }else{
	    	document.getElementById("sportspersonselect_-error_"+i).textContent = "";
	    } 
		
		//validate sp name
		var spname=$('#sp_name_'+i).val();
		if(spname){
			var spNameVal=$.trim(spname.replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z .]*$/; 
			if(spNameVal){
				if(spNameVal.length<3){
					document.getElementById("sportspersonfullname_-error_"+i).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
					errorMessage.push("<liferay-ui:message key='please-enter-atleast-3-character' />");
					$('#sp_name_'+i).focus();
				}else if(!regex.test(spNameVal)){
					console.log("Error 2");
					document.getElementById("sportspersonfullname_-error_"+i).textContent ='<liferay-ui:message key="special-character-not-allowed" />';
					errorMessage.push("<liferay-ui:message key='special-character-not-allowed' />");
					$('#sp_name_'+i).focus();
				}else if(/([.\/ ])\1+/.test(spNameVal)){
					document.getElementById("sportspersonfullname_-error_"+i).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
					errorMessage.push("<liferay-ui:message key='two-special-character-not-allowed' />");
					$('#sp_name_'+i).focus();
				}else{
					console.log("No error ");
					document.getElementById("sportspersonfullname_-error_"+i).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				console.log("No error ");
				document.getElementById("sportspersonfullname_-error_"+i).textContent ='';
			}
		}else{
	    	console.log("inside else sp name");
			document.getElementById("sportspersonfullname_-error_"+i).textContent = "<liferay-ui:message key='please-enter-name' />";
			errorMessage.push("<liferay-ui:message key='please-enter-name' />");
			$('#sp_name_'+i).focus();
	    } 
			
		//validate sp dob
		var spdob=$('#sp_dob_'+i).val();
		if(spdob){
			console.log("spdob :::"+spdob);
			 var dob = new Date(spdob);
			 var today = new Date();
			 var tenYearsAgo = new Date(today.getFullYear() - 10, today.getMonth(), today.getDate());
			 if (dob > tenYearsAgo) {
				 document.getElementById("sportspersondob_-error_"+i).textContent = "<liferay-ui:message key='age-must-be-more-than-10-year' />";
		         $(this).val('');
		         errorMessage.push("<liferay-ui:message key='age-must-be-more-than-10-year' />");
		         $('#sp_dob_'+i).focus();
		     }else{
		    	 document.getElementById("sportspersondob_-error_"+i).textContent = "";
		     }
	    }else{
	    	document.getElementById("sportspersondob_-error_"+i).textContent = "<liferay-ui:message key='please-enter-dob' />";
	    	errorMessage.push("<liferay-ui:message key='please-enter-dob' />");
	    	 $('#sp_dob_'+i).focus();
	    } 
		
		
		//validate school name
		var spschool=$('#sp_school_'+i).val();
		if(spschool){
			var schoolNameVal=$.trim(spschool.replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z .()&,]*$/; 
			
			if(schoolNameVal.length<3){
				document.getElementById("sportspersonschoolname_-error_"+i).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				errorMessage.push("<liferay-ui:message key='please-enter-atleast-3-character' />");
				$('#sp_school_'+i).focus();
			}else if(!regex.test(schoolNameVal)){
				console.log("Error 2");
				document.getElementById("sportspersonschoolname_-error_"+i).textContent ='<liferay-ui:message key="special-character-not-allowed" />';
				errorMessage.push("<liferay-ui:message key='special-character-not-allowed' />");
				$('#sp_school_'+i).focus();
			}else if(/([.\/ ])\1+/.test(schoolNameVal)){
				document.getElementById("sportspersonschoolname_-error_"+i).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				errorMessage.push("<liferay-ui:message key='two-special-character-not-allowed' />");
				$('#sp_school_'+i).focus();
			}else{
				console.log("No error ");
				document.getElementById("sportspersonschoolname_-error_"+i).textContent ='<liferay-ui:message key="" />';
			}
			
	    }else{
	    	document.getElementById("sportspersonschoolname_-error_"+i).textContent = "<liferay-ui:message key='please-enter-school-name' />";
	    	errorMessage.push("<liferay-ui:message key='please-enter-school-name' />");
	    	$('#sp_school_'+i).focus();
	    } 
		
		//Validate mobile number
		var spmobile=$('#sp_mobile_'+i).val();
		if(spmobile){
			var mobileNumberPattern = /^[6-9][0-9]{0,9}$/; 
			if(!mobileNumberPattern.test(spmobile)){
				document.getElementById("sportspersonmobile_-error_"+i).textContent ='<liferay-ui:message key="please-enter-a-valid-mobile" />';
				errorMessage.push("<liferay-ui:message key='please-enter-a-valid-mobile' />");
				$('#sp_mobile_'+i).focus();
			}else if(spmobile.length<10){
				document.getElementById("sportspersonmobile_-error_"+i).textContent ='<liferay-ui:message key="please-enter-10-digit-mobile" />';
				errorMessage.push("<liferay-ui:message key='please-enter-10-digit-mobile' />");
				$('#sp_mobile_'+i).focus();
			}else{
				document.getElementById("sportspersonmobile_-error_"+i).textContent ='';
			}
	    }else{
	    	document.getElementById("sportspersonmobile_-error_"+i).textContent = "<liferay-ui:message key='please-enter-mobile' />";
			errorMessage.push("<liferay-ui:message key='please-enter-mobile' />");
			$('#sp_mobile_'+i).focus();
	    } 
		
		
		//validate sp address
		var spaddress=$('#sp_address_'+i).val();
		if(spaddress){
			var trimmedValue = $.trim(spaddress.replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
			var actualValue=trimmedValue.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
				if(actualValue){
					if(actualValue.length<10){
						document.getElementById("sportspersonaddress_-error_"+i).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
						errorMessage.push("<liferay-ui:message key='please-enter-minimum-10-character' />");
						$('#sp_address_'+i).focus();
					}else if (!regex.test(actualValue)) {
	       	            console.log("1 not allowed special character  ::");
	       	         	document.getElementById("sportspersonaddress_-error_"+i).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
	       	         	errorMessage.push("<liferay-ui:message key='given-special-character-not-allowed' />");
	       	      		$('#sp_address_'+i).focus();
	       			}else if(/([.\/ -,])\1+/.test(actualValue)){
	       	         	console.log("2 not allowed special character  ::");
	       	      		document.getElementById("sportspersonaddress_-error_"+i).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
	       	      		errorMessage.push("<liferay-ui:message key='consecutive-special-characters-not-allowed' />");
	       	    		 $('#sp_address_'+i).focus();
	       	        }else {
	       	         	console.log("allowed special character  ::");
	       	        	 document.getElementById("sportspersonaddress_-error_"+i).textContent = "";
	       	        }
				}else {
	       	         document.getElementById("sportspersonaddress_-error_"+i).textContent = "<liferay-ui:message key='please-enter-address' />";
	       	        errorMessage.push("<liferay-ui:message key='please-enter-address' />");
	       	     	$('#sp_address_'+i).focus();
   	        	}
	    }else{
	    	document.getElementById("sportspersonaddress_-error_"+i).textContent = "<liferay-ui:message key='please-enter-address' />";
			errorMessage.push("<liferay-ui:message key='please-enter-address' />");
			$('#sp_address_'+i).focus();
	    } 
		
		
		//validate sp entry date
		var spentrydate=$('#sp_entry_date_'+i).val();
		if(!spentrydate){
			document.getElementById("sportspersonentrydate_-error_"+i).textContent = "<liferay-ui:message key='please-select-entry-date' />";
			errorMessage.push("<liferay-ui:message key='please-select-entry-date' />");
			$('#sp_entry_date_'+i).focus();
	    }else{
	    	document.getElementById("sportspersonentrydate_-error_"+i).textContent = "";
	    } 
		
		//Validate sp ranking
		var spranking=$('#sp_ranking_'+i).val();
		
		console.log("spranking :::" +spranking);
		
		if(!spranking){
			/* var rankValue= spranking.replace(/[^a-zA-Z0-9]/g, '');
			if(!rankValue){
				  document.getElementById("sportspersonranking_-error_"+i).textContent = "<liferay-ui:message key='please-select-ranking' />";
				  errorMessage.push("<liferay-ui:message key='please-select-ranking' />");
				  $('#sp_ranking_'+i).focus();
			  }else{
				  document.getElementById("sportspersonranking_-error_"+i).textContent = "";
			  } */
			document.getElementById("sportspersonranking_-error_"+i).textContent = "<liferay-ui:message key='please-select-ranking' />";
			errorMessage.push("<liferay-ui:message key='please-select-ranking' />");
			 $('#sp_ranking_'+i).focus();
	    }else{
	    	 document.getElementById("sportspersonranking_-error_"+i).textContent = "";
	    } 
		
		
		//Validate achievement level
		var spachievement=$('#sp_achievement_'+i).val();
		
		console.log("spachievement :::" +spachievement);
		
		if(spachievement){
			var trimmedValue = $.trim(spachievement.replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z .,]*$/; 
			var actualValue=trimmedValue.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
			if(actualValue){
				if(actualValue.length<5){
					document.getElementById("sp_achievement_-error_"+i).textContent = "<liferay-ui:message key='please-enter-minimum-5-character' />";
					errorMessage.push("<liferay-ui:message key='please-enter-minimum-5-character' />");
					$('#sp_achievement_'+i).focus();
				}else if (!regex.test(actualValue)) {
       	            console.log("1 not allowed special character  ::");
       	         	document.getElementById("sp_achievement_-error_"+i).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
	       	         errorMessage.push("<liferay-ui:message key='given-special-character-not-allowed' />");
	     			$('#sp_achievement_'+i).focus();
       			}else if(/([.\/ -,])\1+/.test(actualValue)){
       	         	console.log("2 not allowed special character  ::");
       	      		document.getElementById("sp_achievement_-error_"+i).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
	       	      	errorMessage.push("<liferay-ui:message key='consecutive-special-characters-not-allowed' />");
	    			$('#sp_achievement_'+i).focus();
       	        }else {
       	         	console.log("allowed special character  ::");
       	         document.getElementById("sp_achievement_-error_"+i).textContent = "";
       	        }
			}else {
       	         document.getElementById("sp_achievement_-error_"+i).textContent = "<liferay-ui:message key='please-enter-achievement-level' />";
	       	      errorMessage.push("<liferay-ui:message key='please-select-achievement' />");
	  			$('#sp_achievement_'+i).focus();
   	        }
	    }else{
	    	document.getElementById("sp_achievement_-error_"+i).textContent = "<liferay-ui:message key='please-enter-achievement' />";
			errorMessage.push("<liferay-ui:message key='please-select-achievement' />");
			$('#sp_achievement_'+i).focus();
	    } 
		
		
		//validate coach remarks 
		var spremarks=$('#sp_remarks_'+i).val();
		if(spremarks){
			var trimmedValue = $.trim(spremarks.replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
			var actualValue=trimmedValue.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
			if(actualValue.length<10){
				document.getElementById("spc_remarks_error_"+i).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
				errorMessage.push("<liferay-ui:message key='please-enter-minimum-10-character' />");
				$('#sp_remarks_'+i).focus();
			}else if (!regex.test(actualValue)) {
   	         	document.getElementById("spc_remarks_error_"+i).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
   	         errorMessage.push("<liferay-ui:message key='given-special-character-not-allowed' />");
				$('#sp_remarks_'+i).focus();
   			}else if(/([.\/ -,])\1+/.test(actualValue)){
   	      		document.getElementById("spc_remarks_error_"+i).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
	   	      	errorMessage.push("<liferay-ui:message key='consecutive-special-characters-not-allowed' />");
				$('#sp_remarks_'+i).focus();
   	        }else {
   	         document.getElementById("spc_remarks_error_"+i).textContent = "";
   	        }
		}else{
			 document.getElementById("spc_remarks_error_"+i).textContent = "";
		}
		
	}//end of loop
	
	console.log("errorMessage in sports person:::" +errorMessage);
	
	return errorMessage;
}

function validateReports(tabname){
	console.log("Inside validate reports::::"+tabname);
	var errorMessage=[];
	
	$('.rattachmentRemarks').each(function(){
				//console.log("Code working");
				var remarksValue=$(this).val();
					var currentId=$(this).attr("id");
					$(this).val($.trim(remarksValue.replace(/\s{2,}/g, ' ')));	
					var trimmedValue = $.trim(remarksValue.replace(/\s{2,}/g, ' '));
					var counterRattachment=currentId.split("_");
					var regex =/^[a-zA-Z0-9 .,/-]*$/; 
					var actualValue=$(this).val();
				console.log("remarksValue.length :::" +remarksValue.length);
				if(remarksValue){
					if(remarksValue.length<2){
						document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "<liferay-ui:message key='please-enter-minimum-2-character' />";
						errorMessage.push("<liferay-ui:message key='please-enter-minimum-2-character' />");
					}else if (!regex.test(actualValue)) {
		   	            console.log("1 not allowed special character  ::");
		   	         	document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
		   	         	errorMessage.push("<liferay-ui:message key='given-special-character-not-allowed' />");
		   			}else if(/([.\/ -,])\1+/.test(actualValue)){
		   	         	console.log("2 not allowed special character  ::");
		   	      		document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
		   	      		errorMessage.push("<liferay-ui:message key='consecutive-special-characters-not-allowed' />");
		   	        } else {
		   	         	console.log("allowed special character  ::");
		   	         document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "";
		   	        }
				}else {
		   	         document.getElementById("attachment_remarks_error_"+counterRattachment[1]).textContent = "";
		        }
		});
	
	$('.rattachment').each(function(){
				 console.log("on change clicked for report attachment ::");
		   	   var currentId=$(this).attr("id");
		   	   console.log("currentId :::"+currentId);
		   	   var rattachmentFile=$('#'+currentId)[0].files[0];
		   	   var allowedExtensions = /(\.pdf)$/i;
		   	   var maxSize = 2*1024*1024; // 2 MB
		    	var counterReport=currentId.split("_");
		   	   if(rattachmentFile){
		   		   console.log("counterReport :::" +counterReport);
		   		   var rattachmentFileSize = $('#'+currentId)[0].files[0].size;
		   		   if (!allowedExtensions.exec($('#'+currentId)[0].files[0].name)) {
				        	//console.log("inside   attachment is present :::");
				        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "<liferay-ui:message key='please-select-pdf-file-only' />";
				    		errorMessage.push("<liferay-ui:message key='please-select-pdf-file-only' />");
				    		$(this).focus();
				        }else if (rattachmentFileSize>maxSize) {
				        	//console.log("inside else  if dpr");
				        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "<liferay-ui:message key='select-document-size-less-than-2-mb' />";
				    		errorMessage.push("<liferay-ui:message key='select-document-size-less-than-2-mb' />");
				    		$(this).focus();
				        }else{
				        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "";
				        }
		   	   }else{
		   			document.getElementById("attachment_error_"+counterReport[1]).textContent = "";
		   	   }
	 });
	return errorMessage;
}


function saveTrainingCenterDetails(event,formStatus,currentTab){
	
	console.log("saveTrainingCenterDetails event ::;:",event);
	console.log("saveTrainingCenterDetails formStatus ::;:",formStatus);
	console.log("saveTrainingCenterDetails currentTab ::;:",currentTab);
	
	var tabname=currentTab;
	
	if(tabname=="existingtrainingcentredetails"){
		var errorMessage=validateTrainingCenter(tabname);
		//console.log("errorMessage in errorMessage::::"+errorMessage);
		if(errorMessage.length>0){
			return false;
		}
	}else if(tabname=="coach"){
		//return false;
		var errorMessage=validateCoachDetail(tabname);
		//console.log("errorMessage in errorMessage::::"+errorMessage);
		if(errorMessage.length>0){
			return false;
		}
	}else if(tabname=="sports-person"){
		var errorMessage=validateSportsPerson(tabname);
		//console.log("errorMessage in sports-person::::"+errorMessage);
		if(errorMessage.length>0){
			return false;
		}
	}else if(tabname=="servant"){
		var errorMessage=validateServant(tabname);
		//console.log("errorMessage in sports-person::::"+errorMessage);
		if(errorMessage.length>0){
			return false;
		}
	}else if(tabname=="reports"){
		var errorMessage=validateReports(tabname);
		console.log("errorMessage in reports::::"+errorMessage);
		if(errorMessage.length>0){
			return false;
		}
	}else{
		console.log("correct tab");
	}
	
	var form = document.getElementById('trainingCentreForm');
	var formData = new FormData(form);	 
	
	formData.append("coachCount",coachCount);
	formData.append("formStatus",formStatus);
	formData.append("currentStatus",formStatus);
	formData.append("currentTab",currentTab);
	formData.append("testname",formStatus);
	
	$("#formStatusInput").val("SUBMIT");
	
	uploadedFilesGeoTagPhotos.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingGeoTagPhotos", fileObj.name);
	                formData.append("existingGeoTagPhotosURLs", fileObj.url);
	                formData.append("existingGeoTagPhotosIds", fileObj.id);
	               // console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("actualGeoTagPhotos", fileObj.file);
	                formData.append("actualGeoTagPhotosNames", fileObj.name);
	            }
	        }
    });
	 
	 var url='${saveCoachingWingURL}';
	   // console.log("url:"+url);
		 $.ajax({
			type: "POST",
	        url: '${saveCoachingWingURL}', 
	        data: formData,
  	       processData: false, 
  	      contentType: false, 
			async : false,
	        success: function(data) {
	            	//console.log("inside succeess form submit  :: "+data);
	            	var response=JSON.parse(data);
	            	//console.log("inside succeess  data.trainingCentreId  :: "+response.trainingCentreId);
	            	//window.location.href="http://localhost:8080/group/guest/sports-coaching-wing";
	             // var msg = data.status ? '<liferay-ui:message key="award-organization-id-"/>' + ' ' + data.awardYouthOrgId +  ' ' + '<liferay-ui:message key="-is-succesfully-submitted" />' : 'the-details-failed-to-submit';
	             $('#currentdrafttab').val(currentTab);
	             $('#trainingcenterDraftId').val(response.trainingCentreId);
	             $('#trainingCenterId').val(response.trainingCentreId);
	             $('.treining-center-details').find('input.trainingcenterId:hidden').val(response.trainingCentreId);
	             var msg="";
	             if(formStatus=='SUBMIT'){
	            	 msg = response.status ? '<liferay-ui:message key="training-center-id-"/>  ' + response.trainingCentreId  + '  <liferay-ui:message key="-is-succesfully-submitted" />' : 'the-details-failed-to-submit';
	             }else if(formStatus=='Draft'){
	            	 var msg = response.status ? '<liferay-ui:message key="training-center-id-"/>  ' + response.trainingCentreId  + '  <liferay-ui:message key="-is-save-as-draft" />' : 'the-details-failed-to-submit';
	             }
	        	 $('#success-application').html(msg);
		         $("#saveTrainingCenterModel").modal('show');
	        },error:function(resp){
	        	//console.log("inside error :: ");
	        }
	 }); 
}

	$('.mobcls').on('input',function(){
		$(this).val().replace(/[^\d.-]+/g, '');
	});

</script>

