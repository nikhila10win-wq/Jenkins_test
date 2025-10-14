AUI().ready(function () {});
Liferay.Portlet.ready(function (_portletId, _node) {});
Liferay.on('allPortletsReady', function () {});

/*
Dashboard related scripts
*/

$(document).ready(function() {
$(".hamburger").click(function() {
    $(this).toggleClass("is-active");
    $('.maharashtraSports-dashboard-wrapper').toggleClass('closed-sidebar');
    $('.submenuList.open').slideToggle(500);
});
});
 

//submenuDropdown js start

(function($) {
$(document).ready(function() {
    $('.submenuDropdown.active').addClass('active').children('.submenuList').addClass('open').show();
    $('.submenuDropdown #submenuClick').on('click', function() {
        var element = $(this).parent('li');
        if (element.hasClass('active')) {
            element.removeClass('active');
            element.find('li').removeClass('active');
            element.find('.submenuList.open').removeClass('open').slideUp(200);
        } else {
            element.addClass('active');
            element.children('.submenuList').addClass('open').slideDown(200);
            element.siblings('li').children('.submenuList').removeClass('open').slideUp(200);
            element.siblings('li').removeClass('active');
            element.siblings('li').find('li').removeClass('active');
            element.siblings('li').find('.submenuList').slideUp(200);
        }
    });

});
})(jQuery);

//dropdown js start

$(function() {

$('li.dropdown > a').on('click', function(event) {

    event.preventDefault();
    $(this).toggleClass('selected');
    $(this).parent().find('ul').first().toggle(300);

    $(this).parent().siblings().find('ul').hide(200);

    //Hide menu when clicked outside
    $(this).parent().find('ul').parent().mouseleave(function() {
        var thisUI = $(this);
        $('html').click(function() {
            thisUI.children(".dropdown-menu").hide();
            thisUI.children("a").removeClass('selected');

            $('html').unbind('click');
        });
    });
});

});

$(document).on("click", function(e) {
if (!(e.target.closest('.myDropdown'))) {
    $(".dropdownList").slideUp();
}
});

//fix deshbord on scroll js 

$(window).scroll(function() {
if ($(this).scrollTop() > 0) {
    $('.maharashtraSports-dashboard-wrapper').addClass('deshbordmainFixed');
} else {
    $('.maharashtraSports-dashboard-wrapper').removeClass('deshbordmainFixed');
}
});


/*Dark Mode*/
  if (sessionStorage.getItem('darkMode') === 'true') {
    document.documentElement.classList.add('dark-mode');
  }

  document.addEventListener('DOMContentLoaded', function () {
    const darkModeToggle = document.getElementById('darkModeToggle');

    if (darkModeToggle) {
      darkModeToggle.addEventListener('click', function () {
        if (document.documentElement.classList.contains('dark-mode')) {
          disableDarkMode();
        } else {
          enableDarkMode();
        }
      });
    }
  });

function enableDarkMode() {
	document.documentElement.classList.add('dark-mode');
	sessionStorage.setItem('darkMode', 'true');
}

function disableDarkMode() {
	document.documentElement.classList.remove('dark-mode');
	sessionStorage.setItem('darkMode', 'false');
} 
  
$(document).ready(function(){
	$(".custom-file-input").on("change", function() {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
  }); 
});