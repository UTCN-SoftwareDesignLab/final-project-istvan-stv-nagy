function showFilters(checkbox, text1, text2) {
    var checkBox = document.getElementById(checkbox);

    var filter1 = document.getElementById(text1);
    var filter2 = document.getElementById(text2);

    if (checkBox.checked == true) {
        filter1.style.display = "block";
        filter2.style.display = "block";
    } else {
        filter1.style.display = "none";
        filter2.style.display = "none";
    }
}