<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <base href="<%=basePath%>">
    
    <title>Display file upload form to the user</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Events Around U">
	<link rel="stylesheet" type="text/css" href="master.css">
  </head>
	<body> 
	<div class="banner">
  		<label>Malicious App Analyzer</label>
  	</div>
	
	<div class="UploadTable">
	<form ENCTYPE="multipart/form-data" ACTION="upload.jsp" METHOD=POST>
	<br><br><br>

	<table border="0" bgcolor=#ccFDDEE>
	<tr>
		<td colspan="2" align="center"><B>UPLOAD THE FILE</B></td>
	</tr>
	<tr>
		<td colspan="2" align="center"> </td>
	</tr>
	<tr>
		<td><b>Choose the file To Upload:</b></td>
		<td><INPUT NAME="file" TYPE="file"></td>
	</tr>
	<tr>
		<td colspan="2" align="center"> </td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Send File"> </td>
	</tr>
	</table>
</form>
</div>
</body>
</html>
	