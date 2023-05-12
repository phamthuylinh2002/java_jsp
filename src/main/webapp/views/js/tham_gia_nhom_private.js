document.getElementById("formJoinNhom").addEventListener("submit", function(event) {
	event.preventDefault();
	const url = $(this).attr('action');
	const code = document.getElementById("code_join").value;

	$("#loading").removeClass("d-none");
	$("#formCode").addClass("d-none");
	$("#closeJoinNhom").addClass("d-none");
	$("#buttonJoinNhom").addClass("d-none");

	fetch(url, {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			code_join: code
		})
	}).then(function(response) {
		window.location.reload();
	}).catch(function(error) {
		const errorInfo = error.response;
		if (errorInfo.status === 422) {
			const errors = errorInfo.data.errors;
			_.forEach(errors, function(value, key) {
				$("#formCode").removeClass("d-none");
				$("#closeJoinNhom").removeClass("d-none");
				$("#buttonJoinNhom").removeClass("d-none");
				$("#loading").addClass("d-none");
				$("#" + key + "_error").text(value[0]);
				$("#" + key + "_error").removeClass('hidden');
				$("#" + key + "_error").addClass('text-danger');
			});
		}

		if (errorInfo.status === 403) {
			$("#formCode").removeClass("d-none");
			$("#closeJoinNhom").removeClass("d-none");
			$("#buttonJoinNhom").removeClass("d-none");
			$("#loading").addClass("d-none");
			$("#code_error").text(errorInfo.data);
			$("#code_error").removeClass('hidden');
			$("#code_error").addClass('text-danger');
		}
	});
});