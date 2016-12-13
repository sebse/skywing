$(function(){
	var flughafen = [
		{ value: 'Wien Schwechat (VIE)', code: 'VIE' },
		{ value: 'Amsterdam Schipol (AMS)', code: 'AMS' },
		{ value: 'Antalya (AYT)', code: 'AYT' },
		{ value: 'Atlanta (ATL)', code: 'ATL' },
		{ value: 'Bangkok Suvarnabhumi (BKK)', code: 'BKK' },
		{ value: 'Barcelona (BCN)', code: 'BCN' },
		{ value: 'Boston (BOS)', code: 'BOS' },
		{ value: 'Charlotte (CLT)', code: 'CLT' },
		{ value: 'Chengdu (CTU)', code: 'CTU' },
		{ value: 'Chicago O\'Hare (ORD)', code: 'ORD' },
		{ value: 'Chongquing (CKG)', code: 'CKG' },
		{ value: 'Dallas Fort-Worth (DFW)', code: 'DFW' },
		{ value: 'Denver (DEN)', code: 'DEN' },
		{ value: 'Detroit (DTW)', code: 'DTW' },
		{ value: 'Dubai (DXB)', code: 'DXB' },
		{ value: 'Frankfurt am Main (FRA)', code: 'FRA' },
		{ value: 'Guangzhou (CAN)', code: 'CAN' },
		{ value: 'Hongkong (HKG)', code: 'HKG' },
		{ value: 'Houston (IAH)', code: 'IAH' },
		{ value: 'Istanbul Atatuerk (IST)', code: 'IST' },
		{ value: 'Jakarta (CGK)', code: '(CGK)' },
		{ value: 'Kuala Lumpur (KUL)', code: 'KUL' },
		{ value: 'Kumming (KMG)', code: 'KMG' },
		{ value: 'Las Vegas (LAS)', code: 'LAS' },
		{ value: 'London Gatwick (LGW)', code: 'LGW' },
		{ value: 'London Heathrow (LHR)', code: 'LHR' },
		{ value: 'Los Angeles (LAX)', code: 'LAX' },
		{ value: 'Madrid Barajas (MAD)', code: 'MAD' },
		{ value: 'Manila (MNL)', code: 'MNL' },
		{ value: 'Melbourne (MEL)', code: 'MEL' },
		{ value: 'Mexico City (MEX)', code: 'MEX' },
		{ value: 'MIAMI (MIA)', code: 'MIA' },
		{ value: 'Minneapolis Saint Paul (MSP)', code: 'MSP' },
		{ value: 'Moskau Domodedowo (DME)', code: 'DME' },
		{ value: 'Moskau Scheremetjewo (SVO)', code: 'SVO' },
		{ value: 'Muenchen (MUC)', code: 'MUC' },
		{ value: 'Mumbai (BOM)', code: 'BOM' },
		{ value: 'Neu Delhi Indira Gandhi (DEL)', code: 'DEL' },
		{ value: 'New York John F. Kennedy (JFK)', code: 'JFK' },
		{ value: 'New York LaGuardia (LGA)', code: 'LGA' },
		{ value: 'Orlando (MCO)', code: 'MCO' },
		{ value: 'Paris Charles de Gaulle (CDG)', code: 'CDG' },
		{ value: 'Paris Orly (ORY)', code: 'ORY' },
		{ value: 'Peking (PEK)', code: 'PEK' },
		{ value: 'Philadelphia (PHL)', code: 'PHL' },
		{ value: 'Phoenix (PHX)', code: 'PHX' },
		{ value: 'Rom Fiumicino (FCO)', code: 'FCO' },
		{ value: 'San Francisco (SFO)', code: 'SFO' },
		{ value: 'Sao Paulo Garulhos (GRU)', code: 'GRU' },
		{ value: 'Seattle Tacoma (SEA)', code: 'SEA' },
		{ value: 'Seoul Incheon (ICN)', code: 'ICN' },
		{ value: 'Shanghai Hongquiao (SHA)', code: 'SHA' },
		{ value: 'Shanghai Pudong (PVG)', code: 'PVG' },
		{ value: 'Shenzhen (SZX)', code: 'SZX' },
		{ value: 'Singapur (SIN)', code: 'SIN' },
		{ value: 'Sydney (SYD)', code: 'SYD' },
		{ value: 'Taiwan Taoyuan (TPE)', code: 'TPE' },
		{ value: 'Tokio Haneda (HND)', code: 'HND' },
		{ value: 'Tokio Narita (NRT)', code: 'NRT' },
		{ value: 'Toronto (YYZ)', code: 'YYZ' },
		{ value: 'Xi\'an (XIY)', code: 'XIY' },
		{ value: 'Zuerich (ZRH)', code: 'ZRH' },
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