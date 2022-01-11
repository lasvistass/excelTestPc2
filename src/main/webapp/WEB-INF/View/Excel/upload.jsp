<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<input id="fileupload" type="file" name="fileupload">
	<button id="uploadButton" onclick="uploadFile()"> UPLOAD </button>
	<button id="uploadButton" onclick="uploadFileExcel()"> UPLOAD Excel</button>
	
	<script>
		async function uploadFile() {
		  let formData = new FormData(); 
		  formData.append("file", fileupload.files[0]);
			  let response = await fetch('/upload', {
			    method: "POST", 
			    body: formData
				}); 

		  	if (response.status == 200) {
		    alert("File successfully uploaded.");
		 	 }
		}
	</script>
	<script>
		async function uploadFileExcel() {
		  let formData = new FormData(); 
		  formData.append("file", fileupload.files[0]);
			  let response = await fetch('/upload/excel', {
			    method: "POST", 
			    body: formData
				}); 

		  	if (response.status == 200) {
		    alert("File successfully uploaded.");
		 	 }
		}
	</script>


</body>
</html>