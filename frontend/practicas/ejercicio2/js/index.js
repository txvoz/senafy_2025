$(function () {

    $("#txtNombre, #slcGnero, #txtEdad").on("change", onChangeInputWithErrorClass);
    $("#btnValidar").click(onClickButton);

});

var onChangeInputWithErrorClass = function (e) {
    removeClassError(e.target);
}

var onClickButton = function (e) {
    e.preventDefault();
    var isFormValid = true;

    if($("#txtNombre").val()===""){
        $("#txtNombre").addClass("error");
        isFormValid = false;
    }

    if($("#slcGnero").val()===""){
        $("#slcGnero").addClass("error");
        isFormValid = false;
    }

    if($("#txtEdad").val()===""){
        $("#txtEdad").addClass("error");
        isFormValid = false;
    }

    if(!isFormValid){
        alert("Formulario incompleto!");
        return;
    }

    alert("Formulario completo");

}


function removeClassError(target) {
    $(target).removeClass("error");
}