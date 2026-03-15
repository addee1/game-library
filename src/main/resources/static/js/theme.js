const toggle = document.getElementById("themeToggle");

toggle.addEventListener("change", () => {

    if(toggle.checked){

        document.body.classList.remove("theme-light");
        document.body.classList.add("theme-dark");

        localStorage.setItem("theme","dark");

    } else {

        document.body.classList.remove("theme-dark");
        document.body.classList.add("theme-light");

        localStorage.setItem("theme","light");

    }

});


window.addEventListener("load", () => {

    const savedTheme = localStorage.getItem("theme");

    if(savedTheme === "light"){

        document.body.classList.remove("theme-dark");
        document.body.classList.add("theme-light");

        toggle.checked = false;

    }

});