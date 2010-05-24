$(document).ready(function () {

	// Creation de questionnaire
	bindAddQuestion();
	bindAddAnchor();
});

/********************************************************************************************
 * Ajout d'une nouvelle question
 */

function bindAddQuestion() {
	$('#addQuestion').click(function (e) {
		var form = $('#questionnaire');
		form.attr('action', '/QCM/questionnaire/addQuestion#addQuestion');
		form.submit();
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

