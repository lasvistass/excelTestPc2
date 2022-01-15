<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Excel</title>
<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
	<div align="center" class="mt-5 bg">
		<div class="ml-3">
			<h2 class="text-center text-info">Upload Excel</h2>
			<br> <br>
				<c:forEach items="${list}" var="list">
					<tr>
						<td>${list}</td>
	
					</tr>
				</c:forEach>

			<div class="margin-top">
				<input class="btn btn-info" id="fileupload" type="file"
					name="fileupload"> <br> <br>

				<button type="submit" class="btn btn-success" id="uploadButton"
					onclick="uploadFileExcel()">UPLOAD Excel</button>
			</div>

			
		<script>
			
		  async function uploadFileExcel() {
				let formData = new FormData();
				formData.append("file", fileupload.files[0]);
				let response = await
				fetch('/upload/excel', {
					method : "POST",
					body : formData
				});


				if (response.status == 200) {
					alert("Il file � stato caricato con successo.");
				}else{
					alert(" ** ATTENZIONE ** Il file non � corretto.")
				}
			}
		 
	   </script>

			<br>


		</div>
		<br> <br>
		<div class="ml-5" align="left">

			<h6>Example</h6>
			<div class="mt-3 " align="left">
				<div class="w-25">
					<div class="h-25">
						<table class="table">
							<thead>
								<tr class="bg-light">

									<th scope="col">Nome Prodotto</th>
									<th scope="col">Categoria</th>
									<th scope="col">Prezzo</th>
								</tr>
							</thead>
							<tbody>
								<tr>

									<td>Pc Acer k22zy'</td>
									<td>Informatica</td>
									<td>649,99</td>
								</tr>
								<tr class="bg-light">

									<td>Gilet LHNK</td>
									<td>Abbigliamento</td>
									<td>43,99</td>
								</tr>
								<tr>

									<td>Salmone Premium</td>
									<td>Alimenti</td>
									<td>8,00</td>
								</tr>
								<tr>

									<td colspan="2"></td>
									<td></td>
								</tr>

							</tbody>
						</table>


					   
					</div>
				</div>
			</div>
		</div>
	</div>
	<div align="right" class="mr-5">
		<a class="btn btn-dark" href="http://localhost:8080/index/home"
			role="button">Home</a>

	</div>

</body>
</html>