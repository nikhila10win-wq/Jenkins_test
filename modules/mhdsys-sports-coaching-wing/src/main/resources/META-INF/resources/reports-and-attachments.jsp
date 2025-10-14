 <div class="tab-pane fade" id="reports-and-attachments" role="tabpanel" aria-labelledby="reports-and-attachments-tab">
         <div class="card card-background p-0">
                   <div class="personal_details">
                            <div class="card-header header-card"><liferay-ui:message key="reports-and-attachments" /></div>
                                  <div class="card-body">
                                        <!-- <div class="form-section"> -->
                                       <div class="">
						                  <!--   <h2 class="form-section-title">Reports and Attachments</h2> -->
					                   	 <div class="space-y-6">
						                        <c:if test="${mode eq 'edit' or mode eq 'view'}">
				                        			<c:if test="${not empty reportsAttachments}">
						                        			<div id="reportsContainer1">
						                        				<c:forEach items="${reportsAttachments}" var="reports" varStatus="rcounter">	
							                        				 <input type="hidden" value="${reports.reportId}" name="reportAtcId_${rcounter.count}" id="fdId_${rcounter.count}"/>
							                        				 <input type="hidden" value="${reports.attachmentPath}" name="attachmentPathId_${rcounter.count}" id="attachmentPathId_${rcounter.count}"/>
							                        				 
							                        				 <div class="attachment-group ">
													                    <label class="form-label  ">${reports.description}</label>
													                    <textarea name="remarks_${rcounter.count}" rows="3" class="form-textarea" placeholder="Enter remarks..." <c:if test="${mode == 'view'}">disabled</c:if>>${reports.remarks}</textarea>
													                    
													                    <!-- <div class="mt-2">
													                        <label class="form-label text-sm">Attachment (if any)</label>
													                        <input type="file" name="attachment_'+idSuffix+'" class="form-attachment">
													                    </div> -->
													                    
													                    <div class="custom-file">
																	        <input type="file" class="custom-file-input rattachment" id="attachment_${rcounter.count}" name="attachment_${rcounter.count}"
																	            onchange="handleReportFileUpload(event, 'attachment_${rcounter.count}', 'reportsPreviewContainer${rcounter.count}', 'reportsPreviewLink${rcounter.count}', 'reportsNewDeleteButton${rcounter.count}','attachmentPathId_${rcounter.count}')" <c:if test="${mode == 'view'}">disabled</c:if>>
																	        <label class="custom-file-label" for="attachment_${rcounter.count}">
																	           Attachment (if any) <liferay-ui:message key="choose-file" />
																	        </label>
																	        <p class="error-container" id="attachment_error_${rcounter.count}"></p>
																    	</div>
																    	
									                    				 <%-- Show existing file with delete option if available --%>
											                            <div class="ownerProofid d-flex mt-3" id="reportsPreviewContainer${rcounter.count}">
																              <c:if test="${not empty reports.attachmenturl}"> 
																	             <a class="reportsCls" id="reportsPreviewLink${rcounter.count}"
																	                href="${reports.attachmenturl}" target="_blank"
																	                style="flex-grow: 1; text-decoration: none; word-wrap: break-word; white-space: nowrap; overflow: hidden; max-width: 200px;">
																	                ${reports.attachmentName}
																	            </a>
																	             <button type="button" id="reportsdeleteButton${rcounter.count}" class="reportsUploadBtn close"
																	                aria-label="Close" onclick="deleteFile('reportsPreviewContainer${rcounter.count}', 'attachment_${rcounter.count}')">
																	                <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
																	            </button>
																             </c:if> 
																        </div>
															        <%-- Preview of new file to be uploaded --%>
																    <div class="ownerProofid d-none mt-3" id="reportsPreviewContainer${rcounter.count}">
																        <a class="reportsCls" id="reportsUploadNewPreviewLink${rcounter.count}" href="" target="_blank"
																            style="flex-grow: 1; text-decoration: none;"></a>
																        <button type="button" id="reportsNewDeleteButton${rcounter.count}" class="reportsUploadBtn close"
																            aria-label="Close" onclick="deleteFile('reportsPreviewContainer${rcounter.count}', 'attachment_${rcounter.count}')">
																            <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
																        </button>
													                </div>
													               </div>
													                </c:forEach>
													              </div>
				                        			</c:if>
					                        		<c:if test="${empty reportsAttachments}">
					                        			<div id="reportsContainer1">
					                        			</div>
					                        		</c:if>
						                        </c:if>
						                        <c:if test="${mode eq 'add'}">
						                        	<div id="reportsContainer">
						                        	
						                        	</div>
						                        </c:if>
						                    </div>
						                </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${mode eq 'add' or mode eq 'edit'}">
                            <div class="card-footer bg-transparent text-right p-4">
                            	
                            		<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
                                	<liferay-ui:message key="cancel" />
                           		 </button>
                           		 <button class="btn btn-primary" type="button"
								onclick="location.reload();">
								<liferay-ui:message key="reset" />
							</button>
							 <!-- <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Draft')"> -->
							   <button type="button" class="btn btn-primary" onclick="saveTrainingCenterDetails(event,'Draft','reports')">
                                    <liferay-ui:message key="save-as-draft" />
                                </button> 
                               <button type="button" class="btn btn-primary" onclick="setActiveTab('financial-details','reports-and-attachments','previous')">
                                    <liferay-ui:message key="previous" />
                                </button>
                                
                              <!--   <button type="button" class="btn btn-primary" onclick="saveTrainingCenterDetails(event,'save','reports')">
	                                <liferay-ui:message key="save" />
	                          </button>
	                           -->
	                           <input type="hidden" value="" name="formStatus" id="formStatus"/>
	                           <input type="hidden" value="" name="formStatusInput" id="formStatusInput"/>
	                           
	                           
                                <button type="button" id="tc-save-btn" class="btn btn-primary" onclick="saveTrainingCenterDetails(event,'SUBMIT','reports')"><liferay-ui:message key="submit" /></button> 
                                <!-- <button type="submit" id="tc-save-btn" class="btn btn-primary"><liferay-ui:message key="submit" /></button>  -->
                                
	                                <!--  <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Draft')"> -->
	                            	 <!-- Submission -->
						               <!--  <div class="flex justify-end mt-8">
						                    <button type="button" id="addcoachingwing" class="btn btn-primary" onclick="saveTrainingCenterDetails(event,'save','reports')">Submit Form</button>
						                </div> -->
					         
					          <%-- <c:if test="${mode eq 'view' and isDSO}">
						           <button type="button" class="btn btn-primary" onclick="setActiveTab('financial-details','reports-and-attachments','previous')">
	                                    <liferay-ui:message key="previous" />
	                                </button>
					          </c:if> --%>
                            </div>
                             </c:if>
                            <c:if test="${mode eq 'view' and isDSO}">
                             <div class="card-footer bg-transparent text-right p-4">
                             <button type="button" class="btn btn-primary" onclick="setActiveTab('financial-details','reports-and-attachments','previous')">
                                    <liferay-ui:message key="previous" />
                                </button>
                            </div>
                             </c:if>
                            
                          <!-- ddd review form -->
                          <c:if test="${isdistdeputydirector  or ishoadmin}">
				           	 <%@include file="deputy-director-review-form.jspf" %>
				       	 	</c:if>
                          
                          <c:if test="${ishoadmin}">
				           	
				           	<%@include file="head-office-review-form.jspf" %>
				       	 	</c:if>
                          
                          <!-- ho  review form -->
                            
                        </div>
                        
                        
