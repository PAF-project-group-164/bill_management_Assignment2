$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatebillForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 }
 // If valid------------------------
var type = ($("#hidbidSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "billAPI", 
 type : type, 
 data : $("#formbill").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onbillSaveComplete(response.responseText, status); 
 } 
 }); 
});
 
function onbillSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divbillsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidbidSave").val(""); 
$("#formbill")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidbidSave").val($(this).data("bid")); 
		 $("#uid").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#busage").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#value").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#vat").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#total").val($(this).closest("tr").find('td:eq(4)').text());
		});
 
 $(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "billAPI", 
		 type : "DELETE", 
		 data : "bid=" + $(this).data("bid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onbillDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
 
 
 function onbillDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divbillsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
//client model--------------------------------
function validatebillForm()
{
	// uid
	if ($("#uid").val().trim() == "")
	{
	return "Insert user id.";
	}
	// usage
	if ($("#busage").val().trim() == "")
	{
	return "Insert user usage.";
	}
	

// value-------------------------------
if ($("#value").val().trim() == ""){
		
		return "Insert value.";
}
	// vat------------------------
	if ($("#vat").val().trim() == ""){
		
		return "Insert vat.";
	}
	// total------------------------
	if ($("#total").val().trim() == ""){
		
		return "Insert total.";
	}

return true;
}


 
 