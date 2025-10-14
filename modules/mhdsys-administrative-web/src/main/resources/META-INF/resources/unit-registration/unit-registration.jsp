<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<portlet:resourceURL id="<%=MhdsysAdministrativeWebPortletKeys.UNIT_REGISTRATION_MVC_RESOURCE_COMMAND%>"
var="saveUnitRegistrationURL" />
<style>
    /* Force checkbox label to remain black regardless of validation */
    .form-check-label.text-dark {
        color: #212529 !important; /* Bootstrap's default text-dark */
        font-weight: 700;
    }
</style>
<div class="common-forms-div">
    <div class="container">
		<div class="row">
			<div class="col-md-12">
                <div class="border-0 card shadow">
					<div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="unit-registration" />
						</h5>
						<div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn"
								href="/group/guest/scout-guide-ncc"
								style="background-color: #26268E; border-color: #fff;"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message
									key="back" />
							</a>
						</div>
					</div>

					<form id="unitRegistrationForm" method="POST">
						<div class="card-body">

							<!-- Unit Details -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="unit-details" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="unit-type" /><sup
													class="text-danger">*</sup></label> <select name="unitType"
													class="form-control" id="unitType">
													<option value=""><liferay-ui:message key="select" /></option>
													<option value="Bunny">Bunny</option>
													<option value="Cub">Cub</option>
													<option value="Scout">Scout</option>
													<option value="Rover">Rover</option>
													<option value="Bulbul">Bulbul</option>
													<option value="Guide">Guide</option>
													<option value="Ranger">Ranger</option>
													<option value="Air">Air</option>
													<option value="Water">Water</option>
													<option value="Special">Special</option>
													<option value="Para">Para</option>
												</select>
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="unit-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="unitName" required />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="unit-charter-number" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="unitCharterNumber" required />
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="year" /><sup
													class="text-danger">*</sup></label> <select class="form-control"
													name="year" required>
													<option value=""><liferay-ui:message key="select" /></option>
													<option value="2009-10"
														<c:if test="${'2009-10' == unitReg.year}">selected="selected"</c:if>>2009-10</option>
													<option value="2010-11"
														<c:if test="${'2010-11' == unitReg.year}">selected="selected"</c:if>>2010-11</option>
													<option value="2011-12"
														<c:if test="${'2011-12' == unitReg.year}">selected="selected"</c:if>>2011-12</option>
													<option value="2012-13"
														<c:if test="${'2012-13' == unitReg.year}">selected="selected"</c:if>>2012-13</option>
													<option value="2013-14"
														<c:if test="${'2013-14' == unitReg.year}">selected="selected"</c:if>>2013-14</option>
													<option value="2014-15"
														<c:if test="${'2014-15' == unitReg.year}">selected="selected"</c:if>>2014-15</option>
													<option value="2015-16"
														<c:if test="${'2015-16' == unitReg.year}">selected="selected"</c:if>>2015-16</option>
													<option value="2016-17"
														<c:if test="${'2016-17' == unitReg.year}">selected="selected"</c:if>>2016-17</option>
													<option value="2017-18"
														<c:if test="${'2017-18' == unitReg.year}">selected="selected"</c:if>>2017-18</option>
													<option value="2018-19"
														<c:if test="${'2018-19' == unitReg.year}">selected="selected"</c:if>>2018-19</option>
													<option value="2019-20"
														<c:if test="${'2019-20' == unitReg.year}">selected="selected"</c:if>>2019-20</option>
													<option value="2020-21"
														<c:if test="${'2020-21' == unitReg.year}">selected="selected"</c:if>>2020-21</option>
													<option value="2021-22"
														<c:if test="${'2021-22' == unitReg.year}">selected="selected"</c:if>>2021-22</option>
													<option value="2022-23"
														<c:if test="${'2022-23' == unitReg.year}">selected="selected"</c:if>>2022-23</option>
													<option value="2023-24"
														<c:if test="${'2023-24' == unitReg.year}">selected="selected"</c:if>>2023-24</option>
												</select>

											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- School Details -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="school-details" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="school-udis" /><sup
													class="text-danger">*</sup></label>
												<%--   <select class="form-control" name="schoolUdis" required>
                                                    <option value="">Select</option>
                                                    <c:forEach var="school" items="${schoolList}">
                                                        <option value="${school.udis}">${school.udis}</option>
                                                    </c:forEach>
                                                </select> --%>
												<input type="text" class="form-control" name="udis"
													id="udis" />
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="school-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="schoolName" id="schoolName" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="address" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="address" id="address" />
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="pincode" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="pincode" id="pincode" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="school-email" /><sup
													class="text-danger">*</sup></label> <input type="email"
													class="form-control" name="schoolEmail" id="schoolEmail" />
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message
														key="school-contact-number" /><sup class="text-danger">*</sup></label>
												<input type="text" class="form-control" name="schoolContact"
													id="schoolContact" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="principal-mob-no" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="principalMobile"
													id="principalMobile" />
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- Declaration -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="declaration" />
								</div>
								<div class="card-body">
									<div class="form-check">
										<input type="checkbox" class="form-check-input"
											id="selfDeclaration" name="selfDeclaration" required /> <label
											class="form-check-label fw-bold text-dark" for="selfDeclaration"><liferay-ui:message
											key="self-declaration" /><sup class="text-danger">*</sup></label>
									</div>
								</div>
							</div>
						</div>

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
								onclick="saveUnitRegistration(event)">
								<liferay-ui:message key="submit" />
							</button>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Unit Save Result Modal -->
