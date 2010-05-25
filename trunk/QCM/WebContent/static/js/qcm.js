$(document).ready(function () {

	// Creation de questionnaire
	bindAddQuestion();
	bindAddAnchor();
	datePickers();
	
	$('.answers input[type=checkbox]').unbind('click').click(function (e) {
		$('.' + this.className).attr('checked', '');
		$(this).attr('checked', 'checked');
	});
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

	$('.tag a').unbind('click').click(function (e) {
		e.preventDefault();
		
		$('#tags').load('/QCM/questionnaire/deleteTag/' + $(this).attr('rel'), null, function () {
			bindDeleteAnchors();
		});
	});
	
	$('.questions a').unbind('click').click(function (e) {
		e.preventDefault();
		
		var form = $('#questionnaire');
		form.attr('action', '/QCM/questionnaire/deleteQuestion/' + $(this).attr('rel'));
		form.submit();
	});
}

/********************************************************************************************/

function datePickers() {
	$('.date-pick').datePicker();
	
	$('#start-date').bind(
		'dpClosed',
		function(e, selectedDates)
		{
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#end-date').dpSetStartDate(d.addDays(1).asString());
			}
		}
	);
	
	$('#end-date').bind(
		'dpClosed',
		function(e, selectedDates)
		{
			var d = selectedDates[0];
			if (d) {
				d = new Date(d);
				$('#start-date').dpSetEndDate(d.addDays(-1).asString());
			}
		}
	);
}