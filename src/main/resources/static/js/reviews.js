const toggleBtn = document.getElementById("toggleReviewForm");
const reviewForm = document.getElementById("reviewForm");

if(toggleBtn){
    toggleBtn.addEventListener("click", () => {
        reviewForm.classList.toggle("active");
    });
}


/* STAR RATING */
const stars = document.querySelectorAll("#starRating span");
const ratingInput = document.getElementById("ratingInput");

stars.forEach(star => {

    star.addEventListener("click", () => {

        const value = star.dataset.value;


        if(ratingInput){
            ratingInput.value = value;
        }

        stars.forEach(s => {
            s.classList.remove("active");
        });

        stars.forEach(s => {
            if(s.dataset.value <= value){
                s.classList.add("active");
            }
        });

    });

});

setTimeout(() => {
    const msg = document.querySelector(".form-success");
    if(msg){
        msg.style.opacity = "0";
    }
}, 3000);