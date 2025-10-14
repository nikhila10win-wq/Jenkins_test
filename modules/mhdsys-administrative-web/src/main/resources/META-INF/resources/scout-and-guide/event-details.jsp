<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<portlet:resourceURL id="<%=MhdsysAdministrativeWebPortletKeys.EVENT_DETAILS_MVC_RESOURCE_COMMAND %>" var="saveEventCertificateURL" /> 

<div class="common-forms-div">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="card shadow border-0">
                    <div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="event-details" />
						</h5>
						<div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn" href="/group/guest/event-list"
								style="background-color: #26268E; border-color: #fff;"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message
									key="back" />
							</a>
						</div>
					</div>

					<form id="photoUploadForm" enctype="multipart/form-data">
						<div class="card-body">
							<!-- Event Details -->
							<!-- Event Details -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="event-details" />
								</div>
								<div class="card-body">
									<div class="row mb-3">
										<div class="col-md-6 form-group">
											<label for="year" class="form-label"> <liferay-ui:message
													key="year" /> <sup class="text-danger">*</sup>
											</label> <select class="form-control" name="year" id="year" 
													<c:if test="${mode eq 'view'}">disabled</c:if>>
												<option value=""><liferay-ui:message key="select" /></option>
												<!-- Your existing year options remain the same -->
												<option value="2023-24"
													<c:if test="${'2023-24' == eventCertificate.year}">selected="selected"</c:if>>2023-24</option>
											</select>
											</div>
										<div class="col-md-6 form-group">
											<label for="eventDate" class="form-label"> <liferay-ui:message
													key="event-date" /> <sup class="text-danger">*</sup>
											</label> <input type="date" name="eventDate" class="form-control"
												value="${eventCertificate.eventDateStr }" <c:if test="${mode eq 'view'}">disabled</c:if>/>
										</div>
									</div>

									<div class="row mb-3">
										<div class="col-md-6 form-group">
											<label for="eventName" class="form-label"> <liferay-ui:message
													key="event-name" /> <sup class="text-danger">*</sup>
											</label> <input type="text" name="eventName" class="form-control"
												value="${eventCertificate.eventName }" <c:if test="${mode eq 'view'}">disabled</c:if>/>
										</div>
										<div class="col-md-6 form-group">
											<label for="address" class="form-label"> <liferay-ui:message
													key="address" /> <sup class="text-danger">*</sup>
											</label>
											<textarea name="address" class="form-control" <c:if test="${mode eq 'view'}">disabled</c:if>>${eventCertificate.address }</textarea>
										</div>
									</div>
								</div>
							</div>

							<!-- Photo Upload -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="photos" />
								</div>
								<div class="card-body">
									<div class="form-group">
										<label> <liferay-ui:message key="event-photos" /> <sup
											class="text-danger">*</sup> <em
											class="bi bi-info-circle-fill"
											title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
										</label>
										<c:if test="${ mode ne 'view'}">
											<!-- File Input -->
											<div class="custom-file">
												<input type="file" class="custom-file-input certificate"
													id="certificate" name="locationPhotos" multiple
													accept="application/pdf"
													onchange="handleMultipleFileUpload(this, 'certificate', 'certificatePreviewContainer', 'certificatePreviewList', 'certificateError', 'hiddenCertificate')">
												<label class="custom-file-label" for="certificate">
													<liferay-ui:message key="choose-file" />
												</label>
											</div>

											<!-- Error Message -->
											<span id="certificateError" class="text-danger small"></span>

											<!-- Hidden Field to store selected filenames -->
											<input type="hidden" id="hiddenCertificate"
												name="hiddenCertificate" value="">

											<!-- File Preview Section -->
											<div class="mt-3" id="certificatePreviewContainer"
												style="display: none;">
												<ul id="certificatePreviewList" class="list-group">
													<!-- JS will append preview items here -->
												</ul>
											</div>
										</c:if>
										<c:if test="${ not empty eventCertificate.certificateURLs}">
											<div>
												<c:forEach var="photoURL"
													items="${eventCertificate.certificateURLs}"
													varStatus="status">
													<a href="${photoURL}" target="_blank" class="text-truncate">
														${eventCertificate.certificateFileNames[status.index]} </a>
													<br>
												</c:forEach>
											</div>
										</c:if>

									</div>

								</div>
							</div>

							<!-- Buttons -->
							<c:if test="${mode ne 'view' }">
								<div class="card-footer bg-transparent text-right p-4">

									<button type="button" class="btn btn-primary"
										onclick="location.href='/group/guest/scout-guide-ncc';">
										<liferay-ui:message key="cancel" />
									</button>

									<button class="btn btn-primary" type="button"
										onclick="location.reload();">
										<liferay-ui:message key="reset" />
									</button>

									<button type="button" class="btn btn-primary"
										onclick="saveEventDetailsForm(event)">
										<liferay-ui:message key="submit" />
									</button>

								</div>
							</c:if>
						</div>
					</form>
				</div>
            </div>
        </div>
    </div>
