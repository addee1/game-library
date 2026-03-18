document.addEventListener("DOMContentLoaded", () => {

    const form = document.querySelector(".games-toolbar");
    if (!form) return;

    // SELECT FILTERS (platform, price, genre)
    const selects = form.querySelectorAll("select");
    selects.forEach(select => {
        select.addEventListener("change", () => {
            form.submit();
        });
    });

    // FEATURED TOGGLE
    const featured = form.querySelector('input[name="featured"]');
    if (featured) {
        featured.addEventListener("change", () => {
            form.submit();
        });
    }

    // SEARCH (Enter key)
    const search = form.querySelector('input[name="search"]');
    if (search) {
        search.addEventListener("keypress", (e) => {
            if (e.key === "Enter") {
                e.preventDefault(); // viktigt så inte dubbel submit sker
                form.submit();
            }
        });
    }

});