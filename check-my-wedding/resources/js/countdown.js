// Made by sujeetkrsingh in https://github.com/sujeetkrsingh/simple-jquery-countdown

(function ( $ ) {
	const languages = {
		"en": { 
			"d" : "Days",
			"h" : "Hours",
			"m" : "Minutes",
			"s" : "Seconds",
		},
		"pt": {
			"d" : "Dias",
			"h" : "Horas",
			"m" : "Minutos",
			"s" : "Segundos"
		}
	}

	function pad(n) {
	    return (n < 10) ? ("0" + n) : n;
	}

	$.fn.showclock = function(lang) {
	    
	    var currentDate=new Date();
	    var fieldDate=$(this).data('date').split('-');
	    var fieldTime=[0,0];
	    if($(this).data('time')!=undefined)
	    fieldTime=$(this).data('time').split(':');
	    var futureDate=new Date(fieldDate[0],fieldDate[1]-1,fieldDate[2],fieldTime[0],fieldTime[1]);
	    var seconds=futureDate.getTime() / 1000 - currentDate.getTime() / 1000;

	    if(seconds<=0 || isNaN(seconds)){
	    	this.hide();
	    	return this;
	    }

	    var days=Math.floor(seconds/86400);
	    seconds=seconds%86400;
	    
	    var hours=Math.floor(seconds/3600);
	    seconds=seconds%3600;

	    var minutes=Math.floor(seconds/60);
	    seconds=Math.floor(seconds%60);
	    
	    var html="";

	    if(days!=0){
		    html+="<div class='countdown-container days'>"
				html+="<span class='countdown-value days-bottom'>"+pad(days)+"</span>";
				html+="<span class='countdown-heading days-top'>" + languages[lang].d + "</span>";
		    html+="</div>";
		}

	    html+="<div class='countdown-container hours'>"
			html+="<span class='countdown-value hours-bottom'>"+pad(hours)+"</span>";
			html+="<span class='countdown-heading hours-top'>" + languages[lang].h + "</span>";
	    html+="</div>";

	    html+="<div class='countdown-container minutes'>"
			html+="<span class='countdown-value minutes-bottom'>"+pad(minutes)+"</span>";
	    	html+="<span class='countdown-heading minutes-top'>" + languages[lang].m + "</span>";
	    html+="</div>";

	    html+="<div class='countdown-container seconds'>"
			html+="<span class='countdown-value seconds-bottom'>"+pad(seconds)+"</span>";
	    	html+="<span class='countdown-heading seconds-top'>" + languages[lang].s + "</span>";
	    html+="</div>";

	    this.html(html);
	};

	$.fn.countdown = function(lang) {
		var el=$(this);
		el.showclock(lang);
		setInterval(function(){
			el.showclock(lang);	
		},1000);
		
	}

}(jQuery));

jQuery(document).ready(function(){
	if(jQuery(".countdown").length>0){
		jQuery(".countdown").each(function(){
			jQuery(this).countdown("pt");	
		})
		
	}
})