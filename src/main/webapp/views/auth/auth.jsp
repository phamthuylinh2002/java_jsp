<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=1">

    <meta http-equiv="cache-control" content="no-cache,no-store">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="-1">
    <meta name="mswebdialog-title" content="Connecting to FPT Corporation">

    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
    <title>${page.title }</title>
	<link rel="stylesheet" href="${path}/views/css/home-realm/style.css"> 
	<link rel="stylesheet" href="${path}/views/css/style.bundle.css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,600" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.16/webfont.js"></script>


    <style>
    .illustrationClass {
        background-image: url('${path}/views/css/home-realm/img/logo/illustration.png');
    }
    </style>
</head>

<body dir="ltr" class="body" style="font-family:tahoma">

    <div id="fullPage">
        <div id="brandingWrapper" class="float">
            <div id="branding" class="illustrationClass"></div>
        </div>
        <div id="contentWrapper" class="float">
            <div id="content">
                <div id="header">
                    <img class="logoImage" id="companyLogo" src="${path}/views/css/home-realm/img/logo/Poly.png"
                        alt="FPT Corporation">
                </div>

                <div id="workArea">
                    <div id="hrdArea">
                        <jsp:include page="login.jsp"  />
                    </div>
                </div>

                <div id="footerPlaceholder"></div>
            </div>

        </div>      
    </div>
</body>
</html>
