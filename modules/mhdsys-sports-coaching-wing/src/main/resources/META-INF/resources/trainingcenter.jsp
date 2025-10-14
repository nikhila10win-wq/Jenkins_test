<div class="tab-pane fade show active" id="existingtrainingcentredetails" role="tabpanel" aria-labelledby="existingtrainingcentredetails-tab">
                            <div class="card card-background p-0">
                                <div class="treining-center-details">
                                	 <input class="trainingcenterId" type="hidden" name="trainingcenterId" id="trainingcenterId" value="${trainingCenterDTO.trainingCentreId}"/> 
                                	<input type="hidden" name="geoTagPhotosEntryIds" id="geoTagPhotosEntryIds" value="${trainingCenterDTO.geoTagPhotosIds}"/>
                                	<input type="hidden" name="dprDocPath" id="dprDocPath" value="${trainingCenterDTO.dprDocPath}"/>
                                     <div class="card-header header-card"><liferay-ui:message key="existing-training-centre-details" /></div> 
                                    <div class="card-body">
                                        <!-- Row 1 -->
                                        <!-- <div class="row"> -->
	                                          <!-- <div class="form-section"> -->
	                                      <div class="row"> 
	                                      		<div class="col-md-6">
	                                      			<div class="form-group">
							                            <label for="division" class="form-label"><liferay-ui:message key="division" /><sup class="text-danger">*</sup></label>
							                            <select id="division" id="division" name="division" class="form-control" <c:if test="${mode == 'view'}">disabled</c:if>>
							                                <option value=""><liferay-ui:message key="select-division" /></option>
							                               	  <c:forEach items="${divisions}" var="division">
							                            			<option value="${division.divisionId}" <c:if test="${trainingCenterDTO.division == division.divisionId}">selected</c:if>>${division.divisionName_en}</option>
							                            		</c:forEach>	
							                            </select>
							                            <p class="error-container" id="division-error"></p>
							                        </div>
	                                      		</div>
	                                      		
	                                      		<div class="col-md-6">
	                                      			<div class="form-group">
	                                      				 <label for="district" class="form-label"><liferay-ui:message key="district" /><sup class="text-danger">*</sup></label>
							                            <select id="district" name="district" class="form-control" <c:if test="${mode == 'view'}">disabled</c:if>>
							                                <option value=""><liferay-ui:message key="select-district" /></option>
							                                 <c:if test="${mode == 'view'}">
								                            			<option selected>${trainingCenterDTO.districtName}</option>
							                            	 </c:if>
							                            	 <c:if test="${mode == 'edit'}">
						                            	 		 <c:forEach items="${districts}" var="districts">
							                            			<option value="${districts.districtId}" <c:if test="${trainingCenterDTO.district == districts.districtId}">selected</c:if>>${districts.districtName_en}</option>
							                            		</c:forEach>	
							                            	 </c:if>	
							                            </select>
							                             <p class="error-container" id="district-error"></p>
	                                      			
	                                      			</div>
	                                      		</div>
	                                      		
	                                      		
	                                      		<div class="col-md-6">
	                                      			<div class="form-group">
	                                      			
	                                      				 <label for="district" class="form-label"><liferay-ui:message key="training-center-name" /><sup class="text-danger">*</sup></label>
	                                      				 <div>
	                                      				<label class="flex items-center"><input <c:if test="${mode == 'view'}">disabled</c:if>  type="radio" name="centre_type" value="talent_dev" class="mr-2" <c:if test="${trainingCentre.centreType eq 'talent_dev'}">checked</c:if>>District Sport Talent Development Centre</label>
							                            <label class="flex items-center"><input <c:if test="${mode == 'view'}">disabled</c:if> type="radio" name="centre_type" value="coaching" class="mr-2" <c:if test="${trainingCentre.centreType eq 'coaching'}">checked</c:if>>District Coaching Centre</label>
							                            <label class="flex items-center"><input <c:if test="${mode == 'view'}">disabled</c:if> type="radio" name="centre_type" value="khelo_india" class="mr-2" <c:if test="${trainingCentre.centreType eq 'khelo_india'}">checked</c:if>>Khelo India District Centre</label>
							                            <label class="flex items-center"><input <c:if test="${mode == 'view'}">disabled</c:if> type="radio" name="centre_type" value="taluka" class="mr-2" <c:if test="${trainingCentre.centreType eq 'taluka'}">checked</c:if>>Taluka Coaching Centre</label>
							                        	<p class="error-container" id="training-center-type-error"></p>
	                                      			</div>
	                                      			</div>
	                                      		</div>
	                                      		
	                                      		<div class="col-md-6">
	                                      			<div class="form-group">
	                                      				 <label for="sports_type" class="form-label">Sports Type <sup class="text-danger">*</sup></label>
							                            <select id="sports_type" name="sports_type" class="form-control" <c:if test="${mode == 'view'}">disabled</c:if>>
							                                <option value="">Select Sport</option>
							                                <c:forEach items="${sportsTypes}" var="sportsType">
							                            			<option value="${sportsType.name_en}" <c:if test="${trainingCenterDTO.sportsType == sportsType.name_en}">selected</c:if>>${sportsType.name_en}</option>
							                            		</c:forEach>	
							                                <!-- <option>Athletics</option>
							                                <option>Football</option>
							                                <option>Cricket</option> -->
							                            </select>
							                            <p class="error-container" id="sports_type-error"></p>
	                                      			</div>
	                                      		</div>
	                                      		
	                                      		
	                                      		<div class="col-md-6">
	                                      			<div class="form-group">
	                                      						<%-- <c:if test="${mode eq 'add'}">
													            	<label>
															            <liferay-ui:message key="dpr-document" />
															            <sup class="text-danger">*</sup>
															            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file" />"></em>
															        </label>
													            <div class="custom-file">
													                <input type="file" class="custom-file-input" id="dpr_doc" name="dpr_doc"
													                    onchange="handleFileUpload(event, 'dpr_doc', 'dprDocumentPreviewContainer', 'dprDocumentPreviewLink', 'dprDocumentddeleteButton')">
													                <label class="custom-file-label" for="dpr_doc">
													                    <liferay-ui:message key="choose-file" />
													                </label>
													            </div>
													            Empty container for new file upload preview
													            <div class="ownerProofid d-none mt-3" id="dprDocumentPreviewContainer">
													                <a class="dprDocumentCls" id="dprDocumentPreviewLink" href="" target="_blank"
													                    style="flex-grow: 1; text-decoration: none; white-space: nowrap;overflow: hidden; max-width: 200px; color: #26268E;"></a>
													                <button type="button" id="dprDocumentddeleteButton" class="dprDocumentUploadBtn close"
													                    aria-label="Close" onclick="deleteFile('dprDocumentPreviewContainer', 'dpr_doc')">
													                    <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
													                </button>
													            </div>
													        </c:if> --%>
													        
													        
													        
							                            <!-- <label for="dpr_doc" class="form-label">DPR Document</label>
							                            <input type="file" id="dpr_doc" name="dpr_doc" class="form-attachment"> -->
							                            
							                            <c:if test="${mode eq 'view'}">
												            <c:if test="${not empty trainingCenterDTO.dprDocurl}">
												                 <label>
															            <liferay-ui:message key="dpr-document" />
															            <sup class="text-danger">*</sup>
															            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file" />"></em>
															        </label>
												                <%-- <div>
												                    <a href="${trainingCenterDTO.dprDocurl}" target="_blank" style="flex-grow: 1; text-decoration: none; white-space: nowrap;overflow: hidden; max-width: 200px; color: #26268E;">
												                        ${trainingCenterDTO.dprDocName}
												                    </a>
												                </div> --%>
												                 <div class="ownerProofid d-flex mt-3" id="dprDocumentPreviewContainer">
														            <a class="dprDocumentCls" id="dprDocumentPreviewLink"
														                href="${trainingCenterDTO.dprDocurl}" target="_blank"
														                style="flex-grow: 1; text-decoration: none; white-space: nowrap;overflow: hidden; max-width: 200px; color: #26268E;">
														                ${trainingCenterDTO.dprDocName}
														            </a>
														            <button disabled="disabled" type="button" id="dprDocumentddeleteButton" class="dprDocumentUploadBtn close"
														                aria-label="Close" onclick="deleteFile('dprDocumentPreviewContainer', 'dpr_doc')">
														                <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
														            </button>
														        </div>
												            </c:if>
												        </c:if>
							                            
							                            <c:if test="${mode eq 'edit'}">
							                            			 <label>
															            <liferay-ui:message key="dpr-document" />
															            <sup class="text-danger">*</sup>
															            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file" />"></em>
															        </label>
							                             <div class="custom-file">
														        <input type="file" class="custom-file-input" id="dpr_doc" name="dpr_doc"
														            onchange="handleFileUpload(event, 'dpr_doc', 'dprDocumentPreviewContainer', 'dprDocumentPreviewLink', 'dprDocumentNewDeleteButton')">
														        <label class="custom-file-label" for="dpr_doc">
														            <liferay-ui:message key="choose-file" />
														        </label>
														    </div>
							                             
							                              <%-- Show existing file with delete option if available --%>
							                            <div class="ownerProofid d-flex mt-3" id="dprDocumentPreviewContainer">
												            <a class="dprDocumentCls" id="dprDocumentPreviewLink"
												                href="${trainingCenterDTO.dprDocurl}" target="_blank"
												                style="flex-grow: 1; text-decoration: none; white-space: nowrap;overflow: hidden; max-width: 200px; color: #26268E;">
												                ${trainingCenterDTO.dprDocName}
												            </a>
												            <button type="button" id="dprDocumentddeleteButton" class="dprDocumentUploadBtn close"
												                aria-label="Close" onclick="deleteFile('dprDocumentPreviewContainer', 'dpr_doc')">
												                <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
												            </button>
												        </div>
												        <%-- Preview of new file to be uploaded --%>
													    <div class="ownerProofid d-none mt-3" id="dprDocumentPreviewContainer">
													        <a class="dprDocumentUploadProofCls" id="dprDocumentUploadNewPreviewLink" href="" target="_blank"
													            style="flex-grow: 1; text-decoration: none; white-space: nowrap;overflow: hidden; max-width: 200px; color: #26268E;"></a>
													        <button type="button" id="dprDocumentNewDeleteButton" class="dprDocumentUploadBtn close"
													            aria-label="Close" onclick="deleteFile('dprDocumentPreviewContainer', 'dpr_doc')">
													            <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
													        </button>
													    </div>
												        
							                            </c:if>
							                             <c:if test="${mode eq 'add'}">
							                            		 <label>
															            <liferay-ui:message key="dpr-document" />
															            <sup class="text-danger">*</sup>
															            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file" />"></em>
															        </label>
							                            		 <div class="custom-file">
													                    <input type="file" class="custom-file-input" id="dpr_doc" name="dpr_doc"
													                        onchange="handleFileUpload(event, 'dpr_doc', 'dprDocumentNewPreviewContainer', 'dprDocumentNewPreviewLink', 'dprDocumentNewDeleteButton')"> 
													                    <label class="custom-file-label" for="customFile">
													                        <liferay-ui:message key="choose-file" />
													                    </label>
													                </div>
							                            		<%-- Empty container for new file upload preview --%>
													            <div class="ownerProofid d-none mt-3" id="dprDocumentNewPreviewContainer">
													                <a class="dprDocumentCls" id="dprDocumentNewPreviewLink" href="" target="_blank"
													                    style="flex-grow: 1; text-decoration: none; white-space: nowrap;overflow: hidden; max-width: 200px; color: #26268E;"></a>
													                <button type="button" id="dprDocumentNewDeleteButton" class="dprDocumentUploadBtn close"
													                    aria-label="Close" onclick="deleteFile('dprDocumentNewPreviewContainer', 'dpr_doc')">
													                    <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
													                </button>
													            </div>
							                            </c:if>
	                                      			</div>
	                                      			<p class="error-container" id="dpr_doc-error"></p>
	                                      		 </div>
	                                      		
	                                      		<div class="col-md-6">
	                                      			<div class="form-group">
	                                      						<c:if test="${mode eq 'view'}">
															    	<label><liferay-ui:message key="geo-tag-photos" /><sup class="text-danger">*</sup>
															    	<em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />">
											                		</em></label>
											                		
											                		
															    </c:if>
											        	<c:if test="${mode eq 'add' or mode eq 'edit'}">
											            <label><liferay-ui:message key="geo-tag-photos" /><sup class="text-danger">*</sup>
											                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />">
											                </em>
										        		</label>
											            <div class="custom-file">
											                <label><liferay-ui:message key="geo-tag-photos" /><sup class="text-danger">*</sup></label>
											                <input type="file" class="custom-file-input geoTagPhotos" id="geoTagPhotos" name="geoTagPhotos" multiple
											                    onchange="handleMultipleFileUpload(this, 'geoTagPhotos', 'geoTagPhotosPreviewContainer', 'geoTagPhotosPreviewList', 'geoTagPhotosError', 'geoTagPhoto')">
											                <label class="custom-file-label" for="geoTagPhotos">
											                    <liferay-ui:message key="choose-file" />
											                </label>
											            </div>
											            <!-- Error message -->
											            <span class="error-container" id="geoTagPhotosError"></span>
											
											            <!-- Hidden input to store file details -->
											            <input type="hidden" id="geoTagPhoto" name="geoTagPhoto" 
											                value='<c:if test="${mode eq 'edit' and not empty trainingCenterDTO.geoTagPhotosNames}"><c:forEach var="photoName" items="${trainingCenterDTO.geoTagPhotosNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
											
											            <!-- Preview and Delete Section -->
											            <div class="mt-3" id="geoTagPhotosPreviewContainer" 
											                style='<c:if test="${mode ne 'edit' or empty trainingCenterDTO.geoTagPhotosURLs}">display: none;</c:if>'>
											                <ul id="geoTagPhotosPreviewList" name="geoTagPhotosPreviewList" class="list-group">
											                    <c:if test="${mode eq 'edit' and not empty trainingCenterDTO.geoTagPhotosURLs}">
											                        <c:forEach var="photoURL" items="${trainingCenterDTO.geoTagPhotosURLs}" varStatus="status">
											                            <li class="list-group-item d-flex justify-content-between align-items-center">
											                                <a href="${photoURL}" target="_blank" class="text-truncate">
											                                    ${trainingCenterDTO.geoTagPhotosNames[status.index]}
											                                </a>
											                                <!-- FIXED: Correct parameter order in removeFile call -->
											                                <button type="button" class="btn btn-danger btn-sm" onclick="removeFile(${status.index}, 'geoTagPhotosPreviewContainer', 'geoTagPhotosPreviewList', 'geoTagPhotosError', 'geoTagPhoto', 'geoTagPhotos')">
																			    <i class="bi bi-x-circle-fill"></i>
																			</button>
											                            </li>
											                        </c:forEach>
											                    </c:if>
											                </ul>
											            </div>
											           <!--  <input type="file" class="custom-file-input geoTagPhotos" id="geoAddTagPhotos" name="geoAddTagPhotos" /> -->
											        </c:if>
												        <c:if test="${mode eq 'view' and not empty trainingCenterDTO.geoTagPhotosURLs}">
												            <div>
												                <c:forEach var="photoURL" items="${trainingCenterDTO.geoTagPhotosURLs}" varStatus="status">
												                    <a href="${photoURL}" target="_blank" class="text-truncate">
												                        ${trainingCenterDTO.geoTagPhotosNames[status.index]}
												                    </a><br>
												                </c:forEach>
												            </div>
												            
												        </c:if>
                                    				</div>
                                      			</div>
	                                      </div>
	                                      <div class="row">
	                                        <div class="col-md-12">
												<%-- <c:if test="${mode ne 'view'}"> --%>
												    <div class="form-group">
												        <label><liferay-ui:message key="land-location-coordinates" /></label>
												        <button type="button" class="btn btn-primary <c:if test="${mode == 'view'}">d-none</c:if>" onclick="getLocation()" ><liferay-ui:message key="gis-map-location" /></button>
												    </div>
											    <%-- </c:if> --%>
											    <div id="map" class="d-none" style="height: 300px; border: 1px solid #ccc; z-index: 1;"></div> <!-- Map Container -->
											</div>
											<div class="col-md-6 mt-4">
											    <div class="form-group">
											        <label><liferay-ui:message key="latitude" /><sup class="text-danger">*</sup></label>
											        <input type="text" class="form-control" name="latitude" id="latitude" placeholder="<liferay-ui:message key='latitude' />" value="${trainingCentre.latitude}" readonly />
											    	<p class="error-container" id="latitude-error"></p>
											    </div>
											</div>
											<div class="col-md-6 mt-4">
											    <div class="form-group">
											        <label><liferay-ui:message key="longitude" /><sup class="text-danger">*</sup></label>
											        <input type="text" class="form-control" name="longitude" id="longitude" placeholder="<liferay-ui:message key='longitude' />" value="${trainingCentre.longitude}" readonly />
											    	 <p class="error-container" id="longitude-error"></p>
											    </div>
											</div>
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
									  <button type="button" class="btn btn-primary trainingccenterdraftbtn" onclick="saveTrainingCenterDetails(event,'Draft','existingtrainingcentredetails')">
	                                    <liferay-ui:message key="save-as-draft" />
	                                </button>
	                                 <button type="button" class="btn btn-primary trainingcenternextbtn" onclick="setActiveTab('coach-detail','existingtrainingcentredetails','next')"/>
		                                    <liferay-ui:message key="next"/>
		                                </button>
                                </c:if>
                                <c:if test="${mode eq 'view'}">
		                                 <button type="button" class="btn btn-primary" onclick="setActiveTab('coach-detail','existingtrainingcentredetails','next')"/>
		                                    <liferay-ui:message key="next"/>
		                                </button>
                                </c:if>
                            </div>
                        </div>
                       
