<div class="tab-pane fade" id="servant" role="tabpanel" aria-labelledby="servant-tab">
          <div class="card card-background p-0">
                                <div class="servant_details">
                                  <div class="card-header header-card"><liferay-ui:message key="servant-details" /></div> 
                                     
                                  <div class="card-body">
	                          <%--         <div class="flex justify-between items-center">
	                                    		 <h2 class="form-section-title !border-0 !mb-0"></h2>
								              <c:if test="${mode eq 'edit' or mode eq 'add'}"><button type="button" id="addServantBtn" class="btn btn-secondary">+ Add More</button></c:if>
								       </div> --%>
                                <c:if test="${mode eq 'add'}"> 
                                   <div class="servantcontainer">
                                       <!-- Row 1 -->
								    <!-- <div class="servant-entry"> -->
								    <div class="row">
									    <div class="col-md-6">
									        <div class="form-group">
									            <label><liferay-ui:message key="name" /> <sup class="text-danger">*</sup></label>
									            <input type="text" class="form-control sernamecls" id="servantname_1" name="servantname_1" placeholder="Name" value="" maxlength="75">
									        	<p class="error-container" id="servantname_-error_1"></p>
									        </div>
									    </div>
							
									    <div class="col-md-6">
									        <div class="form-group">
									            <label><liferay-ui:message key="surname" /> <sup class="text-danger">*</sup></label>
									            <input type="text" class="form-control sersurnamecls" id="servantsurname_1" name="servantsurname_1" placeholder="Surname" value="" maxlength="75">
									        	<p class="error-container" id="servantsurname_-error_1"></p>
									        </div>
									    </div>
								
									    <div class="col-md-6">
										    <div class="form-group">
										        <label><liferay-ui:message key="address" /><sup class="text-danger">*</sup></label>
										        <input type="text" class="form-control seraddresscls" id="servantaddress_1" name="servantaddress_1" placeholder="Address" value="">
										    	<p class="error-container" id="servantaddress_-error_1"></p>
										    </div>
										</div>
									    <div class="col-md-6">
									        <div class="form-group">
									            <label><liferay-ui:message key="date-of-birth" /><sup class="text-danger">*</sup></label>
									            <input type="date" class="form-control servantdobcls" id="servantdob_1" name="servantdob_1" value="">
									            <p class="error-container" id="servantdob_-error_1"></p>
									        </div>
									    </div>
									    <div class="col-md-6">
									        <div class="form-group">
									            <label><liferay-ui:message key="date-of-joining" /><sup class="text-danger">*</sup></label>
									            <input type="date" class="form-control servantdojcls" id="servantdoj_1" name="servantdoj_1" value="">
									             <p class="error-container" id="servantdoj_-error_1"></p>
									        </div>
									    </div>
                                    </div>
                                    <!-- </div> -->
                                   </div>
                                   </c:if>
                                   <input type="hidden" id="servantcountvar" name="servantcount" value="1"/>
                                    
                                <c:if test="${mode eq 'edit' or mode eq 'view'}">
                                    <div class="servantcontainer">
		                                   	 <c:if test="${not empty servantDTOs}">
			                                 	 	<c:forEach items="${servantDTOs}" var="servantDTO" varStatus="servantDetailCounter">
			                                    		 <input type="hidden" value="${servantDTO.servantId}" name="servantId_${servantDetailCounter.count}" id="servantId_${servantDetailCounter.count}"/>
			                                    		<div class="servant-entry">
													    <div class="row">
														    <div class="col-md-6">
														        <div class="form-group">
														            <label><liferay-ui:message key="name" /> <sup class="text-danger">*</sup></label>
														            <input type="text" class="form-control sernamecls" id="servantname_${servantDetailCounter.count}" name="servantname_${servantDetailCounter.count}" maxlength="75" placeholder="enter-name" value="${servantDTO.name}" <c:if test="${mode == 'view'}">disabled</c:if>>
														        	<p class="error-container" id="servantname_-error_${servantDetailCounter.count}"></p>
														        </div>
														    </div>
										
														    <div class="col-md-6">
														        <div class="form-group">
														            <label><liferay-ui:message key="surname" /> <sup class="text-danger">*</sup></label>
														            <input type="text" maxlength="75" class="form-control sersurnamecls" id="servantsurname_${servantDetailCounter.count}" name="servantsurname_${servantDetailCounter.count}" placeholder="enter-surname" value="${servantDTO.surname}" <c:if test="${mode == 'view'}">disabled</c:if>>
														        	<p class="error-container" id="servantsurname_-error_${servantDetailCounter.count}"></p>
														        </div>
														    </div>
											
														    <div class="col-md-6">
															    <div class="form-group">
															        <label><liferay-ui:message key="address" /><sup class="text-danger">*</sup></label>
															        <input type="text" maxlength="250" class="form-control seraddresscls" id="servantaddress_${servantDetailCounter.count}" name="servantaddress_${servantDetailCounter.count}" placeholder="enter-address" value="${servantDTO.address}" <c:if test="${mode == 'view'}">disabled</c:if>>
															    	<p class="error-container" id="servantaddress_-error_${servantDetailCounter.count}"></p>
															    </div>
															</div>
														    <div class="col-md-6">
														        <div class="form-group">
														            <label><liferay-ui:message key="date-of-birth" /><sup class="text-danger">*</sup></label>
														            <input type="date" class="form-control servantdobcls" id="servantdob_${servantDetailCounter.count}" name="servantdob_${servantDetailCounter.count}" value="${servantDTO.dobstr}" <c:if test="${mode == 'view'}">disabled</c:if>>
														        	 <p class="error-container" id="servantdob_-error_${servantDetailCounter.count}"></p>
														        </div>
														    </div>
														    <div class="col-md-6">
														        <div class="form-group">
														            <label><liferay-ui:message key="date-of-joining" /><sup class="text-danger">*</sup></label>
														            <input type="date" class="form-control servantdojcls" id="servantdoj_${servantDetailCounter.count}" name="servantdoj_${servantDetailCounter.count}" value="${servantDTO.dojstr}" <c:if test="${mode == 'view'}">disabled</c:if>>
														        	 <p class="error-container" id="servantdoj_-error_${servantDetailCounter.count}"></p>
														        </div>
														    </div>
			                                    		</div>
			                                    	</div>
			                                    </c:forEach>
		                                 </c:if>
		                                 
		                                  <c:if test="${empty servantDTOs}">
		                                  		<div class="servant-entry">
												    <div class="row">
													    <div class="col-md-6">
													        <div class="form-group">
													            <label><liferay-ui:message key="name" /> <sup class="text-danger">*</sup></label>
													            <input type="text" class="form-control sernamecls" id="servantname_1" name="servantname_1" placeholder="enter-name" value="">
													            <p class="error-container" id="servantname_-error_1"></p>
													        </div>
													    </div>
									
													    <div class="col-md-6">
													        <div class="form-group">
													            <label><liferay-ui:message key="surname" /> <sup class="text-danger">*</sup></label>
													            <input type="text" class="form-control sersurnamecls" id="servantsurname_1" name="servantsurname_1" placeholder="enter-surname" value="">
													            <p class="error-container" id="servantsurname_-error_1"></p>
													        </div>
													    </div>
										
													    <div class="col-md-6">
														    <div class="form-group">
														        <label><liferay-ui:message key="address" /><sup class="text-danger">*</sup></label>
														        <input type="text" class="form-control seraddresscls" id="servantaddress_1" name="servantaddress_1" placeholder="enter-address" value="" maxlength="250">
														    	<p class="error-container" id="servantaddress_-error_1"></p>
														    </div>
														</div>
													    <div class="col-md-6">
													        <div class="form-group">
													            <label><liferay-ui:message key="date-of-birth" /><sup class="text-danger">*</sup></label>
													            <input type="date" class="form-control servantdobcls" id="servantdob_1" name="servantdob_1" value="" >
													            <p class="error-container" id="servantdob_-error_1"></p>
													        </div>
													    </div>
													    <div class="col-md-6">
													        <div class="form-group">
													            <label><liferay-ui:message key="date-of-joining" /><sup class="text-danger">*</sup></label>
													            <input type="date" class="form-control servantdojcls" id="servantdoj_1" name="servantdoj_1" value="" >
													            <p class="error-container" id="servantdoj_-error_1"></p>
													        </div>
													    </div>
		                                    		</div>
		                                    	</div>
		                                  </c:if>
                               		 </div>
                         </c:if>
                         
                         		<div class="text-right">
					  				 <c:if test="${mode eq 'edit' or mode eq 'add'}"><button type="button" id="addServantBtn" class="btn btn-secondary">+ Add More</button></c:if>
								</div>
                                </div>
                            </div>
                            </div>
                           <div class="card-footer bg-transparent text-right p-4">
				               	   <c:if test="${mode eq 'add' or mode eq 'edit'}">
					               	  <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
					                       	<liferay-ui:message key="cancel" />
					                	</button>
					               		 <button class="btn btn-primary" type="button" onclick="location.reload();">
											<liferay-ui:message key="reset" />
										</button>
								 	 	  <button type="button" class="btn btn-primary" onclick="saveTrainingCenterDetails(event,'Draft','servant')">
					                            <liferay-ui:message key="save-as-draft" />
					                   </button> 
					                  <button type="button" class="btn btn-primary" onclick="setActiveTab('sports-person','servant','previous')">
					                       <liferay-ui:message key="previous" />
					                   </button>
					                  <button type="button" class="btn btn-primary" onclick="setActiveTab('financial-details','servant','next')">
					                     <liferay-ui:message key="next" />
					                 </button>
				                 </c:if>
				                  <c:if test="${mode eq 'view'}">
					                   <button type="button" class="btn btn-primary" onclick="setActiveTab('sports-person','servant','previous')">
					                       <liferay-ui:message key="previous" />
					                   </button>
					                  <button type="button" class="btn btn-primary" onclick="setActiveTab('financial-details','servant','next')">
					                     <liferay-ui:message key="next" />
					                 </button>
				                 </c:if>
              				</div>
