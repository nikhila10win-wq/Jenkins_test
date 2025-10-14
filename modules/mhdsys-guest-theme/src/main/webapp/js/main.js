//Dark Theme
let lightmode = localStorage.getItem('dark-mode');
const lightmodeToggle = document.querySelector('#theme-icon');
const enableLightMode = () => {
    document.body.classList.add('dark-mode');
    localStorage.setItem('dark-mode', 'enabled');
}

const disablelightmode = () => {
    document.body.classList.remove('dark-mode');
    localStorage.setItem('dark-mode', null);    
}

if (lightmode === 'enabled') {
    enableLightMode();
}

lightmodeToggle.addEventListener('click', () => {
    lightmode = localStorage.getItem('dark-mode'); 

    if (lightmode !== 'enabled') {
        enableLightMode();
    } else {  
        disablelightmode();
    }
});

//Back to Top Btn
$(document).ready(function(){ 
    $(window).scroll(function(){ 
        if ($(this).scrollTop() > 100) { 
            $('#scroll').fadeIn(); 
        } else { 
            $('#scroll').fadeOut(); 
        } 
    }); 
    $('#scroll').click(function(){ 
        $("html, body").animate({ scrollTop: 0 }, 600); 
        return false; 
    }); 
});