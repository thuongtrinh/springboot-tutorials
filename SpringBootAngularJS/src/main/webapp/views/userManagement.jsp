<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringBoot-AngularJS</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="css/app.css">

<style>
	.username.ng-valid {
		background-color: lightgreen;
	}
	.username.ng-dirty.ng-invalid-required {
		background-color: red;
	}
	.username.ng-dirty.ng-invalid-minlength {
		background-color: yellow;
	}
	.email.ng-valid {
		background-color: lightgreen;
	}
	.email.ng-dirty.ng-invalid-required {
		background-color: red;
	}
	.email.ng-dirty.ng-invalid-email {
		background-color: yellow;
	}
</style>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="UserController as ctrl">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="lead">User Registration Form </span>
					</div>

					<form name="myForm" class="form-horizontal bg-info df-form-one" ng-submit="ctrl.submit()" name="myForm">
						<input type="hidden" ng-model="ctrl.user.id">
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="uname">Name</label>
								<div class="col-md-8">
									<input type="text" id="uname" class="username form-control input-sm" required ng-minlength="3"
										placeholder="Enter your name" ng-model="ctrl.user.username"/>
									<div class="has-error" ng-show="myForm.$dirty">
										<span ng-show="myForm.uname.$error.required">This is a required field</span>
										<span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
										<span ng-show="myForm.uname.$invalid">This field is invalid </span>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="address">Address</label>
								<div class="col-md-8">
									<input type="text" id="address" class="form-control input-sm" ng-model="ctrl.user.address"
										placeholder="Enter your Address. [This field is validation free]" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="email">Email</label>
								<div class="col-md-8">
									<input type="email" id="email" class="email form-control input-sm" ng-model="ctrl.user.email"
										placeholder="Enter your Email" required />
									<div class="has-error" ng-show="myForm.$dirty">
										<span ng-show="myForm.email.$error.required">This is a required field</span>
										<span ng-show="myForm.email.$invalid">This field is invalid </span>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12 text-right">
								<input type="submit" value="{{!ctrl.user.id ? 'Add' : 'Update'}}"
									ng-disabled="myForm.$invalid" class="btn btn-primary btn-sm">
								<button type="button" ng-click="ctrl.reset()"
									ng-disabled="myForm.$pristine" class="btn btn-warning btn-sm">Reset Form
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<span class="lead">List of Users </span>
					</div>
					<div class="tablecontainer">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>ID.</th>
									<th>Name</th>
									<th>Address</th>
									<th>Email</th>
									<th width="20%"></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="u in ctrl.users">
									<td><span ng-bind="u.id"></span></td>
									<td><span ng-bind="u.username"></span></td>
									<td><span ng-bind="u.address"></span></td>
									<td><span ng-bind="u.email"></span></td>
									<td>
										<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>
										<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-5 col-md-offset-2">
				<a href="/SpringBootAngularJS/home" class="badge badge-secondary">Back home</a>
			</div>
		</div>
	</div>

	<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script> -->
	<script type="text/javascript" src="<c:url value='/js/lib/angular.min.js' />"></script>
	<script src="<c:url value='/js/app.js' />"></script>
	<script src="<c:url value='/js/service/user_service.js' />"></script>
	<script src="<c:url value='/js/controller/user_controller.js' />"></script>
</body>
</html>

