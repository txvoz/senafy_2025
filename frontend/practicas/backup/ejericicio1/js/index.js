$(function () {

    $("#txtValue").on("change", onChangeInputWithErrorClass);
    $("#btnValidar").click(onClickButton);

});

var onChangeInputWithErrorClass = function (e) {
    removeClassError(e.target);
}

var onClickButton = function (e) {
    e.preventDefault();

    cleanOutput();
    removeClassError($("input.error"));

    var value = $("#txtValue").val();// vacio o lo que tiene en el contenido la caja 

    if (value.trim() === "") {
        alert("Ingrese el valor");
        $("#txtValue").addClass("error");//css
        return;
    }

    var newClassToAdd = "";
    var newHtml = "";
    value = parseInt(value);

    if (value == 0) {
        newClassToAdd = "cero";
        newHtml = "El numero ingresado " + value + " es el Cero!"
    } else if (value % 2 == 0) {
        newClassToAdd = "par";
        newHtml = "El numero ingresado " + value + " es el PAR!"
    } else {
        newClassToAdd = "impar";
        newHtml = "El numero ingresado " + value + " es el IMPAR!"
    }

    $("#salida").addClass(newClassToAdd);
    $("#salida").html(newHtml);
    $("#salida").addClass("show");

}


function cleanOutput() {

    $("#salida").removeClass("show");
    $("#salida").html("");
    $("#salida").removeClass("cero");
    $("#salida").removeClass("par");
    $("#salida").removeClass("impar");
}

function removeClassError(target) {
    $(target).removeClass("error");
}