</div>
<!-- Modal popup for Event Certificate Save -->
<div class="modal fade" id="eventSaveModal" tabindex="-1" role="dialog" aria-labelledby="eventModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content modal-bg">
            <div class="modal-header justify-content-center align-items-center">
                <h5 class="modal-title" id="eventModalTitle">
                    <liferay-ui:message key="event-certificate-submission" />
                </h5>
                <button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <div>
                            <%-- Optional image/icon can be placed here --%>
                            <p id="event-success-message"></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer d-flex justify-content-end">
                <a href="/group/guest/event-list" type="button" class="btn btn-secondary maha-save-btn"
                   data-bs-dismiss="modal" onclick="closeModal('eventSaveModal')">
                    <liferay-ui:message key="close" />
                </a>
            </div>
        </div>
    </div>
</div>
<!-- Modal popup for Event Certificate Save -->

<script>
$(document).ready(function () {
    $('#photoUploadForm').validate({
        onkeyup: function (element) {
            $(element).valid();
        },
        onchange: function (element) {
            $(element).valid();
        },

        rules: {
            year: {
                required: true
            },
            eventDate: {
                required: true
            },
            eventName: {
                required: true,
                minlength: 3,
                maxlength: 150,
                startEndSpace: true,
                validateName: true
            },
            address: {
                required: true,
                minlength: 5,
                maxlength: 250,
                startEndSpace: true
            },
            locationPhotos: {
                required: function () {
                    return uploadedFilesCertificate.length === 0 && $('#certificateError').text().trim() === "";
                }
            }
        },

        messages: {
            year: {
                required: "<liferay-ui:message key='please-select-year' />"
            },
            eventDate: {
                required: "<liferay-ui:message key='please-enter-event-date' />"
            },
            eventName: {
                required: "<liferay-ui:message key='please-enter-event-name' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-150-characters-allowed' />"
            },
            address: {
                required: "<liferay-ui:message key='please-enter-address' />",
                minlength: "<liferay-ui:message key='minimum-5-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-250-characters-allowed' />"
            },
            locationPhotos: {
                required: "<liferay-ui:message key='please-upload-certificate' />"
            }
        },

        errorPlacement: function (error, element) {
            if (element.attr("type") == "file") {
                error.insertAfter(element.closest(".custom-file"));
            } else {
                error.insertAfter(element);
            }
        }
    });

    // Custom methods
    $.validator.addMethod("startEndSpace", function (value, element) {
        return this.optional(element) || /^[^\s].*[^\s]$/.test(value);
    }, "<liferay-ui:message key='leading-or-trailing-spaces-are-not-allowed' />");

    $.validator.addMethod("validateName", function (value, element) {
        return this.optional(element) || /^[A-Za-z0-9.,\- ]+$/.test(value);
    }, "<liferay-ui:message key='only-letters-numbers-and-basic-punctuation-allowed' />");
});

var uploadedFilesCertificate = [];

