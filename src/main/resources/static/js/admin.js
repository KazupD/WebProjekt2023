const logout_button = document.getElementById("logout");

logout_button.addEventListener('click',function ()
{
    alert("You are going back to the main page.")
    location.assign('/');
});