function spinner_on(){
    $('body').addClass('noscroll');
    $('.SPINNER_HOLDER').removeClass('hidden');
}
function spinner_off(){
    $('body').removeClass('noscroll');
    $('.SPINNER_HOLDER').addClass('hidden');
}

function doDelete(url, cb, cerr){
    $.ajax({
        url: url,
        method: "DELETE"
    })
    .done(cb)
    .fail(cerr);
}

function doPut(data, url, cb, cerr){
    var jsData = JSON.stringify(data);
    $.ajax({
        contentType : 'application/json',
        url: url,
        method: "PUT",
        data: jsData
    })
    .done(cb)
    .fail(cerr);
}