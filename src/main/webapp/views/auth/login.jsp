<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="m-login__signin">
	<c:if test="${message!=null}">
		<div class="mt-5">
			<div class="alert alert-danger">${message}</div>
		</div>
	</c:if>
	<div class="container mt-5">
		<div class="row justify-content-center mt-10">
			<a id="gSignInWrapper"
				href="https://accounts.google.com/o/oauth2/auth?
	scope=email&redirect_uri=${URI}&response_type=code&client_id=${ID}&approval_prompt=force">
				<div id="customBtn" class="customGPlusSignIn" style="padding: 10px;">
					<div class="google-icon-wrapper d-inline">
						<img class="google-icon"
							src="https://upload.wikimedia.org/wikipedia/commons/5/53/Google_%22G%22_Logo.svg" />
					</div>
					<span class="buttonText">Đăng nhập với Google</span>
				</div>
			</a>
		</div>
	</div>
</div>

<style type="text/css">
.mt-10 {
	margin-top: 10rem;
}

img.google-icon {
	width: 40px;
	height: 40px;
}

#customBtn {
	display: inline-block;
	background: white;
	color: #444;
	border-radius: 5px;
	border: thin solid #888;
	box-shadow: 1px 1px 1px grey;
	white-space: nowrap;
}

#customBtn:hover {
	cursor: pointer;
}

span.buttonText {
	display: inline-block;
	vertical-align: middle;
	padding-left: 42px;
	padding-right: 42px;
	font-size: 14px;
	font-weight: bold;
	/* Use the Roboto font that is loaded in the <head> */
	font-family: 'Roboto', sans-serif;
}
</style>
