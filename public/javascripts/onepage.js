/* ================================================
----------- Trego Premium Html5 Template ---------- 
-------- Additional js codes for onepage.html ---
==================================================*/
(function($) {
	"use strict";
	var TregoOnepage = {
		initialised : false,
		init: function () {

			if(!this.initialised) {
				this.initialised = true;
			} else {
				return;
			}
			
			this.scrollToElem();
			this.scrollDownBtn();
			this.twitterSlider();
			
		},
		scrollToElem: function () {
			var self = this;
			$('.menu').find('a').on('click', function (e) {
				var $this = $(this),
					targetId = $this.attr('href'),
					targetElem = $(targetId);

					// Check for # in href if not return/go to page
					if(targetId.indexOf('#') === -1) {
						return;
					}

					if (targetElem.length) {
						var targetPosition = targetElem.offset().top;

						$('html, body').animate({'scrollTop': targetPosition},800, function() {
							self.scrollSpyRefresh();
						});
					} else {
						return
					}
					
				e.preventDefault();
			});
		},
		scrollDownBtn: function() {
			var scrollDown = $('.rev-scroll-down'),
				self = this;

				scrollDown.on('click', function (e) {
					var $this = $(this),
						targetId = $this.attr('href'),
						targetElem = $(targetId);

						if (targetElem.length) {
							var targetPosition = targetElem.offset().top;

							$('html, body').animate({'scrollTop': targetPosition},800, function() {
								self.scrollSpyRefresh();
							});
						}

					e.preventDefault();
				});
		},
		twitterSlider: function () {
			/* =========================================
			---- Twitter Feed Plugin 
			=========================================== */
			    if ($.fn.tweet) {
			        $('.latest-tweets-slider').tweet({
			            modpath: './js/twitter/',
			            avatar_size: '',
						count: 3,
						query: "themeforest", // write feed query here
						loading_text: "searching twitter...",
			            join_text: "",
			            template: "{join}{text}{time}<a class='btn' href='#' title='Follow Us'>Follow Us</a>"
			            /* etc... */
			        });
			        
			        $('.latest-tweets-slider.flexslider').flexslider({
						animation: "slide",
						selector: ".tweet_list > li",
						directionNav: false,
						controlNav: true,
						prevText: '',
						nextText: '',
						animationLoop: true,
						smoothHeight: true,
						slideshowSpeed: 5000
					});
			    }

		},
		scrollSpyRefresh: function () {
			// Refresh ScrollSpy
			$('[data-spy="scroll"]').each(function () {
				var $spy = $(this).scrollspy('refresh')
			});
		}
		
	};


	// Init Trego
	TregoOnepage.init();

	$(window).on('resize', function() {
		// Refresh ScrollSpy
		TregoOnepage.scrollSpyRefresh();
    });

	/*  check for pace loader plugin (included in html head tags )*/
	/* Animate Elements When pace page loader plugin done*/
	if(typeof Pace != 'undefined') {

	} else { 

	}
    
})(jQuery);

/*----------------------------------------------------*/
//* Google javascript api v3  -- map */
/*----------------------------------------------------*/
(function () {
    "use strict";

    function initialize() {
        /* change your with your coordinates */

        var myLatLng = new google.maps.LatLng(51.520068, -0.156299), // Your coordinates
            mappy = {
                center: myLatLng,
                zoom: 15,
                scrollwheel: false,
                mapTypeId: google.maps.MapTypeId.ROADMAP
                /* Delete comments if you want styled map ,styles: [{
                    "elementType": "geometry",
                    "stylers": [{
                        "hue": "#000"
                    }, {
                        "weight": 1
                    }, {
                        "saturation": -200
                    }, {
                        "gamma": 0.70
                    }, {
                        "visibility": "on"
                    }]
                }]
                */
            };
        var onepageMap = new google.maps.Map(document.getElementById("onepage-map"), mappy),
        	newpin = 'images/pin.png';
        	

        new google.maps.Marker({
            position: myLatLng,
            map: onepageMap,
            icon: newpin,
            animation: google.maps.Animation.DROP,
            title: 'Trego Onepage' // Title for marker
        });

    }

    if (document.getElementById("onepage-map")) {
        google.maps.event.addDomListener(window, 'load', initialize);
    }

}());