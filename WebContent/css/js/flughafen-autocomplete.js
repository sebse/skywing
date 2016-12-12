$(function(){
	var flughafen = [
		{ value: 'Wien Schwechat (VIE)', code: 'VIE' },
		{ value: 'Bangkok Suv. (BKK)', code: 'BKK' },
		{ value: 'London Heathrow (LHR)', code: 'LHR' },
		{ value: 'London Gatwick (LGW)', code: 'LGW' },
		{ value: 'London Stansted (STN)', code: 'STN' },
		{ value: 'Istanbul Sabiha Gokcen (SAW)', code: 'SAW' },
	];

	$('#ab_ort').autocomplete({
		lookup: flughafen,
		onSelect: function (suggestion) {
			$('#ab_ort').val(suggestion.code);
		}
	});

	$('#an_ort').autocomplete({
		lookup: flughafen,
		onSelect: function (suggestion) {
			$('#an_ort').val(suggestion.code);
		}
	});
});