<!-- modal popup for  -->
<div class="modal fade" id="saveTrainingCenterModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<input type="hidden" name="currentdrafttab" value="" id="currentdrafttab"/>
			<input type="hidden" name="trainingcenterDraftId" value="" id="trainingcenterDraftId"/>
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
										<%-- <img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup> --%>
                                    	<p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
					 <a href="javascript:void(0)" type="button" id="y" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveTrainingCenterModel','/group/guest/sports-coaching-wing-list')"><liferay-ui:message key="close"/></a>
					    <%--  <c:if test="${isSportsPerson }">
					      <a href="/group/guest/dashboard" type="button" id="y" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveTrainingCenterModel','/group/guest/dashboard')"><liferay-ui:message key="close"/></a>
					     </c:if>
       					 <c:if test="${isDDD }">
					      <a href="/group/guest/application-certificate-verification-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveTrainingCenterModel','/group/guest/application-certificate-verification-list')"><liferay-ui:message key="close"/></a>
					     </c:if> --%>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for  -->                        
<script>                   
	
/* function closeModal(id,redirectUrl) {
    $("#"+id).modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
	 var currentdrafttab=$('#currentdrafttab').val();
	 console.log("currentdrafttab :::" +currentdrafttab);
	 if(currentdrafttab!='servant'){
		 console.log("Current tab is not servant");
		 if (redirectUrl) {
		     console.log("inside redirect url after Current tab is not servant");   
			 window.location.href = redirectUrl;
		}	 
	 }else{
		 console.log("Current tab is servant");
		 
	 }
} */

