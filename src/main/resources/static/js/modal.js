console.log("modal.js loaded");
document.addEventListener("DOMContentLoaded", () => {
    console.log("modal.js loaded");
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
    const editButtons = document.querySelectorAll(".openEditModal");
    console.log(editButtons);
    const form = document.getElementById("gameForm");


    if(addBtn){
        addBtn.onclick = () => {

           modal.classList.add("active");

                   document.querySelector(".modal__header h2").textContent = "Add New Game";

                   form.action = "/games";

                   // 🔥 RESET FORM
                   form.reset();

                   // 🔥 Reset genres UI
                   document.querySelectorAll('[name="genres"]').forEach(cb => cb.checked = false);
                   updateSelectedText();

                   // 🔥 Reset featured checkbox
                   document.querySelector('[name="featured"]').checked = false;

        };
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
    if(deleteButtons.length && deleteModal && deleteForm){

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

    }
    if(cancelDelete){
        cancelDelete.addEventListener("click", () => {
            deleteModal.classList.remove("active");
        });
    }


    // update game
    editButtons.forEach(button => {

        button.addEventListener("click", () => {

            modal.classList.add("active");

            document.querySelector(".modal__header h2").textContent = "Edit Game";

            const id = button.dataset.id;
            const genres = (button.dataset.genres || "")
                .split(",")
                .map(g => g.trim());

            document.querySelector('[name="title"]').value = button.dataset.title;
            document.querySelector('[name="platform"]').value = button.dataset.platform;
            document.querySelector('[name="developer"]').value = button.dataset.developer;
            document.querySelector('[name="price"]').value = button.dataset.price;
            document.querySelector('[name="imageUrl"]').value = button.dataset.image;
            document.querySelector('[name="description"]').value = button.dataset.description;
            document.querySelector('[name="releaseDate"]').value = button.dataset.releasedate || "";
            document.querySelector('[name="featured"]').checked = button.dataset.featured === "true";
            document.querySelectorAll('[name="genres"]').forEach(cb => {
                cb.checked = genres.includes(cb.value);
            });

            updateSelectedText();
            form.action = `/games/${id}`;

        });

    });

});