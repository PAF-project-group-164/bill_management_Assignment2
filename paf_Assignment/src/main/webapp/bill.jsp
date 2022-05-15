<%@page import="com.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>bill management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/bill.js"></script>
</head>
<body>
<div class="container">
 <div class="row">
<div class = col-sm-3>
<br>
</div>
<div class="col-sm-6" >
<h1 class="text-center">Bill Management</h1>
<br>

<form id="formbill" name="formbill" method="post" action="bill.jsp" class="mw-50">
User ID: 
 <input id="uid" name="uid" type="text" 
 class="form-control form-control-sm" >
 <br> Usage: 
 <input id="busage" name="busage" type="text" 
 class="form-control form-control-sm">
 <br> Value: 
 <input id="value" name="value" type="text" 
 class="form-control form-control-sm">
  <br> VAT: 
 <input id="vat" name="vat" type="text" 
 class="form-control form-control-sm">
  <br> Total: 
 <input id="total" name="total" type="text" 
 class="form-control form-control-sm">
 <br>
 
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidbidSave" 
 name="hidbidSave" value="">
 </form>


<br>

<div id="alertSuccess" class="alert alert-success " ></div>
<div id="alertError" class="alert alert-danger"></div>
</div>
<div class = col-sm-3>
<br>
</div>
</div>

</div>
<br>
<div class = "row">

<div id="divbillGrid" class = "col-sm-8">
 <%
 Bill billobj = new Bill(); 
 out.print(billobj.readbilling()); 
 %>
</div>
<div class = "col-sm-4"></div>
</div>
 </div>
 