function closeModal(id,redirectUrl) {
    $("#"+id).modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
	 if (redirectUrl) {
	        window.location.href = redirectUrl;
	    }
}


	
//Single file upload
	function handleReportFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn,aid) {
  
	    $("#"+aid).val(0);
  
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
	
	
	$(document).ready(function(){
		$("#tc-save-btn1").click(function(e){
			//var form = document.getElementById('trainingCentreForm');
			//var formData = new FormData(form);	 
			//formData.append("coachCount",coachCount);
			//formData.append("formStatus","Draft");
			//formData.append("currentStatus",formStatus);
			//formData.append("currentTab",currentTab);
			//formData.append("testname","Draft");
		/* 	const input = document.createElement('input');
			input.type = 'file';
			input.name = 'actualtestGeoTagPhotos';
			
			uploadedFilesGeoTagPhotos.forEach((fileObj) => {
			        if (fileObj && !fileObj.markedForDelete) {
			        	
			        	input.value = fileObj.file;
			        	
			            if (fileObj.isExisting) {
			                formData.append("existingGeoTagPhotos", fileObj.name);
			                formData.append("existingGeoTagPhotosURLs", fileObj.url);
			                formData.append("existingGeoTagPhotosIds", fileObj.id);
			                console.log("Id : "+fileObj.id);
			            } else {
			                formData.append("actualGeoTagPhotos", fileObj.file);
			                formData.append("actualGeoTagPhotosNames", fileObj.name);
			            }
			        }
		    }); */
			

			// Add hidden input dynamically
			
			//form.appendChild(input);

			// Submit the form
			// // Regular submit
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
				//console.log("actualValue :::",actualValue);
				//console.log("actualValue :::",regex.test(actualValue));
				//console.log("remarksValue :::" +remarksValue);
				//console.log("currentId :::" +currentId);
				//console.log("actualValue :::" +actualValue);
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
				//console.log("errorMessage in loop::" +errorMessage);
			});
			
			$('.rattachment').each(function(){
				 console.log("on change clicked for report attachment ::");
		    	   var currentId=$(this).attr("id");
		    	   var rattachmentFile=$('#'+currentId)[0].files[0];
		    	  // console.log("rattachmentFile :::" +rattachmentFile);
		    	   var allowedExtensions = /(\.pdf)$/i;
		    	   var maxSize = 2*1024*1024; // 2 MB
		    	   if(rattachmentFile){
		    		   var counterReport=currentId.split("_");
		    		  // console.log("counterReport :::" +counterReport);
		    		   var rattachmentFileSize = $('#'+currentId)[0].files[0].size;
		    		   if (!allowedExtensions.exec($('#'+currentId)[0].files[0].name)) {
				        	//console.log("inside   attachment is present :::");
				        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "<liferay-ui:message key='please-select-pdf-file-only' />";
				    		errorMessage.push("<liferay-ui:message key='please-select-pdf-file-only' />");
				        }else if (rattachmentFileSize>maxSize) {
				        	//console.log("inside else  if dpr");
				        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "<liferay-ui:message key='select-document-size-less-than-2-mb' />";
				    		errorMessage.push("<liferay-ui:message key='select-document-size-less-than-2-mb' />");
				        }else{
				        	document.getElementById("attachment_error_"+counterReport[1]).textContent = "";
				        }
		    	   }
			 });
			
			
			//console.log("errorMessage ::"+errorMessage);
			console.log("errorMessage ::"+errorMessage.length);
			
			if(errorMessage.length>0){
				console.log("inside if error message length is greater");
				e.preventDefault();
				return false;
			}else{
				console.log("inside else error message length is 0");
				$("#formStatus").val("SUBMIT");
				form.submit();
			}
		});
		
	});
</script>                   
                      
                        