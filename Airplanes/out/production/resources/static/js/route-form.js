function showFilters(checkbox, text) {
    var checkBox = document.getElementById(checkbox);

    var filter = document.getElementById(text)

    if (checkBox.checked == true) {
        filter.style.display = "block";
    } else {
        filter.style.display = "none";
    }
}

function bookFlight() {
    $.post('/flight/book', {
        data: JSON.stringify(route),
    });
}