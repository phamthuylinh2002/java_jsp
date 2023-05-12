
function myFunction(event){
	var url = window.location.href;
	url = url.substring(0,url.indexOf("#"));
	url = url.substring(0,url.indexOf("?"));
	console.log(104, event.target.value);
	const dot_id = event.target.value;
	
	window.location.href = url+"?dot-dang-ky-id="+dot_id;
	
};