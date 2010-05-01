$(document).ready(function () {
	
	$('#addTag').click(function (e) {
		e.preventDefault();
		
		var tag = jQuery.trim($('#newTag').val());
		
		if (tag != '') {
			$('#tags').load('/QCM/questionnaire/addTag/' + tag, null, function () {
				$('#newTag').val('');
				bindDeleteAnchors();
			});
		}
	});
	
	bindDeleteAnchors();
	
	$('#tagsCloud').tagcloud();
});

function bindDeleteAnchors() {

	$('.deleteTag a').unbind('click').click(function (e) {
		e.preventDefault();
		
		$('#tags').load('/QCM/questionnaire/deleteTag/' + $(this).attr('rel'), null, function () {
			bindDeleteAnchors();
		});
	});
}