</div>


<!-- modal popup for  -->
<div class="modal fade" id="servantModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="sports-coaching-wing"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
                                    	<p><liferay-ui:message key="please-save-as-draft-to-calculate-the-financial" /> </p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
					 <a href="javascript:void(0)" type="button" id="y" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeServantModal('servantModel','/group/guest/sports-coaching-wing-list')"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for  -->   




<script>

function closeServantModal(id,redirectUrl) {
    $("#"+id).modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}

var mode='${mode}';
var servantDTOCount='${servantDTOCount}';
console.log("mode in servant ::::" +mode);
console.log("servantDTOCount in servant ::::" +servantDTOCount);

	var servantCount=1;
	if(mode=='edit'){
		servantCount=servantDTOCount;
	}
	$(document).ready(function(){
		 var today = new Date();
      	 var year = today.getFullYear();
      	 var month = String(today.getMonth() + 1).padStart(2, '0'); // Months are 0-indexed
      	 var day = String(today.getDate()).padStart(2, '0');
      	 var formattedDate = year+'-'+month+'-'+day;
         console.log("formattedDate :::" +formattedDate);
		
		$('.servantdobcls').each(function(){
			$(this).attr('max',formattedDate);
		});
		
		
		$('.servantdojcls').each(function(){
			$(this).attr('max',formattedDate);
		});
		
		
        $(document).on('click', '.remove-servant-btn', function() {
        	servantCount=servantCount-1;
        	console.log("remove servant clicked :::");
            $(this).parent('.servant-entry').find('input, textarea, select').each(function() {
                $(this).rules('remove');
            });
            $(this).closest('.servant-entry, .sp-entry').remove(); 
            $('#servantcountvar').val(servantCount);
            console.log("servantCount remove:::" +servantCount);
        });
				
		$('#addServantBtn').click(function(){
				if(servantCount<3){
					console.log("servantCount :::" +servantCount);
					servantCount=servantCount+1;
					var removebtnhtml="";
						if(servantCount>1){
		        			removebtnhtml='<button type="button"  class="remove-servant-btn remove-btn"><i class="bi bi-x-circle text-danger"></i></button>';	
		        		}
						var servantHTML='<div class="servant-entry">'+
						'<div class="text-right">'+removebtnhtml+'</div>'+
						'<div class="row">'+
						   '<div class="col-md-6">'+
						        '<div class="form-group">'+
							           '<label><liferay-ui:message key="name" /> <sup class="text-danger">*</sup></label>'+
							            '<input type="text" class="form-control sernamecls" id="servantname_'+servantCount+'" name="servantname_'+servantCount+'" placeholder="enter-name" value="">'+
							            '<p class="error-container" id="servantname_-error_'+servantCount+'"></p>'+
						         '</div>'+
				    		'</div>'+
						    '<div class="col-md-6">'+
						        '<div class="form-group">'+
						            '<label><liferay-ui:message key="surname" /> <sup class="text-danger">*</sup></label>'+
						            '<input type="text" class="form-control sersurnamecls" id="servantsurname_'+servantCount+'" name="servantsurname_'+servantCount+'" placeholder="enter-surname" value="">'+
						            '<p class="error-container" id="servantsurname_-error_'+servantCount+'"></p>'+
					           ' </div>'+
						    '</div>'+
						    '<div class="col-md-6">'+
							    '<div class="form-group">'+
								       ' <label><liferay-ui:message key="address" /><sup class="text-danger">*</sup></label>'+
								        '<input type="text" class="form-control seraddresscls" id="servantaddress_'+servantCount+'" name="servantaddress_'+servantCount+'" placeholder="enter-address" value="">'+
								        '<p class="error-container" id="servantaddress_-error_'+servantCount+'"></p>'+
							       '</div>'+
							'</div>'+
						    '<div class="col-md-6">'+
						       ' <div class="form-group">'+
						            '<label><liferay-ui:message key="date-of-birth" /><sup class="text-danger">*</sup></label>'+
						           ' <input type="date" class="form-control servantdobcls" id="servantdob_'+servantCount+'" name="servantdob_'+servantCount+'" value="" max='+formattedDate+'>'+
						           '<p class="error-container" id="servantdob_-error_'+servantCount+'"></p>'+
						          ' </div>'+
						    '</div>'+
						    '<div class="col-md-6">'+
						        '<div class="form-group">'+
						           ' <label><liferay-ui:message key="date-of-joining" /><sup class="text-danger">*</sup></label>'+
						           ' <input type="date" class="form-control servantdojcls" id="servantdoj_'+servantCount+'" name="servantdoj_'+servantCount+'" value="" max='+formattedDate+'>'+
						           '<p class="error-container" id="servantdoj_-error_'+servantCount+'"></p>'+
						          '</div>'+
						   ' </div>'+
				   ' </div>'+
	            '</div>';
				$('.servantcontainer').append(servantHTML);	
				 $('#servantcountvar').val(servantCount);
			}	
		});
		
		
		//Servant Name 
		$(document).on('keyup','.sernamecls',function(){
			var servantNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z . ]*$/; 
			
			if(servantNameVal){
				if(servantNameVal.length<3){
					document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(servantNameVal)){
					document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-alphabate-only" />';
				}else if(/([.\/ ])\1+/.test(servantNameVal)){
					document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-name" />';
			}
		});
		
		//blur event of Servant name
		$(document).on('blur','.sernamecls',function(){
			var servantNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			$(this).val(servantNameVal);
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z . ]*$/; 
			if(servantNameVal){
				if(servantNameVal.length<3){
					document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(servantNameVal)){
					document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-alphabate-only" />';
				}else if(/([.\/ ])\1+/.test(servantNameVal)){
					document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				document.getElementById("servantname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-name" />';
			}
		});
		
		//surname
		$(document).on('keyup','.sersurnamecls',function(){
			var servantNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z . ]*$/; 
			
			if(servantNameVal){
				if(servantNameVal.length<3){
					document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(servantNameVal)){
					document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-alphabate-only" />';
				}else if(/([.\/ ])\1+/.test(servantNameVal)){
					document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-surname" />';
			}
		});
		
		//blur event of Servant name
		$(document).on('blur','.sersurnamecls',function(){
			var servantNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			$(this).val(servantNameVal);
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z . ]*$/; 
			if(servantNameVal){
				if(servantNameVal.length<3){
					document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(servantNameVal)){
					document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-alphabate-only" />';
				}else if(/([.\/ ])\1+/.test(servantNameVal)){
					document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				document.getElementById("servantsurname_-error_"+currentCounter[1]).textContent ='<liferay-ui:message key="please-enter-surname" />';
			}
		});
		
		//Address
		 $(document).on('keyup','.seraddresscls',function(){
				var addressValue=$(this).val();
	   			var currentId=$(this).attr("id");
	   			var trimmedValue = $.trim(addressValue.replace(/\s{2,}/g, ' '));
	   			var counterAddress=currentId.split("_");
	   			var regex =/^[a-zA-Z0-9 .,/#-]*$/; 
	   			var actualValue1=$(this).val();
	   			var actualValue=actualValue1.replace(/[^a-zA-Z0-9\s.,/#-]|(?<!\.)$/g, '');
	   			$(this).val(actualValue);
				console.log("actualValue.length :::" +actualValue.length);
				if(actualValue){
					if(actualValue.length<10){
						document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
					}else if (!regex.test(actualValue)) {
	       	            console.log("1 not allowed special character  ::");
	       	         	document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
	       			}else if(/([.\/ -,])\1+/.test(actualValue)){
	       	         	console.log("2 not allowed special character  ::");
	       	      		document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
	       	        }else {
	       	         	console.log("allowed special character  ::");
	       	         document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "";
	       	        }
				}else {
					 document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "<liferay-ui:message key='please-enter-address' />";
	   	        }
			});
			
			$(document).on('blur','.seraddresscls',function(){
				var addressValue=$(this).val();
	   			var currentId=$(this).attr("id");
	   			$(this).val($.trim(addressValue.replace(/\s{2,}/g, ' ')));	
	   			var trimmedValue = $.trim(addressValue.replace(/\s{2,}/g, ' '));
	   			var counterAddress=currentId.split("_");
	   			var regex =/^[a-zA-Z0-9 .,/#-]*$/; 
	   			var actualValue=$(this).val();
				console.log("actualValue.length :::" +actualValue.length);
				
				if(actualValue){
					if(actualValue.length<10){
						document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
					}else if (!regex.test(actualValue)) {
	       	            console.log("1 not allowed special character  ::");
	       	         	document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
	       			}else if(/([.\/ -,])\1+/.test(actualValue)){
	       	         	console.log("2 not allowed special character  ::");
	       	      		document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
	       	        }else {
	       	         	console.log("allowed special character  ::");
	       	         document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "";
	       	        }
				}else {
	       	         document.getElementById("servantaddress_-error_"+counterAddress[1]).textContent = "<liferay-ui:message key='please-enter-address' />";
	   	        }
			});
			
			
			$(document).on('change','.servantdobcls',function(){
				console.log("Dob Change event");
				var edVal=$(this).val();
				var dobid=$(this).prop('id');
				var idcounter=dobid.split('_');
				var idvalue=idcounter[1];
				if(edVal){
					console.log("edVal :::"+edVal);
					 var dob = new Date(edVal);
					 var today = new Date();
					 var tenYearsAgo = new Date(today.getFullYear() - 10, today.getMonth(), today.getDate());
					 if (dob > tenYearsAgo) {
						 document.getElementById("servantdob_-error_"+idvalue).textContent = "<liferay-ui:message key='age-must-be-more-than-10-year' />";
				         $(this).val(''); 
				     }else{
				    	 document.getElementById("servantdob_-error_"+idvalue).textContent = "";
				     }
				}else{
					document.getElementById("servantdob_-error_"+idvalue).textContent = "<liferay-ui:message key='please-select-dob' />";
					
				}
		});
			
			
			$(document).on('change','.servantdojcls',function(){
				var edVal=$(this).val();
				var dobid=$(this).prop('id');
				var idcounter=dobid.split('_');
				var idvalue=idcounter[1];
				if(!edVal){
					document.getElementById("servantdoj_-error_"+idvalue).textContent = "<liferay-ui:message key='please-select-doj' />";
				}else{
					document.getElementById("servantdoj_-error_"+idvalue).textContent = "";
				}
		});
			
			 
	//
	/* $('#showAllServantBtn').click(function(){
		$('.servother').each(function(){
			$(this).removeClass("d-none");
		});
		$('#showAllServantBtn').addClass("d-none");
		$('#hideAllServantBtn').removeClass("d-none");
	});
			 
	$('#hideAllServantBtn').click(function(){
		$('.servother').each(function(){
			$(this).addClass("d-none");
		});
		$('#showAllServantBtn').removeClass("d-none");
		$('#hideAllServantBtn').addClass("d-none");
	});	 */	 
		
});

</script>
