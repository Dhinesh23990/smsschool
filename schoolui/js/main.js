
	$('#listResults').on('click', 'li:first', function() {
			$("#slimScrollDiv").find('ul li:first').remove('active');
			$("#slimScrollDiv").find('ul li:first').addClass('active');
			$("#slimScrollDiv").find('ul').parent('li').removeClass('active')
		});
          var l = $(this),
		  o = $("#slimScrollDiv").find('ul').parent('li'),
          n = o.children('a'),
		  k = o.find('ul li a');
		  kl = o.find('ul li');
          c = $("#slimScrollDiv").find('.nav-sidebar').children('li').not(o),
          d = c.children('a'),
          m = $('.appWrap'),
          b = m.find('#navigation-toggle');
          //g = angular.element(a);
          l.on('mouseenter', function () {
            m.hasClass('hz-menu') && !m.hasClass('viewport-sm') && (l.addClass('nav-expanded'), m.addClass('nav-expanded'))
          }),
          l.on('mouseleave', function () {
            m.hasClass('hz-menu') && !m.hasClass('viewport-sm') && (l.removeClass('nav-expanded'), m.removeClass('nav-expanded'))
          }),
          o.addClass('dropdown');
          var h = o.find('ul >.dropdown');
          h.addClass('submenu'),
          n.append('<span class="indicator"></span>'),
          n.on('click', function (t) {
            var a = $(this),
            i = a.parent('li'),
            l = $('.submenu.open');
			$("#slimScrollDiv").find('li').removeClass('active');
			i.addClass('active');
            i.hasClass('submenu') || o.not(i).removeClass('open').find('ul').slideUp(),
            l.not(a.parents('.submenu')).removeClass('open').find('ul').slideUp(),
            i.toggleClass('open').find('>ul').stop().slideToggle(),
            t.preventDefault()
          }),
		  k.on('click', function () {
				kl.removeClass('active');
				$(this).closest('li').addClass('active');
          }),
          d.on('click', function () {
            o.removeClass('open').find('ul').slideUp()
          });
          var v = $('.dropdown>ul>.active').parent();
          v.css('display', 'block');
		  b.on('click', function () {
            m.hasClass('viewport-sm') ? (l.toggleClass('navigation-hidden'), m.toggleClass('navigation-hidden'))  : (l.toggleClass('navigation-sm'), m.toggleClass('navigation-sm')),
			$("#nav-bar").hasClass('navigation-sm') ? ($("#nav-bar").removeClass('navigation-sm')) : ($("#nav-bar").addClass('navigation-sm'))
          });