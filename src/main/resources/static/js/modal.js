const addBtn = document.querySelector(".add-game-btn");
const modal = document.getElementById("addGameModal");
const closeBtn = document.getElementById("closeAddGame");
const genreToggle = document.getElementById("genreDropdownToggle");
const genreMenu = document.getElementById("genreDropdownMenu");

const deleteButtons = document.querySelectorAll(".openDeleteModal");
const deleteModal = document.getElementById("deleteModal");
const cancelDelete = document.getElementById("cancelDelete");
const deleteText = document.getElementById("deleteText");
const deleteForm = document.getElementById("deleteForm");


if(addBtn){
    addBtn.onclick = () => modal.classList.add("active");
}

if(closeBtn){
    closeBtn.onclick = () => modal.classList.remove("active");
}

if(modal){
    modal.onclick = (e) => {
        if(e.target === modal){
            modal.classList.remove("active");
        }
    };
}


if (genreToggle && genreMenu) {

    const checkboxes = genreMenu.querySelectorAll('input[type="checkbox"]');

    function updateSelectedText() {

        const checked = genreMenu.querySelectorAll('input[type="checkbox"]:checked').length;

        if (checked === 0) {
            genreToggle.textContent = "Select genres";
        } else if (checked === 1) {
            genreToggle.textContent = "1 selected";
        } else {
            genreToggle.textContent = `${checked} selected`;
        }
    }

    genreToggle.addEventListener("click", () => {
        genreMenu.classList.toggle("is-open");
    });

    checkboxes.forEach(box => {
        box.addEventListener("change", updateSelectedText);
    });

    document.addEventListener("click", (event) => {
        if (!genreToggle.contains(event.target) && !genreMenu.contains(event.target)) {
            genreMenu.classList.remove("is-open");
        }
    });

}

// DELETE MODAL
deleteButtons.forEach(button => {

    button.addEventListener("click", (e) => {

        e.preventDefault();

        const gameId = button.dataset.id;
        const gameTitle = button.dataset.title;

        deleteText.textContent =
            `Are you sure you want to delete "${gameTitle}"?`;

        deleteForm.action = `/games/${gameId}/delete`;

        deleteModal.classList.add("active");

    });

});

cancelDelete.addEventListener("click", () => {
    deleteModal.classList.remove("active");
});