$(document).ready(function () {
	
	$("table").tablesorter({ 
        sortList: [[0,0]] 
    }); 
	
	$('#addTag').click(function (e) {
		e.preventDefault();
		
		$('#tags').load('/QCM/questionnaire/addTag/' + $('#newTag').val(), null, function () {
			$('#newTag').val('');
			$('#addTag').css('display', 'none');
			bindDeleteAnchors();
		});
	});
	
	$('#newTag').each(function () {
		$('#addTag').css('display', 'none');
		
	}).keyup(function (e) {
		var tag = jQuery.trim($(this).val());
		
		if (tag != '') {
			$('#addTag').css('display', 'inline');
		} else {
			$('#addTag').css('display', 'none');
		}
	});
		
	$.plot($("#chartdiv"), data);

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
