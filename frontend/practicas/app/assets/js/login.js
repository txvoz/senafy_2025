var BASE_URL_LOGUP = ROOT_URL + "user/logup";

$(function(){
    onSubmitFormLogUp();
});

function onSubmitFormLogUp(){
    $("#logup-form form").on("submit", function(event) {
        event.preventDefault();

        var request = {
            "idType":  $("#id_type").val(),
            "idNumber": $("#id_number").val(), 
            "firstName": $("#first_name").val(), 
            "lastName": $("#last_name").val(),
            "gender": $("#gender").val(), 
            "email": $("#email").val(),
            "password": $("#pwd").val()
        };

        saveNewUser(request, function(response){
            alert("Registro exitoso");
            event.target.reset(); 
        }, cbError); 

    });
}

function saveNewUser(data,cbSuccess,cbError) {
    var url = BASE_URL_LOGUP;
    var method = "POST";
    callApi(url, method, data, cbSuccess, cbError);
}

function cbError(error) {
    var json = JSON.stringify(error);
    var obj = JSON.parse(json);
    console.log(obj);
    alert(obj.message);
}