<script>
	$(document).ready(function(){
		
		
		$('#district').change(function(e){
			console.log("district change called  :::");
			var districtval=$(this).val();
			if(!districtval){
				document.getElementById("district-error").textContent = "<liferay-ui:message key='please-select-district' />";
				$(this).focus();
			}else{
				document.getElementById("district-error").textContent = "";
			}
			
		});
		
		$('#sports_type').change(function(e){
			console.log("sports_type change called  :::");
			var sportsTypeVal=$(this).val();
			if(!sportsTypeVal){
				document.getElementById("sports_type-error").textContent = "<liferay-ui:message key='please-select-sports-type' />";
				$(this).focus();
			}else{
				document.getElementById("sports_type-error").textContent = "";
			}
		});
		
		//change event of division
		$('#division').change(function(e){
			console.log("Division change function ::::");
			var id=$(this).val();
			console.log("division id :::"+id);
			
			if(id!=null && id>0){
				document.getElementById("division-error").textContent = "";
				$.ajax({
	     			type: "POST",
	     			datatype:"json",
	     	        url: '${getDistrictByDivisionURL}', 
	     	        data: {
	     	        	divisionId:id 
	     	        },
	     			async : false,
	     	        success: function(data) {
	     	            	console.log("inside  division succeess :: "+data);
	     	            	var response=JSON.parse(data);
	     	            	if(response!=null && response.length>0){
	     	            		var distrivchtml="";
	     	            		$.each(response,function(k,v){
	     	            			//console.log("k::",k);
	     	            			//console.log("v::",v);
	     	            			distrivchtml=distrivchtml+'<option value="'+v.id+'">'+v.name+'</option>'
	     	            		});
	     	            		$('#district').html('').append('<option value=""><liferay-ui:message key="select-district" /></option>').append(distrivchtml);
	     	            	}
	     	        },error:function(resp){
	     	        	console.log("inside  division error :: ");
	     	        }
	     		});
			}else{
				var distrivchtml='<option value=""><liferay-ui:message key="select-district" /></option>'
				$('#district').html('').append(distrivchtml);;
			}
		});
		
		//Change event of training center name
		 $('input[type="radio"][name="centre_type"]').on('change', function() {
	            var selectedValue = $(this).val();
	            console.log("Selected radio button value: " + selectedValue);
	            // Perform actions based on the selected value
	            if(selectedValue){
	            	document.getElementById("training-center-type-error").textContent = "";
	            }
	        });
		 
		//Change event of dpr document 
		 $('#dpr_doc').change(function(){
			 var dprDocs=$("#dpr_doc")[0].files[0];
			 if(dprDocs){
				 	console.log("dprDocs:::" +dprDocs);
					var dprdoc=$('#dpr_doc')[0];
					var dprdocSize = $("#dpr_doc")[0].files[0].size;
					var dprdoclength=$('#dpr_doc')[0].files.length;
					 var maxSize = 2*1024*1024; // 2 MB
					 console.log("dprdocSize 2:::" +dprdocSize);
					 console.log("maxSize> :::" +dprdocSize>maxSize);
					var allowedExtensions = /(\.pdf)$/i;
				
				 if(dprdoclength==0){
			        	console.log("inside if dpr");
			        	document.getElementById("dpr_doc-error").textContent = "<liferay-ui:message key='please-select-dpr_doc' />";
			    		$(this).focus();
			        }else if (!allowedExtensions.exec(dprdoc.files[0].name)) {
			        	console.log("inside else  if dpr");
			        	document.getElementById("dpr_doc-error").textContent = "<liferay-ui:message key='please-select-pdf-file-only' />";
			        }else if (dprdocSize>maxSize) {
			        	console.log("inside else  if dpr");
			        	document.getElementById("dpr_doc-error").textContent = "<liferay-ui:message key='select-document-size-less-than-2-mb' />";
			        }else{
			        	document.getElementById("dpr_doc-error").textContent = "";
			        }
			}else{
				document.getElementById("dpr_doc-error").textContent = "<liferay-ui:message key='please-select-dpr_doc' />";
	    		$(this).focus();
			}
		 });
		
		//geo tag photo change event
		 $('#geoTagPhotos').change(function(){
			//geo tag validation 
				var ulElement = document.getElementById("geoTagPhotosPreviewList");
				// Get all li elements within that ul
				var  liElements = ulElement.getElementsByTagName("li");
				// Get the number of li elements
				var numberOfLi = liElements.length;
				console.log("Number of list items:" +numberOfLi);
				if(numberOfLi==0){
					document.getElementById("geoTagPhotosError").textContent = "<liferay-ui:message key='please-select-geo-tag-photos' />";
					$(this).focus();
				}else{
					document.getElementById("geoTagPhotosError").textContent = "";
				}
		 });
		
		
	});
	

</script>