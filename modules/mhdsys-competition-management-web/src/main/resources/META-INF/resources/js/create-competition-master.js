
$(document).ready(function () {
    // Add a change event to all checkboxes with the class "ageGroupCheckbox"
    $('.ageGroupCheckbox').on('change', function () {
        if ($(this).is(':checked')) {
            $(this).val('true'); // Set value to true if checked
        } else {
            $(this).val('false'); // Set value to false if unchecked
        }
    });
});
function saveCompetitionMaster(){debugger
	var form = $("#create_comp_master")[0];
	var formData = new FormData(form);
	 if($('#create_comp_master').valid()){
 $.ajax({
        type: "POST",
        url: saveCompetitionMasterURL,
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
        	if(data.createCompetition == true){
        		var $jq = jQuery.noConflict();
        		$jq("#saveCompetitionMasterModal").modal('show');
        		 /* $("#saveCompetitionMasterModal").modal('show');  */
        	}else{
        		var msg = competitionCreationErrorMessage;
        		 $("#saveCompetitionMasterModal").modal('show'); 
        	}
    	 }
       
    });
   }
};

function closeModal() {
    $("#saveCompetitionMasterModal").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}
