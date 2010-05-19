$(document).ready(function () {
	
	// Tableaux triables
	//$("table").tablesorter({sortList: [[0,0]]});
	
	// Graphiques utilisateur
	//$.plot($("#chartdiv"), data);

	// Creation de questionnaire
	bindAddQuestion();
	bindAddAnchor();
	
	// Nuage de tags
	$('#tagsCloud').tagcloud();
});

/********************************************************************************************
 * Ajout d'une nouvelle question
 */

function bindAddQuestion() {
	$('#addQuestion').click(function (e) {
		location.href = '/QCM/questionnaire/addQuestion';
	});
}

/********************************************************************************************
 * Tags
 */

function bindAddAnchor() {
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
	
	bindDeleteAnchors();
}

function bindDeleteAnchors() {

	$('.deleteTag a').unbind('click').click(function (e) {
		e.preventDefault();
		
		$('#tags').load('/QCM/questionnaire/deleteTag/' + $(this).attr('rel'), null, function () {
			bindDeleteAnchors();
		});
	});
}

/********************************************************************************************/

