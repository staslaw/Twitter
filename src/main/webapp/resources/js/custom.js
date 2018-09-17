$(document).on("click", "a.confirm", function(e) {
    e.preventDefault();
    if (confirm('Czy jesteś pewien ?')) {
        location.href = $(this).attr('href');
    }
});