function handleMultipleFileUpload(eventOrInput, inputId, previewContainerId, previewListId, errorSpanId, hiddenInputId) {
    const fileInput = eventOrInput.target ? eventOrInput.target : eventOrInput;
    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    const uploadedFiles = uploadedFilesCertificate;
    const newFiles = Array.from(fileInput.files);

    for (let file of newFiles) {
        const ext = file.name.split('.').pop().toLowerCase();

        if (uploadedFiles.some(f => f.name === file.name && !f.markedForDelete)) {
            errorSpan.innerHTML = '<span style="color: red;">This file is already uploaded.</span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }

        if (ext !== "pdf") {
            errorSpan.innerHTML = '<span style="color: red;">Only PDF files are allowed.</span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }

        if (file.size > 2 * 1024 * 1024) {
            errorSpan.innerHTML = '<span style="color: red;">File size must be under 2MB.</span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }
    }

    errorSpan.innerHTML = "";
    errorSpan.style.display = "none";

    newFiles.forEach(file => {
        uploadedFiles.push({
            file: file,
            name: file.name,
            url: URL.createObjectURL(file),
            markedForDelete: false
        });
    });

    renderFilePreviews(previewContainer, previewList, errorSpan, hiddenInput, uploadedFiles, true);
    fileInput.value = "";
}

function removeFile(index, previewContainerId, previewListId, errorSpanId, hiddenInputId, inputId) {
    const uploadedFiles = uploadedFilesCertificate;

    if (index >= 0 && index < uploadedFiles.length) {
        uploadedFiles.splice(index, 1);
    }

    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    renderFilePreviews(previewContainer, previewList, errorSpan, hiddenInput, uploadedFiles, true);

    if (uploadedFiles.length === 0) {
        errorSpan.innerHTML = '<span style="color: red;">Please upload at least one file.</span>';
        errorSpan.style.display = "block";
    }
}

function renderFilePreviews(previewContainer, previewList, errorSpan, hiddenInput, uploadedFiles, showDeleteBtn) {
    previewList.innerHTML = "";

    uploadedFiles.forEach((fileObj, index) => {
        if (fileObj.markedForDelete) return;

        const listItem = document.createElement("li");
        listItem.className = "list-group-item d-flex justify-content-between align-items-center";

        const link = document.createElement("a");
        link.href = fileObj.url;
        link.target = "_blank";
        link.textContent = fileObj.name;
        link.className = "text-truncate";
        link.style.cssText = "flex-grow: 1; text-decoration: none; max-width: 200px;";

        listItem.appendChild(link);

        if (showDeleteBtn) {
            const deleteBtn = document.createElement("button");
            deleteBtn.type = "button";
            deleteBtn.className = "btn btn-danger btn-sm";
            deleteBtn.innerHTML = '<i class="bi bi-x-circle-fill"></i>';
            deleteBtn.onclick = () => removeFile(index, previewContainer.id, previewList.id, errorSpan.id, hiddenInput.id, '');
            listItem.appendChild(deleteBtn);
        }

        previewList.appendChild(listItem);
    });

    previewContainer.style.display = uploadedFiles.length > 0 ? "block" : "none";
    hiddenInput.value = uploadedFiles.map(f => f.name).join(",");
}



function saveEventDetailsForm(event) { 
    event.preventDefault();

    if ($('#photoUploadForm').valid()) {
        var form = $('#photoUploadForm')[0];
        var formData = new FormData(form);

        // Append manually uploaded files if necessary
       uploadedFilesCertificate.forEach((fileObj) => {
		    if (fileObj && !fileObj.markedForDelete) {
		        formData.append("certificate", fileObj.file);
		        formData.append("certificateNames", fileObj.name);
		    }
		});

        $.ajax({
            url: "${saveEventCertificateURL}", // Replace with Liferay resource/action URL
            method: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                var data = typeof response === "string" ? JSON.parse(response) : response;

                if (data.createEvent) {
                    $('#event-success-message').html('<div >Event certificate saved successfully.</div>');
                } else {
                    $('#event-success-message').html('<div >Failed to save event certificate. Please try again.</div>');
                }

                $('#eventSaveModal').modal('show');
            },
            error: function (xhr) {
                console.log("Error saving event certificate: ", xhr);
                $('#event-success-message').html('<div >Something went wrong. Please try again later.</div>');
                $('#eventSaveModal').modal('show');
            }
        });
    }
}
function closeModal(id) {debugger
 	$("#"+id).modal('hide');
 	$(".modal-backdrop").remove();
 	$("body").removeClass("modal-open");
}
</script>