<div class="modal fade" id="saveUnitModal" tabindex="-1"
	role="dialog" aria-labelledby="saveUnitModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="saveUnitModalTitle">
					<liferay-ui:message key="unit-registration" />
				</h5>
				<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 text-center">
						<div>
							<img src="<%=request.getContextPath()%>/images/check.png" alt=""
								width="50px" class="my-3">
							<p id="unit-save-message" ></p>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer d-flex justify-content-end">
				<a href="/group/guest/scout-guide-ncc" type="button"
					class="btn btn-secondary maha-save-btn"
					data-bs-dismiss="modal" onclick="closeModal('saveUnitModal')">
					<liferay-ui:message key="close" />
				</a>
			</div>
		</div>
	</div>
</div>

<script>
function findProperty(obj, key) {
    if (typeof obj === "object") {
        if (key in obj) {
            return true;
        } else {
            return false;
        }
    }
    return false;
}
$(document).ready(function () {
	addValidationMethods();
	$("#unitRegistrationForm").validate({
		rules : {
			unitType : {
				required : true
			},
			unitName : {
				required : true
			},
			unitCharterNumber : {
				required : true,
				number : true
			},
			year : {
				required : true
			},
			udis : {
				required : true
			},
			schoolName : {
				required : true
			},
			address : {
				required : true
			},
			pincode : {
				required : true,
				digits : true
			},
			schoolEmail : {
				required : true,
				email : true
			},
			schoolContact : {
				required : true,
				mobileNumberValidation : true
			},
			principalMobile : {
				required : true,
				mobileNumberValidation : true
			},
			selfDeclaration : {
				required : true
			}
		},
		messages : {
			unitType : {
				required : "<liferay-ui:message key='please-select-unit-type' />"
			},
			unitName : {
				required : "<liferay-ui:message key='please-enter-unit-name' />"
			},
			unitCharterNumber : {
				required : "<liferay-ui:message key='please-enter-unit-charter-number' />",
				number : "<liferay-ui:message key='please-enter-valid-charter-number' />"
			},
			year : {
				required : "<liferay-ui:message key='please-select-year' />"
			},
			udis : {
				required : "<liferay-ui:message key='please-enter-school-udis' />"
			},
			schoolName : {
				required : "<liferay-ui:message key='please-enter-school-name' />"
			},
			address : {
				required : "<liferay-ui:message key='please-enter-address' />"
			},
			pincode : {
				required : "<liferay-ui:message key='please-enter-pincode' />",
				digits : "<liferay-ui:message key='please-enter-only-digits' />"
			},
			schoolEmail : {
				required : "<liferay-ui:message key='please-enter-school-email' />",
				email : "<liferay-ui:message key='please-enter-valid-email' />"
			},
			schoolContact : {
				required : "<liferay-ui:message key='please-enter-contact-number' />",
				mobileNumberValidation : "<liferay-ui:message key='enter-valid-mobile-number' />"
			},
			principalMobile : {
				required : "<liferay-ui:message key='please-enter-principal-mobile' />",
				mobileNumberValidation : "<liferay-ui:message key='enter-valid-mobile-number' />"
			},
			selfDeclaration : {
				required : "<liferay-ui:message key='please-accept-declaration' />"
			}
		},
		errorElement : 'div',
		errorPlacement: function(error, element) {
		    error.addClass('invalid-feedback');

		    if (element.is(':checkbox')) {
		        element.closest('.form-check').append(error);
		    } else {
		        element.closest('.form-group').append(error);
		    }
		},
		highlight : function(element) {
			$(element).addClass(
					'is-invalid');
		},
		unhighlight : function(element) {
			$(element).removeClass(
					'is-invalid');
		}
	});
	function addValidationMethods() {
		if (!findProperty($.validator.methods,'aadhaarValidation')) {
		$.validator.addMethod("aadhaarValidation",
			function(value, element) {
				const regex = /^[0-9]{4}[ -]?[0-9]{4}[ -]?[0-9]{4}$/;
				if (!regex.test(value)) {
					return false;
				}
				return this.optional(element)|| regex.test(value);
			});
		}
		if (!findProperty($.validator.methods,'mobileNumberValidation')) {
			$.validator.addMethod("mobileNumberValidation",
				function(value, element) {
					var basicRegex = /^[6-9]\d{9}(\d{3})?$/; // 10 or 13 digits, starting with 6-9
					var sameDigitsRegex = /^(\d)\1{9,12}$/; // All digits are the same for 10 or 13 digits
					if (value === "") {
						return true; // Skip validation if the field is empty
					}
					if (!basicRegex.test(value)) {
						$.validator.messages.mobileNumberValidation = '<liferay-ui:message key="valid-mobile-msg"/>';
						return false;
					} else if (sameDigitsRegex
							.test(value)) {
						$.validator.messages.mobileNumberValidation = '<liferay-ui:message key="all-same-digits-msg"/>';
						return false;
					}
					return true;
				});
			}
		}
	});
	function saveUnitRegistration(event) {
		event.preventDefault(); // Prevent default form submission

		var form = $("#unitRegistrationForm")[0];
		var formData = new FormData(form);

		if ($('#unitRegistrationForm').valid()) {
			$.ajax({
				type : "POST",
				url : "${saveUnitRegistrationURL}", // Set this using <portlet:resourceURL />
				data : formData,
				processData : false,
				contentType : false,
				success : function(response) {
				try {
					var data = (typeof response === "string") ? JSON.parse(response): response;

					if (data.createUnit === true) {
						$("#unit-save-message").text("<liferay-ui:message key='unit-registration-success' />");
					} else {
						$("#unit-save-message").text("<liferay-ui:message key='unit-registration-failed' />");
					}

					$("#saveUnitModal").modal("show");
				} catch (e) {
					console.error("Failed to parse response", e);
				}
			},
			error : function(xhr, status, error) {
				console.error("AJAX Error:", error);
			}
		});
		}
	}
function closeModal(id) {
	debugger
	$("#" + id).modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}
</script>
