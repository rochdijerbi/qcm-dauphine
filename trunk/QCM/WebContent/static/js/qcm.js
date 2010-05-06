$(document).ready(function () {
	
	$("table").tablesorter({ 
        sortList: [[0,0]] 
    }); 
	
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
		
    var data = [[0, 3], [4, 8], [8, 5], [9, 13]];

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
